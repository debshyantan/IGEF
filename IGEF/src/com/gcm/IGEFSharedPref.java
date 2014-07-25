package com.gcm;

import android.content.Context;
import android.content.SharedPreferences;

public class IGEFSharedPref {
	
	public static String DEVICE_TOKEN="devicetoken";
	
	static IGEFSharedPref Igefpref;
	
	static SharedPreferences pref;
	public IGEFSharedPref(Context context)
	{
		pref = context.getSharedPreferences("IGEFpreferences",
				Context.MODE_PRIVATE);
	}
	
	

	public static String getDEVICE_TOKEN() {
		return pref.getString(DEVICE_TOKEN, "");
	}

	public static void setDEVICE_TOKEN(String dEVICE_TOKEN) {
		pref.edit().putString(DEVICE_TOKEN,dEVICE_TOKEN).commit();

	}
	



}
