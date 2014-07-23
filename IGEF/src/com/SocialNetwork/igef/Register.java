package com.SocialNetwork.igef;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

public class Register extends ActionBarActivity {
	Spinner branchSpinner,year;
	Button submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrationpage);
		branchSpinner=(Spinner)findViewById(R.id.branch);
		year=(Spinner)findViewById(R.id.year);
		submit=(Button)findViewById(R.id.submit);
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
System.out.println(""+String.valueOf(branchSpinner.getSelectedItem()));
System.out.println(""+String.valueOf(year.getSelectedItem()));
//System.out.println();
				
			}
		});		

	}

}
