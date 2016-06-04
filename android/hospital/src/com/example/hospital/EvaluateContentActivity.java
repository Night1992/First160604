package com.example.hospital;

import java.util.HashMap;
import java.util.Map;

import com.example.util.HttpConnectionUtil;
import com.example.util.StaticConfig;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EvaluateContentActivity extends Activity implements OnClickListener, OnTouchListener {
	private static final String TAG = "EvaluateContentActivity";
	
	private ImageView iv1; //总体评价
	private ImageView iv2; //就医环境
	private ImageView iv3; //诊疗流程
	private ImageView iv4; //诚心收费
	private ImageView iv5; //便民设置
	private ImageView iv6; //诊室布局
	private EditText edt; //我的评价
	private Button submit; //提交
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private TextView tv4;
	private TextView tv5;
	private TextView tv6;
	
	private int e1;
	private int e2;
	private int e3;
	private int e4;
	private int e5;
	private int e6;
	private int from;
	private String username;
	private String name;
	
	private int imageViewWidth = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_evaluate);
		
		initView();
		initData();
		initEvent();
		
	}

	private void initData() {
		from = getIntent().getIntExtra("from", 0);
		name = getIntent().getStringExtra("name");
		SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
		username = sp.getString("userName", "未知");
		
		if (from == StaticConfig.OFFICE_FROM) {
			tv2.setText("诊疗资源");
			tv3.setText("科室环境");
			tv4.setText("患者隐私");
			tv5.setText("护理服务");
			tv6.setText("病床数量");
		}else if (from == StaticConfig.DOCTOR_FROM) {
			tv2.setText("专业水平");
			tv3.setText("诊疗规范");
			tv4.setText("服务态度");
			tv5.setText("预约诊疗");
			tv6.setText("合理用药");
		}
	}

	private void initEvent() {
		iv1.setOnTouchListener(this);
		iv2.setOnTouchListener(this);
		iv3.setOnTouchListener(this);
		iv4.setOnTouchListener(this);
		iv5.setOnTouchListener(this);
		iv6.setOnTouchListener(this);
		submit.setOnClickListener(this);
	}

	private void initView() {
		iv1 = (ImageView) findViewById(R.id.evaluate_view_iv1);
		iv2 = (ImageView) findViewById(R.id.evaluate_view_iv2);
		iv3 = (ImageView) findViewById(R.id.evaluate_view_iv3);
		iv4 = (ImageView) findViewById(R.id.evaluate_view_iv4);
		iv5 = (ImageView) findViewById(R.id.evaluate_view_iv5);
		iv6 = (ImageView) findViewById(R.id.evaluate_view_iv6);
		edt = (EditText) findViewById(R.id.evaluate_view_et);
		submit = (Button) findViewById(R.id.evaluate_view_btn);
		
		tv1 = (TextView) findViewById(R.id.evaluate_view_tv1);
		tv2 = (TextView) findViewById(R.id.evaluate_view_tv2);
		tv3 = (TextView) findViewById(R.id.evaluate_view_tv3);
		tv4 = (TextView) findViewById(R.id.evaluate_view_tv4);
		tv5 = (TextView) findViewById(R.id.evaluate_view_tv5);
		tv6 = (TextView) findViewById(R.id.evaluate_view_tv6);
		
		imageViewWidth = iv1.getHeight();
	}

	@Override
	public void onClick(View v) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("username", username);
		if (from == StaticConfig.HOSPITAO_FROM) {
			data.put("hospitalName", name);
			data.put("hcOverallLevel", e1 + "");
			data.put("hcEnvironmentLevel", e2 + "");
			data.put("hcTreatmentProcessLevel", e3 + "");
			data.put("hcCostLevel", e4 + "");
			data.put("hcConvenienceDeviceLevel", e5 + "");
			data.put("hcClinicLayoutLevel", e6 + "");
			data.put("hcOverall", edt.getText().toString());
			HttpConnectionUtil.startHttpConnection(StaticConfig.add_hos_url, data, null);
		}else if (from == StaticConfig.OFFICE_FROM) {
			data.put("departmentName", name);
			data.put("decOveralllevel", e1 + "");
			data.put("decTreatmentResourcesLevel", e2 + "");
			data.put("decDepartmentEnvironmentLevel", e3 + "");
			data.put("decPatientPrivacyLevel", e4 + "");
			data.put("decNursingServiceLevel", e5 + "");
			data.put("decNumOfHospitalBedLevel", e6 + "");
			data.put("decOverall", edt.getText().toString());
			HttpConnectionUtil.startHttpConnection(StaticConfig.add_off_url, data, null);
		}else if (from == StaticConfig.DOCTOR_FROM) {
			data.put("doctorName", name);
			data.put("dcOverallLevel", e1 + "");
			data.put("dcProfessionalLevel", e2 + "");
			data.put("dcMedicalStandardLevel", e3 + "");
			data.put("dcServiceAttitudeLevel", e4 + "");
			data.put("dcOrderedServiceLevel", e5 + "");
			data.put("dcMedicationLevel", e6 + "");
			data.put("dcOverall", edt.getText().toString());
			HttpConnectionUtil.startHttpConnection(StaticConfig.add_doc_url, data, null);
		}
//		data.put("hcEnvironmentLevel", e2 + "");
//		data.put("hcTreatmentProcessLevel", e3 + "");
//		data.put("hcCostLevel", e4 + "");
//		data.put("hcConvenienceDeviceLevel", e5 + "");
//		data.put("hcClinicLayoutLevel", e6 + "");
//		data.put("hcOverall", edt.getText().toString());
		Intent in = new Intent();
		in.putExtra("notify", true);
		setResult(RESULT_OK, in);
		finish();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (v instanceof ImageView) {
			switch (v.getId()) {
			case R.id.evaluate_view_iv1:
				e1 = getNumberByTouch(v, event.getX());
				break;

			case R.id.evaluate_view_iv2:
				e2 = getNumberByTouch(v, event.getX());
				break;

			case R.id.evaluate_view_iv3:
				e3 = getNumberByTouch(v, event.getX());
				break;

			case R.id.evaluate_view_iv4:
				e4 = getNumberByTouch(v, event.getX());
				break;

			case R.id.evaluate_view_iv5:
				e5 = getNumberByTouch(v, event.getX());
				break;

			case R.id.evaluate_view_iv6:
				e6 = getNumberByTouch(v, event.getX());
				break;

			default:
				break;
			}
		}
		return false;
	}
	
	private int getNumberByTouch(View v, float x){
		int resId = R.drawable.pp_0;
		imageViewWidth = v.getWidth();
		int part = imageViewWidth / 5;
		Log.i(TAG, "res------" + x + "------" + imageViewWidth + "------" + (x / part));
		switch ((int) (x / part)) {
		case 0:
			resId = R.drawable.pp_1;
			break;
			
		case 1:
			resId = R.drawable.pp_2;
			break;

		case 2:
			resId = R.drawable.pp_3;
			break;

		case 3:
			resId = R.drawable.pp_4;
			break;

		case 4:
			resId = R.drawable.pp_5;
			break;

		case 5:
			resId = R.drawable.pp_5;
			break;

		default:
			break;
		}
		((ImageView) v).setImageResource(resId);
		return (int) (x / part);
	}
}
