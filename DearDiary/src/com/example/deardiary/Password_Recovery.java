package com.example.deardiary;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Password_Recovery extends Activity {
	FragmentManager fm;
	EditText _sQuestion,_sAnswer;
	TextView tv1;
	Button pass_recovery;
	DatabaseHelper dh;
    SQLiteDatabase db;
    String question;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password__recovery);
		//fm = getFragmentManager();
		//fm.beginTransaction().add(R.id.frame,new ChangePassButtonFrag()).commit();
		dh = new DatabaseHelper(this);

		pass_recovery = (Button) findViewById(R.id.password_recovery_button);
		_sQuestion = (EditText) findViewById(R.id.security_question);
		_sAnswer = (EditText) findViewById(R.id.security_answer);
		tv1 = (TextView) findViewById(R.id.textView1);
		Intent i = getIntent();
		 question = i.getStringExtra("sQuestion");
		_sQuestion.setText(question);
		
		pass_recovery.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(TextUtils.isEmpty(_sAnswer.getText().toString()))
					Toast.makeText(Password_Recovery.this, "Please enter answer", Toast.LENGTH_SHORT).show();
				else{
					//dh = new DatabaseHelper(getActivity());
					db = dh.getReadableDatabase();
					String q = "select answer from SignupTable where question=?";
					Cursor c = db.rawQuery(q, new String[]{question});
					if(c.moveToFirst()){
						String sAnswer = c.getString(c.getColumnIndex("answer"));
						if(sAnswer == _sAnswer.getText().toString()){
							Bundle bundle = new Bundle();
							bundle.putString("answer", sAnswer);
							Pass_Frag fragInfo = new Pass_Frag();
					        fragInfo.setArguments(bundle);
		                fm.beginTransaction().replace(R.id.frame, new Pass_Frag()).commit();
		                tv1.setText("Change Password");
		                //_sQuestion.
						}			
		               // finish();
					}
				
				}

			}
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.password__recovery, menu);
		return true;
	}

}
