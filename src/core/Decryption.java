package core;

import core.utils.ByteUtil;
import core.utils.FileUtil;
import core.utils.HashUtil;
import core.utils.RsaUtil;
import core.utils.StringUtil;
import core.utils.SymEnc;

/**
 * whole process of decryption
 * @author 14391
 *
 */
public class Decryption {
	public static UserAccount sender = null;
	public static UserAccount receiver = null;
	
	public static void fileDecrypt(String filePath) 
			throws Exception {
		
		byte[] decryptedBytes = decrypt(FileUtil.readBytesFromFile(filePath));
		
		String newPath = FileUtil.renameFile(filePath, "dec");
		
		FileUtil.writeBytesToFile(newPath, decryptedBytes);
		
	}
	
	public static String stringDecrypt(String s) throws Exception {
		
		byte[] decryptedBytes = decrypt(StringUtil.stringToByte(s));
		
		String decryptedString = StringUtil.byteToString(decryptedBytes);
		
		return decryptedString;
		
	}
	
	/**
	 * 对比特流解密
	 * @param bytes
	 * @return
	 */
	public static byte[] decrypt(byte[] bytes) {
		
		// 组合2: 加密后的组合1 + 加密后的symKey
		// 获取symKey长度
		int skLength = ByteUtil.getLengthField(4, bytes);
		byte[] encSKB = new byte[skLength];
		System.arraycopy(bytes,);
		
		// 对明文HASH进行私钥签名
		byte[] hash = HashUtil.getHash(bytes);
		byte[] signedHash = RsaUtil.rsaEncrypt(hash, sender.getPrivateKey());
		
		// 组合1: 明文 + 签名后的HASH(MAC)
		byte[] c1 = ByteUtil.mergeByteArrays(bytes, signedHash);
		
		// 对组合1进行对称加密
		byte[] encryptedC1 = SymEnc.symEncrypt(c1);
		
		// 对称加密密钥获取
		byte[] symKeyBytes = SymEnc.getKeyBytes();
		
		// 对symKey进行公钥加密
		byte[] encryptedSKB = RsaUtil.rsaEncrypt(symKeyBytes, receiver.publicKey);
		
		
		byte[] c2 = ByteUtil.mergeByteArrays(encryptedC1, encryptedSKB);
		
		return c2;
		
		
		
		return bytes;
		
	}
	
	
	/**
	 * 对文件解密
	 */
    public static Boolean decrypt(String filePath) {
    	try {
    		
    		
    		
    		
    		
    	}catch(Exception e) {
    		System.out.println(e);
    		return false;
    	}
		return true;
    	
    }
   
}
