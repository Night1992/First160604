package com.moxiu.filemanage.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moxiu.filemanage.R;

import android.os.Environment;

public class FileManageUtil {

	public static final String ROOT_FILE = Environment
			.getExternalStorageDirectory().getAbsolutePath();
	public static final String IS_DIR = "dir";
	public static final String CLOSE_DIR = "closeDir";
	public static final String MP3 = "mp3";
	public static final String JPG = "jpg";
	public static final String TXT = "txt";
	public static final String MP4 = "mp4";
	
	public static Map<String, Integer> map = new HashMap<String, Integer>();

	public FileManageUtil() {
		map.put(IS_DIR, R.drawable.close_dir);
		map.put(CLOSE_DIR, R.drawable.open_dir);
		map.put(MP3, R.drawable.mp3_file);
		map.put(JPG, R.drawable.image_file);
		map.put(TXT, R.drawable.txt_file);
		map.put(MP4, R.drawable.mp4_file);
	}
	
	public List<Map<String, String>> getFileList(String dir){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		File[] fs = new File(dir).listFiles();
		if (!ROOT_FILE.equals(dir)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", CLOSE_DIR);
			map.put("name","...返回上一级");
			map.put("file", "toPrevior");
			list.add(map);
		}
		for (File f : fs) {
			Map<String, String> map = new HashMap<String, String>();
			if (f.isDirectory()) {
				map.put("type", IS_DIR);
			}else if (f.isFile() && f.getName().endsWith(".mp3")) {
				map.put("type", MP3);
			}else if (f.isFile() && f.getName().endsWith(".jpg")) {
				map.put("type", JPG);
			}else if (f.isFile() && f.getName().endsWith(".mp4")) {
				map.put("type", MP4);
			}else {
				map.put("type", TXT);
			}
			map.put("name", f.getName());
			map.put("file", f.getAbsolutePath());
				
			list.add(map);
		}
		
		return list;
	}

	public boolean isDir(String path){
		if (new File(path).isDirectory()) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getRootLocation(String path){
		return new File(path).getParent();
	}
	
}
