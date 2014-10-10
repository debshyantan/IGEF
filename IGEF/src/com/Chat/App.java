package com.Chat;

import java.util.ArrayList;

import android.app.Application;

import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.igef.IgefSocailNetwork;
import com.parse.Parse;
import com.parse.PushService;
import com.userscreen.Custom;

public class App extends Application {

    
    
    public  ArrayList<Custom> statuslist;
    public  ArrayList<Custom> FriendsArraylist;
    public ArrayList<Custom> clglist;
    public ArrayList<Custom> asgnlist;

	public ArrayList<Custom> getAsgnlist() {
		return asgnlist;
	}
	public void setAsgnlist(ArrayList<Custom> asgnlist) {
		this.asgnlist = asgnlist;
	}
	public ArrayList<Custom> getClglist() {
		return clglist;
	}
	public void setClglist(ArrayList<Custom> clglist) {
		this.clglist = clglist;
	}
	public ArrayList<Custom> getStatuslist() {
		return statuslist;
	}
	public void setStatuslist(ArrayList<Custom> statuslist) {
		this.statuslist = statuslist;
	}
	public ArrayList<Custom> getFriendsArraylist() {
		return FriendsArraylist;
	}
	public void setFriendsArraylist(ArrayList<Custom> friendsArraylist) {
		FriendsArraylist = friendsArraylist;
	}
	

    @Override
    public void onCreate() {
    	IGEFSharedPrefrence obj=new IGEFSharedPrefrence(getApplicationContext());
    	 Parse.initialize(this, "mi3qCGDTbjdgmWGIehsgBSo0uEZrlPO9mgF1vCF2", "huZuXAtIPUMVfKrQ4fhJsfZJbe8PvU8iB3StObVG");
    	 PushService.setDefaultPushCallback(this, IgefSocailNetwork.class);
        super.onCreate();
    }

   

}
