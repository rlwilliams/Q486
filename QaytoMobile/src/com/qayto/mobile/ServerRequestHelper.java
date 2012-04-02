package com.qayto.mobile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.util.Log;


public class ServerRequestHelper {
	
	private final String serverLocation = "http://capstone.robertwinkler.com/";
	public ServerRequestHelper() {}
	
	public JSONArray getSubcategories(String category) {

		JSONArray result = null;
		JSONTokener tokener = null;
		URL url = null;
		HttpURLConnection connection = null;
		OutputStreamWriter request = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuilder sb = null;
		String line = null;
		
		//Get data from the server
		try {
			url = new URL(serverLocation + "sub_categories.php");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestMethod("POST");
			
			request = new OutputStreamWriter(connection.getOutputStream());
			request.write("cat_id=" + category);
			request.flush();
			request.close();
			
			isr = new InputStreamReader(connection.getInputStream());
			br = new BufferedReader(isr);
			sb = new StringBuilder();
			
			line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			isr.close();
			br.close();
		} catch(Exception e) {
			Log.e("Problem connecting to HTTP server...", e.getMessage());
		}
		
		//Parse response into JSONArray
		try {
			tokener = new JSONTokener(sb.toString());
			result = new JSONArray(tokener);
		} catch (JSONException e) {
			Log.e("Problem parsing JSON from response...", e.getMessage());
		}
		
		return result;
	}
}


//http://stackoverflow.com/questions/4470936/how-to-do-a-http-post-in-android