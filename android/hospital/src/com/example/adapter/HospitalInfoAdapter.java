package com.example.adapter;

import java.util.List;

import com.example.hospital.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HospitalInfoAdapter extends BaseAdapter {
	private static final String TAG = "HospitalInfoAdapter";
	
	private List<HospitalInfoBean> datas;
	private Context ctx;
	
	public HospitalInfoAdapter(Context ctx, List<HospitalInfoBean> datas){
		this.ctx = ctx;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas == null ? null : datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder = null;
		if (null == convertView) {
			convertView = LayoutInflater.from(ctx).inflate(R.layout.item_hospital, null);
			viewHolder = new ViewHolder();
			viewHolder.logoIv = (ImageView) convertView.findViewById(R.id.logo_hospital_iv);
			viewHolder.nameTv = (TextView) convertView.findViewById(R.id.name_hospital_tv);
			viewHolder.levelTv = (TextView) convertView.findViewById(R.id.level_hospital_tv);
			viewHolder.publicPraiseIv = (ImageView) convertView.findViewById(R.id.public_praise_hospital_iv);
			viewHolder.environmentTv = (TextView) convertView.findViewById(R.id.environment_hospital_tv);
			viewHolder.facilityTv = (TextView) convertView.findViewById(R.id.facility_hospital_tv);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.logoIv.setImageResource(datas.get(position).getLogoRes());
		viewHolder.nameTv.setText(datas.get(position).getName());
		viewHolder.levelTv.setText(datas.get(position).getLevel());
		viewHolder.publicPraiseIv.setImageResource(datas.get(position).getPublicPraiseRes());
		viewHolder.environmentTv.setText(datas.get(position).getEnvironment());
		viewHolder.facilityTv.setText(datas.get(position).getFacility());
		
		return convertView;
	}
	
	private class ViewHolder{
		ImageView logoIv;
		TextView nameTv;
		TextView levelTv;
		ImageView publicPraiseIv;
		TextView environmentTv;
		TextView facilityTv;
	}

}
