package com.example.util;

import java.util.HashMap;
import java.util.Map;

import android.os.Environment;

import com.example.hospital.R;

public class StaticConfig {
	public static final String IP_HOSPITAL = "http://192.168.0.101:8080";
	public static final String commomUrl = IP_HOSPITAL + "/Hospital/hospital/test.action";
	public static final String getUrl = IP_HOSPITAL + "/Hospital/hospital/hospital_list.action";
	public static final String offUrl = IP_HOSPITAL + "/Hospital/hospital/department_list.action";
	public static final String docUrl = IP_HOSPITAL + "/Hospital/hospital/doctor_list.action";
	public static final String regUrl = IP_HOSPITAL + "/Hospital/hospital/user_register.action";
	public static final String logUrl = IP_HOSPITAL + "/Hospital/hospital/user_login.action";
	public static final String eva_hos_url = IP_HOSPITAL + "/Hospital/hospital/hospitalComment_list.action";
	public static final String add_hos_url = IP_HOSPITAL + "/Hospital/hospital/hospitalComment_add.action";
	public static final String eva_off_url = IP_HOSPITAL + "/Hospital/hospital/departmentComment_list.action";
	public static final String add_off_url = IP_HOSPITAL + "/Hospital/hospital/departmentComment_add.action";
	public static final String eva_doc_url =IP_HOSPITAL + "/Hospital/hospital/doctorComment_list.action";
	public static final String add_doc_url = IP_HOSPITAL + "/Hospital/hospital/doctorComment_add.action";
	
	public static final int REG_MSG = 0;
	public static final int LOG_MSG = 1;
	public static final int HOSPITAL_MSG = 2;
	public static final int OFFICE_MSG = 3;
	public static final int DOCTOR_MSG = 4;
//	public static final int REG_MSG = 5;
	
	public static final int connectionTimeOut = 5000;
	
	//谷歌地图API 中间用","把经纬度隔开
	public static final String[] googleMapUrl = {"http://maps.google.com/maps/api/geocode/json?latlng=","&language=zh-CN&sensor=true"};
	public static final String PROVINCE_URL = "http://flash.weather.com.cn/wmaps/xml/china.xml";

	//省市对应的id
	public static final String CITYS_PROVINCE = "citys_province";
	
	//message 
	public static final int NOW_CITY_MESSAGE = 0; //当前城市  
	public static final int SELECT_CITY_MESSAGE = 1; //选择城市
	public static final int INIT_HOSPITAL = 2; //加载医院信息
	public static final int INIT_OFFICE = 3; //加载科室信息
	public static final int INIT_DOCTOR = 4;  //加载医生信息
	public static final int EVA_HOSPITAL = 5; //加载医院评价信息
	public static final int EVA_OFFICE = 6; //加载科室信息
	public static final int EVA_DOCTOR = 7; //加载医生信息
	public static final int SEARCH_MESSAGE = 8; //关键字搜索
	
	public static final String[] ORDER_STRINGS= {"级别","距离","评价"};
	
	public static final String IMAGE_PATH = Environment.getExternalStorageDirectory().getPath() + "/hospital/imgs/";
	public static final int HOSPITAO_FROM = 0;
	public static final int OFFICE_FROM = 1;
	public static final int DOCTOR_FROM = 2;
	
}
