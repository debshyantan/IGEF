package com.userscreen;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
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
import android.content.res.TypedArray;
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
import com.squareup.picasso.Picasso;

public class Status extends Fragment {
	PullToRefreshListView listview;
	static ListView lv;
//	ImageView profile_iv, status_iv;
//	TextView name, status, timestamp;
	String d_status, d_name, d_roll, d_created;

	public static ArrayList<Custom> statuslist;
    static MyAdapter adapter;
    
    Boolean isInternetPresent = false;
	ConnectionDetector cd;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.userscreen, container,false);
		listview=(PullToRefreshListView)rootView.findViewById(R.id.list);
		lv=listview.getRefreshableView();
		statuslist=((App)getActivity().getApplication()).getStatuslist();
	
		//connection checking
		cd = new ConnectionDetector(getActivity().getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		System.out.println("Network states:" + isInternetPresent);
		
		
		
		listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

		

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				
				
				if (isInternetPresent) {
					try {
						
					
			
								new AsyncTask<Void , Void, Void>(){
									String value;
									@Override
									protected void onPreExecute() {
										if (isInternetPresent) {
										if(statuslist!=null){
											statuslist.clear();
										}
										}
										
										
									};
									@Override
								
									protected Void doInBackground(
											Void... params) {
										
										try {
											
										
										

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
							                        System.out.println(status);
							                        
							                        
							                        String full_name = c.getString("full_name");
							                        String roll_no=c.getString("roll_no");
							                        String department = c.getString("department");
							                        String year = c.getString("year");
							                        String section=c.getString("section");
							                        String created_at=c.getString("created_at");
							                        String statusprofilepicurl=c.getString("profilepicurl");
							                        Custom d=new Custom();
							                        
							                        d.setStatus(status);
							                        d.setName(full_name);
							                        d.setTimestamp(created_at);
							                        d.setStatusprofilepicurl(statusprofilepicurl);
							                        statuslist.add(d);
							 
							                       
							                    }
							                } catch (JSONException e) {
							                    e.printStackTrace();
							                }
							            } else {
							                Log.e("ServiceHandler", "Couldn't get any data from the url");
							            }
								        
								        
								        
										} catch (Exception e) {
											// TODO: handle exception
										}
								        
								
									
										
										return null;
									}
									@Override
									protected void onPostExecute(Void result) {

										adapter=new MyAdapter();
										lv.setAdapter(adapter);
										listview.onRefreshComplete();
									};
									
								}.execute();
								
					} catch (Exception e2) {

					listview.onRefreshComplete();
					
					}
				}
				
				else {

							Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
						}
							
				

				
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
			}
			
		});
		
		
		if (isInternetPresent) {
			
		
	    new AsyncTask<Void, Void, Void>() {
//	    	ProgressDialog pd;
	    	String value;
	    	@Override
	    	protected void onPreExecute() {
//	    		pd=new ProgressDialog(getActivity());
//				 pd.setMessage("LoginNow");
//				 pd.show();
	    		statuslist=new ArrayList<Custom>();
	    		
	    		if(statuslist!=null){
					statuslist.clear();
				}
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
	                        String statusprofilepicurl=c.getString("profilepicurl");
	                        
	                        Custom d=new Custom();
	                        
	                        d.setStatus(status);
	                        d.setName(full_name);
	                        d.setTimestamp(created_at);
	                        d.setStatusprofilepicurl(statusprofilepicurl);
	                        statuslist.add(d);
	 
	                       
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
//				pd.dismiss();
				
				adapter=new MyAdapter();
				lv.setAdapter(adapter);
			};
	    	
		}.execute();
	    
		
		
	}
		
		else {

					Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
				}
		
	
	
	
		
		
		
		
		return rootView;
		
	}
	


	class MyAdapter extends BaseAdapter {
		private TypedArray statusphoto ;
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			viewholder holder;
			if (convertView==null) {
				holder=new viewholder();
			
			LayoutInflater inflater = (LayoutInflater) getActivity()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.status, parent, false);
			statusphoto = getResources().obtainTypedArray(R.array.status_photos);

			
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.timestamp = (TextView) convertView.findViewById(R.id.timestamp);
			holder.status = (TextView) convertView.findViewById(R.id.txtStatusMsg);

			holder.status_iv = (ImageView) convertView.findViewById(R.id.feedImage1);
			
			
			
			
			holder.profile_iv = (ImageView) convertView.findViewById(R.id.profilePic);
			
			
			
			
			
			convertView.setTag(holder);
			System.out.println("student status--->"+ statuslist.get(position).getStatus());
			}
			else {
				holder=(viewholder)convertView.getTag();
				}
			String statuspicurl=Custom.getImageurl() + statuslist.get(position).getStatusprofilepicurl();
			
			if (statuspicurl.equals("http://shypal.com/IGEF/task_manager/uploadedimages/null")) {
				holder.profile_iv.setImageResource(R.drawable.ic_launcher);
				
				System.out.println("No Image of " +statuslist.get(position).name);
			}
			else {
				
				System.out.println("Loading the image of " +statuslist.get(position).name );
				
				System.out.println(statuspicurl);
				
				Picasso.with(getActivity())
		        .load(statuspicurl)
		        .placeholder(R.drawable.ic_launcher)
		        .resize(100	, 100)
		        .centerCrop()
		        .into(holder.profile_iv);
				
			}
			
			Random r= new Random();
			holder.status_iv.setImageResource(statusphoto.getResourceId(r.nextInt(8), 1));
//			status_iv.setImageResource(R.drawable.adminblock);
			holder.name.setText(statuslist.get(position).name);
			holder.timestamp.setText(parseDate(position));
			holder.status.setText(statuslist.get(position).status);
			return convertView;
		}
		
		class viewholder{
			TextView name, timestamp, status;
			ImageView status_iv,profile_iv;
		}

		public String parseDate(int position) throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss", Locale.getDefault());
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			long value = 0;
			try {
				value = dateFormat.parse(statuslist.get(position).timestamp)
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
			return statuslist.size();
		}

	}



	public static void showstatus() {
		
		System.out.println("------refreshing status started------");
		
		
		   new AsyncTask<Void, Void, Void>() {
//		    	ProgressDialog pd;
		    	String value;
		    	@Override
		    	protected void onPreExecute() {
//		    		pd=new ProgressDialog(getActivity());
//					 pd.setMessage("LoginNow");
//					 pd.show();
		    		statuslist=new ArrayList<Custom>();
		    		
		    		if(statuslist!=null){
						statuslist.clear();
					}
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
		                        String section=c.getString("section");
		                        String created_at=c.getString("created_at");
		                        String statusprofilepicurl=c.getString("profilepicurl");
		                        Custom d=new Custom();
		                        
		                        d.setStatus(status);
		                        d.setName(full_name);
		                        d.setTimestamp(created_at);
		                        d.setStatusprofilepicurl(statusprofilepicurl);
		                        statuslist.add(d);
		 
		                       
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
//					pd.dismiss();
					
				
					lv.setAdapter(adapter);
				};
		    	
			}.execute();		
	}

}
