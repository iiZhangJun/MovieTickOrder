package com.icss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.util.Log;

public class UserAuthFilter implements Filter {

	public void destroy() {
		System.out.println("UserAuthFilter:destroy");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Log.logger.info("进入UserAuthFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		Object user = req.getSession().getAttribute("user");
		if(user == null){
			req.setAttribute("msg", "无权访问，请先登录！");
			req.getRequestDispatcher("/LoginSvl").forward(req, response);
			//rep.sendRedirect("http://10.0.19.23:8081/TicketOrder/LoginSvl");
		}else{
			chain.doFilter(req, response);
		}
		Log.logger.info("执行完UserAuthFilter");
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("UserAuthFilter:" + this.hashCode());
	}

}
