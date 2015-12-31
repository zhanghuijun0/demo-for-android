package com.example.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "test_db.sqlite";
	private static final String TABLE_NAME = "user_table";
	private static final int VERSION = 1;

	public DBHelper(Context context) {
		// TODO Auto-generated constructor stub
		this(context, DATABASE_NAME, null, VERSION);
	}

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table "
				+ TABLE_NAME
				+ "(id integer primary key autoincrement,name varchar(20),content text,time date);";
		db.execSQL(sql);
		db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql = "drop table ifexists diary";
		db.execSQL(sql);
		this.onCreate(db);
	}

}
