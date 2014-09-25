package com.SocialNetwork.igef;

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

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.quickblox.core.QBCallback;
import com.quickblox.core.result.Result;
import com.quickblox.module.chat.QBChatService;
import com.quickblox.module.chat.listeners.SessionCallback;
import com.quickblox.module.chat.smack.SmackAndroid;
import com.quickblox.module.users.QBUsers;
import com.quickblox.module.users.model.QBUser;

public class Register extends Fragment {
	static EditText name, roll, contact, email, password;
	static RadioGroup rdg_g;
	static RadioGroup rdg_s;
	static RadioButton rb1, rb2;
	static Spinner branchSpinner, year;
	static Button submit;
	private QBUser user;
    private SmackAndroid smackAndroid;
	static String d_name, d_roll, d_dept, d_year, d_contact, d_gender, d_email,
			d_password, d_section;

	 public Register() {

	 }

	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.registrationpage,
				container, false);
	
	
	rdg_g = (RadioGroup) rootView.findViewById(R.id.radioSex);
			int selectedID = rdg_g.getCheckedRadioButtonId();
			rb1 = (RadioButton) rootView.findViewById(selectedID);
			rdg_s = (RadioGroup)rootView. findViewById(R.id.radioSection);
			int selectID = rdg_s.getCheckedRadioButtonId();
			rb2 = (RadioButton) rootView.findViewById(selectID);
			branchSpinner = (Spinner) rootView.findViewById(R.id.branch);
			year = (Spinner) rootView.findViewById(R.id.year);
			submit = (Button)rootView. findViewById(R.id.submit);
			name = (EditText)rootView. findViewById(R.id.enterstudentname);
			roll = (EditText) rootView.findViewById(R.id.enterrollno);
			contact = (EditText) rootView.findViewById(R.id.econtactno);
			email = (EditText)rootView. findViewById(R.id.eemailid);
			password = (EditText) rootView.findViewById(R.id.epassword);

			// new commit
			smackAndroid = SmackAndroid.init(getActivity());
			submit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					d_name = name.getText().toString();
					d_roll = roll.getText().toString();
					d_gender = rb1.getText().toString();
					d_contact = contact.getText().toString();
					d_email = email.getText().toString();
					d_password = password.getText().toString();
					d_dept = String.valueOf(branchSpinner.getSelectedItem());
					d_year = String.valueOf(year.getSelectedItem());
					d_section = rb2.getText().toString();
					
					System.out.println(d_name + d_roll + d_dept + d_section
							+ d_year + d_contact + d_email + d_gender + d_password);
					if(d_name.equals("") || d_roll.equals("") || d_contact.equals("") || d_email.equals("") || d_password.equals("") ){
						
						Toast.makeText(getActivity(), "Required Fields Missing", Toast.LENGTH_LONG).show();

					}
					else{
						
					
					
					if (d_password.length()<8) {
						Toast.makeText(getActivity(), "Min. Length of Password is 8", Toast.LENGTH_LONG).show();
						password.setText("");
						password.requestFocus(); 
						
						System.out.println("password length" + d_password.length());
						
					}
					
					else{
						
					

					new AsyncTask<Void, Void, Void>() {

						ProgressDialog pd;

						String value;

						@Override
						protected void onPreExecute() {

							pd = new ProgressDialog(getActivity());
							pd.setMessage("Requesting Server..");
							pd.show();

						};

						@Override
						protected Void doInBackground(Void... params) {
							// TODO Auto-generated method stub
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost(
									"http://shypal.com/IGEF/task_manager/v1/register");
							List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
							nameValuePairs.add(new BasicNameValuePair("full_name",
									d_name));
							nameValuePairs.add(new BasicNameValuePair("roll_no",
									d_roll));
							nameValuePairs.add(new BasicNameValuePair("gender",
									d_gender));
							nameValuePairs.add(new BasicNameValuePair("department",
									d_dept));
							nameValuePairs.add(new BasicNameValuePair("year",
									d_year));
							nameValuePairs.add(new BasicNameValuePair("section",
									d_section));
							nameValuePairs.add(new BasicNameValuePair("contactno",
									d_contact));
							nameValuePairs.add(new BasicNameValuePair("email",
									d_email));
							nameValuePairs.add(new BasicNameValuePair("password",
									d_password));

							try {
								httppost.setEntity(new UrlEncodedFormEntity(
										nameValuePairs));
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							// Execute HTTP Post Request
							HttpResponse response = null;
							try {
								response = httpclient.execute(httppost);
							} catch (ClientProtocolException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						
								try {
									value = EntityUtils.toString(response.getEntity());
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
							pd.dismiss();

							user = new QBUser(d_roll, d_password);
							name.setText(null);
							roll.setText(null);
							contact.setText(null);
							email.setText(null);
							password.setText(null);
							System.out.println("" + value);
							
							QBUsers.signUpSignInTask(user, new QBCallback() {
								
								@Override
								public void onComplete(Result arg0, Object arg1) {
									// TODO Auto-generated method stub
									
								}
								@Override
								public void onComplete(Result result) {
									// TODO Auto-generated method stub
									 if (result.isSuccess()) {
								            ((com.Chat.App) getActivity().getApplication()).setQbUser(user);
								       
								            QBChatService.getInstance().loginWithUser(user, new SessionCallback() {
								                @Override
								                public void onLoginSuccess() {
								                    if (pd != null) {
								                        pd.dismiss();
														getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginStudent()).commit();

								                    }
								                
								                }
												@Override
												public void onLoginError(String error) {
													// TODO Auto-generated method stub
													
												}
												  
								                });
								            }
									 else {
								                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
								                dialog.setMessage("Error(s) occurred... " +"\n"+
								                        "Errors: " + result.getErrors()).create().show();
								            }
									
								}
							});
							
							
							
						};

							}.execute();
						}
					}
				}
			});

		
	

		return rootView;
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		smackAndroid.onDestroy();
		super.onDestroy();
	}
}