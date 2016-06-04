package com.example.hospital;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.adapter.DoctorAdapter;
import com.example.adapter.DoctorInfoBean;
import com.example.adapter.OfficeAdapter;
import com.example.adapter.OfficeInfoBean;
import com.example.util.StaticConfig;
import com.example.util.Staticmethod;

public class OfficeActivity extends Activity {
	private static final String TAG = "OfficeActivity";

	private ListView officeLv;
	private OfficeAdapter officeAdapter;
	private DoctorAdapter doctorAdapter;
	private ImageView backIv;
	
	private List<OfficeInfoBean> datas;
	private List<DoctorInfoBean> doctorDatas;
	private String hospitalName;
	private int from;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_office);
		
		initView();
		initData();
		initEvent();
		
	}

	private void initEvent() {
		officeLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent intent = new Intent(OfficeActivity.this, InfomationActivity.class);
				if (from == StaticConfig.HOSPITAO_FROM) {
					intent.putExtra("from", StaticConfig.OFFICE_FROM);
					intent.putExtra("logo_path", datas.get(position).getPath());
					intent.putExtra("name", datas.get(position).getName());
					intent.putExtra("level", datas.get(position).getDoctor());
				}else if (from == StaticConfig.OFFICE_FROM) {
					intent.putExtra("from", StaticConfig.DOCTOR_FROM);
					intent.putExtra("name", doctorDatas.get(position).getName());
					intent.putExtra("job", doctorDatas.get(position).getJob());
					intent.putExtra("evaluate_level", doctorDatas.get(position).getEvaluateLevel());
					intent.putExtra("path", doctorDatas.get(position).getPath());
				}
				startActivity(intent);
			}
		});
		
		backIv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	private void initData() {
		from = getIntent().getIntExtra("from", 0);
		
		hospitalName = getIntent().getStringExtra("hospital_name");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				if (from == StaticConfig.HOSPITAO_FROM) {
					datas = Staticmethod.getOfficeInfo(hospitalName, handler);
				}else if (from == StaticConfig.OFFICE_FROM){
					doctorDatas = Staticmethod.getDoctorInfo(hospitalName, handler);
				}
			}
		}).start();
		
	}

	private void initView() {
		officeLv = (ListView) findViewById(R.id.office_office_lv);
		backIv = (ImageView) findViewById(R.id.back_top_iv);
	}
	
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case StaticConfig.INIT_OFFICE:
				officeAdapter = new OfficeAdapter(OfficeActivity.this, datas);
				officeLv.setAdapter(officeAdapter);
				break;
				
			case StaticConfig.INIT_DOCTOR:
				doctorAdapter = new DoctorAdapter(OfficeActivity.this, doctorDatas);
				officeLv.setAdapter(doctorAdapter);
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
		
	};

}
