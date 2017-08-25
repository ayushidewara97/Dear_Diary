package com.example.deardiary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	public static final String db_name = "aDIARY.db";
	public static final int db_version = 1;
	public static final String tbl_name = "SignupTable";
	public static final String tblname = "FriendsTable";
	public static final String bankTable = "BankInfoTable";

	public DatabaseHelper(Context context) {
		
		super(context, db_name, null, db_version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "create table "+tbl_name+"(sid integer primary key autoincrement,name text,uname text,password text,question text,answer text)";
		db.execSQL(query);
		
		String q = "create table "+tblname+"(fid integer primary key autoincrement,name text,address text,birthday text,mobile_no text,anniversary text,mail text,gender text,key text)";
		db.execSQL(q);
		
		String q1 = "create table "+bankTable+"(fid integer primary key autoincrement,name text,account_no text,bank_name text,location text,key text1)";
		db.execSQL(q1);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase sq, int arg1, int arg2) {
		
		
	}
	
	public Cursor getData(int id,String key) {
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select name,address,birthday,mobile_no,anniversary,mail,gender from FriendsTable where fid=? AND key=?", new String[]{Integer.toString(id),key} );
	      return res;
	   }
	
	public Integer deleteFriend (Integer id,String key) {
	      SQLiteDatabase db = this.getWritableDatabase();
	      return db.delete("FriendsTable", 
	      "fid = ? AND key = ?", 
	      new String[] { Integer.toString(id),key });
	   }

}
