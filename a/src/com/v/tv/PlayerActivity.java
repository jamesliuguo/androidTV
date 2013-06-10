package com.v.tv;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayerActivity extends Activity {
	public MediaController mCtl = null;
	public VideoView  video = null;
	public String fileName = "http://192.168.1.107/a.mp4";
	private java.util.Timer timer = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       // mCtl = (MediaController) findViewById(R.id.mediaController1);
        video = (VideoView) findViewById(R.id.videoView1);
       
        new Thread(downloadRun).start();  
        
        timer = new java.util.Timer(true); 
        timer.schedule(timerTask, new Date(), 2000);
    }
    
    public Runnable downloadRun = new Runnable(){  
	    @Override  
	    public void run() {  
	        // TODO Auto-generated method stub  
	            NetClient c = new NetClient();
	            String str = c.Get("");
	            c.downloadFile("http://192.168.1.107/a.mp4");
	           // c.downloadFile("http://192.168.1.107/a.avi");
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
    public TimerTask timerTask = new TimerTask() {   
    	public void run() {   //implemented with sub-thread
    		System.out.print("task");
    		if (NetClient.bDownload)
    		{
    			NetClient.bDownload = false;
    	        Message msg = mHandler.obtainMessage();  
                msg.what = 1;  
                msg.sendToTarget(); 
    	        
    	        timer.cancel();

    		}
    	 }   
    };  
}