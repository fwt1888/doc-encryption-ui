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
	 * �ڴ���ִ��Ŀ¼�д���.crypt_data�ļ��У������洢ϵͳ����
	 */
    public static void createDataFolder() {
        String folderPath = getExecutionDirectory() + "/.crypt_data";

        File folder = new File(folderPath);

        // ����ļ����Ƿ���ڣ�����������򴴽�
        if (!folder.exists()) {
            boolean created = folder.mkdir(); // �����ļ���
            if (created) {
                System.out.println("�ļ��д����ɹ�");
            } else {
                System.out.println("�޷������ļ���");
            }
        } else {
            System.out.println("�ļ����Ѿ�����");
        }
    }
    /**
     * ��ȡ�������е� Java �����ִ��Ŀ¼
     * @return ִ��Ŀ¼�ľ���·��
     */
    public static String getExecutionDirectory() {
        // ��ȡ��ļ���λ�ã�ͨ��Ϊ JAR �ļ������ļ����ڵ��ļ��У�
        String classLocation = FileUtil.class.getProtectionDomain().
        		getCodeSource().getLocation().getPath();
        
        // ��� classLocation ���ļ���·����δ����� JAR����ʹ������Ϊִ��Ŀ¼
        // ��� classLocation �� JAR �ļ�·������ȡ������Ŀ¼��Ϊִ��Ŀ¼
        File executionDirectory = new File(classLocation).isDirectory() ? 
        		new File(classLocation) : new File(classLocation).getParentFile();
        
        return executionDirectory.getAbsolutePath();
    }


}
