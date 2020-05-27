package com.icss.dto;

public class TurnPage {
	private int pageno;
	private int rows = 5;
	private int allrows;
	private int allpages;
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getAllrows() {
		return allrows;
	}
	public void setAllrows(int allrows) {
		this.allrows = allrows;
	}
	public int getAllpages() {
		return allpages;
	}
	public void setAllpages(int allpages) {
		this.allpages = allpages;
	}
}
