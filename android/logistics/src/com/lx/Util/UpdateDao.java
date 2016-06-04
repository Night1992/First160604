package com.lx.Util;

import android.database.sqlite.SQLiteDatabase;

public class UpdateDao {
	
	public static void updateInfo(String sql,SQLiteDatabase db){	
		db.execSQL(sql);
	}
}
