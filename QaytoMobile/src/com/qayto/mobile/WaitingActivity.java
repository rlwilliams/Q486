package com.qayto.mobile;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;


public class WaitingActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    	public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.waiting);
            
            Button connect = (Button) findViewById(R.id.ok);
            connect.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                	Intent intent = new Intent();
    				intent.setClass(WaitingActivity.this, QaytoMobileActivity.class);
                    startActivity(intent);
                }
            });
                
             Button back = (Button) findViewById(R.id.button1);
                back.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                    	Intent intent = new Intent();
                    	intent.putExtra("subcatIndex", 1);
        				intent.setClass(WaitingActivity.this, SubcatListActivity.class);
                        startActivity(intent);
                    }
                });
    }
}
