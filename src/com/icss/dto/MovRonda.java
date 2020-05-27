package com.icss.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MovRonda {
	
	
	private String cid;			//影院id
	private String cinama;
	private String address;
	private String mov;
	private String hno;
	private String language;
	private String beginDate;
	private double price;
	private String movid;
	private long rid;	//场次id
	private String movhall;
	private int allrows;
	private int allcols;
	private int row;
	private int col;
	private int state;
	
	
	public long getRid() {
		return rid;
	}
	public void setRid(long rid) {
		this.rid = rid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMovid() {
		return movid;
	}
	public void setMovid(String movid) {
		this.movid = movid;
	}
	public String getCinama() {
		return cinama;
	}
	public void setCinama(String cinama) {
		this.cinama = cinama;
	}
	public String getMov() {
		return mov;
	}
	public void setMov(String mov) {
		this.mov = mov;
	}
	public String getHno() {
		return hno;
	}
	public void setHno(String hno) {
		this.hno = hno;
	}
	public String getMovhall() {
		return movhall;
	}
	public void setMovhall(String movhall) {
		this.movhall = movhall;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getAllrows() {
		return allrows;
	}
	public void setAllrows(int allrows) {
		this.allrows = allrows;
	}
	public int getAllcols() {
		return allcols;
	}
	public void setAllcols(int allcols) {
		this.allcols = allcols;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
