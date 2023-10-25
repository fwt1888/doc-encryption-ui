package core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class HashUtil {
	
	private static String HASH_ALGORITHM = "SHA-1";
	private static int BUFFER_SIZE = 8192; //�鴦��Ĭ�ϻ�������С 8kb
	
	// Integer: �ֽ�����String: �㷨��
	public static Map<Integer, String> ALGORITHM_MAP = null;
	// ��̬��ʼ���飬ֻ�������ʱִ��һ��
	static {
       initTreeMap();
    }
	
	/**
	 * �����������ݵ�HASH������ǰ�ж��������Ƿ񳬹�BUFFER_SIZE��
	 * @param bytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] getHash(byte[] bytes) throws Exception {
		if(bytes.length > BUFFER_SIZE) {
			throw new Exception("Length of bytes has exceeded buffer size"
					+ BUFFER_SIZE);
		}
        MessageDigest sha = MessageDigest.getInstance(HASH_ALGORITHM);

        // ����ɢ���㷨�����룬���Զ�ε����Դ�����ļ�
        sha.update(bytes);
        // ����ɢ��ֵ
        byte[] hash = sha.digest();
        
        return hash;
	}
	

	/**
	 * �����ļ���HASH
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static byte[] getHash(String filePath) throws Exception {
        MessageDigest hasher = MessageDigest.getInstance(HASH_ALGORITHM);

        byte[] buffer = new byte[BUFFER_SIZE]; 
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                hasher.update(buffer, 0, bytesRead);
            }
        }
        byte[] hash = hasher.digest();
        
        return hash;
	    }
	
	public static void changeAlgorithm(String algorithm) {
		HASH_ALGORITHM = algorithm;
	}
	
	/**
	 * ������Ҫ����MAC��info�ĳ��ȣ�ѡ��ͬHASH�㷨
	 * @param infoLength
	 */
	public static void chooseAlgorithm(long infoLength) {
		for (Map.Entry<Integer, String> entry : ALGORITHM_MAP.entrySet()) {
			int key = entry.getKey();
            if(infoLength>=key)
            	changeAlgorithm(entry.getValue());
            else
            	break;
        }
		
	}
	
	public static void initTreeMap() {
		ALGORITHM_MAP = new TreeMap<>();
		ALGORITHM_MAP.put(16, "MD5");
		ALGORITHM_MAP.put(20, "SHA-1");
		ALGORITHM_MAP.put(32, "SHA3-256");
		ALGORITHM_MAP.put(48, "SHA3-384");
		ALGORITHM_MAP.put(64, "SHA3-512");
	}
	
	public static void changeBufferSize(int newBufferSize) {
		BUFFER_SIZE = newBufferSize;
	}

}
