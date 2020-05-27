package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.icss.dto.TurnPage;
import com.icss.entity.TMovie;
import com.icss.entity.TSeat;
import com.icss.util.ImgServerUtil;

public class MovieDao extends BaseDao{
	
	public TMovie getOneMov(String mname) throws Exception{
		TMovie mov = null;
		String sql="Select * From TMOVIE where mname like '%" + mname + "%'";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			ImgServerUtil isUtil = ImgServerUtil.newInstance();
			mov = new TMovie();
			mov.setActor(rs.getString("actor"));
			mov.setArea(rs.getString("area"));
			mov.setDirector(rs.getString("director"));
			mov.setLength(rs.getString("length"));
			mov.setMid(rs.getString("mid"));
			mov.setMname(rs.getString("mname"));
			mov.setScore(rs.getDouble("score"));
			mov.setType(rs.getString("type"));
			mov.setPicurl(isUtil.getAccesspath()+rs.getString("picurl"));
			mov.setMdesc(rs.getString("mdesc"));
			mov.setState(Integer.parseInt(rs.getString("state")));
		}
		return mov;
	}
	
	
	
	
	/**
	 * ��ȡ��Ӱ����
	 * TODO
	 * MovieDao.java
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public TMovie getOneMov(String mid,int state) throws Exception{
		TMovie mov = null;
		String sql="Select * From TMOVIE where mid = ? and state = ?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, mid);
		ps.setInt(2, state);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			ImgServerUtil isUtil = ImgServerUtil.newInstance();
			mov = new TMovie();
			mov.setMid(mid);
			mov.setActor(rs.getString("actor"));
			mov.setArea(rs.getString("area"));
			mov.setDirector(rs.getString("director"));
			mov.setLength(rs.getString("length"));
			mov.setMid(rs.getString("mid"));
			mov.setMname(rs.getString("mname"));
			mov.setScore(rs.getDouble("score"));
			mov.setType(rs.getString("type"));
			mov.setPicurl(isUtil.getAccesspath()+rs.getString("picurl"));
			mov.setMdesc(rs.getString("mdesc"));
			mov.setState(state);
		}
		return mov;
	}
	
	
	/**
	 * �����Ͳ�ѯ  ���ҳ��
	 */
	public List<TMovie>  getAllNewMov(String type,int state,TurnPage tp) throws Exception{
		List<TMovie> tmovies = null;
		String sql="Select * From TMOVIE where state =" + state;
		if(type != null && !type.equals("")){
			sql += " and type like " + "'%" + type + "%'";
		}
		int allrows = this.getAllrows(sql);
		int allpages = (allrows-1)/tp.getRows()+1;
		tp.setAllrows(allrows);
		tp.setAllpages(allpages);
		int pageno = tp.getPageno();
		if(tp.getPageno()>tp.getAllpages()){
			pageno = tp.getAllpages();
			tp.setPageno(pageno);
		}
		
		int istart = (tp.getPageno()-1)*tp.getRows()+1;
		int iend = istart + tp.getRows() - 1;
		String newSql = this.getTurnPagesql(sql,istart,iend);
		
		this.openConnection();
		PreparedStatement  ps = this.conn.prepareStatement(newSql);
        ResultSet	rs = ps.executeQuery();
        tmovies = new ArrayList<TMovie>();
        ImgServerUtil isUtil = ImgServerUtil.newInstance();
        while(rs.next()){
        	TMovie tmovie = new TMovie();
        	tmovie.setActor(rs.getString("actor"));
        	tmovie.setArea(rs.getString("area"));
        	tmovie.setDirector(rs.getString("director"));
        	tmovie.setLength(rs.getString("length"));
        	tmovie.setMid(rs.getString("mid"));
        	tmovie.setMname(rs.getString("mname"));
        	tmovie.setScore(rs.getDouble("score"));
        	tmovie.setType(rs.getString("type"));
        	tmovie.setPicurl(isUtil.getAccesspath()+rs.getString("picurl"));
        	tmovie.setMdesc(rs.getString("mdesc"));
        	tmovie.setState(state);
        	tmovies.add(tmovie);
        }
        rs.close();
        ps.close();
		return tmovies;
	}
	
	/*
	*//**
	 * ��ҳ��ѯĳ״̬��������ӳ��������ӳ�������еĵ�Ӱ
	 * @throws Exception 
	 *//*
	public List<TMovie>  getAllNewMov(int state,TurnPage tp) throws Exception{
		List<TMovie> tmovies = null;
		String sql="Select * From TMOVIE where state =" + state;
		int allrows = this.getAllrows(sql);
		int allpages = (allrows-1)/tp.getRows()+1;
		tp.setAllrows(allrows);
		tp.setAllpages(allpages);
		int pageno = tp.getPageno();
		if(tp.getPageno()>tp.getAllpages()){
			pageno = tp.getAllpages();
			tp.setPageno(pageno);
		}
		
		int istart = (tp.getPageno()-1)*tp.getRows()+1;
		int iend = istart + tp.getRows() - 1;
		String newSql = this.getTurnPagesql(sql,istart,iend);
		
		this.openConnection();
		PreparedStatement  ps = this.conn.prepareStatement(newSql);
        ResultSet	rs = ps.executeQuery();
        tmovies = new ArrayList<TMovie>();
        ImgServerUtil isUtil = ImgServerUtil.newInstance();
        while(rs.next()){
        	TMovie tmovie = new TMovie();
        	tmovie.setActor(rs.getString("actor"));
        	tmovie.setArea(rs.getString("area"));
        	tmovie.setDirector(rs.getString("director"));
        	tmovie.setLength(rs.getString("length"));
        	tmovie.setMid(rs.getString("mid"));
        	tmovie.setMname(rs.getString("mname"));
        	tmovie.setScore(rs.getDouble("score"));
        	tmovie.setType(rs.getString("type"));
        	tmovie.setPicurl(isUtil.getAccesspath()+rs.getString("picurl"));
        	tmovies.add(tmovie);
        }
        rs.close();
        ps.close();
		return tmovies;
	}
	*/
	/*
	*//**
	 * �����ҳ��Ӱ
	 * TODO
	 * MovieDao.java
	 * @param type
	 * @param movie
	 * @return
	 * @throws Exception
	 *//*
	public  List<TMovie> queryMovie(String type,String movie,TurnPage tp)throws Exception{
		List<TMovie> tmovies = null;
		
		String sql="Select  MID,MNAME,LENGTH,AREA,TYPE, DIRECTOR,ACTOR,SCORE,STATE,PICURL  From TMOVIE";
		if(type  != null && !type.equals("")){
			sql = sql +"where type='"+type; 
		}
		if(movie != null && !movie.equals("")){
			sql = sql+ " where mname like'%"+movie+"%' or  actor like'%"+movie+"%'";
		}
		
		int allrows = this.getAllrows(sql);
		int allpages = (allrows-1)/tp.getRows()+1;
		tp.setAllrows(allrows);
		tp.setAllpages(allpages);
		int pageno = tp.getPageno();
		if(tp.getPageno()>tp.getAllpages()){
			pageno = tp.getAllpages();
			tp.setPageno(pageno);
		}
		
		int istart = (tp.getPageno()-1)*tp.getRows()+1;
		int iend = istart + tp.getRows() - 1;
		String newSql = this.getTurnPagesql(sql,istart,iend);
		
		this.openConnection();
		PreparedStatement  ps = this.conn.prepareStatement(newSql);
        ResultSet	rs = ps.executeQuery();
        tmovies = new ArrayList<TMovie>();
        ImgServerUtil isUtil = ImgServerUtil.newInstance();
        while(rs.next()){
        	TMovie tmovie = new TMovie();
        	tmovie.setActor(rs.getString("actor"));
        	tmovie.setArea(rs.getString("area"));
        	tmovie.setDirector(rs.getString("director"));
        	tmovie.setLength(rs.getString("length"));
        	tmovie.setMid(rs.getString("mid"));
        	tmovie.setMname(rs.getString("mname"));
        	tmovie.setScore(rs.getDouble("score"));
        	tmovie.setType(rs.getString("type"));
        	tmovie.setPicurl(isUtil.getAccesspath()+rs.getString("picurl"));
        	tmovie.setMdesc(rs.getString("mdesc"));
        	tmovie.setState(state);
        	tmovies.add(tmovie);
        }
        rs.close();
        ps.close();
        
		return tmovies;
	}*/
}
