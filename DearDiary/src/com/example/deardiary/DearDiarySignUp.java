package com.example.deardiary;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DearDiarySignUp extends Activity {
	Button createbutton;
	EditText t1,t2,t3,t4,t5;
	ProgressBar p;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dear_diary_sign_up);
		createbutton = (Button) findViewById(R.id.button1);
		
		t1 = (EditText) findViewById(R.id.editText1);
		t2 = (EditText) findViewById(R.id.editText2);
		t3 = (EditText) findViewById(R.id.editText3);
		t4 = (EditText) findViewById(R.id.editText4);
		t5 = (EditText) findViewById(R.id.editText5);
		createbutton.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				signup();
			}			
		});
	}
	private void signup() {
		DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
		SQLiteDatabase db = dh.getWritableDatabase();
		String full_name = t1.getText().toString();
	    String u_name = t2.getText().toString();
		String pass_word = t3.getText().toString();
		String que = t4.getText().toString();
		String ans = t5.getText().toString();
	//	if(!validate()){
		//	onSignUpFailed();
		//	return;
		//}
		ContentValues cv = new ContentValues();
		cv.put("name", full_name);
		cv.put("uname", u_name);
		cv.put("password", pass_word);
		cv.put("question", que);
		cv.put("answer", ans);
		db.insert("SignupTable", null, cv);
		Toast.makeText(this, "data successfully saved", Toast.LENGTH_LONG).show();
		Intent i = new Intent(getApplicationContext(), DearDiary.class);
		startActivity(i);

	}
	/*	
		create.setEnabled(false);
		final ProgressDialog p = new ProgressDialog(getApplicationContext(),R.style.AppBaseTheme);
		p.setIndeterminate(true);
		p.setMessage("Creating Account...");
		p.show();
		new Thread(){
			public void run(){
				
				try {
					onSignUpSuccess();
					sleep(3000);
					p.dismiss();
				//	Bundle b = new Bundle();
				//	b.putString("uname", u_name);
					Intent i = new Intent(getApplicationContext(), DearDiary.class);
				//	i.putExtras(b);
					startActivity(i);
					finish();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}.start(); 
		}
	public void onSignUpFailed(){
		Toast.makeText(getApplicationContext(), "SignUp Failed!",Toast.LENGTH_LONG).show();
		create.setEnabled(true);
	}
	public void onSignUpSuccess(){
		Toast.makeText(getApplicationContext(), "SignUp Succeed!", Toast.LENGTH_LONG).show();
		create.setEnabled(true);
		
	}*/
	//public boolean validate(){
	//	boolean valid = true;
	//	return true;
		
	//}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dear_diary_sign_up, menu);
		return true;
	}

}
