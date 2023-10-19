package core.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DESUtils {
    //��Կ�㷨
    private static final String KEY_ALGORITHM = "DES";

    //�����㷨/����ģʽ/��䷽ʽ
    private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    
    

    /**ʹ��DES���ַ�������
     * @param str utf8������ַ���
     * @param key ��Կ��8�ֽڣ�
     * @return ���ܽ��
     * @throws Exception
     */
    public static byte[] desEncrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), KEY_ALGORITHM));
        byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
        return  bytes;
    }
    
    
    /**ʹ��DES�����ݽ���
     * @param bytes utf8����Ķ���������
     * @param key ��Կ��8�ֽڣ�
     * @return ���ܽ��
     * @throws Exception
     */
    public static String desDecrypt(byte[] bytes, String key) throws Exception {
        if (bytes == null || key == null) return null;
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), KEY_ALGORITHM));
        bytes = cipher.doFinal(bytes);
        return new String(bytes, "utf-8");
    }
    
    public static String desKeyGeneration() throws NoSuchAlgorithmException {
    	// ����һ��α��������������ṩ����
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(System.currentTimeMillis()); // ʹ�õ�ǰʱ����Ϊ����

        // ����DES��Կ������
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(secureRandom);

        // ����DES��Կ
        SecretKey secretKey = keyGenerator.generateKey();

        // ��ȡ���ɵ���Կ���ֽ�����
        byte[] keyBytes = secretKey.getEncoded();

		return ByteUtils.bytesToHex(keyBytes);
    	
    }

    
}
