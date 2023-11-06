package test;


import core.utils.RsaUtil;

public class RsaKeyTest {
	public static void main(String[] args) throws Exception {
		
		RsaUtil.rsaKeyGeneration();
		
		System.out.println("n: " + RsaUtil.getModulus());
		
		System.out.println("private key (d,n): " + RsaUtil.getPrivateKeyString());
		System.out.println("public key (e,n): " + RsaUtil.getPublicKeyString());
		
//		System.out.println("Prime P : " + RsaUtil.getP());
//		System.out.println("Prime Q : " + RsaUtil.getQ());
	}

}
