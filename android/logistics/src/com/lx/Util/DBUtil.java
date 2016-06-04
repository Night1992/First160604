package com.lx.Util;

import android.R.integer;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUtil extends SQLiteOpenHelper {

	private static String DBName = "user.db";
	private static int version = 1;
	
	public DBUtil(Context context) {
		super(context, DBName, null, version);
	}
	
	public DBUtil(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
			String sql = "create table logistics (" +
							 "id integer  primary key," +
							 "userId integer," +
							 "cargoId integer," +
							 "sellerId integer" +
							 "orderId integer)" ;
			db.execSQL(sql);
			
			 sql = "create table user(" +
					"userId integer primary key," +
					"userName text," +
					"userPhone text," +
					"userLevel integer)";
			db.execSQL(sql);
			
			sql = "create table cargo(" +
					"cargoId integer primary key," +
					"cargoName text," +
					"cargoPrice text," +
					"cargoWeight text)";
			db.execSQL(sql);
			/*sql = "drop table seller";
			db.execSQL(sql);
			sql = "drop table buyer";
			db.execSQL(sql);*/
			sql = "create table seller(" +
					"sellerId integer primary key," +
					"sellerName text," +
					"sellerAddress text," +
					"sellerPhone text)";
			db.execSQL(sql);
			sql = "create table buyer(" +
					"buyerId integer primary key," +
					"buyerName text," +
					"buyerAdress text," +
					"buyerPhone text)";
			db.execSQL(sql);
			
			sql = "create table bookings (" +
		 "bookingsId integer  primary key," + "userId text," + "cargoId text,"
		 + "sellerId text" + "buyerId text)";
			db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stubString sql = "drop table news";
		String sql = "create table logistics (" +
				 "id integer  primary key," +
				 "userId integer," +
				 "cargoId integer," +
				 "sellerId integer" +
				 "orderId integer)" ;
		db.execSQL(sql);
		
		sql = "create table user(" +
				"userId integer primary key," +
				"userName text," +
				"userPhone text," +
				"userLevel integer)";
		db.execSQL(sql);
		
		sql = "create table cargo(" +
				"cargoId integer primary key," +
				"cargoName text," +
				"cargoPrice text," +
				"cargoWeight text)";
		db.execSQL(sql);
		/*sql = "drop table seller";
		db.execSQL(sql);
		sql = "drop table buyer";
		db.execSQL(sql);*/
		sql = "create table seller(" +
				"sellerId integer primary key," +
				"sellerName text," +
				"sellerAddress text," +
				"sellerPhone text)";
		db.execSQL(sql);
		sql = "create table buyer(" +
				"buyerId integer primary key," +
				"buyerName text," +
				"buyerAdress text," +
				"buyerPhone text)";
		db.execSQL(sql);
		
		sql = "create table bookings (" +
				 "bookingsId integer  primary key," + "userId text," + "cargoId text,"
				 + "sellerId text" + "buyerId text)";
					db.execSQL(sql);
	}

}
