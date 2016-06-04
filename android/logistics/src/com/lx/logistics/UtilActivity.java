package com.lx.logistics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lx.Util.BaseActivaty;

public class UtilActivity extends BaseActivaty {

	private static Button[] btns = new Button[4];
	private static Class[] targetClasses = new Class[] {
			BaseInfoActivity.class, SentInfoActivity.class,
			UnsentInfoActivity.class, SignCargoActivity.class };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_util);
	    super.init(0);
		/*btns[0] = (Button) findViewById(R.id.select_base_btn);
		btns[1] = (Button) findViewById(R.id.sent_btn);
		btns[2] = (Button) findViewById(R.id.unsent_btn);
		btns[3] = (Button) findViewById(R.id.sigh_cargo);

		btns[0].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(UtilActivity.this, targetClasses[0]);
				startActivity(in);
			}
		});
		btns[1].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(UtilActivity.this, targetClasses[1]);
				startActivity(in);
			}
		});
		btns[2].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(UtilActivity.this, targetClasses[2]);
				startActivity(in);
			}
		});
		btns[3].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(UtilActivity.this, targetClasses[3]);
				startActivity(in);
			}
		});*/

	}

}
