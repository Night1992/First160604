package com.lx.logistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lx.Adapter.SelectAdapter;
import com.lx.Util.BaseActivaty;
import com.lx.Util.DBUtil;
import com.lx.Util.selectDao;

public class BaseInfoActivity extends BaseActivaty {

	private ListView listView;
	private SelectAdapter selectAdapter;
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private DBUtil db = new DBUtil(this);
	private Button[] buttons = new Button[5];
	private String flagString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_info);

		buttons[0] = (Button) findViewById(R.id.cargo_info_btn);
		buttons[1] = (Button) findViewById(R.id.owner_info_btn);
		buttons[2] = (Button) findViewById(R.id.seller_info_btn);
		buttons[3] = (Button) findViewById(R.id.logistics_info_btn);
		buttons[4] = (Button) findViewById(R.id.order_info_btn);

		super.init(0);
		listView = (ListView) findViewById(R.id.userView);
		
		// 显示货物信息的button监听
		buttons[0].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				flagString = "货物";
				list = selectDao.selectInfo("cargo", db.getReadableDatabase());
				selectAdapter = new SelectAdapter(BaseInfoActivity.this, list,"cargo",buttons[0]);
				// listView.removeAllViewsInLayout();
				listView.setAdapter(selectAdapter);
			}
		});

		// 显示买家信息的button监听
		buttons[1].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				flagString = "买家";
				list = selectDao.selectInfo("buyer", db.getReadableDatabase());
				selectAdapter = new SelectAdapter(BaseInfoActivity.this, list,"buyer",buttons[1]);
				// listView.removeAllViewsInLayout();
				listView.setAdapter(selectAdapter);
			}
		});

		// 显示卖家信息的button监听
		buttons[2].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				flagString = "卖家";
				list = selectDao.selectInfo("seller", db.getReadableDatabase());
				selectAdapter = new SelectAdapter(BaseInfoActivity.this, list,"seller",buttons[2]);
				// listView.removeAllViewsInLayout();
				listView.setAdapter(selectAdapter);
			}
		});

		// 显示物流信息的button监听
		buttons[3].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				flagString = "物流";
				list = selectDao.selectInfo("sentLogistics", db.getReadableDatabase());
				selectAdapter = new SelectAdapter(BaseInfoActivity.this, list);
				// listView.removeAllViewsInLayout();
				listView.setAdapter(selectAdapter);
			}
		});

		// 显示订单信息的button监听
		buttons[4].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				flagString = "订单";
				list = selectDao.selectInfo("bookings", db.getReadableDatabase());
				selectAdapter = new SelectAdapter(BaseInfoActivity.this, list);
				// listView.removeAllViewsInLayout();
				listView.setAdapter(selectAdapter);
			}
		});

		// ShowInfo.show(this, listView, 1);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case RESULT_OK:
			Bundle bundle = data.getExtras();
			flagString = bundle.getString("textStr");
			if (flagString.equals("货物")) {
				buttons[0].performClick();
			}else if (flagString.equals("买家")) {
				buttons[1].performClick();
			}else {
				buttons[2].performClick();
			}
			break;

		default:
			break;
		}
	}

}
