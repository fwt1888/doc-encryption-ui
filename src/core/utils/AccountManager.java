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
	 * �����û��˺�(�½�.jks,�洢��˽Կ��) ��������keystore
	 */
	public static UserAccount userRegist(UserAccount user) throws Exception{
		
		// �趨·��
		String rkPath = FileUtil.FOLDER_FOR_KEYSTORE + "/rk/" 
								+ user.name + ".pem";
		String pkPath = FileUtil.FOLDER_FOR_KEYSTORE + "/pk/" 
				+ user.name + ".pem";
		
		// ���keystore����
//		KeyManager.clearKeyStore();
		
		// ����RSA��Կ��
		RsaUtil.rsaKeyGeneration();
		
		user.publicKey = RsaUtil.publicKey;
		user.setPrivateKey(RsaUtil.getPrivateKey());
		
		// ������.keystore
//		KeyManager.initKeyStore();
//		KeyManager.saveKeyStoreFile(rkPath);
		
		// ������.pem
		KeyManager.writeKeyToPemFile(pkPath, user.publicKey);
		KeyManager.writeKeyToPemFile(rkPath, user.getPrivateKey());

		return user;
	}
	
	/**
	 * ��ʱ�����û��˺ţ�������
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static UserAccount userCreate(UserAccount user) throws Exception{
		
		// ����RSA��Կ��
		RsaUtil.rsaKeyGeneration();
		
		user.publicKey = RsaUtil.publicKey;
		user.setPrivateKey(RsaUtil.getPrivateKey());
		
		return user;
	}
	
	public static void getAllUsers() throws Exception {
		
		userAccounts = new HashMap<>();
		 
		 // ��ȡ ./pem (��pk��rk)
		File folder = new File(FileUtil.FOLDER_FOR_KEYSTORE + "/pk");
        File[] files = folder.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".pem")) {
                	String userName = file.getName().replace(".pem", "");
                	
                	// ��ȡ˽Կ
//                	KeyManager.readKeyStoreFile(file.getAbsolutePath());
//                	KeyManager.parseKeyStore();
                	
                	// ��ȡ��Կ
                	String pkPath = file.getAbsolutePath();
                	RsaUtil.publicKey = (RSAPublicKey) KeyManager.readKeyFromPemFile(pkPath, "public");
                	
                	// ��ȡ˽Կ
                	String rkPath = FileUtil.FOLDER_FOR_KEYSTORE + "/rk/" 
							+ userName + ".pem";
                	RsaUtil.setPrivateKey((RSAPrivateKey)KeyManager.readKeyFromPemFile(rkPath, "private"));              	
                	
                	// �����б�
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
