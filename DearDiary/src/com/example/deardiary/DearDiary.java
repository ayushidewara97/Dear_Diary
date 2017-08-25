 package com.example.deardiary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import SessionManager.SessionManager;


public class DearDiary extends Activity implements OnClickListener {
	Button supButton,loginButton;
	EditText t1,t2;
	TextView _passhint;
    SessionManager session;
    DatabaseHelper dh;
    SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dear_diary);
		supButton = (Button) findViewById(R.id.button2);
		loginButton = (Button) findViewById(R.id.button1);
		t1 = (EditText) findViewById(R.id.editText1);
		t2 = (EditText) findViewById(R.id.editText2);
		_passhint = (TextView) findViewById(R.id.textView2);
		_passhint.setOnClickListener(this);
		loginButton.setOnClickListener(this); 
		supButton.setOnClickListener(this);
	
	
	}	
	private void loginValidation(){
		String u_name = t1.getText().toString();
		String pass_word = t2.getText().toString();
		DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
		SQLiteDatabase db = dh.getReadableDatabase();
		String q = "select* from SignupTable where uname=? AND password=?";
		Cursor c = db.rawQuery(q, new String[]{u_name,pass_word});
		if(c.moveToFirst()){
			session = new SessionManager(getApplicationContext());
			session.createLoginSession(u_name);
			Intent i1 = new Intent(DearDiary.this, Firstpage.class);
			startActivity(i1);
			finish();
		}
		else{
			Toast.makeText(getApplicationContext(), "Login Unsuccessful!", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dear_diary, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			loginValidation();
			break;
		case R.id.button2:
			Intent i = new Intent(getApplicationContext(),DearDiarySignUp.class);
			startActivity(i);
			break;
		case R.id.textView2:
			if(TextUtils.isEmpty(t1.getText().toString()))
			Toast.makeText(DearDiary.this, "insert username first!", Toast.LENGTH_SHORT).show();
			else{
				String u_name = t1.getText().toString();
				dh = new DatabaseHelper(DearDiary.this);
				db = dh.getReadableDatabase();
				String q = "select* from SignupTable where uname=?";
				Cursor c = db.rawQuery(q, new String[]{u_name});
				if(c.moveToFirst()){
					//Bundle b = new Bundle();
					//b.putString("uname", u_name);
					//session.createLoginSession(u_name);
					String sQuestion = c.getString(c.getColumnIndex("question"));
					Intent i1 = new Intent(DearDiary.this, Password_Recovery.class);
					i1.putExtra("sQuestion", sQuestion);
					startActivity(i1);				
					
					finish();
				}
			
			}
		default:
			break;
		}
		
	}
	

}
