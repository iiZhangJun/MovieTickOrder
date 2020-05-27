package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.icss.dto.HallSeatdto;
import com.icss.dto.MovRonda;
import com.icss.dto.RondaMovdto;
import com.icss.dto.Rondadto;
import com.icss.util.ImgServerUtil;

public class MovHallDao extends BaseDao{

	/**
	 *
	 * TODO
	 * MovHallDao.java
	 * @param aid
	 * @return
	 * @throws Exception
	 */
 
 public List<HallSeatdto> getHallSeat(long aid) throws Exception{
		List<HallSeatdto> hallSeats = null;
		
		String sql = "select tk.TSTATE,ts.ROWNO,ts.COLNO,rd.AID,tk.SEATNO From TICKETS tk" 
					+" Inner Join TRONDA rd On rd.AID = tk.AID"
					+" Inner Join CMOVIEHALL mh On rd.HNO = mh.HNO Inner Join TSEAT ts "
					+" On tk.SEATNO = ts.SEATNO And mh.HNO = ts.HNO where rd.aid = " + aid +"order by seatno";
		this.openConnection();
	    PreparedStatement ps = this.conn.prepareStatement(sql);	
	    ResultSet rs = ps.executeQuery();
	    hallSeats = new ArrayList<HallSeatdto>();
	    while(rs.next()){
	    	HallSeatdto hs = new HallSeatdto();
	    	hs.setStno(rs.getString("SEATNO"));
	    	hs.setState(rs.getInt("TSTATE"));
	    	hs.setRow(Integer.parseInt(rs.getString("ROWNO")));
	    	hs.setCol(Integer.parseInt(rs.getString("COLNO")));
	    	hallSeats.add(hs);
	    }
	    return hallSeats;
	}
 	
 	/**��øó��ζ�Ӧ��ӰƬ��Ϣ
 	 * 
 	 */
 	public Rondadto getRondaMov(long aid) throws Exception{
 		Rondadto movRonda = null;
 		String sql = "Select to_char(rd.BEGINTIME,'yyyy/MM/dd HH24:MI:SS') beginDate,rd.LANGUAGE,rd.PRICE,mh.HNAME,mh.HNO,m.MNAME,m.picurl,cn.CID,"
 				   + "m.Length,cn.ADDRESS,cn.CNAME,mh.ALLROWSNUM,mh.ALLCOLNUM From TRONDA rd"
 				   + " Inner Join CMOVIEHALL mh On mh.HNO = rd.HNO Inner Join TMOVIE m"
 				   + " On m.MID = rd.MID Inner Join TCINEMA cn On cn.CID = mh.CID"
 				   + " where rd.aid = " + aid;
 		this.openConnection();
	    PreparedStatement ps = this.conn.prepareStatement(sql);	
	    ResultSet rs = ps.executeQuery();
	    ImgServerUtil imgUtil = ImgServerUtil.newInstance();
	    while(rs.next()){
	    	movRonda = new Rondadto();
	    	movRonda.setAid(aid);
	    	movRonda.setAddress(rs.getString("ADDRESS"));
	    	movRonda.setCid(rs.getString("CID"));
	    	movRonda.setCname(rs.getString("CNAME"));
	    	movRonda.setMov(rs.getString("MNAME"));
	    	movRonda.setMovurl(imgUtil.getAccesspath() + rs.getString("picurl"));
	    	movRonda.setHname(rs.getString("HNAME"));
	    	movRonda.setBegintime(rs.getString("beginDate"));
	    	movRonda.setLanguage(rs.getString("LANGUAGE"));
	    	movRonda.setLength(rs.getString("Length"));
	    	movRonda.setPrice(rs.getDouble("PRICE"));
	    	movRonda.setRows(Integer.parseInt(rs.getString("ALLROWSNUM")));
	    	movRonda.setCols(Integer.parseInt(rs.getString("ALLCOLNUM")));
 	}
	    return movRonda;
 	}
 	
}
