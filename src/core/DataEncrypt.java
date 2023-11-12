package core;

import core.utils.RsaUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
public class DataEncrypt {
	
	public static UserAccount sender = null;
	public static UserAccount receiver = null;
	
	public static void fileEncrypt(String filePath) 
			throws Exception {
		
		byte[] encryptedBytes = encrypt(FileUtil.readBytesFromFile(filePath));
		
		String newPath = FileUtil.renameFile(filePath, "enc");
		
		FileUtil.writeBytesToFile(newPath, encryptedBytes);
		
	}
	
	public static String stringEncrypt(String s) throws Exception {
		
		byte[] encryptedBytes = encrypt(StringUtil.stringToByteArray(s));
		
		String encryptedString = ByteUtil.byteArrayToHex(encryptedBytes);
		
		return encryptedString;
		
	}
	
	/**
	 * 对字符串进行加密
	 * @param bytes 字符串比特流
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] bytes) throws Exception {
		
		System.out.println("M (HEX) : " + ByteUtil.byteArrayToHex(bytes));
		
		// 对明文HASH进行私钥签名
		byte[] hash = HashUtil.getHash(bytes);
		byte[] signedHash = RsaUtil.rsaEncrypt(hash, sender.getPrivateKey());
		
		// 组合1: 明文 + 签名后的HASH(MAC)
		byte[] c1 = ByteUtil.mergeByteArrays(signedHash, bytes);
		// 添加MAC长度字段(最开始）
		c1 = ByteUtil.addLengthField(signedHash.length, c1);
		
		// 对组合1进行对称加密
		byte[] encryptedC1 = SymEnc.symEncrypt(c1);
		
		// 对称加密密钥获取
		byte[] symKeyBytes = SymEnc.getKeyBytes();
		
		// 对symKey进行公钥加密
		byte[] encryptedSKB = RsaUtil.rsaEncrypt(symKeyBytes, receiver.publicKey);
		
		// 组合2: 加密后的组合1 + 加密后的symKey
		byte[] c2 = ByteUtil.mergeByteArrays(encryptedSKB,encryptedC1);
		// 添加K长度字段（最开始）
		c2 = ByteUtil.addLengthField(encryptedSKB.length, c2);
		
		return c2;
			
	}
	
	/**
	 * 对文件进行加密
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static Boolean encrypt(String filePath) throws Exception {
		
		try {
			byte[] bytes = FileUtil.readBytesFromFile(filePath);
			// 获取后缀
	//		String extension = FileUtil.getFileExtension(filePath);
			String tempFile1 = FileUtil.renameFile(filePath,"tmp1");
			String tempFile2 = FileUtil.renameFile(filePath,"tmp2");
					
			// 对明文HASH进行私钥签名
			byte[] hash = HashUtil.getHash(filePath);
			byte[] signedHash = RsaUtil.rsaEncrypt(hash, sender.getPrivateKey());
			
			// 组合1: 明文 + 签名后的HASH(MAC)
			byte[] c1 = ByteUtil.mergeByteArrays(signedHash, bytes);
			// 添加MAC长度字段(最开始）
			c1 = ByteUtil.addLengthField(signedHash.length, c1);
		
			
			// 对组合1进行对称加密
			FileUtil.writeBytesToFile(tempFile1, c1);
			SymEnc.symEncrypt(tempFile1,tempFile2);
			byte[] encryptedC1 = FileUtil.readBytesFromFile(tempFile2);
			
			// 对称加密密钥获取
			byte[] symKeyBytes = SymEnc.getKeyBytes();
			
			// 对symKey进行公钥加密
			byte[] encryptedSKB = RsaUtil.rsaEncrypt(symKeyBytes, receiver.publicKey);
			
			// 组合2: 加密后的组合1 + 加密后的symKey
			byte[] c2 = ByteUtil.mergeByteArrays(encryptedSKB,encryptedC1);
			// 添加K长度字段（最开始）
			c2 = ByteUtil.addLengthField(encryptedSKB.length, c2);
			
			// 删除临时文件，将最终结果存入_enc
			Path path1 = Paths.get(tempFile1);
	        Path path2 = Paths.get(tempFile2);
	        Files.delete(path1);
	        Files.delete(path2);
			
			String finalFile = FileUtil.renameFile(filePath, "enc");
			FileUtil.writeBytesToFile(finalFile, c2);
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	
		return true;
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
	
    public static void setSenderAndRecevier(UserAccount sender, UserAccount receiver) {
    	DataEncrypt.sender =  sender;
    	DataEncrypt.receiver = receiver;
    }
	

}
