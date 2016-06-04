package com.lx.Util;

import com.lx.logistics.BaseInfoActivity;
import com.lx.logistics.InsertBuyerActivity;
import com.lx.logistics.InsertCargoActivity;
import com.lx.logistics.InsertInfoActivity;
import com.lx.logistics.InsertSellerActivity;
import com.lx.logistics.InsertSentLogisticsActivity;
import com.lx.logistics.InsertUserActivity;
import com.lx.logistics.LogisticsActivity;
import com.lx.logistics.R;
import com.lx.logistics.SentInfoActivity;
import com.lx.logistics.SignCargoActivity;
import com.lx.logistics.UnsentInfoActivity;
import com.lx.logistics.UtilActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BaseActivaty extends Activity {

	private static Button[] btns = new Button[4];
	private static Button[] insertBtn = new Button[6];
	private static Class[] targetClasses = new Class[] {
			BaseInfoActivity.class, SentInfoActivity.class,
			UnsentInfoActivity.class, SignCargoActivity.class };
	private static Class[] insertTargetClasses = new Class[] {
			InsertUserActivity.class, InsertCargoActivity.class,
			InsertSellerActivity.class, InsertBuyerActivity.class,LogisticsActivity.class,InsertSentLogisticsActivity.class };
	private int i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void init(int flag) {
		btns[0] = (Button) findViewById(R.id.select_base_btn);
		btns[1] = (Button) findViewById(R.id.sent_btn);
		btns[2] = (Button) findViewById(R.id.unsent_btn);
		btns[3] = (Button) findViewById(R.id.sigh_cargo);

		for (i = 0; i < btns.length; i++) {
			final int temp = i;
			if (i == flag) {
				btns[i].setTextSize(14);
			} else {
				btns[i].setTextSize(10);
				btns[i].setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent in = new Intent(BaseActivaty.this,
								targetClasses[temp]);
						finish();
						startActivity(in);
					}
				});
			}
		}
	}
	
	public void insertInit(int flag){
		insertBtn[0] = (Button) findViewById(R.id.insert_user);
		insertBtn[1] = (Button) findViewById(R.id.insert_cargo);
		insertBtn[2] = (Button) findViewById(R.id.insert_seller);
		insertBtn[3] = (Button) findViewById(R.id.insert_buyer);
		insertBtn[4] = (Button) findViewById(R.id.insert_logistics);
		insertBtn[5] = (Button) findViewById(R.id.insert_sentLogistics);
		

		for (i = 0; i < insertBtn.length; i++) {
			final int temp = i;
			if (i == flag) {
				insertBtn[i].setTextSize(14);
			} else {
				insertBtn[i].setTextSize(10);
				insertBtn[i].setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						/*Intent in = new Intent(BaseActivaty.this,
								insertTargetClasses[temp]);*/
						Intent in = new Intent(BaseActivaty.this,
								InsertInfoActivity.class);
						in.putExtra("button", temp);
						finish();
						startActivity(in);
					}
				});
			}
		}
	}

}
