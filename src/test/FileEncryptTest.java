package test;


import core.DataDecrypt;
import core.DataEncrypt;
import core.UserAccount;
import core.utils.AccountManager;


public class FileEncryptTest {
	
	public static void main(String[] args) throws Exception {
		
		String oriFile = "G:\\Codes\\doc-encryption-ui\\src\\test\\file_to_encrypt.txt";
		System.out.println("Original File : " + oriFile);
		
		// new user accounts
		UserAccount sender = new UserAccount("sender", null, null);
		UserAccount recevier = new UserAccount("recevier", null, null);
		
		sender = AccountManager.userCreate(sender);
		recevier = AccountManager.userCreate(recevier);
		
		// encrypt
		DataEncrypt.setSenderAndRecevier(sender, recevier);
		String encFile = DataEncrypt.encrypt(oriFile);
		System.out.println("File After Encryption : " + encFile);
		
		// decrypt
		DataDecrypt.setSenderAndRecevier(sender, recevier);
		String decFile = DataDecrypt.decrypt(encFile);
		System.out.println("File After Decryption : " + decFile);
	}
	
}
