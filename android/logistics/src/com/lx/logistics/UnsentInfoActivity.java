package com.lx.logistics;

import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.capture.CaptureActivity;
import com.lx.Adapter.SelectAdapter;
import com.lx.Util.BaseActivaty;
import com.lx.Util.DBUtil;
import com.lx.Util.selectDao;

public class UnsentInfoActivity extends BaseActivaty {

	private DBUtil dbUtil = new DBUtil(this);
	private SelectAdapter selectAdapter;
	private ArrayAdapter<String> arrayAdapter;
	private ListView listView;
	private Button qrButton;
	private Button msgButton;
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> unsentList = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> bookingsList = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> cargoList = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
	// 返回的买家的详细信息list
	private List<Map<String, Object>> buyerResultList = new ArrayList<Map<String, Object>>();
	private Map<String, Object> mapResult = new HashMap<String, Object>();
	private Map<String, Object> phoneMap = new HashMap<String, Object>();
	private List<String> listStrings = new ArrayList<String>();
	private List<String> phoneNumberList = new ArrayList<String>();
	private String bookingsId;
	private String cargoName;
	private String result = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unsent_info);
		super.init(2);

		listView = (ListView) findViewById(R.id.unsentView);

		list = selectDao.selectInfo("sentLogistics",
				dbUtil.getReadableDatabase());
		bookingsList = selectDao.selectInfo("bookings",
				dbUtil.getReadableDatabase());
		cargoList = selectDao.selectInfo("cargo", dbUtil.getReadableDatabase());

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("address").toString().equals("未签收")) {
				listStrings.add(list.get(i).get("sid").toString());
				bookingsId = list.get(i).get("sid").toString();
				for (int j = 0; j < bookingsList.size(); j++) {
					if (bookingsList.get(j).get("sid").toString()
							.equals(bookingsId)) {
						//cargoName = bookingsList.get(j).get("name").toString();
						/*for (int k = 0; k < cargoList.size(); k++) {
							if (cargoList.get(k).get("name").toString()
									.equals(cargoName)) {
								unsentList.add(cargoList.get(k));
							}
						}*/
						unsentList.add(bookingsList.get(j));
						phoneMap = selectDao.selectOne("buyer",
								dbUtil.getReadableDatabase(), "buyerName",
								bookingsList.get(j).get("address").toString());
						buyerResultList.add(phoneMap);
					}
				}
			}
		}

		selectAdapter = new SelectAdapter(UnsentInfoActivity.this, unsentList);

		listView.setAdapter(selectAdapter);

		// 二维码扫描按钮
		qrButton = (Button) findViewById(R.id.call_btn);
		qrButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(UnsentInfoActivity.this,
						CaptureActivity.class);
				// Bundle bundle = new Bundle();
				// bundle.putString("dataStr", result);
				// intent.putExtras(bundle);
				// startActivity(intent);
				startActivityForResult(intent, 0);
			}
		});

		// 发送短信按钮
		msgButton = (Button) findViewById(R.id.message_btn);
		msgButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String msg = "好孩子提示您快递到了，请速来取件，谢谢！";
				// 获取smsmanager
				SmsManager smsManager = SmsManager.getDefault();
				// 若短信内容多余70字，则拆分，并保存在list中
				List<String> smsList = smsManager.divideMessage(msg);
				// 逐条发送短信
				for (int i = 0; i < buyerResultList.size(); i++) {
					for (String sms : smsList) {
						smsManager.sendTextMessage(
								buyerResultList.get(i).get("buyerPhone")
										.toString(), null, sms, null, null);
					}

				}
				// 发送结果提示
				Toast.makeText(UnsentInfoActivity.this, "发送成功",
						Toast.LENGTH_LONG).show();

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (resultCode) {
		case RESULT_OK:
			Bundle bundle = data.getExtras();
			result = bundle.getString("dataStr");
			// 二维码扫描结束后将结果回填到当前activity相应位置的spinner
			Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

			listView = (ListView) findViewById(R.id.unsentView);

			listResult.clear();
			String[] resultStrings = result.split(",");
			if (resultStrings.length == 4) {
				mapResult.put("sid", resultStrings[0]);
				mapResult.put("name", resultStrings[1]);
				mapResult.put("phone", resultStrings[2]);
				mapResult.put("address", resultStrings[3]);
				listResult.add(mapResult);

				buyerResultList.clear();
				phoneMap = selectDao.selectOne("buyer",
						dbUtil.getReadableDatabase(), "buyerName",
						resultStrings[3]);
				buyerResultList.add(phoneMap);
				
				selectAdapter = new SelectAdapter(UnsentInfoActivity.this,
						listResult);
				listView.setAdapter(selectAdapter);
			} else {
				Toast.makeText(this, "二维码不符合本快递公司要求，请扫描正确的二维码",
						Toast.LENGTH_SHORT).show();
			}

			break;

		default:
			break;
		}
		// 获取扫描二维码后的买家电话，并加入到phoneNumberList中
		// 循环获取显示在页面的买家的手机号
		for (int i = 0; i < buyerResultList.size(); i++) {
			Toast.makeText(this,
					buyerResultList.get(i).get("buyerPhone").toString(),
					Toast.LENGTH_SHORT).show();
		}

	}

}
