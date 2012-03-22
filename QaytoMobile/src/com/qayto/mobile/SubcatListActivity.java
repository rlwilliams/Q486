package com.qayto.mobile;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubcatListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		JSONArray sample = new JSONArray();
		sample.put("Subcategory 1");
		sample.put("Subcategory 2");
		sample.put("Subcategory 3");
		sample.put("Subcategory 4");
		sample.put("Subcategory 5");
		sample.put("Subcategory 6");
		
		ArrayList<String> sampleData = new ArrayList<String>();
		
		try {
			
			for (int i = 0; i<sample.length(); i++) {
				sampleData.add(sample.getString(i));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("log_tag", e.toString());
		}
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, sampleData));
		ListView lv = this.getListView();
		lv.setTextFilterEnabled(true);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});
	}
	
}