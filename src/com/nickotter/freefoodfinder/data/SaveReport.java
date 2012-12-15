package com.nickotter.freefoodfinder.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.google.gson.Gson;

import android.os.AsyncTask;
import android.util.Log;

public class SaveReport extends AsyncTask<String, String, String> {
	
	private static final String LOGTAG = "SaveReport";
	
	private String apiKey;
	private static final String baseEndpoint = "https://api.mongolab.com/api/1/databases/"; 
	private static final String entriesEndpoint = baseEndpoint + "freefoodfinder/collections/entries";
	
	private Report report;
	
	public SaveReport(String apiKey, Report report) {
		this.apiKey = apiKey;
		this.report = report;
	}
	
	public String save() {
		Log.v(LOGTAG, "saveReport: E");
		
		Log.v(LOGTAG, "saveReport: Title=" + this.report.getTitle());
    	Log.v(LOGTAG, "saveReport: Location Description=" + this.report.getLocationDescription());
    	Log.v(LOGTAG, "saveReport: Description= " + this.report.getDescription());
    	
    	String url = SaveReport.entriesEndpoint + "?apiKey="+this.apiKey;
    	Log.v(LOGTAG, "saveReport: url=" + url);
    	
    	String responseString = null;
    	
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost(url);
    	
    	try {
    		
    		Gson gson = new Gson();
    		String insertJSON = gson.toJson(this.report);
    		
    		StringEntity entity = new StringEntity(insertJSON);
    		entity.setContentType("application/json");
    		httppost.setEntity(entity);
    		
    		Log.v(LOGTAG, "saveReport: Execute HTTP Post Request");
    		HttpResponse response = httpclient.execute(httppost);
    		
    		StatusLine statusLine = response.getStatusLine();
    		
    		if(statusLine.getStatusCode() == HttpStatus.SC_OK) {
    			Log.v(LOGTAG, "saveReport: recieved HTTP Status OK, converting resposne to string");
    			ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
    		} else {
    			Log.v(LOGTAG, "saveReport: unacceptable HTTP Status "+ statusLine.getStatusCode() +", throwing IOException");
    			//Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
    		}
    		
    		
    	} catch (ClientProtocolException e) {
    		
    	} catch (IOException e) {
    		
    	}
		
    	Log.v(LOGTAG, "saveReport: X");
    	return responseString;
	}

	@Override
	protected String doInBackground(String... params) {
		Log.v(LOGTAG, "doInBackground: E");
		return this.save();
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
		Log.v(LOGTAG, "onPostExecute: result is: "+ result);
	}

}
