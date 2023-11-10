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
	 * @throws Exception 
	 */
	public static byte[] decrypt(byte[] bytes) throws Exception {
		
		// ���2: ���ܺ�����1 + ���ܺ��symKey
		// ��ȡsymKey����
		int skLength = ByteUtil.getLengthField(4, bytes);
		byte[] encSKB = new byte[skLength];
		// ��ȡK
		System.arraycopy(bytes,4,encSKB,0,skLength);
		// ɾ��K�����ֶ� + k
		bytes = ByteUtil.removePartFromArray(4 + skLength, bytes);
		
		// receiver ˽Կ ����K
		SymEnc.setSymKeyBytes(
				RsaUtil.rsaDecrypt(encSKB, receiver.getPrivateKey()));
		
		// K���� ���� + MAC
		byte[] m = SymEnc.symDecrypt(bytes);
		
		// ��ȡMAC���� + MAC
		int macLength = ByteUtil.getLengthField(4, m);
		byte[] encMAC = new byte[macLength];
		System.arraycopy(m, 4, encMAC, 0, macLength);
		
		// ɾ��MAC�����ֶ� + MAC
		m = ByteUtil.removePartFromArray(4 + macLength, bytes);
		
		// ���ļ���HASH
		byte[] hashFromM = HashUtil.getHash(m);
		
		// sender ��Կ ��֤MAC
		byte[] hashFromMAC = RsaUtil.rsaDecrypt(encMAC, 
				sender.publicKey);
		
		if (hashFromM.equals(hashFromMAC)) {
			return m;
		}

		
		return null;
		
	}
	
	
	/**
	 * ���ļ�����
	 */
    public static Boolean decrypt(String filePath) {
    	try {
    		byte[] bytes = FileUtil.readBytesFromFile(filePath);
			// ��ȡ��׺
	//		String extension = FileUtil.getFileExtension(filePath);
			String tempFile1 = FileUtil.renameFile(filePath,"tmp1");
			String tempFile2 = FileUtil.renameFile(filePath,"tmp2");
    		
    		// ��ȡsymKey����
    		int skLength = ByteUtil.getLengthField(4, bytes);
    		byte[] encSKB = new byte[skLength];
    		// ��ȡK
    		System.arraycopy(bytes,4,encSKB,0,skLength);
    		// ɾ��K�����ֶ� + k
    		bytes = ByteUtil.removePartFromArray(4 + skLength, bytes);
    		
    		// receiver ˽Կ ����K
    		SymEnc.setSymKeyBytes(
    				RsaUtil.rsaDecrypt(encSKB, receiver.getPrivateKey()));
    		
    		// K���� ���� + MAC
    		FileUtil.writeBytesToFile(tempFile1, bytes);
    		SymEnc.symDecrypt(tempFile1, tempFile2);
    		byte[] m = FileUtil.readBytesFromFile(tempFile2);
    		
    		// ��ȡMAC���� + MAC
    		int macLength = ByteUtil.getLengthField(4, m);
    		byte[] encMAC = new byte[macLength];
    		System.arraycopy(m, 4, encMAC, 0, macLength);
    		
    		// ɾ��MAC�����ֶ� + MAC
    		m = ByteUtil.removePartFromArray(4 + macLength, bytes);
    		String finalFile = FileUtil.renameFile(filePath, "dec");
			FileUtil.writeBytesToFile(finalFile, m);
    		
    		// ���ļ���HASH
    		byte[] hashFromM = HashUtil.getHash(finalFile);
    		
    		// sender ��Կ ��֤MAC
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
   
}
