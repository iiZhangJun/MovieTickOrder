package com.icss.svl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.dto.MyOrderdto;
import com.icss.entity.TUser;

public class MidlSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MidlSvl() {
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
		TUser user = (TUser) request.getSession().getAttribute("user");
		if(user != null){
			request.setAttribute("user", user);
			List<MyOrderdto> myOrder = (List<MyOrderdto>) request.getSession().getAttribute("myOrder");
			request.setAttribute("myOrder", myOrder);
		}
		String aid = request.getParameter("rid");
		String hno = request.getParameter("hno");
		request.setAttribute("aid", aid);
		request.setAttribute("hno", hno);
		request.getRequestDispatcher("/WEB-INF/detail/seatSelect.jsp").forward(request, response);
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
