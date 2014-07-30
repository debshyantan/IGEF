package com.userscreen;

import java.util.ArrayList;

import com.SocialNetwork.igef.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class Status extends Fragment{
	ListView lv;
	ImageView profile_iv, status_iv;
	TextView name,status,timestamp;
	
	
	ArrayList<Custom> list;
	
public Status() {
	
}


MyAdapter adapter;
	
	
@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.userscreen, container,false);
		lv=(ListView)rootView.findViewById(R.id.list);
		
		list=new ArrayList<Custom>();
		
		Custom c=new Custom();
		c.setName("Rahul Manchanda");
		c.setTimestamp("July 30,2014 11.15 AM");
		c.setStatus("Welcome to IGEF Networks..!!");
		
		list.add(c);
		
		adapter=new MyAdapter();
		lv.setAdapter(adapter);
	
		
		
		
		
		
		return rootView;
		
	}

class MyAdapter extends BaseAdapter
{

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView=inflater.inflate(R.layout.status, parent, false);
		profile_iv=(ImageView)convertView.findViewById(R.id.profilePic);
		name=(TextView)convertView.findViewById(R.id.name);
		timestamp=(TextView)convertView.findViewById(R.id.timestamp);
		status=(TextView)convertView.findViewById(R.id.txtStatusMsg);
		
		status_iv=(ImageView)convertView.findViewById(R.id.feedImage1);
		profile_iv.setImageResource(R.drawable.ic_launcher);
		status_iv.setImageResource(R.drawable.adminblock);
		name.setText(list.get(position).name);
		timestamp.setText(list.get(position).timestamp);
		status.setText(list.get(position).status);
		
		
		return convertView;
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}
	
}

}
