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

public class InsertSentLogisticsActivity extends BaseActivaty {

	DBUtil db = new DBUtil(this);
	private Spinner bookingSpinner;
	private Spinner userSpinner;
	private Button sentButton;
	private List<String> listBookingStrings = new ArrayList<String>();
	private List<String> listUserStrings = new ArrayList<String>();
	private List<Map<String, Object>> bookingList = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
	private ArrayAdapter<String> bookingAdapter;
	private ArrayAdapter<String> userAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_sent_logistics);
		//super.insertInit(5);

		sentButton = (Button) findViewById(R.id.sent_cargo_btn);
		bookingSpinner = (Spinner) findViewById(R.id.booking_number);
		userSpinner = (Spinner) findViewById(R.id.sent_user);

		bookingList = selectDao
				.selectInfo("bookings", db.getReadableDatabase());
		userList = selectDao.selectInfo("user", db.getReadableDatabase());

		for (int i = 0; i < bookingList.size(); i++) {
			listBookingStrings.add(bookingList.get(i).get("sid").toString());
		}
		for (int i = 0; i < userList.size(); i++) {
			listUserStrings.add(userList.get(i).get("name").toString());
		}

		String[] bookingStrings = new String[listBookingStrings.size()];
		String[] userStrings = new String[listUserStrings.size()];
		listBookingStrings.toArray(bookingStrings);
		listUserStrings.toArray(userStrings);

		bookingAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, bookingStrings);
		userAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, userStrings);

		bookingSpinner.setAdapter(bookingAdapter);
		userSpinner.setAdapter(userAdapter);

		sentButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("bookingsId", bookingSpinner.getSelectedItem()
						.toString());
				map.put("userId", userSpinner.getSelectedItem().toString());
				map.put("bookingsStatus", "Œ¥«© ’");

				InsertDao.insertSentLogistics(InsertSentLogisticsActivity.this,
						db.getWritableDatabase(), map);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("insert", "sentLogistics");
				intent.putExtras(bundle);
				setResult(RESULT_OK, intent);
				//selectAdapter.notifyDataSetChanged();
				finish();
			}
		});

	}
}
