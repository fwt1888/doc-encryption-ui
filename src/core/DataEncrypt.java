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
	 * ���ַ������м���
	 * @param bytes �ַ���������
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] bytes) throws Exception {
		
		System.out.println("M (HEX) : " + ByteUtil.byteArrayToHex(bytes));
		
		// ������HASH����˽Կǩ��
		byte[] hash = HashUtil.getHash(bytes);
		byte[] signedHash = RsaUtil.rsaEncrypt(hash, sender.getPrivateKey());
		
		// ���1: ���� + ǩ�����HASH(MAC)
		byte[] c1 = ByteUtil.mergeByteArrays(signedHash, bytes);
		// ���MAC�����ֶ�(�ʼ��
		c1 = ByteUtil.addLengthField(signedHash.length, c1);
		
		// �����1���жԳƼ���
		byte[] encryptedC1 = SymEnc.symEncrypt(c1);
		
		// �ԳƼ�����Կ��ȡ
		byte[] symKeyBytes = SymEnc.getKeyBytes();
		
		// ��symKey���й�Կ����
		byte[] encryptedSKB = RsaUtil.rsaEncrypt(symKeyBytes, receiver.publicKey);
		
		// ���2: ���ܺ�����1 + ���ܺ��symKey
		byte[] c2 = ByteUtil.mergeByteArrays(encryptedSKB,encryptedC1);
		// ���K�����ֶΣ��ʼ��
		c2 = ByteUtil.addLengthField(encryptedSKB.length, c2);
		
		return c2;
			
	}
	
	/**
	 * ���ļ����м���
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static Boolean encrypt(String filePath) throws Exception {
		
		try {
			byte[] bytes = FileUtil.readBytesFromFile(filePath);
			// ��ȡ��׺
	//		String extension = FileUtil.getFileExtension(filePath);
			String tempFile1 = FileUtil.renameFile(filePath,"tmp1");
			String tempFile2 = FileUtil.renameFile(filePath,"tmp2");
					
			// ������HASH����˽Կǩ��
			byte[] hash = HashUtil.getHash(filePath);
			byte[] signedHash = RsaUtil.rsaEncrypt(hash, sender.getPrivateKey());
			
			// ���1: ���� + ǩ�����HASH(MAC)
			byte[] c1 = ByteUtil.mergeByteArrays(signedHash, bytes);
			// ���MAC�����ֶ�(�ʼ��
			c1 = ByteUtil.addLengthField(signedHash.length, c1);
		
			
			// �����1���жԳƼ���
			FileUtil.writeBytesToFile(tempFile1, c1);
			SymEnc.symEncrypt(tempFile1,tempFile2);
			byte[] encryptedC1 = FileUtil.readBytesFromFile(tempFile2);
			
			// �ԳƼ�����Կ��ȡ
			byte[] symKeyBytes = SymEnc.getKeyBytes();
			
			// ��symKey���й�Կ����
			byte[] encryptedSKB = RsaUtil.rsaEncrypt(symKeyBytes, receiver.publicKey);
			
			// ���2: ���ܺ�����1 + ���ܺ��symKey
			byte[] c2 = ByteUtil.mergeByteArrays(encryptedSKB,encryptedC1);
			// ���K�����ֶΣ��ʼ��
			c2 = ByteUtil.addLengthField(encryptedSKB.length, c2);
			
			// ɾ����ʱ�ļ��������ս������_enc
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
	 * ��ʼ����������
	 * @param hashAlgo ��ϣ�㷨
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
