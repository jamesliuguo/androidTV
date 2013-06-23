package com.v.tv;

enum App_Status
{
	waitingResource,
	downloading,
	finishing,
	playing,
	done
}
enum Net_Status
{
	pending,
	downloadingMovie,
	finish,
}
public class AppKernel {
	public static Configuration config = new Configuration();
	public static App_Status appStatus;
	public static Net_Status  netStatus;
	
}
