package com.lx.logistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.lx.Util.BaseActivaty;
import com.lx.Util.DBUtil;
import com.lx.Util.InsertDao;
import com.lx.Util.selectDao;
import com.lx.qrcode.DoQrCode;

public class LogisticsActivity extends BaseActivaty {
	private DBUtil db = new DBUtil(this);
	private Spinner userSpinner;
	private Spinner cargoSpinner;
	private Spinner fromSpinner;
	private Spinner toSpinner;
	private Button insertButton;
	private Button resetButton;
	private List<String> listUserString = new ArrayList<String>();
	private List<String> listCargoString = new ArrayList<String>();
	private List<String> listBuyerString = new ArrayList<String>();
	private List<String> listSellerString = new ArrayList<String>();
	// private List<String> listOrderString = new ArrayList<String>();
	// private String[] strings = new String[] { "a", "b", "c", "d" };
	private ArrayAdapter<String> userAdapter;
	private ArrayAdapter<String> cargoAdapter;
	private ArrayAdapter<String> buyerAdapter;
	private ArrayAdapter<String> sellerAdapter;
	private List<Map<String, Object>> listUser = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> listcargo = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> listBuyer = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> listSeller = new ArrayList<Map<String, Object>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logistics);
		//super.insertInit(4);

		userSpinner = (Spinner) findViewById(R.id.user_id);
		cargoSpinner = (Spinner) findViewById(R.id.order_cargo);
		toSpinner = (Spinner) findViewById(R.id.to_area);
		fromSpinner = (Spinner) findViewById(R.id.from_area);

		listUser = selectDao.selectInfo("user", db.getReadableDatabase());
		listcargo = selectDao.selectInfo("cargo", db.getReadableDatabase());
		listBuyer = selectDao.selectInfo("buyer", db.getReadableDatabase());
		listSeller = selectDao.selectInfo("seller", db.getReadableDatabase());

		for (int i = 0; i < listUser.size(); i++) {
			listUserString.add(listUser.get(i).get("name").toString());
		}
		for (int i = 0; i < listcargo.size(); i++) {
			listCargoString.add(listcargo.get(i).get("name").toString());
		}
		for (int i = 0; i < listBuyer.size(); i++) {
			listBuyerString.add(listBuyer.get(i).get("name").toString());
		}
		for (int i = 0; i < listSeller.size(); i++) {
			listSellerString.add(listSeller.get(i).get("name").toString());
		}

		String[] sUser = new String[listUserString.size()];
		String[] scargo = new String[listCargoString.size()];
		String[] sBuyer = new String[listBuyerString.size()];
		String[] sSeller = new String[listSellerString.size()];
		listUserString.toArray(sUser);
		listCargoString.toArray(scargo);
		listBuyerString.toArray(sBuyer);
		listSellerString.toArray(sSeller);

		userAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, sUser);
		cargoAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, scargo);
		buyerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, sBuyer);
		sellerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, sSeller);

		userSpinner.setAdapter(userAdapter);
		cargoSpinner.setAdapter(cargoAdapter);
		toSpinner.setAdapter(buyerAdapter);
		fromSpinner.setAdapter(sellerAdapter);

		insertButton = (Button) findViewById(R.id.insert_logistics_btn);
		insertButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("userId", userSpinner.getSelectedItem().toString());
				map.put("cargoId", cargoSpinner.getSelectedItem().toString());
				map.put("sellerId", fromSpinner.getSelectedItem().toString());
				map.put("buyerId", toSpinner.getSelectedItem().toString());

				InsertDao.insertLogistics(LogisticsActivity.this,
						db.getWritableDatabase(), map);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("insert", "logistics");
				intent.putExtras(bundle);
				setResult(RESULT_OK, intent);
				//selectAdapter.notifyDataSetChanged();
				finish();
			}
		});

		// 生成二维码的button监听
		resetButton = (Button) findViewById(R.id.reset_logistics_btn);
		resetButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] dataStr = new String[] {
						userSpinner.getSelectedItem().toString(),
						cargoSpinner.getSelectedItem().toString(),
						fromSpinner.getSelectedItem().toString(),
						toSpinner.getSelectedItem().toString() };
				/*Intent intent = new Intent(LogisticsActivity.this,
						QrCodeActivity.class);
				intent.putExtra("dataStr", dataStr);
				startActivity(intent);*/
				DoQrCode dq = new DoQrCode(dataStr);
				dq.createQrCode(LogisticsActivity.this);
			}
		});

	}
}
