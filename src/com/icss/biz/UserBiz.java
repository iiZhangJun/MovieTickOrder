package com.icss.biz;

import java.util.Date;

import java.util.List;

import com.icss.dao.CinamaDao;
import com.icss.dao.UserDao;
import com.icss.dto.MyOrderdto;
import com.icss.dto.TurnPage;
import com.icss.entity.TUser;
import com.icss.util.Log;

public class UserBiz {
	

	public int OrderTick(TUser user,double payMony,long aid,String hno,List<String> seats,int state) throws Exception{
		int t = 0;
		if(user != null && hno != null && !hno.equals("") && seats != null){
			
			UserDao udao = new UserDao();
			CinamaDao cdao = new CinamaDao();
			
				if(payMony <= user.getAccount()){
					try {
						 udao.beginTransaction();
						 udao.cutAccount(payMony,user);
						 cdao.setConn(udao.getConn());
						 List<String> sno = cdao.updateSaleSeat(aid,cdao.getWillSaleSeat(hno, seats));
						for(int i = 0;i<sno.size();i++){
							udao.createNewOrder((payMony/sno.size()),sno.get(i),user.getUname(),state);
						}
						udao.commit();
						t = 1;
					}catch (Exception e) {
						e.printStackTrace();
						Log.logger.error(e.getMessage());
						udao.rollback();
					}finally{
						udao.closeConnection();
					}
				}else{
					throw new Exception("余额不足，请及时充值！");
				}
			}else{
			throw new Exception("参数错误");
		}
		return t;
	}
	
	public List<MyOrderdto> getMyAllOrder(String uname)throws Exception{
		List<MyOrderdto> myOrders = null;
		if(uname != null && !uname.equals("")){
			UserDao dao = new UserDao();
			try {
				myOrders = dao.getMyAllOrder(uname);
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				throw e;
			}finally{
				dao.closeConnection();
			}
		}else{
			throw new Exception("uname参数出错");
			
		}
		return myOrders;
	}
	
	
	public void registeUser(TUser user) throws Exception{
		if(user != null){
			UserDao dao = new UserDao();
			try {
				dao.registeUser(user);
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				throw e;
			}finally{
				dao.closeConnection();
			}
		}else{
			throw new Exception("user参数出错");
		}
	}
	
	
	public int isSameTel(String tel) throws Exception{
		int flag = -1;
		if(tel != null && !tel.trim().equals("")){
			UserDao dao = new UserDao();
			try {
				flag = dao.isSameTel(tel);
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				throw e;
			}finally{
				dao.closeConnection();
			}
		}
		return flag;
	}
	
	public int isSameEmail(String email) throws Exception{
		int flag = -1;
		if(email != null && !email.trim().equals("")){
			UserDao dao = new UserDao();
			try {
				flag = dao.isSameEmail(email);
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				throw e;
			}finally{
				dao.closeConnection();
			}
		}
		return flag;
	}
	
	/**
	 * 
	 */
	public int isSameUname(String uname) throws Exception{
		int flag = -1;
		if(uname != null && !uname.trim().equals("")){
			UserDao dao = new UserDao();
			try {
				flag = dao.isSameUname(uname);
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				throw e;
			}finally{
				dao.closeConnection();
			}
		}
		return flag;
	}
	
	/**
	 *
	 * TODO
	 * UserBiz.java
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public TUser login(String uname,String pwd) throws Exception{
		TUser user = null;
		if(uname != null && pwd!= null && !uname.equals("") && !pwd.equals("")){
			UserDao dao = new UserDao();
			try {
				user = dao.login(uname, pwd);
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				throw e;
			}finally{
				dao.closeConnection();
			}
		}
		return user;
	}


}
