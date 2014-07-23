package com.SocialNetwork.igef;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class IgefSocailNetwork extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_igef_socail_network);
//asbsdbvfjsadbvkjadsbvjkhbsdabsdvjdbs
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
			
			//sdvakjsdjvbasdj
			//my change
			
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.igef_socail_network, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements
			AnimationListener {
		ImageView signup;
		// Animation
		Animation animRotate, animFadein;;
		Handler handel;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_igef_socail_network, container, false);

			
			signup = (ImageView) rootView.findViewById(R.id.signupbutton);
			handel = new Handler();
			animRotate = AnimationUtils.loadAnimation(getActivity(),
					R.anim.rotate);

			
			// create button
			animFadein = AnimationUtils.loadAnimation(getActivity(),
					R.anim.fade_in);

			// set animation listener
			animFadein.setAnimationListener(this);
			handel.postDelayed(new Runnable() {

				@Override
				public void run() {
					
					signup.setVisibility(View.VISIBLE);

					// start the animation
					signup.startAnimation(animFadein);

				}

			}, 3000);

			return rootView;
		}

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}
	}

}
