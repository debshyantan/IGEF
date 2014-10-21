package com.userscreen;


import Tool.ConnectionDetector;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.igef.R;
import com.SocialNewtwork.AsyncTask.ProfilePhotoAsyncTask;


public class UserScreen extends ActionBarActivity implements ActionBar.TabListener{
	

	Boolean isInternetPresent = false;
	ConnectionDetector cd;
	
	ActionBar actionBar;
	private ViewPager viewPager;
	 MyProfilePagerAdapter1 mAdapter;
//    private int[] tabs = { R.drawable.statusupdates, R.drawable.conversations,R.drawable.friendlist,R.drawable.menutab};
//
//    private int[] tabs = { R.drawable.statusupdates, R.drawable.conversations,R.drawable.menuicon};

        private int[] tabs = { R.drawable.statusupdates, R.drawable.friendlist,R.drawable.menuicon};

@Override
protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
super.onCreate(savedInstanceState);
setContentView(R.layout.first);

//connection checking
		cd = new ConnectionDetector(getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		System.out.println("Network states:" + isInternetPresent);
		



 viewPager = (ViewPager) findViewById(R.id.viewPager);
    actionBar = getSupportActionBar();
//    actionBar.setDisplayHomeAsUpEnabled(true);
    viewPager.setOffscreenPageLimit(3);
   
    mAdapter = new MyProfilePagerAdapter1(getSupportFragmentManager());
    

    viewPager.setAdapter(mAdapter);
 //  viewPager.setBackgroundColor(getResources().getColor(android.R.color.white));
  
    
    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    	 
        @Override
        public void onPageSelected(int position) {
            // on changing the page
            // make respected tab selected
            actionBar.setSelectedNavigationItem(position);
            
            
        }
     
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
     
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    });



    
    
    
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);     
    




   //  Adding Tabs
    for (int tab_name : tabs) {
    	
        actionBar.addTab(actionBar.newTab().setIcon(tab_name)
                .setTabListener(this));
    }


  
    //run the Asynctask only if thier is some value in the shared prefrence
    
    if (!IGEFSharedPrefrence.getPROFILEPICURL().equals("null")) {
    	//Asynk Task to download Profile Photo of User
    	
    	
		if (isInternetPresent) {
    	new ProfilePhotoAsyncTask(UserScreen.this).execute();
    	

		}
		
		else {

				
				}
		
	}
    
  

}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.userscreenmenu, menu);

       return true;	


	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.writepost:
			
			viewPager.setCurrentItem(2);
			
			getSupportFragmentManager().beginTransaction().replace(R.id.container, new PostStatus()).addToBackStack(null).commit();
			
	
			break;
			
		case R.id.groupmsg:
			
			viewPager.setCurrentItem(2);
			
			getSupportFragmentManager().beginTransaction().replace(R.id.container, new GroupMsg()).addToBackStack(null).commit();
			
	
			break;	

		default:
			return super.onOptionsItemSelected(item);
		}

		return false;


		}

@Override
public void onBackPressed() {
	
//	super.onBackPressed();
//	 final boolean doubleBackToExitPressedOnce;

	if (viewPager.getCurrentItem()>0) {
		
		
		if(getSupportFragmentManager().getBackStackEntryCount()>0){
			super.onBackPressed();
			
		}
		else {
			
			viewPager.setCurrentItem(0);
//			status.o
		}
        
		
        
    	
    	
    } else if (viewPager.getCurrentItem()==0) {
    	super.onBackPressed();
    }
		
//	} {
//    	System.out.println("You have Pressed back,  Status screen will open");
//        System.out.println("1");
//    	
//    	getSupportFragmentManager().beginTransaction().replace(R.id.container, new Register()).commit();
//    	
//    	actionBar.setSelectedNavigationItem(0);
//    	
//        this.doubleBackToExitPressedOnce = true;
//        
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//            	doubleBackToExitPressedOnce = false;
//            }
//        }, 2000);
    
   
	}

@Override
public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
// TODO Auto-generated method stub

}
@Override
public void onTabSelected(Tab tab, FragmentTransaction arg1) {
// TODO Auto-generated method stub
viewPager.setCurrentItem(tab.getPosition());
}
@Override
public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
// TODO Auto-generated method stub

}
}