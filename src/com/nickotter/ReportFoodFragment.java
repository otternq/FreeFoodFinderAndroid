package com.nickotter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public class ReportFoodFragment  extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	this.setHasOptionsMenu(true);
    	
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.report_food, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.report_food, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
	
}