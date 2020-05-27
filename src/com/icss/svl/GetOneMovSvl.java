package com.icss.svl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.MovieBiz;
import com.icss.dto.MyOrderdto;
import com.icss.entity.TMovie;
import com.icss.entity.TUser;
import com.icss.util.Log;

public class GetOneMovSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetOneMovSvl() {
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

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		TUser user = (TUser) request.getSession().getAttribute("user");
		if(user != null){
			request.setAttribute("user", user);
			List<MyOrderdto> myOrder = (List<MyOrderdto>) request.getSession().getAttribute("myOrder");
			request.setAttribute("myOrder", myOrder);
		}
		request.setCharacterEncoding("utf-8");
		String mname = request.getParameter("mname");
		if(mname != null && !mname.equals("")){
			MovieBiz biz = new MovieBiz();
			try {
				TMovie mov = biz.getOneMov(mname);
				request.setAttribute("state", mov.getState());
				request.setAttribute("mov", mov);
				request.getRequestDispatcher("/WEB-INF/detail/preview.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				Log.logger.error(e.getMessage());
			}
			
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
