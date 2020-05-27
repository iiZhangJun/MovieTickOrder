package com.icss.biz;

import java.util.List;

import com.icss.dao.MovHallDao;
import com.icss.dto.HallSeatdto;
import com.icss.dto.MovRonda;
import com.icss.dto.Rondadto;
import com.icss.entity.TSeat;
import com.icss.util.Log;

public class MovHallBiz {
	
	/**
	 *
	 */
	public Rondadto getRondaInfo(long aid) throws Exception{
		MovHallDao dao = new MovHallDao();
		Rondadto rondadto = new Rondadto();;
		try {
			rondadto = dao.getRondaMov(aid);
			rondadto.setSeats(dao.getHallSeat(aid));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("场次信息获取失败");
		}
		return rondadto;
 	}
	
	/**
	 * 
	 * @throws Exception 
	 */
	public List<HallSeatdto> getHallSeat(long aid) throws Exception{
		List<HallSeatdto> seats = null;
		MovHallDao dao = new MovHallDao();
		try {
			seats = dao.getHallSeat(aid);
		} catch (Exception e) {
			Log.logger.error(e.getMessage());
			throw new Exception("****");
		}finally{
			dao.closeConnection();
		}
		return seats;
	}
	
}
