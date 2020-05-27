package com.icss.svl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.UserBiz;

public class ValidTelSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ValidTelSvl() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String tel = request.getParameter("tel");
		int flag = -1;
		if(tel != null && !tel.equals("")){
			UserBiz biz = new UserBiz();
			try {
				flag = biz.isSameTel(tel);
				out.println(flag);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			request.setAttribute("errMsg", "网络出错，请咨询管理员！");
			request.getRequestDispatcher("/error.jsp");
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
