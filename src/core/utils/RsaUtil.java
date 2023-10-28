package core.utils;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RsaUtil {
	
	private static int KEY_SIZE = 2048;
	private static RSAPrivateKey privateKey = null;
	public static RSAPublicKey publicKey = null;
	
	public static KeyPair rsaKeyGeneration() throws NoSuchAlgorithmException {
		 // ����RSA��Կ��������
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(KEY_SIZE);

        // ����RSA��Կ��
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();
        publicKey = (RSAPublicKey) keyPair.getPublic();
        
		return keyPair;
	}
	
	/**
	 * �������ݣ�����Ҫ���û�����
	 * @param bytes
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] rsaEncrypt(byte[] bytes, Key key) throws Exception {
		
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
        
        return cipher.doFinal(bytes);
	}
	
	public static byte[] rsaDecrypt(byte[] bytes, Key key) throws Exception {
		
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		return cipher.doFinal(bytes);
		
	}

	public static Key getPrivateKey() {

		return privateKey;
	}
	
	/**
	 * ��ȡ ������Կ ���ѡȡ��ģ��n
	 * @return
	 */
	public static BigInteger getModulus() {
		return publicKey.getModulus();
	}
	
	public static void setPrivateKey(RSAPrivateKey rk) {
		privateKey = rk;
	}
}
