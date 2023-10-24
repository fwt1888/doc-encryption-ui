package core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
	
	private static String HASH_ALGORITHM = "SHA-1";
	
	public static byte[] getHash(byte[] bytes) throws NoSuchAlgorithmException {
	        MessageDigest sha = MessageDigest.getInstance(HASH_ALGORITHM);
	
	        // ����ɢ���㷨�����룬���Զ�ε����Դ�����ļ�
	        sha.update(bytes);
	        // ����ɢ��ֵ
	        byte[] hash = sha.digest();
	        
	        return hash;
	    }
	
	public static void changeAlgorithm(String algorithm) {
		HASH_ALGORITHM = algorithm;
	}
	

}
