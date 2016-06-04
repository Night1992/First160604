package com.example.adapter;

public class EvaluateInfoBean {
	private String userNmae;
	private String content;
	public String getUserNmae() {
		return userNmae == null ? "Î´ÖªÓÃ»§" : userNmae;
	}
	public void setUserNmae(String userNmae) {
		this.userNmae = userNmae;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
