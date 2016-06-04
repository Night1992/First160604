package com.lx.logistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.lx.Adapter.SelectAdapter;
import com.lx.Util.BaseActivaty;
import com.lx.Util.DBUtil;
import com.lx.Util.UpdateInfo;
import com.lx.Util.selectDao;

public class SentInfoActivity extends BaseActivaty {

	private SelectAdapter selectAdapter;
	private ArrayAdapter<String> arrayAdapter;
	private ListView listView;
	private Spinner bookingSpinner;
	private Button selectOneButton;
	private DBUtil dbUtil = new DBUtil(this);
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> sentList = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> bookingsList = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> cargoList = new ArrayList<Map<String, Object>>();
	private List<String> listStrings = new ArrayList<String>();
	private String bookingsId;
	private String cargoName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sent_info);
		super.init(1);

		listView = (ListView) findViewById(R.id.sentView);
		bookingSpinner = (Spinner) findViewById(R.id.select_one_order);

		list = selectDao.selectInfo("sentLogistics",
				dbUtil.getReadableDatabase());
		bookingsList = selectDao.selectInfo("bookings",
				dbUtil.getReadableDatabase());
		cargoList = selectDao.selectInfo("cargo", dbUtil.getReadableDatabase());

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("address").toString().equals("已发送")) {
				listStrings.add(list.get(i).get("sid").toString());
				bookingsId = list.get(i).get("sid").toString();
				for (int j = 0; j < bookingsList.size(); j++) {
					if (bookingsList.get(j).get("sid").toString()
							.equals(bookingsId)) {
						cargoName = bookingsList.get(j).get("name").toString();
						for (int k = 0; k < cargoList.size(); k++) {
							if (cargoList.get(k).get("name").toString()
									.equals(cargoName)) {
								sentList.add(cargoList.get(k));
							}
						}
					}
				}
			}
		}

		String[] bookingsIdStrings = new String[listStrings.size()];
		listStrings.toArray(bookingsIdStrings);

		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listStrings);
		bookingSpinner.setAdapter(arrayAdapter);

		selectAdapter = new SelectAdapter(SentInfoActivity.this, sentList);

		listView.setAdapter(selectAdapter);

		selectOneButton = (Button) findViewById(R.id.select_one_btn);
		selectOneButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sentList.clear();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).get("address").toString().equals("已发送")
							&& list.get(i)
									.get("sid")
									.toString()
									.equals(bookingSpinner.getSelectedItem().toString())) {
						bookingsId = list.get(i).get("sid").toString();
						for (int j = 0; j < bookingsList.size(); j++) {
							if (bookingsList.get(j).get("sid").toString()
									.equals(bookingsId)) {
								cargoName = bookingsList.get(j).get("name")
										.toString();
								for (int k = 0; k < cargoList.size(); k++) {
									if (cargoList.get(k).get("name").toString()
											.equals(cargoName)) {
										sentList.add(cargoList.get(k));
									}
								}
							}
						}
					}
				}

				selectAdapter = new SelectAdapter(SentInfoActivity.this,
						sentList);
				listView.removeAllViewsInLayout();
				listView.setAdapter(selectAdapter);
			}
		});

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//onCreate(null);
	}
}
