package com.qayto.mobile;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class RatingsActivity extends Activity {
	private RatingBar ratingBar;
	  private TextView txtRatingValue;
	  private Button btnSubmit;
	 
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ratings);
	 
		addListenerOnRatingBar();
		addListenerOnButton();
        final RadioButton Yes = (RadioButton) findViewById(R.id.Yes);
        final RadioButton No = (RadioButton) findViewById(R.id.No);
        Yes.setOnClickListener(radio_listener);
        No.setOnClickListener(radio_listener);
	  }
	    OnClickListener radio_listener = new OnClickListener() {
	    	public void onClick(View v){
	    		//Perform action on clicks
	    		RadioButton rb = (RadioButton) v;
	    		Toast.makeText(RatingsActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
	    	}	
	    
	};

	  public void addListenerOnRatingBar() {
	 
		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);
	 
		//if rating value is changed,
		//display the current rating value in the result (textview) automatically
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating,boolean fromUser) 
			{ 
				txtRatingValue.setText(String.valueOf(rating));
			}
		});
	  }
	 
	  public void addListenerOnButton() {
	 
		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
	 
		//if click on me, then display the current rating value.
		btnSubmit.setOnClickListener(new OnClickListener() 
		{
	 
			public void onClick(View v) {
	 
				Toast.makeText(RatingsActivity.this,String.valueOf(ratingBar.getRating()),Toast.LENGTH_SHORT).show();
	 
			}
	 
		});
	 
	  }
}

