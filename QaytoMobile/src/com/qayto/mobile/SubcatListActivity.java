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
		
		Bundle extras = getIntent().getExtras();
		String subcategories = "";
		if (extras != null) {
			subcategories = extras.getString("subcategories");
		}
		
		ArrayList<String> data = new ArrayList<String>();
		
		try {
			JSONArray jdata = new JSONArray(subcategories);
			for (int i = 0; i<jdata.length(); i++) {
				data.add(jdata.getString(i));
			}
		} catch (JSONException e) {
			Log.e("JSON error...", e.getMessage());
		}
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, data));
		ListView lv = this.getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});
	}
	
}