package com.moxiu.phoneAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class PhoneAdapter extends BaseExpandableListAdapter {

	private Context ctx;

	private List<Map<String, String>> phoneNames;
	private List<List<String>> phoneNumbers;
	private List<String> phoneEmails;

	public PhoneAdapter() {
	}

	public PhoneAdapter(Context ctx, List<Map<String, String>> phoneNames,
			List<List<String>> phoneNumbers, List<String> phoneEmails) {
		this.ctx = ctx;
		this.phoneNames = phoneNames;
		this.phoneNumbers = phoneNumbers;
		this.phoneEmails = phoneEmails;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
//		return Arrays.asList(phoneNumbers.get(childPosition),
//				phoneEmails.get(childPosition));
		return phoneNumbers.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		return arg1;
	}

	private TextView getTextView() {
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 100);
		TextView textView = new TextView(ctx);
		textView.setLayoutParams(lp);
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		textView.setPadding(30, 0, 0, 0);
		textView.setTextSize(24);

		return textView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TextView tv = getTextView();
		tv.setText(getChild(groupPosition, childPosition).toString());

		return tv;
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return phoneNames.get(arg0).size();
	}

	@Override
	public Object getGroup(int arg0) {
		return phoneNames.get(arg0).get("name");
	}

	@Override
	public int getGroupCount() {
		return phoneNames.size();
	}

	@Override
	public long getGroupId(int arg0) {
		return arg0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView tv = getTextView();
		tv.setText(getGroup(groupPosition).toString());
		
		return tv;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
