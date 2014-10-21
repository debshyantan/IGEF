package com.userscreen;

import java.io.File;
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
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.Toast;

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
	 Bitmap b;
	
	static MyTimelineAdapter adapter;
	
	GetMyStatus getstatus;
	static ProgressDialog pd;
	
	 private Integer[] mImageIds = {
	            R.drawable.clga,
	            R.drawable.clgb,
	            R.drawable.clgc,
	            R.drawable.clgd,
	            R.drawable.clge,
	            R.drawable.clgf,
	            R.drawable.clgg,
	            R.drawable.clgh
	    };
ImageView coverphoto;

Boolean isInternetPresent = false;
ConnectionDetector cd;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.mystatuslistview, container, false);
		listview=(PullToRefreshListView)rootView.findViewById(R.id.list);
		lv=listview.getRefreshableView();
		statuslist=((App)getActivity().getApplication()).getStatuslist();
		//connection checking
		cd = new ConnectionDetector(getActivity().getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		System.out.println("Network states:" + isInternetPresent);
	
			

		if (isInternetPresent) {
			//get my status
		getstatus=new GetMyStatus();
		getstatus.execute();
			}
		
		else {

		Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
				}

		pd=new ProgressDialog(getActivity());
		

				
				
				
		

		
		View myheader = View.inflate(getActivity(), R.layout.mytimelineheader, null);
		
		//setting the image of cover photo
		coverphoto=(ImageView)myheader.findViewById(R.id.coverphoto);
		
		if(IGEFSharedPrefrence.getCOVERPHOTO().equals("null")){
			coverphoto.setImageResource(R.drawable.indogloablclg);
		}
		else{
			int pos=Integer.parseInt(IGEFSharedPrefrence.getCOVERPHOTO());
			
			coverphoto.setImageResource(mImageIds[pos]);
		}
		
		
		//setting the name of student
		TextView myname=(TextView)myheader.findViewById(R.id.myname);
		TextView myrollno=(TextView)myheader.findViewById(R.id.myroolno);
		TextView myEmailid=(TextView)myheader.findViewById(R.id.myEmaillid);
		TextView MyContactno=(TextView)myheader.findViewById(R.id.myContno);
		
		// setting the image in image view from SDcard
		  final ImageView iv = (ImageView)myheader.findViewById(R.id.profilephoto);
		  
	      File root = android.os.Environment.getExternalStorageDirectory();
	      b=BitmapFactory.decodeFile(root.getAbsolutePath() + "/ProfilePhoto/MyprofilePhoto.jpg");

//		   b = BitmapFactory.decodeFile(root.getAbsolutePath() + "/ProfilePhoto/MyprofilePhoto.jpg", options);
		  iv.setImageBitmap(b);
		  iv.setBackgroundResource(R.drawable.img);
		
		myname.setText(IGEFSharedPrefrence.getFULL_NAME());
		myrollno.setText("Roll No : " + IGEFSharedPrefrence.getROLL_NO());
		MyContactno.setText(IGEFSharedPrefrence.getCONTACTNO());
		myEmailid.setText( IGEFSharedPrefrence.getEMAIL());
		
		
		
		listview.getRefreshableView().addHeaderView(myheader);
		
	
		return rootView;
	}
	
	



	public   class MyTimelineAdapter extends BaseAdapter {


		Bitmap  bb;
		private TypedArray statusphoto ;


		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = (LayoutInflater) getActivity()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.mytimeline, parent, false);
			
			statusphoto = getResources().obtainTypedArray(R.array.status_photos);

//			File root = android.os.Environment.getExternalStorageDirectory();
//		      bb=BitmapFactory.decodeFile(root.getAbsolutePath() + "/ProfilePhoto/MyprofilePhoto.jpg");

			
			profile_iv = (ImageView) convertView.findViewById(R.id.myyprofilePic);
			profile_iv.setImageBitmap(b);
			 
			
			name = (TextView) convertView.findViewById(R.id.myyname);
			timestamp = (TextView) convertView.findViewById(R.id.myytimestamp);
			status = (TextView) convertView.findViewById(R.id.myytxtStatusMsg);
			
			
			

			status_iv = (ImageView) convertView.findViewById(R.id.feedImage1);
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
								if (isInternetPresent) {
								
						DeleteMyStatusAsncTask deletetask=new DeleteMyStatusAsncTask(getActivity(),  statuslist.get(position).mystatus_id , statuslist, position, lv);
								deletetask.execute();
								}
								
								else {

							Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
							}
								
								
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
			
			Random r= new Random();
			status_iv.setImageResource(statusphoto.getResourceId(r.nextInt(8), 1));
			
//			status_iv.setImageResource(R.drawable.adminblock);
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
    		 pdd.setCancelable(false);
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
