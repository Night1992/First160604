package com.lx.logistics;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.lx.Adapter.SelectAdapter;
import com.lx.Util.BaseActivaty;
import com.lx.Util.DBUtil;
import com.lx.Util.InsertDao;

public class InsertUserActivity extends BaseActivaty {
	private static Button insertButton;
	private static EditText[] insertEditTexts = new EditText[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_user);
		//super.insertInit(0);

		final DBUtil db = new DBUtil(this);
		insertButton = (Button) findViewById(R.id.insert_user_btn);
		insertEditTexts[0] = (EditText) findViewById(R.id.insert_user_name);
		insertEditTexts[1] = (EditText) findViewById(R.id.insert_user_phone);
		insertEditTexts[2] = (EditText) findViewById(R.id.insert_user_level);
		//final SelectAdapter selectAdapter = InsertInfoActivity.getSelectAdapter();
		insertButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userName", insertEditTexts[0].getText().toString());
				map.put("userPhone", insertEditTexts[1].getText().toString());
				map.put("userLevel", insertEditTexts[2].getText().toString());
				
				InsertDao.insertUser(InsertUserActivity.this,
						db.getWritableDatabase(), map);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("insert", "user");
				intent.putExtras(bundle);
				setResult(RESULT_OK, intent);
				//selectAdapter.notifyDataSetChanged();
				finish();
			}
		});
	}
}
