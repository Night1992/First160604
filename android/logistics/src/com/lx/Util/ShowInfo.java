package com.lx.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import com.lx.Adapter.SelectAdapter;
public class ShowInfo {
	private static Context context;
	private static ListView listView;
	private static SelectAdapter selectAdapter;
	private static List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	private static DBUtil db;
	private Integer flag;
	
	public static void show(Context context,ListView listView,Integer flag){
		db = new DBUtil(context);
		
		if (flag == 1) {
			list = selectDao.selectInfo("cargo",db.getReadableDatabase());
			Toast.makeText(context, list.size(), Toast.LENGTH_SHORT).show();
			selectAdapter = new SelectAdapter(context, list);
			listView.setAdapter(selectAdapter);
		}
	}	
}
