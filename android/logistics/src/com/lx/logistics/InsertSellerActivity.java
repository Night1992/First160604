package com.lx.logistics;

import java.util.HashMap;
import java.util.Map;

import com.lx.Util.BaseActivaty;
import com.lx.Util.DBUtil;
import com.lx.Util.InsertDao;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertSellerActivity extends BaseActivaty {
	
	private static Button insertButton;
	private static EditText[] insertEditTexts = new EditText[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_seller);
		//super.insertInit(2);
		
		final DBUtil db = new DBUtil(this);
		insertButton = (Button) findViewById(R.id.insert_seller_btn);
		insertEditTexts[0] = (EditText) findViewById(R.id.insert_seller_address);
		insertEditTexts[1] = (EditText) findViewById(R.id.insert_seller_name);
		insertEditTexts[2] = (EditText) findViewById(R.id.insert_seller_phone);
		insertButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*Map<String, Object> map = new HashMap<String, Object>();
				map.put("sellerAddress", insertEditTexts[0].getText().toString());
				map.put("sellerName", insertEditTexts[1].getText().toString());
				map.put("sellerPhone", insertEditTexts[2].getText().toString());
				InsertDao.insertSeller(InsertSellerActivity.this,
						db.getWritableDatabase(), map);*/
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sellerAddress", insertEditTexts[0].getText().toString());
				map.put("sellerName", insertEditTexts[1].getText().toString());
				map.put("sellerPhone", insertEditTexts[2].getText().toString());
				
				InsertDao.insertSeller(InsertSellerActivity.this,
						db.getWritableDatabase(), map);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("insert", "seller");
				intent.putExtras(bundle);
				setResult(RESULT_OK, intent);
				//selectAdapter.notifyDataSetChanged();
				finish();
			}
		});
	}


}
