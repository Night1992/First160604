package com.lx.logistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.Result;
import com.lx.qrcode.RGBLuminanceSource;

public class QrCodeActivity extends Activity implements OnClickListener {

	private Button generateButton;
	private Button decodeQrCodeButton;
	private ImageView imageView;
	private TextView textView;
	private EditText contentText;
	private static int QR_WIDTH = 400;
	private static int QR_HEIGHT = 400;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qr_code);
		initWidget();
	}

	private void initWidget() {
		generateButton = (Button) findViewById(R.id.main_btn_generateQRCode);
		generateButton.setOnClickListener(this);

		decodeQrCodeButton = (Button) findViewById(R.id.main_btn_decodeQRCode);
		decodeQrCodeButton.setOnClickListener(this);

		imageView = (ImageView) findViewById(R.id.main_iv);
		textView = (TextView) findViewById(R.id.main_tv);
		contentText = (EditText) findViewById(R.id.main_et);
		
		String[] dataStr = getIntent().getStringArrayExtra("dataStr");
		contentText.setText(dataStr[0] + "," + dataStr[1] + "," + dataStr[2] + "," + dataStr[3]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == generateButton) {
			String text = contentText.getText().toString();
			if (text.equals("") || text == null) {
				Toast.makeText(this, "请输入内容！", Toast.LENGTH_SHORT).show();
				return;
			}
			Bitmap bitmap = createBitmap(text);
			if (bitmap != null) {
				imageView.setImageBitmap(bitmap);
				saveBitmap(bitmap);
				Toast.makeText(this, "二维码图片已保存至/sdcard/image/目录下", Toast.LENGTH_SHORT).show();
			}
		} else if (v == decodeQrCodeButton) {
			String content = readImage(imageView);
			textView.setText(content);
		}
	}

	// 生成二维码
	public Bitmap createBitmap(String text) {
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
		String imageName = contentText.getText().toString() + ".jpg";
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

	//解析QR图内容
	public String readImage(ImageView imageView) {
		String content = null;
		Map<DecodeHintType, String> hints = new HashMap<DecodeHintType, String>();
		hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
		
		//获得解析的图片
		Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
		RGBLuminanceSource source = new RGBLuminanceSource(bitmap);
		
		BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
		QRCodeReader reader = new QRCodeReader();
		
		try {
			Result result = reader.decode(bitmap1,hints);
			content = result.getText();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ChecksumException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return content;
	}

}
