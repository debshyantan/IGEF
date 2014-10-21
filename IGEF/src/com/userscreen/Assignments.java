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

import Tool.ConnectionDetector;
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
import android.widget.Toast;

import com.Chat.App;
import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.igef.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


public class Assignments extends Fragment {
	PullToRefreshListView listview;
	static ListView lv;
	public static ArrayList<Custom> asgnlist;
	AsgnAdapter adapter;
	Boolean isInternetPresent = false;
	ConnectionDetector cd;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rootView = inflater.inflate(R.layout.userscreen, container,false);
		listview=(PullToRefreshListView)rootView.findViewById(R.id.list);
		lv=listview.getRefreshableView();
		asgnlist=((App)getActivity().getApplication()).getAsgnlist();
		
		
		
		//connection checking
			cd = new ConnectionDetector(getActivity().getApplicationContext());
			isInternetPresent = cd.isConnectingToInternet();
			System.out.println("Network states:" + isInternetPresent);
			
			
			
			
			if (isInternetPresent) {
		
		new AsyncTask<Void , Void, Void>(){
			String value;
			@Override
			protected void onPreExecute() {
				

				asgnlist=new ArrayList<Custom>();
				
				if(asgnlist!=null){

					
					asgnlist.clear();
				}
				
				
			};
			@Override
		
			protected Void doInBackground(
					Void... params) {
				
				

				// TODO Auto-generated method stub
				HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/getTeachersAssignment.php");
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
	                         
	                 
	                        String teacher_name = c.getString("teacher_name");
	                        String section = c.getString("section");
	                        if(section.equals("a")){
	                        	section="A";
	                        }
	                        else if(section.equals("b")){
	                        	section="B";
	                        }
	                        String details=c.getString("details");
	                        String lastdate = c.getString("lastdate");
	                        String created_at = c.getString("created_at");
	                        Custom d=new Custom();
	                        
	                        d.setAdetails(details);
	                        d.setSection(section);
	                        d.setTname(teacher_name);
	                        d.setLdate(lastdate);
	                        d.setAtime(created_at);
	                        
	                        asgnlist.add(d);
	 
	                       
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

				adapter=new AsgnAdapter();
				lv.setAdapter(adapter);
				listview.onRefreshComplete();
			};
			
		}.execute();
		
			}
			
			else {

						Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
					}
		
listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

			

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {

				if (isInternetPresent) {
			
								new AsyncTask<Void , Void, Void>(){
									String value;
									@Override
									protected void onPreExecute() {
										
										if(asgnlist!=null){
											asgnlist.clear();
										}
										
										
									};
									@Override
								
									protected Void doInBackground(
											Void... params) {
										
										

										// TODO Auto-generated method stub
										HttpClient httpclient = new DefaultHttpClient();
									    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/getTeachersAssignment.php");
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
							                         
							                 

							                        String teacher_name = c.getString("teacher_name");
							                        String section = c.getString("section");
							                        if(section.equals("a")){
							                        	section="A";
							                        }
							                        else if(section.equals("b")){
							                        	section="B";
							                        }
							                        
							                        String details=c.getString("details");
							                        String lastdate = c.getString("lastdate");
							                        String created_at = c.getString("created_at");
							                        Custom d=new Custom();
							                        
							                        d.setAdetails(details);
							                        d.setSection(section);
							                        d.setTname(teacher_name);
							                        d.setLdate(lastdate);
							                        d.setAtime(created_at);
							                        
							                        asgnlist.add(d);
							 
							                       
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

										adapter=new AsgnAdapter();
										lv.setAdapter(adapter);
										listview.onRefreshComplete();
									};
									
								}.execute();
				}
				
				else {

							Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
						}

				
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
	class AsgnAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return asgnlist.size();
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
			convertView = inflater.inflate(R.layout.assignments, parent, false);
			holder.tname=(TextView)convertView.findViewById(R.id.tname);
			holder.section=(TextView)convertView.findViewById(R.id.asection);
			holder.itime=(TextView)convertView.findViewById(R.id.issuetime);
			holder.detail=(TextView)convertView.findViewById(R.id.adetails);
			holder.ldate=(TextView)convertView.findViewById(R.id.last_date);
			holder.iv=(ImageView)convertView.findViewById(R.id.asgPic);
			convertView.setTag(holder);
			}
			else {
			holder=(viewholder)convertView.getTag();
			}
			holder.tname.setText(asgnlist.get(position).tname);
			String sec=asgnlist.get(position).section;
			if(sec.equals("a")){
            	sec="A";
            }
            else if(sec.equals("b")){
            	sec="B";
            }
			holder.section.setText("SECTION : " + sec);
			holder.detail.setText(asgnlist.get(position).adetails);
			holder.ldate.setText("LAST DATE : " + asgnlist.get(position).ldate);
			holder.itime.setText(parseDate(position));
			return convertView;
		}
		public String parseDate(int position) throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss", Locale.getDefault());
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			long value = 0;
			try {
				value = dateFormat.parse(asgnlist.get(position).atime)
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
			TextView tname,section,ldate,itime,detail;
			ImageView iv;
		}
		
	}

}
