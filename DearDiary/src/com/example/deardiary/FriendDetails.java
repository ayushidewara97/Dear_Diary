package com.example.deardiary;

import java.util.ArrayList;

import SessionManager.SessionManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class FriendDetails extends Activity {
	TextView t1,t2,t3,t4,t5,t6,t7;
	String s1,s2,s3,s4,s5,s6,s7;

	//String friendname,faddress,fbirth,fmob,fanni,fmail,fgen;
	SessionManager session;
	ArrayList<String> n;
	ArrayList<String> a;
	ArrayList<String> b;
	ArrayList<String> m;
	ArrayList<String> ani;
	ArrayList<String> ml;
	ArrayList<String> g;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend_details);
		Intent i = getIntent();
		String hide = i.getStringExtra("name");
		
		t1 = (TextView) findViewById(R.id.textView1);
		t2 = (TextView) findViewById(R.id.textView2);
		t3 = (TextView) findViewById(R.id.textView3);
		t4 = (TextView) findViewById(R.id.textView4);
		t5 = (TextView) findViewById(R.id.textView5);
		t6 = (TextView) findViewById(R.id.textView6);
		t7 = (TextView) findViewById(R.id.textView7);
		 session = new SessionManager(getApplicationContext());
		 session.isLoggedIn();
		 n = new ArrayList<String>();
			a = new ArrayList<String>();
			b = new ArrayList<String>();
			m = new ArrayList<String>();
			ani = new ArrayList<String>();
			ml = new ArrayList<String>();
			g = new ArrayList<String>();
		 DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
			SQLiteDatabase db = dh.getReadableDatabase();
			String st = "select name,address,birthday,mobile_no,anniversary,mail,gender from FriendsTable where name=?";
			Cursor c = db.rawQuery(st, new String[]{hide});
			while(c.moveToNext()){
				n.add(c.getString(0));
				a.add(c.getString(1));
				b.add(c.getString(2));
				m.add(c.getString(3));
				ani.add(c.getString(4));
				ml.add(c.getString(5));
				g.add(c.getString(6));		
			}
			 s1 = n+"";
			 s2 = a+"";
			 s3 = b+"";
			 s4 = m+"";
			 s5 = ani+"";
			 s6 = ml+"";
			 s7 = g+"";
		
		t1.setText("Friend name: "+s1);
		t2.setText("Address: "+s2);
		t3.setText("Birthday: "+s3);
		t4.setText("Mobile no.: "+s4);
		t5.setText("Anniversary: "+s5);
		t6.setText("Email Address: "+s6);
		t7.setText("Gender: "+s7);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friend_details, menu);
		return true;
	}

}
