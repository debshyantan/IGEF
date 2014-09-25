package com.SocialNetwork.igef;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.Photo.Base64;
import com.edmodo.cropper.CropImageView;
import com.userscreen.UserScreen;

public class PhotoUpload extends Activity {
	ImageView gallery, camera, rotate, crop, uploadphoto, croppedImageView,
			noimage;
	  InputStream inputStream;
	TextView skip;
	static Cursor c;
	CropImageView cropImageView;
	TableRow photochooserrow, editorrow, uploadrow;
	private static final int ROTATE_NINETY_DEGREES = 90;
	Bitmap croppedImage;
	BitmapFactory.Options options;

	ProgressDialog pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photoupload);
		cropImageView = (CropImageView) findViewById(R.id.CropImageView);

		// Skip Textview
		skip = (TextView) findViewById(R.id.skip);
		skip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (IGEFSharedPrefrence.getCOVERPHOTO().equals("")) {
					System.out.println("no COver Photo Set --> moving to CoverPhotoChooser activity");
				
				Intent intt=new Intent(PhotoUpload.this, CoverPhotoChooser.class);
					startActivity(intt);
					finish();
				
			}
				
				else{
					Intent i = new Intent(PhotoUpload.this, UserScreen.class);
					startActivity(i);
					finish();
				}
				
				
			
			}
		});

		// photoupload button
		noimage = (ImageView) findViewById(R.id.noimage);
		gallery = (ImageView) findViewById(R.id.gallery);
		camera = (ImageView) findViewById(R.id.camera);
		rotate = (ImageView) findViewById(R.id.rotate);
		crop = (ImageView) findViewById(R.id.crop);
		uploadphoto = (ImageView) findViewById(R.id.uploadphoto);
		croppedImageView = (ImageView) findViewById(R.id.cropedphoto);
		// table rows
		photochooserrow = (TableRow) findViewById(R.id.photochooserrow);
		editorrow = (TableRow) findViewById(R.id.editorrow);
		uploadrow = (TableRow) findViewById(R.id.uploadrow);

		// gallery to choose photo
		gallery.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent ig = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(ig, 1);

			}
		});

		// camera button to choose photo
		camera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent ic = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(ic, 2);

			}
		});

		// rotate button
		rotate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				cropImageView.rotateImage(ROTATE_NINETY_DEGREES);
			}
		});

		// cropped button and set on a hidden image view
		crop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				options=new BitmapFactory.Options();
				options.inSampleSize=8;
				
				
				croppedImage = cropImageView.getCroppedImage();

				croppedImageView.setImageBitmap(croppedImage);
				
				editorrow.setVisibility(View.GONE);
				uploadrow.setVisibility(View.VISIBLE);
				
				LinearLayout hideit=(LinearLayout)findViewById(R.id.hideit);
				hideit.setVisibility(View.GONE);
				croppedImageView.setVisibility(View.VISIBLE);
				
				
				
				
				
			}
		});

		// upload button 
		uploadphoto.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View v) {
				
				  pd=new ProgressDialog(PhotoUpload.this);
            		pd.setTitle("Uploading Photo");
            		pd.setMessage("Wait While we Upload Your Photo...");
//            		pd.setCancelable(false);
            		pd.show();

	            BitmapDrawable drawable = (BitmapDrawable) croppedImageView.getDrawable();
	            Bitmap bitmap1 = drawable.getBitmap();
	            
	           int oldwidth= bitmap1.getWidth();
	           int oldheight=bitmap1.getHeight();
	           
	           System.out.println("oldwidth"+oldwidth +"old height" + oldheight);
	            
	            Bitmap bitmap=getResizedBitmap( bitmap1, 50	 , 50);
	                    

	            int newwidth = bitmap.getWidth();
		           int newheight =bitmap.getHeight();
		           
		           System.out.println("oldwidth"+newwidth +"old height" + newheight);
	            
	            
	            
	            ByteArrayOutputStream stream = new ByteArrayOutputStream();
	            bitmap.compress(Bitmap.CompressFormat.PNG, 10, stream);
	            //compress to which format you want.
	            byte [] byte_arr = stream.toByteArray();
	            String image_str = Base64.encodeBytes(byte_arr);
//	            new PhotouploadAsynktask(image_str,PhotoUpload.this).execute();
	            
	            
	            
	            
	            final ArrayList<NameValuePair> nameValuePairs = new  ArrayList<NameValuePair>();
	 
	            nameValuePairs.add(new BasicNameValuePair("image",image_str));
	            nameValuePairs.add(new BasicNameValuePair("roll_no",IGEFSharedPrefrence.getROLL_NO()));
	             Thread t = new Thread(new Runnable() {
	             
	            @Override
	            public void run() {
	                  try{
	                	
	                	  
	                         HttpClient httpclient = new DefaultHttpClient();
	                         HttpPost httppost = new HttpPost("http://shypal.com/IGEF/task_manager/uploadedimages/imageupload.php");
	                         httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	                         HttpResponse response = httpclient.execute(httppost);
	                         
	                         final String the_string_response = convertResponseToString(response);
	                         runOnUiThread(new Runnable() {
	                                 
	                                @Override
	                                public void run() {
	                                	Toast.makeText(PhotoUpload.this, "Response " + the_string_response, Toast.LENGTH_LONG).show();                          
	                                	pd.dismiss();

	                                	Intent i = new Intent(PhotoUpload.this, UserScreen.class);
	                    				startActivity(i);
	                    				finish();
	                                	
	                                	
	                                	
	                                }
	                            });
	                          
	                     }catch(final Exception e){
	                          runOnUiThread(new Runnable() {
	                             
	                            @Override
	                            public void run() {
	                            	pd.dismiss();
	                                Toast.makeText(PhotoUpload.this, "ERROR " + e.getMessage(), Toast.LENGTH_LONG).show();                              
	                            }
	                        });
	                           System.out.println("Error in http connection "+e.toString());
	                     }  
	            }

				
	        });
	         t.start();
				
				
					
				
				
				

			}

			private Bitmap getResizedBitmap(Bitmap bitmap1, int i, int j) {
				// TODO Auto-generated method stub
				 return Bitmap.createScaledBitmap(bitmap1 , i, j, true);
			}
		});

	}
	

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
			Uri image = data.getData();
			String[] loc = { MediaStore.Images.Media.DATA };
			c = getContentResolver().query(image, loc, null, null, null);
			c.moveToFirst();
			int index = c.getColumnIndex(loc[0]);
			String picturePath = c.getString(index);
			c.close();
			cropImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			photochooserrow.setVisibility(View.GONE);
			
			editorrow.setVisibility(View.VISIBLE);
			noimage.setVisibility(View.GONE);
			cropImageView.setVisibility(View.VISIBLE);
			
		} else if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
			Bundle b = data.getExtras();
			Bitmap bmp = (Bitmap) b.get("data");

			cropImageView.setImageBitmap(bmp);
			photochooserrow.setVisibility(View.GONE);
			editorrow.setVisibility(View.VISIBLE);
			noimage.setVisibility(View.GONE);
			cropImageView.setVisibility(View.VISIBLE);


		}
	}
	
	
	public String convertResponseToString(HttpResponse response) throws IllegalStateException, IOException{
		 
        String res = "";
        final StringBuffer buffer = new StringBuffer();
        inputStream = response.getEntity().getContent();
        final int contentLength = (int) response.getEntity().getContentLength(); //getting content length…..
         runOnUiThread(new Runnable() {
        
       @Override
       public void run() {
           Toast.makeText(PhotoUpload.this, "contentLength : " + contentLength, Toast.LENGTH_LONG).show();                     
       }
   });
     
        if (contentLength < 0){
        }
        else{
               byte[] data = new byte[512];
               int len = 0;
               try
               {
                   while (-1 != (len = inputStream.read(data)) )
                   {
                       buffer.append(new String(data, 0, len)); //converting to string and appending  to stringbuffer…..
                   }
               }
               catch (IOException e)
               {
                   e.printStackTrace();
               }
               try
               {
                   inputStream.close(); // closing the stream…..
               }
               catch (IOException e)
               {
                   e.printStackTrace();
               }
               res = buffer.toString();     // converting stringbuffer to string…..

               runOnUiThread(new Runnable() {
                
               @Override
               public void run() {
                  Toast.makeText(PhotoUpload.this, "Result : " + buffer.toString()  , Toast.LENGTH_LONG).show();
               }
           });
               
               System.out.println("Response => " +  EntityUtils.toString(response.getEntity()));
               
//               pd.dismiss();
        }
        return res;
   }

}
