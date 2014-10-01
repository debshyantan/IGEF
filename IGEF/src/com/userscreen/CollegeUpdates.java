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

import com.Chat.App;
import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.igef.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.userscreen.Status.MyAdapter;
import com.userscreen.UserTask.MyprofileTaskAdapter.viewholder;

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

public class CollegeUpdates extends Fragment {
	PullToRefreshListView listview;
	static ListView lv;
	public static ArrayList<Custom> clglist;
	ClgAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container,Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.userscreen, container,false);
		listview=(PullToRefreshListView)rootView.findViewById(R.id.list);
		lv=listview.getRefreshableView();
		clglist=((App)getActivity().getApplication()).getClglist();
		
		
		
		new AsyncTask<Void , Void, Void>(){
			String value;
			@Override
			protected void onPreExecute() {
				clglist=new ArrayList<Custom>();
				if(clglist!=null){
					clglist.clear();
				}
				
				
			};
			@Override
		
			protected Void doInBackground(
					Void... params) {
				
				

				// TODO Auto-generated method stub
				HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/getcollegeupdates.php");
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		        nameValuePairs.add(new BasicNameValuePair("department", IGEFSharedPrefrence.getDEPARTMENT()));
		        
		        
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
	                         
	                 
	                        String issuing_authority = c.getString("issuing_authority");
	                        String title = c.getString("title");
	                        String content=c.getString("content");
	                        String issuedate = c.getString("issuedate");
	                        Custom d=new Custom();
	                        
	                        d.setCnews(content);
	                        d.setTitle(title);
	                        d.setCname(issuing_authority);
	                        d.setCtime(issuedate);
	                        
	                        clglist.add(d);
	 
	                       
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

				adapter=new ClgAdapter();
				lv.setAdapter(adapter);
				listview.onRefreshComplete();
			};
			
		}.execute();
		listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

			

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
			
								new AsyncTask<Void , Void, Void>(){
									String value;
									@Override
									protected void onPreExecute() {
										
										if(clglist!=null){
											clglist.clear();
										}
										
										
									};
									@Override
								
									protected Void doInBackground(
											Void... params) {
										
										

										// TODO Auto-generated method stub
										HttpClient httpclient = new DefaultHttpClient();
									    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/getcollegeupdates.php");
								        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
								        nameValuePairs.add(new BasicNameValuePair("department", IGEFSharedPrefrence.getDEPARTMENT()));
								        
								        
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
							                         
							                 
							                        String issuing_authority = c.getString("issuing_authority");
							                        String title = c.getString("title");
							                        String content=c.getString("content");
							                        String issuedate = c.getString("issuedate");
							                        Custom d=new Custom();
							                        
							                        d.setCnews(content);
							                        d.setTitle(title);
							                        d.setCname(issuing_authority);
							                        d.setCtime(issuedate);
							                        
							                        clglist.add(d);
							 
							                       
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

										adapter=new ClgAdapter();
										lv.setAdapter(adapter);
										listview.onRefreshComplete();
									};
									
								}.execute();
							

				
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				listview.onRefreshComplete();
				
			}
		});
		
		
			
		return rootView;
	}
	

	class ClgAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return clglist.size();
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
			viewholder holder;
			if (convertView==null) {
				holder=new viewholder();
			LayoutInflater inflater = (LayoutInflater) getActivity()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.collegefeeds, parent, false);
			holder.name=(TextView)convertView.findViewById(R.id.authname);
			holder.heading=(TextView)convertView.findViewById(R.id.title);
			holder.time=(TextView)convertView.findViewById(R.id.issuetimestamp);
			holder.news=(TextView)convertView.findViewById(R.id.clgnews);
			holder.iv=(ImageView)convertView.findViewById(R.id.authPic);
			convertView.setTag(holder);
			}
			else {
			holder=(viewholder)convertView.getTag();
			}
			holder.name.setText(clglist.get(position).cname);
			holder.heading.setText(clglist.get(position).title);
			holder.news.setText(clglist.get(position).cnews);
			holder.time.setText(parseDate(position));
			return convertView;
		}
		public String parseDate(int position) throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss", Locale.getDefault());
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			long value = 0;
			try {
				value = dateFormat.parse(clglist.get(position).ctime)
						.getTime();
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
					Long.parseLong(String.valueOf(value)),
					System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);

			return timeAgo.toString();
		}
		class viewholder{
			TextView name,heading,news,time;
			ImageView iv;
		}
		
	}
}
