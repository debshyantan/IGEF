package com.userscreen;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Chat.contactlist;
import com.SocialNetwork.igef.R;


public class UserTask extends Fragment{
	ListView listView;
	MyprofileTaskAdapter myadp;
	ArrayList< ProfiletaskList> profileArraylist;
	 private TypedArray micon ;
	 private String[] mMenuTitles;
	
	
	 
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container,  Bundle savedInstanceState) {
		View rootview=inflater.inflate(R.layout.userprofilelist, container, false);
		
		listView=(ListView)rootview.findViewById(R.id.userporifletask);
		mMenuTitles = getResources().getStringArray(R.array.nav_drawer);
		micon = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		profileArraylist=new ArrayList<ProfiletaskList>();
		for(int i=0;i<mMenuTitles.length;i++){
		profileArraylist.add(new ProfiletaskList(mMenuTitles[i], micon.getResourceId(i, -1)));
		}
	
		myadp=new MyprofileTaskAdapter(getActivity(),profileArraylist);
		listView.setAdapter(myadp);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				
				if(position==0){
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new PostStatus()).addToBackStack(null).commit();
		
				}
				if(position==1){
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ViewMyProfile()).addToBackStack(null).commit();
		
				}
				if(position==2){
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new MyTimeLine()).addToBackStack(null).commit();
	
				}
				if(position==3){

					Intent intent=new Intent(getActivity(),contactlist.class);
				       getActivity().startActivity(intent);
					
				}
				
				if(position==6){
					
					new clearLogouttask(getActivity()).execute();
				
				}
				
			}
		});
	
		
		
		
		return rootview;
	}
	
	
	
	public class MyprofileTaskAdapter extends BaseAdapter{
		FragmentActivity activity;
		ArrayList<ProfiletaskList> profileArraylist;

		public MyprofileTaskAdapter(FragmentActivity activity,
				ArrayList<ProfiletaskList> profileArraylist) {
			
			this.activity=activity;
			this.profileArraylist=profileArraylist;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return profileArraylist.size();
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
			viewholder holder;
			
			if (convertView==null) {
				holder=new viewholder();
				LayoutInflater inflator=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView=inflator.inflate(R.layout.drawer_list_item, parent, false);
				holder.tv = (TextView) convertView.findViewById(R.id.drawer_text);
				holder.iv = (ImageView) convertView.findViewById(R.id.drawer_icon);
				
				
				convertView.setTag(holder);
			}else {
				holder=(viewholder)convertView.getTag();
			}
			holder.tv.setText(profileArraylist.get(position).getmMenuTitles());
			holder.iv.setImageResource(profileArraylist.get(position).getIcon());
			
						
			return convertView;
		}
		class viewholder{
			TextView tv;
			ImageView iv;
		}
		
	}

}
