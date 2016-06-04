package com.example.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class HttpConnectionUtil {
	/**
	 * 上传data数据并得到返回的字符串
	 * @param data
	 * @return
	 */
	public static String startHttpConnection(final String url, final Map<String, String> data, final Handler handler){
		final String[] requestStr = {"empty"};
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				BufferedOutputStream bos = null;
				BufferedInputStream bis = null;
				
				HttpURLConnection mHttpURLConnection = null;
				URL mUrl = null;
				String request = null;
				int flag = 0;
				
				try {
					mUrl = new URL(url);
					mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
					mHttpURLConnection.setDoInput(true);
					mHttpURLConnection.setDoOutput(true);
					mHttpURLConnection.setRequestMethod("POST");
					mHttpURLConnection.setConnectTimeout(StaticConfig.connectionTimeOut);
					mHttpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
					
					if (null != data) {
						JSONObject jsoObject = Staticmethod.transformMapToJson(data);
						bos = new BufferedOutputStream(mHttpURLConnection.getOutputStream());
						byte[] bytes = jsoObject.toString().getBytes();
						bos.write(bytes);
						bos.flush();
					}
					
					if (mHttpURLConnection.getResponseCode() == 200) {
						bis = new BufferedInputStream(mHttpURLConnection
								.getInputStream());
						request = new String(Staticmethod.streamToByte(bis));
						requestStr[0] = request;
						Log.i("http", "request--------" + request);
						if (null != handler) {
							if (StaticConfig.regUrl.equals(url)) {
								flag = StaticConfig.REG_MSG;
							}else if (StaticConfig.logUrl.equals(url)) {
								flag = StaticConfig.LOG_MSG;
							}
							Message msg = handler.obtainMessage(flag, request);
							handler.sendMessage(msg);
						}
					}
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		return requestStr[0];
	}
	
	public static String httpGet(String url){
		Log.i("http", "result------httpGet");
		HttpGet get = new HttpGet(url);
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 8000);
		HttpEntity entity = null;
		InputStream is = null;
		BufferedReader br = null;
		String result = "";
		String line = "";
		
		try {
			HttpResponse response = client.execute(get);
			if (null != response) {
				entity = response.getEntity();
				is = entity.getContent();
				br = new BufferedReader(new InputStreamReader(is));
				while (null != (line = br.readLine())) {
					result += line;
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Log.i("http", "result------" + result);
		return result;
	}
	
	
	
}
