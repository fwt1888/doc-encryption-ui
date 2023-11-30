package core;

import java.security.Key;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class UserAccount {
	
	public String name = null;
	public Key publicKey = null;
	public String pkString = null;
	private Key privateKey = null;
	private String rkString = null;

	public Key getPrivateKey() {
		return privateKey;
	}
	
	public String getRKString() {
		return rkString;
	}
	
	public UserAccount(String name, Key publicKey, Key privateKey) {
		this.name = name;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}
	
	public void setPrivateKey(Key key) {
		privateKey = key;
	}
	
	public void setRKString(String s) {
		rkString = s;
	}
	
	public void printInfo() {
		System.out.println(name + " : ");
		System.out.println("pk : " + publicKey);
		System.out.println("rk : " + privateKey);
	}
	
	public void generateKeyStrings() {
		pkString = "( " + ((RSAPublicKey) publicKey).getModulus() + " , " 
					+ ((RSAPublicKey) publicKey).getPublicExponent() + " )";
		rkString = "( " + ((RSAPrivateKey) privateKey).getModulus() + " , " 
					+ ((RSAPrivateKey) privateKey).getPrivateExponent() + " )";
	}
}
