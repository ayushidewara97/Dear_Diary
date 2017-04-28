package com.example.deardiary;

import java.util.ArrayList;

import SessionManager.SessionManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Friendinfo extends Activity implements OnClickListener, OnItemClickListener {
	Button addinfo,search;
	ListView lv;
	EditText t1;
	ArrayList<String> n;
	ArrayList<String> a;
	ArrayList<String> b;
	ArrayList<String> m;
	ArrayList<String> ani;
	ArrayList<String> ml;
	ArrayList<String> g;
	String name,key;
	SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friendinfo);
		addinfo = (Button) findViewById(R.id.button1);
		search = (Button) findViewById(R.id.button2);
		t1 = (EditText) findViewById(R.id.editText1);
		lv = (ListView) findViewById(R.id.listView1);	
		session = new SessionManager(getApplicationContext());
		session.isLoggedIn();
		key = session.getUserDetails();
		/*Intent i1 = getIntent();
		key = i1.getStringExtra("uname");*/
		//t1.setText(key);
		addinfo.setOnClickListener(this);
		n = new ArrayList<String>();
		a = new ArrayList<String>();
		b = new ArrayList<String>();
		m = new ArrayList<String>();
		ani = new ArrayList<String>();
		ml = new ArrayList<String>();
		g = new ArrayList<String>();


		DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
		SQLiteDatabase db = dh.getReadableDatabase();
		String st = "select name from FriendsTable where key=?";
		Cursor c = db.rawQuery(st, new String[]{key});
		while(c.moveToNext()){
			n.add(c.getString(0));
			/*a.add(c.getString(1));
			b.add(c.getString(2));
			m.add(c.getString(3));
			ani.add(c.getString(4));
			ml.add(c.getString(5));
			g.add(c.getString(6));*/


		}
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,n);
		lv.setAdapter(ad);
		lv.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friendinfo, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			
			Intent i = new Intent(getApplicationContext(), Friendinfo1.class);
			//i.putExtra("uname",key);
			startActivity(i);
			break;
		case R.id.button2:
			Intent i1 = new Intent(getApplicationContext(),FriendDetails.class);
			startActivity(i1);
			break;

		default:
			break;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int index, long arg3) {
		
	String friendname = ((TextView)v.findViewById(R.id.textView1)).getText().toString();
	/*String faddress = ((TextView)v.findViewById(R.id.textView2)).getText().toString();
	String fbirth = ((TextView)v.findViewById(R.id.textView3)).getText().toString();
	String fmob = ((TextView)v.findViewById(R.id.textView4)).getText().toString();
	String fanni = ((TextView)v.findViewById(R.id.textView5)).getText().toString();
	String fmail = ((TextView)v.findViewById(R.id.textView6)).getText().toString();
	String fgen = ((TextView)v.findViewById(R.id.textView7)).getText().toString();*/

		Intent i = new Intent(this,FriendDetails.class);
		i.putExtra("friendname", friendname);
		/*i.putExtra("faddress", faddress); 
		i.putExtra("fbirth", fbirth); 
		i.putExtra("fmob", fmob); 
		i.putExtra("fanni", fanni); 
		i.putExtra("fmail", fmail); 
		i.putExtra("fgen", fgen); */

		startActivity(i);
	}

	
	

}
