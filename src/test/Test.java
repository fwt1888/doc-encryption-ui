package test;

import core.DESUtils;

public class Test {
	public static void testEncrypt() throws Exception{
	
	    String data="我是测试数据，用来测试DES";
	    String key="2234234d";
	    byte[] bytes = DESUtils.desEncrypt(data, key);
	    System.out.println("加密后报文为："+new String(bytes));
	    String s = DESUtils.desDecrypt(bytes, key);
	    System.out.println("解密后报文为："+s);
	}
	
	
	public static void main(String[] args) throws Exception {
	    testEncrypt();
	}

}



