package core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	public static byte[] readBytesFromFile(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        return buffer;
    }
	
	public static void writeBytesToFile(String filePath, byte[] data) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(data);
        fos.close();
    }
	
	public static String renameFile(String filePath, String suffix) {
		String[] parts = filePath.split("\\.", 2);
		String newPath = parts[0] + "_" + suffix + "." + parts[1];
		return newPath;
	}

	/**
	 * 在代码执行目录中创建.crypt_data文件夹，用来存储系统数据
	 */
    public static void createDataFolder() {
        String folderPath = getExecutionDirectory() + "/.crypt_data";

        File folder = new File(folderPath);

        // 检查文件夹是否存在，如果不存在则创建
        if (!folder.exists()) {
            boolean created = folder.mkdir(); // 创建文件夹
            if (created) {
                System.out.println("文件夹创建成功");
            } else {
                System.out.println("无法创建文件夹");
            }
        } else {
            System.out.println("文件夹已经存在");
        }
    }
    /**
     * 获取正在运行的 Java 程序的执行目录
     * @return 执行目录的绝对路径
     */
    public static String getExecutionDirectory() {
        // 获取类的加载位置（通常为 JAR 文件或类文件所在的文件夹）
        String classLocation = FileUtil.class.getProtectionDomain().
        		getCodeSource().getLocation().getPath();
        
        // 如果 classLocation 是文件夹路径（未打包成 JAR），使用它作为执行目录
        // 如果 classLocation 是 JAR 文件路径，获取其所在目录作为执行目录
        File executionDirectory = new File(classLocation).isDirectory() ? 
        		new File(classLocation) : new File(classLocation).getParentFile();
        
        return executionDirectory.getAbsolutePath();
    }


}
