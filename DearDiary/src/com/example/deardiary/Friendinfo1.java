package com.example.deardiary;

import java.util.Calendar;

import SessionManager.SessionManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Friendinfo1 extends Activity implements OnClickListener, OnCheckedChangeListener {
	Button save;
	//RadioGroup rb;
	EditText t1,t2,t3,t4,t5,t6;
	RadioButton r1,r2;
	int mYear, mMonth, mDay;
	String gender="";
	SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friendinfo1);
		save = (Button) findViewById(R.id.button1);
		t1 = (EditText) findViewById(R.id.editText1);
		t2 = (EditText) findViewById(R.id.editText2);
		t3 = (EditText) findViewById(R.id.editText3);
		t4 = (EditText) findViewById(R.id.editText4);
		t5 = (EditText) findViewById(R.id.editText5);
		t6 = (EditText) findViewById(R.id.editText6);
		r1 = (RadioButton) findViewById(R.id.radio1);
		r2 = (RadioButton) findViewById(R.id.radio2);
		session = new SessionManager(getApplicationContext());
		session.isLoggedIn();
		r1.setOnCheckedChangeListener(this);
		r2.setOnCheckedChangeListener(this);
		save.setOnClickListener(this);
		t3.setOnClickListener(this);
		t5.setOnClickListener(this);
		
	}
	public void saveData(){
		DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
		SQLiteDatabase db = dh.getWritableDatabase();
		String name = t1.getText().toString();
	    String addss = t2.getText().toString();
		String bday = t3.getText().toString();
		String phone = t4.getText().toString();
		String anni = t5.getText().toString();
		String mail = t6.getText().toString();
		ContentValues cv = new ContentValues();
		/*Bundle b = new Bundle();
		Intent i1 = getIntent();
		String key = i1.getStringExtra("uname");
		/*rb = (RadioGroup) findViewById(R.id.radioGroup1);
		String s1 = ((RadioButton)this.findViewById(rb.getCheckedRadioButtonId())).toString();
		cv.put("gender", s1);*/
		String key = session.getUserDetails();
		cv.put("name", name);
		cv.put("address", addss);
		cv.put("birthday", bday);
		cv.put("mobile_no", phone);
		cv.put("anniversary", anni);
		cv.put("mail", mail);
		cv.put("gender", gender);
		cv.put("key", key);
		
		if(!emailCheck()){
		db.insert("FriendsTable", null, cv);
		Toast.makeText(this, "data successfully saved", 3000).show();
		//b.putString("data1", name);
	//	session.createLoginSession(name);
		//session.createLoginSession(key);
		//b.putString("data2", key);
		Intent i = new Intent(this,Friendinfo.class);
		
		//i.putExtras(b);
		startActivity(i);
		
		}
		else
		Toast.makeText(getApplicationContext(), "fill correct info", 3000).show();
	}
	private boolean emailCheck(){
		boolean valid=false;
		String mail = t6.getText().toString();
		String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
		if(mail.isEmpty()  || !mail.matches(emailPattern)){
			t6.setError("Please enter a valid email address");
			valid = true;
		}
		return valid;
	}
	
	public void dateset(){
		
		Calendar mcurrentDate=Calendar.getInstance();
        mYear=mcurrentDate.get(Calendar.YEAR);
        mMonth=mcurrentDate.get(Calendar.MONTH);
        mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);
  DatePickerDialog mDatePicker=new DatePickerDialog(Friendinfo1.this,R.style.picker, new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
				// TODO Auto-generated method stub
				month++;
				
				t3.setText(dayOfMonth + "/" + month+ "/" + year );
				
				t5.setText(dayOfMonth + "/" + month+ "/" + year );

				 				
			}
		} , mYear, mMonth, mDay );
        mDatePicker.setTitle("Select date");                
        mDatePicker.show(); 
        // String s1 = t3.getText().toString();
    	

		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friendinfo1, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			saveData();
			break;
		case R.id.editText3:
			dateset();
			break;
		case R.id.editText5:
			dateset();
			break;
		default:
			break;
		}
		
	}
	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		switch (arg0.getId()) {
		case R.id.radio1:
			if(arg1)
				gender = "Male";
			break;
		case R.id.radio2:
			if(arg1)
				gender = "Female";
			break;
		default:
			break;
		}
		
	}

}

