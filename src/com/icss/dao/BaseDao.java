package com.icss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.icss.util.DBInfo;

public class BaseDao {
	
	protected Connection conn;
	
	public void openConnection() throws Exception{
		if(this.conn == null || this.conn.isClosed()){
			DBInfo dbInfo = DBInfo.getInstance();
			Class.forName(dbInfo.getDriverName());
			this.conn = DriverManager.getConnection(dbInfo.getUrl(), dbInfo.getUname(), dbInfo.getPwd());
		}
	}
	
	public void beginTransaction() throws Exception{
		this.openConnection();
		if(conn != null){
			this.conn.setAutoCommit(false);
		}
	}
	public void commit() throws SQLException{
		if(conn != null){
			this.conn.commit();
		}
	}
	public void rollback() throws SQLException{
		if(conn != null){
			this.conn.rollback();
		}
	}
	public void closeConnection(){
		if(this.conn != null){
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public String getTurnPagesql(String sql,int istart,int iend){
		
		String newSql = "select * from (select rownum rn,t.* from ("+ sql +") t )  where rn >="
					  + istart +" and rn <= " + iend;
		return newSql;
	}
	
	//获得总记录数
	public int getAllrows(String sql) throws Exception{
		int allrows = 0;
		String newSql = "select count(*) rns from (select rownum rn,t.* from ("+ sql +") t )";
		this.openConnection();
		PreparedStatement ps  = this.conn.prepareStatement(newSql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			allrows = rs.getInt("rns");
		}
		rs.close();
		ps.close();
		return allrows;
	}
	
}
