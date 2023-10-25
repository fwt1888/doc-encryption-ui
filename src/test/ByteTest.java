package test;

import core.utils.ByteUtil;

public class ByteTest {
	
	public static void main(String[] args) {
		int i =16;
		byte[] result = ByteUtil.intToByteArray(i);
		for (byte bit : result) {
		    String hexString = String.format("%02X", bit); // ���ֽ�ת��Ϊʮ�������ַ���
		    System.out.println(hexString);
		}
		
		System.out.println(ByteUtil.byteArrayToHex(result));
		System.out.println(ByteUtil.byteArrayToInt(result));

	}

}
