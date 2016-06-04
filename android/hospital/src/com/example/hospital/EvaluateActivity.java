package com.example.hospital;

import com.example.util.StaticConfig;
import com.example.view.MyPopupWindow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class EvaluateActivity extends Activity implements OnClickListener {
	public static final int HOSPITAL_EVALUATE_STATUS = 0;
	public static final int OFFICE_EVALUATE_STATUS = 1;
	public static final int DOCTOR_EVALUATE_STATUS = 2;
	public static final int PHYSICAL_EVALUATE_STATUS = 3;
	
	private TextView cityTv;
	private Spinner orderSp;
	private ListView infoLv;
	private ListView orderLv;
	private TextView orderTv;
	private Button orderBtn;
	private ImageView backIv;
	
	private int evaluateStatus;  //ÆÀ¼Û×´Ì¬
	private String nowCity;
	private MyPopupWindow myPopupWindow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evaluate);
		
		initView();
		initData();
		
	}
	
	private void initView(){
		cityTv = (TextView) findViewById(R.id.city_evaluate_tv);
//		orderSp = (Spinner) findViewById(R.id.order_evaluate_sp);
		infoLv = (ListView) findViewById(R.id.info_evaluate_lv);
		myPopupWindow = new MyPopupWindow(this, R.layout.pop_listview);
		orderTv = (TextView) findViewById(R.id.order_evaluate_tv);
		orderBtn = (Button) findViewById(R.id.order_evaluate_btn);
		backIv = (ImageView) findViewById(R.id.back_evaluate_iv);
		orderBtn.setOnClickListener(this);
		orderTv.setOnClickListener(this);
		backIv.setOnClickListener(this);
	}
	
	@SuppressLint("InlinedApi")
	private void initData(){
		evaluateStatus = getIntent().getExtras().getInt("evaluateStatus");
		nowCity = getIntent().getExtras().getString("nowCity");
		Log.d("lixiao", "nowCity" + nowCity + " | status : " + evaluateStatus);
		
		cityTv.setText(nowCity);
		if (evaluateStatus == HOSPITAL_EVALUATE_STATUS) {
//			orderSp.setVisibility(View.VISIBLE);
//			ArrayAdapter<String> orderAdapter = new ArrayAdapter<>(
//					EvaluateActivity.this,
//					android.R.layout.test_list_item,
//					StaticConfig.ORDER_STRINGS);
//			orderSp.setAdapter(orderAdapter);
			
			orderBtn.setVisibility(View.VISIBLE);
			orderTv.setVisibility(View.VISIBLE);
			orderLv = myPopupWindow.getListView();
			ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<>(
					EvaluateActivity.this, android.R.layout.simple_list_item_1,
					StaticConfig.ORDER_STRINGS);
			orderLv.setAdapter(mArrayAdapter);
			
		}else {
			orderSp.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.order_evaluate_btn:
			myPopupWindow.showAsDropDown(v);
			break;
			
		case R.id.order_evaluate_tv:
			myPopupWindow.showAsDropDown(v);
			break;
			
		case R.id.back_evaluate_iv:
			finish();

		default:
			break;
		}
		
	}
	
	
}
