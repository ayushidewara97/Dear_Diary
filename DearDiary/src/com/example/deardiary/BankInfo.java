package com.example.deardiary;

import SessionManager.SessionManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BankInfo extends Activity {
	EditText b_uname, b_name, b_account, b_location; 
	Button save_bankinfo;
	SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bank_info);
		
		session = new SessionManager(getApplicationContext());
		session.checkLogin();
		
		b_account  = (EditText) findViewById(R.id.bank_account_editText);
		b_location  = (EditText) findViewById(R.id.bank_location_editText);
		b_name  = (EditText) findViewById(R.id.bank_name_editText);
		b_uname  = (EditText) findViewById(R.id.bank_uname_editText);
		save_bankinfo = (Button) findViewById(R.id.save_bankinfo_button);
		save_bankinfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bank_info, menu);
		return true;
	}

}
