package com.moxiu.mycalendar.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.provider.CalendarContract.Calendars;
import android.util.Log;

public class DateUtil {
	
	private static final int NEXT = 0;
	private static final int PREVIOR = 1;
	private static final int SUN = 0;
	private static final int MON = 1;
	private static final int TUE = 2;
	private static final int WED = 3;
	private static final int THU = 4;
	private static final int FRI = 5;
	private static final int SAT = 6;

	private String nowDate;
	private int selectYear;
	private int selectMonth;
	private int selectDay;
	private int selectWeek;
	private int daysInMonth;
	
	private Calendar calendar;
	
	public DateUtil(){
		calendar = Calendar.getInstance();
	}
	
	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

	public int getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(int selectYear) {
		this.selectYear = selectYear;
	}

	public int getSelectMonth() {
		return selectMonth;
	}

	public void setSelectMonth(int selectMonth) {
		this.selectMonth = selectMonth;
	}

	public int getSelectDay() {
		return selectDay;
	}

	public void setSelectDay(int selectDay) {
		this.selectDay = selectDay;
	}

	public int getSelectWeek() {
		return selectWeek;
	}

	public void setSelectWeek(int selectWeek) {
		this.selectWeek = selectWeek;
	}

	public String getNowDateFromSystem(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.nowDate = sdf.format(date);
		this.selectYear = Integer.parseInt(nowDate.split("-")[0]);
		this.selectMonth = Integer.parseInt(nowDate.split("-")[1]);
		this.selectDay = Integer.parseInt(nowDate.split("-")[2]);
		Log.e("date", nowDate + ">>>>>>date");
		
		return this.nowDate;
	}
	
	public String selectMonth(String nowMonth,int action){
		return "1";
	}
	
	public int nextMonth(int month){
		if (month < 12) {
			month++;
		}else {
			month = 1;
		}
		return month;
	}
	
	public int preMonth(int month){
		if (month > 0) {
			month--;
		}else {
			month = 12;
		}
		return month;
	}
	
	public List<Integer> getDaysToShow(int year,int month){
		List<Integer> days = new ArrayList<Integer>();
		int numDay = getDaysInMonth(month);
		int numPreDay = getDaysInMonth(preMonth(month));
		int weekFir = getWeekOfFirstDay(year, month);
		for (int i = 1; i <= 42; i++) {
			if (i - weekFir < numDay) {
				if (i < weekFir) {
					days.add(numPreDay + i - weekFir + 1);
				}else {
					days.add(i - weekFir + 1);
				}
			}else {
				days.add(i - numDay - weekFir + 1);
			}
		}
		
		return days;
	}
	
	/**
	 * 得到该月第一天的星期
	 * @param year 当前年份
	 * @param month 当前月份
	 * @return 星期，0为周日，1为周一。。。
	 */
	public int getWeekOfFirstDay(int year, int month){
		int weekOfFirstDay = 1;
		calendar.set(year, month - 1, 1);
		weekOfFirstDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		
		Log.e("week", weekOfFirstDay + ">>>>>>>week");
		return weekOfFirstDay;
	}
	
	public int getDaysInMonth(int month){
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			daysInMonth = 31;
			break;
			
		case 4:
		case 6:
		case 9:
		case 11:
			daysInMonth = 30;
			break;
			
		case 2:
			daysInMonth = 28;
			break;
			
		default:
			break;
		}
		
		return daysInMonth;
	}
	
}
