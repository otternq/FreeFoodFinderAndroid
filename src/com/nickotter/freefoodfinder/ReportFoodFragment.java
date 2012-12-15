package com.nickotter.freefoodfinder;

import com.nickotter.freefoodfinder.R;
import com.nickotter.freefoodfinder.data.Report;
import com.nickotter.freefoodfinder.data.SaveReport;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.util.Log;

public class ReportFoodFragment  extends Fragment {
	
	private static final String LOGTAG = "ReportFodFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	Log.v(LOGTAG, "onCreateView: E");
    	
    	this.setHasOptionsMenu(true);
    	
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.report_food, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.report_food, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Log.v(LOGTAG, "onOptionsItemSelected: E");
    	
    	switch(item.getItemId()) {
    		case R.id.menu_save:
    			Log.v(LOGTAG, "onOptionsItemSelected: Clicked the Save Menu item");
    			this.createReport();
    			break;
    		default:
    			Log.v(LOGTAG, "onOptionsItemSelected: failed to identify what was clicked");
    			return false;
    	}
    	
    	return true;
    }
    
    private void createReport() {
    	EditText title = (EditText)getView().findViewById(R.id.report_food_title);
    	EditText locationDescription = (EditText)getView().findViewById(R.id.report_food_location);
    	EditText description = (EditText)getView().findViewById(R.id.report_food_description);
    	
    	Log.v(LOGTAG, "createReport: creating report");
    	
    	Report report = new Report();
    	report.setTitle(title.getText().toString());
    	report.setLocationDescription(locationDescription.getText().toString());
    	report.setDescription(description.getText().toString());
    	
    	Log.v(LOGTAG, "createReport: sending the report to SaveReport.save");
    	new SaveReport(getActivity(), getString(R.string.mongolabAPIKey), report).execute();
    }
	
}