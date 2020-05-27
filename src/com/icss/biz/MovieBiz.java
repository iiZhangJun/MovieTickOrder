package com.icss.biz;

import java.util.List;

import com.icss.dao.MovieDao;
import com.icss.dto.TurnPage;
import com.icss.entity.TMovie;
import com.icss.entity.TUser;
import com.icss.util.Log;

public class MovieBiz {
	
	static int t = 1;
	static int tt = 1;
	/**
	 * 
	 * TODO
	 * MovieDao.java
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public TMovie getOneMov(String mname) throws Exception{
		TMovie mov = null;
		MovieDao dao = new MovieDao();
		try {
			mov = dao.getOneMov(mname);
		} catch (Exception e) {
			Log.logger.error(e.getMessage());
		}finally{
			dao.closeConnection();
		}
		return mov;
	}
	
	public TMovie getOneMov(String mid,int state) throws Exception{
		TMovie mov = null;
		MovieDao dao = new MovieDao();
		try {
			mov = dao.getOneMov(mid,state);
		} catch (Exception e) {
			Log.logger.error(e.getMessage());
		}finally{
			dao.closeConnection();
		}
		return mov;
	}
	
	/**
	 * 
	 */
	
	public String createOnePage(String type,int state,TurnPage tp,TUser user) throws Exception{
		tp.setRows(20);
		String desFile = null;
		/**
		 * 
		 */
		List<TMovie> hotMov = this.getAllNewMov(type,state,tp);
		
		String path = MovieBiz.class.getResource("/").getPath() + "movTic/tmp/type.html";
		path = path.substring(1);
		path = path.replace("WEB-INF/classes/", "");
		System.out.println(path);
		
		String movPth = MovieBiz.class.getResource("/").getPath() + "movTic/tmp/mov.html";
		movPth = movPth.substring(1);
		movPth = movPth.replace("WEB-INF/classes/", "");
		System.out.println(movPth);
		
		CharStreamIO io = new CharStreamIO();
		String mainStr = io.readFile11(path);
		String mainMovStr = io.readFile11(movPth);
		
		/*if(user != null){
			String wel = "欢迎您" + user.getUname();
			mainStr = mainStr.replace("<a href=\"#\" id=\"example\">��¼</a>","<li><a href=\"#\">" + wel +"<a/></li>");
			mainStr = mainStr.replace("<input type=\"hidden\" name=\"*\"/>","<li><a href=\"http://10.0.19.23:8081/TicketOrder/LogoutSvl\">�˳�</a><li>");
			if(user.getRole() == 1){
				mainStr = mainStr.replace("<input type=\"hidden\" name=\"**\"/>","<li><a href=\"http://10.0.19.23:8081/TicketOrder/back/MovInfoSvl\">��̨</a><li>");
				
			}
		}*/
		//System.out.println(mainMovStr);
		if(state == 2){
			mainStr = mainStr.replace("<h3>热映中</h3>", "<h3>即将上映</h3>");
		}
		   mainStr = mainStr.replace("%{state}%", Integer.toString(state));
		   mainStr = mainStr.replace("%{beforePage}%",Integer.toString(tp.getPageno()-1));
		   mainStr = mainStr.replace("%{afterPage}%",Integer.toString(tp.getPageno()+1));
		   mainStr = mainStr.replace("%{allPage}%",Integer.toString(tp.getAllpages()));
		for(int i = 0;i<hotMov.size();i++){
			TMovie hmov = hotMov.get(i);
			mainStr = mainStr.replace("<input type = \"hidden\" />",mainMovStr);
			String mStr = null;
			//mainStr = mainStr.replace("%{picurl}%", hmov.getPicurl());
			mainStr = mainStr.replace("%{mid}%", hmov.getMid());
			mainStr = mainStr.replace("%{state}%", Integer.toString(state));
			mainStr = mainStr.replace("%{picurl}%", hmov.getPicurl());
			mainStr = mainStr.replace("%{mname}%", hmov.getMname());
			mainStr = mainStr.replace("%{score}%", Double.toString(hmov.getScore()));
			/*************/
		}
		
		String desc =  MovieBiz.class.getResource("/").getPath();
		desc = desc.substring(1);
		desc = desc.replace("WEB-INF/classes/", "");
		System.out.println(desc);
		desc = desc + "movTic/movPag";
		desFile = desc + "/page" + tp.getPageno() + t + state + ".html";
		t++;
		io.writeFile11(mainStr,desFile);
		return desFile;
		
	}
	

