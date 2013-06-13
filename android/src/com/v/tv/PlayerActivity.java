package com.v.tv;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.view.WindowManager;
import android.widget.EditText;
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
        
 
        getIP();
        
        timerStartupDownload = new java.util.Timer(true); 
        timerStartupDownload.schedule(timerTaskDownload, new Date(), 2000);
        
        timerQueryServer = new java.util.Timer(true); 
        timerQueryServer.schedule(taskQueryServer, new Date(), 2000);
 
    }
    public String strIP;
    private void getIP()
    {
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);

    	alert.setTitle("Title");
    	alert.setMessage("Message");

    	// Set an EditText view to get user input 
    	final EditText input = new EditText(this);
    	alert.setView(input);

    	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
    		//strIP = input.getText();
    	  // Do something with value!
    	  }
    	});

    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    	  public void onClick(DialogInterface dialog, int whichButton) {
    	    // Canceled.
    	  }
    	});

    	alert.show();
    }
    public Runnable downloadRun = new Runnable(){  
	    @Override  
	    public void run() {  
          
	        // TODO Auto-generated method stub  
	            NetClient c = new NetClient();
	            c.downloadFile(AppKernel.config.ServerURL + "Client.cfg");
	            AppKernel.netStatus = Net_Status.DownloadingMovie;
	            c.downloadFile(AppKernel.config.ServerURL +"a.mp4");
	            AppKernel.netStatus = Net_Status.Finish;
	    }
    } ;

    Handler mHandler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            if(msg.what == 1) {  
    	        try{
                    String filename = (String) msg.obj;
    	            video.setVideoPath(filename);
    	            final MediaController mc = new MediaController(PlayerActivity.this);
        	        video.setMediaController(mc);
        	        video.requestFocus();
        	        video.start();
        	        video.setOnCompletionListener(new OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                           // stopAudio();
                        	System.out.print("on complettion");
                	

                            
                        	//pManager.
                            //    Intent i= new Intent( Intent.ACTION_REBOOT);  
                              //  sendBroadcast( i );  
                               
                             //   shutdown():  
                           //     Intent i= new Intent( Intent.ACTION_SHUTDOWN);  
                            //    sendBroadcast( i );  
                                  
                      
                                
                        }
                    });

        	        //setOnInfoListener (MediaPlayer.OnInfoListener listener)
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
                String local = AppEnv.getLocalFile(AppKernel.config.ServerURL+"a.mp4");
                msg.obj = local;
               // msg.obj 
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