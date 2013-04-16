package com.nickotter.freefoodfinder.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.nickotter.freefoodfinder.utils.AsyncResponse;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class GetReports extends AsyncTask<String, String, String> {
	
	private String LOGTAG = "GetReports";
	
	private String apiKey;
	private static final String baseEndpoint = "https://api.mongolab.com/api/1/databases/"; 
	private static final String entriesEndpoint = baseEndpoint + "freefoodfinder/collections/entries";
	
	private Context appContext;
	private Report report;

	protected LatLng location;
	
	public GetReports(Context appContext, String apiKey, LatLng currentLocation) {
		this.appContext = appContext;
		this.apiKey = apiKey;
		this.location = currentLocation;
	}
	
	protected String getUrl() {
		String url = "";
		try {
			url = GetReports.entriesEndpoint + "?q=" + URLEncoder.encode("{\"location\":{\"$near\":[45,-116],\"$maxDistance\":5}}", "UTF-8") + "&apiKey=" + this.apiKey;
			Log.v(LOGTAG, "query = " + url);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
	protected String retrieve() {
		
		String LOGTAG = this.LOGTAG + " retrieve";
		
		Log.v(LOGTAG, "e");
		
		String url = this.getUrl();
		
		HttpClient httpclient = new DefaultHttpClient();  
        HttpGet request = new HttpGet(url);  
        ResponseHandler<String> handler = new BasicResponseHandler();  
        
        String result = "";
        
        Log.v(LOGTAG, "trying to execute request");
        
		try {  
            result = httpclient.execute(request, handler);  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		
		Log.v(LOGTAG, "shutting down connection manager");
        httpclient.getConnectionManager().shutdown();   
        
        Log.i(LOGTAG, result);
        
        Log.v(LOGTAG, "x");
        return result;
		
	}

	@Override
	protected String doInBackground(String... params) {
		Log.v(LOGTAG, "doInBackground: E");
		return this.retrieve();
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		((AsyncResponse) this.appContext).processFinish(result);
		
		Log.v(LOGTAG, "onPostExecute: result is: "+ result);
	}

}
