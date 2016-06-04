package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ProvinceCityAdapter extends BaseAdapter {
	
	private Context ctx;
	private String[] datas;
	
	public ProvinceCityAdapter(Context ctx, String[] datas){
		this.ctx = ctx;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
