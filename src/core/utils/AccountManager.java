package core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.UserAccount;

public class AccountManager {
	
	public static HashMap<String, UserAccount> userAccounts = null;
	
	/**
	 * �����û��˺�(�½�.jks,�洢��˽Կ��)
	 */
	public static void userRegister(UserAccount user) throws Exception{
		// �趨·��
		KeyManager.changeKeystoreDir(FileUtil.FOLDER_FOR_KEYSTORE);
		KeyManager.changeKeystoreName(user.name);
		
		// ���keystore����
		KeyManager.clearKeyStore();
		
		// ����RSA��Կ��
		RsaUtil.rsaKeyGeneration();
		
		// ������.jks
		KeyManager.initKeyStore();
		KeyManager.saveKeyStoreFile();
	}
	
	public static void getAllUsers() {
		
		userAccounts = new HashMap<>();
		 
		 // ��ȡ ./keyStore
//		 userAccounts.add(new UserAccount("john_doe", "password123", "john@example.com"));
//	     userAccounts.add(new UserAccount("jane_smith", "pass456", "jane@example.com"));
		

		
	}


}
