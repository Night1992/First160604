package com.example.hospital;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.util.HttpConnectionUtil;
import com.example.util.StaticConfig;
import com.example.util.Staticmethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private static final String TAG = "LoginActivity";
	
	private EditText userNameEt;
	private EditText passwordEt;
	private Button loginBtn;
	private Button registerBtn;
	private Button reglogBtn;
	
	private LinearLayout registerLl;
	private EditText passwordEt2;
	private RadioGroup genderRg;
	private RadioButton genderRb;
	private EditText ageEt;
	private EditText jobEt;
	
	private String userName;
	private String password;
	private String password2;
	private String gender;
	private String age;
	private String job;
	
	private LoginHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		initView();
		initEvent();
		
	}

	private void initEvent() {
		loginBtn.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void onClick(View arg0) {
				userName = userNameEt.getText().toString();
				password = passwordEt.getText().toString();
				if (!checkLoginState(false)) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("username", userName);
					map.put("password", password);
					HttpConnectionUtil.startHttpConnection(StaticConfig.logUrl, map, handler);
//					login(userName, password);
				}
			}
		});
		
		registerBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				changePageState(true);
			}
		});
		
		reglogBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				userName = userNameEt.getText().toString();
				password = passwordEt.getText().toString();
				genderRb = (RadioButton) genderRg.findViewById(genderRg.getCheckedRadioButtonId());
				password2 = passwordEt2.getText().toString();
				gender = genderRb.getText().toString();
				age = ageEt.getText().toString();
				job = jobEt.getText().toString();
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("username", userName);
				map.put("password", password);
				map.put("sex", gender);
				map.put("age", age);
				map.put("profession", job);
				if (!checkLoginState(true)) {
					HttpConnectionUtil.startHttpConnection(StaticConfig.regUrl, map, handler);
				}
			}
		});
	}
	
	/**
	 * �����½��ע����Ϣ
	 * @param isRigister �Ƿ�Ϊע��״̬
	 */
	private boolean checkLoginState(boolean isRigister){
		if (isRigister) {
			if (checkEmpty(userNameEt, userName)) {
				Toast.makeText(LoginActivity.this, "�û�������Ϊ�գ�", Toast.LENGTH_SHORT).show();
				return true;
			}else if (checkEmpty(passwordEt, password)) {
				Toast.makeText(LoginActivity.this, "���벻��Ϊ�գ�", Toast.LENGTH_SHORT).show();
				return true;
			}else if (checkEmpty(passwordEt2, password2)) {
				Toast.makeText(LoginActivity.this, "ȷ�����벻��Ϊ�գ�", Toast.LENGTH_SHORT).show();
				return true;
			}else if (checkEmpty(ageEt, age)) {
				Toast.makeText(LoginActivity.this, "���䲻��Ϊ�գ�", Toast.LENGTH_SHORT).show();
				return true;
			}else if (checkEmpty(jobEt, job)) {
				Toast.makeText(LoginActivity.this, "ְҵ����Ϊ�գ�", Toast.LENGTH_SHORT).show();
				return true;
			}else {
				if (!password.equals(password2)) {
					Toast.makeText(LoginActivity.this, "����ǰ��һ�£�", Toast.LENGTH_SHORT).show();
					passwordEt2.setText("");
					getFocus(passwordEt2);
					return true;
				}
			}
		}else {
			if (checkEmpty(userNameEt, userName)) {
				Toast.makeText(LoginActivity.this, "�û�������Ϊ�գ�", Toast.LENGTH_SHORT).show();
				return true;
			}else if (checkEmpty(passwordEt, password)) {
				Toast.makeText(LoginActivity.this, "���벻��Ϊ�գ�", Toast.LENGTH_SHORT).show();
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * �ж��Ƿ�Ϊ��
	 * @param v �жϵĿؼ�
	 * @param s �ؼ�������
	 * @return true ��
	 */
	private boolean checkEmpty(EditText et, String s){
		if (null == s || "".equals(s)) {
			et.setText("");
			getFocus(et);
			return true;
		}
		
		return false;
	}
	
	private void login(String userName, String password) {
		Staticmethod.saveLoginInfo(this, userName, password);
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	/**
	 * ����ҳ��״̬
	 * @param register ע��״̬
	 */
	private void changePageState(boolean register){
		if (register) {
			registerLl.setVisibility(View.VISIBLE);
			loginBtn.setVisibility(View.GONE);
			registerBtn.setVisibility(View.GONE);
			reglogBtn.setVisibility(View.VISIBLE);
		}else {
			registerLl.setVisibility(View.GONE);
			loginBtn.setVisibility(View.VISIBLE);
			registerBtn.setVisibility(View.VISIBLE);
			reglogBtn.setVisibility(View.GONE);
		}
	}
	
	/**
	 * ��ؼ���ȡ����
	 * @param v �ؼ�����
	 */
	private void getFocus(View v){
		v.setFocusable(true);
		v.setFocusableInTouchMode(true);
		v.requestFocus();
		v.findFocus();
	}

	private void initView() {
		userNameEt = (EditText) findViewById(R.id.username_login_et);
		passwordEt = (EditText) findViewById(R.id.password_login_et);
		loginBtn = (Button) findViewById(R.id.login_login_btn);
		registerBtn = (Button) findViewById(R.id.register_login_btn);
		reglogBtn = (Button) findViewById(R.id.reglog_login_btn);
		
		registerLl = (LinearLayout) findViewById(R.id.register_login_ll);
		passwordEt2 = (EditText) findViewById(R.id.password2_login_et);
		genderRg = (RadioGroup) findViewById(R.id.gender_login_rg);
//		maleRb = (RadioButton) findViewById(R.id.male_login_rb);
//		femaleRb = (RadioButton) findViewById(R.id.female_login_rb);
		ageEt = (EditText) findViewById(R.id.age_login_et);
		jobEt = (EditText) findViewById(R.id.job_login_et);
		
		handler = new LoginHandler();
	}
	
	private class LoginHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			String f;
			switch (msg.what) {
			case StaticConfig.REG_MSG:
				f = msg.obj.toString();
				JSONObject json;
				Log.i("http", "ffffff------" + f);
				try {
					json = new JSONObject(f);
					if (json.has("flag") && "1".equals(json.getString("flag"))) {
						login(userName, password);
					}else {
						Toast.makeText(LoginActivity.this, "�û����Ѵ��ڣ�", Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;
				
			case StaticConfig.LOG_MSG:
				f = msg.obj.toString();
				try {
					JSONObject json2 = new JSONObject(f);
					if (json2.has("flag") && "1".equals(json2.getString("flag"))) {
						login(userName, password);
					}else {
						Toast.makeText(LoginActivity.this, "�˻������������", Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	}

}
