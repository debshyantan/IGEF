//package com.SocialNewtwork.AsyncTask;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.ParseException;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.os.AsyncTask;
//import android.support.v4.app.FragmentActivity;
//import android.util.Log;
//import android.widget.ListView;
//
//import com.Prefrence.IGEFSharedPrefrence;
//import com.userscreen.Custom;
//import com.userscreen.Status.MyAdapter;
//
//public class GetstatusAsyncTask extends AsyncTask<Void, Void, Void>{
//
//	String value;
//	ListView lv;
//	ArrayList<Custom> statuslist;
//	FragmentActivity activity;
//	MyAdapter adapter;
//
//	
//	
//	public GetstatusAsyncTask(ListView lv, ArrayList<Custom> statuslist,
//			FragmentActivity activity, MyAdapter adapter) {
//		this.lv=lv;
//		this.statuslist=statuslist;
//		this.activity=activity;
//		this.adapter=adapter;
//		
//		
//		
//		// TODO Auto-generated constructor stub
//	}
//	@Override
//	protected void onPreExecute() {
//		if(statuslist!=null){
//			statuslist.clear();
//		}
//		
//	};
//	@Override
//
//	protected Void doInBackground(
//			Void... params) {
//		
//		
//
//		// TODO Auto-generated method stub
//		HttpClient httpclient = new DefaultHttpClient();
//	    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/selectstatus.php");
//        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//        nameValuePairs.add(new BasicNameValuePair("department", IGEFSharedPrefrence.getDEPARTMENT()));
//        nameValuePairs.add(new BasicNameValuePair("year", IGEFSharedPrefrence.getYEAR()));
//        
//        try {
//			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        HttpResponse response = null;
//        try {
//			response = httpclient.execute(httppost);
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        try {
//			value=EntityUtils.toString(response.getEntity());
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        System.out.println(value);
//        JSONArray result;
//        if (value != null) {
//            try {
//                JSONObject jsonObj = new JSONObject(value);
//                 
//                // Getting JSON Array node
//                result = jsonObj.getJSONArray("result");
//
//                // looping through All Contacts
//                for (int i = 0; i < result.length(); i++) {
//                    JSONObject c = result.getJSONObject(i);
//                     
//                    String status_id = c.getString("status_id");
//                    String status = c.getString("status");
//                    String full_name = c.getString("full_name");
//                    String roll_no=c.getString("roll_no");
//                    String department = c.getString("department");
//                    String year = c.getString("year");
//                    String section=c.getString("section");
//                    String created_at=c.getString("created_at");
//                    Custom d=new Custom();
//                    
//                    d.setStatus(status);
//                    d.setName(full_name);
//                    d.setTimestamp(created_at);
//                    
//                    statuslist.add(d);
//
//                   
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Log.e("ServiceHandler", "Couldn't get any data from the url");
//        }
//        
//        
//        
//        
//        
//
//	
//		
//		return null;
//	}
//	@Override
//	protected void onPostExecute(Void result) {
//		adapter=new MyAdapter(activity, statuslist);
//
//		
//		lv.setAdapter(adapter);
//	};
//	
//
//
//}
