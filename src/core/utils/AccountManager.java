package core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.UserAccount;

public class AccountManager {
	
	public static HashMap<String, UserAccount> userAccounts = null;
	
	/**
	 * 创建用户账号(新建.jks,存储公私钥对)
	 */
	public static void userRegister(UserAccount user) throws Exception{
		// 设定路径
		KeyManager.changeKeystoreDir(FileUtil.FOLDER_FOR_KEYSTORE);
		KeyManager.changeKeystoreName(user.name);
		
		// 清空keystore缓存
		KeyManager.clearKeyStore();
		
		// 生成RSA密钥对
		RsaUtil.rsaKeyGeneration();
		
		// 保存至.jks
		KeyManager.initKeyStore();
		KeyManager.saveKeyStoreFile();
	}
	
	public static void getAllUsers() {
		
		userAccounts = new HashMap<>();
		 
		 // 读取 ./keyStore
//		 userAccounts.add(new UserAccount("john_doe", "password123", "john@example.com"));
//	     userAccounts.add(new UserAccount("jane_smith", "pass456", "jane@example.com"));
		

		
	}


}
