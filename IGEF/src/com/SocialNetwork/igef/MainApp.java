package com.SocialNetwork.igef;

import java.util.concurrent.atomic.AtomicInteger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.Prefrence.IGEFSharedPrefrence;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;


//@ReportsCrashes(formKey="dGVacG0ydVHnaNHjRjVTUTEtb3FPWGc6MQ",
//mode = ReportingInteractionMode.DIALOG,
//resDialogText = R.string.crash_dialog_text,
//resDialogIcon = android.R.drawable.ic_dialog_info, //optional. default is a warning sign
//resDialogTitle = R.string.crash_dialog_title, // optional. default is your application name
//resDialogCommentPrompt = R.string.crash_dialog_comment_prompt, // optional. when defined, adds a user text field input with this text resource as a label
//mailTo = "anmol.verma@svimtech.com"
//
//)
public class MainApp extends Application {

	
	
  
	

	
    private GoogleCloudMessaging gcm;
    static Context ctx=null;
    private AtomicInteger msgId = new AtomicInteger();
    private String regid;
    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000; 
	
     
        
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
		super.onCreate();
		//ACRA.init(this);
		ctx=getApplicationContext();
		
		IGEFSharedPrefrence obj=new IGEFSharedPrefrence(getApplicationContext());
		postInitApplication();
		
	
	}




	
	
	public static void postInitApplication()
	{
	
		
			MainApp app=(MainApp)MainApp.ctx;
			app.initPlayServices(ctx);
		
	
	}
	
	void initPlayServices(Context ctx2) {
        if (checkPlayServices()) {
        
        	
            gcm = GoogleCloudMessaging.getInstance(ctx);
            regid = getRegistrationId(ctx);

            if (regid.length() == 0) {
                registerInBackground();
                
            } else {
                //sendRegistrationIdToBackend(false);
            }
        } else {
            Log.d("tmessages", "No valid Google Play Services APK found.");
        }
    }
	
	 public void registerInBackground() {
		
	        AsyncTask<String, String, Boolean> task = new AsyncTask<String, String, Boolean>() {
	            @Override
	            protected Boolean doInBackground(String... objects) {
	                if (gcm == null) {
	                    gcm = GoogleCloudMessaging.getInstance(ctx);
	                }
	                int count = 0;
	                while (count < 1000) {
	                    try {
	                        count++;
	                        regid = gcm.register(GCMglobals.GCM_SENDER_ID);
	                       // sendRegistrationIdToBackend(true);
	                     IGEFSharedPref.setDEVICE_TOKEN(regid);
	                     Log.i("devicetoken", regid);
	                        return true;
	                    } catch (Exception e) {
	                        Log.i("tmessages", e.toString());
	                    }
	                    try {
	                        if (count % 20 == 0) {
	                            Thread.sleep(60000 * 30);
	                        } else {
	                            Thread.sleep(5000);
	                        }
	                    } catch (InterruptedException e) {
	                       Log.e("tmessages", e.toString());
	                    }
	                }
	                return false;
	            }
	        }.execute(null, null, null);
	    }
	
 private boolean checkPlayServices() {
     int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
     return resultCode == ConnectionResult.SUCCESS;
     /*if (resultCode != ConnectionResult.SUCCESS) {
         if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
             GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
         } else {
             Log.i("tmessages", "This device is not supported.");
         }
         return false;
     }
     return true;*/
 }
 
 private String getRegistrationId(Context ctx2) {
	
     final SharedPreferences prefs = getGCMPreferences(ctx);
     String registrationId = prefs.getString("devicetoken", "");
     if (registrationId.length() == 0) {
         Log.d("tmessages", "Registration not found.");
         return "";
     }
     else{
    	 Log.d("IGEF devicetoken", registrationId);
     }
     
     
 
     return registrationId;
 }
 
 private SharedPreferences getGCMPreferences(Context context) {
    return getSharedPreferences("IGEFpreferences", Context.MODE_PRIVATE);
 }
 

	




}
