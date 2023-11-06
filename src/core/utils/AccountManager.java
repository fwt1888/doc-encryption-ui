package core.utils;

import java.io.File;
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
		String filePath = FileUtil.FOLDER_FOR_KEYSTORE + "/" 
				+ user.name + ".keystore";
		
		// ���keystore����
		KeyManager.clearKeyStore();
		
		// ����RSA��Կ��
		RsaUtil.rsaKeyGeneration();
		
		// ������.keystore
		KeyManager.initKeyStore();
		KeyManager.saveKeyStoreFile(filePath);
	}
	
	public static void getAllUsers() throws Exception {
		
		userAccounts = new HashMap<>();
		 
		 // ��ȡ ./keyStore
		File folder = new File(FileUtil.FOLDER_FOR_KEYSTORE);
        File[] files = folder.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".keystore")) {
                	String userName = file.getName();
                	KeyManager.readKeyStoreFile(file.getAbsolutePath());
                	KeyManager.parseKeyStore();
                	
                    userAccounts.put(userName,
                    		new UserAccount(file.getName(),RsaUtil.publicKey,RsaUtil.getPrivateKey()));
                }
            }
        }
        
     	
	}


}
