package com.icss.svl;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.icss.biz.CinamaBiz;
import com.icss.entity.TCinama;
import com.icss.util.Log;

public class GetCinamaSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetCinamaSvl() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.setHeader("content-type", "text/html;charset=UTF-8");
		Object user = request.getSession().getAttribute("user");
		if(user!=null){
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");		
			PrintWriter out = response.getWriter();
			String ct = URLDecoder.decode(request.getParameter("city"),"utf-8");
			String mid = request.getParameter("mid");
				 String area[] = ct.split("-");
				 String cname = null;
				 String dname = null;
				 if(area.length == 3){
					 cname = area[1];
					 dname = area[2];
				 }else if(area.length == 2){
					 cname = area[1];
				 }else{
					 cname = area[0];
				 }
				 CinamaBiz biz = new CinamaBiz();
				 try {
					List<TCinama> cnas = biz.getCinama(cname, dname, mid);
					JSONArray JSONArr = JSONArray.fromObject(cnas);
					String cnInfo = JSONArr.toString();
					System.out.println(cnInfo);
					out.print(cnInfo);
					out.flush();
					out.close();
				} catch (Exception e) {
					Log.logger.error("");
					e.printStackTrace();
					request.setAttribute("errMsg", "异常");
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				}
		}else{
			PrintWriter out = response.getWriter();
			out.println("2");
			out.flush();
			out.close();
		}
		
		 
	}
	

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
