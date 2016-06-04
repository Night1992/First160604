package com.lx.logistics;

import javax.security.auth.PrivateCredentialPermission;

import com.lx.Util.DBUtil;
import com.lx.Util.UpdateDao;

import android.R.anim;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateDialogActivity extends Activity {

	// private String[] dataStrings;
	private TextView[] textViews = new TextView[3];
	private EditText[] editTexts = new EditText[3];
	private Button submitButton;
	private Button backButton;
	private DBUtil dbUtil = new DBUtil(this);
	private String sqlString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_dialog);
		
		/*WindowManager w = getWindowManager();
		Display d = w.getDefaultDisplay();//获取屏幕的宽和高
		android.view.WindowManager.LayoutParams params = getWindow().getAttributes();
		Point outSize = new Point();
		params.height = (int)(d.getHeight() * 0.8);*/
		

		textViews[0] = (TextView) findViewById(R.id.update_tv_name);
		textViews[1] = (TextView) findViewById(R.id.update_tv_phone);
		textViews[2] = (TextView) findViewById(R.id.update_tv_address);

		editTexts[0] = (EditText) findViewById(R.id.update_ev_name);
		editTexts[1] = (EditText) findViewById(R.id.update_ev_phone);
		editTexts[2] = (EditText) findViewById(R.id.update_ev_address);

		submitButton = (Button) findViewById(R.id.update_submit_btn);
		backButton = (Button) findViewById(R.id.update_reset_btn);

		final String[] dataStrings = getIntent().getStringExtra("editStr")
				.split(",");
		// Toast.makeText(UpdateDialogActivity.this, dataStrings[0],
		// Toast.LENGTH_SHORT).show();

		if (getIntent().getStringExtra("textStr").equals("cargo")) {
			textViews[0].setText("货物名称：");
			textViews[1].setText("货物价格：");
			textViews[2].setText("货物重量：");
		}else if (getIntent().getStringExtra("textStr").equals("buyer")) {
			textViews[0].setText("买家名称：");
			textViews[1].setText("买家地址：");
			textViews[2].setText("买家电话：");
		}else if (getIntent().getStringExtra("textStr").equals("user")) {
			textViews[0].setText("用户名称：");
			textViews[1].setText("用户密码：");
			textViews[2].setText("用户等级：");
		}
		else {
			textViews[0].setText("卖家名称：");
			textViews[1].setText("卖家地址：");
			textViews[2].setText("卖家电话：");
		}

		editTexts[0].setText(dataStrings[1]);
		editTexts[1].setText(dataStrings[2]);
		editTexts[2].setText(dataStrings[3]);

		submitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (getIntent().getStringExtra("textStr").equals("cargo")) {
					sqlString = "update cargo set cargoName='"
							+ editTexts[0].getText().toString() + "',cargoPrice='"
							+ editTexts[1].getText().toString() + "',cargoWeight='"
							+ editTexts[2].getText().toString()
							+ "' where cargoId='"
							+ dataStrings[0] + "'";
				}else if (getIntent().getStringExtra("textStr").equals("buyer")) {
					sqlString = "update buyer set buyerName='"
							+ editTexts[0].getText().toString() + "',buyerAddress='"
							+ editTexts[1].getText().toString() + "',buyerPhone='"
							+ editTexts[2].getText().toString()
							+ "' where buyerId='"
							+ dataStrings[0] + "'";
				}else if (getIntent().getStringExtra("textStr").equals("user")) {
					sqlString = "update user set userName='"
							+ editTexts[0].getText().toString() + "',userPhone='"
							+ editTexts[1].getText().toString() + "',userLevel='"
							+ editTexts[2].getText().toString()
							+ "' where userId='"
							+ dataStrings[0] + "'";
				}
				else if (getIntent().getStringExtra("textStr").equals("seller")){
					sqlString = "update seller set sellerName='"
							+ editTexts[0].getText().toString() + "',sellerAddress='"
							+ editTexts[1].getText().toString() + "',sellerPhone='"
							+ editTexts[2].getText().toString()
							+ "' where sellerId='"
							+ dataStrings[0] + "'";
				}
				
				UpdateDao.updateInfo(sqlString, dbUtil.getWritableDatabase());
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("insert", getIntent().getStringExtra("textStr"));
				intent.putExtras(bundle);
				setResult(RESULT_OK, intent);
				finish();
			}

		});
		
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				//bundle.putString("insert", getIntent().getStringExtra("textStr"));
				//intent.putExtras(bundle);
				setResult(RESULT_CANCELED, intent);
				finish();
				
			}
		});

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_dialog, menu);
		return true;
	}

}
