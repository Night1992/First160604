package com.lx.logistics;

import java.util.HashMap;
import java.util.Map;
import com.lx.Util.BaseActivaty;
import com.lx.Util.DBUtil;
import com.lx.Util.InsertDao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertCargoActivity extends BaseActivaty {
	private static Button insertButton;
	private static EditText[] insertEditTexts = new EditText[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_cargo);
		//super.insertInit(1);

		final DBUtil db = new DBUtil(this);
		insertButton = (Button) findViewById(R.id.insert_cargo_btn);
		insertEditTexts[0] = (EditText) findViewById(R.id.insert_cargo_name);
		insertEditTexts[1] = (EditText) findViewById(R.id.insert_cargo_price);
		insertEditTexts[2] = (EditText) findViewById(R.id.insert_cargo_weight);
		insertButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*Map<String, Object> map = new HashMap<String, Object>();
				map.put("cargoName", insertEditTexts[0].getText().toString());
				map.put("cargoPrice", insertEditTexts[1].getText().toString());
				map.put("cargoWeight", insertEditTexts[2].getText().toString());
				Toast.makeText(InsertCargoActivity.this,
						map.get("cargoName").toString(), Toast.LENGTH_SHORT)
						.show();
				InsertDao.insertCargo(InsertCargoActivity.this,
						db.getWritableDatabase(), map);*/
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cargoName", insertEditTexts[0].getText().toString());
				map.put("cargoPrice", insertEditTexts[1].getText().toString());
				map.put("cargoWeight", insertEditTexts[2].getText().toString());
				
				InsertDao.insertCargo(InsertCargoActivity.this,
						db.getWritableDatabase(), map);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("insert", "cargo");
				intent.putExtras(bundle);
				setResult(RESULT_OK, intent);
				//selectAdapter.notifyDataSetChanged();
				finish();
			}
		});
	}
}
