package com.v.tv;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class SetupActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
             
        final EditText ip = (EditText) findViewById(R.id.editTextIP);
        ip.setHint("Input the server ip");
        final EditText name = (EditText) findViewById(R.id.editName);
        name.setHint("Give a name to this client");
        Button btn = (Button) findViewById(R.id.buttonSave);
        
		Configuration.basConfig.read();
		ip.setText(Configuration.basConfig.serverIP);
		name.setText(Configuration.basConfig.clientName);
		
        btn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String strIp = ip.getText().toString();
				String strName = name.getText().toString();
				
				Configuration.basConfig.serverIP = strIp;
				Configuration.basConfig.clientName = strName;
				Configuration.basConfig.save();
				
			}
        });
    
 
    }
}