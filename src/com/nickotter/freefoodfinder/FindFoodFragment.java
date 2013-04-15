package com.nickotter.freefoodfinder;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nickotter.freefoodfinder.R;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nickotter.freefoodfinder.data.SaveReport;
import com.nickotter.freefoodfinder.gps.GPSTracker;

public class FindFoodFragment extends MapFragment {
	
	private static final String LOGTAG = "FindFoodFragment";
	
	private GoogleMap mMap = null;
	
	@Override
	public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2) {
	    View v = super.onCreateView(arg0, arg1, arg2);
    	this.setHasOptionsMenu(true);
    	
	    initMap();
	    
	    return v;
	}
    
    public void initMap() {
    	Log.v(LOGTAG, "initMap: E");
    	
    	GPSTracker gps = new GPSTracker(getActivity());
    	if(gps.canGetLocation()) {//gps enabled
    		Log.v(LOGTAG, "initMap: able to find location");
    				
    		this.mMap = this.getMap();
    		
    		LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
    		
    		this.mMap.animateCamera(
    				CameraUpdateFactory.newLatLng(MELBOURNE)
    		);
    		
    		
    		Marker melbourne = mMap.addMarker(new MarkerOptions()
                  .position(MELBOURNE)
                  .title("Melbourne")
                  .snippet("Population: 4,137,400"));
    		
    	} else {
    		Log.v(LOGTAG, "initMap: unable to find location, sending to settings");
    		gps.showSettingsAlert();
    	}
    	
    	Log.v(LOGTAG, "initMap: X");
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
	
}