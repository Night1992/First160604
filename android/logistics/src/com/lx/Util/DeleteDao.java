package com.lx.Util;

import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.Toast;

public class DeleteDao {

	public static void deleteUser(Context context, String table, SQLiteDatabase db,Map<String, Object> map) {
		String sql = "delete from " + table + " where " + table + "Id=?";
		if (table.equals("sentLogistics")) {
			sql = "delete from " + table + " where slId=?";
		}
		db.execSQL(sql,new Object[] { map.get("sid")});
		Toast.makeText(context, map.get("name").toString() + "É¾³ý³É¹¦",
				Toast.LENGTH_SHORT).show();
	}
}
