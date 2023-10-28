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
		 // 创建RSA密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(KEY_SIZE);

        // 生成RSA密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();
        publicKey = (RSAPublicKey) keyPair.getPublic();
        
		return keyPair;
	}
	
	/**
	 * 少量数据，不需要设置缓冲区
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
	 * 获取 生成密钥 随机选取的模数n
	 * @return
	 */
	public static BigInteger getModulus() {
		return publicKey.getModulus();
	}
	
	public static void setPrivateKey(RSAPrivateKey rk) {
		privateKey = rk;
	}
}
