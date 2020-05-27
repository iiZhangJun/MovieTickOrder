package com.icss.svl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.MovieBiz;
import com.icss.dto.TurnPage;
import com.icss.entity.TUser;

public class MoreSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MoreSvl() {
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
		MovieBiz biz = new MovieBiz();
		TurnPage tp = new TurnPage();
		String page = request.getParameter("page");
		if(page != null){
			int currentPage = Integer.parseInt(page);
			if(currentPage < 1){
				currentPage = 1;
			}else{
				tp.setPageno(currentPage);
			}
		}else{
			tp.setPageno(1);
		}
		
		String type = request.getParameter("type");
		System.out.println(type);
		String state = request.getParameter("state");
		System.out.println(state);
		int sta = 0;
		if(state != null){
			sta = Integer.parseInt(state);
		}
		try {
			String desc = biz.createOnePage(type,sta,tp,user);
			System.out.println(desc);
			request.getRequestDispatcher(desc.replace("D:/Java/apache-tomcat-6.0.18/webapps/TicketOrder/", "")).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
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