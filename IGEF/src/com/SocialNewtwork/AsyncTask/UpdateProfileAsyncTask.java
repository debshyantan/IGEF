package com.SocialNewtwork.AsyncTask;

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

import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.igef.IgefSocailNetwork;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

public class UpdateProfileAsyncTask extends AsyncTask<Void, Void, Void> {
	ProgressDialog pd;
	String newnam;
	String newemail;
	String roll_no;
	String value;
	String contactno;
	FragmentActivity activity;
	
	public UpdateProfileAsyncTask(String newnam, String newemail
			, String contactno, FragmentActivity activity) {
		this.newnam = newnam;
		this.newemail = newemail;
		this.activity = activity;
		
		this.contactno = contactno;
	}

	public UpdateProfileAsyncTask() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pd = new ProgressDialog(activity);
		pd.setTitle("Updating");
		pd.setMessage("Updating Your Profile...");
		pd.setCancelable(false);
		pd.show();

	}

	@Override
	protected Void doInBackground(Void... params) {
		
		HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/v1/updateuserdetails");
  
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("full_name", newnam));
        
        System.out.println(contactno);
        nameValuePairs.add(new BasicNameValuePair("contactno",contactno));
        nameValuePairs.add(new BasicNameValuePair("email",newemail));
        nameValuePairs.add(new BasicNameValuePair("roll_no",IGEFSharedPrefrence.getROLL_NO()));
        
       
       
        try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Execute HTTP Post Request
        HttpResponse response = null;
		try {
			
			System.out.println("http post executed");
		
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
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pd.dismiss();
		
	

	}

}
