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

import com.SocialNetwork.igef.R;







import android.R.string;
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
					    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/v1/tasks");
					    					
					    httppost.addHeader("Authorization", "a25a361f3f78fec6d48b4baeb848c766");
					    
//					    setHeader();
				        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				        nameValuePairs.add(new BasicNameValuePair("task", statusUpdates));
				        
				        
				        
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
						
						Toast.makeText(getActivity(), "Status Posted", Toast.LENGTH_LONG).show();
						
						Intent intt=new Intent(getActivity(), UserScreen.class);
						getActivity().startActivity(intt);
						statusUpdate.setText("");
						
					};
					
				}.execute();
				
				
				
				
				
				
				
			}
		});
		
		
		
		return rootView;

	}
}
