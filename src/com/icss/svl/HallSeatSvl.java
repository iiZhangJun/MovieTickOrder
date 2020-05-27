package com.icss.svl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.icss.biz.MovHallBiz;
import com.icss.dto.Rondadto;
import com.icss.entity.TUser;

public class HallSeatSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public HallSeatSvl() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		TUser user = (TUser) request.getSession().getAttribute("user");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(user != null){
			String rd = request.getParameter("aid");
			if(rd != null){
				long aid = Integer.parseInt(rd);
				MovHallBiz biz = new MovHallBiz();
				Rondadto ronda = null;
				try {
					ronda = biz.getRondaInfo(aid);
					JSONArray JSONArr = JSONArray.fromObject(ronda);
					System.out.println(JSONArr.toString());
					out.println(JSONArr.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else{
			out.print(0);
		}
		out.flush();
		out.close();
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
