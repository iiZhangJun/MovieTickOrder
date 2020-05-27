package com.icss.svl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.CinamaBiz;
import com.icss.dto.MovRonda;
import com.icss.dto.MyOrderdto;
import com.icss.entity.TUser;

public class TimeTableSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TimeTableSvl() {
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
		if(user != null){
			request.setAttribute("user", user);
			List<MyOrderdto> myOrder = (List<MyOrderdto>) request.getSession().getAttribute("myOrder");
			request.setAttribute("myOrder", myOrder);
		}
		String mid = request.getParameter("mid");
		String cid = request.getParameter("cid");
		if(mid != null){
			CinamaBiz biz = new CinamaBiz();
			try {
				List<MovRonda> mrds = biz.getMovRonda(mid, cid);
				request.setAttribute("mrds", mrds);
				request.setAttribute("cinema",mrds.get(0).getCinama());
				request.setAttribute("mov", mrds.get(0).getMov());
				request.getRequestDispatcher("/WEB-INF/detail/timeTable.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errMsg", "网络错误");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}else{
			 request.setAttribute("errMsg", "ӰƬ��������");
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
