package core.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;



public class KeyManager {
	
	private static String keystoreDir = ".";
	private static String keystoreName = "keystore";
    private static char[] keystorePassword = "aTiansKeyStore".toCharArray();
    private static KeyStore keyStore = null;
	
    /**
     * ���� keystore ���ļ�
     * @throws Exception
     */
	public static void saveKeyStoreFile() throws Exception{
		String keystorePath = keystoreDir + "/" + keystoreName + ".jks";
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
		 clearKeyStore();
		 String keystorePath = keystoreDir + keystoreName + ".jks";
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
	
	/**
	 * ���� ���ݵ���Կ�� ��ʼ��������� keystore
	 * @throws Exception
	 */
	public static void initKeyStore() throws Exception{
		keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, null);

        keyStore.setKeyEntry("private_key", RsaUtil.getPrivateKey(), 
        		"RK".toCharArray(), null); 
        keyStore.setKeyEntry("public_key", RsaUtil.publicKey, 
        		"PK".toCharArray(), null);
	}
	
	/**
	 * ������ȡ��KeyStore,��Կ�Դ洢��RSAUtil
	 */
	public static void parseKeyStore() throws Exception{
		
		// ��ȡ˽Կ
		String alias = "private_key";
		PasswordProtection keyProtection = new PasswordProtection("RK".toCharArray());
        SecretKeyEntry rkEntry = (SecretKeyEntry)keyStore.getEntry(alias, keyProtection);
        
        // ��ȡ��Կ
        alias = "public_key";
        keyProtection = new PasswordProtection("PK".toCharArray());
        SecretKeyEntry pkEntry = (SecretKeyEntry)keyStore.getEntry(alias, keyProtection);
        
        // ��ֵ��RSAUtil
        RsaUtil.publicKey = (RSAPublicKey)pkEntry.getSecretKey();
        RsaUtil.setPrivateKey((RSAPrivateKey)rkEntry.getSecretKey());
	}
}
