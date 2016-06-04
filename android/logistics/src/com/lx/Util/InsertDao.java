package com.lx.Util;

import java.util.HashMap;
import java.util.Map;

import com.lx.logistics.InsertUserActivity;
import com.lx.logistics.LogisticsActivity;

import android.R.string;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class InsertDao {
	public static void insertUser(Context context, SQLiteDatabase db,
			Map<String, Object> map) {
		/*
		 * String hqlString = "drop table user"; db.execSQL(hqlString);
		 * hqlString = "create table user(" + "userId integer primary key," +
		 * "userName text," + "userPhone text," + "userLevel integer)";
		 * db.execSQL(hqlString);
		 */
		String sql = "insert into user (userName,userPhone,userLevel) values (?,?,?)";
		db.execSQL(
				sql,
				new Object[] { map.get("userName"), map.get("userPhone"),
						map.get("userLevel") });
		Toast.makeText(context, map.get("userName").toString() + "新增成功",
				Toast.LENGTH_SHORT).show();
	}

	public static void insertCargo(Context context, SQLiteDatabase db,
			Map<String, Object> map) {
		String sql = "insert into cargo (cargoName,cargoPrice,cargoWeight) values (?,?,?)";
		db.execSQL(
				sql,
				new Object[] { map.get("cargoName"), map.get("cargoPrice"),
						map.get("cargoWeight") });
		Toast.makeText(context, map.get("cargoName").toString() + "新增成功",
				Toast.LENGTH_SHORT).show();
	}

	public static void insertSeller(Context context, SQLiteDatabase db,
			Map<String, Object> map) {
		/*
		 * String hqlString = "drop table seller"; db.execSQL(hqlString);
		 * hqlString = "create table seller(" + "sellerId integer primary key,"
		 * + "sellerName text," + "sellerAddress text," + "sellerPhone text)";
		 * db.execSQL(hqlString);
		 */
		String sql = "insert into seller (sellerAddress,sellerName,sellerPhone) values (?,?,?)";
		db.execSQL(sql,
				new Object[] { map.get("sellerAddress"), map.get("sellerName"),
						map.get("sellerPhone") });
		Toast.makeText(context, map.get("sellerName").toString() + "新增成功",
				Toast.LENGTH_SHORT).show();
	}

	public static void insertBuyer(Context context, SQLiteDatabase db,
			Map<String, Object> map) {
		/*
		 * String hqlString = "drop table buyer"; db.execSQL(hqlString);
		 */
		/*
		 * String hqlString = "create table buyer(" +
		 * "buyerId integer primary key," + "buyerName text," +
		 * "buyerAddress text," + "buyerPhone text)"; db.execSQL(hqlString);
		 */

		String sql = "insert into buyer (buyerName,buyerPhone,buyerAddress) values (?,?,?)";

		db.execSQL(
				sql,
				new Object[] { map.get("buyerName"), map.get("buyerPhone"),
						map.get("buyerAddress") });

		Toast.makeText(context, map.get("buyerName").toString() + "新增成功",
				Toast.LENGTH_SHORT).show();
	}

	public static void insertLogistics(Context context, SQLiteDatabase db,
			Map<String, Object> map) {
		//Map<String, Object> values = new HashMap<String, Object>();
		String hqlString;
	/*hqlString ="drop table bookings"; db.execSQL(hqlString);
		 hqlString = "create table bookings (" +
		 "bookingsId integer  primary key," + "userId text," + "cargoId text,"
		 + "sellerId text," + "buyerId text)" ; db.execSQL(hqlString);*/
		 
		String sql = "insert into bookings (userId,cargoId,sellerId,buyerId) values (?,?,?,?)";
		db.execSQL(sql, new Object[]{map.get("userId"),map.get("cargoId"),map.get("sellerId"),map.get("buyerId")});
		//hqlString = "insert into bookings (userId,cargoId,sellerId,buyerId) values ('lx','笔记本','百度','林月如')";
		//db.execSQL(hqlString);
		Toast.makeText(
				context,
				map.get("buyerId").toString() + map.get("sellerId").toString()
						+ map.get("userId").toString()
						+ map.get("cargoId").toString(), Toast.LENGTH_SHORT)
				.show();
	}
	
	public static void insertSentLogistics(Context context,SQLiteDatabase db,Map<String, Object> map){
		
		/*String sql = "create table sentLogistics(" +
				"slId integer primary key," +
				"bookingsId text," +
				"userId text," +
				"bookingsStatus text)";
		db.execSQL(sql);*/
		String sql = "insert into sentLogistics (bookingsId,userId,bookingsStatus) values (?,?,?)";
		db.execSQL(sql, new Object[]{map.get("bookingsId"),map.get("userId"),map.get("bookingsStatus")});
		Toast.makeText(context, map.get("bookingsId").toString() + "已发货", Toast.LENGTH_SHORT).show();
	} 
	
}
