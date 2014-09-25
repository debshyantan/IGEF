package com.SocialNetwork.igef;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class CoverPhotoChooser extends Activity{
	
	Gallery gallery;
	ImageView selectedImage;
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
		
		gallery=(Gallery)findViewById(R.id.choosefrom);
//		selectedImage=(ImageView)findViewById(R.id.selectedImage);
        gallery.setSpacing(1);
        gallery.setAdapter(new GalleryImageAdapter(this));

         // clicklistener for Gallery
        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(CoverPhotoChooser.this, "Your selected position = " + position, Toast.LENGTH_SHORT).show();
                // show the selected Image
                selectedImage.setImageResource(mImageIds[position]);
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



}
