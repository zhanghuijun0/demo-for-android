package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager {
	private static final String DATABASE_NAME = "qipei.sqlite";
	private static final int DATABASE_VERSION = 1;
	private static DatabaseManager sInstance;
	private static SQLiteDatabase sDatabase;

	public static DatabaseManager initialize(Context context) {
		if (sInstance == null) {
			sInstance = new DatabaseManager(context.getApplicationContext());
		}
		return sInstance;
	}

	public static void close() {
		if (sInstance != null && sDatabase.isOpen()) {
			sDatabase.close();
		}
		sInstance = null;
	}

	public synchronized static SQLiteDatabase getDatabase(Context context) {
		if (sDatabase == null) {
			sInstance = new DatabaseManager(context.getApplicationContext());
		}
		return sDatabase;
	}

	private DatabaseManager(Context context) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context,
				DATABASE_NAME);
		sDatabase = databaseHelper.getWritableDatabase();
	}

	/**
	 * Sqlite insert query
	 * 
	 * @param table
	 * @param values
	 * @return boolean result
	 */
	public static long insert(String table, ContentValues values) {
		return sDatabase.insert(table, null, values);
	}

	/**
	 * Sqlite update query
	 * 
	 * @param table
	 * @param values
	 * @param whereClause
	 * @return boolean result
	 */
	public static boolean update(String table, ContentValues values,
			String whereClause) {
		return sDatabase.update(table, values, whereClause, null) > 0;
	}

	/**
	 * Sqlite delete query
	 * 
	 * @param table
	 * @param whereClause
	 * @return boolean result
	 */
	public synchronized static boolean delete(String table, String whereClause) {
		return sDatabase.delete(table, whereClause, null) > 0;
	}

	/**
	 * Sqlite select query
	 * 
	 * @param table
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @param limit
	 * @return cursor
	 */
	public static Cursor select(String table, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy, String limit) {
		return sDatabase.query(table, columns, selection, selectionArgs,
				groupBy, having, orderBy, limit);
	}

	/**
	 * Sqlite select query
	 * 
	 * @param table
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @return cursor
	 */
	public static Cursor select(String table, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy) {
		return sDatabase.query(table, columns, selection, selectionArgs,
				groupBy, having, orderBy);
	}

	/**
	 * Sqlite select query
	 * 
	 * @param sql
	 * @param selectionArgs
	 * @return cursor
	 */
	public static Cursor select(String sql, String[] selectionArgs) {
		return sDatabase.rawQuery(sql, selectionArgs);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		private static final String USER = "CREATE TABLE [user] (id integer primary key autoincrement,title varchar(20),content text,time date);";
		
		public DatabaseHelper(Context context, String databaseName) {
			// calls the super constructor, requesting the default cursor
			// factory.
			super(context, databaseName, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO
			db.execSQL(USER);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// apply necessary change of database
		}

	}

	public static void clear() {
		// TODO 不考虑id自增
		String sql_user = "delete from [user] where 1=1";
		sDatabase.execSQL(sql_user);
	}
}
