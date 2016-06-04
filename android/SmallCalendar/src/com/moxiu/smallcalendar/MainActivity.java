package com.moxiu.smallcalendar;

import java.util.ArrayList;
import java.util.List;

import com.moxiu.smallcalendar.R;
import com.moxiu.mycalendar.util.DateUtil;
import com.moxiu.mycalendar.view.CalendarView;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private TextView nowDateTv;
	private DateUtil dateUtil;

	private String nowDate;
	private List<Integer> daysToShow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// setContentView(new CalendarView(this));

		init();

	}

	public void init() {
		nowDateTv = (TextView) findViewById(R.id.nowdate_calendar_tv);

//		daysToShow = new ArrayList<Integer>();

		initShow();
	}

	public void initShow() {
		dateUtil = new DateUtil();
		nowDate = dateUtil.getNowDateFromSystem();
		nowDateTv.setText(nowDate);
		dateUtil.getWeekOfFirstDay(dateUtil.getSelectYear(),
				dateUtil.getSelectMonth());
		daysToShow = dateUtil.getDaysToShow(dateUtil.getSelectYear(),
				dateUtil.getSelectMonth());
		Log.e("list", daysToShow.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
