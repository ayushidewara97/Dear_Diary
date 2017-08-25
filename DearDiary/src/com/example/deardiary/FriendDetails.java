package com.example.deardiary;

import java.util.ArrayList;
import SessionManager.SessionManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class FriendDetails extends Activity {
	TextView t1,t2,t3,t4,t5,t6,t7;
	DatabaseHelper dh;
	SQLiteDatabase db;
	//String friendname,faddress,fbirth,fmob,fanni,fmail,fgen;
	SessionManager session;
	ArrayList<String> n;
	ArrayList<String> a;
	ArrayList<String> b;
	ArrayList<String> m;
	ArrayList<String> ani;
	ArrayList<String> ml;
	ArrayList<String> g;
	String s1,s2,s3,s4,s5,s6,s7;
	int hide;
	String fkey;
	int id_To_Update = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend_details);
		//Intent i = getIntent();
	      Bundle extras = getIntent().getExtras(); 
		//Integer hide = i.getIntExtra("friend_id",0);
	     
		t1 = (TextView) findViewById(R.id.textView1);
		t2 = (TextView) findViewById(R.id.textView2);
		t3 = (TextView) findViewById(R.id.textView3);
		t4 = (TextView) findViewById(R.id.textView4);
		t5 = (TextView) findViewById(R.id.textView5);
		t6 = (TextView) findViewById(R.id.textView6);
		t7 = (TextView) findViewById(R.id.textView7);
		 session = new SessionManager(getApplicationContext());
			session.checkLogin();
		 n = new ArrayList<String>();
			a = new ArrayList<String>();
			b = new ArrayList<String>();
			m = new ArrayList<String>();
			ani = new ArrayList<String>();
			ml = new ArrayList<String>();
			g = new ArrayList<String>();
		  dh = new DatabaseHelper(getApplicationContext());
			//SQLiteDatabase db = dh.getReadableDatabase();
			 if(extras !=null) {
				 hide = extras.getInt("friend_id"); 
				 fkey = extras.getString("key");
			if(hide>0){
				
	            Cursor c = dh.getData(hide,fkey);
	            id_To_Update = hide;
			//String st = "select name,address,birthday,mobile_no,anniversary,mail,gender from FriendsTable where fid="+hide+"";
			//Cursor c = db.rawQuery(st, null);
	       // Cursor singleRow  = db.rawQuery("SELECT name,address,birthday,mobile_no,anniversary,mail,gender FROM "+  DatabaseHelper.tblname + " WHERE fid=?" , new String[]{Integer.toString(hide)});
			if(c != null && c.moveToFirst()){
	       // if(singleRow.moveToFirst()){
			//c.moveToPosition(hide);
				n.add(c.getString(0));
				a.add(c.getString(1));
				b.add(c.getString(2));
				m.add(c.getString(3));
				ani.add(c.getString(4));
				ml.add(c.getString(5));
				g.add(c.getString(6));		
			
			

            if (!c.isClosed())  {
            	c.close();
            }
			 s1 = n.toString().replaceAll("\\[|\\]", "").replaceAll(", ","\t");
			 s2 = a.toString().replaceAll("\\[|\\]", "").replaceAll(", ","\t");
			 s3 = b.toString().replaceAll("\\[|\\]", "").replaceAll(", ","\t");
			 s4 = m.toString().replaceAll("\\[|\\]", "").replaceAll(", ","\t");
			 s5 = ani.toString().replaceAll("\\[|\\]", "").replaceAll(", ","\t");
			 s6 = ml.toString().replaceAll("\\[|\\]", "").replaceAll(", ","\t");
			 s7 = g.toString().replaceAll("\\[|\\]", "").replaceAll(", ","\t");
		
		t1.setText("Friend name: "+s1);
		t2.setText("Address: "+s2);
		t3.setText("Birthday: "+s3);
		t4.setText("Mobile no.: "+s4);
		t5.setText("Anniversary: "+s5);
		t6.setText("Email Address: "+s6);
		t7.setText("Gender: "+s7);
			}
			
			}
			 }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friend_details, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.update:
			
			Intent i = new Intent(FriendDetails.this,UpdateFriends.class);
			i.putExtra("hide", hide);
			i.putExtra("name", s1);
			i.putExtra("address", s2);
			i.putExtra("birthday", s3);
			i.putExtra("mobile_no", s4);
			i.putExtra("anniversary", s5);
			i.putExtra("mail", s6);
			i.putExtra("gender", s7);
			startActivity(i);
			return true;
			
		case R.id.delete:
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Do you realy want to delete this friend's information ?");
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					 dh=new DatabaseHelper(getApplicationContext());
					// db=dh.getWritableDatabase();
					//int ans=db.delete("FriendsTable", "fid=? AND key=?", new String[]{Integer.toString(hide),fkey});
					//if(ans==1)
					//{
	                  dh.deleteFriend(id_To_Update,fkey);
						Toast.makeText(FriendDetails.this, "User Deleted", Toast.LENGTH_SHORT).show();
						Intent i=new Intent(FriendDetails.this,Friendinfo.class);
						startActivity(i);
					//}
					//else
					//{
						//Toast.makeText(FriendDetails.this, "User Not Present", Toast.LENGTH_SHORT).show();
						
					//}
					

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

				
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
