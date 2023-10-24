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
		
		// ������HASH����˽Կǩ��
		byte[] hash = HashUtil.getHash(bytes);
		byte[] signedHash = RsaUtil.rsaEncrypt(bytes, sender.getPrivateKey());
		
		// ���1: ���� + ǩ�����HASH(MAC)
		byte[] c1 = ByteUtil.mergeByteArrays(bytes, signedHash);
		
		// �����1���жԳƼ���
		byte[] encryptedC1 = SymEnc.symEncrypt(c1);
		
		// �ԳƼ�����Կ��ȡ
		byte[] symKeyBytes = SymEnc.getKeyBytes();
		
		// ��symKey���й�Կ����
		byte[] encryptedSKB = RsaUtil.rsaEncrypt(symKeyBytes, receiver.publicKey);
		
		// ���2: ���ܺ�����1 + ���ܺ��symKey
		byte[] c2 = ByteUtil.mergeByteArrays(encryptedC1, encryptedSKB);
		
		return c2;
		
		
	}
	
	
	/**
	 * ��ʼ����������
	 * @param hashAlgo ��ϣ�㷨
	 */
	public static void initEncryption(String hashAlgo, String encAlgo,
			String encMode, String padMode) {
		
		SymEnc.initAlgorithm(encAlgo, encMode, padMode); 
		HashUtil.changeAlgorithm(hashAlgo);
		
	}
	

}
