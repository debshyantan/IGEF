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
	ImageView iv;
	TextView name,status;
	Button like, comment;
	
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
		c.setName("Rahul");
		c.setStatus("skvkjsvkjdvjkhbsdvjhsdvhjsd");
		
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
		iv=(ImageView)convertView.findViewById(R.id.stuImage);
		name=(TextView)convertView.findViewById(R.id.name);
		status=(TextView)convertView.findViewById(R.id.status);
		like=(Button)convertView.findViewById(R.id.like);
		comment=(Button)convertView.findViewById(R.id.comment);
		iv.setImageResource(R.drawable.ic_launcher);
		name.setText(list.get(position).name);
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
