package com.icss.dto;

import java.util.ArrayList;
import java.util.List;

public class Rondadto {
	private long aid;			//���α��
	private String mov;
	private String movurl;
	private double price;
	private String language;
	private String length;
	private String hname;
	private int rows;
	private int cols;
	private String cid;
	private String cname;
	private String address;
	private String begintime;
	List<HallSeatdto> seats;
	public Rondadto(){
		seats = new ArrayList<HallSeatdto>();
	}
	public void add(HallSeatdto seat){
		this.seats.add(seat);
	}
	public void remover(HallSeatdto seat){
		this.seats.remove(seat);
	}
	public long getAid() {
		return aid;
	}
	public void setAid(long aid) {
		this.aid = aid;
	}
	public String getMov() {
		return mov;
	}
	public void setMov(String mov) {
		this.mov = mov;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
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
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public List<HallSeatdto> getSeats() {
		return seats;
	}
	public void setSeats(List<HallSeatdto> seats) {
		this.seats = seats;
	}
	public String getMovurl() {
		return movurl;
	}
	public void setMovurl(String movurl) {
		this.movurl = movurl;
	}
}
