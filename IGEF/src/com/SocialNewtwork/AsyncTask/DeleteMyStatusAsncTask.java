package com.SocialNewtwork.AsyncTask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.Prefrence.IGEFSharedPrefrence;
import com.userscreen.Custom;
import com.userscreen.MyTimeLine.MyTimelineAdapter;

public class DeleteMyStatusAsncTask extends AsyncTask<Void, Void, Void> {
	FragmentActivity activity;
	String mystatus_id;
	ProgressDialog pd;
	String value;
	JSONObject jsonobject;
	String error;
	ArrayList<Custom> statuslist;
	int position;
	ListView listview;
	MyTimelineAdapter timelineadapter;
	int flag;
	
	public DeleteMyStatusAsncTask(FragmentActivity activity, String mystatus_id, ArrayList<Custom> statuslist, int position, ListView lv, MyTimelineAdapter adapter) {
	this.activity=activity;
	this.mystatus_id=mystatus_id;
	this.statuslist=statuslist;
	this.position=position;
	this.listview=lv;
	this.timelineadapter=timelineadapter;
	
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		pd=new ProgressDialog(activity);		
		pd.setTitle("Deleting Your Status");
		pd.setMessage("Wait While the staus is Deleting");
		pd.show();
		
		
		
		
	}

	@Override
	protected Void doInBackground(Void... params) {
		
		
		HttpClient client=new DefaultHttpClient();
		HttpPost httpPost=new HttpPost("http://shypal.com/IGEF/task_manager/v1/deletemystatus");
		List<NameValuePair> nameValuePair= new ArrayList<NameValuePair>();
		nameValuePair.add(new BasicNameValuePair("roll_no", IGEFSharedPrefrence.getROLL_NO()));
		nameValuePair.add(new BasicNameValuePair("status_id", mystatus_id));
		HttpResponse response;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
			 response=client.execute(httpPost);
			
			 value=EntityUtils.toString(response.getEntity());
			 System.out.println(value);
			 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	try {
		 jsonobject= new JSONObject(value);
		error= jsonobject.getString("error");
		 
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if(error.equals("false")){
		flag=1;
		
		
	}
	else{
		Toast.makeText(activity, "Try Again Later", Toast.LENGTH_LONG).show();
	}
		
		
		
		
		
		
		
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		 Vibrator v = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
		 // Vibrate for 500 milliseconds
		 v.vibrate(200);
		pd.dismiss();
		
		if(flag==1){
			activity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("Earlier count" +statuslist.size());
					
					statuslist.remove(position);
//					timelineadapter.notifyDataSetChanged();
					System.out.println( "Later count" + statuslist.size());
//					MyTimeLine.MyTimelineAdapter.refreshmytimelist();
//					listview.invalidateViews();					
				}
			});
			
		}
		
		
	}
	
	
}
