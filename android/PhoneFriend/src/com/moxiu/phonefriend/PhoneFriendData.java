package com.moxiu.phonefriend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

public class PhoneFriendData {
	
	private Context ctx;
	
	private List<Map<String, String>> phoneNames;
	private List<List<String>> phoneNumbers;
	private List<String> phoneEmails;

	public PhoneFriendData(Context ctx) {
		this.ctx = ctx;
		phoneNames = new ArrayList<Map<String, String>>();
		phoneNumbers = new ArrayList<List<String>>();
		phoneEmails = new ArrayList<String>();
	}
	
	public List<Map<String, String>> getPhoneNames(){
		Cursor cursor = ctx.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		while (cursor.moveToNext()) {
			Map<String, String> map = new HashMap<String, String>();
			String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));  //获取ID
			String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));  //获取名字
			map.put("_id", contactId);
			map.put("name", name);
			phoneNames.add(map);
		}
		cursor.close();
		return phoneNames;
	}
	
	public List<List<String>> getPhoneNumbers(List<Map<String, String>> l){
		List<String> numbers = new ArrayList<String>();
		for (Map<String, String> map : l) {
			Cursor cursor = ctx.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + map.get("_id"), null, null);
			while (cursor.moveToNext()) {
				String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));  //获取电话号码
				numbers.add("Tel: " + phoneNumber);
			}
			phoneNumbers.add(numbers);
			cursor.close();
		}
		
		return phoneNumbers;
	}

	public List<String> getPhoneEmails(List<Map<String, String>> l){
		for (Map<String, String> map : l) {
			Cursor cursor = ctx.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + map.get("_id"), null, null);
			while (cursor.moveToNext()) {
				String phoneEmail = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));  //获取电话号码
				phoneEmails.add("Email: " + phoneEmail);
			}
			cursor.close();
		}
		
		return phoneEmails;
	}
}
