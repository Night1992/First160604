package com.example.adapter;

import java.util.List;

import com.example.hospital.R;
import com.example.util.Staticmethod;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DoctorAdapter extends BaseAdapter {
	private static final String TAG = "DoctorAdapter";
	
	private Context ctx;
	private List<DoctorInfoBean> datas;
	
	public DoctorAdapter(Context ctx, List<DoctorInfoBean> datas) {
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
			convertView = LayoutInflater.from(ctx).inflate(R.layout.item_doctor, null);
			viewHolder.nameTv = (TextView) convertView.findViewById(R.id.name_doctor_tv);
			viewHolder.jobTv = (TextView) convertView.findViewById(R.id.job_doctor_tv);
			viewHolder.evaluateIv = (ImageView) convertView.findViewById(R.id.evaluate_doctor_iv);
			viewHolder.photoIv = (ImageView) convertView.findViewById(R.id.photo_doctor_iv);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.nameTv.setText(datas.get(position).getName());
		viewHolder.jobTv.setText(datas.get(position).getJob());
		Bitmap evaluateBmp = Staticmethod.getBitmapFromPath(datas.get(position).getPath());
		int evaluateId = Staticmethod.getPpRes(datas.get(position).getEvaluateLevel());
		viewHolder.photoIv.setImageBitmap(evaluateBmp);
		viewHolder.evaluateIv.setImageResource(evaluateId);
		
		return convertView;
	}

	private class ViewHolder{
		TextView nameTv;
		TextView jobTv;
		ImageView evaluateIv;
		ImageView photoIv;
	}
	
}
