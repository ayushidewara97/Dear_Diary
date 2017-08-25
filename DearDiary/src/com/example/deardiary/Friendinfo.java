package com.example.deardiary;

import java.util.ArrayList;

import SessionManager.SessionManager;
import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Friendinfo extends Activity implements OnClickListener, OnItemClickListener {
	Button addinfo;
	ListView lv;
	AutoCompleteTextView t1;
	ArrayList<String> n;
	//ArrayList<Integer> i;
	String name,key;
	DatabaseHelper dh;
	SQLiteDatabase db;
	SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friendinfo);
		addinfo = (Button) findViewById(R.id.button1);
		t1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		
	        
	      
		lv = (ListView) findViewById(R.id.listView1);	
		session = new SessionManager(getApplicationContext());
		session.checkLogin();
		key = session.getUserDetails();
		
		addinfo.setOnClickListener(this);
		n = new ArrayList<String>();
		//i = new ArrayList<Integer>();

		 dh = new DatabaseHelper(getApplicationContext());
		 db = dh.getReadableDatabase();
		String st = "select fid,name from FriendsTable where key=?";
		Cursor c = db.rawQuery(st, new String[]{key});
		while(c.moveToNext()){
			//i.add(c.getInt(c.getColumnIndex("fid")));
			n.add(c.getString(1));
			
		}
		
		// _id = i.toString().replaceAll("\\[|\\]", "").replaceAll(", ","\t");
		 	
		
		CustomAdapter1 ad = new CustomAdapter1(this, R.layout.model, n);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,n);
		t1.setAdapter(adapter);
		t1.setThreshold(1);
		t1.setTextColor(Color.RED);
		lv.setAdapter(ad);
		lv.setOnItemClickListener(this);
		
		/*t1.setOnTouchListener(new OnTouchListener() {
		@Override
		  public boolean onTouch(View v, MotionEvent event) {
		        
	            if(event.getAction() == MotionEvent.ACTION_UP) {
	                if(event.getRawX() >= (t1.getRight() - t1.getTotalPaddingRight())) {
	                    // your action here event.getRawX() >= txtsearch.getRight() - txtsearch.getTotalPaddingRight()
	                	//String friendname = t1.getText().toString();
	                	Toast.makeText(getApplicationContext(), "this is working properly", Toast.LENGTH_SHORT).show();

	                	Bundle dataBundle = new Bundle();
	                	dataBundle.putInt("friend_id", _id);
	            		dataBundle.putString("key", key);	     
	            		Intent i1 = new Intent(Friendinfo.this,FriendDetails.class);
	            		//i1.putExtra("friend_id", _id);
	                    i1.putExtras(dataBundle);
	            		startActivity(i1);
	                 return true;
	                }
	            }
	            return false;
	        }

			
	    });*/
		t1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				int _id = ++index;
				Bundle dataBundle = new Bundle();
				dataBundle.putInt("friend_id", _id);
				dataBundle.putString("key", key);
				Intent i1 = new Intent(Friendinfo.this,FriendDetails.class);
				//i1.putExtra("friend_id", _id);
		        i1.putExtras(dataBundle);
				startActivity(i1);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friendinfo, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			
			Intent intent = new Intent(getApplicationContext(), Friendinfo1.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int index, long arg3) {
		
	//String friendname = ((TextView)v.findViewById(R.id.textView1)).getText().toString();
	// _id = index + 1;
		int _id = ++index;
	 
	// dh = new DatabaseHelper(getApplicationContext());
	// db = dh.getReadableDatabase();
	//String st = "select fid from FriendsTable where key=? AND name=?";
	//Cursor c = db.rawQuery(st, new String[]{key,friendname});
	
	/*while(c.moveToNext()){
		_id=c.getInt(c.getColumnIndex("fid"));
		//n.add(c.getString(1));
		
	}*/
		Toast.makeText(getApplicationContext(), _id+" "+index+" "+arg3+" "+n, Toast.LENGTH_LONG).show();

		Bundle dataBundle = new Bundle();
		dataBundle.putInt("friend_id", _id);
		dataBundle.putString("key", key);
		Intent i1 = new Intent(this,FriendDetails.class);
		//i1.putExtra("friend_id", _id);
        i1.putExtras(dataBundle);
		startActivity(i1);
	}

	
	

}
