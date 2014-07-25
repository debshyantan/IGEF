package com.SocialNetwork.igef;

import java.io.IOException;
import java.io.InputStream;
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

import android.support.v4.app.FragmentActivity;


public class MessageSenderGCM {

	                                                                                                                                                
    // calling android push method                                                                               
                                                                                                                                                     
                                                                                                                                                       
//Android push message to GCM server method                                                                                       
public static String AndroidPush(String message, FragmentActivity fragmentActivity)                                                                                                                      
    {                                                                                                                                                   
// your RegistrationID paste here which is received from GCM server.                                                               
   String regId = IGEFSharedPref.getDEVICE_TOKEN();                                                                                           
// applicationID means google Api key                                                                                                     
   String applicationID = "AIzaSyDUefZdS3kSI8btc4dkGt_K3-pb__YeP3M";                                                         
//SENDER_ID is nothing but your ProjectID (from API Console- google code)//                                          
   String SENDER_ID = GCMglobals.GCM_SENDER_ID;                                                                                            

       HttpClient httpclient=new DefaultHttpClient();
       HttpPost httpost=new HttpPost("https://android.googleapis.com/gcm/send");

       List<NameValuePair> formparams = new ArrayList<NameValuePair>();

       formparams.add(new BasicNameValuePair("registration_id", regId));
       formparams.add(new BasicNameValuePair("data.message", message));
       // UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
       // "UTF-8");
       
       
       System.out.println(message  +  regId);
      
       HttpResponse response = null;

       httpost.setHeader("Authorization",
               "key="+applicationID);
       httpost.setHeader("Content-Type",
               "application/x-www-form-urlencoded;charset=UTF-8");

       try {
    	   httpost.setEntity(new UrlEncodedFormEntity(formparams, "utf-8"));
          

           //Get the response
           response = httpclient.execute(httpost);

           int responseCode = response.getStatusLine().getStatusCode();
            String responseText = Integer.toString(responseCode);      
            System.out.println("HTTP POST : " + responseText);

            /*Checking response */
            if(response!=null){
                InputStream in = response.getEntity().getContent(); //Get the data in the entity
                System.out.println("HTTP POST : " + in.toString());
            }
           //Print result
           System.out.println(response.toString());

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
	return response.toString();                                                                                                              
           }                                            

	
}
