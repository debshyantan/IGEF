
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
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.Prefrence.IGEFSharedPrefrence;
import com.userscreen.UserScreen;

public class GettingStarted extends ActionBarActivity implements
		ActionBar.TabListener {
	static ActionBar act;
	static FragmentManager fm;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_igef_socail_network);

		fm = getSupportFragmentManager();

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new Register()).commit();
		}
		act = getSupportActionBar();
		act.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		act.addTab(act.newTab().setText("REGISTER NOW").setTabListener(this));
		act.addTab(act.newTab().setText("LOGIN").setTabListener(this));
		act.setDisplayHomeAsUpEnabled(true);

	}

	 @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      // Respond to the action bar's Up/Home button
	      case android.R.id.home:
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    }
	 
	    return super.onOptionsItemSelected(item);
	  }
	

	// LOGIN FRAGMENT
	public static class PlaceholderFragment1 extends Fragment {
		EditText rollno1, password1;
			Button login;
			 String login_password,login_roll_no;
			 TextView incorrectloginnn;

		public PlaceholderFragment1() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			final View rootView = inflater.inflate(R.layout.loginpage, container,
					false);
			

			rollno1=(EditText)rootView.findViewById(R.id.loginrollno);
				password1=(EditText)rootView.findViewById(R.id.loginpassword);
				login=(Button)rootView.findViewById(R.id.login);
				login.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
					login_roll_no=	rollno1.getText().toString();
					 login_password= password1.getText().toString();
					new AsyncTask<Void, Void, Void>(){

						ProgressDialog pd;
						String jsonStr,id11, full_name11,roll_no11, gender11, department11, year11, section11, contactno11, email11, devicetoken11, apiKey11, status11,createdAt11;
						String value;
						
						@Override
						protected void onPreExecute() {
							
							 pd=new ProgressDialog(getActivity());
							 pd.setMessage("Login");
							 pd.show();
							
						};

						@Override
						protected Void doInBackground(Void... params) {
							// TODO Auto-generated method stub
							
							HttpClient httpclient = new DefaultHttpClient();
						    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/v1/login");
					        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					        nameValuePairs.add(new BasicNameValuePair("roll_no", login_roll_no));
					        nameValuePairs.add(new BasicNameValuePair("password", login_password));
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
								
								jsonStr=value;
								
								 if (jsonStr != null) {
									 JSONObject jsonObj = null;
									try {
										jsonObj = new JSONObject(jsonStr);
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									 
									try {
										if(jsonObj.getString("error").equals("false"))
										{
										
										id11 = jsonObj.getString("id");
										  full_name11 = jsonObj.getString("full_name");										
										roll_no11=jsonObj.getString("roll_no");
										gender11 = jsonObj.getString("gender");
										department11 = jsonObj.getString("department");
										year11 = jsonObj.getString("year");
										section11 = jsonObj.getString("section");
										contactno11 = jsonObj.getString("contactno");
										email11 = jsonObj.getString("email");
										devicetoken11 = jsonObj.getString("devicetoken");
										apiKey11 = jsonObj.getString("apiKey");
										status11 = jsonObj.getString("status");
										createdAt11 = jsonObj.getString("createdAt");
										
										System.out.println(id11);
										System.out.println(full_name11);
				                        System.out.println(roll_no11);
				                        System.out.println(gender11);
				                        System.out.println(department11);
				                        System.out.println(year11);
				                        System.out.println(section11);
				                        System.out.println(contactno11);
				                        System.out.println(email11);
				                        System.out.println(devicetoken11);
				                        System.out.println(apiKey11);
				                        System.out.println(status11);
				                        System.out.println(createdAt11);
				                        
				                        // storing in shared preffrence
				                        
				                        final IGEFSharedPrefrence obj = new IGEFSharedPrefrence(getActivity());
				                        
				                        IGEFSharedPrefrence.setID(id11);
				                        IGEFSharedPrefrence.setFULL_NAME(full_name11);				                       
				                        IGEFSharedPrefrence.setROLL_NO(login_roll_no);
				                        IGEFSharedPrefrence.setGENDER(gender11);
				                        IGEFSharedPrefrence.setDEPARTMENT(department11);
				                        IGEFSharedPrefrence.setYEAR(year11);
				                        IGEFSharedPrefrence.setSECTION(section11);
				                        IGEFSharedPrefrence.setCONTACTNO(contactno11);
				                        IGEFSharedPrefrence.setEMAIL(email11);
				                        IGEFSharedPrefrence.setDEVICETOKEN(devicetoken11);
				                        IGEFSharedPrefrence.setAPI_KEY(apiKey11);
				                        IGEFSharedPrefrence.setSTATUS(status11);
				                        IGEFSharedPrefrence.setCREATEDAT(createdAt11);
				                        IGEFSharedPrefrence.setPASSWORD(login_password);
				                        
				                  
				                       
												
										
//										Toast.makeText(getActivity(),"Roll_no: " + IGEFSharedPrefrence.getROLL_NO()+ " Full Name : " + IGEFSharedPrefrence.getFULL_NAME(),Toast.LENGTH_SHORT).show();

				                        
				                        
				                        
				                        Intent intt=new Intent(getActivity(), UserScreen.class);
										getActivity().startActivity(intt);
										
										
										
										
										
										}
													
										else 
										{
											if(jsonObj.getString("message").equals("Login failed. Incorrect credentials"))
											{
											
//											incorrectloginnn=(TextView)rootView.findViewById(R.id.incorrectlogin);
//											
//											incorrectloginnn.setText("galat roll no hai chore");
//											incorrectlogin.setVisibility(View.VISIBLE);
//												
											//	System.out.println("you ENtered to the Login Failed Loop");
											
											}
										}
										

									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									}
								
								
								
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
							
							
							
							rollno1.setText(null);
							password1.setText(null);
							
							

							
							
							
							
							
							
						};
						
					}.execute();
					
						
					}
				});
				
			
			
			return rootView;
		}
	}



	

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		if (arg0.getPosition() == 0) {

			fm.beginTransaction()
					.replace(R.id.container, new Register())
					.commit();
		}

		else if (arg0.getPosition() == 1) {
			fm.beginTransaction()
					.replace(R.id.container, new Login())
					.commit();
		}

	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	public static void replace(FragmentActivity fragmentActivity, int i) {
		fragmentActivity.getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.container, new Register())
		.commit();
		
			act.setSelectedNavigationItem(1);
		
	}

}

