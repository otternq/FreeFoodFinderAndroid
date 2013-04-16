package com.nickotter.freefoodfinder;

import com.nickotter.freefoodfinder.R;
import com.nickotter.freefoodfinder.data.MyLatLng;
import com.nickotter.freefoodfinder.data.Report;
import com.nickotter.freefoodfinder.data.SaveReport;
import com.nickotter.freefoodfinder.gps.GPSTracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;


public class ReportFoodActivity extends SherlockActivity {
	
	private static final String LOGTAG = "ReportFoodFragment";

    public void onCreate(Bundle savedInstanceState) {
    	Log.v(LOGTAG, "onCreateView: E");
    	super.onCreate(savedInstanceState);
    	
        // Inflate the layout for this fragment
        setContentView(R.layout.report_food);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.report_food, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Log.v(LOGTAG, "onOptionsItemSelected: E");
    	
    	switch(item.getItemId()) {
    		case android.R.id.home:
    		case R.id.menu_cancel:
    			Log.v(LOGTAG, "onOptionsItemSelected: Clicked the home or cancel button");
    			finish();
    		break;
    		case R.id.menu_save:
    			Log.v(LOGTAG, "onOptionsItemSelected: Clicked the Save Menu item");
    			this.createReport();
    			finish();
    			break;
    		default:
    			Log.v(LOGTAG, "onOptionsItemSelected: failed to identify what was clicked");
    			return false;
    	}
    	
    	return true;
    }
    
    private void createReport() {
    	EditText title = (EditText)findViewById(R.id.report_food_title);
    	EditText locationDescription = (EditText)findViewById(R.id.report_food_location);
    	EditText description = (EditText)findViewById(R.id.report_food_description);
    	
    	Log.v(LOGTAG, "createReport: creating report");
    	
    	Report report = new Report();
    	report.setTitle(title.getText().toString());
    	report.setLocationDescription(locationDescription.getText().toString());
    	report.setDescription(description.getText().toString());
    	
    	GPSTracker gps = new GPSTracker(this);
    	if(gps.canGetLocation()) {//gps enabled
    		Log.v(LOGTAG, "createReport: able to find location");
    		report.setLocation(
    				new MyLatLng(
    						gps.getLatitude(), 
    						gps.getLongitude()
    				)
			);
    		
    		Log.v(LOGTAG, "createReport: sending the report to SaveReport.save");
        	new SaveReport(this, getString(R.string.mongolabAPIKey), report).execute();
        	
    	} else {
    		Log.v(LOGTAG, "createReport: unable to find location, sending to settings");
    		gps.showSettingsAlert();
    	}
    	
    	Log.v(LOGTAG, "createReport: X");
    }
    
	
}