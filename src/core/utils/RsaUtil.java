package core.utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RsaUtil {
	
	private static int KEY_SIZE = 2048;
	private static PrivateKey privateKey = null;
	public static PublicKey publicKey = null;
	
	public static KeyPair rsaKeyGeneration() throws NoSuchAlgorithmException {
		 // 创建RSA密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(KEY_SIZE);

        // 生成RSA密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
        
		return keyPair;
	}
	
	public static byte[] rsaEncrypt(byte[] bytes, Key key) throws NoSuchAlgorithmException,
		NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
        
        return cipher.doFinal(bytes);
	}
	
	public static byte[] rsaDecrypt(byte[] bytes, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, 
		InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		return cipher.doFinal(bytes);
		
	}

	public static Key getPrivateKey() {

		return privateKey;
	}
	

}
