package com.gcm;



import com.SocialNetwork.igef.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gcm);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
    	//EditText t;
    	
    	Button b;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_gcm, container, false);
           IGEFSharedPref pref=new IGEFSharedPref(getActivity());
          // t=(EditText)rootView.findViewById(R.id.txt);
           b=(Button)rootView.findViewById(R.id.btn);
           
            b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
							new AsyncTask<Void, Void, Void>(){

								@Override
								protected Void doInBackground(Void... params) {
									// TODO Auto-generated method stub
									MessageSenderGCM.AndroidPush("Welcome to IGEF NETWORKS, Registartion Successful.!!",getActivity());
									return null;
									
								}
								  
								
							}.execute();
						
					
					// TODO Auto-generated method stub
					
					
				
				}
				
			});
            
            return rootView;
        }
    }

}
