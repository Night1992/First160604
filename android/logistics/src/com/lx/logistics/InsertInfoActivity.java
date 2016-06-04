package com.lx.logistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lx.Adapter.SelectAdapter;
import com.lx.Util.BaseActivaty;
import com.lx.Util.DBUtil;
import com.lx.Util.selectDao;

public class InsertInfoActivity extends BaseActivaty {
	
	private DBUtil dbUtil;
	private List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	private ListView listView;
	private SelectAdapter selectAdapter;
	private int buttonInt;
	private Button[] buttons = new Button[6];
	private Button insertbButton;

	
	
	public SelectAdapter getSelectAdapter() {
		return selectAdapter;
	}

	public void setSelectAdapter(SelectAdapter selectAdapter) {
		this.selectAdapter = selectAdapter;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_info);
		
		buttonInt = getIntent().getIntExtra("button", 0);
		super.insertInit(buttonInt);
		dbUtil = new DBUtil(this);
		listView = (ListView)findViewById(R.id.insert_list);
		//Toast.makeText(this, "---" + buttonInt , Toast.LENGTH_SHORT).show();
		
		buttons[0] = (Button) findViewById(R.id.insert_user);
		buttons[1] = (Button) findViewById(R.id.insert_cargo);
		buttons[2] = (Button) findViewById(R.id.insert_seller);
		buttons[3] = (Button) findViewById(R.id.insert_buyer);
		buttons[4] = (Button) findViewById(R.id.insert_logistics);
		buttons[5] = (Button) findViewById(R.id.insert_sentLogistics);
		
		switch (buttonInt) {
		case 0:
			list = selectDao.selectInfo("user", dbUtil.getReadableDatabase());
			selectAdapter = new SelectAdapter(this, list, "user", buttons[0]);
			listView.setAdapter(selectAdapter);
			break;
			
		case 1:
			list = selectDao.selectInfo("cargo", dbUtil.getReadableDatabase());
			selectAdapter = new SelectAdapter(this, list, "cargo", buttons[1]);
			listView.setAdapter(selectAdapter);
			break;
			
		case 2:
			list = selectDao.selectInfo("seller", dbUtil.getReadableDatabase());
			selectAdapter = new SelectAdapter(this, list, "seller", buttons[2]);
			listView.setAdapter(selectAdapter);
			break;
			
		case 3:
			list = selectDao.selectInfo("buyer", dbUtil.getReadableDatabase());
			selectAdapter = new SelectAdapter(this, list, "buyer", buttons[3]);
			listView.setAdapter(selectAdapter);
			break;
			
		case 4:
			list = selectDao.selectInfo("bookings", dbUtil.getReadableDatabase());
			selectAdapter = new SelectAdapter(this, list, "bookings", buttons[4]);
			listView.setAdapter(selectAdapter);
			break;
			
		case 5:
			list = selectDao.selectInfo("sentLogistics", dbUtil.getReadableDatabase());
			selectAdapter = new SelectAdapter(this, list, "sentLogistics", buttons[5]);
			listView.setAdapter(selectAdapter);
			break;

		default:
			break;
		}
		
		insertbButton = (Button)findViewById(R.id.insert_btn);
		insertbButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				switch (buttonInt) {
				case 0:
					Intent intent = new Intent(InsertInfoActivity.this, InsertUserActivity.class);
					startActivityForResult(intent, 0);
					break;
					
				case 1:
					Intent intent1 = new Intent(InsertInfoActivity.this, InsertCargoActivity.class);
					startActivityForResult(intent1, 0);
					break;
				
				case 2:
					Intent intent2 = new Intent(InsertInfoActivity.this, InsertSellerActivity.class);
					startActivityForResult(intent2, 0);
					break;
					
				case 3:
					Intent intent3 = new Intent(InsertInfoActivity.this, InsertBuyerActivity.class);
					startActivityForResult(intent3, 0);
					break;
					
				case 4:
					Intent intent4 = new Intent(InsertInfoActivity.this, LogisticsActivity.class);
					startActivityForResult(intent4, 0);
					break;
					
				case 5:
					Intent intent5 = new Intent(InsertInfoActivity.this, InsertSentLogisticsActivity.class);
					startActivityForResult(intent5, 0);
					break;
				default:
					break;
				}
				
			}
		});
		
	}
	
/*	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		super.insertInit(buttonInt);
	}*/
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		
		switch (resultCode) {
		case RESULT_OK:
			Bundle bundle = data.getExtras();
			//Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
			if (bundle.getString("insert").equals("user")) {
				list = selectDao.selectInfo("user", dbUtil.getReadableDatabase());
				selectAdapter = new SelectAdapter(this, list, "user", buttons[0]);
				listView.setAdapter(selectAdapter);
			}else if (bundle.getString("insert").equals("cargo")) {
				list = selectDao.selectInfo("cargo", dbUtil.getReadableDatabase());
				selectAdapter = new SelectAdapter(this, list, "cargo", buttons[1]);
				listView.setAdapter(selectAdapter);
			}else if (bundle.getString("insert").equals("seller")) {
				list = selectDao.selectInfo("seller", dbUtil.getReadableDatabase());
				selectAdapter = new SelectAdapter(this, list, "seller", buttons[2]);
				listView.setAdapter(selectAdapter);
			}else if (bundle.getString("insert").equals("buyer")) {
				list = selectDao.selectInfo("buyer", dbUtil.getReadableDatabase());
				selectAdapter = new SelectAdapter(this, list, "buyer", buttons[3]);
				listView.setAdapter(selectAdapter);
			}else if (bundle.getString("insert").equals("bookings")) {
				list = selectDao.selectInfo("bookings", dbUtil.getReadableDatabase());
				selectAdapter = new SelectAdapter(this, list, "bookings", buttons[4]);
				listView.setAdapter(selectAdapter);
			}else if (bundle.getString("insert").equals("sentLogistics")) {
				list = selectDao.selectInfo("sentLogistics", dbUtil.getReadableDatabase());
				selectAdapter = new SelectAdapter(this, list, "sentLogistics", buttons[5]);
				listView.setAdapter(selectAdapter);
			}
			break;

		case RESULT_CANCELED:
			
			break;
		default:
			break;
		}
		
	}

}
