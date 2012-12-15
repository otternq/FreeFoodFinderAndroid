package com.nickotter;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// setup action bar for tabs
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
        
        Tab tab = actionBar.newTab()
                .setText(R.string.menu_find_food)
                .setTabListener(new TabListener<FindFoodFragment>(
                        this, "Basic", FindFoodFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
            .setText(R.string.menu_report_food)
            .setTabListener(new TabListener<ReportFoodFragment>(
                    this, "Basic", ReportFoodFragment.class));
        actionBar.addTab(tab);
		
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}*/

}
