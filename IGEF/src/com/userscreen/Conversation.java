package com.userscreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.SocialNetwork.igef.R;

public class Conversation extends Fragment {

public Conversation() {
}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.userscreen, container, false);
		
		
		return rootView;

	}

}
