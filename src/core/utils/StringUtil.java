package core.utils;

import java.io.UnsupportedEncodingException;

public class StringUtil {
	
	public static byte[] stringToByte(String s) {
		
		return s.getBytes();
	}

	public static String byteToString(byte[] bytes) throws UnsupportedEncodingException {
		
		return new String(bytes, "UTF-8");
	}
	
}
