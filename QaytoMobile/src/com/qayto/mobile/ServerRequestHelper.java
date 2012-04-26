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
	private InputStreamReader isr = null;
	private BufferedReader br = null;
	private String line = null;
	
	public ServerRequestHelper() {}
	
	public JSONArray getSubcategories(String category) {

		JSONArray result = null;
		JSONTokener tokener = null;
		StringBuilder sb = null;
		HttpURLConnection connection = null;
		
		//Get data from the server
		try {
			URL url = new URL(serverLocation + "sub_categories.php");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestMethod("POST");
			
			OutputStreamWriter request = new OutputStreamWriter(connection.getOutputStream());
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
		} finally {
			connection.disconnect();
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
	
	public void createQuestion(String question, int subcat) {

		HttpURLConnection connection = null;
		URL url = null;
		OutputStreamWriter request = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		try {
			url = new URL(serverLocation + "question_create.php");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestMethod("POST");

			request = new OutputStreamWriter(connection.getOutputStream());
			request.write("question=" + question + "&sub_cat_id=" + subcat);
			request.flush();
			request.close();
			
			isr = new InputStreamReader(connection.getInputStream());
			br = new BufferedReader(isr);
			
			isr.close();
			br.close();
			
		} catch (Exception e) {
			Log.e("Problem connecting to HTTP server...", e.getMessage());
		} finally {
			connection.disconnect();
		}
	}
	
	public void updateQuestion(String question, int isAnswered, int rating) {

		HttpURLConnection connection = null;
		URL url = null;
		OutputStreamWriter request = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		try {
			url = new URL(serverLocation + "question_update.php");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestMethod("POST");

			request = new OutputStreamWriter(connection.getOutputStream());
			request.write("question=" + question + "&isAnswered=" + isAnswered + "&rating=" + rating);
			request.flush();
			request.close();
			
			isr = new InputStreamReader(connection.getInputStream());
			br = new BufferedReader(isr);
			
			isr.close();
			br.close();
			
		} catch (Exception e) {
			Log.e("Problem connecting to HTTP server...", e.getMessage());
		} finally {
			connection.disconnect();
		}
	}
}
