package core.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;


public class KeyManager {
	
	private static String keystoreDir = "./";
	private static String keystoreName = "keystore";
    private static char[] keystorePassword = "aTiansKeyStore".toCharArray();
    private static KeyStore keyStore = null;
	
    /**
     * 保存 keystore 到文件
     * @throws Exception
     */
	public static void saveKeyStoreFile() throws Exception{
		String keystorePath = keystoreDir + keystoreName + ".jks";
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
	public static void readKeyStoreFile() throws Exception {
		 String keystorePath = keystoreDir + keystoreName + ".jks";
		 keyStore = KeyStore.getInstance("JKS");
		 try {
			 FileInputStream fis = new FileInputStream(keystorePath);
	         keyStore.load(fis, keystorePassword);
	         fis.close();
		 } catch (Exception e) {
	    	// 如果 KeyStore 文件不存在，创建一个新的
	         keyStore.load(null, keystorePassword); 
	     }
	}
	
	/**
	 * 清空keyStore中缓存的密钥信息
	 */
	public static void clearKeyStore() {
		keyStore = null;
	}

	/**
	 * 改变keystore存放的目录路径
	 */
	public static void changeKeystoreDir(String newDir) {
		keystoreDir = newDir;
	}
	
	public static void changeKeystoreName(String newName) {
		keystoreName = newName;
	}
}
