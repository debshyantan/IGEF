package com.SocialNetwork.igef;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends ActionBarActivity {
	Spinner branchSpinner,year;
	Button submit;
	EditText name, roll, contact, email, password;
	RadioGroup rdg;
	RadioButton rb;
	String d_name, d_roll, d_dept, d_year, d_contact, d_gender, d_email, d_password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrationpage);
		rdg=(RadioGroup)findViewById(R.id.radioSex);
		int selectedID=rdg.getCheckedRadioButtonId();
		rb=(RadioButton)findViewById(selectedID);
		branchSpinner=(Spinner)findViewById(R.id.branch);
		year=(Spinner)findViewById(R.id.year);
		submit=(Button)findViewById(R.id.submit);
		name=(EditText)findViewById(R.id.enterstudentname);
		roll=(EditText)findViewById(R.id.enterrollno);
		contact=(EditText)findViewById(R.id.econtactno);
		email=(EditText)findViewById(R.id.eemailid);
		password=(EditText)findViewById(R.id.epassword);
		
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				d_name=name.getText().toString();
				d_roll=roll.getText().toString();
				d_gender=rb.getText().toString();
				d_contact=contact.getText().toString();
				d_email=email.getText().toString();
				d_password=password.getText().toString();
				d_dept=String.valueOf(branchSpinner.getSelectedItem());
				d_year=String.valueOf(year.getSelectedItem());
				
				Toast.makeText(Register.this, d_name + d_roll + d_contact + d_email, 1000).show();
				Log.i("name", d_name);
				System.out.println(d_name);
System.out.println(d_name + d_roll + d_dept + d_year);
System.out.println(d_contact + d_email + d_gender + d_password);
				
			}
		});		

	}

}
