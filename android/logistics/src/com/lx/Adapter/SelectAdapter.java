package com.lx.Adapter;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lx.Util.BaseActivaty;
import com.lx.Util.DBUtil;
import com.lx.Util.DeleteDao;
import com.lx.logistics.BaseInfoActivity;
import com.lx.logistics.InsertInfoActivity;
import com.lx.logistics.R;
import com.lx.logistics.UpdateDialogActivity;

public class SelectAdapter extends BaseAdapter {

	private Context ctx;
	private String table;
	private List<Map<String, Object>> values;
	private DBUtil dbUtil;
	private Button button;
	private float DownX;
	private float UpX;

	public SelectAdapter(Context ctx, List<Map<String, Object>> values) {
		this.ctx = ctx;
		this.values = values;
	}
	
	public SelectAdapter(Context ctx, List<Map<String, Object>> values, String table, Button button) {
		this.ctx = ctx;
		this.values = values;
		this.table = table;
		this.button = button;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return values.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return values.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		dbUtil = new DBUtil(ctx);
		if (convertView == null) {
			convertView = LayoutInflater.from(ctx).inflate(
					R.layout.select_adapter, null);
		}

		TextView sid = (TextView) convertView.findViewById(R.id.sid);
		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView phone = (TextView) convertView.findViewById(R.id.phone);
		TextView address = (TextView) convertView.findViewById(R.id.address);
		
		final Map<String, Object> map = values.get(position);

		sid.setText(map.get("sid").toString());
		name.setText(map.get("name").toString());
		phone.setText(map.get("phone").toString());
		address.setText(map.get("address").toString());
		
		//长按监听
		
		//滑动监听
		convertView.setOnTouchListener(new OnTouchListener() {
			
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				switch(event.getAction())//根据动作来执行代码     
                {    
                case MotionEvent.ACTION_MOVE://滑动     
                    //Toast.makeText(ctx, "move...", Toast.LENGTH_SHORT).show();  
                    break;    
                case MotionEvent.ACTION_DOWN://按下     
                    //Toast.makeText(ctx, "down...", Toast.LENGTH_SHORT).show();  
                    DownX = event.getX();
                    break;    
                case MotionEvent.ACTION_UP://松开     
                    UpX = event.getX();  
                    //Toast.makeText(ctx, "up..." + Math.abs(UpX-DownX), Toast.LENGTH_SHORT).show();  
                    if(Math.abs(UpX-DownX) > 300){  
                    	Toast.makeText(ctx, map.get("name").toString(), Toast.LENGTH_SHORT).show();
                    	Builder builder = new Builder(ctx);
						builder.setTitle("好孩子提示");
						builder.setMessage("确定删除" + map.get("name").toString() + "？");
						builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								DeleteDao.deleteUser(ctx, table, dbUtil.getWritableDatabase(), map);
								values.remove(map);
								notifyDataSetChanged();;
								//button.performClick();
							}
						});
						builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
							}
						});
						builder.create().show();
                    }else{
                    	Activity myaActivity = (Activity)ctx;
        				String string = map.get("sid").toString() + ","
        						+ map.get("name").toString() + ","
        						+ map.get("phone").toString() + ","
        						+ map.get("address").toString();
        				Toast.makeText(ctx, string,
        						Toast.LENGTH_SHORT).show();
        				
        				//创建修改数据的dialog
        				Intent intent = new Intent(myaActivity, UpdateDialogActivity.class);
        				Bundle bundle = new Bundle();
        				bundle.putString("editStr", string);
        				bundle.putString("textStr", table);
        				intent.putExtras(bundle);
        				
        				((Activity)ctx).startActivityForResult(intent, 0);
					}
                    break;    
                default:    
                }    
					
				return true;
			}
		}); 

		return convertView;
	}

}
