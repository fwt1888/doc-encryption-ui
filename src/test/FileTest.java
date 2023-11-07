package test;

import core.utils.FileUtil;

public class FileTest {
	
	public static void main(String[] args) {
		System.out.println(FileUtil.getExecutionDirectory());
		System.out.println(FileUtil.getFileExtension("a.txt"));
	}

}
