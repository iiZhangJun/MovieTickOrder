package com.icss.svl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.UserBiz;
import com.icss.dto.MyOrderdto;
import com.icss.util.Log;

public class UserOrderSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserOrderSvl() {
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
		List<MyOrderdto> myOrders = null;
		String uname = request.getParameter("username");
		if(uname != null && !uname.equals("")){
			UserBiz biz = new UserBiz();
			try {
				myOrders = biz.getMyAllOrder(uname);
				request.setAttribute("myOrder",myOrders);
				request.getRequestDispatcher("/WEB-INF/detail/myOrder.jsp").forward(request, response);
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				request.setAttribute("errMsg", "网络出错，请联系管理员！");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("errMsg", "网络出错，请联系管理员！");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
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
