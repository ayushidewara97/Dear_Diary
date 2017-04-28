package com.example.deardiary;

import SessionManager.SessionManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Firstpage extends Activity {
	Button click;
	TextView tv;
	String s;
	SessionManager session;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_firstpage);
		session = new SessionManager(getApplicationContext());
		session.isLoggedIn();
		tv = (TextView) findViewById(R.id.textView1);
		tvBundle();
		click = (Button) findViewById(R.id.button1);
		click.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				clickButton();
				
			}
		});
	}
	private void tvBundle(){
		/*Intent i = getIntent();
		s = i.getStringExtra("uname");
		tv.setText("Welcome "+s);*/
		s = session.getUserDetails();
		tv.setText("Welcome "+s);
	}
	private void clickButton(){
		
		Intent i = new Intent(getApplicationContext(), Friendinfo.class);
		//i.putExtra("uname",s);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.firstpage, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item1:
			session.logoutUser();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
