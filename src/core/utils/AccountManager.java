package core.utils;

import java.util.ArrayList;
import java.util.List;

import core.UserAccount;

public class AccountManager {
	
	/**
	 * �����û��˺�(�½�.jks,�洢��˽Կ��)
	 */
	public static void userRegister() {
		
		
	}
	
	public static List<UserAccount> getAllUsers() {
		
		 List<UserAccount> userAccounts = new ArrayList<>();
		 
		 // ��ȡ ./keyStore
//		 userAccounts.add(new UserAccount("john_doe", "password123", "john@example.com"));
//	     userAccounts.add(new UserAccount("jane_smith", "pass456", "jane@example.com"));
		
		return userAccounts;
		
	}


}
