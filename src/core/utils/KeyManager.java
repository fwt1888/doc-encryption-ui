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
     * ���� keystore ���ļ�
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
	 * ��ȡ����keystore�������Ϣ
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
	    	// ��� KeyStore �ļ������ڣ�����һ���µ�
	         keyStore.load(null, keystorePassword); 
	     }
	}
	
	/**
	 * ���keyStore�л������Կ��Ϣ
	 */
	public static void clearKeyStore() {
		keyStore = null;
	}

	/**
	 * �ı�keystore��ŵ�Ŀ¼·��
	 */
	public static void changeKeystoreDir(String newDir) {
		keystoreDir = newDir;
	}
	
	public static void changeKeystoreName(String newName) {
		keystoreName = newName;
	}
}
