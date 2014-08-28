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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.igef.R;


public class FreindsList extends Fragment {
ListView lv;
ImageView profile_iv;
TextView name, roll_no;
ArrayList<Custom> list;

MyFriendAdapter adapter;
public FreindsList() {
	
}
	@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View rootView = inflater.inflate(R.layout.userscreen, container, false);
	
	lv=(ListView)rootView.findViewById(R.id.list);
	new AsyncTask<Void, Void, Void>(){
		ProgressDialog pd;
		String Value_friend;
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
		    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/getallfriends.php");
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
				Value_friend=EntityUtils.toString(response.getEntity());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(Value_friend);
	        JSONArray result = null;
	        if(Value_friend!=null){
	        	JSONObject jsonObj = null;
				try {
					jsonObj = new JSONObject(Value_friend);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                // Getting JSON Array node
                try {
					result = jsonObj.getJSONArray("result");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                // looping through All Contacts
                for (int i = 0; i < result.length(); i++) {
                    JSONObject c;
					try {
						c = result.getJSONObject(i);
					
                    
						String id=c.getString("id");
					
                    String full_name= c.getString("full_name");
					
                    String roll_no=c.getString("roll_no");
                    Custom d=new Custom();
                    d.setFriendname(full_name);
                    d.setRoll_no(roll_no);
                    list.add(d);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
	        }}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			pd.dismiss();
			adapter=new MyFriendAdapter();
			lv.setAdapter(adapter);
		};
		
	}.execute();
	
	
	return rootView;
		
}
	
	class MyFriendAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			LayoutInflater inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.layout.friends, parent, false);
			profile_iv=(ImageView)convertView.findViewById(R.id.img);
			name=(TextView)convertView.findViewById(R.id.name);
			roll_no=(TextView)convertView.findViewById(R.id.roll);
			name.setText(list.get(position).friendname);
			roll_no.setText(list.get(position).roll_no);
			
			return convertView;
		}

		}
		

}
