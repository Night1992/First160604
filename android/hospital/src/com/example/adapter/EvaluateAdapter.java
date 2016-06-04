package com.example.adapter;

import java.util.List;

import com.example.hospital.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EvaluateAdapter extends BaseAdapter {
	private static final String TAG = "EvaluateAdapter";
	
	private Context ctx;
	private List<EvaluateInfoBean> datas;
	
	public EvaluateAdapter(Context ctx, List<EvaluateInfoBean> datas) {
		this.ctx = ctx;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (null == convertView) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(ctx).inflate(R.layout.item_evaluate, null);
			viewHolder.userNameTv = (TextView) convertView.findViewById(R.id.userName_item_tv);
			viewHolder.contentTv = (TextView) convertView.findViewById(R.id.content_item_tv);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.userNameTv.setText(datas.get(position).getUserNmae());
		viewHolder.contentTv.setText(datas.get(position).getContent());
		
		return convertView;
	}
	
	private class ViewHolder{
		TextView userNameTv;
		TextView contentTv;
	}

}
