package core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class HashUtil {
	
	private static String HASH_ALGORITHM = "SHA-1";
	private static int BUFFER_SIZE = 8192; //块处理默认缓冲区大小 8kb
	
	// Integer: 字节数，String: 算法名
	public static Map<Integer, String> ALGORITHM_MAP = null;
	// 静态初始化块，只在类加载时执行一次
	static {
       initTreeMap();
    }
	
	/**
	 * 计算少量数据的HASH（调用前判断数据量是否超过BUFFER_SIZE）
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

        // 更新散列算法的输入，可以多次调用以处理大文件
        sha.update(bytes);
        // 计算散列值
        byte[] hash = sha.digest();
        
        return hash;
	}
	

	/**
	 * 计算文件的HASH
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
	 * 根据需要生成MAC的info的长度，选择不同HASH算法
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
