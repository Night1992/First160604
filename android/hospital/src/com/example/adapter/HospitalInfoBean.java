package com.example.adapter;

public class HospitalInfoBean {
	private int logoRes; //医院logo
	private String name; //医院名称
	private String level; //医院等级
	private int publicPraiseRes; //总体评价
	private String environment; //环境信息
	private String facility; //设施信息
	private String city; //城市
	private String province; //省份
	
	public int getLogoRes() {
		return logoRes;
	}
	public void setLogoRes(int logoRes) {
		this.logoRes = logoRes;
	}
	public String getName() {
		return name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getPublicPraiseRes() {
		return publicPraiseRes;
	}
	public void setPublicPraiseRes(int publicPraiseRes) {
		this.publicPraiseRes = publicPraiseRes;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	
	
}
