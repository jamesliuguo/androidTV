package com.v.tv;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class NetClient {
	public void ownloadFile()
	{
		
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
