<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script> 
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
</head>
<body>
	<div class="header">
		 <div class="headertop_desc">
			<div class="wrap">
				<div class="nav_list">
					<ul>
						<li><a href="<%=basePath%>movTic/movPag/movie.html">首页</a></li>
						<li><a href="register.jsp">注册</a></li>
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
						
			   <div class="clear"></div>
  		    </div>     				
   		</div>
   </div>
 <div class="main">
 	<div class="wrap">
     <div class="content">
   
    	<div class="section group">
    	<h4>${message}</h4>
				<div class="col span_2_of_3">
				  <div class="contact-form">
					    <form method="post" action="<%=basePath%>LoginSvl">
					    	<div>
						    	<span><label>用户名</label></span>
						    	<span><input name="uname" type="text" class="textbox" ></span>
						    </div>
						    	<span><label>密码</label></span>
						    	<span><input name="pwd" type="password" class="textbox"></span>
						    </div>
						   <div>
						   		<span><input type="submit" value="登录"  class="mybutton"></span>
						   		<span>${msg}</span>
						  </div>
						  
					    </form>
				  </div>
  				</div>
      			
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