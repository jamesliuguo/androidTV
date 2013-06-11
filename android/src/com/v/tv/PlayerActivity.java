package com.v.tv;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayerActivity extends Activity {
	public MediaController mCtl = null;
	public VideoView  video = null;
	public String fileName = "http://192.168.1.107/a.mp4";
	private java.util.Timer timerStartupDownload = null;
	private java.util.Timer timerQueryServer = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD, 
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, 
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                
        setContentView(R.layout.main);

        int i = 0;
        i = 8;
        System.out.print(i);
        
        video = (VideoView) findViewById(R.id.videoView1);
       
        AppKernel.appStatus = App_Status.waitingResource;
        new Thread(downloadRun).start();  
        
        timerStartupDownload = new java.util.Timer(true); 
        timerStartupDownload.schedule(timerTaskDownload, new Date(), 2000);
        
        timerQueryServer = new java.util.Timer(true); 
        timerQueryServer.schedule(taskQueryServer, new Date(), 2000);
    }
    
    public Runnable downloadRun = new Runnable(){  
	    @Override  
	    public void run() {  
	        // TODO Auto-generated method stub  
	            NetClient c = new NetClient();
	            c.downloadFile("http://192.168.1.107/Client.cfg");
	            AppKernel.netStatus = Net_Status.DownloadingMovie;
	            c.downloadFile("http://192.168.1.107/a.mp4");
	            AppKernel.netStatus = Net_Status.Finish;
	    }
    } ;
    Handler mHandler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            if(msg.what == 1) {  
                final String filename = Environment.getExternalStorageDirectory()+"/a.mp4";
    	        try{
    	            video.setVideoPath(filename);
        	        video.setMediaController(new MediaController(PlayerActivity.this));
        	        video.requestFocus();
        	        video.start();
    	        }
    	        catch (Exception e)
    	        {
    	        	String str = e.getMessage();
    	        	System.out.print(str);
    	   
    	        }
            }  
            super.handleMessage(msg);  
        }  
    };
    // 
    public TimerTask timerTaskDownload = new TimerTask() {   
    	public void run() {   //implemented with sub-thread
    		System.out.print("task");
    		if (NetClient.bDownload)
    		{
    			NetClient.bDownload = false;
    	        Message msg = mHandler.obtainMessage();  
                msg.what = 1;  
                msg.sendToTarget(); 
    	        
    	        timerStartupDownload.cancel();
    		}
    		
    	 }   
    };  
    // 
    public TimerTask taskQueryServer = new TimerTask() {   
    	public void run() {   //implemented with sub-thread
    		System.out.print("task");
    		// get task and execute, sendToget the message
    	 }   
    };
    
}