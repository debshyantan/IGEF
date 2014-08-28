package com.userscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.SocialNetwork.igef.R;



public class root extends Fragment{
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
			
		View rootview=inflater.inflate(R.layout.rootuserscreen, container, false);
		
		getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new UserTask()).commit();
		
	
		
		return rootview;
		
	
	}

}
