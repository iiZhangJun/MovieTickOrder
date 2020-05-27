package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.icss.dto.MyOrderdto;
import com.icss.entity.TUser;

public class UserDao extends BaseDao{
	
	/**
	 * 
	 */
	public void createNewOrder(double price,String tno,String uname,int state) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String oid = sdf.format(new Date().getTime());
		String sql = "insert into ToutTicket values(?,?,?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, oid);
		ps.setString(2, uname);
		ps.setString(3, tno);
		ps.setDouble(4,price);
		ps.setTimestamp(5, new java.sql.Timestamp(new Date().getTime()));
		
		ps.setInt(6,state);		//
		ps.executeUpdate();
		ps.close();
	}

	public void cutAccount(double payMon,TUser user) throws Exception{
			double left = user.getAccount() - payMon;
			String sql = "update tuser set account=? where uname=?";
			this.openConnection();
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.setDouble(1, left);
			ps.setString(2, user.getUname());
			ps.executeUpdate();
			ps.close();
			user.setAccount(left);
	}

	public List<MyOrderdto> getMyAllOrder(String uname)throws Exception{
		List<MyOrderdto> myOrders = null;
		String sql = "Select ts.SEATNAME,ot.STATE,to_char(ot.BUYTIME,'mm\"月\"dd\"日\" hh24:MI') BUYTIME,ot.REALPAY,tm.MNAME,tc.CNAME,"
				   + "to_char(tr.BEGINTIME,'mm\"月\"dd\"日\" hh24:MI') BEGINTIME,mh.HNAME From TOUTTICKET ot Inner Join TICKETS tk On ot.TNO = tk.TNO"
				   + " Inner Join TRONDA tr On tk.AID = tr.AID Inner Join TMOVIE tm On tr.MID = tm.MID"
				   + " Inner Join CMOVIEHALL mh On tr.HNO = mh.HNO Inner Join TCINEMA tc On mh.CID = tc.CID"
				   + " Inner Join TSEAT ts On tk.SEATNO = ts.SEATNO where uname = ?";
		
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, uname);
		ResultSet rs = ps.executeQuery();
		myOrders = new ArrayList<MyOrderdto>();
		while(rs.next()){
			MyOrderdto orderdto = new MyOrderdto();
			orderdto.setMname(rs.getString("mname"));
			orderdto.setCname(rs.getString("cname"));
			orderdto.setHname(rs.getString("hname"));
			orderdto.setSeatname(rs.getString("SEATNAME"));
			orderdto.setRealpay(rs.getDouble("realpay"));
			orderdto.setBuytime(rs.getString("BUYTIME"));
			orderdto.setBegintime(rs.getString("BEGINTIME"));
			orderdto.setState(rs.getInt("STATE"));
			myOrders.add(orderdto);
		}
		ps.close();
		return myOrders;
	}
	
	public void registeUser(TUser user) throws Exception{
		
		String sql = "insert into tuser values(?,?,?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, user.getUname());
		ps.setString(2, user.getPassword());
		ps.setInt(3, user.getRole());
		ps.setString(4, user.getTel());
		ps.setDouble(5, user.getAccount());
		ps.setString(6, user.getEmail());
		ps.executeUpdate();
		ps.close();
	}
	
	
	public int isSameTel(String tel) throws Exception{
		int flag = 1;
		String sql = "select * from tuser where tel = ?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, tel); 
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			flag = 0;  //存在同名
			break;
		}
		rs.close();
		ps.close();
		return flag;
	}
	public int isSameEmail(String email) throws Exception{
		int flag = 1;
		String sql = "select * from tuser where EMAIL = ?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, email); 
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			flag = 0;  //存在同名
			break;
		}
		rs.close();
		ps.close();
		return flag;
	}
	/**
	 * ͬ��У��
	 * TODO
	 * UserDao.java
	 * @param uname
	 */
	public int isSameUname(String uname) throws Exception{
		int flag = 1;
		String sql = "select * from tuser where uname = ?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, uname); 
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			flag = 0;  //存在同名
			break;
		}
		rs.close();
		ps.close();
		return flag;
	}
	
	
	/**
	 * �û���¼У��
	 * TODO
	 * UserDao.java
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public TUser login(String uname,String pwd) throws Exception{
		TUser user = null;
		String sql = "select * from tuser where uname = ? and pwd = ?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setString(2, pwd);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			user = new TUser();
			user.setUname(rs.getString("uname"));
			user.setPassword(rs.getString("pwd"));
			user.setAccount(rs.getDouble("account"));
			user.setRole(rs.getInt("role"));
			break;
		}
		rs.close();
		ps.close();
		return user;
	}
}
