package com.example.deardiary;

import SessionManager.SessionManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class FriendDetails extends Activity {
	TextView t1,t2,t3,t4,t5,t6,t7;
	String friendname,faddress,fbirth,fmob,fanni,fmail,fgen;
	SessionManager session;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend_details);
		 session = new SessionManager(getApplicationContext());
		 session.isLoggedIn();
		Intent i = getIntent();
		friendname = i.getStringExtra("friendname");
		faddress = i.getStringExtra("faddress");
		fbirth = i.getStringExtra("fbirth");
		fmob = i.getStringExtra("fmob");
		fanni = i.getStringExtra("fanni");
		fmail = i.getStringExtra("fmail");
		fgen = i.getStringExtra("fgen");

		t1 = (TextView) findViewById(R.id.textView1);
		t2 = (TextView) findViewById(R.id.textView2);
		t3 = (TextView) findViewById(R.id.textView3);
		t4 = (TextView) findViewById(R.id.textView4);
		t5 = (TextView) findViewById(R.id.textView5);
		t6 = (TextView) findViewById(R.id.textView6);
		t7 = (TextView) findViewById(R.id.textView7);
		t1.setText("Friend name: "+friendname);
		t2.setText("Address: "+faddress);
		t3.setText("Birthday: "+fbirth);
		t4.setText("Mobile no.: "+fmob);
		t5.setText("Anniversary: "+fanni);
		t6.setText("Email Address: "+fmail);
		t7.setText("Gender: "+fgen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friend_details, menu);
		return true;
	}

}
