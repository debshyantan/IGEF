package com.userscreen;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.CustomFonts.MyProfileBoldFOnt;
import com.SocialNetwork.CustomFonts.MyProfileFont;
import com.SocialNetwork.igef.R;

public class ViewMyProfile extends Fragment {
	
	MyProfileBoldFOnt myname;
	MyProfileFont myrollno,myYear, myDepartment,myScetion, myEmailID;
	ImageView updateprofile;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.viewmyprofile, container, false);
		
		myname=(MyProfileBoldFOnt)rootView.findViewById(R.id.myname);
		myname.setText(IGEFSharedPrefrence.getFULL_NAME());
		
		myrollno=(MyProfileFont)rootView.findViewById(R.id.myrollno);
		myrollno.setText(IGEFSharedPrefrence.getROLL_NO());
		
		myYear=(MyProfileFont)rootView.findViewById(R.id.myYear);
		myYear.setText(IGEFSharedPrefrence.getYEAR());
		
		myDepartment=(MyProfileFont)rootView.findViewById(R.id.myDepartment);
		myDepartment.setText(IGEFSharedPrefrence.getDEPARTMENT());
		
		myScetion=(MyProfileFont)rootView.findViewById(R.id.myScetion);
		myScetion.setText(IGEFSharedPrefrence.getSECTION());
		
		myEmailID=(MyProfileFont)rootView.findViewById(R.id.myEmailID);
		myEmailID.setText(IGEFSharedPrefrence.getEMAIL());
		
		
		updateprofile=(ImageView)rootView.findViewById(R.id.updateprofile);
		updateprofile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new UpdateMyprofile()).addToBackStack(null).commit();
				
			}
		});
		
		
		
		
		
		
		return rootView;
	}

}
