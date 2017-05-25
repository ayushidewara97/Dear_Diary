package com.example.deardiary;

import java.util.ArrayList;

import SessionManager.SessionManager;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Friendinfo extends Activity implements OnClickListener, OnItemClickListener {
	Button addinfo;
	ListView lv;
	String s1,s2,s3,s4,s5,s6,s7;
	AutoCompleteTextView t1;
	ArrayList<String> n;
	
	String name,key;
	SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friendinfo);
		addinfo = (Button) findViewById(R.id.button1);
		t1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		t1.setOnTouchListener(new OnTouchListener() {
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	            final int DRAWABLE_LEFT = 0;
	            final int DRAWABLE_TOP = 1;
	            final int DRAWABLE_RIGHT = 2;
	            final int DRAWABLE_BOTTOM = 3;

	            if(event.getAction() == MotionEvent.ACTION_UP) {
	                if(event.getRawX() >= (t1.getRight() - t1.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
	                    // your action here
	                	//String friendname = ((TextView)v.findViewById(R.id.textView1)).getText().toString();
	                	Toast.makeText(getApplicationContext(), "this is working properly", 2000).show();

	            		//Intent i1 = new Intent(getApplicationContext(), FriendDetails.class);
	            		//i1.putExtra("name", friendname);
	            		//startActivity(i1);
	                 return true;
	                }
	            }
	            return false;
	        }

			
	    });
		lv = (ListView) findViewById(R.id.listView1);	
		session = new SessionManager(getApplicationContext());
		session.isLoggedIn();
		key = session.getUserDetails();
		
		addinfo.setOnClickListener(this);
		n = new ArrayList<String>();

		DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
		SQLiteDatabase db = dh.getReadableDatabase();
		String st = "select name from FriendsTable where key=?";
		Cursor c = db.rawQuery(st, new String[]{key});
		while(c.moveToNext()){
			n.add(c.getString(0));
			
		}
	
		
		CustomAdapter1 ad = new CustomAdapter1(this, R.layout.model, n);
		//ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,n);
		t1.setAdapter(ad);
		t1.setThreshold(1);
		t1.setTextColor(Color.RED);
		lv.setAdapter(ad);
		lv.setOnItemClickListener(this);
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
			
			Intent i = new Intent(getApplicationContext(), Friendinfo1.class);
			startActivity(i);
			break;
		case R.id.button2:
			Intent i1 = new Intent(getApplicationContext(),FriendDetails.class);
			startActivity(i1);
			break;

		default:
			break;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int index, long arg3) {
		
	String friendname = ((TextView)v.findViewById(R.id.textView1)).getText().toString();
	

		Intent i = new Intent(this,FriendDetails.class);
		i.putExtra("name", friendname);
		startActivity(i);
	}

	
	

}
