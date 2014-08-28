package com.userscreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyProfilePagerAdapter1 extends FragmentPagerAdapter {
 
    public MyProfilePagerAdapter1(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            
            return new Status();
        case 1:
            
            return new Conversation();
            
        case 2:
        	
        	return new FreindsList();
        	
        case 3:
        	
        	return new UserTask();
        	
    
   
      
     
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 4;
    }
 
}
