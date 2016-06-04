package com.example.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.hospital.R;

/**
 * 位置管理类
 * @author lx
 *
 */
public class LocationUtil {

	public static String getNowCity(Context context, final Handler handler) {
		final String[] nowCity = new String[1];
		LocationClient locationClient;
		locationClient = new LocationClient(context);
		LocationClientOption locationClientOption = new LocationClientOption();
		locationClientOption.setOpenGps(true);
		locationClientOption.setCoorType("bd09ll");       //设置返回值的坐标类型。
		locationClientOption.setPriority(LocationClientOption.NetWorkFirst);
		locationClientOption.setProdName("hospital");
		locationClientOption.setAddrType("all");
//		locationClientOption.setScanSpan(5000);
		
		locationClient.setLocOption(locationClientOption);
		
		locationClient.registerLocationListener(new BDLocationListener() {
			
			@Override
			public void onReceiveLocation(BDLocation location) {
				nowCity[0] = location.getCity();
				if (nowCity[0].contains("市")) {
					nowCity[0] = nowCity[0].substring(0, nowCity[0].indexOf("市"));
				}
				Message msg = handler.obtainMessage(StaticConfig.NOW_CITY_MESSAGE,
						nowCity[0]);
				handler.sendMessage(msg);
				Log.d("lixiao", "location : " + nowCity[0] + "," + location.getCity());
			}
		});
		
		locationClient.start();
		locationClient.requestLocation();
//		Message msg = handler.obtainMessage(StaticConfig.NOW_CITY_MESSAGE,
//				nowCity[0]);
//		handler.sendMessage(msg);
//		handler.sendEmptyMessage(0);
		return nowCity[0];
	}

	/**
	 * 从资源中拿到所有省份名称
	 * @param context
	 * @return 得到省份数组；单数为名称，双数为Key值（如：province[0] = "beijing_province_item";province[1] = "北京"）
	 */
	public static String[] getProvince(Context context){
		return context.getResources().getStringArray(R.array.province_item);
	}
	
	/**
	 * 根据省份名称返回相应的城市列表
	 * @param context
	 * @param province
	 * @return
	 */
	public static String[] getCitys(Context context, String province){
		SharedPreferences sp = context.getSharedPreferences(StaticConfig.CITYS_PROVINCE, Activity.MODE_PRIVATE);
		int provinceId = sp.getInt(province, 0);
		
		if (provinceId != 0) {
			return context.getResources().getStringArray(provinceId);
		}else {
			return null;
		}
	}
	
	/**
	 * 把资源中的String数组转换成Map泛型的List
	 * @param s
	 * @return
	 */
	public static List<Map<String, String>> transStringToListMap(String[] s){
		List<Map<String, String>> results = new ArrayList<>();
		
		for (int i = 0; i < s.length; i += 2) {
			Map<String, String> map = new HashMap<>();
			map.put(s[i], s[i + 1]);
			results.add(map);
		}
		
		return results;
	}
	
	/**
	 * 加载省份对应的城市列表
	 * @param context
	 */
	public static void initCitysProvince(Context context){
		String[] provinces = context.getResources().getStringArray(R.array.province_item);
		SharedPreferences sp = context.getSharedPreferences(StaticConfig.CITYS_PROVINCE, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		
		for (String p : provinces) {
			if ("北京".equals(p)) {
				editor.putInt(p, R.array.beijin_province_item);
			}else if ("天津".equals(p)) {
				editor.putInt(p, R.array.tianjin_province_item);
			}else if ("河北".equals(p)) {
				editor.putInt(p, R.array.hebei_province_item);
			}else if ("山西".equals(p)) {
				editor.putInt(p, R.array.shanxi1_province_item);
			}else if ("内蒙古".equals(p)) {
				editor.putInt(p, R.array.neimenggu_province_item);
			}else if ("辽宁".equals(p)) {
				editor.putInt(p, R.array.liaoning_province_item);
			}else if ("吉林".equals(p)) {
				editor.putInt(p, R.array.jilin_province_item);
			}else if ("黑龙江".equals(p)) {
				editor.putInt(p, R.array.heilongjiang_province_item);
			}else if ("上海".equals(p)) {
				editor.putInt(p, R.array.shanghai_province_item);
			}else if ("江苏".equals(p)) {
				editor.putInt(p, R.array.jiangsu_province_item);
			}else if ("浙江".equals(p)) {
				editor.putInt(p, R.array.zhejiang_province_item);
			}else if ("安徽".equals(p)) {
				editor.putInt(p, R.array.anhui_province_item);
			}else if ("福建".equals(p)) {
				editor.putInt(p, R.array.fujian_province_item);
			}else if ("江西".equals(p)) {
				editor.putInt(p, R.array.jiangxi_province_item);
			}else if ("山东".equals(p)) {
				editor.putInt(p, R.array.shandong_province_item);
			}else if ("河南".equals(p)) {
				editor.putInt(p, R.array.henan_province_item);
			}else if ("湖北".equals(p)) {
				editor.putInt(p, R.array.hubei_province_item);
			}else if ("湖南".equals(p)) {
				editor.putInt(p, R.array.hunan_province_item);
			}else if ("广东".equals(p)) {
				editor.putInt(p, R.array.guangdong_province_item);
			}else if ("广西".equals(p)) {
				editor.putInt(p, R.array.guangxi_province_item);
			}else if ("海南".equals(p)) {
				editor.putInt(p, R.array.henan_province_item);
			}else if ("重庆".equals(p)) {
				editor.putInt(p, R.array.chongqing_province_item);
			}else if ("四川".equals(p)) {
				editor.putInt(p, R.array.sichuan_province_item);
			}else if ("贵州".equals(p)) {
				editor.putInt(p, R.array.guizhou_province_item);
			}else if ("云南".equals(p)) {
				editor.putInt(p, R.array.yunnan_province_item);
			}else if ("西藏".equals(p)) {
				editor.putInt(p, R.array.xizang_province_item);
			}else if ("陕西".equals(p)) {
				editor.putInt(p, R.array.shanxi2_province_item);
			}else if ("甘肃".equals(p)) {
				editor.putInt(p, R.array.gansu_province_item);
			}else if ("青海".equals(p)) {
				editor.putInt(p, R.array.qinghai_province_item);
			}else if ("宁夏".equals(p)) {
				editor.putInt(p, R.array.ningxia_province_item);
			}else if ("新疆".equals(p)) {
				editor.putInt(p, R.array.xinjiang_province_item);
			}else if ("香港".equals(p)) {
				editor.putInt(p, R.array.hongkong_province_item);
			}else if ("澳门".equals(p)) {
				editor.putInt(p, R.array.aomen_province_item);
			}else if ("台湾".equals(p)) {
				editor.putInt(p, R.array.taiwan_province_item);
			}
		}
		
		editor.commit();
	}
	
	
}
