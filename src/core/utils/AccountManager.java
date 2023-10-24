package core.utils;

import java.util.ArrayList;
import java.util.List;

import core.UserAccount;

public class AccountManager {
	
	/**
	 * 创建用户账号(新建.jks,存储公私钥对)
	 */
	public static void userRegister() {
		
		
	}
	
	public static List<UserAccount> getAllUsers() {
		
		 List<UserAccount> userAccounts = new ArrayList<>();
		 
		 // 读取 ./keyStore
//		 userAccounts.add(new UserAccount("john_doe", "password123", "john@example.com"));
//	     userAccounts.add(new UserAccount("jane_smith", "pass456", "jane@example.com"));
		
		return userAccounts;
		
	}


}
