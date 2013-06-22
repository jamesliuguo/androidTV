package com.v.tv;

public class Configuration {
	//the first item
	public static  String ServerURL = "http://192.168.1.1/";
	public static String  clientName = "Client1";
	public static boolean AnalyzeClientConfig()
	{
		return true;
	}
	public static boolean persistBasicConfig()
	{
		return true;
	}
	public static boolean getBasicConfig()
	{
		return true;
	}
	
	//
	public static String[] movieFiles = null;
}
