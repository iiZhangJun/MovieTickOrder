<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML>
<head>
<title>注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script> 
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">

	function validUname(){
	var uname = document.getElementById("userName").value;
	var destURL = "<%=basePath%>ValidUnameSvl?uname=" + uname;
	var tishi1 = document.getElementById("tishi1");
	if(uname==""){
		tishi1.innerHTML="请输入用户名！";
		return false;
	}
	tishi1.innerHTML="";
		debugger;
		$.ajax({
		   type: "GET",
		   url: destURL,
		   success: function(msg){
		   	if(msg==1){
		   		tishi1.innerHTML = "√√√";
		   	}else if(msg==0){
		   		tishi1.innerHTML = "该用户名已被注册";
		   		return false;
		   	}else{
		   		alert("网络出错");
		   		return false;
		   	}
		   }
		}); 
	}
	
	/**校验邮箱格式**/
	function checkEmail(){
		var userEmail = document.getElementById("userEmail").value;
	  	var tishi2 = document.getElementById("tishi2");
	  	if(userEmail==""){
			tishi2.innerHTML="请填写注册邮箱！";
			return false;
		}
		tishi2.innerHTML="";
		var reg=/^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
		debugger;
		if(reg.test(userEmail)==false){
			tishi2.innerHTML = "Email格式不正确，例如web@sohu.com";
			return false;
		}else{
		var destURL = "<%=basePath%>ValidEmailSvl?email=" + userEmail;
			$.ajax({
		   type: "GET",
		   url: destURL,
		   success: function(msg){
		   	if(msg==1){
		   		tishi2.innerHTML = "√√√";
		   	}else if(msg==0){
		   		tishi2.innerHTML = "该邮箱已被注册";
		   		return false;
		   	}else{
		   		alert("网络出错");
		   		return false;
		   	}
		   }
		}); 
		}
	}
	
	/*验证手机号码*/
	function checkMobile(){
		var tel = document.getElementById("userPhone").value;
		var tishi3 = document.getElementById("tishi3");
		if(tel==""){
			tishi3.innerHTML="请填写注册手机号！";
			return false;
		}
		tishi3.innerHTML="";
		debugger;
		var regMobile=/^1\d{10}$/;
		if(regMobile.test(tel)==false){
			tishi3.innerHTML="手机号码格式不正确，请重新输入";
			return false;
		}else{
			var destURL = "<%=basePath%>ValidTelSvl?tel=" + tel;
			$.ajax({
			   type: "GET",
			   url: destURL,
			   success: function(msg){
			   	if(msg==1){
			   		tishi3.innerHTML = "√√√";
			   	}else if(msg==0){
			   		tishi3.innerHTML = "该手机号已被注册";
			   		return false;
			   	}else{
			   		alert("网络出错");
			   		return false;
			   	}
		     }
		  }); 
		
		
		}
	}
	function checkPwd(){
		var pwd1 = document.getElementById("userPwd1").value;
		var pwd2 = document.getElementById("userPwd2").value;
		var tishi4 = document.getElementById("tishi4").value;
		if(pwd1 == ""){
			tishi4.innerHTML="请输入注册密码！！";
			return false;
		}
		if(pwd2 == ""){
			tishi4.innerHTML="请确认密码！！";
			return false;
		}
		debugger;
		if(pwd1!=pwd2){
			tishi4.innerHTML = "两次密码输入不一致，请重新输入！"
			return false;
		}else{
			var myform = document.getElementById("myform");
			myform.submit();
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
						<li><a href="<%=basePath%>LoginSvl">登录</a></li>
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
				<div class="col span_2_of_3">
				  <div class="contact-form">
					    <form method="post" id="myform" action="<%=basePath%>RegisterSvl">
					    	<div> 
						    	<span><label>用户名</label></span>
						    	<span>
						    		<input name="userName" id="userName" type="text" class="textbox" onblur="validUname()">
						    		<span id="tishi1"></span>
						    	</span>
						    </div>
						    <div>
						    	<span><label>邮箱</label></span>
						    	<span>
						    		<input name="userEmail" id="userEmail" type="text" class="textbox" onblur="checkEmail()">
						    		<span id="tishi2"></span>
						    	</span>
						    </div>
						    <div>
						     	<span><label>手机号</label></span>
						    	<span>
						    		<input name="userPhone" id="userPhone" type="text" class="textbox" onblur="checkMobile()">
						    		<span id="tishi3"></span>
						    	</span>
						    </div>
						    <div>
						    	<span><label>密码</label></span>
						    	<span><input name="userPwd1" id="userPwd1" type="password" class="textbox"></span>
						    </div>
						    <div>
						    	<span><label>确认密码</label></span>
						    	<span>
						    		<input name="userPwd2" id="userPwd2" type="password" class="textbox" onblur="checkPwd()">
						    		<span id="tishi4"></span>
						    	</span>
						    </div>
						   <div>
						   		<span><input type="button" value="注册"  class="mybutton" onclick="checkPwd()"></span>
						  </div>
					    </form>
					    
					    <h4>${msg}</h4>
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
