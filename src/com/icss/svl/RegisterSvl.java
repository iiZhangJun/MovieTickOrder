package com.icss.svl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.UserBiz;
import com.icss.entity.TUser;

public class RegisterSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterSvl() {
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
		request.getRequestDispatcher("/WEB-INF/detail/register.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String uname = new String(request.getParameter("userName").getBytes("ISO-8859-1"),"utf-8")   ;
		String uEmail = request.getParameter("userEmail");
		String utel = request.getParameter("userPhone");
		String userPwd2 = request.getParameter("userPwd2");
		
		UserBiz biz = new UserBiz();
		TUser user = new TUser();
		user.setUname(uname);
		user.setTel(utel);
		user.setEmail(uEmail);
		user.setPassword(userPwd2);
		try {
			try {
				biz.registeUser(user);
				request.setAttribute("message","恭喜您，成功注册，请登录！");
				request.getRequestDispatcher("/WEB-INF/detail/login.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("msg","网络出错，注册失败，请重新注册！");
				request.getRequestDispatcher("/WEB-INF/detail/register.jsp").forward(request, response);
			}
			
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
