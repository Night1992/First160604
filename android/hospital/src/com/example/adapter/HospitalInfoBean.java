package com.example.adapter;

public class HospitalInfoBean {
	private int logoRes; //ҽԺlogo
	private String name; //ҽԺ����
	private String level; //ҽԺ�ȼ�
	private int publicPraiseRes; //��������
	private String environment; //������Ϣ
	private String facility; //��ʩ��Ϣ
	private String city; //����
	private String province; //ʡ��
	
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
