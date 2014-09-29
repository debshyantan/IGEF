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
	static String PROFILEPICURL = "profilepicurl";
	static String COVERPHOTO = "coverphoto";
	static String STATUS = "status";
	static String CREATEDAT = "createdAt";
	static String USERNAME = "username";
	static String PHOTOURl = "photourl";

	public IGEFSharedPrefrence(Context ctx) {
		prefobj = ctx.getSharedPreferences("IGEFSharedPrefrence",
				Context.MODE_PRIVATE);

	}

	public static String getUSERNAME() {
		return prefobj.getString(USERNAME, "");
	}

	public static void setUSERNAME(String uSERNAME) {
		prefobj.edit().putString(USERNAME, uSERNAME).commit();
	}

	public static String getID() {
		return prefobj.getString(ID, "");
	}

	public static void setID(String iD) {
		prefobj.edit().putString(ID, iD).commit();
	}

	public static String getFULL_NAME() {
		return prefobj.getString(FULL_NAME, "");
	}

	public static void setFULL_NAME(String fULL_NAME) {
		prefobj.edit().putString(FULL_NAME, fULL_NAME).commit();
	}

	public static String getROLL_NO() {
		return prefobj.getString(ROLL_NO, "");
	}

	public static void setROLL_NO(String rOLL_NO) {

		prefobj.edit().putString(ROLL_NO, rOLL_NO).commit();
	}

	public static String getGENDER() {
		return prefobj.getString(GENDER, "");
	}

	public static void setGENDER(String gENDER) {
		prefobj.edit().putString(GENDER, gENDER).commit();
	}

	public static String getDEPARTMENT() {
		return prefobj.getString(DEPARTMENT, "");
	}

	public static void setDEPARTMENT(String dEPARTMENT) {
		prefobj.edit().putString(DEPARTMENT, dEPARTMENT).commit();
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

	public static String getPROFILEPICURL() {
		return prefobj.getString(PROFILEPICURL, "");
	}

	public static void setPROFILEPICURL(String pROFILEPICURL) {
		prefobj.edit().putString(PROFILEPICURL, pROFILEPICURL).commit();

	}

	public static String getCOVERPHOTO() {
		return prefobj.getString(COVERPHOTO, "");
	}

	public static void setCOVERPHOTO(String cOVERPHOTO) {
		prefobj.edit().putString(COVERPHOTO, cOVERPHOTO).commit();
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

	public static String getPHOTOURl() {
		return prefobj.getString(PHOTOURl, "");
	}

	public static void setPHOTOURl(String pHOTOURl) {
		prefobj.edit().putString(PHOTOURl, pHOTOURl).commit();
	}

}
