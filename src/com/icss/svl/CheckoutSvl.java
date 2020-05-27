package com.icss.svl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.UserBiz;
import com.icss.dao.UserDao;
import com.icss.dto.MyOrderdto;
import com.icss.entity.TUser;
import com.icss.util.Log;

public class CheckoutSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CheckoutSvl() {
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

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		TUser user = (TUser) request.getSession().getAttribute("user");
		
		String seat_str = request.getParameter("seat_str");
		String hno = request.getParameter("hno");
		String rid = request.getParameter("rid");
		String payMoney = request.getParameter("payMon");
		
		if(seat_str != null && !seat_str.equals("") && hno != null && !hno.equals("")&& rid != null && !rid.equals("")&& payMoney != null && !payMoney.equals("") ){
			String seatArr[] =seat_str.split(",");
			List<String> seats = new ArrayList<String>();
			if(seatArr.length>0){
				for(int i=0;i<seatArr.length;i++){
					seats.add(seatArr[i]);
				}
			}
			long aid = Integer.parseInt(rid);
			double payMon = Double.parseDouble(payMoney);
			UserBiz biz = new UserBiz();
			
			try {
				int t = biz.OrderTick(user, payMon, aid,hno,seats,1);
				if(user != null && t==1){
					request.setAttribute("user", user);
					List<MyOrderdto> myOrders = null;
					try {
						myOrders = biz.getMyAllOrder(user.getUname());
						request.getSession().setAttribute("myOrder", myOrders);
					} catch (Exception e) {
						Log.logger.error(e.getMessage());
						request.setAttribute("errMsg", "网络出错，请联系管理员！");
						request.getRequestDispatcher("/error.jsp").forward(request, response);
					}
				}
				out.print(t);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				Log.logger.error(e.getMessage());
			}
			
		}else{
			request.setAttribute("errMsg", "网络出错，请咨询管理员！");
			request.getRequestDispatcher("/error.jsp");
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
