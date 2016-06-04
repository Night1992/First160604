package com.example.adapter;

import java.util.List;

import com.example.hospital.R;
import com.example.util.Staticmethod;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OfficeAdapter extends BaseAdapter {
	private static final String TAG = "OfficeAdapter";
	
	private Context ctx;
	private List<OfficeInfoBean> datas;

	public OfficeAdapter(Context ctx, List<OfficeInfoBean> datas) {
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
			convertView = LayoutInflater.from(ctx).inflate(R.layout.item_office, null);
			viewHolder.nameTv = (TextView) convertView.findViewById(R.id.name_office_tv);
			viewHolder.doctorTv = (TextView) convertView.findViewById(R.id.doctor_office_tv);
			viewHolder.doctorIv = (ImageView) convertView.findViewById(R.id.doctor_office_iv);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Bitmap bmp = Staticmethod.getBitmapFromPath(datas.get(position).getPath());
		viewHolder.nameTv.setText(datas.get(position).getName());
		viewHolder.doctorTv.setText(datas.get(position).getDoctor());
		viewHolder.doctorIv.setImageBitmap(bmp);
		
		return convertView;
	}
	
	private class ViewHolder{
		TextView nameTv;
		TextView doctorTv;
		ImageView doctorIv;
	}

}
