package com.userscreen;

import java.io.File;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

public class clearLogouttask extends AsyncTask<Void, Void, Void> {

	int answer = 0;

	FragmentActivity activity;

	public clearLogouttask(FragmentActivity activity2) {
		// TODO Auto-generated constructor stub
		this.activity = activity2;
		File root = android.os.Environment.getExternalStorageDirectory();

		File dir = new File(root.getAbsolutePath() + "/ProfilePhoto");
		if (dir.exists() == true) {
			// this will clear data of photo from the SD CARD
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
		// TODO Auto-generated method stub
		try {
			answer = clearApplicationData();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (answer == 1) {
			Handler h = new Handler();
			h.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					Toast.makeText(activity, "Wait Clearing Account...",
							Toast.LENGTH_SHORT).show();
					System.exit(0);

				}
			}, 2000);
		}

	}

	public int clearApplicationData() {

		File cache = activity.getApplicationContext().getCacheDir();
		File appDir = new File(cache.getParent());
		if (appDir.exists()) {
			String[] children = appDir.list();
			for (String s : children) {
				if (!s.equals("lib")) {
					deleteDir(new File(appDir, s));
					Log.i("TAG", "IGEF NETWORKD Data" + s + " DELETED ");
				}
			}
		}
		return 1;
	}

	public static boolean deleteDir(File dir) {
		if (dir != null && dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

}