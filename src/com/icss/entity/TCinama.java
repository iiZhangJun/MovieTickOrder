package com.icss.entity;

public class TCinama {
	private String cid;
	private String dno;
	private String cname;
	private String address;
	private String tcmid;			//用于查询时使用
	
	public String getTcmid() {
		return tcmid;
	}
	public void setTcmid(String tcmid) {
		this.tcmid = tcmid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getDno() {
		return dno;
	}
	public void setDno(String dno) {
		this.dno = dno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
