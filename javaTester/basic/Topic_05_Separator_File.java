package basic;

import java.io.File;

public class Topic_05_Separator_File {

	public static void main(String[] args) {
		String projectPath = System.getProperty("user.dir");
		String appleName = "Apple.png";		
		String appleFilePath = projectPath + File.separator + "uploadFiles" + File.separator + appleName;
		
		System.out.println(appleFilePath);
		

	}

}
