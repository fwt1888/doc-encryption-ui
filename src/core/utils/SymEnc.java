package core.utils;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SymEnc {
    //��Կ�㷨
    private static String KEY_ALGORITHM = "DES";

    //�����㷨/����ģʽ/��䷽ʽ
    private static String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    
    //�ԳƼ��� ��Կ
    private static byte[] symKeyBytes = null;
    private static Key symKey = null;
    

    /**�Ա���������
     * @param key ��Կ(������)
     * @return ���ܽ��
     * @throws Exception
     */
    public static byte[] symEncrypt(byte[] bytes) throws Exception {
    	
    	// ��������Կ
    	symKeyGeneration();
    	
    	// �ټ���
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(symKeyBytes, KEY_ALGORITHM));
        return cipher.doFinal(bytes);
    }
    
    
    /**�����ݽ���
     * @param bytes utf8����Ķ���������
     * @param key ��Կ(������)
     * @return ���ܽ��
     * @throws Exception
     */
    public static String symDecrypt(byte[] bytes) throws Exception {
        if (bytes == null || symKey == null) return null;
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(symKeyBytes, KEY_ALGORITHM));
        bytes = cipher.doFinal(bytes);
        return new String(bytes, "utf-8");
    }
    
    /**
     * �ԳƼ�����Կ����
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static void symKeyGeneration() throws NoSuchAlgorithmException {
    	// ����һ��α��������������ṩ����
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(System.currentTimeMillis()); // ʹ�õ�ǰʱ����Ϊ����

        // ����DES��Կ������
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(secureRandom);

        // ����DES��Կ
        SecretKey secretKey = keyGenerator.generateKey();

        // ��ȡ���ɵ���Կ���ֽ�����
		symKeyBytes = secretKey.getEncoded();
    	symKey = secretKey;
    }
    
    
    /**
     * ���ü����㷨������ģʽ�����ģʽ
     * @param encAlgo
     */
    public static void initAlgorithm(String encAlgo, String encMode, String padMode) {
    	
    	KEY_ALGORITHM = encAlgo;
    	CIPHER_ALGORITHM = encAlgo + "/" + encMode + "/" + padMode;
    	
    }


	public static Key getKey() {
		
		return symKey;
	}

	public static byte[] getKeyBytes() {
		return symKeyBytes;
	}
    
}
