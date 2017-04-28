package com.example.deardiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	public static final String db_name = "DGDIARY.db";
	public static final int db_version = 1;
	public static final String tbl_name = "SignupTable";
	public static final String tblname = "FriendsTable";
	public DatabaseHelper(Context context) {
		
		super(context, db_name, null, db_version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "create table "+tbl_name+"(sid integer primary key autoincrement,name text,uname text,password text,question text,answer text)";
		db.execSQL(query);
		String q = "create table "+tblname+"(fid integer primary key autoincrement,name text,address text,birthday text,mobile_no text,anniversary text,mail text,gender text,key text)";
		db.execSQL(q);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase sq, int arg1, int arg2) {
		
		
	}

}
