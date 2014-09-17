package com.SocialNetwork.igef;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.edmodo.cropper.CropImageView;
import com.userscreen.UserScreen;

public class PhotoUpload extends Activity {
	ImageView gallery, camera, rotate, crop, uploadphoto, croppedImageView,
			noimage;
	TextView skip;
	static Cursor c;
	CropImageView cropImageView;
	TableRow photochooserrow, editorrow, uploadrow;
	private static final int ROTATE_NINETY_DEGREES = 90;
	Bitmap croppedImage;

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
				Intent i = new Intent(PhotoUpload.this, UserScreen.class);
				startActivity(i);
				finish();
			
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
				
				croppedImage = cropImageView.getCroppedImage();

				croppedImageView.setImageBitmap(croppedImage);
				
				editorrow.setVisibility(View.GONE);
				uploadrow.setVisibility(View.VISIBLE);
			}
		});

		// upload button
		uploadphoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PhotoUpload.this.finish();

			}
		});

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
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

}
