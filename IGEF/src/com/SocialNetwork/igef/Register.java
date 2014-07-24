package com.SocialNetwork.igef;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.gcm.MainActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
	RadioGroup rdg_g,rdg_s;
	RadioButton rb1,rb2;
	String d_name, d_roll, d_dept, d_year, d_contact, d_gender, d_email, d_password, d_section;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrationpage);
		rdg_g=(RadioGroup)findViewById(R.id.radioSex);
		int selectedID=rdg_g.getCheckedRadioButtonId();
		rb1=(RadioButton)findViewById(selectedID);
		rdg_s=(RadioGroup)findViewById(R.id.radioSection);
		int selectID=rdg_s.getCheckedRadioButtonId();
		rb2=(RadioButton)findViewById(selectID);
		branchSpinner=(Spinner)findViewById(R.id.branch);
		year=(Spinner)findViewById(R.id.year);
		submit=(Button)findViewById(R.id.submit);
		name=(EditText)findViewById(R.id.enterstudentname);
		roll=(EditText)findViewById(R.id.enterrollno);
		contact=(EditText)findViewById(R.id.econtactno);
		email=(EditText)findViewById(R.id.eemailid);
		password=(EditText)findViewById(R.id.epassword);
		
		//new commit
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				d_name=name.getText().toString();
				d_roll=roll.getText().toString();
				d_gender=rb1.getText().toString();
				d_contact=contact.getText().toString();
				d_email=email.getText().toString();
				d_password=password.getText().toString();
				d_dept=String.valueOf(branchSpinner.getSelectedItem());
				d_year=String.valueOf(year.getSelectedItem());
				d_section=rb2.getText().toString();
System.out.println(d_name + d_roll + d_dept + d_section + d_year + d_contact + d_email + d_gender + d_password);

				new AsyncTask<Void, Void, Void>(){
					
					ProgressDialog pd;
					
					String value;
					@Override
					protected void onPreExecute() {
						
						 pd=new ProgressDialog(Register.this);
						 pd.setMessage("Requesting Server..");
						 pd.show();
						
					};
					@Override
					protected Void doInBackground(Void... params) {
						// TODO Auto-generated method stub
						HttpClient httpclient = new DefaultHttpClient();
					    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/v1/register");
					    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				        nameValuePairs.add(new BasicNameValuePair("full_name",d_name));
				        nameValuePairs.add(new BasicNameValuePair("roll_no", d_roll));
				        nameValuePairs.add(new BasicNameValuePair("gender", d_gender));
				        nameValuePairs.add(new BasicNameValuePair("department", d_dept));
				        nameValuePairs.add(new BasicNameValuePair("year",d_year));
				        nameValuePairs.add(new BasicNameValuePair("section", d_section));
				        nameValuePairs.add(new BasicNameValuePair("contactno",d_contact));
				        nameValuePairs.add(new BasicNameValuePair("email",d_email));
				        nameValuePairs.add(new BasicNameValuePair("password",d_password));
				       
				        try {
							httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

				        // Execute HTTP Post Request
				        HttpResponse response = null;
						try {
							response = httpclient.execute(httppost);
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        
				      try {
						value=EntityUtils.toString(response.getEntity());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				      System.out.println(value);
						return null;
					}
					
					@Override
					protected void onPostExecute(Void result) {
						pd.dismiss();
						
						Intent in=new Intent(Register.this,MainActivity.class);
						startActivity(in);
						System.out.println(""+value);
					};
					
				}.execute();
			}
		});		

	}

}
