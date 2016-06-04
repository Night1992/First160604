package com.moxiu.phonefriend;

import java.util.List;
import java.util.Map;

import com.moxiu.phoneAdapter.PhoneAdapter;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private Button searchBtn;
	private Button insertBtn;
	private ExpandableListView ealv;
	
	private PhoneFriendData pfd;
	//private PhoneAdapter phoneAdapter;
	private List<Map<String, String>> phoneNames;
	private List<List<String>> phoneNumbers;
	private List<String> phoneEmails;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		initDate();
	}

	private void initDate() {
		phoneNames = pfd.getPhoneNames();
		phoneNumbers = pfd.getPhoneNumbers(phoneNames);
		phoneEmails = pfd.getPhoneEmails(phoneNames);
	}

	private void init() {
		searchBtn = (Button) findViewById(R.id.search_phone_btn);
		insertBtn = (Button) findViewById(R.id.insert_phone_btn);
		
		
		pfd = new PhoneFriendData(this);
		insertBtn.setOnClickListener(this);
		
		searchBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		PhoneAdapter phoneAdapter = new PhoneAdapter(this, phoneNames, phoneNumbers, phoneEmails);
		View resultDialog = getLayoutInflater().inflate(R.layout.dialog_result, null);
		ealv = (ExpandableListView) resultDialog.findViewById(R.id.phone_ealv);
		ealv.setAdapter(phoneAdapter);
		new AlertDialog.Builder(MainActivity.this).setView(resultDialog).setPositiveButton("È·¶¨", null).show();
	}

	
}
