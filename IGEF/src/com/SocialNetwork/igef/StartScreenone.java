package com.SocialNetwork.igef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.Prefrence.IGEFSharedPrefrence;
import com.quickblox.core.QBCallback;
import com.quickblox.core.QBSettings;
import com.quickblox.core.result.Result;
import com.quickblox.module.auth.QBAuth;


public class StartScreenone extends Activity implements QBCallback, AnimationListener{
	
	private static final String APP_ID = "13032";
    private static final String AUTH_KEY = "Qg87qH7jg-FCRbd";
    private static final String AUTH_SECRET = "DewYHDRsf78OL69";
    
	IGEFSharedPrefrence pref;
	TextView blinking;
	Animation animRotate,animBlink;
	
    
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.startscreen);
	
	blinking=(TextView)findViewById(R.id.authentication);
	animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
	animBlink.setAnimationListener(this);
	blinking.setText("Creating Session...");
	blinking.setVisibility(View.VISIBLE);
	blinking.startAnimation(animBlink);
	
	
	 
     QBSettings.getInstance().fastConfigInit(APP_ID, AUTH_KEY, AUTH_SECRET);
     QBAuth.createSession(this); 
	

	
     
	
}
	@Override
	public void onComplete(Result result) {
     //   progressbar1.setVisibility(View.GONE);

		
		System.out.println(result);
		System.out.println(result.isSuccess());
		
        if (result.isSuccess()) {
        	
         pref=new IGEFSharedPrefrence(getApplicationContext());
        
//        	String password = pref.getString(PREF_PASSWORD,"");
//
//        	if (username.equals("") || password.equals("")) {
//        	//Prompt for username and password
//        		  Intent intent = new Intent(this,registeractivity.class);
//                  startActivity(intent);
//                  finish();
        		
        	//}
        	//else{
         
            Intent intent = new Intent(this, StartScreen.class);
            startActivity(intent);
            finish();
        	//}
        } 
        
        else {
        	
        	Toast.makeText(this, "Error(s) occurred....  " +"\n"+
                    "Errors: " + result.getErrors(), Toast.LENGTH_LONG).show();
        	
//            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//            dialog.setMessage("Error(s) occurred....  " +"\n"+
//                    "Errors: " + result.getErrors()).create().show();
//            ((DialogInterface) dialog).dismiss();
        
        	Intent intent = new Intent(this, StartScreen.class);
            startActivity(intent);
            finish();
            
        	 
        	
        	
        }
    }

	@Override
	public void onComplete(Result arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		if (animation == animRotate) {
		}
		
	}

}
