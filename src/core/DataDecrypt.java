package core;

import java.util.Arrays;

import core.utils.ByteUtil;
import core.utils.FileUtil;
import core.utils.HashUtil;
import core.utils.RsaUtil;
import core.utils.SymEnc;

/**
 * whole process of decryption
 * @author 14391
 *
 */
public class DataDecrypt {
	public static UserAccount sender = null;
	public static UserAccount receiver = null;
	
	public static void fileDecrypt(String filePath) 
			throws Exception {
		
		byte[] decryptedBytes = decrypt(FileUtil.readBytesFromFile(filePath));
		
		String newPath = FileUtil.renameFile(filePath, "dec");
		
		FileUtil.writeBytesToFile(newPath, decryptedBytes);
		
	}
	
	public static String stringDecrypt(String s) throws Exception {
		
		byte[] decryptedBytes = decrypt(ByteUtil.hexStringToByteArray(s));
		
		// HASH验证失败
		if(decryptedBytes == null) { return null;}
		
		String decryptedString = ByteUtil.byteArrayToHex(decryptedBytes);
		
		return decryptedString;
		
	}
	
	/**
	 * 对比特流解密
	 * @param bytes
	 * @return
	 * @throws Exception 
	 */
	public static byte[] decrypt(byte[] bytes) throws Exception {
		
		// 组合2: 加密后的组合1 + 加密后的symKey
		// 获取symKey长度
		int skLength = ByteUtil.getLengthField(4, bytes);
		byte[] encSKB = new byte[skLength];
		// 获取K
		System.arraycopy(bytes,4,encSKB,0,skLength);
		
		// 删除K长度字段 + k
		bytes = ByteUtil.removePartFromArray(4 + skLength, bytes);
		
		// receiver 私钥 解密K
		SymEnc.setSymKeyBytes(
				RsaUtil.rsaDecrypt(encSKB, receiver.getPrivateKey()));
		
		// K解密 明文 + MAC
		byte[] m = SymEnc.symDecrypt(bytes);
		
		// 获取MAC长度 + MAC
		int macLength = ByteUtil.getLengthField(4, m);
		byte[] encMAC = new byte[macLength];
		System.arraycopy(m, 4, encMAC, 0, macLength);
		
		// 删除MAC长度字段 + MAC
		m = ByteUtil.removePartFromArray(4 + macLength, m);
		
		// 明文计算HASH
		byte[] hashFromM = HashUtil.getHash(m);
		
		// sender 公钥 验证MAC
		byte[] hashFromMAC = RsaUtil.rsaDecrypt(encMAC, 
				sender.publicKey);
		
		if (Arrays.equals(hashFromM, hashFromMAC)) {
			return m;
		}

		
		return null;
		
	}
	
	
	/**
	 * 对文件解密
	 */
    public static Boolean decrypt(String filePath) {
    	try {
    		byte[] bytes = FileUtil.readBytesFromFile(filePath);
			// 获取后缀
	//		String extension = FileUtil.getFileExtension(filePath);
			String tempFile1 = FileUtil.renameFile(filePath,"tmp1");
			String tempFile2 = FileUtil.renameFile(filePath,"tmp2");
    		
    		// 获取symKey长度
    		int skLength = ByteUtil.getLengthField(4, bytes);
    		byte[] encSKB = new byte[skLength];
    		// 获取K
    		System.arraycopy(bytes,4,encSKB,0,skLength);
    		// 删除K长度字段 + k
    		bytes = ByteUtil.removePartFromArray(4 + skLength, bytes);
    		
    		// receiver 私钥 解密K
    		SymEnc.setSymKeyBytes(
    				RsaUtil.rsaDecrypt(encSKB, receiver.getPrivateKey()));
    		
    		// K解密 明文 + MAC
    		FileUtil.writeBytesToFile(tempFile1, bytes);
    		SymEnc.symDecrypt(tempFile1, tempFile2);
    		byte[] m = FileUtil.readBytesFromFile(tempFile2);
    		
    		// 获取MAC长度 + MAC
    		int macLength = ByteUtil.getLengthField(4, m);
    		byte[] encMAC = new byte[macLength];
    		System.arraycopy(m, 4, encMAC, 0, macLength);
    		
    		// 删除MAC长度字段 + MAC
    		m = ByteUtil.removePartFromArray(4 + macLength, m);
    		String finalFile = FileUtil.renameFile(filePath, "dec");
			FileUtil.writeBytesToFile(finalFile, m);
    		
    		// 明文计算HASH
    		byte[] hashFromM = HashUtil.getHash(finalFile);
    		
    		// sender 公钥 验证MAC
    		byte[] hashFromMAC = RsaUtil.rsaDecrypt(encMAC, 
    				sender.publicKey);
        	if (hashFromM.equals(hashFromMAC)) {
    			return true;
    		}
        	else {
        		return false;
        	}
    		
    	}catch(Exception e) {
    		System.out.println(e);
    		return false;
    	}
    }
    
	/**
	 * 初始化解密设置
	 * @param hashAlgo 哈希算法
	 */
	public static void initDecryption(String hashAlgo, String encAlgo,
			String encMode, String padMode) {
		
		SymEnc.initAlgorithm(encAlgo, encMode, padMode); 
		HashUtil.changeAlgorithm(hashAlgo);
		
	}
    
    public static void setSenderAndRecevier(UserAccount sender, UserAccount receiver) {
    	DataDecrypt.sender =  sender;
    	DataDecrypt.receiver = receiver;
    }
    
   
}
