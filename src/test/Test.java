package test;

import core.DESUtils;

public class Test {
	public static void testEncrypt() throws Exception{
	
	    String data="���ǲ������ݣ���������DES";
	    String key="2234234d";
	    byte[] bytes = DESUtils.desEncrypt(data, key);
	    System.out.println("���ܺ���Ϊ��"+new String(bytes));
	    String s = DESUtils.desDecrypt(bytes, key);
	    System.out.println("���ܺ���Ϊ��"+s);
	}
	
	
	public static void main(String[] args) throws Exception {
	    testEncrypt();
	}

}



