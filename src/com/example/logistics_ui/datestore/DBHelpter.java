package com.example.logistics_ui.datestore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelpter extends SQLiteOpenHelper {

	private static final String dbName = "logistic.db";
	private static final String createSql = "create table if not exists LogisticInfo( "
			+ LogisticColumns._ID
			+ " integer primary key autoincrement,"
			+ LogisticColumns.LOGISTIC_COMPANY
			+ " text,"
			+ LogisticColumns.LOGISTIC_NO
			+ " text,"
			+ LogisticColumns.TIME + " text)";

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DBHelpter(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	/**
	 * @param context
	 */
	public DBHelpter(Context context) {
	      super(context, dbName, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(createSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
