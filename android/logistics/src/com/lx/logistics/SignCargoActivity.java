package com.lx.logistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.lx.Adapter.SelectAdapter;
import com.lx.Util.BaseActivaty;
import com.lx.Util.DBUtil;
import com.lx.Util.selectDao;

public class SignCargoActivity extends BaseActivaty {

	private SelectAdapter selectAdapter;
	private ArrayAdapter<String> arrayAdapter;
	private ListView listView;
	private Spinner bookingSpinner;
	private DBUtil dbUtil = new DBUtil(this);
	private Button bookingSearchButton;
	private Button signButton;
	// private EditText selectBooking;
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> unsentList = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> bookingsList = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> cargoList = new ArrayList<Map<String, Object>>();
	private List<String> bookingsIdList = new ArrayList<String>();
	private String bookingsId;
	private String cargoName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_cargo);
		super.init(3);

		listView = (ListView) findViewById(R.id.sign_cargo_view);
		bookingSearchButton = (Button) findViewById(R.id.select_cargo);
		signButton = (Button) findViewById(R.id.sign_cargo_btn);
		// selectBooking = (EditText)findViewById(R.id.select_booking);
		bookingSpinner = (Spinner) findViewById(R.id.select_booking);

		list = selectDao.selectInfo("sentLogistics",
				dbUtil.getReadableDatabase());
		for (int i = 0; i < list.size(); i++) {
			bookingsIdList.add(list.get(i).get("sid").toString());
		}
		String[] bookingStrings = new String[bookingsIdList.size()];
		bookingsIdList.toArray(bookingStrings);
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, bookingStrings);
		bookingSpinner.setAdapter(arrayAdapter);
		selectAdapter = new SelectAdapter(this, list);
		listView.setAdapter(selectAdapter);

		bookingSearchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				bookingsList.clear();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i)
							.get("sid")
							.toString()
							.equals(bookingSpinner.getSelectedItem().toString())) {
						bookingsList.add(list.get(i));
					}
				}
				selectAdapter = new SelectAdapter(SignCargoActivity.this,
						bookingsList);

				listView.setAdapter(selectAdapter);
			}
		});

		signButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String status = "已发送";
				String sql = "update sentLogistics set bookingsStatus = '"
						+ status + "' where slId = '"
						+ bookingSpinner.getSelectedItem().toString() + "'";
				dbUtil.getWritableDatabase().execSQL(sql);
				selectAdapter.notifyDataSetChanged();
				Toast.makeText(SignCargoActivity.this,
						bookingSpinner.getSelectedItem().toString() + "已发送",
						Toast.LENGTH_SHORT).show();
			}
		});

	}
}
