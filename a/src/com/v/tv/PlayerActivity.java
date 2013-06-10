package com.v.tv;

import android.app.Activity;
import android.os.Bundle;

public class PlayerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        

        //String str = c.Get("");
        new Thread(downloadRun).start();  
    }
    
    public Runnable downloadRun = new Runnable(){  
	    @Override  
	    public void run() {  
	        // TODO Auto-generated method stub  
	            NetClient c = new NetClient();
	            String str = c.Get("");
	    }
    } ;
}