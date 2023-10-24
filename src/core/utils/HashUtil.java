package core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
	
	private static String HASH_ALGORITHM = "SHA-1";
	
	public static byte[] getHash(byte[] bytes) throws NoSuchAlgorithmException {
	        MessageDigest sha = MessageDigest.getInstance(HASH_ALGORITHM);
	
	        // 更新散列算法的输入，可以多次调用以处理大文件
	        sha.update(bytes);
	        // 计算散列值
	        byte[] hash = sha.digest();
	        
	        return hash;
	    }
	
	public static void changeAlgorithm(String algorithm) {
		HASH_ALGORITHM = algorithm;
	}
	

}
