package com.example.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.AvoidXfermode.Mode;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;

import com.example.adapter.DoctorInfoBean;
import com.example.adapter.EvaluateInfoBean;
import com.example.adapter.HospitalInfoBean;
import com.example.adapter.OfficeInfoBean;
import com.example.hospital.R;

public class Staticmethod {
	/**
	 * ��mapת����json
	 * @param map
	 * @return
	 */
	public static JSONObject transformMapToJson(Map<String, String> map){
		JSONObject jsonObject = new JSONObject();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			try {
				jsonObject.put(entry.getKey(), entry.getValue());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return jsonObject;
	}
	
	/**
	 * InputStreamת����bytes����
	 * @param is
	 * @return
	 */
	public static byte[] streamToByte(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int c = 0;
		byte[] buffer = new byte[8 * 1024];
		try {
			while ((c = is.read(buffer)) != -1) {
				baos.write(buffer, 0, c);
				baos.flush();
			}
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * ����ģ������
	 * @return
	 */
	public static List<HospitalInfoBean> initInfo(Handler handler){
		List<HospitalInfoBean> datas = new ArrayList<>();
		
		String request = HttpConnectionUtil.httpGet(StaticConfig.getUrl);
		try {
			JSONObject jsonTop = new JSONObject(request);
			JSONArray array = jsonTop.getJSONArray("hospitalList");
			for (int i = 0; i < array.length(); i++) {
				JSONObject j = array.getJSONObject(i);
				HospitalInfoBean info = new HospitalInfoBean();
				info.setLogoRes(R.drawable.ic_launcher);
				info.setName(j.getString("hospitalName"));
				info.setLevel("" + j.getInt("hospitalLevel"));
				info.setProvince(j.getString("hospitalProvince"));
				info.setCity(j.getString("hospitalCity"));
				if (null == j.getString("publicPraiseRes")) {
					info.setPublicPraiseRes(getPpRes(0));
				}else {
					info.setPublicPraiseRes(getPpRes(Integer.parseInt(j.getString("publicPraiseRes"))));
				}
				info.setEnvironment(j.getString("hospitalEnvironment"));
				info.setFacility(j.getString("hospitalFacility"));
				datas.add(info);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.i("http", "req------" + request);
		handler.sendEmptyMessage(StaticConfig.INIT_HOSPITAL);
		return datas;
		
	}
	
	public static int getPpRes(int p){
		int r;
		switch (p) {
		case 0:
			r = R.drawable.pp_0;
			break;

		case 1:
			r = R.drawable.pp_1;
			break;

		case 2:
			r = R.drawable.pp_2;
			break;

		case 3:
			r = R.drawable.pp_3;
			break;
			
		case 4:
			r = R.drawable.pp_4;
			break;

		case 5:
			r = R.drawable.pp_5;
			break;

		default:
			r = R.drawable.pp_0;
			break;
		}
		
		return r;
	}
	
	/**
	 * �����¼��Ϣ
	 * @param context ������
	 * @param userName �û���
	 * @param password ����
	 */
	public static void saveLoginInfo(Context context, String userName, String password){
		SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("userName", userName);
		editor.putString("password", password);
		editor.commit();
	}
	
	/**
	 * �����¼��Ϣ
	 * @param context
	 */
	public static void clearLoginInfo(Context context){
		SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove("password");
		editor.commit();
	}
	
	/**
	 * ����·��
	 * @param path
	 */
	public static void createPath(String path){
		File f = new File(StaticConfig.IMAGE_PATH);
		if (!f.exists()) {
			f.mkdirs();
		}
	}
	
	/**
	 * �õ�������ϢԴ
	 * @param url ������Ϣ�ķ����url
	 * @param name ����Դ
	 * @param handler handlerʵ��
	 * @return
	 */
	public static List<EvaluateInfoBean> getEvaluateInfo(String url, String name, Handler handler){
		List<EvaluateInfoBean> datas = new ArrayList<>();
		String s = "hospitalCommentsList";
		String content = "hcOverall";
		String reg = "hospitalName";
		int m = StaticConfig.EVA_HOSPITAL;
		if (StaticConfig.eva_off_url.equals(url)) {
			s = "departmentCommentsList";
			m = StaticConfig.EVA_OFFICE;
			content = "decOverall";
			reg = "departmentName";
		}else if (StaticConfig.eva_doc_url.equals(url)) {
			s = "doctorCommentsList";
			m = StaticConfig.EVA_DOCTOR;
			content = "dcOverall";
			reg = "doctorName";
		}
		
		String request = HttpConnectionUtil.httpGet(url);
		try {
			JSONObject jsonTop = new JSONObject(request);
			JSONArray array = jsonTop.getJSONArray(s);
			for (int i = 0; i < array.length(); i++) {
				JSONObject j = array.getJSONObject(i);
				if (name.equals(j.getString(reg))) {
					EvaluateInfoBean info = new EvaluateInfoBean();
					info.setUserNmae(j.getString("username"));
					info.setContent(j.getString(content));
					datas.add(info);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i("http", "req------" + request);
		handler.sendEmptyMessage(m);
		
		return datas;
	}
	
	/**
	 * �õ�������ϢԴ
	 * @param hospitalName ҽԺ����
	 * @param handler handlerʵ��
	 * @return
	 */
	public static List<OfficeInfoBean> getOfficeInfo(String hospitalName, Handler handler){
		List<OfficeInfoBean> datas = new ArrayList<>();
		
		String request = HttpConnectionUtil.httpGet(StaticConfig.offUrl);
		try {
			JSONObject jsonTop = new JSONObject(request);
			JSONArray array = jsonTop.getJSONArray("departmentList");
			for (int i = 0; i < array.length(); i++) {
				JSONObject j = array.getJSONObject(i);
				if (hospitalName.equals(j.getString("hospitalName"))) {
					OfficeInfoBean info = new OfficeInfoBean();
					info.setName(j.getString("departmentName"));
					info.setDoctor(j.getString("mainDoctor"));
					info.setPath(StaticConfig.IMAGE_PATH + "doctor.jpg");
					datas.add(info);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i("http", "req------" + request);
		handler.sendEmptyMessage(StaticConfig.INIT_OFFICE);
		
		return datas;
	}
	
	/**
	 * �õ�ҽ����ϢԴ
	 * @param officeName ��������
	 * @param handler handlerʵ��
	 * @return
	 */
	public static List<DoctorInfoBean> getDoctorInfo(String officeName, Handler handler){
		List<DoctorInfoBean> datas = new ArrayList<>();
		String request = HttpConnectionUtil.httpGet(StaticConfig.docUrl);
		try {
			JSONObject jsonTop = new JSONObject(request);
			JSONArray array = jsonTop.getJSONArray("doctorList");
			for (int i = 0; i < array.length(); i++) {
				JSONObject j = array.getJSONObject(i);
				if (officeName.equals(j.getString("departmentName"))) {
					DoctorInfoBean info = new DoctorInfoBean();
					info.setName(j.getString("doctorName"));
					info.setJob(j.getString("doctorProfessionalInformation"));
					info.setEvaluateLevel(j.getInt("doctorLevel"));
					info.setPath(StaticConfig.IMAGE_PATH + "doctor.jpg");
					datas.add(info);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.i("http", "req------" + request);
		handler.sendEmptyMessage(StaticConfig.INIT_DOCTOR);
		
		return datas;
	}
	
	/**
	 * ����·���õ�λͼbitmap
	 * @param path ·��
	 * @return
	 */
	public static Bitmap getBitmapFromPath(String path){
		Bitmap bmp = null;
		bmp = BitmapFactory.decodeFile(path);
		
		return bmp;
	}
	
	/**
	 * ���ݹؼ���������
	 * @param name �����ؼ���
	 * @param datas ����Դ
	 * @return
	 */
	public static List<HospitalInfoBean> searchName(String name, List<HospitalInfoBean> datas){
		List<HospitalInfoBean> searchs = new ArrayList<HospitalInfoBean>();
		for (HospitalInfoBean hospitalInfoBean : datas) {
			if (hospitalInfoBean.getName().contains(name)) {
				searchs.add(hospitalInfoBean);
			}
		}
		
		return searchs;
	}
	
	/**
	 * ����ʡ��ѡ��ҽԺ
	 * @param flag 0��ʡ;1����
	 * @param name �����ؼ���
	 * @param datas ����Դ
	 * @return
	 */
	public static List<HospitalInfoBean> searchName(int flag, String name, List<HospitalInfoBean> datas){
		List<HospitalInfoBean> searchs = new ArrayList<HospitalInfoBean>();
		if (null != datas) {
			for (HospitalInfoBean hospitalInfoBean : datas) {
				if (flag == 0) {
					if (hospitalInfoBean.getProvince().contains(name)) {
						searchs.add(hospitalInfoBean);
					}
				}else {
					if (hospitalInfoBean.getCity().contains(name)) {
						searchs.add(hospitalInfoBean);
					}
				}
			}
		}
		
		return searchs;
	}
}
