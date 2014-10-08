package com.SocialNewtwork.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.Prefrence.IGEFSharedPrefrence;
import com.userscreen.UserScreen;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class ProfilePhotoAsyncTask extends AsyncTask<Void, Void, Void>{
	String url11=IGEFSharedPrefrence.getPHOTOURl()+IGEFSharedPrefrence.getPROFILEPICURL();

//	FragmentActivity activity;

	UserScreen userScreen;
	
public ProfilePhotoAsyncTask(UserScreen userScreen) {
		// TODO Auto-generated constructor stub
	
	this.userScreen=userScreen;
	
	}

@Override
protected void onPreExecute() {
	// TODO Auto-generated method stub
	super.onPreExecute();
	File root = android.os.Environment.getExternalStorageDirectory();               

	
	 File dir = new File (root.getAbsolutePath() + "/ProfilePhoto");
     if(dir.exists()==true) {
    	 DeleteRecursive(dir);
     }
}

	private void DeleteRecursive(File dir) {
		Log.d("DeleteRecursive", "DELETEPREVIOUS TOP" + dir.getPath());
	    if (dir.isDirectory())
	    {
	        String[] children = dir.list();
	        for (int i = 0; i < children.length; i++)
	        {
	            File temp = new File(dir, children[i]);
	            if (temp.isDirectory())
	            {
	                Log.d("DeleteRecursive", "Recursive Call" + temp.getPath());
	                DeleteRecursive(temp);
	            }
	            else
	            {
	                Log.d("DeleteRecursive", "Delete File" + temp.getPath());
	                boolean b = temp.delete();
	                if (b == false)
	                {
	                    Log.d("DeleteRecursive", "DELETE FAIL");
	                }
	            }
	        }

	    }
	    dir.delete();
	
}

	@Override
	protected Void doInBackground(Void... params) {
		
		
		try {
			
	        File root = android.os.Environment.getExternalStorageDirectory();               

	        File dir = new File (root.getAbsolutePath() + "/ProfilePhoto");
	        if(dir.exists()==false) {
	             dir.mkdirs();
	        }
	        
	        System.out.println(url11);
	        URL url = new URL(""+url11);
	        System.out.println(""+url);
	        File file = new File(dir, "MyprofilePhoto.jpg");

	        long startTime = System.currentTimeMillis();
	        Log.d("DownloadManager", "download begining");
	        Log.d("DownloadManager", "download url:" + url);
	        Log.d("DownloadManager", "downloaded file name:" + "MyprofilePhoto.jpg");

	        /* Open a connection to that URL. */
	        URLConnection ucon = url.openConnection();
	      int filelength=  ucon.getContentLength();
	        /*
	         * Define InputStreams to read from the URLConnection.
	         */
	        InputStream is = ucon.getInputStream();
	       
	        FileOutputStream fos = new FileOutputStream(file);
	        /*
	         * Read bytes to the Buffer until there is nothing more to read(-1).
	         */
	        byte data[] = new byte[1024];
	        long total = 0;
	        int count;
	        while ((count = is.read(data)) != -1) {
	            total += count ;
	            // publishing the progress....
	            fos.write(data, 0, count);
	        }

	        /* Convert the Bytes read to a String. */
	       
	       
	        fos.flush();
	        fos.close();
	        Log.d("DownloadManager", "download ready in" + ((System.currentTimeMillis() - startTime) / 1000) + " sec");

	        publish();
	} catch (IOException e) {
	    Log.d("DownloadManager", "Error: " + e);
	}
		
		return null;
	}
	

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		publish();
	}
	
	
	
		private void publish() {
			// TODO Auto-generated method stub
//			Intent i=new Intent();
//			i.setAction("filecreation");
//			
//			i.addCategory(Intent.CATEGORY_DEFAULT);
//			i.putExtra("photodownload", "done");
//			
//			activity.sendBroadcast(i);
			
			
		}

	}


