package com.icss.dto;

public class HallSeatdto {
	
	private String stno;						//��λ��
	private int row;								//�к�
	private int col;    							//�к�
	private int state;   //��λ״̬
	//�¶����ɹ�ʱ  Ҫ�޸�ӳƱ��Ϣ������ӰƱ״̬
	
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
