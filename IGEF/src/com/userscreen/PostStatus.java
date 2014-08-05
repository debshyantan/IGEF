package com.userscreen;

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
import android.widget.Toast;

import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.igef.R;


public class PostStatus extends Fragment {
	Button post, reset;
	EditText statusUpdate;
	public PostStatus() {
		
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.poststatus, container, false);
		
	
		statusUpdate=(EditText)rootView.findViewById(R.id.statusUpdate);

		post=(Button)rootView.findViewById(R.id.post);
		reset=(Button)rootView.findViewById(R.id.reset);
		
		//clear all the status updates
		reset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			statusUpdate.setText("");
				
			}
		});
		
		//Post the status updates
		
		post.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				final String statusUpdates=statusUpdate.getText().toString();
				
		//		IGEFSharedPrefrence obj = new IGEFSharedPrefrence(getActivity());
				System.out.println("shared preference fullnae" + IGEFSharedPrefrence.getFULL_NAME());
				System.out.println("shared preference rollno " + IGEFSharedPrefrence.getROLL_NO());
				
				
				new AsyncTask<Void, Void, Void>(){

					ProgressDialog pd;
					
					String value;
					@Override
					protected void onPreExecute() {
						
						 pd=new ProgressDialog(getActivity());
						 pd.setMessage("Posting Your Status....");
						 pd.show();
						
					};

					@Override
					protected Void doInBackground(Void... params) {
						// TODO Auto-generated method stub
						
						HttpClient httpclient = new DefaultHttpClient();
					    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/v1/status");
					    					
					    
				        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				        nameValuePairs.add(new BasicNameValuePair("status", statusUpdates));
				        nameValuePairs.add(new BasicNameValuePair("full_name", IGEFSharedPrefrence.getFULL_NAME()));
				        nameValuePairs.add(new BasicNameValuePair("roll_no", IGEFSharedPrefrence.getROLL_NO()));
				        nameValuePairs.add(new BasicNameValuePair("department", IGEFSharedPrefrence.getDEPARTMENT()));
				        nameValuePairs.add(new BasicNameValuePair("year", IGEFSharedPrefrence.getYEAR()));
				        nameValuePairs.add(new BasicNameValuePair("section", IGEFSharedPrefrence.getSECTION()));
				        
				        
				        
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
				        
				        System.out.println("Status response" + value);
						return null;
					}
					@Override
					protected void onPostExecute(Void result) {
						pd.dismiss();
						
						
						
					};
					
				}.execute();
				
				
				
				
				
				
				
			}
		});
		
		
		
		return rootView;

	}
}
