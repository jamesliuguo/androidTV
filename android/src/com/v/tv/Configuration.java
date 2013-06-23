package com.v.tv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

class BasicConfig
{
	public static  String serverIP = "192.168.1.1";
	public static String  clientName = "client1";
	private static String configFile()
	{
		return AppEnv.getLocalPath()+ "basic.cfg";
	}
	public static boolean save()
	{
		boolean success = false;
		String filename = configFile();
	    Properties properties = new Properties();
		try{
		    final FileOutputStream cfg = new FileOutputStream(filename);
		    properties.setProperty("serverIP", serverIP);
		    properties.setProperty("clientName", clientName);
		    properties.store(cfg, "");
		    success = true;
			
		}
		catch(Exception e)
		{
			
		}

		return success;
	}
	public static boolean read()
	{
		boolean success = false;
		String filename = configFile();
	    Properties properties = new Properties();
		try{
		    final InputStream cfg = new FileInputStream(filename);
			properties.load(cfg);	
			serverIP= properties.getProperty("serverIP");
			clientName = properties.getProperty("clientName");
			success = true;
		}
		catch(Exception e)
		{
			
		}

		return success;
	}

}
class DownloadItem
{
	public String fileName;
	public String URL;
	public Net_Status status;
}
class SiteConfig
{
	//analyze the client.cfg file from server
	public static List<DownloadItem> listFiles;
	public static void init()
	{
		if (listFiles == null) listFiles = new LinkedList<DownloadItem>();
		DownloadItem it = new DownloadItem();
		it.fileName =  clientconfg;
		it.URL = Configuration.buildURL(it.fileName);
		it.status = Net_Status.pending;
		listFiles.add(it);
	}
	public static boolean configFinishDownload()
	{
		boolean finish = false;
		DownloadItem it = listFiles.get(0);
		if (it.status == Net_Status.finish) finish = true;
		return finish;
	}
	public static boolean allFinishDownload()
	{
		int last = listFiles.size() -1;
		//
		if (last == 0) return false;
		
		boolean finish = false;
		DownloadItem it = listFiles.get(last);
		if (it.status == Net_Status.finish) finish = true;
		return finish;
	}
	public static String clientconfg = "client.cfg";
	
	public static boolean ParseConfig() 
	{
		if (!configFinishDownload()) return false;
		
		String filename = AppEnv.getLocalPath() + clientconfg;
		Properties properties = new Properties();
		try{
			final InputStream cfg = new FileInputStream(filename);
			properties.load(cfg);	
		}
		catch(Exception e){
				
		}

		for(int i = 0; i<10; i++)
		{
			StringBuilder builder = new StringBuilder();
			builder.append("moviefile").append(i);
			String name = builder.toString();
			String value= properties.getProperty(name);
			if (value == null) break;
			if (value.isEmpty()) break;
			
			DownloadItem it = new DownloadItem();
			it.fileName =  clientconfg;
			it.URL = Configuration.buildURL(it.fileName);
			it.status = Net_Status.pending;
			listFiles.add(it);
		}
		
		return true;
	}
}

public class Configuration {
	//the first item
	public static BasicConfig   basConfig = new BasicConfig();
	public static SiteConfig    siteConfig = new SiteConfig();
	public static String siteConfigURL()
	{
		return "";
	}
	public static String buildURL(String purlFile)
	{
		String str = "http://" + basConfig.serverIP + "/" + purlFile;
		return str;
	}
	
}
