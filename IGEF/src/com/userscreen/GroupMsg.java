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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.igef.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class GroupMsg extends Fragment {
	PullToRefreshListView listview;
	static ListView lv;
	EditText chatmsg;
	Button msgSnd;
	String msg;
	public static ArrayList<Custom> msglist;
	GroupAdapter adapter;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container,Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.conversation_list, container,false);
		listview=(PullToRefreshListView)rootView.findViewById(R.id.c_list);
		lv=listview.getRefreshableView();
		msglist=new ArrayList<Custom>();
		
		
		chatmsg=(EditText)rootView.findViewById(R.id.text_msg);
		
		
		msgSnd=(Button)rootView.findViewById(R.id.snd);
		
		
		msgSnd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				msg=chatmsg.getText().toString();
				
				new AsyncTask<Void, Void, Void>() {
					ProgressDialog pd;
					String value;
					@Override
					protected void onPreExecute() {
						pd=new ProgressDialog(getActivity());
						pd.setMessage("Sending message..");
						pd.show();
					};

					@Override
					protected Void doInBackground(Void... arg0) {
						// TODO Auto-generated method stub
						HttpClient httpclient = new DefaultHttpClient();
					    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/v1/groupmsg");
					    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					    nameValuePairs.add(new BasicNameValuePair("roll_no", IGEFSharedPrefrence.getROLL_NO()));
					    nameValuePairs.add(new BasicNameValuePair("msg", msg));
				        nameValuePairs.add(new BasicNameValuePair("department", IGEFSharedPrefrence.getDEPARTMENT()));
				        nameValuePairs.add(new BasicNameValuePair("year", IGEFSharedPrefrence.getYEAR()));
				        
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
						chatmsg.setText("");
					
					};
				}.execute();
				
			}
		});
		
		new AsyncTask<Void , Void, Void>(){
			String value;
			@Override
			protected void onPreExecute() {
				

				msglist=new ArrayList<Custom>();
				
				if(msglist!=null){

					
					msglist.clear();
				}
				
				
			};
			@Override
		
			protected Void doInBackground(
					Void... params) {
				
				

				// TODO Auto-generated method stub
				HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/getgroupmessage.php");
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
	                         
	                        String roll_no = c.getString("roll_no");
	                        String msg = c.getString("msg");
	                        
	                        
	                        String mcreated_at=c.getString("mcreated_at");
	                       
	                        Custom d=new Custom();
	                        
	                        d.setG_roll(roll_no);
	                        d.setG_msg(msg);
	                        d.setG_time(mcreated_at);
	                        
	                        msglist.add(d);
	 
	                       
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

				adapter=new GroupAdapter();
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
										
										if(msglist!=null){
											msglist.clear();
										}
										
										
									};
									@Override
								
									protected Void doInBackground(
											Void... params) {
										
										

										// TODO Auto-generated method stub
										HttpClient httpclient = new DefaultHttpClient();
									    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/getgroupmessage.php");
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
							                         
							                 

							                        String roll_no = c.getString("roll_no");
							                        String msg = c.getString("msg");
							                        
							                        
							                        String mcreated_at=c.getString("mcreated_at");
							                       
							                        Custom d=new Custom();
							                        
							                        d.setG_roll(roll_no);
							                        d.setG_msg(msg);
							                        d.setG_time(mcreated_at);
							                        
							                        msglist.add(d);
							 
							                       
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

										adapter=new GroupAdapter();
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
	
		
	class GroupAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return msglist.size();
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
			convertView = inflater.inflate(R.layout.conversation_item, parent, false);
			holder.groll=(TextView)convertView.findViewById(R.id.c_roll);
			holder.gmsg=(TextView)convertView.findViewById(R.id.c_msg);
			holder.gtime=(TextView)convertView.findViewById(R.id.c_time);
			
			convertView.setTag(holder);
			}
			else {
			holder=(viewholder)convertView.getTag();
			}
			holder.groll.setText(msglist.get(position).g_roll);
			holder.gmsg.setText(msglist.get(position).g_msg);
			holder.gtime.setText(parseDate(position));
			return convertView;
		}
		public String parseDate(int position) throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss", Locale.getDefault());
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			long value = 0;
			try {
				value = dateFormat.parse(msglist.get(position).g_time)
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
			TextView groll,gmsg,gtime;
		}
		
	}

}
