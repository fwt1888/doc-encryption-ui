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
	 * �Ա���������
	 * @param bytes
	 * @return
	 */
	public static byte[] decrypt(byte[] bytes) {
		
		// ���2: ���ܺ�����1 + ���ܺ��symKey
		// ��ȡsymKey����
		int skLength = ByteUtil.getLengthField(4, bytes);
		byte[] encSKB = new byte[skLength];
		System.arraycopy(bytes,);
		
		// ������HASH����˽Կǩ��
		byte[] hash = HashUtil.getHash(bytes);
		byte[] signedHash = RsaUtil.rsaEncrypt(hash, sender.getPrivateKey());
		
		// ���1: ���� + ǩ�����HASH(MAC)
		byte[] c1 = ByteUtil.mergeByteArrays(bytes, signedHash);
		
		// �����1���жԳƼ���
		byte[] encryptedC1 = SymEnc.symEncrypt(c1);
		
		// �ԳƼ�����Կ��ȡ
		byte[] symKeyBytes = SymEnc.getKeyBytes();
		
		// ��symKey���й�Կ����
		byte[] encryptedSKB = RsaUtil.rsaEncrypt(symKeyBytes, receiver.publicKey);
		
		
		byte[] c2 = ByteUtil.mergeByteArrays(encryptedC1, encryptedSKB);
		
		return c2;
		
		
		
		return bytes;
		
	}
	
	
	/**
	 * ���ļ�����
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
