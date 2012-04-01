package com.qayto.mobile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;


public class ServerRequestHelper {
	
	private final String serverLocation = "http://capstone.robertwinkler.com/";
	public ServerRequestHelper() {}
	
	public String getSubcategories(String category) {

		URL url = null;
		HttpURLConnection connection = null;
		OutputStreamWriter request = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuilder sb = null;
		String line = null;
		String response = null;
		
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
			
			response = sb.toString();
			isr.close();
			br.close();
		} catch(Exception e) {
			Log.e("Problem connecting to HTTP server...", e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}
}


//http://stackoverflow.com/questions/4470936/how-to-do-a-http-post-in-android