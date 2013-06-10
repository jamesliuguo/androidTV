package com.v.tv;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Environment;

public class NetClient {
	static public boolean bDownload = false;
	public boolean downloadFile(String destUrl)
	{
		String fileName = Environment.getExternalStorageDirectory() + Utility.getFileNameFromPath(destUrl) ;
		boolean bExist = (new File(fileName).exists());
		if (bExist) {
			bDownload = true;
			return true;
		}
		
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		byte[] buf = new byte[1024];
		int size = 0;
		       
		boolean bRet = false;
		//建立链接
		try{
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			fos = new FileOutputStream(fileName);
			
			while ((size = bis.read(buf)) != -1)
			            fos.write(buf, 0, size);

			 fos.close();
			 bis.close();
			 httpUrl.disconnect();
			 bRet = true;
		}
		catch (Exception e)
		{
			bRet = false;
		}
		
		bDownload = true;
		return bRet;

	}
	public void Post(String url, String vars)
	{
		
	}
	public String Get(String url)
	{
		String strRet = "";
		url = "http://192.168.1.107/service.php?A=B";
		HttpGet get = new HttpGet(url);
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(get);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				strRet = EntityUtils.toString(response.getEntity());
			}
		}
		catch(ClientProtocolException e)
		{
			strRet = e.getMessage();
		}
		catch (IOException e)
		{
			strRet = e.getMessage();
		}
		catch (Exception e)
		{
			strRet = e.getMessage();
		}
		
		return strRet;
	}
	 public static void main(String[] args) { 
		 System.out.print("ok");
	 }
	 
}
