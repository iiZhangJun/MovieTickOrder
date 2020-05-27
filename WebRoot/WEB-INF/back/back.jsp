<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/icon.css">	
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>

  </head>
  
  <body>
    <div class="easyui-layout" style="width:90%;height:90%;">
        <div data-options="region:'north'" style="height:60px">
             <h2>影片管理系统后台</h2>
        </div>
        <div data-options="region:'west',split:true" title="West" style="width:150px;">
             <ul>
             	<li><a href="<%=basePath%>back/MainRenewSvl" target="myiframe">首页更新</a></li>
        		<li> <a href="<%=basePath%>back/upCounter.html" target="myiframe">影片上架</a></li>
            	<li><a href="<%=basePath%>back/douwnCounter.html" target="myiframe">影片下架</a></li>
        	</ul>
        </div>
        <div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">           
    		<iframe name="myiframe" width="99%" height="99%" scrolling="no"></iframe>    
        </div>
    </div>
  </body>
</html>
