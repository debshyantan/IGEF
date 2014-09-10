package com.SocialNetwork.CustomFonts;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class FriendsListFontclass extends TextView{
	Typeface tt;
	private void init(Context context) {
		// TODO Auto-generated method stub
		tt=Typeface.createFromAsset(context.getAssets(),"verdana.ttf");
		setTypeface(tt);
		
	}
	public FriendsListFontclass(Context context) {
		super(context);
		init(context);
		// TODO Auto-generated constructor stub
	}

	

	public FriendsListFontclass(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
		// TODO Auto-generated constructor stub
	}

	public FriendsListFontclass(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
		// TODO Auto-generated constructor stub
	}
	
	
}