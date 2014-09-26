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

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Chat.App;
import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.igef.R;
import com.SocialNewtwork.AsyncTask.DeleteMyStatusAsncTask;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MyTimeLine extends Fragment{
	
	PullToRefreshListView listview;
	static ListView lv;
	ImageView profile_iv, status_iv, delete_iv;
	TextView name, status, timestamp;
	String d_status, d_name, d_roll, d_created;

	public static ArrayList<Custom> statuslist;
	
	static MyTimelineAdapter adapter;
	
	GetMyStatus getstatus;
	static ProgressDialog pd;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.mystatuslistview, container, false);
		listview=(PullToRefreshListView)rootView.findViewById(R.id.list);
		lv=listview.getRefreshableView();
		statuslist=((App)getActivity().getApplication()).getStatuslist();
			

		getstatus=new GetMyStatus();
		getstatus.execute();
		pd=new ProgressDialog(getActivity());
		
//		listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
//			
//	
//			
//			
//		
//
//			@Override
//			public void onPullDownToRefresh(
//					PullToRefreshBase<ListView> refreshView) {
//			
//								new AsyncTask<Void , Void, Void>(){
//									String value;
//									@Override
//									protected void onPreExecute() {
//										if(statuslist!=null){
//											statuslist.clear();
//										}
//										
//										
//									};
//									@Override
//								
//									protected Void doInBackground(
//											Void... params) {
//										
//										
//
//										// TODO Auto-generated method stub
//										HttpClient httpclient = new DefaultHttpClient();
//									    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/selectstatus.php");
//								        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//								        nameValuePairs.add(new BasicNameValuePair("department", IGEFSharedPrefrence.getDEPARTMENT()));
//								        nameValuePairs.add(new BasicNameValuePair("year", IGEFSharedPrefrence.getYEAR()));
//								        
//								        try {
//											httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//										} catch (UnsupportedEncodingException e) {
//											// TODO Auto-generated catch block
//											e.printStackTrace();
//										}
//								        
//								        HttpResponse response = null;
//								        try {
//											response = httpclient.execute(httppost);
//										} catch (ClientProtocolException e) {
//											// TODO Auto-generated catch block
//											e.printStackTrace();
//										} catch (IOException e) {
//											// TODO Auto-generated catch block
//											e.printStackTrace();
//										}
//								        try {
//											value=EntityUtils.toString(response.getEntity());
//										} catch (ParseException e) {
//											// TODO Auto-generated catch block
//											e.printStackTrace();
//										} catch (IOException e) {
//											// TODO Auto-generated catch block
//											e.printStackTrace();
//										}
//								        
//								        System.out.println(value);
//								        JSONArray result;
//								        if (value != null) {
//							                try {
//							                    JSONObject jsonObj = new JSONObject(value);
//							                     
//							                    // Getting JSON Array node
//							                    result = jsonObj.getJSONArray("result");
//							 
//							                    // looping through All Contacts
//							                    for (int i = 0; i < result.length(); i++) {
//							                        JSONObject c = result.getJSONObject(i);
//							                         
//							                        String my_status_id = c.getString("status_id");
//							                        String my_status = c.getString("status");
//							                        String my_full_name = c.getString("full_name");
//							                        String my_roll_no=c.getString("roll_no");
//							                        String my_department = c.getString("department");
//							                        String my_year = c.getString("year");
//							                        String my_section=c.getString("section");
//							                        String my_created_at=c.getString("created_at");
//							                        Custom d=new Custom();
//							                       
//							                        
//							                        d.setMystatus(my_status);
//							                        d.setMyname(my_full_name);
//							                        d.setMytimestamp(my_created_at);
//							                        d.setMystatus_id(my_status_id);
//							                        
//							                        statuslist.add(d);
//							 
//							                       
//							                    }
//							                } catch (JSONException e) {
//							                    e.printStackTrace();
//							                }
//							            } else {
//							                Log.e("ServiceHandler", "Couldn't get any data from the url");
//							            }
//								        
//								        
//								        
//								        
//								        
//								
//									
//										
//										return null;
//									}
//									@Override
//									protected void onPostExecute(Void result) {
//
//										adapter=new MyTimelineAdapter(getActivity(), statuslist);
//										lv.setAdapter(adapter);
//										listview.onRefreshComplete();
//									};
//									
//								}.execute();
//							
//				
//
//				
//			}
//
//			@Override
//			public void onPullUpToRefresh(
//					PullToRefreshBase<ListView> refreshView) {
//				// TODO Auto-generated method stub
//			}
//			
//		});
		
	
        
        
        
        
        
		
		
		
		
		return rootView;
	}
	
	
	public   class MyTimelineAdapter extends BaseAdapter {
//		FragmentActivity activity;
//		ArrayList<Custom> statuslist;
//
//		public MyTimelineAdapter(FragmentActivity activity,
//				ArrayList<Custom> statuslist) {
//				this.activity=activity;
//				this.statuslist=statuslist;
//		}

		

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = (LayoutInflater) getActivity()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.mytimeline, parent, false);
			profile_iv = (ImageView) convertView.findViewById(R.id.profilePic);
			name = (TextView) convertView.findViewById(R.id.name);
			timestamp = (TextView) convertView.findViewById(R.id.timestamp);
			status = (TextView) convertView.findViewById(R.id.txtStatusMsg);
			

			status_iv = (ImageView) convertView.findViewById(R.id.feedImage1);
			profile_iv.setImageResource(R.drawable.ic_launcher);
			delete_iv=(ImageView)convertView.findViewById(R.id.delete);
			
			delete_iv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
					 Vibrator v = (Vibrator)getActivity().getSystemService(Context.VIBRATOR_SERVICE);
					 // Vibrate for 500 milliseconds
					 v.vibrate(150);

					 
					 
					 
					 AlertDialog.Builder adialog=new AlertDialog.Builder(getActivity());
						adialog.setTitle("Delete Status");
						adialog.setMessage("Are You Sure to Delete the Status?");
						adialog.setIcon(R.drawable.ic_launcher);
						
						adialog.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								
						DeleteMyStatusAsncTask deletetask=new DeleteMyStatusAsncTask(getActivity(),  statuslist.get(position).mystatus_id , statuslist, position, lv);
								deletetask.execute();
								
								
							}
						  })
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								
								dialog.cancel();
							}
						});
						
						AlertDialog alertDialog = adialog.create();
						 
						// show it
						alertDialog.show();
						
					
					
					
				}
			});
			
			
			
			status_iv.setImageResource(R.drawable.adminblock);
			name.setText(IGEFSharedPrefrence.getFULL_NAME());
			timestamp.setText(parseDate(position));
			status.setText(statuslist.get(position).mystatus);

			return convertView;
		}

		public String parseDate(int position) throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss", Locale.getDefault());
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			long value = 0;
			try {
				value = dateFormat.parse(statuslist.get(position).mytimestamp)
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
	

	public class GetMyStatus extends AsyncTask<Void, Void, Void>{
		

    	String value;
//    	FragmentActivity activity;
//    	ArrayList<Custom> statuslist;
//    	MyTimelineAdapter adapter;
//    	ListView lv;

    	ProgressDialog pdd;

    	@Override
    	protected void onPreExecute() {
    		pdd=new ProgressDialog(getActivity());
    		 pdd.setMessage(" Retrieving Your Status ");
    		 pdd.show();
    		statuslist=new ArrayList<Custom>();
    	};

    	@Override
    	protected Void doInBackground(Void... params) {
    		// TODO Auto-generated method stub
    		HttpClient httpclient = new DefaultHttpClient();
    	    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/mystatusupdates.php");
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("roll_no", IGEFSharedPrefrence.getROLL_NO()));
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
                         
                        String my_status_id = c.getString("status_id");
                        String my_status = c.getString("status");
                        String my_full_name = c.getString("full_name");
                        String my_roll_no=c.getString("roll_no");
                        String my_department = c.getString("department");
                        String my_year = c.getString("year");
                        String my_section=c.getString("section");
                        String my_created_at=c.getString("created_at");
                        Custom d=new Custom();
                        
                        
                        d.setMystatus(my_status);
                        d.setMyname(my_full_name);
                        d.setMytimestamp(my_created_at);
                        d.setMystatus_id(my_status_id);
                        
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
    		pdd.dismiss();
    		

    				adapter=new MyTimelineAdapter();
    				lv.setAdapter(adapter);

    	};
    	

		
	}


	public static void refreshMyStatus() {

		new AsyncTask<Void, Void, Void>(){

			

	    	
	    	String value;
//	    	FragmentActivity activity;
//	    	ArrayList<Custom> statuslist;
//	    	MyTimelineAdapter adapter;
//	    	ListView lv;

	    	

	    	@Override
	    	protected void onPreExecute() {
	    		
	    		 pd.setMessage("Refreshing Your Status");
	    		 pd.show();
	    		statuslist=new ArrayList<Custom>();
	    	};

	    	@Override
	    	protected Void doInBackground(Void... params) {
	    		// TODO Auto-generated method stub
	    		HttpClient httpclient = new DefaultHttpClient();
	    	    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/mystatusupdates.php");
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	            nameValuePairs.add(new BasicNameValuePair("roll_no", IGEFSharedPrefrence.getROLL_NO()));
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
	            	statuslist.clear();
	                try {
	                    JSONObject jsonObj = new JSONObject(value);
	                     
	                    // Getting JSON Array node
	                    result = jsonObj.getJSONArray("result");

	                    // looping through All Contacts
	                    for (int i = 0; i < result.length(); i++) {
	                        JSONObject c = result.getJSONObject(i);
	                         
	                        String my_status_id = c.getString("status_id");
	                        String my_status = c.getString("status");
	                        String my_full_name = c.getString("full_name");
	                        String my_roll_no=c.getString("roll_no");
	                        String my_department = c.getString("department");
	                        String my_year = c.getString("year");
	                        String my_section=c.getString("section");
	                        String my_created_at=c.getString("created_at");
	                        Custom d=new Custom();
	                        
	                        
	                        d.setMystatus(my_status);
	                        d.setMyname(my_full_name);
	                        d.setMytimestamp(my_created_at);
	                        d.setMystatus_id(my_status_id);
	                        
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
	    		pd.dismiss();
	    		

	    				lv.setAdapter(adapter);

	    	};
	    	

			
		
		}.execute();
		
		
	}
}
