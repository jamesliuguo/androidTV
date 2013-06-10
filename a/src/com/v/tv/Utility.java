package com.v.tv;

public class Utility {
	static String getFileNameFromPath(String strPath)
	{
		String str = "";
		int pos = strPath.lastIndexOf("/");
		if (pos > 0) str = strPath.substring(pos);
		else str = strPath;
		return str;
	}
}
