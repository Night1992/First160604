package com.example.hospital;

import java.net.URI;
import java.util.List;

import com.baidu.location.LLSInterface;
import com.example.adapter.EvaluateAdapter;
import com.example.adapter.EvaluateInfoBean;
import com.example.util.HttpConnectionUtil;
import com.example.util.StaticConfig;
import com.example.util.Staticmethod;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class InfomationActivity extends Activity implements OnClickListener {
	private static final String TAG = "InfomationActivity";
	
	private ImageView logoInfoIv;
	private TextView nameInfoTv;
	private TextView levelInfoTv;
	private ImageView publicPraiseInfoIv;
	private TextView environmentInfoTv;
	private TextView facilityInfoTv;
	private Button officeBtn;
	private Button evaluateBtn;
	private ListView evaluateLv;
	private EvaluateAdapter evaluateAdapter;
	private ImageView backIv;
	private TextView enviromentBaseTv;
	private TextView facilityBaseTv;
	private LinearLayout evaluateContent;
	
	private String logoPath;
	private Bitmap logoBmp;
	private int logoRes;
	private String name;
	private String level;
	private String publicPraisePath;
	private Bitmap publicPraiseBmp;
	private int publicPraiseRes;
	private String environment;
	private String facility;
	private List<EvaluateInfoBean> datas;
	private int from;
	private boolean notify = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_infomation);
		
		initView();
		initData();
		initEvent();
		
	}

	private void initEvent() {
		evaluateBtn.setOnClickListener(this);
		officeBtn.setOnClickListener(this);
		backIv.setOnClickListener(this);
	}

	private void initData() {
		from = getIntent().getIntExtra("from", 0);
		if (from == StaticConfig.OFFICE_FROM) {
			publicPraiseInfoIv.setVisibility(View.INVISIBLE);
			environmentInfoTv.setVisibility(View.INVISIBLE);
			facilityInfoTv.setVisibility(View.INVISIBLE);
			enviromentBaseTv.setVisibility(View.INVISIBLE);
			facilityBaseTv.setVisibility(View.INVISIBLE);
			officeBtn.setText("名医风采");
			
			logoPath = getIntent().getStringExtra("logo_path");
			name = getIntent().getStringExtra("name");
			level = getIntent().getStringExtra("level");
			Bitmap bmp = Staticmethod.getBitmapFromPath(logoPath);
			nameInfoTv.setText(name);
			levelInfoTv.setText(level);
			logoInfoIv.setImageBitmap(bmp);
		}else if (from == StaticConfig.HOSPITAO_FROM) {
			logoPath = getIntent().getStringExtra("logo_path");
			logoRes = getIntent().getIntExtra("logo_res", R.drawable.ic_launcher);
			name = getIntent().getStringExtra("name");
			level = getIntent().getStringExtra("level");
			publicPraisePath = getIntent().getStringExtra("pp_path");
			publicPraiseRes = getIntent().getIntExtra("public_praise_res", R.drawable.pp_1);
			environment = getIntent().getStringExtra("environment");
			facility = getIntent().getStringExtra("facility");
			
			logoInfoIv.setImageResource(logoRes);
			nameInfoTv.setText(name);
			levelInfoTv.setText(level);
			publicPraiseInfoIv.setImageResource(publicPraiseRes);
			environmentInfoTv.setText(environment);
			facilityInfoTv.setText(facility);
		}else {
			environmentInfoTv.setVisibility(View.INVISIBLE);
			facilityInfoTv.setVisibility(View.INVISIBLE);
			enviromentBaseTv.setVisibility(View.INVISIBLE);
			facilityBaseTv.setVisibility(View.INVISIBLE);
			officeBtn.setVisibility(View.GONE);
			
			name = getIntent().getStringExtra("name");
			level = getIntent().getStringExtra("job");
			logoPath = getIntent().getStringExtra("path");
			publicPraiseRes = Staticmethod.getPpRes(getIntent().getIntExtra("evaluate_level", 0));
			
			nameInfoTv.setText(name);
			levelInfoTv.setText(level);
			Bitmap bmp = BitmapFactory.decodeFile(logoPath);
			logoInfoIv.setImageBitmap(bmp);
			publicPraiseInfoIv.setImageResource(publicPraiseRes);
		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				if (from == StaticConfig.OFFICE_FROM) {
					datas = Staticmethod.getEvaluateInfo(StaticConfig.eva_off_url, name, handler);
				}else if (from == StaticConfig.HOSPITAO_FROM) {
					datas = Staticmethod.getEvaluateInfo(StaticConfig.eva_hos_url, name, handler);
				}else {
					datas = Staticmethod.getEvaluateInfo(StaticConfig.eva_doc_url, name, handler);
				}
			}
		}).start();
	}

	private void initView() {
		logoInfoIv = (ImageView) findViewById(R.id.logo_info_iv);
		nameInfoTv = (TextView) findViewById(R.id.name_info_tv);
		levelInfoTv = (TextView) findViewById(R.id.level_info_tv);
		publicPraiseInfoIv = (ImageView) findViewById(R.id.public_praise_info_iv);
		environmentInfoTv = (TextView) findViewById(R.id.environment_info_tv);
		facilityInfoTv = (TextView) findViewById(R.id.facility_info_tv);
		officeBtn = (Button) findViewById(R.id.offices_login_btn);
		evaluateBtn = (Button) findViewById(R.id.evaluate_info_btn);
		evaluateLv = (ListView) findViewById(R.id.evaluate_info_lv);
		backIv = (ImageView) findViewById(R.id.back_top_iv);
		enviromentBaseTv = (TextView) findViewById(R.id.environment_base_info_tv);
		facilityBaseTv = (TextView) findViewById(R.id.facility_base_info_tv);
		evaluateContent = (LinearLayout) findViewById(R.id.evaluate_content);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.offices_login_btn:
			if (from == StaticConfig.HOSPITAO_FROM) {
				Intent intent = new Intent(InfomationActivity.this, OfficeActivity.class);
				intent.putExtra("from", StaticConfig.HOSPITAO_FROM);
				intent.putExtra("hospital_name", name);
				startActivity(intent);
			}else if (from == StaticConfig.OFFICE_FROM) {
				Intent intent = new Intent(InfomationActivity.this, OfficeActivity.class);
				intent.putExtra("from", StaticConfig.OFFICE_FROM);
				intent.putExtra("hospital_name", name);
				startActivity(intent);
			}
			break;
			
		case R.id.evaluate_info_lv:
//			evaluateContent.setVisibility(View.VISIBLE);
			
			break;
		case R.id.back_top_iv:
			finish();
			break;
			
		case R.id.evaluate_info_btn:
			Log.i(TAG, "button------------");
			Intent in = new Intent(InfomationActivity.this, EvaluateContentActivity.class);
			in.putExtra("from", from);
			in.putExtra("name", name);
			startActivityForResult(in, 0);
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			notify = data.getBooleanExtra("notify", false);
			initData();
		}else {
			notify = false;
		}
		Log.i(TAG, "notify------" + requestCode + "-----" + resultCode + "-----" + notify);
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			Log.i("http", "data----------" + datas + "-----" + msg.what);
			evaluateAdapter = new EvaluateAdapter(InfomationActivity.this, datas);
			evaluateLv.setAdapter(evaluateAdapter);
			evaluateAdapter.notifyDataSetChanged();
			
			super.handleMessage(msg);
		}
		
	};
}
