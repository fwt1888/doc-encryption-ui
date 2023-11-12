package test;

import core.utils.ByteUtil;
import core.utils.StringUtil;

public class ByteTest {
	
	public static void main(String[] args) {
		int i =256;
		
		System.out.println("-- HEX Test --");
		byte[] result = ByteUtil.intToByteArray(i);
		for (byte bit : result) {
		    String hexString = String.format("%02X", bit); // 将字节转换为十六进制字符串
		    System.out.println(hexString);
		}
		
		System.out.println(ByteUtil.byteArrayToHex(result));
		System.out.println(ByteUtil.byteArrayToInt(result));
		
		System.out.println("-- Normal Test --");
		String s = "hello --";
		byte[] result2 = StringUtil.stringToByteArray(s);
		for (byte bit : result2) {
		    String hexString = String.format("%02X", bit); // 将字节转换为十六进制字符串
		    System.out.println(hexString);
		}
		System.out.println(StringUtil.byteArrayToString(result2));

	}

}
