package com.moxiu.mycalendar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.View;

public class CalendarView extends View {
	
	private Paint textPaint;      //文字画笔。
	private Paint tablePaint;     //表格画笔。
	private Paint cellPaint;      //表格元素画笔.
	

	public CalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}



	public CalendarView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}



	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		canvas.drawColor(Color.DKGRAY);
		
		
//		Paint testPaint = new Paint();
//		Paint textPaint = new Paint();
//		textPaint.setColor(Color.BLACK);
//		textPaint.setStrokeWidth(5);
//		textPaint.setSubpixelText(true);
//		textPaint.setTextAlign(Align.CENTER);
//		testPaint.setColor(Color.WHITE);
//		testPaint.setStrokeWidth(3);
//		canvas.drawRect(10, 20, 200, 200, testPaint);
//		canvas.drawText("hello world,my calendar!", 95, 90, textPaint);
		
	}
	
	public void init(){
		
	}
	
	public void initPaint(){
		textPaint = new Paint();
		textPaint.setStrokeWidth(10);
		textPaint.setColor(Color.BLACK);
		
		tablePaint = new Paint();
		tablePaint.setStrokeWidth(5);
		tablePaint.setColor(Color.BLACK);
		
		cellPaint = new Paint();
		cellPaint.setStrokeWidth(5);
		cellPaint.setColor(Color.WHITE);
	}

}
