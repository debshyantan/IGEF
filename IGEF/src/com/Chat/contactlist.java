package com.Chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import com.SocialNetwork.igef.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quickblox.core.QBCallback;
import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.result.Result;
import com.quickblox.internal.core.request.QBPagedRequestBuilder;
import com.quickblox.module.content.QBContent;
import com.quickblox.module.content.model.QBFile;
import com.quickblox.module.content.result.QBFileResult;
import com.quickblox.module.users.QBUsers;
import com.quickblox.module.users.model.QBUser;
import com.quickblox.module.users.result.QBUserPagedResult;





import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;

import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class contactlist extends ActionBarActivity implements QBCallback{

	 private static final int PAGE_SIZE = 1000;
	    private PullToRefreshListView usersList1;
	    private QBUser companionUser;
	    private int listViewIndex;
	    private int listViewTop;
	    private static final String KEY_USER_LOGIN = "userLogin";
      	 QBUserPagedResult usersResult;

		
		private ArrayList<QBUser> al;
		private ArrayList<QBUser> users;
		Myadapter adapter;
		private ListView list_of_users;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.fragmentview);
		
		
		 usersList1 = (PullToRefreshListView)findViewById(R.id.usersList);
		 
		
		 
		 list_of_users=usersList1.getRefreshableView();
		  usersList1.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
	    
				@Override
	            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
					
	                // Do work to refresh the list here.
					loadPage();
	                listViewIndex = usersList1.getRefreshableView().getFirstVisiblePosition();
	                View v = usersList1.getRefreshableView().getChildAt(0);
	                listViewTop = (v == null) ? 0 : v.getTop();
	            }

				
	        });
		  
		  usersList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
	                companionUser = users.get(position-1);
	                System.out.println(""+companionUser);
	                if (companionUser != null) {
	                    startChat();
	                }
	            }
	        });
	
	}
	
	 public void startChat() {
	        Bundle bundle = new Bundle();
	        bundle.putSerializable(ChatActivity.EXTRA_MODE, ChatActivity.Mode.SINGLE);
	        bundle.putInt(SingleChat.EXTRA_USER_ID, companionUser.getId());
	        ChatActivity.start(contactlist.this, bundle);
	    }
	
	private void loadPage() {
		// TODO Auto-generated method stub
		int currentPage = ((App)getApplication()).getCurrentPage();
        QBUsers.getUsers(getQBPagedRequestBuilder(currentPage),this);
        
	}
	
	 public static QBPagedRequestBuilder getQBPagedRequestBuilder(int page) {
	        QBPagedRequestBuilder pagedRequestBuilder = new QBPagedRequestBuilder();
	        pagedRequestBuilder.setPage(page);
	        pagedRequestBuilder.setPerPage(PAGE_SIZE);

	        return pagedRequestBuilder;
	    }

	@Override
	public void onComplete(Result result) {
		// TODO Auto-generated method stub
		
		  if (result.isSuccess()) {
			  
			  
      	
	    usersResult = (QBUserPagedResult) result;
	       		users = usersResult.getUsers();
	       		for(int i=0;i<users.size();i++){
	       			System.out.println(""+users.get(i).getLogin());
	       		}
	       	 }
	       		if(users.size()>0){
	            new AsyncTask<Void, Void, Void>() {
	            	@Override
	            	protected void onPreExecute() {
	            		users=usersResult.getUsers();
	            		
	            	};
	            	


					@Override
					protected Void doInBackground(Void... params) {
						// TODO Auto-generated method stub
					  al=new ArrayList<QBUser>();
					        
					    
					      //  System.out.println(number);
					    
					    	for(int k=0;k<users.size();k++)
					        	{
					    		
					        		
					        					al.add(users.get(k));
					        			//	System.out.println(number+" matches");	
					        		
					        		
					        	
					        	
					    	 }
					
					      
						return null;
					}
					@Override
					protected void onPostExecute(Void result) {
						  System.out.println("comparedd");
						  usersList1.onRefreshComplete();
						  adapter=new Myadapter(contactlist.this,al);
					    	list_of_users.setAdapter(adapter);
					    	adapter.notifyDataSetChanged();
					    	
					};
				}.execute();
	         
				
				
		      
	       	}
	       		else{
	       		usersList1.onRefreshComplete();
	       		}
		  }

	@Override
	public void onComplete(Result arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	
	}

	class Myadapter extends BaseAdapter{

		contactlist contactlist;
		ArrayList<QBUser> al;
		public Myadapter(contactlist contactlist, ArrayList<QBUser> al) {
			// TODO Auto-generated constructor stub
			this.contactlist=contactlist;
			this.al=al;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return al.size();
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
			// TODO Auto-generated method stub
			
			ViewHolder holder=null;
			if(convertView==null){
			LayoutInflater inf=(LayoutInflater)contactlist.getSystemService(Context.LAYOUT_INFLATER_SERVICE);	
			convertView=inf.inflate(R.layout.list_item_user, parent, false);
			holder=new ViewHolder();
			holder.t=(TextView)convertView.findViewById(R.id.userLogin);
			//holder.imageView=(ImageView)convertView.findViewById(R.id.image);
			
			convertView.setTag(holder);
			}
			else{
				holder=(ViewHolder)convertView.getTag();
			}
			holder.t.setText(al.get(position).getLogin());
			
			// TODO Auto-generated method stub
			return convertView;
		}
		class ViewHolder{
			TextView t;
			//ImageView imageView;
			
		}
	
	}