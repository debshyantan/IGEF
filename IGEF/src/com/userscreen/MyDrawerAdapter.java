package com.userscreen;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.SocialNetwork.igef.R;

public class MyDrawerAdapter extends BaseAdapter {
	Context applicationContext;
	ArrayList<NavDrawerItem> navDrawerItem;

	public MyDrawerAdapter(Context applicationContext,
			ArrayList<NavDrawerItem> navDrawerItems) {
		this.applicationContext = applicationContext;
		this.navDrawerItem = navDrawerItems;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return navDrawerItem.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflator = (LayoutInflater) applicationContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflator
				.inflate(R.layout.drawer_list_item, parent, false);
		TextView tv = (TextView) convertView.findViewById(R.id.drawer_text);
		ImageView iv = (ImageView) convertView.findViewById(R.id.drawer_icon);
		
	//	System.out.println(navDrawerItem.get(position).getTitle());

		tv.setText(navDrawerItem.get(position).getTitle());
		iv.setImageResource(navDrawerItem.get(position).getIcon());

		return convertView;
	}

}
