<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>		<!-- -el表达式要引入 -->>
<%@ page import="java.util.List" %>
<%@ page import="pojo.TableImformation" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>就私服</title>
	<script>
		function addFavorite(){
			if(document.all){
				window.external.AddFavorite("https://www.945.com","就私服");
			}else if(window.sidebar){
				window.sidebar.addPanel("就私服","https://www.945.com","");
			}
		}
	</script>
	<link rel="stylesheet" href="style.css" type="text/css" />
	<script type="text/javascript" src="../jQuery/jquery-3.1.0.js"></script>
</head>
<body class="body1">
	<div class="up1">
		<a href="#" class="jsf"></a> <a href="javascript:addFavorite()"class="save"></a> 
		<a href="uLogin.jsp" class="login"></a>
	</div>
	
	<div class="up2">
		<a href="#" class="up2a"></a>
	</div>

	<div class="main">
		<table class="table" border="3px">
			<thead>
				<tr>
					<td>服务器名</td>
					<td>服务器IP</td>
					<td>开机时间</td>
					<td>线路类别</td>
					<td>详细版本介绍</td>
					<td>客户QQ</td>
					<td>游戏主页</td>
				</tr>
			</thead>
			
			<tbody id="tb">
			</tbody>
		</table>
	</div>
</body>
<script type="text/javascript">

	$.ajax({
		url:"../HomeServlet",
		data:{},
		type:"post",
		success:function(data){
			if(null!=data){
				for( var i =0;i < data.length; i++ ) {
					var obj = data[i];
					console.info(obj);
					console.info(obj.detail);
					console.info(typeof obj.detail);
					var html="<tr>"
									+"<td>"+obj.sName+"</td>"
									+"<td>"+obj.sIP+"</td>"
									+"<td>"+obj.onTime+"</td>"
									+"<td>"+obj.roadType+"</td>"
									+"<td>"+obj.detail+"</td>"
									+"<td>"+obj.qq+"</td>"
									+"<td>"
									+"<a href=\""+obj.gameAddress+"\" target=\"_blank\">点击详情</a>"
									+"</td>"+
								"</tr>";
					console.info(html);
					$("#tb").append(html);
				}
			}
		}
	});
</script>
</html>