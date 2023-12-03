package core.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserSettings {
	
	// 加密策略
	public static String symAlgo = "DES";
	public static String symKey = "seed"; // seed or provided
	public static String providedSymKey = null;
	public static String hashAlgo = "SHA-1";
	
	// 密钥管理
	public static String keyStorePath = FileUtil.FOLDER_FOR_KEYSTORE;
	public static String ifDeleteRealFile = "y";
	
	private static String settingPath = FileUtil.FOLDER_FOR_DATA_STORAGE 
			+ "/setting.txt";
	
	/**
	 * 保存至/.crypt_data/setting.txt
	 */
	public static void saveToFile() {
		 try (BufferedWriter writer = 
				new BufferedWriter(new FileWriter(settingPath))) {
	            writer.write("symAlgo: " + symAlgo);
	            writer.newLine();
	            writer.write("symKey: " + symKey);
	            writer.newLine();
	            writer.write("providedSymKey: " + providedSymKey);
	            writer.newLine();
	            writer.write("hashAlgo: " + hashAlgo);
	            writer.newLine();
	            writer.write("keyStorePath: " + keyStorePath);
	            writer.newLine();
	            writer.write("ifDeleteRealFile: " + ifDeleteRealFile);
	        } catch (IOException e) {
	            e.printStackTrace();
	     }
		
		
	}
	
	/**
	 * 读取/.crypt_data/setting.txt
	 */
	public static void readFromFile() {
		if(FileUtil.fileExists(settingPath)) {
		 try (BufferedReader reader = new BufferedReader(new FileReader(settingPath))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(": ");
	                if (parts.length == 2) {
	                    String variable = parts[0].trim();
	                    String value = parts[1].trim();
	                    setVariable(variable, value);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	// 设置类的成员变量
    private static void setVariable(String variable, String value) {
        switch (variable) {
            case "symAlgo":
                symAlgo = value;
                break;
            case "symKey":
                symKey = value;
                break;
            case "providedSymKey":
            	providedSymKey = value;
                break;
            case "hashAlgo":
            	hashAlgo = value;
                break;
            case "keyStorePath":
            	keyStorePath = value;
                break;
            case "ifDeleteRealFile":
            	ifDeleteRealFile = value;
                break;
        }
    }
    
    public static void changeSettings() {
    	SymEnc.initAlgorithm(symAlgo, "ECB", "PKCS5Padding");
    	if(symKey == "provided") {
    		SymEnc.setProvidedSymKey(providedSymKey);
    	}
    	HashUtil.changeAlgorithm(hashAlgo);
    	FileUtil.FOLDER_FOR_KEYSTORE = keyStorePath;
    }

}
