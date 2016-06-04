package com.lx.logistics;

import java.util.HashMap;
import java.util.Map;

import com.lx.Util.BaseActivaty;
import com.lx.Util.DBUtil;
import com.lx.Util.InsertDao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertBuyerActivity extends BaseActivaty {
	
	private static Button insertButton;
	private static EditText[] insertEditTexts = new EditText[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_buyer);
		//super.insertInit(3);
		
		final DBUtil db = new DBUtil(this);
		insertButton = (Button) findViewById(R.id.insert_buyer_btn);
		insertEditTexts[0] = (EditText) findViewById(R.id.insert_buyer_address);
		insertEditTexts[1] = (EditText) findViewById(R.id.insert_buyer_name);
		insertEditTexts[2] = (EditText) findViewById(R.id.insert_buyer_phone);
		insertButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*Map<String, Object> map = new HashMap<String, Object>();
				map.put("buyerAddress", insertEditTexts[0].getText().toString());
				map.put("buyerName", insertEditTexts[1].getText().toString());
				map.put("buyerPhone", insertEditTexts[2].getText().toString());
				Toast.makeText(InsertBuyerActivity.this,
						map.get("buyerName").toString(), Toast.LENGTH_SHORT)
						.show();
				InsertDao.insertBuyer(InsertBuyerActivity.this,
						db.getWritableDatabase(), map);*/
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("buyerAddress", insertEditTexts[0].getText().toString());
				map.put("buyerName", insertEditTexts[1].getText().toString());
				map.put("buyerPhone", insertEditTexts[2].getText().toString());
				
				InsertDao.insertBuyer(InsertBuyerActivity.this,
						db.getWritableDatabase(), map);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("insert", "buyer");
				intent.putExtras(bundle);
				setResult(RESULT_OK, intent);
				//selectAdapter.notifyDataSetChanged();
				finish();
			}
		});
	}

}
