
package com.SocialNetwork.igef;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.Prefrence.IGEFSharedPrefrence;
import com.userscreen.UserScreen;

public class GettingStarted extends ActionBarActivity implements
		ActionBar.TabListener {
	static ActionBar act;
	static FragmentManager fm;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_igef_socail_network);

		fm = getSupportFragmentManager();

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new Register()).commit();
		}
		act = getSupportActionBar();
		act.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		act.addTab(act.newTab().setText("REGISTER NOW").setTabListener(this));
		act.addTab(act.newTab().setText("LOGIN").setTabListener(this));
		act.setDisplayHomeAsUpEnabled(true);

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
		if (arg0.getPosition() == 0) {

			fm.beginTransaction()
					.replace(R.id.container, new Register())
					.commit();
		}

		else if (arg0.getPosition() == 1) {
			fm.beginTransaction()
					.replace(R.id.container, new LoginStudent())
					.commit();
		}

	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	public static void replace(FragmentActivity fragmentActivity, int i) {
		fragmentActivity.getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.container, new Register())
		.commit();
		
			act.setSelectedNavigationItem(1);
		
	}

}