	/**
	 * 
	 * @throws Exception 
	 */
	public String createMainPage(String type,TurnPage tp1,TurnPage tp2,TUser user) throws Exception{
		String desFile = null;
		String path = MovieBiz.class.getResource("/").getPath() + "movTic/tmp/main.html";
		path = path.substring(1);
		path = path.replace("WEB-INF/classes/", "");
		System.out.println(path);
		String desc =  MovieBiz.class.getResource("/").getPath() + "movTic/movPag";
		desc = desc.substring(1);
		desc = desc.replace("WEB-INF/classes/", "");
		System.out.println(desc);
		CharStreamIO io = new CharStreamIO();
		String mainTemp = io.readFile11(path);
		List<TMovie> hotMov = this.getAllNewMov(type,1,tp1);
		List<TMovie> newMov = this.getAllNewMov(type,2,tp2);
		/*if(user != null){
			String wel = "��ӭ��!" + user.getUname();
			mainTemp = mainTemp.replace("<a href=\"#\" id=\"example\">��¼</a>","<li><a href=\"#\">" + wel +"<a/></li>");
			mainTemp = mainTemp.replace("<input type=\"hidden\" name=\"*\"/>","<li><a href=\"http://10.0.19.23:8081/TicketOrder/LogoutSvl\">�˳�</a><li>");
			if(user.getRole() == 1){
				mainTemp = mainTemp.replace("<input type=\"hidden\" name=\"**\"/>","<li><a href=\"http://10.0.19.23:8081/TicketOrder/back/MovInfoSvl\">��̨</a><li>");
				
			}
		}*/
		for(int i=1;i<=5;i++){
			TMovie hmov = hotMov.get(i-1);
			mainTemp = mainTemp.replace("%{mid1"+i+"}%",hmov.getMid());
			mainTemp = mainTemp.replace("%{picurl1"+i+"}%",hmov.getPicurl());
			mainTemp = mainTemp.replace("%{mname1"+i+"}%",hmov.getMname());
			mainTemp = mainTemp.replace("%{score1"+i+"}%",Double.toString(hmov.getScore()));
		}
		for(int i=1;i<=5;i++){
			TMovie nmov = newMov.get(i-1);
			mainTemp = mainTemp.replace("%{mid2"+i+"}%",nmov.getMid());
			mainTemp = mainTemp.replace("%{picurl2"+i+"}%",nmov.getPicurl());
			mainTemp = mainTemp.replace("%{mname2"+i+"}%",nmov.getMname());
			mainTemp = mainTemp.replace("%{score2"+i+"}%",Double.toString(nmov.getScore()));
		}
		desFile = desc + "/movie" /*+ tp1.getPageno() + tp2.getPageno()*/ + ".html";
		//desFile = "/movTic/movPag/movie.html";
		System.out.println(desFile);
		io.writeFile11(mainTemp,desFile);
		return desFile;
	}
	
	/**
	 * 
	 * TODO
	 * MovieDao.java
	 * @param type
	 * @param movie
	 * @return
	 * @throws Exception
	 */
	public List<TMovie>  getAllNewMov(String type,int state,TurnPage tp) throws Exception{
		List<TMovie> movies = null;
		MovieDao dao = new MovieDao();
		try {
			movies = dao.getAllNewMov(type,state,tp);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return movies;
	}
	
}
