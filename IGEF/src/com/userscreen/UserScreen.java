package com.userscreen;


import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;

import com.SocialNetwork.igef.R;


public class UserScreen extends ActionBarActivity implements ActionBar.TabListener{
	
//	private DrawerLayout mDrawerLayout;
//    private ActionBarDrawerToggle mDrawerToggle;
//    private CharSequence mDrawerTitle;
//    private CharSequence mTitle;
//    private String[] mMenuTitles;
//    private ListView mDrawerList;
//    private TypedArray navMenuIcons;
    
//    MyDrawerAdapter myDrawerAdapter;
	
//    private ArrayList<NavDrawerItem> navDrawerItems;
    
	
	ActionBar actionBar;
	private ViewPager viewPager;
	 MyProfilePagerAdapter1 mAdapter;
//    private int[] tabs = { R.drawable.statusupdates, R.drawable.conversations,R.drawable.friendlist,R.drawable.ic_launcher};
    private int[] tabs = { R.drawable.statusupdates, R.drawable.friendlist,R.drawable.menu};

@Override
protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
super.onCreate(savedInstanceState);
setContentView(R.layout.first);

//mMenuTitles = getResources().getStringArray(R.array.nav_drawer);
//navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
////mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//
//mDrawerList = (ListView) findViewById(R.id.left_drawer);
//mTitle = mDrawerTitle = getTitle();
//
//navDrawerItems = new ArrayList<NavDrawerItem>();
//
//// adding nav drawer items to array
//// Home
//navDrawerItems.add(new NavDrawerItem(mMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
//// Find People
//navDrawerItems.add(new NavDrawerItem(mMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
//// Photos
//navDrawerItems.add(new NavDrawerItem(mMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
//// Communities, Will add a counter here
//navDrawerItems.add(new NavDrawerItem(mMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
//// Pages
//navDrawerItems.add(new NavDrawerItem(mMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
//// What's hot, We  will add a counter here
//navDrawerItems.add(new NavDrawerItem(mMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
//
//// Recycle the typed array
//navMenuIcons.recycle();
//
//
//
//myDrawerAdapter = new MyDrawerAdapter(getApplicationContext(), navDrawerItems);
//mDrawerList.setAdapter(myDrawerAdapter);
//
//
//
//
//mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
//        R.drawable.ic_drawer, //nav menu toggle icon
//        R.string.app_name, // nav drawer open - description for accessibility
//        R.string.app_name // nav drawer close - description for accessibility
//){
//    public void onDrawerClosed(View view) {
//    	getSupportActionBar().setTitle(mTitle);
//        // calling onPrepareOptionsMenu() to show action bar icons
//        invalidateOptionsMenu();
//    }
//
//    public void onDrawerOpened(View drawerView) {
//        getSupportActionBar().setTitle(mDrawerTitle);
//        // calling onPrepareOptionsMenu() to hide action bar icons
//        invalidateOptionsMenu();
//    }
//};
//mDrawerLayout.setDrawerListener(mDrawerToggle);
//
//if (savedInstanceState == null) {
//    // on first time display view for first nav item
////    displayView(0);
//}

// Set the adapter for the list view
//mDrawerList.setAdapter(new ArrayAdapter<String>(this,
//        R.layout.drawer_list_item, mPlanetTitles));

//menu = new SlidingMenu(this);
//menu.setMode(SlidingMenu.LEFT);
//menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
////menu.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
////menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//menu.setShadowDrawable(R.drawable.shadow);
//menu.setFadeDegree(0.35f);
//menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
//menu.setMenu(R.layout.menu);
//
//getActionBar().setDisplayHomeAsUpEnabled(true);


 viewPager = (ViewPager) findViewById(R.id.viewPager);
    actionBar = getSupportActionBar();
//    actionBar.setDisplayHomeAsUpEnabled(true);
   
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

//private void displayView(int i) {
//	
////        // update the main content by replacing fragments
////        Fragment fragment = null;
////        switch (position) {
////        case 0:
////            fragment = new HomeFragment();
////            break;
////        case 1:
////            fragment = new FindPeopleFragment();
////            break;
////        case 2:
////            fragment = new PhotosFragment();
////            break;
////        case 3:
////            fragment = new CommunityFragment();
////            break;
////        case 4:
////            fragment = new PagesFragment();
////            break;
////        case 5:
////            fragment = new WhatsHotFragment();
////            break;
//// 
////        default:
////            break;
//       
//	
//}

//@Override
//protected void onPostCreate(Bundle savedInstanceState) {
//    super.onPostCreate(savedInstanceState);
//    // Sync the toggle state after onRestoreInstanceState has occurred.
//    mDrawerToggle.syncState();
//}




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