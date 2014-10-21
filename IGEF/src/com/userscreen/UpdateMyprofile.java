package com.userscreen;


import Tool.ConnectionDetector;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.igef.R;
import com.SocialNewtwork.AsyncTask.UpdateProfileAsyncTask;

public class UpdateMyprofile extends Fragment {

	EditText newname, newemailid,newcontactno;
	Button updateprofile;
	
	Boolean isInternetPresent = false;
	ConnectionDetector cd;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.updateprofile, container,
				false);
		
		
		//connection checking
			cd = new ConnectionDetector(getActivity().getApplicationContext());
			isInternetPresent = cd.isConnectingToInternet();
			System.out.println("Network states:" + isInternetPresent);
			
			
			
		
		
		newname = (EditText) rootView.findViewById(R.id.newname);
		newname.setText(IGEFSharedPrefrence.getFULL_NAME());

		newemailid = (EditText) rootView.findViewById(R.id.newemailid);
		newemailid.setText(IGEFSharedPrefrence.getEMAIL());
		
		newcontactno = (EditText) rootView.findViewById(R.id.newcontactno);
		newcontactno.setText(IGEFSharedPrefrence.getCONTACTNO());

		updateprofile = (Button) rootView.findViewById(R.id.updateprofile);

		updateprofile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String newnam = newname.getText().toString();
				String newemail = newemailid.getText().toString();
				String newcontact = newcontactno.getText().toString();
				
				System.out.println("contact no at page" +newcontact);

				if (!newname.equals("") && !newemailid.equals("") &&  !newcontact.equals("") ) {
					
					
					if (isInternetPresent) {
					
					new UpdateProfileAsyncTask(newnam, newemail, newcontact, getActivity()).execute();
					}
					
					else {

								Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
							}

				}

				else {
				
					Toast.makeText(getActivity(), "Field can't remain Empty", Toast.LENGTH_LONG).show();


				}

			}
		});

		return rootView;
	}

}
