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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Prefrence.IGEFSharedPrefrence;
import com.userscreen.UserScreen;

public class LoginStudent extends Fragment {
	EditText rollno1, password1;
	Button login;
	 String login_password,login_roll_no;
	 TextView incorrectloginnn;
	 int flag=0;

	 private static final String APP_ID = "13032";
	    private static final String AUTH_KEY = "rQHh7DVeAbrOPmn";
	    private static final String AUTH_SECRET = "5XXkbUK9pBg8L9c";
	    private static ProgressBar progressBar;
	
 public LoginStudent() {
	// TODO Auto-generated constructor stub
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	final View rootView = inflater.inflate(R.layout.loginpage, container,
			false);
	
		incorrectloginnn=(TextView)rootView.findViewById(R.id.incorrectlogin);
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
				String jsonStr,id11, full_name11,roll_no11, gender11, department11, year11, section11, contactno11, email11, devicetoken11, apiKey11,profilepicurl11, coverphoto11, status11,createdAt11;
				String value;
				
				@Override
				protected void onPreExecute() {
					
					 pd=new ProgressDialog(getActivity());
					 pd.setMessage("LoginNow");
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
						System.out.println(value);
						
						
						
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
									
								System.out.println("LOgin sucessfull Json parsing stared");
								
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
								profilepicurl11=jsonObj.getString("profilepicurl");
								coverphoto11=jsonObj.getString("coverphoto");
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
		                        System.out.println(profilepicurl11);
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
		                        IGEFSharedPrefrence.setPROFILEPICURL(profilepicurl11);
		                        IGEFSharedPrefrence.setCOVERPHOTO(coverphoto11);
		                        IGEFSharedPrefrence.setSTATUS(status11);
		                        IGEFSharedPrefrence.setCREATEDAT(createdAt11);
		                        IGEFSharedPrefrence.setPASSWORD(login_password);
		                        
 
		                        flag=1;
		                       
										
								

		                        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
//		                		QBSettings.getInstance().fastConfigInit(APP_ID, AUTH_KEY, AUTH_SECRET);
//		                        QBAuth.createSession(LoginStudent.this);
//		                      
								
								
								
								
								
								}
											
								else 
								{
									if(jsonObj.getString("message").equals("LoginNow failed. Incorrect credentials"))
									{
									flag=2;
									
									
									}
									else{
										flag=3;
									}
								}
								

							} catch (JSONException e) {
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
			     
					return null;
				}
				@Override
				protected void onPostExecute(Void result) {
					pd.dismiss();
					
					
					
					rollno1.setText(null);
					password1.setText(null);
					
					show(flag);
					
					
				}

				private void show(int flag) {
					
					if(flag==1){
						
						if (IGEFSharedPrefrence.getPROFILEPICURL().equals("")){
							
							System.out.println("no image in profilepic url --> moving to photoupload activity");
							
							Intent intt=new Intent(getActivity(), PhotoUpload.class);
								getActivity().startActivity(intt);
								getActivity().finish();
							
						}
						
						else if (IGEFSharedPrefrence.getCOVERPHOTO().equals("")) {
								System.out.println("no COver Photo Set --> moving to CoverPhotoChooser activity");
							
							Intent intt=new Intent(getActivity(), CoverPhotoChooser.class);
								getActivity().startActivity(intt);
								getActivity().finish();
							
						}
						
						else{						
						
						Toast.makeText(getActivity(), "LoginNow Successfull", Toast.LENGTH_SHORT).show();
						
						  Intent intt=new Intent(getActivity(), UserScreen.class);
						getActivity().startActivity(intt);
						getActivity().finish();
						}
					}
					
					if(flag==2){
						Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();

						
						
					
		
						incorrectloginnn.setText("Roll No. and Password do not match.!!");
						incorrectloginnn.setVisibility(View.VISIBLE);
							
							System.out.println("Roll No. and Password do not match.!!");
					}
					
					if(flag==3){
						Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();

						
						
						
		
						incorrectloginnn.setText("Login Failed, Required fields missing.!!");
						incorrectloginnn.setVisibility(View.VISIBLE);
							
							System.out.println("Missing credentials.!!");
					}
				};
				
			}.execute();
			
			
				
			}
		});
		
	
	
	return rootView;
}

//@Override
//public void onComplete(Result result) {
//	// TODO Auto-generated method stub
//	 progressBar.setVisibility(View.GONE);
//
//     if (result.isSuccess()) {
//         Intent intent = new Intent(getActivity(), UserScreen.class);
//         startActivity(intent);
//         getActivity().finish();
//     } else {
//         AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
//         dialog.setMessage("Error(s) occurred. Look into DDMS log for details, " +
//                 "please. Errors: " + result.getErrors()).create().show();
//     }
//	
//}
//
//@Override
//public void onComplete(Result result, Object context) {
//	// TODO Auto-generated method stub
//	
//}
}