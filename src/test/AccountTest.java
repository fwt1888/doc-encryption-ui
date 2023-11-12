package test;

import core.UserAccount;
import core.utils.AccountManager;

public class AccountTest {
	
	public static void main(String[] args) throws Exception {
		
		// save files
		UserAccount sender = new UserAccount("sender", null, null);
		UserAccount recevier = new UserAccount("recevier", null, null);
		
		sender = AccountManager.userRegist(sender);
		recevier = AccountManager.userRegist(recevier);
		
		sender.printInfo();
		recevier.printInfo();
		
		// read  files
		AccountManager.getAllUsers();
		AccountManager.showAllUserInfo();
	}

}
