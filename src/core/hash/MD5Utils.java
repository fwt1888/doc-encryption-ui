package core.hash;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
/**
 * Can convert a file to MD5
 * @param text
 * @return md5
 */
public static String encode(String text){
	try {
	MessageDigest digest = MessageDigest.getInstance("md5");
	byte[] buffer = digest.digest(text.getBytes());
	// byte -128 ---- 127
	StringBuffer sb = new StringBuffer();
	for (byte b : buffer) {
		int a = b & 0xff;
		// Log.d(TAG, "" + a);
		String hex = Integer.toHexString(a);

		if (hex.length() == 1) {
			hex = 0 + hex;
		}
		sb.append(hex);
	}
	return sb.toString();
} catch (NoSuchAlgorithmException e) {
	e.printStackTrace();
}
return null;
}

/***
 * Can convert a text to MD5
 * @param inputStream
 * @return md5
 */

public static String encode(InputStream in) {
	try {
		MessageDigest digester = MessageDigest.getInstance("MD5");
		byte[] bytes = new byte[8192];
		int byteCount;
		while ((byteCount = in.read(bytes)) > 0) {
			digester.update(bytes, 0, byteCount);
		}
		byte[] digest = digester.digest();

		// byte -128 ---- 127
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			int a = b & 0xff;
			// Log.d(TAG, "" + a);

			String hex = Integer.toHexString(a);

			if (hex.length() == 1) {
				hex = 0 + hex;
			}

			sb.append(hex);
		}

		return sb.toString();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			in = null;
		}
	}
	return null;
}



}