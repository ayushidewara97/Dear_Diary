 package com.example.deardiary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import SessionManager.SessionManager;


public class DearDiary extends Activity implements OnClickListener {
	Button supButton,loginButton;
	EditText t1,t2;
    SessionManager session;
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dear_diary);
		session = new SessionManager(getApplicationContext());
		session.isLoggedIn();
		supButton = (Button) findViewById(R.id.button2);
		loginButton = (Button) findViewById(R.id.button1);
		t1 = (EditText) findViewById(R.id.editText1);
		t2 = (EditText) findViewById(R.id.editText2);
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
			//Bundle b = new Bundle();
			//b.putString("uname", u_name);
			session.createLoginSession(u_name);
			Intent i1 = new Intent(getApplicationContext(), Firstpage.class);
			//i1.putExtra("uname",u_name);
			startActivity(i1);
			finish();
		}
		else{
			Toast.makeText(getApplicationContext(), "Login Unsuccessful!", 3000).show();
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

		default:
			break;
		}
		
	}
	

}
