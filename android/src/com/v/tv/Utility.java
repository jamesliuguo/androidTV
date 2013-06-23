package com.v.tv;

import java.io.File;

public class Utility {
	static public boolean reboot()
	{
		boolean bSuccess = false;

        try{      
               Process p = Runtime.getRuntime().exec(new String[]{"su", "-c","reboot"});
               p.waitFor();  
               bSuccess = true;
          }
        catch(Exception e){
              e.printStackTrace();
          }
        return bSuccess;
	}
	
	//untest
	static public boolean stutdown()
	{
		boolean bSuccess = false;
        try{      
               Process p = Runtime.getRuntime().exec(new String[]{"su", "-c","reboot -p"});
               p.waitFor();  
               bSuccess = true;
          }
        catch(Exception e){
              e.printStackTrace();
          }
        return bSuccess;
	}
	public static boolean fileExists(String path)
	{
		//boolean exist = false;
	    java.io.File file = new java.io.File(path);
		 return file.exists();
	}
	public static boolean moveFile(String from, String to)
	{
		boolean success = false;
		 File file = new File(from);
		 File file2 = new File("newname");
		 if(file2.exists()) file2.delete();
		  // Rename file (or directory)
		 success = file.renameTo(file2);
		 return success;
	}
}
