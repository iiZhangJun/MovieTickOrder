<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<head>
<title>Preview</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script> 
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script src="js/popt.js"></script>
<script src="js/cityjson.js"></script>
<script src="js/cityset.js"></script>
<style type="text/css">
* { -ms-word-wrap: break-word; word-wrap: break-word; }
html { -webkit-text-size-adjust: none; text-size-adjust: none; }
html, body {height:100%;width:100%;}
html, body, h1, h2, h3, h4, h5, h6, div, ul, ol, li, dl, dt, dd, iframe, textarea, input, button, p, strong, b, i, a, span, del, pre, table, tr, th, td, form, fieldset, .pr, .pc { margin: 0; padding: 0; word-wrap: break-word; font-family: verdana,Microsoft YaHei,Tahoma,sans-serif; *font-family: Microsoft YaHei,verdana,Tahoma,sans-serif; }
body, ul, ol, li, dl, dd, p, h1, h2, h3, h4, h5, h6, form, fieldset, .pr, .pc, em, del { font-style: normal; font-size: 100%; }
ul, ol, dl { list-style: none; }
._citys { width: 450px; display: inline-block; border: 2px solid #eee; padding: 5px; position: relative; background:#fff; }
._citys span { color: #56b4f8; height: 15px; width: 15px; line-height: 15px; text-align: center; border-radius: 3px; position: absolute; right: 10px; top: 10px; border: 1px solid #56b4f8; cursor: pointer; }
._citys0 { width: 100%; height: 34px; display: inline-block; border-bottom: 2px solid #56b4f8; padding: 0; margin: 0; }
._citys0 li { display: inline-block; line-height: 34px; font-size: 15px; color: #888; width: 80px; text-align: center; cursor: pointer; }
.citySel { background-color: #56b4f8; color: #fff !important; }
._citys1 { width: 100%; display: inline-block; padding: 10px 0; }
._citys1 a { width: 83px; height: 35px; display: inline-block; background-color: #f5f5f5; color: #666; margin-left: 6px; margin-top: 3px; line-height: 35px; text-align: center; cursor: pointer; font-size: 13px; overflow: hidden; }
._citys1 a:hover { color: #fff; background-color: #56b4f8; }
.AreaS { background-color: #56b4f8 !important; color: #fff !important; }
</style>
<script type="text/javascript">
	function valid(){
	
		var city = document.getElementById("city").value;
		var mid = document.getElementById("mid").value;
		var destURL = "<%=basePath%>ajax/GetCinamaSvl";
		debugger;
		if(city == ''){
			alert("*****请先选择观影城市*****");
			return false;
		}
		if(city != ""){
				$.getJSON(destURL, {city:encodeURI(city),mid:mid},function callback(data){
					$("#cinm").empty();
					debugger;
					if(data==2){
					    alert("您尚未登录，请先登录！");
					    window.location.href= "<%=basePath%>LoginSvl";
					}else{
						 $(data).each(function(i){
	 				 	 $("<li>影院：<a href=\"<%=basePath%>user/TimeTableSvl?mid="+ data[i].tcmid + "&cid=" +data[i].cid + "\">" + data[i].cname  + "</a></li>").appendTo("#cinm");
		            	  $("<li>地 址：" + data[i].address + "</li>").appendTo("#cinm");
		            	 }); 
					}
 				     });
			}else{
				//$("#ShiID").empty();
		   }
		//var myform = document.getElementById("myform");
		//myform.submit();
	}

	$(function(){
		var win = document.getElementById("addWinow");
		debugger;
		win.style.display = 'none';
	});
	
	function openAddWin(){	
		var win = document.getElementById("addWinow");
		debugger;
		if(win.style.display == 'block'){
			win.style.display = 'none';
		}else{
			win.style.display = 'block';
		}
	}	
</script>
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
      	    		<li><a href="javascript:void(0)">欢迎您!${user.uname}</a></li>
      	    		<input type="hidden" id="uname" value="${user.uname}">
      	    		<li><a href="javascript:void(0)">我的账户:${user.account}</a></li>   
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
						   		<hr style="width:100%;size:2px;">
						   </div>
						  </c:forEach>
      	    		</div>
      	    		</c:if>
						</ul>
					</div>
				<div class="clear"></div>
			</div>
	  	</div>
  	  		<div class="wrap">
				<div class="header_top">
					<div class="logo">
						<a href="index.html"><img src="images/logo.png" alt="" /></a>
					</div>
						<div class="header_top_right">
							  <div class="search_box">
					     		<form action="<%=basePath%>GetOneMovSvl" method="post">
					     			<input type="text" name="mname" placeholder="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}"><input type="submit" value="">
					     		</form>
					     	</div>
						 <div class="clear"></div>
					</div>
						  <script type="text/javascript">
								function DropDown(el) {
									this.dd = el;
									this.initEvents();
								}
								DropDown.prototype = {
									initEvents : function() {
										var obj = this;
					
										obj.dd.on('click', function(event){
											$(this).toggleClass('active');
											event.stopPropagation();
										});	
									}
								}
					
								$(function() {
					
									var dd = new DropDown( $('#dd') );
					
									$(document).click(function() {
										// all dropdowns
										$('.wrapper-dropdown-2').removeClass('active');
									});
					
								});
					    </script>
			   <div class="clear"></div>
  		    </div>     				
   		</div>
   </div>
   <div class="main">
   	 <div class="wrap">
   	 	<div class="content_top">
    		<div class="back-links">
    		<p><a href="<%=basePath%>MainSvl">Home</a> &gt;&gt;&gt;&gt; <a href="#" class="active">English</a></p>
    	    </div>
    		<div class="clear"></div>
    	</div>
   	 	<div class="section group">
				<div class="cont-desc span_1_of_2">
				  <div class="product-details">				
					<div class="grid images_3_of_2">
						<img src="${mov.picurl}" alt="" />
				  </div>
				<div class="desc span_3_of_2">
					<h2 style="font-size:2.2em;">${mov.mname}</h2>
					<div class="price">
						<p>时长:<span>${mov.length}</span>分钟</p>
					</div>
					<div class="available">
						<ul>
						  <li><span>类型:</span> &nbsp;${mov.type} </li>
						  <li><span>地区:</span> &nbsp;${mov.area} </li>
						  <li><span>导演:</span>&nbsp;${mov.director} </li>
						  <li><span>主演:</span>&nbsp;${mov.actor}</li>
						  <li><span>评分:</span>&nbsp;${mov.score}</li>
					    </ul>
					</div>
					<c:if test="${state == 1}">
					<form action="<%=basePath%>GetCinamaSvl" method="post" id="myform">
				<div class="share-desc">
					<input type="hidden" name="mid" value="${mov.mid}" id="mid"/>
					<div class="button"><span><input type="button" onclick="valid()" value="购票" style="width:180px;height:50px;font-size:1.5em;background:#FC7D01;color:#FFF;border:none;"></span></div>					
					<div class="clear"></div>
				</div>
			</div>
			<div class="clear"></div>
		  </div>
		  <c:if test="${state == 1||state == 2}">
		<div class="product_desc">	
			 <h2 style="font-size:1.5em;">剧情简介 :</h2>
			 <p>${mov.mdesc}</p>
	   </div>
	   </c:if>
   </div>
				<div class="rightsidebar span_3_of_1 sidebar">
					<h2>地区选择</h2>
					
					<!-- 代码 开始 -->
					<div style=" width:210px; margin:25px auto 30px auto;">
						<!-- <form action="####" method="post"> -->
							<input type="text" id="city" name="city"style="height:30px; width:200px; line-height:30px; font-size:14px; padding:0 5px;" />
							<!-- <div class="button"><span><input type="submit" value = "购票" style="width:180px;height:50px;font-size:1.5em;background:#FC7D01;color:#FFF;"/></span></div>	 -->
						<!-- </form> -->
					</div>
					</form>
					<script type="text/javascript">
					$("#city").click(function (e) {
						SelCity(this,e);
					    console.log("inout",$(this).val(),new Date())
					});
					</script>
					<!-- 代码 结束 -->
					<ul id="cinm" style="font-size:1.2em;font-color:#000;">
					</ul>
				</div>
				</c:if>	 
 					   </div>
 		 		 </div>
   	 		</div>
        </div>
        
        
        
   <div class="footer">
   	  <div class="wrap">	
	     <div class="section group">
				<div class="col_1_of_4 span_1_of_4">
						<h4>Information</h4>
						<ul>
						<li><a href="#">About Us</a></li>
						<li><a href="#">Customer Service</a></li>
						<li><a href="#">Advanced Search</a></li>
						<li><a href="#">Orders and Returns</a></li>
						<li><a href="contact.html">Contact Us</a></li>
						</ul>
					</div>
				<div class="col_1_of_4 span_1_of_4">
					<h4>Why buy from us</h4>
						<ul>
						<li><a href="#">About Us</a></li>
						<li><a href="#">Customer Service</a></li>
						<li><a href="#">Privacy Policy</a></li>
						<li><a href="contact.html">Site Map</a></li>
						<li><a href="#">Search Terms</a></li>
						</ul>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h4>My account</h4>
						<ul>
							<li><a href="contact.html">Sign In</a></li>
							<li><a href="index.html">View Cart</a></li>
							<li><a href="#">My Wishlist</a></li>
							<li><a href="#">Track My Order</a></li>
							<li><a href="contact.html">Help</a></li>
						</ul>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h4>Contact</h4>
						<ul>
							<li><span>+91-123-456789</span></li>
							<li><span>+00-123-000000</span></li>
						</ul>
						<div class="social-icons">
							<h4>Follow Us</h4>
					   		  <ul>
							      <li><a href="#" target="_blank"><img src="images/facebook.png" alt="" /></a></li>
							      <li><a href="#" target="_blank"><img src="images/twitter.png" alt="" /></a></li>
							      <li><a href="#" target="_blank"><img src="images/skype.png" alt="" /> </a></li>
							      <li><a href="#" target="_blank"> <img src="images/linkedin.png" alt="" /></a></li>
							      <div class="clear"></div>
						     </ul>
   	 					</div>
				</div>
			</div>		
        </div>
    </div>
   <script type="text/javascript">
		$(document).ready(function() {			
			$().UItoTop({ easingType: 'easeOutQuart' });
			
		});
	</script>
    <a href="#" id="toTop"><span id="toTopHover"> </span></a>

</body>
</html>

