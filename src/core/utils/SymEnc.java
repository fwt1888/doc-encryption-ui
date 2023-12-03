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
 * �ԳƼ��� <- ������, ��Ҫ���û�����
 * @author fwt1888
 *
 */
public class SymEnc {
    //��Կ�㷨
    private static String KEY_ALGORITHM = "DES";

    //�����㷨/����ģʽ/��䷽ʽ
    private static String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    private static int BUFFER_SIZE = 8192; //�鴦��Ĭ�ϻ�������С 8kb
    
    //�ԳƼ��� ��Կ
    private static byte[] symKeyBytes = null;
    private static Key symKey = null;
    

    /**���ַ�������
     * @param key ��Կ(������)
     * @return ���ܽ��
     * @throws Exception
     */
    public static byte[] symEncrypt(byte[] bytes) throws Exception {
    	
    	// ��������Կ
    	if("seed".equals(UserSettings.symKey))
    		symKeyGeneration();
    	
    	System.out.println("Sym Key for Encrypt: " + ByteUtil.byteArrayToHex(symKeyBytes));
    	
    	// �ټ���
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(symKeyBytes, KEY_ALGORITHM));
        return cipher.doFinal(bytes);
    }
    
    
    /**
     * ���ļ�����
     */
    public static void symEncrypt(String inputFile, String outputFile) throws Exception {
    	
    	// ��������Կ
    	if("seed".equals(UserSettings.symKey))
    		symKeyGeneration();
    	
    	// �ټ���
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(symKeyBytes, KEY_ALGORITHM));
        
    	FileInputStream inputFileStream = new FileInputStream(inputFile);
        FileOutputStream outputFileStream = new FileOutputStream(outputFile);
        // ����8KB�����ݻ�����
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        // ����ȡ�����ܺ�д������
        while ((bytesRead = inputFileStream.read(buffer)) != -1) {
            byte[] encryptedBytes = cipher.update(buffer, 0, bytesRead);
            outputFileStream.write(encryptedBytes);
        }
        // ������ܵ�ʣ�����ݿ�
        byte[] finalBytes = cipher.doFinal();
        outputFileStream.write(finalBytes);

        // �ر��ļ���
        inputFileStream.close();
        outputFileStream.close();

        System.out.println("Encryption completed.");

    }
    
    
    /**�����ݽ���
     * @param bytes utf8����Ķ���������
     * @param key ��Կ(������)
     * @return ���ܽ��
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
     * ���ļ����н���
     * @param inputFile
     * @param outputFile
     * @return
     */
    public static byte[] symDecrypt(String inputFile, String outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(symKeyBytes, KEY_ALGORITHM));
        
    	FileInputStream inputFileStream = new FileInputStream(inputFile);
        FileOutputStream outputFileStream = new FileOutputStream(outputFile);
        // ����8KB�����ݻ�����
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        // ����ȡ�����ܺ�д������
        while ((bytesRead = inputFileStream.read(buffer)) != -1) {
            byte[] decryptedBytes = cipher.update(buffer, 0, bytesRead);
            outputFileStream.write(decryptedBytes);
        }
        // ������ܵ�ʣ�����ݿ�
        byte[] finalBytes = cipher.doFinal();
        outputFileStream.write(finalBytes);

        // �ر��ļ���
        inputFileStream.close();
        outputFileStream.close();

        System.out.println("Decryption completed.");
    	
    	
		return symKeyBytes;
    	
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
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
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
