package core.utils;

import java.io.File;
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
		String filePath = FileUtil.FOLDER_FOR_KEYSTORE + "/" 
				+ user.name + ".keystore";
		
		// 清空keystore缓存
		KeyManager.clearKeyStore();
		
		// 生成RSA密钥对
		RsaUtil.rsaKeyGeneration();
		
		// 保存至.keystore
		KeyManager.initKeyStore();
		KeyManager.saveKeyStoreFile(filePath);
	}
	
	public static void getAllUsers() throws Exception {
		
		userAccounts = new HashMap<>();
		 
		 // 读取 ./keyStore
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
