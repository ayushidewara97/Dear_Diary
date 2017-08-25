package com.example.deardiary;

import java.util.ArrayList;

import SessionManager.SessionManager;
import adapters.BankAdapter1;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BankInfo1 extends Activity implements OnItemClickListener {
	Button add_bankinfo;
	ListView lv1;
	AutoCompleteTextView t1;
	SessionManager session;
	String key;
	ArrayList<String> bank_n;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bank_info1);
		session = new SessionManager(getApplicationContext());
		session.checkLogin();
		key = session.getUserDetails();
		lv1 = (ListView) findViewById(R.id.bank_infolistView);
		t1 = (AutoCompleteTextView) findViewById(R.id.bank_autoCompleteTextView1);
		add_bankinfo = (Button) findViewById(R.id.bank_addbutton);
		add_bankinfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), BankInfo.class);
				startActivity(i);
				
			}
		});
		bank_n = new ArrayList<String>();

		DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
		SQLiteDatabase db = dh.getReadableDatabase();
		String st = "select name from BankInfoTable where key=?";
		Cursor c = db.rawQuery(st, new String[]{key});
		while(c.moveToNext()){
			bank_n.add(c.getString(0));
			
		}
	
		
		BankAdapter1 bd = new BankAdapter1(this, R.layout.model, bank_n);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,bank_n);
		t1.setAdapter(adapter);
		t1.setThreshold(1);
		t1.setTextColor(Color.RED);
		lv1.setAdapter(bd);
		lv1.setOnItemClickListener(this); 
		t1.setOnTouchListener(new OnTouchListener() {
			@Override
			  public boolean onTouch(View v, MotionEvent event) {
			        
		            if(event.getAction() == MotionEvent.ACTION_UP) {
		                if(event.getRawX() >= (t1.getRight() - t1.getTotalPaddingRight())) {
		                    // your action here event.getRawX() >= txtsearch.getRight() - txtsearch.getTotalPaddingRight()
		                	String bank_uname = t1.getText().toString();
		                	Toast.makeText(getApplicationContext(), "this is working properly", Toast.LENGTH_SHORT).show();

		            		Intent i1 = new Intent(getApplicationContext(), BankDetails.class);
		            		i1.putExtra("bank_uname", bank_uname);
		            		startActivity(i1);
		                 return true;
		                }
		            }
		            return false;
		        }

				
		    });	
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bank_info1, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
		String bank_uname = ((TextView)v.findViewById(R.id.textView1)).getText().toString();
		

		Intent i = new Intent(this,FriendDetails.class);
		i.putExtra("bank_uname", bank_uname);
		startActivity(i);		
	}


}
