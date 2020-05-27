<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<title>选择座位</title>
	<link rel="stylesheet" type="text/css" href="css/ppt.css" />
	<link rel="stylesheet" type="text/css" href="css/selectseat.css" />
	<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
  </head>
  <body>
  <div class="header">
		 <div class="headertop_desc">
			<div class="wrap">
				<div class="nav_list">
					<ul>
						<li><a href="<%=basePath%>movTic/movPag/movie.html">首页</a></li>
						<li><a href="<%=basePath%>MoreSvl?state=1">热映中</a></li>
						<li><a href="<%=basePath%>MoreSvl?state=2">即将上映</a></li>
					</ul>
				</div>
					<div class="account_desc">
						<ul>
						<li><a href="<%=basePath%>RegisterSvl">注册</a></li>
						<c:if test="${user==null}">
							<li><a href="<%=basePath%>LoginSvl">登录</a>
						</c:if>
      	    		<c:if test="${user!=null}">
      	    		<li><a href="javascript:void(0)" id="uname">欢迎您!${user.uname}</a></li>
      	    		<li><a href="javascript:void(0)">我的账户:${user.account}</a></li>   
      	    		<input type="hidden" id="uaccount" value="${user.account}">
					<input type="hidden" id="user" value="${user.uname}">
					<li><a href="javascript:void(0)" onclick="openAddWin()">我的订单</a></li>
      	    			<c:if test="${user.role==1}">
      	    				<li><a href="http://10.0.19.23:8081/OnlineOrderTicketBack/">后台</a></li>
      	    			</c:if>
      	    		<li><a href="<%=basePath%>LogoutSvl">退出</a></li>
      	    		<div id="addWinow" style="padding:20px;background:rgb(246, 246, 246);position:absolute;left:5%;right:5%;top:10%;margin-left:300px;overflow-y:auto;overflow-x:hidden;z-index:3;">
      	    			 <c:forEach var="od" items="${myOrder}">
						  	<div style="min-width:150px;height:150px;margin-right:15px;margin-top:20px;float:left;font-size:0.8em;">
						   		<p>${od.mname}</p>
						   		<p>${od.begintime}</p>
						   		<p>${od.cname}</p>
						   		<p>${od.hname}</p>
						   		<p>${od.seatname}</p>
						   		<p>${od.realpay}</p>
						   		<p>${od.buytime}</p>
						   		<c:if test="${od.state == 1}">
						   			<p>未出票</p>
						   		</c:if>
						   		<c:if test="${od.state == 0}">
						   			<p>已出票</p>
						   		</c:if>
						   </div>
						  </c:forEach>
      	    		</div>	
      	    		</c:if>
						</ul>
					</div>
				<div class="clear"></div>
			</div>
	  	</div>
  <!-- ************************* -->
    <div class="main_box">
			<div class="seat_tit">
				<span class="screens_name">
						1号厅（4k）
				</span>
			</div>
			<div class="seat_list" style="background:#fff;">
				<div class="seat-tip">
					<span style="background-color: #0e9aef;" class="seat can_select"></span><span>可选座位</span>
					<span class="seat cant_select"></span><span>已售座位</span>
					<span class="seat selected"></span><span>已选座位</span>
					<!-- <span class="seat SeatOfDie"></span><span>情侣座位</span> -->
				</div>
				<div class="screen"></div>
				<div class="screen_center">
					 屏幕中央
				</div>
				<div class="seat_box">
					<ul id="y_shaft">
					</ul>
					<ul id="seats">
					</ul>
				</div>
			</div>
			<div class="order">
				<div class="order_tit">
					<img src="img/movie.png" id="movPic"/>
					<h1 id="movie_name"></h1>
					<p>版本: <span id="movie_language"></span></p>
					<p>片长: <span id="movie_time"></span>分钟</p>
				</div>
				<div class="order_context">
					<p>影院: <span id="cinema"></span></p>
					<p>影厅: <span class="screens_name"></span></p>
					<p>场次: <span id="movie_optime"></span></p>
					<p>座位: <span id="select_seat"></span></p>
				</div>
				<div class="order_price">
					<p>价格: <span id="price_original"></span></p>
					<p>售价: <span id="price_sale"></span></p>
					<p>总价: <span id="price_all"></span></p>
				</div>
				<div class="order_determine">
				<!--	<h1>接收电子码的手机号：</h1>
					<span id="user_tel">
						
					</span>  -->
					<input type="button" name="payment" id="payment" value="确认下单" />
				</div>
			</div>
		</div>
		<input type="hidden" id="aid" value="${aid}">
		<input type="hidden" id="hno" value="${hno}">
		
		<script src="js/jquery-1.11.0.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			function openAddWin(){	
				var win = document.getElementById("addWinow");
				debugger;
				if(win.style.display == 'block'){
					win.style.display = 'none';
				}else{
					win.style.display = 'block';
				}
			}	
			var movie_name = document.getElementById("movie_name");
			var movie_language = document.getElementById("movie_language");
			var cinema = document.getElementById("cinema");
			var screens_name = document.getElementsByClassName("screens_name");
			var movie_optime = document.getElementById("movie_optime");
			var price_original = document.getElementById("price_original");
			var movie_time = document.getElementById("movie_time");
			var movie_pic = document.getElementById("movPic");
			//var price_sale = document.getElementById("price_sale");
			//var user_tel = document.getElementById("user_tel");
			
			var shafty = document.getElementById("y_shaft");
			var seats = document.getElementById("seats");
			var ticket_num = 0;
			var create_shafty = function(ul, count) {				//创建Y轴
				var fragment = document.createDocumentFragment();
				var li;

				for(var i = 0; i < count; i++) {
					li = document.createElement('li');
					li.innerHTML = (i + 1);
					fragment.appendChild(li);
				}
				return fragment;
			};

			var create_seats = function(ul, count, arr) {
				var fragment = document.createDocumentFragment();
				var li;

				for(var i = 0; i < count; i++) {
					li = document.createElement('li');
					if(arr[i] == 1) {
						li.className = 'seat can_select';
					} else if(arr[i] == 0) {
						li.className = 'seat SeatNone';
					} else if(arr[i] == 2) {
						li.className = 'seat cant_select';
				/* 	} else if(arr[i] == 3) {
						li.className = 'seat SeatOfDie'; */
					} else {
						console.log("第" + (i + 1) + "号座位的状态码出错,快去查")
						li.className = 'seat cant_select';
					}
					fragment.appendChild(li);
				}
				return fragment;
			};
			
			var select_seat = document.getElementById("select_seat");
			var create_rowcol = function(ul, seat_row, sear_col, ticket_num) {
				var fragment = document.createDocumentFragment();
				var span;
				
				span = document.createElement('span');
				span.innerHTML = seat_row + "排" + sear_col + "列 ";
				span.className = "ticket_num" + ticket_num;
				fragment.appendChild(span);
				return fragment;
			};
		
			var shafty_li = shafty.getElementsByTagName("li");
			var seats_li = seats.getElementsByTagName("li");
			var seats = document.getElementById("seats");
			
			/***/
			
			$(function(){
				var win = document.getElementById("addWinow");
				win.style.display = 'none';
				var aid = document.getElementById("aid").value;
				var destURL = "<%=basePath%>ajax/HallSeatSvl?aid=" + aid;
				$.ajax({
				type: "get",
				url: destURL,
				success: function(data) {
				
					if(data == 0){
						alert("请先进行登录！");
						window.location.href = "<%=basePath%>LoginSvl";
					}else{
										var info = eval("(" + data + ")");
					movie_name.innerHTML = info[0].mov;
					movie_pic.src = info[0].movurl;
					movie_language.innerHTML = info[0].language;
					cinema.innerHTML = info[0].cname;
					screens_name[0].innerHTML = info[0].hname;
					screens_name[1].innerHTML = info[0].hname;
					movie_optime.innerHTML = info[0].begintime;
					price_original.innerHTML = info[0].price;
					price_sale.innerHTML = info[0].price;
					movie_time.innerHTML = info[0].length;
					//user_tel.innerHTML = data.user_tel;	
					var row = info[0].rows; //行
					var col = info[0].cols;
					var seat_row = 0; //行
					var seat_col = 0;
					var seats_count = row * col;
					var state_arr = new Array();
					for(var i = 0; i < info[0].seats.length; i++) {
						state_arr[i] = info[0].seats[i].state;
					}
					seats.insertBefore(create_seats(seats, info[0].seats.length, state_arr), seats.childNodes[0]);
					shafty.insertBefore(create_shafty(shafty, row), shafty.childNodes[0]);
					seats.style.width = col * 26 + "px";
			
					/***********/		
					
						var seat = seats.getElementsByClassName("seat");
			for(var i = 0; i < seat.length; i++) {
				seat[i].onclick = (function(i) {
					return function() {
						if(seat[i].className == "seat can_select") {
							var nowi = i + 1;
							ticket_num = ticket_num + 1;
							seat[i].className = "seat can_select selected seat_ticket_num" + ticket_num;
							seat_row = Math.ceil(nowi / col);
							seat_col = nowi % col;
							/*******/
							var select_seat = document.getElementById("select_seat");
							var sel_seats = select_seat.children;
							if(sel_seats.length > 3){
								alert("温馨提示：您最多只能购买4张票！");
							}
							/*******/
							if(seat_col == 0) {
								seat_col = col;
							}
							select_seat.insertBefore(create_rowcol(select_seat, seat_row, seat_col, ticket_num), select_seat.childNodes[0]);
							calculate_prices();
						} else {
							var now_ticket_num = "ticket_num" + seat[i].className.substring(40);
							var ticket_remove = document.getElementsByClassName(now_ticket_num)[0];
							ticket_remove.parentNode.removeChild(ticket_remove);

							seat[i].className = "seat can_select";
							ticket_num = ticket_num - 1;
							calculate_prices();
						}

					}
				})(i);
			}
					}
				},
				// XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象
				error: function(XMLHttpRequest, type, errorThrown) {
					//异常处理；
					console.log(type);
					alert("加载失败,请检查网络设置");
				}
				});
				
			});

			var price_all = document.getElementById("price_all");
			var price_sale = document.getElementById("price_sale");
			var calculate_prices = function() {
				var prices = price_sale.innerHTML * ticket_num;
				price_all.innerHTML = price_sale.innerHTML + "×" + ticket_num + "=" + prices;
			}
			
			//确认下单
			var payment = document.getElementById("payment");
			payment.onclick = function () {
			var user = document.getElementById("user").value;
				if(user == ""){
					alert("不好意思，您尚未登录，请登陆后进行购票！");
					//debugger;
				}else{
				
					var uaccount = document.getElementById("uaccount").value;
					//var acc = uaccount.replace('"','');
					debugger;
					var i = price_all.innerHTML.indexOf('=');
					var payMon = price_all.innerHTML.substring(i+1);
					
					var hno = document.getElementById("hno").value;
					var select_seat = document.getElementById("select_seat");
					var sel_seats = select_seat.children;
					
					if(sel_seats.length <= 4 && sel_seats.length >= 1){
					
						if(parseInt(uaccount) > parseInt(payMon)){
							
							/**获取下订单的各参数*/
							var hno = document.getElementById("hno").value;
							var sel_arr = new Array();
							for(var i=0;i<sel_seats.length;i++){
								sel_arr[i] = sel_seats[i].innerHTML;
								//sel_arr[i] = sel_arr[i].replace('列','');
								//sel_arr[i] = hno + "-" +sel_arr[i];
							}
							var seat_str = sel_arr.toString();
							var aid = document.getElementById("aid").value;
							var destURL = "<%=basePath%>user/CheckoutSvl?rid=" + aid +"&seat_str=" + seat_str + "&hno="+ hno+ "&payMon="+ payMon;
							
							$.ajax({
							   type: "POST",
							   url: destURL,
							   success: function(msg){
							   	if(msg == 1){
							   		alert("恭喜您付款成功");
							   		window.location.href = "<%=basePath%>movTic/movPag/movie2.html";
							   	}else{
							   		alert("请重新付款");
							   	}
							   },
							    error: function(msg){
							     alert( "网络出错，付款失败！" );
							   }
							}); 
							
						
						}else{ alert("您的账户余额不足，请充值！"); }
						
					}else if(sel_seats.length > 3){
					
						alert("温馨提示：您最多只能选择4个座位！");
					
					}else{
						alert("温馨提示：您还未进行选座！！");
					}
				}
			}
		</script>
  </body>
</html>
