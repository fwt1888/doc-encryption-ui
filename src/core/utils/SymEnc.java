package core.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 对称加密 <- 大数据, 需要设置缓冲区
 * @author fwt1888
 *
 */
public class SymEnc {
    //密钥算法
    private static String KEY_ALGORITHM = "DES";

    //加密算法/工作模式/填充方式
    private static String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    private static int BUFFER_SIZE = 8192; //块处理默认缓冲区大小 8kb
    
    //对称加密 密钥
    private static byte[] symKeyBytes = null;
    private static Key symKey = null;
    

    /**对字符串加密
     * @param key 密钥(比特流)
     * @return 加密结果
     * @throws Exception
     */
    public static byte[] symEncrypt(byte[] bytes) throws Exception {
    	
    	// 先生成密钥
    	if("seed".equals(UserSettings.symKey))
    		symKeyGeneration();
    	
    	System.out.println("Sym Key for Encrypt: " + ByteUtil.byteArrayToHex(symKeyBytes));
    	
    	// 再加密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(symKeyBytes, KEY_ALGORITHM));
        return cipher.doFinal(bytes);
    }
    
    
    /**
     * 对文件加密
     */
    public static void symEncrypt(String inputFile, String outputFile) throws Exception {
    	
    	// 先生成密钥
    	if("seed".equals(UserSettings.symKey))
    		symKeyGeneration();
    	
    	// 再加密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(symKeyBytes, KEY_ALGORITHM));
        
    	FileInputStream inputFileStream = new FileInputStream(inputFile);
        FileOutputStream outputFileStream = new FileOutputStream(outputFile);
        // 创建8KB的数据缓冲区
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        // 逐块读取、加密和写入数据
        while ((bytesRead = inputFileStream.read(buffer)) != -1) {
            byte[] encryptedBytes = cipher.update(buffer, 0, bytesRead);
            outputFileStream.write(encryptedBytes);
        }
        // 处理可能的剩余数据块
        byte[] finalBytes = cipher.doFinal();
        outputFileStream.write(finalBytes);

        // 关闭文件流
        inputFileStream.close();
        outputFileStream.close();

        System.out.println("Encryption completed.");

    }
    
    
    /**对数据解密
     * @param bytes utf8编码的二进制数据
     * @param key 密钥(比特流)
     * @return 解密结果
     * @throws Exception
     */
    public static byte[] symDecrypt(byte[] bytes) throws Exception {
    	
    	System.out.println("Sym Key for Decrypt: " + ByteUtil.byteArrayToHex(symKeyBytes));
    	
        if (bytes == null || symKey == null) return null;
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(symKeyBytes, KEY_ALGORITHM));
        return cipher.doFinal(bytes);
    }
    
    /**
     * 对文件进行解密
     * @param inputFile
     * @param outputFile
     * @return
     */
    public static byte[] symDecrypt(String inputFile, String outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(symKeyBytes, KEY_ALGORITHM));
        
    	FileInputStream inputFileStream = new FileInputStream(inputFile);
        FileOutputStream outputFileStream = new FileOutputStream(outputFile);
        // 创建8KB的数据缓冲区
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        // 逐块读取、解密和写入数据
        while ((bytesRead = inputFileStream.read(buffer)) != -1) {
            byte[] decryptedBytes = cipher.update(buffer, 0, bytesRead);
            outputFileStream.write(decryptedBytes);
        }
        // 处理可能的剩余数据块
        byte[] finalBytes = cipher.doFinal();
        outputFileStream.write(finalBytes);

        // 关闭文件流
        inputFileStream.close();
        outputFileStream.close();

        System.out.println("Decryption completed.");
    	
    	
		return symKeyBytes;
    	
    }
    
    
    
    
    /**
     * 对称加密密钥生成
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static void symKeyGeneration() throws NoSuchAlgorithmException {
    	// 创建一个伪随机数生成器并提供种子
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(System.currentTimeMillis()); // 使用当前时间作为种子

        // 创建DES密钥生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(secureRandom);

        // 生成DES密钥
        SecretKey secretKey = keyGenerator.generateKey();

        // 获取生成的密钥的字节数组
		symKeyBytes = secretKey.getEncoded();
    	symKey = secretKey;
    }
    
    
    /**
     * 设置加密算法、加密模式、填充模式
     * @param encAlgo
     */
    public static void initAlgorithm(String encAlgo, String encMode, String padMode) {
    	
    	KEY_ALGORITHM = encAlgo;
    	CIPHER_ALGORITHM = encAlgo + "/" + encMode + "/" + padMode;
    	
    }
    
    public static void setSymKeyBytes(byte[] bytes) {
    	
    	symKeyBytes = bytes;
    }


	public static Key getKey() {
		
		return symKey;
	}

	public static byte[] getKeyBytes() {
		return symKeyBytes;
	}
    
	public static void changeBufferSize(int newBufferSize) {
		BUFFER_SIZE = newBufferSize;
	}


	public static void setProvidedSymKey(String providedSymKey) {
		symKeyBytes = StringUtil.stringToByteArray(providedSymKey);	
	}
}
