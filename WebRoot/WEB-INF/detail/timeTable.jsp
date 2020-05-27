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
    
    <title>My JSP 'timeTable.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script> 
	<script type="text/javascript">
	$(function(){
		var win = document.getElementById("addWinow");
		win.style.display = 'none';
		debugger;
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
	<style type="text/css">
		*{
		margin:0;
		}
		table.hovertable {
			
			font-family: verdana,arial,sans-serif;
			font-size:15px;
			color:#333333;
			border-width: 1px;
			border-color: #999999;
			border-collapse: collapse;
			width:90%;
			height:150px;
			margin:100px auto;
		}
		table.hovertable th {
			background-color:#c3dde0;
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #a9c6c9;
		}
		table.hovertable tr {
			background-color:#d4e3e5;
		}
		table.hovertable td {
			border-width: 1px;
			padding: 25px;
			border-style: solid;
			border-color: #a9c6c9;
			height:100px;
			text-align: center;
		}
	</style>
	
  </head>
  <body style="background-image:url(images/banner2.jpg);width:100%;height:700px;">
  	<div class="header">
		 <div class="headertop_desc">
			<div class="wrap">
				<div class="nav_list">
					<ul>
						<li><a href="<%=basePath%>MainSvl">首页</a></li>
						<li><a href="<%=basePath%>MoreSvl?state=1">热映中</a></li>
						<li><a href="<%=basePath%>MoreSvl?state=2">即将上映</a></li>
					</ul>
				</div>
					<div class="account_desc">
						<ul>
						<li><a href="register.jsp">注册</a></li>
						<c:if test="${user==null}">
							<li><a href="<%=basePath%>LoginSvl">登录</a>
						</c:if>
      	    		<c:if test="${user!=null}">
      	    		<li><a href="javascript:void(0)">欢迎您!${user.uname}&nbsp;&nbsp;</a></li>
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
						   </div>
						  </c:forEach>
      	    		</div>	
      	    		</c:if>
						</ul>
					</div>
				<div class="clear"></div>
			</div>
	  	</div>
	  	
  	  	<div style="width:100%;height:700px;">
   		<center>
		   <c:if test="${mrds == null}">
		   	<h2>不好意思，该影院暂时没有对应的场次表</h2>
		   </c:if>
		  <c:if test="${mrds!=null}">
		 <h1 style="font-size:1.5em;color:rgb(221,78,66);height:75px;padding-top:15px;">欢迎来到${cinema},${mov}的场次表如下</h1>
		  <table class="hovertable" align="center">
		  <tr height="10"></tr>
				<tr>
					<th>放映厅</th><th>语言版本</th><th>放映时间</th><th>价钱</th><th>在线选座</th>
				</tr>
				<c:forEach var="mrd" items="${mrds}">
				<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
					<td>${mrd.movhall}</td><td>${mrd.language}</td><td>${mrd.beginDate}</td><td>${mrd.price}</td><td align="center"><a href="<%=basePath%>user/MidlSvl?rid=${mrd.rid}&hno=${mrd.hno}" >选座购票</a></td>
				</tr>
				</c:forEach>		
		</table>
		 </c:if>
  	 </center>
   </div>
  </body>
</html>
