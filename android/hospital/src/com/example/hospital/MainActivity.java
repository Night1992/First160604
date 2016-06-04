package com.example.hospital;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.adapter.HospitalInfoAdapter;
import com.example.adapter.HospitalInfoBean;
import com.example.util.LocationUtil;
import com.example.util.StaticConfig;
import com.example.util.Staticmethod;

public class MainActivity extends Activity implements OnClickListener{
	private TextView nowCityTv;
	private Spinner provinceSn;
	private Spinner citySn;
	private TextView searchTv;
	private ImageView hospitalIv;
	private ImageView officeIv;
	private ImageView doctorIv;
	private ImageView physicalIv;
	private ListView hospitaLv;
	private EditText searchEdt;

	private ArrayAdapter<String> provinceArrayAdapter;
	private ArrayAdapter<String> cityArrayAdapter;
	private HospitalInfoAdapter hospitalInfoAdapter;
	private MyHandler myHandler;
	
	private String[] provinces;
	private String[] citys;
	private String nowCity;
	private String selectProvince;
	private String selectCity;
	private List<HospitalInfoBean> datas;
	private List<HospitalInfoBean> searchdatas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);  //去除标题栏
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initDatas();
		initEvents();

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void initView() {
		nowCityTv = (TextView) findViewById(R.id.nowCity_hospital_tv);
		provinceSn = (Spinner) findViewById(R.id.province_hospital_sn);
		citySn = (Spinner) findViewById(R.id.city_hospital_sn);
		searchTv = (TextView) findViewById(R.id.search_hospital_tv);
		hospitaLv = (ListView) findViewById(R.id.hospital_main_lv);
		searchEdt = (EditText) findViewById(R.id.search_main_et);
	}

	private void initDatas() {
		Staticmethod.createPath(null);
		LocationUtil.initCitysProvince(MainActivity.this);      //初始化省份城市列表
		myHandler = new MyHandler();
		nowCity = LocationUtil.getNowCity(MainActivity.this, myHandler);
		Log.d("lixiao", "now city : " + nowCity);
		this.nowCityTv.setText(nowCity);
		
		provinces = LocationUtil.getProvince(MainActivity.this);
		citys = LocationUtil.getCitys(MainActivity.this, provinces[0]);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				datas = Staticmethod.initInfo(myHandler);
			}
		}).start();
		
		
		provinceArrayAdapter = new ArrayAdapter<>(
				MainActivity.this,
				/*android.R.layout.simple_spinner_dropdown_item*/R.layout.item_spinner, provinces);
		cityArrayAdapter = new ArrayAdapter<>(MainActivity.this,
				/*android.R.layout.simple_spinner_dropdown_item*/R.layout.item_spinner2, citys);
//		hospitalInfoAdapter = new HospitalInfoAdapter(MainActivity.this, datas);
//		hospitaLv.setAdapter(hospitalInfoAdapter);
		provinceSn.setAdapter(provinceArrayAdapter);
		citySn.setAdapter(cityArrayAdapter);
	}
	
	private void initEvents(){
		provinceSn.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				citys = LocationUtil.getCitys(MainActivity.this, provinces[position]);
				cityArrayAdapter = new ArrayAdapter<>(MainActivity.this,
						/*android.R.layout.simple_spinner_dropdown_item*/R.layout.item_spinner, citys);
				citySn.setAdapter(cityArrayAdapter);
				selectProvince = (String) provinceSn.getSelectedItem();
				searchdatas = Staticmethod.searchName(0, provinces[position], datas);
				myHandler.sendEmptyMessage(StaticConfig.SELECT_CITY_MESSAGE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		citySn.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				selectCity = (String) citySn.getSelectedItem();
				searchdatas = Staticmethod.searchName(1, selectCity, datas);
				Message msg = myHandler.obtainMessage(
						StaticConfig.SELECT_CITY_MESSAGE, selectProvince + " "
								+ selectCity);
				myHandler.sendMessage(msg);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		this.nowCityTv.setOnClickListener(this);
		hospitaLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent intent = new Intent(MainActivity.this, InfomationActivity.class);
				intent.putExtra("from", StaticConfig.HOSPITAO_FROM);
				intent.putExtra("logo_res", searchdatas.get(position).getLogoRes());
				intent.putExtra("name", searchdatas.get(position).getName());
				intent.putExtra("level", searchdatas.get(position).getLevel());
				intent.putExtra("public_praise_res", searchdatas.get(position).getPublicPraiseRes());
				intent.putExtra("environment", searchdatas.get(position).getEnvironment());
				intent.putExtra("facility", searchdatas.get(position).getFacility());
				startActivity(intent);
			}
		});
		
		searchEdt.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				Log.i("search", "afterTextChanged-----------");
				if (null != searchEdt.getText() && !"".equals(searchEdt.getText().toString())) {
					searchdatas = Staticmethod.searchName(searchEdt.getText().toString(), datas);
					Log.i("search", "afterTextChanged1-----------" + searchdatas.size());
				}else {
					searchdatas = datas;
				}
				myHandler.sendEmptyMessage(StaticConfig.SEARCH_MESSAGE);
			}
		});
	}

	@Override
	public void onClick(View v) {
		Intent in = new Intent(MainActivity.this, EvaluateActivity.class);
		in.putExtra("nowCity", nowCityTv.getText());
		switch (v.getId()) {
		case R.id.nowCity_hospital_tv:  //点击当前城市
			searchTv.setText(nowCityTv.getText());
			Staticmethod.searchName(1, nowCityTv.getText().toString(), datas);
			myHandler.sendEmptyMessage(StaticConfig.SELECT_CITY_MESSAGE);
			break;

		default:
			break;
		}
	}
	
	private class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case StaticConfig.NOW_CITY_MESSAGE:
				if (null == msg.obj || "".equals(msg.obj.toString())) {
					nowCity = "无法获取城市";
					nowCityTv.setText(nowCity);
				}else {
					nowCity = msg.obj.toString();
					nowCityTv.setText(nowCity);
					Log.d("lixiao", "msg : " + msg.obj.toString());
				}
				break;
				
			case StaticConfig.SELECT_CITY_MESSAGE:
				searchTv.setText(selectProvince + " " + selectCity);
				hospitalInfoAdapter = new HospitalInfoAdapter(MainActivity.this, searchdatas);
				hospitaLv.setAdapter(hospitalInfoAdapter);
				hospitalInfoAdapter.notifyDataSetChanged();
				break;
				
			case StaticConfig.INIT_HOSPITAL:
				searchdatas = datas;
				hospitalInfoAdapter = new HospitalInfoAdapter(MainActivity.this, searchdatas);
				hospitaLv.setAdapter(hospitalInfoAdapter);
				break;
				
			case StaticConfig.SEARCH_MESSAGE:
				hospitalInfoAdapter = new HospitalInfoAdapter(MainActivity.this, searchdatas);
				hospitaLv.setAdapter(hospitalInfoAdapter);
				hospitalInfoAdapter.notifyDataSetChanged();
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	}
}
