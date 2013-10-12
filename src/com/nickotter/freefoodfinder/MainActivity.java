package com.nickotter.freefoodfinder;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;


import com.actionbarsherlock.view.MenuItem;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.nickotter.freefoodfinder.R;
import com.nickotter.freefoodfinder.data.GetReports;
import com.nickotter.freefoodfinder.data.Report;
import com.nickotter.freefoodfinder.gps.GPSTracker;
import com.nickotter.freefoodfinder.utils.AsyncResponse;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockActivity implements AsyncResponse<String> {
	
	private String LOGTAG = "MainActivity";
	
	protected GoogleMap mMap = null;
	protected List<Marker> markers = new ArrayList<Marker>();
	protected LatLng current;
	
	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance().activityStart(this); // Add this method.
	}
	
	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance().activityStop(this); // Add this method.
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.v(LOGTAG, "onCreate e");
		
		
		
		GPSTracker gps = new GPSTracker(this);
    	if(gps.canGetLocation()) {//gps enabled
    		Log.v(LOGTAG, "initMap: able to find location");
    				
    		this.mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
    		        .getMap();
    		
    		current = new LatLng(gps.getLatitude(), gps.getLongitude());
    		
    		this.mMap.animateCamera(
    				CameraUpdateFactory.newLatLng(current)
    		);
    		
    		this.mMap.animateCamera(
    			CameraUpdateFactory.zoomTo(21)
    		);
    		
    		this.loadReports();
    		
    	} else {
    		Log.v(LOGTAG, "initMap: unable to find location, sending to settings");
    		gps.showSettingsAlert();
    	}
    	
    	Log.v(LOGTAG, "onCreate x");
		
	}
	
	@Override
	public void onResume() {
		this.loadReports();
		
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
    {    
	   EasyTracker.getTracker().sendEvent("ui_action", "button_press", item.getTitle().toString(), (long)item.getItemId());
		
       switch (item.getItemId()) 
       {        
          case R.id.add_report:
        	  
        	  Log.v(LOGTAG, "menu item add has been clicked");
        	  
        	  Intent report = new Intent(getBaseContext(), ReportFoodActivity.class);
        	  startActivityForResult(report, 100);
        	  
        	  return true;
          case R.id.menu_refresh:
        	  this.loadReports();
        	  return true;
          default: 
        	 Log.v(LOGTAG, "onOptionItemSelected could not match menu item to action. " + item.getItemId());
             return false;    
       }
    }
	
	protected void loadReports() {
		new GetReports(this, getString(R.string.mongolabAPIKey), this.current).execute();
	}

	@Override
	public void processFinish(String output) {
		
		Gson gson = new Gson();
		Report[] reports = gson.fromJson(output, Report[].class);
		
		if (reports != null && reports.length > 0) {
			
			this.mMap.clear();
			
			Log.v(LOGTAG, "retrieved the following from MongoDB: "+ output);
			
			Log.v(LOGTAG, "onPostExecute: creating toast");
			int duration = Toast.LENGTH_SHORT;
	
			Toast toast = Toast.makeText(this, "Loaded " + reports.length + " report(s)", duration);
			toast.show();
			
			for (Report temp : reports) {
				Log.v(LOGTAG, "found report with title=" + temp.getTitle());
	    		
	    		this.mMap.animateCamera(
	    				CameraUpdateFactory.newLatLng(temp.getLocation())
	    		);
	    		
	    		
	    		this.markers.add(
					this.mMap.addMarker(new MarkerOptions()
				      .position(temp.getLocation())
				      .title(temp.getTitle())
				      .snippet(temp.getDescription())
					)
	            );
	    		
			}
		}
	}

}
