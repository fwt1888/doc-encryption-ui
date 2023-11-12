package test;

import core.DataDecrypt;
import core.DataEncrypt;
import core.UserAccount;
import core.utils.AccountManager;
import core.utils.ByteUtil;
import core.utils.StringUtil;

public class StringEncryptTest {
	
	public static void main(String[] args) throws Exception{
	
		String s = "Hello, world!";
		System.out.println("M (String) : " + s);
		
		// new user accounts
		
		UserAccount sender = new UserAccount("sender", null, null);
		UserAccount recevier = new UserAccount("recevier", null, null);
		
		sender = AccountManager.userCreate(sender);
		recevier = AccountManager.userCreate(recevier);
		
		
		// encrypt
		DataEncrypt.setSenderAndRecevier(sender, recevier);
		String encS = DataEncrypt.stringEncrypt(s);
		System.out.println("Enc (HEX) : " + encS);
		
		// decrypt
		DataDecrypt.setSenderAndRecevier(sender, recevier);
		String decS = DataDecrypt.stringDecrypt(encS);
		System.out.println("Dec (HEX) : " + decS);
		System.out.println("Dec (String) : " + 
				StringUtil.byteArrayToString(ByteUtil.hexStringToByteArray(decS)));
	}
	
	

}
