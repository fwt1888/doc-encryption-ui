package core;

import core.utils.RsaUtil;
import core.utils.ByteUtil;
import core.utils.FileUtil;
import core.utils.HashUtil;
import core.utils.StringUtil;
import core.utils.SymEnc;

/**
 * whole process of encryption
 * @author 14391
 *
 */
public class Encryption {
	
	public static UserAccount sender = null;
	public static UserAccount receiver = null;
	
	public static void fileEncrypt(String filePath) 
			throws Exception {
		
		byte[] encryptedBytes = encrypt(FileUtil.readBytesFromFile(filePath));
		
		String newPath = FileUtil.renameFile(filePath, "encrypted");
		
		FileUtil.writeBytesToFile(newPath, encryptedBytes);
		
	}
	
	public static String stringEncrypt(String s) throws Exception {
		
		byte[] encryptedBytes = encrypt(StringUtil.stringToByte(s));
		
		String encryptedString = StringUtil.byteToString(encryptedBytes);
		
		return encryptedString;
		
	}
	
	public static byte[] encrypt(byte[] bytes) throws Exception {
		
		// 对明文HASH进行私钥签名
		byte[] hash = HashUtil.getHash(bytes);
		byte[] signedHash = RsaUtil.rsaEncrypt(bytes, sender.getPrivateKey());
		
		// 组合1: 明文 + 签名后的HASH(MAC)
		byte[] c1 = ByteUtil.mergeByteArrays(bytes, signedHash);
		
		// 对组合1进行对称加密
		byte[] encryptedC1 = SymEnc.symEncrypt(c1);
		
		// 对称加密密钥获取
		byte[] symKeyBytes = SymEnc.getKeyBytes();
		
		// 对symKey进行公钥加密
		byte[] encryptedSKB = RsaUtil.rsaEncrypt(symKeyBytes, receiver.publicKey);
		
		// 组合2: 加密后的组合1 + 加密后的symKey
		byte[] c2 = ByteUtil.mergeByteArrays(encryptedC1, encryptedSKB);
		
		return c2;
		
		
	}
	
	
	/**
	 * 初始化加密设置
	 * @param hashAlgo 哈希算法
	 */
	public static void initEncryption(String hashAlgo, String encAlgo,
			String encMode, String padMode) {
		
		SymEnc.initAlgorithm(encAlgo, encMode, padMode); 
		HashUtil.changeAlgorithm(hashAlgo);
		
	}
	

}
