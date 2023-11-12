package core.utils;

import java.nio.charset.StandardCharsets;

public class StringUtil {
	
	public static byte[] stringToByteArray(String s) {
		return s.getBytes(StandardCharsets.UTF_8);
	}
	
	public static String byteArrayToString(byte[] bytes) {
		
		 StringBuilder hexStringBuilder = new StringBuilder();

	        for (byte b : bytes) {
	        	int decimalValue = Byte.toUnsignedInt(b);
	            hexStringBuilder.append((char)decimalValue);
	        }

	        return hexStringBuilder.toString();
	}

}
