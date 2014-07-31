package com.Prefrence;

import android.content.Context;
import android.content.SharedPreferences;

public class IGEFSharedPrefrence {

	static SharedPreferences prefobj;

	static String ID = "id";
	static String FULL_NAME = "full_name";
	static String ROLL_NO = "roll_no";
	static String GENDER = "gender";
	static String DEPARTMENT = "department";
	static String YEAR = "year";
	static String SECTION = "section";
	static String CONTACTNO = "contactno";
	static String EMAIL = "email";
	static String DEVICETOKEN = "devicetoken";
	static String PASSWORD = "password";
	static String API_KEY = "api_key";
	static String STATUS = "status";
	static String CREATEDAT = "createdAt";

	public IGEFSharedPrefrence(Context ctx) {
		prefobj = ctx
				.getSharedPreferences("MyPreference", Context.MODE_PRIVATE);

	}

	public static String getID() {
		return prefobj.getString(ID, "");
	}

	public static void setID(String iD) {
		prefobj.edit().putString("username", iD).commit();
	}

	public static String getFULL_NAME() {
		return prefobj.getString(FULL_NAME, "");
	}

	public static void setFULL_NAME(String fULL_NAME) {
		prefobj.edit().putString("username", fULL_NAME).commit();	}

	public static String getROLL_NO() {
		return prefobj.getString(ROLL_NO, "");
	}

	public static void setROLL_NO(String rOLL_NO) {
		prefobj.edit().putString("username", rOLL_NO).commit();	}

	public static String getGENDER() {
		return prefobj.getString(GENDER, "");
	}

	public static void setGENDER(String gENDER) {
		prefobj.edit().putString("username", gENDER).commit();
		}

	public static String getDEPARTMENT() {
		return prefobj.getString(DEPARTMENT, "");
	}

	public static void setDEPARTMENT(String dEPARTMENT) {
		prefobj.edit().putString("username", dEPARTMENT).commit();
		}

	public static String getYEAR() {
		return prefobj.getString(YEAR, "");
	}

	public static void setYEAR(String yEAR) {
		prefobj.edit().putString(YEAR, yEAR).commit();
		}

	public static String getSECTION() {
		return prefobj.getString(SECTION, "");
	}

	public static void setSECTION(String sECTION) {
		prefobj.edit().putString(SECTION, sECTION).commit();
		}

	public static String getCONTACTNO() {
		return prefobj.getString(CONTACTNO, "");
	}

	public static void setCONTACTNO(String cONTACTNO) {
		prefobj.edit().putString(CONTACTNO, cONTACTNO).commit();
		}

	public static String getEMAIL() {
		return prefobj.getString(EMAIL, "");
	}

	public static void setEMAIL(String eMAIL) {
		prefobj.edit().putString(EMAIL, eMAIL).commit();
		}

	public static String getDEVICETOKEN() {
		return prefobj.getString(DEVICETOKEN, "");
	}

	public static void setDEVICETOKEN(String dEVICETOKEN) {
		prefobj.edit().putString(DEVICETOKEN, dEVICETOKEN).commit();
		}

	public static String getPASSWORD() {
		return prefobj.getString(PASSWORD, "");
	}

	public static void setPASSWORD(String pASSWORD) {
		prefobj.edit().putString(PASSWORD, pASSWORD).commit();
		}

	public static String getAPI_KEY() {
		return prefobj.getString(API_KEY, "");
	}

	public static void setAPI_KEY(String aPI_KEY) {
		prefobj.edit().putString(API_KEY, aPI_KEY).commit();
		}

	public static String getSTATUS() {
		return prefobj.getString(STATUS, "");
	}

	public static void setSTATUS(String sTATUS) {
		prefobj.edit().putString(STATUS, sTATUS).commit();
		}

	public static String getCREATEDAT() {
		return prefobj.getString(CREATEDAT, "");
	}

	public static void setCREATEDAT(String cREATEDAT) {
		prefobj.edit().putString(CREATEDAT, cREATEDAT).commit();
		}
	
	
	

}
