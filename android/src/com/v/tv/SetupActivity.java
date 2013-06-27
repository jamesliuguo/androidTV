package com.v.tv;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class SetupActivity extends Activity {
	private EditText edtIP;
	private EditText editClientID;
	private CheckBox chkShowSeting;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
             
        edtIP = (EditText) findViewById(R.id.editTextIP);
        edtIP.setHint("Input the server ip");
        editClientID  = (EditText) findViewById(R.id.editName);
        editClientID.setHint("Give a name to this client");
        chkShowSeting = (CheckBox) findViewById(R.id.checkBox1);
        Button btn = (Button) findViewById(R.id.buttonSave);
        

		edtIP.setText(Configuration.getInstance().basConfig.serverIP);
		editClientID.setText(Configuration.getInstance().basConfig.clientName);
		
        btn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String strIp = edtIP.getText().toString();
				String strName = editClientID.getText().toString();
				boolean bShow = chkShowSeting.isChecked();
				
				Configuration.getInstance().basConfig.serverIP = strIp;
				Configuration.getInstance().basConfig.clientName = strName;
				Configuration.getInstance().basConfig.showSetting = bShow;
				Configuration.getInstance().basConfig.save();
				
	    		Intent t = new Intent();
	    		t.setClass(SetupActivity.this, PlayerActivity.class);
	    		startActivity(t);
	    		SetupActivity.this.finish();
	    		
	    		Configuration.getInstance().siteConfig.init();
	    		PlayerActivity.playActivity.settingChanges();
				
			}
        });
    
 
    }
}