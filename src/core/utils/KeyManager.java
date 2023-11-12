package core.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.cert.Certificate;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;

import java.security.interfaces.RSAPrivateKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyManager {
	
    private static char[] keystorePassword = "aTiansKeyStore".toCharArray();
    
    // store private key ( public key -> pem )
    private static KeyStore keyStore = null;
 
	
    /**
     * 保存 keystore 到文件
     * @throws Exception
     */
	public static void saveKeyStoreFile(String keystorePath) throws Exception{
        FileOutputStream fos = new FileOutputStream(keystorePath);
        keyStore.store(fos, keystorePassword);
        fos.close();
	}
	
	public static void addKeyToStore(Key key, String alias) throws Exception {
		
		keyStore.setKeyEntry(alias, key, keystorePassword, null);
		
	}
	
	
	/**
	 * 获取已有keystore保存的信息
	 * @throws Exception
	 */
	public static void readKeyStoreFile(String keystorePath) throws Exception {
		 clearKeyStore();
		 keyStore = KeyStore.getInstance("JKS");
		 
		 try {
			 FileInputStream fis = new FileInputStream(keystorePath);
	         keyStore.load(fis, keystorePassword);
	         fis.close();
		 }catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 清空keyStore中缓存的密钥信息
	 */
	public static void clearKeyStore() {
		keyStore = null;
	}
	
	/**
	 * 根据 传递的密钥对 初始化待保存的 keystore
	 * @throws Exception
	 */
	public static void initKeyStore() throws Exception{
		keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, null);
        
        Certificate[] virtualCertArray = new Certificate[1];
	    
        keyStore.setKeyEntry("private_key", RsaUtil.getPrivateKey(), 
        		"RK".toCharArray(), virtualCertArray); 
	}
	
	/**
	 * 解析读取的KeyStore,密钥对存储至RSAUtil
	 */
	public static void parseKeyStore() throws Exception{
		
		// 获取私钥
		String alias = "private_key";
		PasswordProtection keyProtection = new PasswordProtection("RK".toCharArray());
        SecretKeyEntry rkEntry = (SecretKeyEntry)keyStore.getEntry(alias, keyProtection);
        
        // 赋值至RSAUtil
        RsaUtil.setPrivateKey((RSAPrivateKey)rkEntry.getSecretKey());
        
	}
	
	public static void writeKeyToPemFile(String filePath, Key key) throws Exception {
		try(FileWriter fileWriter = new FileWriter(filePath)){
		
	        // 获取公钥的字节数组
	        byte[] keyBytes = key.getEncoded();
	
	        // 对公钥进行Base64编码
	        String base64Key = Base64.getEncoder().encodeToString(keyBytes);
	
	        // 将Base64编码后的公钥按64个字符一行写入PEM文件
	        int lineLength = 64;
	        for (int i = 0; i < base64Key.length(); i += lineLength) {
	            int endIndex = Math.min(i + lineLength, base64Key.length());
	            fileWriter.write(base64Key.substring(i, endIndex));
	            fileWriter.write("\n");
	        }
		}
        
    }
   

	public static Key readKeyFromPemFile(String filePath, String keyType) throws Exception {
		
		if(FileUtil.fileExists(filePath) == false) {
			return null;
		}
		
		String algorithm= "RSA";
	    // 从PEM文件读取Base64编码的密钥
	    StringBuilder keyBuilder = new StringBuilder();
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            keyBuilder.append(line);
	        }
	    }
	
	    // 解码Base64编码的公钥
	    byte[] keyBytes = Base64.getDecoder().decode(keyBuilder.toString());
	
	    // 根据密钥类型构造KeySpec对象
	    KeySpec keySpec;
	    KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        if ("public".equalsIgnoreCase(keyType)) {
        	
            keySpec = new X509EncodedKeySpec(keyBytes);
            return keyFactory.generatePublic(keySpec);
            
        } else if ("private".equalsIgnoreCase(keyType)) {
        	
        	// 使用PKCS8EncodedKeySpec创建私钥规范
            keySpec = new PKCS8EncodedKeySpec(keyBytes);
            return keyFactory.generatePrivate(keySpec);
            
        } else {
        	
            throw new IllegalArgumentException("Invalid key type");
        }
        
	}

	
}
