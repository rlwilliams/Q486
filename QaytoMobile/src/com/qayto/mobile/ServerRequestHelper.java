package com.qayto.mobile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

//It may be a good idea to implement these things as AsyncTasks

public class ServerRequestHelper {
	private final String location = "http://...";
	
	private ArrayList<NameValuePair> nameValuePairs;
	private HttpClient client;
	private HttpPost post;
	private HttpResponse response;
	private HttpEntity entity;
	private InputStream in;
	private BufferedReader reader;
	private StringBuilder stringBuilder;
	
	public ServerRequestHelper() {
		nameValuePairs = new ArrayList<NameValuePair>();
		client = new  DefaultHttpClient();
		stringBuilder = new StringBuilder();
	}
	
	public JSONArray getSubcategories(String category) {
		String result = "";
		String line = null;
		JSONArray jResult = null;
		
		//Get data from the server
		try {
			this.post = new HttpPost(location);
			this.nameValuePairs.clear();
			this.nameValuePairs.add(new BasicNameValuePair("category", category));
			this.post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			this.response = client.execute(post);
			this.entity = response.getEntity();
			this.in = entity.getContent();
		} catch(Exception e) {
			Log.e("Problem connecting to HTTP server...", e.getMessage());
		}
		
		//Convert the HTTP response to a string
		try {
			reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"), 8);
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			in.close();
			result = stringBuilder.toString();
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " +e.toString());
		}
		
		//Parse JSON data
		try {
			jResult = new JSONArray(result);
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing JSON data " + e.toString());
		}
		
		return jResult;
	}
}
