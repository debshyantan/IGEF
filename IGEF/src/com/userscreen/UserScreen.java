package com.userscreen;


import java.util.ArrayList;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.SocialNetwork.igef.R;


public class UserScreen extends ActionBarActivity implements ActionBar.TabListener{
	
	private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mMenuTitles;
    private ListView mDrawerList;
    private TypedArray navMenuIcons;
    
    MyDrawerAdapter myDrawerAdapter;
	
    private ArrayList<NavDrawerItem> navDrawerItems;
    
	
	ActionBar actionBar;
	private ViewPager viewPager;
	 MyProfilePagerAdapter1 mAdapter;
//    private int[] tabs = { R.drawable.statusupdates, R.drawable.conversations,R.drawable.friendlist,R.drawable.ic_launcher};
    private int[] tabs = { R.drawable.statusupdates, R.drawable.friendlist,R.drawable.ic_launcher};

@Override
protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
super.onCreate(savedInstanceState);
setContentView(R.layout.first);




 viewPager = (ViewPager) findViewById(R.id.viewPager);
    actionBar = getSupportActionBar();

   
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

//   for (int tab_name : tabs) {
////    	  actionBar.addTab(actionBar.newTab().setCustomView(R.layout.tab_item)
////                  .setTabListener(this));
//	   LinearLayout view = (LinearLayout) getLayoutInflater().inflate(R.layout.tab_item, null);
//	 
//       Tab newtab=actionBar.newTab();
//       newtab.setCustomView(view);
//       ImageView icon = (ImageView) newtab.getCustomView().findViewById(R.id.image_tab);
//       icon.setImageResource(tab_name);
//       newtab.setTabListener(this);
//       actionBar.addTab(newtab);
//    
//      }  
    
  
    
}








//@Override
//public void onConfigurationChanged(Configuration newConfig) {
//	// TODO Auto-generated method stub
//	super.onConfigurationChanged(newConfig);
//    mDrawerToggle.onConfigurationChanged(newConfig);
//
//	if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {     
//    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//
//
//	}
//	else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {     
//	 	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//
//
//	} 
//	else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {     
//    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//
//
//	} 
//	else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {     
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//	}
//	else {
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//	}
//}


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