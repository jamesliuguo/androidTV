package com.v.tv;

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
}
