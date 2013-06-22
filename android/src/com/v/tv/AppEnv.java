package com.v.tv;

import android.os.Environment;

public class AppEnv {
	private static String localCachePath = null;
	private static String getLocalPath()
	{
		if (localCachePath != null) return localCachePath;
		
	   //File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
	   String err = null;
	   try{
		   localCachePath = Environment.getExternalStorageDirectory().getAbsolutePath();
	   }
	   catch(Exception e) {
		   err = e.getMessage();
	   }
	   return localCachePath;
	}
	private static String getNameOnly(String url)
	{
		String str = "";
		int index = url.lastIndexOf("/");
		if (index >=0)	str = url.substring(index+1);
		return str;
	}
	static public String getLocalFile(String url)
	{
		String str = getLocalPath() + getNameOnly(url);
		return str;
	}
	/* Checks if external storage is available to at least read */
	public static boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}
}
