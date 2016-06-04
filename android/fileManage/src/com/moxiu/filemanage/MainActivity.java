package com.moxiu.filemanage;

import java.io.File;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.moxiu.fileAdapter.FileAdapter;
import com.moxiu.filemanage.util.FileManageUtil;

public class MainActivity extends Activity implements OnItemClickListener {

	private TextView locationTv;
	private ListView fileListLv;

	private String location;
	private FileManageUtil fmu;
	private List<Map<String, String>> values;
	private FileAdapter fileAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();

		fileListLv.setOnItemClickListener(this);
	}

	private void init() {
		locationTv = (TextView) findViewById(R.id.location_tv);
		fileListLv = (ListView) findViewById(R.id.fileList_lv);

		fmu = new FileManageUtil();
		location = FileManageUtil.ROOT_FILE;
		values = fmu.getFileList(location);
		show();
	}

	private void show() {
		locationTv.setText(location);
		fileAdapter = new FileAdapter(this, values);
		fileListLv.setAdapter(fileAdapter);
		fileAdapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		if (fmu.isDir(values.get(position).get("file"))) {
			location = values.get(position).get("file");
			values = fmu.getFileList(values.get(position).get("file"));
			show();
		} else if (FileManageUtil.CLOSE_DIR.equals(values.get(position).get(
				"type"))) {
			location = fmu.getRootLocation(location);
			values = fmu.getFileList(location);
			show();
		} else {
			AlertDialog.Builder dialog = new AlertDialog.Builder(
					MainActivity.this);
			dialog.setTitle("文件信息");
			dialog.setMessage("文件名：" + values.get(position).get("name")
					+ "\r\n" + "文件大小："
					+ new File(values.get(position).get("file")).length() / 1024.00 / 1024.00 + "M");
			dialog.setNegativeButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			});
			dialog.create().show();
		}

	}

}
