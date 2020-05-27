package com.icss.dto;

public class HallSeatdto {
	
	private String stno;						//座位号
	private int row;								//行号
	private int col;    							//列号
	private int state;   //座位状态
	//下订单成功时  要修改映票信息中上午影票状态
	
	public String getStno() {
		return stno;
	}
	public void setStno(String stno) {
		this.stno = stno;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
}
