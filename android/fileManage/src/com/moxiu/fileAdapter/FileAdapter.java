package com.moxiu.fileAdapter;

import java.util.List;
import java.util.Map;

import com.moxiu.filemanage.R;
import com.moxiu.filemanage.util.FileManageUtil;

import android.R.layout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileAdapter extends BaseAdapter {

	private Context ctx;
	private List<Map<String, String>> values;

	private TextView fileNameTv;
	private ImageView fileImageIv;

	public FileAdapter(Context ctx, List<Map<String, String>> values) {
		this.ctx = ctx;
		this.values = values;
	}

	@Override
	public int getCount() {
		return values.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View converView, ViewGroup view) {

		if (converView == null) {
			converView = LayoutInflater.from(ctx).inflate(
					R.layout.item_filelist, null);
		}

		fileNameTv = (TextView) converView.findViewById(R.id.file_name_tv);
		fileImageIv = (ImageView) converView.findViewById(R.id.file_iv);
		fileNameTv.setText(values.get(position).get("name"));
		if ((FileManageUtil.IS_DIR).equals(values.get(position).get("type"))) {
			fileImageIv.setImageResource(R.drawable.close_dir);
		}
		fileImageIv.setImageResource(FileManageUtil.map.get(values
				.get(position).get("type")));
		return converView;
	}

}
