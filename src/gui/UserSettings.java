package gui;

public class UserSettings {
	
	// 删除身份时，是否删除本地磁0盘上的.pem文件
	public static boolean deleteRealPEMFile = true;
	
	// 加密策略
	public static String symAlgo = "DES";
	public static String symKey = "seed"; // seed or provided
	public static String hashAlgo = "SHA";

}
