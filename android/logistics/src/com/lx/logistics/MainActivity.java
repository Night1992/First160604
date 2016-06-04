package com.lx.logistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lx.Util.BaseActivaty;
import com.lx.Util.DBUtil;
import com.lx.Util.selectDao;

import android.app.Activity;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText uid;
	private EditText pwd;
	private Button loginButton;
	private Button resetButton;
	private DBUtil dbUtil;
	private List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        dbUtil = new DBUtil(this);
        list = selectDao.selectInfo("user", dbUtil.getReadableDatabase());
        
        uid = (EditText) findViewById(R.id.uid);
        pwd = (EditText) findViewById(R.id.pwd);
        loginButton = (Button) findViewById(R.id.log);
        resetButton = (Button) findViewById(R.id.res);
        
        loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String uidString = uid.getText().toString();
				String pwdString = pwd.getText().toString();
				int i = 0;
				for (; i < list.size(); i++) {
					if (list.get(i).get("name").toString().equals(uidString) && list.get(i).get("phone").toString().equals(pwdString)) {
						if (!list.get(i).get("address").toString().equals("0")) {
							Toast.makeText(MainActivity.this, "登陆成功！", Toast.LENGTH_LONG).show();
							Intent intent = new Intent(MainActivity.this, BaseInfoActivity.class);
							startActivity(intent);
							break;
						}else {
							Toast.makeText(MainActivity.this, "管理员登陆成功！", Toast.LENGTH_LONG).show();
							Intent intent = new Intent(MainActivity.this, InsertInfoActivity.class);
							startActivity(intent);
							break;
						}
					}
				}
				if (i == list.size()) {
					Toast.makeText(MainActivity.this, "账号或密码错误！", Toast.LENGTH_LONG).show();
					pwd.setText("");
				}
					
					/*if (uidString.equals("a") && pwdString.equals("a")) {
						Toast.makeText(MainActivity.this, "登陆成功！", Toast.LENGTH_LONG).show();
						Intent intent = new Intent(MainActivity.this, BaseInfoActivity.class);
						startActivity(intent);
					} else if (uidString.equals("admin") && pwdString.equals("admin")) {
						Toast.makeText(MainActivity.this, "管理员登陆成功！", Toast.LENGTH_LONG).show();
						Intent intent = new Intent(MainActivity.this, InsertInfoActivity.class);
						startActivity(intent);
					} else {
						Toast.makeText(MainActivity.this, "账号或密码错误！", Toast.LENGTH_LONG).show();
						pwd.setText("");
					}*/
			}
		});
        
        resetButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				uid.setText("");
				pwd.setText("");
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
