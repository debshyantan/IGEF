package com.userscreen;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.igef.R;



public class Status extends Fragment{
	ListView lv;
	ImageView profile_iv, status_iv;
	TextView name,status,timestamp;
	String d_status, d_name, d_roll, d_created;
	
	ArrayList<Custom> list;

MyAdapter adapter;
	
	
@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.userscreen, container,false);
		lv=(ListView)rootView.findViewById(R.id.list);
		
	    new AsyncTask<Void, Void, Void>() {
	    	ProgressDialog pd;
	    	String value;
	    	@Override
	    	protected void onPreExecute() {
	    		pd=new ProgressDialog(getActivity());
				 pd.setMessage("LoginNow");
				 pd.show();
				 list=new ArrayList<Custom>();
	    	};

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/selectstatus.php");
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		        nameValuePairs.add(new BasicNameValuePair("department", IGEFSharedPrefrence.getDEPARTMENT()));
		        nameValuePairs.add(new BasicNameValuePair("year", IGEFSharedPrefrence.getYEAR()));
		        try {
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
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
		        JSONArray result;
		        if (value != null) {
	                try {
	                    JSONObject jsonObj = new JSONObject(value);
	                     
	                    // Getting JSON Array node
	                    result = jsonObj.getJSONArray("result");
	 
	                    // looping through All Contacts
	                    for (int i = 0; i < result.length(); i++) {
	                        JSONObject c = result.getJSONObject(i);
	                         
	                        String status_id = c.getString("status_id");
	                        String status = c.getString("status");
	                        String full_name = c.getString("full_name");
	                        String roll_no=c.getString("roll_no");
	                        String department = c.getString("department");
	                        String year = c.getString("year");
	                        String section=c.getString("section");
	                        String created_at=c.getString("created_at");
	                        Custom d=new Custom();
	                        
	                        d.setStatus(status);
	                        d.setName(full_name);
	                        d.setTimestamp(created_at);
	                        
	                        list.add(d);
	 
	                       
	                    }
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }
	            } else {
	                Log.e("ServiceHandler", "Couldn't get any data from the url");
	            }
		        
		        
		        
		        
		        
		        
		        
				return null;
			}
			@Override
			
			protected void onPostExecute(Void result) {
				pd.dismiss();
				
				adapter=new MyAdapter();
				lv.setAdapter(adapter);
			};
	    	
		}.execute();
	    
		
		
		
		
	
	
	
		
		
		
		
		return rootView;
		
	}



class MyAdapter extends BaseAdapter
{

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView=inflater.inflate(R.layout.status, parent, false);
		profile_iv=(ImageView)convertView.findViewById(R.id.profilePic);
		name=(TextView)convertView.findViewById(R.id.name);
		timestamp=(TextView)convertView.findViewById(R.id.timestamp);
		status=(TextView)convertView.findViewById(R.id.txtStatusMsg);
		
		status_iv=(ImageView)convertView.findViewById(R.id.feedImage1);
		profile_iv.setImageResource(R.drawable.ic_launcher);
		status_iv.setImageResource(R.drawable.adminblock);
		name.setText(list.get(position).name);
		timestamp.setText(parseDate(position));
		status.setText(list.get(position).status);
		
		
		return convertView;
	}
	
	public String parseDate(int position)
		    throws ParseException
		{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",
		                                                    Locale.getDefault()); 
		    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		    long value = 0;
			try {
				value = dateFormat.parse(list.get(position).timestamp).getTime();
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
					Long.parseLong(String.valueOf(value)),
					System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
			
		    return timeAgo.toString();
		}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	
}

}
