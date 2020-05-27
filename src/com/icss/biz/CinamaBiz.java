package com.icss.biz;

import java.util.List;

import com.icss.dao.CinamaDao;
import com.icss.dto.MovRonda;
import com.icss.entity.TCinama;
import com.icss.util.Log;

public class CinamaBiz {
	
	/**
	 * *********
	 * *********
	 */
	public  List<MovRonda>  getMovRonda(String mid,String cid) throws Exception{
		List<MovRonda> movRondas = null;
		if(mid != null){
			CinamaDao dao =new CinamaDao();
			try {
				movRondas = dao.getMovRonda(mid, cid);
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
			}finally{
				dao.closeConnection();
			}
		}else{
			throw new Exception("*********");
		}
		return movRondas;
	}
	
	/**
	 *
	 */
	public  List<TCinama>  getCinama(String cname,String dname,String mid) throws Exception{
		List<TCinama> cnas = null;
		if(cname != null && !cname.equals("") && dname != null &&!dname.equals("")){
			CinamaDao dao = new CinamaDao();
			try {
				cnas = dao.getCinama(cname, dname, mid);
			} catch (Exception e) {
				Log.logger.error("*********");
			}finally{
				dao.closeConnection();
			}
		}else{
			throw new Exception("*********");
		}

		return cnas;
	} 
}
