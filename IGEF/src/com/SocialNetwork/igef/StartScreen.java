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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.Prefrence.IGEFSharedPrefrence;
import com.userscreen.UserScreen;

public class StartScreen extends Activity implements AnimationListener{
	//ImageView spinn;
	TextView blinking;
	Animation animRotate,animBlink;
	Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	

		setContentView(R.layout.startscreen);
		
		
		blinking=(TextView)findViewById(R.id.authentication);
		animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
		animBlink.setAnimationListener(this);
		blinking.setVisibility(View.VISIBLE);
		blinking.startAnimation(animBlink);
		
		System.out.println("On create of screen");
		
		
		if(!IGEFSharedPrefrence.getROLL_NO().equals("")){
			new AsyncTask<Void, Void, Void>(){
				int flag=0;
				String value;
				
				

				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					
					System.out.println("Login check started");
					
					HttpClient httpclient = new DefaultHttpClient();
				    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/v1/login");
			        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			        nameValuePairs.add(new BasicNameValuePair("roll_no", IGEFSharedPrefrence.getROLL_NO()));
			        nameValuePairs.add(new BasicNameValuePair("password", IGEFSharedPrefrence.getPASSWORD()));
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
						
						String jsonStr = value;
						System.out.println("JSON ARRIVED");
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
		                        flag=1;
		                        System.out.println("LOgin sucessfull");
		                       }											
								else 
								{
									if(jsonObj.getString("message").equals("LoginNow failed. Incorrect credentials"))
									{
									flag=2;	
									 System.out.println("WRONG ID PASSWORD");
									
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
					show(flag);				
				}

				private void show(int flag) {
					
					if(flag==1){
						System.out.println("Sucessfull login Moving to users screen");
						
						Intent i1=new Intent(getApplicationContext(), UserScreen.class);
						startActivity(i1);
						finish();
					}
					
					
					if(flag==2){	
						
						System.out.println("Wrong ID password Moveing to Login Screen");
						Intent i2=new Intent(getApplicationContext(), LoginStudent.class);
						startActivity(i2);
						finish();		
							
					}
					
					
				};
				
			}.execute();

		}
		
		else{
		System.out.println("Nonthing in the shared preference Move to start screen");
		Intent i4=new Intent(getApplicationContext(), IgefSocailNetwork.class);
		startActivity(i4);
		finish();
		}
		
		
	}


	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		if (animation == animRotate) {
		}
		
	}


	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}
}
