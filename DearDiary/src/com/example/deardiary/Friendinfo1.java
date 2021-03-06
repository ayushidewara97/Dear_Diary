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
import android.text.InputFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
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
		t4.setFilters( new InputFilter[] { new InputFilter.LengthFilter(10)});

		t5 = (EditText) findViewById(R.id.editText5);
		t6 = (EditText) findViewById(R.id.editText6);
		r1 = (RadioButton) findViewById(R.id.radio1);
		r2 = (RadioButton) findViewById(R.id.radio2);
		session = new SessionManager(getApplicationContext());
		session.checkLogin();
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
		
		String key = session.getUserDetails();
		cv.put("name", name);
		cv.put("address", addss);
		cv.put("birthday", bday);
		cv.put("mobile_no", phone);
		cv.put("anniversary", anni);
		cv.put("mail", mail);
		cv.put("gender", gender);
		cv.put("key", key);
		
		if(!emailCheck() && !phoneField() && check()){
		db.insert("FriendsTable", null, cv);
		Toast.makeText(this, "data successfully saved", Toast.LENGTH_SHORT).show();
		
		Intent i = new Intent(this,Friendinfo.class);
		i.putExtra("name", name);
		i.putExtra("address", addss);
		i.putExtra("birthday", bday);
		i.putExtra("mobile_no", phone);
		i.putExtra("anniversary", anni);
		i.putExtra("mail", mail);
		i.putExtra("gender", gender);

		startActivity(i);
		
		}
		else
		Toast.makeText(getApplicationContext(), "fill correct info", Toast.LENGTH_SHORT).show();
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
	private boolean phoneField(){
		boolean valid=false;
		if(t4.getText().toString().isEmpty() && t4.getText().toString().length()<10){
			t4.setError("enter valid contactnumber");
			valid = true;
		}
		return valid;
	}
	private boolean check(){
		if(t1.getText().toString().isEmpty())
			t1.setError("Please enter valid name");
		if(t2.getText().toString().isEmpty())
			t2.setError("Please enter valid address");
		if(t3.getText().toString().isEmpty())
			t3.setError("Please enter valid birth-date");
		if(t5.getText().toString().isEmpty())
			t5.setError("Please enter valid anniversary-date");
		return true;
		
	}
	
	public void dateBirthday(){
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

				 				
			}
		} , mYear, mMonth, mDay );
        mDatePicker.setTitle("Select date");                
        mDatePicker.show(); 
        // String s1 = t3.getText().toString();
    	

		
	}
	public void dateAnniversary(){
		Calendar mcurrentDate=Calendar.getInstance();
	      mYear=mcurrentDate.get(Calendar.YEAR);
	      mMonth=mcurrentDate.get(Calendar.MONTH);
	      mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);	
		
  DatePickerDialog mDatePicker=new DatePickerDialog(Friendinfo1.this,R.style.picker, new OnDateSetListener() {
	  
			@Override
			public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
				// TODO Auto-generated method stub
				month++;
				t5.setText(dayOfMonth + "/" + month+ "/" + year );

				 				
			}
		} , mYear, mMonth, mDay );
        mDatePicker.setTitle("Select date");                
        mDatePicker.show(); 		
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
			dateBirthday();
			break;
		case R.id.editText5:
			dateAnniversary();
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

