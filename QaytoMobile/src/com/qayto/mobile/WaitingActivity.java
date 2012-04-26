package com.qayto.mobile;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class WaitingActivity extends Activity {
	
	ServerRequestHelper srh;
	int subcatIndex;
	
    /** Called when the activity is first created. */
    @Override
    	public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.waiting);
            
            subcatIndex = 1;
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
    			subcatIndex = extras.getInt("subcatIndex");
    		}
            srh = new ServerRequestHelper();
            Button connect = (Button) findViewById(R.id.button1);
            connect.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                	
                	EditText text = (EditText)findViewById(R.id.editText1);
                	String question = text.getText().toString();
                	
                	srh.createQuestion(question, subcatIndex);
                	
                	Intent intent = new Intent();
      				intent.setClass(WaitingActivity.this, MediaPlayer_Video.class);
      				intent.putExtra("question", question);
                	intent.setAction(Intent.ACTION_MAIN);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY
                            | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
    }
}
