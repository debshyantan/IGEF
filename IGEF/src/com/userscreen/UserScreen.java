package com.userscreen;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.SocialNetwork.igef.R;

public class UserScreen extends ActionBarActivity implements TabListener {

	android.support.v7.app.ActionBar act;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_igef_socail_network);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new Status()).commit();
		}

		act = getSupportActionBar();
		act.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		act.addTab(act.newTab().setIcon(R.drawable.statusupdates)
				.setTabListener(this));

		act.addTab(act.newTab().setIcon(R.drawable.conversations)
				.setTabListener(this));
		act.addTab(act.newTab().setIcon(R.drawable.friendlist)
				.setTabListener(this));
		act.setDisplayHomeAsUpEnabled(true);
		act.addTab(act.newTab().setText("Post").setTabListener(this));


	}
	
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      // Respond to the action bar's Up/Home button
	      case android.R.id.home:
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    }
	 
	    return super.onOptionsItemSelected(item);
	  }

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		if (arg0.getPosition() == 0) {

			getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, new Status()).commit();
		}
		if (arg0.getPosition() == 1) {

			getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, new Conversation()).commit();

		}

		else if (arg0.getPosition() == 2) {

			getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, new FreindsList()).commit();
		}
		
		
		else if (arg0.getPosition() == 3) {
			
			getSupportFragmentManager().beginTransaction().replace(R.id.container, new PostStatus()).commit();
		}
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

}
