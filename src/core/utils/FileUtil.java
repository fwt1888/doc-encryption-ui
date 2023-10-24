package core.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	public static byte[] readBytesFromFile(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        return buffer;
    }
	
	public static void writeBytesToFile(String filename, byte[] data) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        fos.write(data);
        fos.close();
    }
	
	public static String renameFile(String filePath, String suffix) {
		String[] parts = filePath.split("\\.", 2);
		String newPath = parts[0] + "_" + suffix + "." + parts[1];
		return newPath;
	}

}
