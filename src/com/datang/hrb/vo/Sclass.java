package com.datang.hrb.vo;

public class Sclass {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYx() {
		return yx;
	}
	public void setYx(String yx) {
		this.yx = yx;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	private String name;		//教室名称
	private String yx;				//所属院校
	private String zy;				//所属专业
	private int num;				//班级人数

}
