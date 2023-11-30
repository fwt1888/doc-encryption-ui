package core.utils;

import java.io.File;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

import core.UserAccount;

public class AccountManager {
	
	// string: name
	public static HashMap<String, UserAccount> userAccounts = null;
	
	/**
	 * 创建用户账号(新建.jks,存储公私钥对) 并保存至keystore
	 */
	public static UserAccount userRegist(UserAccount user) throws Exception{
		
		// 设定路径
		String rkPath = FileUtil.FOLDER_FOR_KEYSTORE + "/rk/" 
								+ user.name + ".pem";
		String pkPath = FileUtil.FOLDER_FOR_KEYSTORE + "/pk/" 
				+ user.name + ".pem";
		
		// 清空keystore缓存
//		KeyManager.clearKeyStore();
		
		// 生成RSA密钥对
		RsaUtil.rsaKeyGeneration();
		
		user.publicKey = RsaUtil.publicKey;
		user.setPrivateKey(RsaUtil.getPrivateKey());
		
		// 保存至.keystore
//		KeyManager.initKeyStore();
//		KeyManager.saveKeyStoreFile(rkPath);
		
		// 保存至.pem
		KeyManager.writeKeyToPemFile(pkPath, user.publicKey);
		KeyManager.writeKeyToPemFile(rkPath, user.getPrivateKey());

		return user;
	}
	
	/**
	 * 临时创建用户账号，不保存
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static UserAccount userCreate(UserAccount user) throws Exception{
		
		// 生成RSA密钥对
		RsaUtil.rsaKeyGeneration();
		
		user.publicKey = RsaUtil.publicKey;
		user.setPrivateKey(RsaUtil.getPrivateKey());
		
		return user;
	}
	
	public static void getAllUsers() throws Exception {
		
		userAccounts = new HashMap<>();
		 
		 // 读取 ./pem (先pk后rk)
		File folder = new File(FileUtil.FOLDER_FOR_KEYSTORE + "/pk");
        File[] files = folder.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".pem")) {
                	String userName = file.getName().replace(".pem", "");
                	
                	// 读取私钥
//                	KeyManager.readKeyStoreFile(file.getAbsolutePath());
//                	KeyManager.parseKeyStore();
                	
                	// 读取公钥
                	String pkPath = file.getAbsolutePath();
                	RsaUtil.publicKey = (RSAPublicKey) KeyManager.readKeyFromPemFile(pkPath, "public");
                	
                	// 读取私钥
                	String rkPath = FileUtil.FOLDER_FOR_KEYSTORE + "/rk/" 
							+ userName + ".pem";
                	RsaUtil.setPrivateKey((RSAPrivateKey)KeyManager.readKeyFromPemFile(rkPath, "private"));              	
                	
                	// 登入列表
                    userAccounts.put(userName,
                    		new UserAccount(file.getName(),RsaUtil.publicKey,RsaUtil.getPrivateKey()));
                }
            }
        }
        
     	
	}
	
	public static void showAllUserInfo() {
		userAccounts.forEach((key, value) -> {
            value.printInfo();
        });
	}
	

}
