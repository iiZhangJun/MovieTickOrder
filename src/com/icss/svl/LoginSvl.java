package com.icss.svl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.icss.biz.UserBiz;
import com.icss.dto.MyOrderdto;
import com.icss.entity.TUser;
import com.icss.util.Log;

public class LoginSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginSvl() {
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
      	response.setCharacterEncoding("utf-8");
      	PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		request.getRequestDispatcher("/WEB-INF/detail/login.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
      	response.setCharacterEncoding("utf-8");
      	PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		UserBiz biz = new UserBiz();
		TUser user;
		try {
			user = biz.login(uname, pwd);
			if(user != null){	
				request.getSession().setAttribute("user", user);
				List<MyOrderdto> myOrders = null;
				try {
					myOrders = biz.getMyAllOrder(user.getUname());
					request.getSession().setAttribute("myOrder", myOrders);
					request.setAttribute("myOrder",myOrders);
					response.sendRedirect("http://10.0.19.23:8081/TicketOrder/movTic/movPag/movie2.html");
				} catch (Exception e) {
					Log.logger.error(e.getMessage());
					request.setAttribute("errMsg", "网络出错，请联系管理员！");
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("msg", "此用户不存在，请核实信息后重新登录！");
				request.getRequestDispatcher("/WEB-INF/detail/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "异常");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
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
