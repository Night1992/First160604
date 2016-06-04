package com.lx.qrcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class DoQrCode {
	private static int QR_WIDTH = 400;
	private static int QR_HEIGHT = 400;
	
	private String[] dataStrs = new String[4];

	public DoQrCode(String[] dataStrs) {
		super();
		this.dataStrs = dataStrs;
	}
	
	public void createQrCode(Context context){
		Bitmap bitmap = createBitmap(dataStrs[0] + "," + dataStrs[1] + "," + dataStrs[2] + "," + dataStrs[3]);
		saveBitmap(bitmap);
		Toast.makeText(context, "二维码图片已保存至/sdcard/image/目录下", Toast.LENGTH_SHORT).show();
	}
	
	//生成二维码
	private Bitmap createBitmap(String text) {
		Bitmap bitmap = null;

		try {
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new QRCodeWriter().encode(text,
					BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
			
			//text转成二维码
			int[] pixels = new int[QR_HEIGHT * QR_WIDTH];
			for (int y = 0; y < QR_HEIGHT; y++) {
				for (int x = 0; x < QR_WIDTH; x++) {
					if (bitMatrix.get(x, y)) {
						pixels[y * QR_WIDTH + x] = 0xff000000;
					}else {
						pixels[y * QR_WIDTH + x] = 0xffffffff;
					}
				}
			}
			
			bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
			
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bitmap;
	}
	//保存bitmap到本地
		private void saveBitmap(Bitmap bitmap){
			File imageDir = new File(Environment.getExternalStorageDirectory(), "image");
			if (!imageDir.exists()) {
				imageDir.mkdirs();
			}
			String imageName = System.currentTimeMillis() + ".jpg";
			File imgFile = new File(imageDir, imageName);
			try {
				FileOutputStream outputStream = new FileOutputStream(imgFile);
				bitmap.compress(CompressFormat.JPEG, 100, outputStream);
				outputStream.flush();
				outputStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}
