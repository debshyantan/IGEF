package com.userscreen;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;

import com.SocialNetwork.igef.R;


public class UserScreen extends ActionBarActivity implements ActionBar.TabListener{
	

    
	
	ActionBar actionBar;
	private ViewPager viewPager;
	 MyProfilePagerAdapter1 mAdapter;
//    private int[] tabs = { R.drawable.statusupdates, R.drawable.conversations,R.drawable.friendlist,R.drawable.ic_launcher};
    private int[] tabs = { R.drawable.statusupdates, R.drawable.friendlist,R.drawable.menutab};

@Override
protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
super.onCreate(savedInstanceState);
setContentView(R.layout.first);



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
//private boolean doubleBackToExitPressedOnce = false;

//@Override
//public void onBackPressed() {
//	
////	super.onBackPressed();
////	 final boolean doubleBackToExitPressedOnce;
//
//	if (doubleBackToExitPressedOnce) {
//        
//		System.out.println("2");
//        Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG).show();
//        
//    	super.onBackPressed();
//    	
//    } else {
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
//    }
//
//
//	
//	    	 
//	      
//	}
	        
	        
	      
	   
	





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