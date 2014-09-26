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

import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.igef.CoverPhotoChooser;
import com.userscreen.UserScreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

public class savecoverphotoAsyncTask extends AsyncTask<Void,Void, Void>{
	CoverPhotoChooser coverPhotoChooser;
	int coverphotopostion;
	ProgressDialog pd;
	String value;
	
	public savecoverphotoAsyncTask(CoverPhotoChooser coverPhotoChooser,
			int coverphotopostion) {
	this.coverPhotoChooser=coverPhotoChooser;
	this.coverphotopostion=coverphotopostion;
	}



	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pd=new ProgressDialog(coverPhotoChooser);
		
		pd.setTitle("Cover Photo");
		pd.setMessage("Saveing Your Cover Photo");
		pd.show();
		
		
	}
	
	
	
	@Override
	protected Void doInBackground(Void... params) {

		HttpClient client=new DefaultHttpClient();
		HttpPost httppost=new HttpPost("http://shypal.com/IGEF/task_manager/v1/updatecoverphoto");
		List<NameValuePair> namevaluepair=new ArrayList<NameValuePair>();
		namevaluepair.add(new BasicNameValuePair("roll_no", IGEFSharedPrefrence.getROLL_NO()));
		namevaluepair.add(new BasicNameValuePair("coverphoto", ""+coverphotopostion));
		
		try {
			httppost.setEntity(new UrlEncodedFormEntity(namevaluepair));
			HttpResponse response=null;
			response=client.execute(httppost);
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
		
		
		
		
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pd.dismiss();
		
		Intent i=new Intent(coverPhotoChooser	, UserScreen.class);
		coverPhotoChooser.startActivity(i);
		coverPhotoChooser.finish();
		
		
	}
	
	
}
