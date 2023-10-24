package core;

import java.security.Key;

public class UserAccount {
	
	public String name = null;
	public Key publicKey = null;
	
	private Key privateKey = null;

	public Key getPrivateKey() {
		return privateKey;
	}
	
	public UserAccount(String name, Key publicKey, Key privateKey) {
		this.name = name;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}
	
}
