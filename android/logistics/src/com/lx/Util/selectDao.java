package com.lx.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class selectDao {
	
	public static List<Map<String, Object>> selectInfo(String table,SQLiteDatabase db){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from " + table;
		Cursor cursor = db.rawQuery(sql, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sid", cursor.getInt(0));
			if (table.equals("bookings")) {
				map.put("name", cursor.getString(2));
				map.put("phone", cursor.getString(3));
				map.put("address", cursor.getString(4));
			}else {
				map.put("name", cursor.getString(1));
				map.put("phone", cursor.getString(2));
				map.put("address", cursor.getString(3));
			}
			if (map.get("sid") != null && map.get("name") != null
					&& map.get("phone") != null && map.get("address") != null) {
				list.add(map);
			}
			cursor.moveToNext();
		}
		cursor.close();

		return list;
	}
	
	//²éÕÒµ¥¸ö
	public static Map<String, Object> selectOne(String table,SQLiteDatabase db,String col ,String flag){
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "select * from " + table + " where " + col + " = '" + flag + "'";
		//String sqlString = "select * from buyer where buyerName='dggu'";
		Cursor cursor = db.rawQuery(sql, null);
		int count = cursor.getCount();
		cursor.moveToFirst();
		/*System.out.println(cursor.getInt(0)+"----------------");
		System.out.println(cursor.toString());*/
		while (!cursor.isAfterLast()) {
			map.put(table + "Id", cursor.getInt(0));
			map.put(table + "Name", cursor.getString(1));
			map.put(table + "Address", cursor.getString(2));
			map.put(table + "Phone", cursor.getString(3));
		
			cursor.moveToNext();
		}
		cursor.close();
		
		return map;
	}
	
	/*public static List<Map<String, Object>> selectUser(SQLiteDatabase db) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from user";
		Cursor cursor = db.rawQuery(sql, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sid", cursor.getInt(0));
			map.put("name", cursor.getString(1));
			map.put("phone", cursor.getString(2));
			map.put("address", cursor.getString(3));
			if (map.get("sid") != null && map.get("name") != null
					&& map.get("phone") != null && map.get("address") != null) {
				list.add(map);
			}
			cursor.moveToNext();
		}
		cursor.close();

		return list;
	}

	public static List<Map<String, Object>> selectCargo(SQLiteDatabase db) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from cargo";
		Cursor cursor = db.rawQuery(sql, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sid", cursor.getInt(0));
			map.put("name", cursor.getString(1));
			map.put("phone", cursor.getString(2));
			map.put("address", cursor.getString(3));
			if (map.get("sid") != null && map.get("name") != null
					&& map.get("phone") != null && map.get("address") != null) {
				list.add(map);
			}
			cursor.moveToNext();
		}
		cursor.close();

		return list;
	}
	
	public static List<Map<String, Object>> selectOwner(SQLiteDatabase db) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from buyer";
		Cursor cursor = db.rawQuery(sql, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sid", cursor.getInt(0));
			map.put("name", cursor.getString(1));
			map.put("phone", cursor.getString(2));
			map.put("address", cursor.getString(3));
			if (map.get("sid") != null && map.get("name") != null
					&& map.get("phone") != null && map.get("address") != null) {
				list.add(map);
			}
			cursor.moveToNext();
		}
		cursor.close();

		return list;
	}*/
}
