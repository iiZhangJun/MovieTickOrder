package com.icss.svl.back;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.MovieBiz;
import com.icss.dto.TurnPage;
import com.icss.entity.TUser;

public class MainRenewSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MainRenewSvl() {
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
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		TUser user = (TUser) request.getSession().getAttribute("user");
		MovieBiz biz = new MovieBiz();
		TurnPage tp = new TurnPage();
		tp.setPageno(1);
		try {
			biz.createMainPage(null, tp,tp,user);
			request.getRequestDispatcher("/movTic/movPag/movie.html").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
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
