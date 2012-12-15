package com.nickotter.freefoodfinder.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

import android.util.Log;

public class SaveReport {
	
	private static final String LOGTAG = "SaveReport";
	
	private static String apiKey;
	private static final String baseEndpoint = "https://api.mongolab.com/api/1/databases/"; 
	private static final String entriesEndpoint = baseEndpoint + "freefoodfinder/collections/entries";
	
	public SaveReport(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public void save(Report report) {
		Log.v(LOGTAG, "saveReport: E");
		
		Log.v(LOGTAG, "saveReport: Title=" + report.getTitle());
    	Log.v(LOGTAG, "saveReport: Location Description=" + report.getLocationDescription());
    	Log.v(LOGTAG, "saveReport: Description= " + report.getDescription());
    	
    	DefaultHttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost(entriesEndpoint + "?apiKey="+apiKey);
    	
    	try {
    		
    		Gson gson = new Gson();
    		
    		List<NameValuePair> postVariables = new ArrayList<NameValuePair>();
    		postVariables.add(new BasicNameValuePair("q", gson.toJson(report)));
    		
    		Log.v(LOGTAG, "saveReport: Execute HTTP Post Request");
    		HttpResponse response = httpclient.execute(httppost);
    		
    		
    	} catch (ClientProtocolException e) {
    		
    	} catch (IOException e) {
    		
    	}
		
    	Log.v(LOGTAG, "saveReport: X");
	}

}
