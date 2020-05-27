package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.icss.dto.MovRonda;
import com.icss.entity.TCinama;

public class CinamaDao extends BaseDao{
	
	
	/**
	 *  tstate=2
	 */
	public List<String> updateSaleSeat(long aid,List<String> seatno) throws Exception{
		List<String> tno = new ArrayList<String>();
		String seatnos = "'";
		for(int i=0;i<seatno.size();i++){
			if(i == seatno.size()-1){
				seatnos = seatnos + seatno.get(i).trim() + "'";
			}else{
				seatnos = seatnos + seatno.get(i).trim() + "','";
			}
		}
		String sql = "update tickets set tstate=2 where aid = ? and tstate= 1 and seatno in (" + seatnos +")";
		String selSql = "select tno from tickets where aid = ? and seatno in (" + seatnos +")";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setLong(1, aid);
		//ps.setString(2, seatnos);
		ps.executeUpdate();
		ps.close();
		
		ps = this.conn.prepareStatement(selSql);
		ps.setLong(1, aid);
//		ps.setString(2, seatnos);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			tno.add(rs.getString("tno"));
		}
		return tno;
	}
	
	
	/**
	 * ��ݳ��Ρ�Ӱ�������λ��Ʋ��Ҷ�Ӧ��λ��
	 * @throws Exception 
	 */
	public List<String> getWillSaleSeat(String hno,List<String> seatName) throws Exception{
		List<String> seatno = null;
		String stname = "'";
		for(int i=0;i<seatName.size();i++){
			if(i== seatName.size()-1){
				stname =  stname + seatName.get(i).trim() + "'";
			}else{
				stname = stname + seatName.get(i).trim() + "','";
			}
		}
		String sql = "select seatno from TSEAT t where hno = ? and seatname in ("+ stname + ")" ;
		
		//String sql = "select seatno from TSEAT t where hno = ? and seatname = ? ";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, hno);
		//ps.setString(2, stname);
		ResultSet rs = ps.executeQuery();
		seatno = new ArrayList<String>();
		while(rs.next()){
			seatno.add(rs.getString("seatno"));
		}
		return seatno;
	}
	
	
	
	/**
	 * ���ĳӰƬ��ĳӰԺ�ĳ������飨��
	 * ��������ӰԺ�ĳ�������
	 */
	public  List<MovRonda>  getMovRonda(String mid,String cid) throws Exception{
		List<MovRonda> movRondas = null;
		String sql = "Select rd.aid,cn.cid,cn.CNAME,cn.ADDRESS,mh.HNAME,mh.ALLROWSNUM,mh.ALLCOLNUM,m.MID,m.MNAME,"
				   + "to_char(rd.BEGINTIME,'yyyy/MM/dd HH:MI:SS') beginDate,rd.LANGUAGE,rd.PRICE,mh.HNO"
				   + " From TCINEMA cn Inner Join CMOVIEHALL mh On cn.CID = mh.CID Inner Join TRONDA rd"
				   + " On mh.HNO = rd.HNO Inner Join TMOVIE m On m.MID = rd.MID Where m.mid ='" + mid + "'";
		if(cid != null){
			sql += " and cn.cid = '" + cid + "'";
		}
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		movRondas = new ArrayList<MovRonda>();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		while(rs.next()){
			MovRonda mrd = new MovRonda();
			mrd.setRid(rs.getLong("aid"));
			mrd.setCid(rs.getString("cid"));
			mrd.setCinama(rs.getString("CNAME"));
			mrd.setAddress(rs.getString("ADDRESS"));
			mrd.setHno(rs.getString("HNO"));
			mrd.setMovhall(rs.getString("HNAME"));
			mrd.setMov(rs.getString("MNAME"));
			mrd.setMovid(mid);
			mrd.setBeginDate(rs.getString("beginDate"));
			System.out.println(rs.getString("beginDate"));
			mrd.setLanguage(rs.getString("LANGUAGE"));
			mrd.setPrice(rs.getDouble("PRICE"));
			movRondas.add(mrd);
		}
		return movRondas;
	}
	
	/**
	 * �����--��������Ӧ��һЩӰԺ
	 * @throws Exception 
	 */
	public  List<TCinama>  getCinama(String cname,String dname,String mid) throws Exception{
		List<TCinama> cinas = null;
		String sql = "Select distinct cn.CNAME, m.mid,cn.ADDRESS, cn.CID From TCINEMA cn" +
                     " Inner Join TDISTINCT d On d.DNO = cn.DNO Inner Join TCITY ct" +
                     " On ct.CNO = d.CNO Inner Join CMOVIEHALL h On cn.CID = h.CID" +
                     " Inner Join TRONDA r On r.HNO = h.HNO Inner Join TMOVIE m On m.MID = r.MID" +
                     " where ct.cname like '%" + cname + "%'";
		if(dname != null){
			sql += " and d.dname='" + dname +"'";
		}
		if(mid != null){
			sql += " and m.mid='" + mid + "'";
		}
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		cinas = new ArrayList<TCinama>();
		while(rs.next()){
			TCinama cn = new TCinama();
			cn.setCid(rs.getString("cid"));
			cn.setCname(rs.getString("cname"));
			cn.setAddress(rs.getString("address"));
			cn.setTcmid(mid);
			cinas.add(cn);
		}
		return cinas;
	} 
}
