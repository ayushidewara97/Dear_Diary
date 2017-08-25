package com.example.deardiary;

import SessionManager.SessionManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class UpdateFriends extends Activity implements OnCheckedChangeListener {
EditText _update_name,_update_address,_update_bday,_update_mobile,_update_anni,_update_email,_update_gender;
Button _update;
SessionManager session;
DatabaseHelper dh;
SQLiteDatabase db;
String s1,s2,s3,s4,s5,s6,s7;
int _id;
//RadioButton _male,_female;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_friends);
		session = new SessionManager(getApplicationContext());
		session.checkLogin();
		Intent i = getIntent();
		_id = i.getIntExtra("hide", 0);
		 s1 = i.getStringExtra("name");
		 s2 = i.getStringExtra("address");
		 s3 = i.getStringExtra("birthday");
		 s4 = i.getStringExtra("mobile_no");
		 s5 = i.getStringExtra("anniversary");
		 s6 = i.getStringExtra("mail");
		 s7 = i.getStringExtra("gender");

		_update_name = (EditText) findViewById(R.id.update_name_et);
		_update_address = (EditText) findViewById(R.id.update_address_et);
		_update_bday = (EditText) findViewById(R.id.update_bday_et);
		_update_mobile = (EditText) findViewById(R.id.update_mobile_et);
		_update_anni = (EditText) findViewById(R.id.update_anni_et);
		_update_email = (EditText) findViewById(R.id.update_email_et);
		_update_gender = (EditText) findViewById(R.id.update_gender_et);
		_update = (Button) findViewById(R.id.update_button);
		
	/*	_male = (RadioButton) findViewById(R.id.update_male);
		_female = (RadioButton) findViewById(R.id.update_female);
		
		_male.setOnCheckedChangeListener(this);
		_female.setOnCheckedChangeListener(this);*/
		_update_name.setText(s1);
		_update_address.setText(s2);
		_update_bday.setText(s3);
		_update_mobile.setText(s4);
		_update_anni.setText(s5);
		_update_email.setText(s6);
		_update_gender.setText(s7);
		
		_update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
				builder.setMessage("Do you really want to update this friend's information ?");
				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						 dh=new DatabaseHelper(getApplicationContext());
						 db = dh.getWritableDatabase();
						ContentValues cv = new ContentValues();
						if(_id>0){
						if(checkUpdate()){
							cv.put("fname", _update_name.getText().toString());
							cv.put("faddress", _update_address.getText().toString());
							cv.put("fbday", _update_bday.getText().toString());
							cv.put("fmobile", _update_mobile.getText().toString());
							cv.put("fanni", _update_anni.getText().toString());
							cv.put("femail", _update_email.getText().toString());
							cv.put("fgender", _update_gender.getText().toString());

						}
						int ans=db.update("FriendsTable", cv,"fid=?", new String[]{Integer.toString(_id)});
						if(ans==1)
						{
							Toast.makeText(UpdateFriends.this, "Information Updated", Toast.LENGTH_SHORT).show();
							
						}
					}
						else
						{
							Toast.makeText(UpdateFriends.this, "User Not Present", Toast.LENGTH_SHORT).show();
							
						}
						Intent i=new Intent(UpdateFriends.this,Friendinfo.class);
						startActivity(i);

					}

					
				});
				builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// Action for 'NO' Button
						dialog.cancel();
					}
				});

				AlertDialog alert = builder.create();
				alert.setTitle("Delete");
				alert.show();
				
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_friends, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean checkUpdate() {
		// TODO Auto-generated method stub
		if(_update_name.getText().equals(null)){
			_update_name.setError("enter valid name");
			return false;
			}
		if(_update_address.getText().equals(null)){
			_update_address.setError("enter valid address");
			return false;
			}
		if(_update_bday.getText().equals(null)){
				_update_bday.setError("enter valid b'day");
				return false;
				}
		if(_update_mobile.getText().equals(null)){
					_update_mobile.setError("enter valid mobile no.");
					return false;
					}
		if(_update_anni.getText().equals(null)){
						_update_anni.setError("enter valid anniversary");
						return false;
						}
		if(_update_email.getText().equals(null)){
			_update_email.setError("enter valid email address");
			return false;
			}
		return true;
	}

}
