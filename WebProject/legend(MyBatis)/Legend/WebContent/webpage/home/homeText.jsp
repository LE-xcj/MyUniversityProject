<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body class="body1">
	<div class="up1">
		<a href="#" class="jsf"></a> <a href="javascript:addFavorite()"class="save"></a> <a href="#" class="login"></a>
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
			
			<tbody>
				<c:forEach items="${tableListdd }" var="item">
					<tr>
						<td>${item.sName }</td>
						<td>${item.sIP}</td>
						<td>${item.onTime}</td>
						<td>${item.roadType}</td>
						<td>${item.detail}</td>
						<td>${item.qq}</td>
						<td>${item.gameAddress}</td>
					</tr>
				</c:forEach>
<%-- 				<%List<TableImformation> list=(List<TableImformation>)request.getAttribute("tableListdd");%>
					<%if(null!=list){
						for(int i=0;i<list.size();++i){	
							TableImformation row=list.get(i);
						%>
							<tr>
								<td><%=row.getsName() %></td>
								<td><%=row.getsIP() %></td>
								<td><%=row.getOnTime() %></td>
								<td><%=row.getRoadType() %></td>
								<td><%=row.getDetail() %></td>
								<td><%=row.getQq() %></td>
								<td><%=row.getGameAddress() %></td>
							</tr>
						<%}
					 }%> --%>
			</tbody>
		</table>
	</div>
</body>
</html>