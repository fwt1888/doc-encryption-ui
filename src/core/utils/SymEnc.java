package core.utils;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SymEnc {
    //密钥算法
    private static String KEY_ALGORITHM = "DES";

    //加密算法/工作模式/填充方式
    private static String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    
    //对称加密 密钥
    private static byte[] symKeyBytes = null;
    private static Key symKey = null;
    

    /**对比特流加密
     * @param key 密钥(比特流)
     * @return 加密结果
     * @throws Exception
     */
    public static byte[] symEncrypt(byte[] bytes) throws Exception {
    	
    	// 先生成密钥
    	symKeyGeneration();
    	
    	// 再加密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(symKeyBytes, KEY_ALGORITHM));
        return cipher.doFinal(bytes);
    }
    
    
    /**对数据解密
     * @param bytes utf8编码的二进制数据
     * @param key 密钥(比特流)
     * @return 解密结果
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
     * 对称加密密钥生成
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static void symKeyGeneration() throws NoSuchAlgorithmException {
    	// 创建一个伪随机数生成器并提供种子
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(System.currentTimeMillis()); // 使用当前时间作为种子

        // 创建DES密钥生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
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


	public static Key getKey() {
		
		return symKey;
	}

	public static byte[] getKeyBytes() {
		return symKeyBytes;
	}
    
}
