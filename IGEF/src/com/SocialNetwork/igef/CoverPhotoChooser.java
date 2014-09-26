package com.SocialNetwork.igef;

import Tool.ConnectionDetector;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.SocialNewtwork.AsyncTask.savecoverphotoAsyncTask;
import com.userscreen.UserScreen;

public class CoverPhotoChooser extends ActionBarActivity{
	ActionBar act;
	Gallery gallery;
	ImageView selectedImage, savecoverphoto;
	TextView swipp;
	int coverphotopostion=0;
	Boolean isInternetPresent = false;
	ConnectionDetector cd;
	  private Integer[] mImageIds = {
	            R.drawable.clga,
	            R.drawable.clgb,
	            R.drawable.clgc,
	            R.drawable.clgd,
	            R.drawable.clge,
	            R.drawable.clgf,
	            R.drawable.clgg,
	            R.drawable.clgh
	    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coverphotochooser);
		
		
		cd = new ConnectionDetector(getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		System.out.println("Network states:" + isInternetPresent);
		
		gallery=(Gallery)findViewById(R.id.choosefrom);
		selectedImage=(ImageView)findViewById(R.id.selectedImage);
		savecoverphoto=(ImageView)findViewById(R.id.savecoverphoto);
		
		
		swipp=(TextView)findViewById(R.id.swipp);
		
		act=getSupportActionBar();
		act.setTitle("Cover Photo");
		
		
        gallery.setSpacing(3);
        gallery.setAdapter(new GalleryImageAdapter(this));

         // clicklistener for Gallery
        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	
            	swipp.setVisibility(View.GONE);
            	savecoverphoto.setVisibility(View.VISIBLE);
            	coverphotopostion=position;
            	
                Toast.makeText(CoverPhotoChooser.this, "Your selected position = " + position, Toast.LENGTH_SHORT).show();
                // show the selected Image
                selectedImage.setImageResource(mImageIds[position]);
            }
		
		
        });
      
        //click listener on saving the photo
        savecoverphoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (isInternetPresent) {
				new savecoverphotoAsyncTask(CoverPhotoChooser.this, coverphotopostion).execute();
				}
				else {

					Toast.makeText(CoverPhotoChooser.this, "No Internet Connection", Toast.LENGTH_LONG).show();
				}
				
				
				
			}
		});
		
	}


	public class GalleryImageAdapter extends BaseAdapter 
	{
	    private Context mContext;

	    private Integer[] mImageIds = {
	            R.drawable.clga,
	            R.drawable.clgb,
	            R.drawable.clgc,
	            R.drawable.clgd,
	            R.drawable.clge,
	            R.drawable.clgf,
	            R.drawable.clgg,
	            R.drawable.clgh
	    };

	    public GalleryImageAdapter(Context context) 
	    {
	        mContext = context;
	    }

	    public int getCount() {
	        return mImageIds.length;
	    }

	    public Object getItem(int position) {
	        return position;
	    }

	    public long getItemId(int position) {
	        return position;
	    }


	    // Override this method according to your need
	    public View getView(int index, View view, ViewGroup viewGroup) 
	    {
	        // TODO Auto-generated method stub
	        ImageView i = new ImageView(mContext);

	        i.setImageResource(mImageIds[index]);
//	        i.setLayoutParams(new Gallery.LayoutParams(, 200));
	    
	        i.setScaleType(ImageView.ScaleType.FIT_XY);

	        return i;
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.actionbarmenu, menu);

	       return true;	
	
	
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	// Take appropriate action for each action item click
	switch (item.getItemId()) {
	case R.id.skipp:
		
		Intent i = new Intent(CoverPhotoChooser.this, UserScreen.class);
		startActivity(i);
		finish();
		
	break;
	
	default:
		return super.onOptionsItemSelected(item);
	}
	
	return false;
	
	
	}



}
