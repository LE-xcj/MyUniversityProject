<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="pojo.TableImformation" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户界面</title>



<style>
.menu { height: 45px; display: block; }
.menu ul { list-style: none; padding: 0; margin: 0; }
.menu ul li { float: left; /* 菜单子元素的内容超出不可见 */  
			overflow: hidden; position: relative; text-align: center; 
			line-height: 45px; }
.menu ul li a { /* 必须是相对定位  */ 
			position: relative; display: block; width: 110px; height: 45px; 
			font-family: Arial; font-size: 11px; font-weight: bold; 
			letter-spacing: 1px; text-transform: uppercase; 
			text-decoration: none; cursor: pointer; }
.menu ul li a span { /* 所有层将使用绝对定位 */
			position: absolute; left: 0; width: 110px; }
.menu ul li a span.out { top: 0px; }
.menu ul li a span.over,  .menu ul li a span.bg { /* 起初.over层和.bg层相对a元素-45px以达到隐藏 */  
			top: -45px; }
				
			
/** 简化版示例 **/
		
#menu2 { background:#45A8DF; }
#menu2 ul li a { color:#FFF; }
#menu2 ul li a span.over { background: #A6DD00; color:#333; }
#menu2 ul li.nav1 a span.over { background: #fea274; }
#menu2 ul li.nav2 a span.over { background: #b0bbba; }
#menu2 ul li.nav3 a button span.over { background: #a3f091; }
#menu2 ul li.nav4 a span.over { background: #86dbf9; }
#menu2 ul li.nav5 a span.over { background: #e0caf0; }
#menu2 ul li.nav6 a span.over { background: #9dace9; }

/*----------------------------------*/
body { margin:0; padding:0; background:#f1f1f1; font:70% Arial, Helvetica, sans-serif; color:#555; line-height:150%; text-align:left; }
a { text-decoration:none; color:#057fac; }
a:hover { text-decoration:none; color:#999; }
h1 { font-size:140%; margin:0 20px; line-height:80px; }
h2 { font-size:120%; }
#container { margin:0 auto; width:680px; background:#fff; padding-bottom:20px; }
#content { margin:0 20px; }
form { margin:1em 0; padding:.2em 20px; background:#eee; }

</style>

<!-- 表格样式 -->
<script type="text/javascript" src="../jQuery/jquery-3.1.0.js"></script>
<link href="../css/tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />


<script language="javascript">
		$(document).ready(function() {
			/*	简化版示例	*/
					
			$("#menu2 li a").wrapInner( '<span class="out"></span>' );
			
			$("#menu2 li a").each(function() {
				$('<span class="over">' +  $(this).text() + '</span>' ).appendTo( this );
			});

			$("#menu2 li a").hover(function() {
				$(".out",this).stop().animate({'top':'45px'},200); // 向下滑动 - 隐藏
				$(".over",this).stop().animate({'top':'0px'},200); // 向下滑动 - 显示

			}, function() {
				$(".out",this).stop().animate({'top':'0px'},200); // 向上滑动 - 显示
				$(".over",this).stop().animate({'top':'-45px'},200); // 向上滑动 - 隐藏
			});

		});
</script>

<script type="text/javascript">

	function showUser(){
		var newname= '<%=(String)request.getSession().getAttribute("uName")%>';
		var newpwd= '<%=(String)request.getSession().getAttribute("pwd") %>';
		console.info(newname);
		console.info(newpwd);
		$("input[name=newname]").val("123");
		$("input[name=newpwd]").val("123");
	}

	function showSiFu(){
		$("#tb").html("");
		$.ajax({
			url: "/Legend/SiFuList",
			data:{},
			type:"post",
			success:function(data){
				if(null!=data){
					for(var i=0; i<data.length; ++i){
						var obj=data[i];
						console.info(typeof obj.sIP);
						var html="<tr>"
									+"<td>"+obj.sName+"</td>"
									+"<td>"+obj.sIP+"</td>"
									+"<td>"+obj.onTime+"</td>"
									+"<td>"+obj.roadType+"</td>"
									+"<td>"+obj.detail+"</td>"
									+"<td>"+obj.qq+"</td>"
									+"<td><a href=\""+obj.gameAddress+"\" target=\"_blank\">点击详情</a></td>"
									+"<td><a href=\"javascript:saveURL('"+obj.sIP+"');\">收藏</a></td>"+
								"</tr>";
						console.info("<td><a href=\"javascript:saveURL('"+obj.sIP+"');\">收藏</a></td>");
						$("#tb").append(html);
					}
					
				}
			}
		});
	}
	
	function saveURL(sIP){
		console.info(sIP);
		$.ajax({
			url:"/Legend/SaveURL",
			data:{"sIP":sIP},
			type:"post",
			success:function(data){
				if(1==data.flag){
					alert("收藏成功")
				}else{
					alert("已经收藏过了")
				}
			}
		});
	}
	
	function showSaveSiFu(){
		$.ajax({
			url: "/Legend/ShowSaveSiFu",
			data:{},
			type:"post",
			success:function(data){
				$("#tb").html("");
				for(var i=0; i<data.length; ++i){
					var obj=data[i];
					var html="<tr>"
						+"<td>"+obj.sName+"</td>"
						+"<td>"+obj.sIP+"</td>"
						+"<td>"+obj.onTime+"</td>"
						+"<td>"+obj.roadType+"</td>"
						+"<td>"+obj.detail+"</td>"
						+"<td>"+obj.qq+"</td>"
						+"<td><a href=\""+obj.gameAddress+"\" target=\"_blank\">点击详情</a></td>"
						+"<td><a href=\"javascript:cancelSaveURL('"+obj.sIP+"');\">取消收藏</a></td>"+
					"</tr>";
					console.info("<td><a href=\"javascript:cancelSaveURL('"+obj.sIP+"');\">取消收藏</a></td>");
					$("#tb").append(html);
				}
			}
		});
	}
	
 
	
	function cancelSaveURL(sIP){
		console.info(sIP);
		$.ajax({
			url: "/Legend/CancelSaveURL",
			data: {"sIP":sIP},
			success:function(data){
				if(1==data.flag){
					alert("成功取消收藏！");
				}
			}
		});
	}
	
	
</script>



</head>
<body>
	<div id="menu2" class="menu">
		<ul>
			<li class="nav1"><a href="javascript:showSiFu()">私服列表</a></li>
			<li class="nav2"><a href="javascript:showSaveSiFu()">收藏列表</a></li>
			<li class="nav3">			
				<a class="nav3" date-href="javascript:showUser()" data-toggle="modal" data-target="#myModal" >修改信息</a>
				<!-- <button data-toggle="modal" data-target="#myModal" id="updateImfo">修改信息 </button> -->

			</li>
			<li class="nav4"><a href="">注销</a></li>		
		</ul>
	</div>
	<div id="content">
		<table cellspacing="0" cellpadding="0" id="tbl">
			<thead>
				<tr>
					<td>服务器名</td>
					<td>服务器IP</td>
					<td>开机时间</td>
					<td>线路类别</td>
					<td>详细版本介绍</td>
					<td>客户QQ</td>
					<td>游戏主页</td>
					<td>收藏</td>
				</tr>
			</thead>
			<tbody id="tb"></tbody>
		</table>
	</div>
	 
  
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">  
    <div class="modal-dialog" role="document">  
        <div class="modal-content">  
            <div class="modal-header">  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span  
                        aria-hidden="true">×</span></button>  
                <h4 class="modal-title" id="exampleModalLabel">修改信息</h4>  
            </div>  
            <div class="modal-body">  
                <form action="">  
                    <div class="form-group">  
                        <label for="recipient-name" class="control-label">用户名:</label>  
                        <input type="text" class="form-control" name="newname" >  
                        <p id="input1" style="color:red">&nbsp</p>
                    </div>  
                    <div class="form-group">  
                        <label for="message-text" class="control-label">密码:</label>  
                        <input type="text" class="form-control" name="newpwd" />  
                         <p id="input2" style="color:red">&nbsp</p>
                    </div>  
                </form>  
            </div>  
            <div class="modal-footer">  
                <button type="button" class="btn btn-primary" id="update">确认修改</button>  
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>  
            </div>  
        </div>  
    </div>  
</div>   

<!-- 模态框的样式链接 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>  
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">		

<script type="text/javascript">
	
	$("#update").click(function(){
 		var newName=$("input[name=newname]").val();
		var newpwd=$("input[name=newpwd]").val();
		console.info(newName);
		console.info(newpwd);
		$("#input1").html("")
		$("#input2").html("")
		if(null==newName||""==newName){
			$("#input1").html("用户名不能为空")
		}else if(null==newpwd||""==newpwd||newpwd.length<6){
			$("#input2").html("密码输入有误")
		}else{
			$.ajax({
				url: "/Legend/Update",
				data: {"newName":newName, "newpwd":newpwd},
				type: "post",
				success:function(data){
					if(0==data.flag){
						$("#input1").html("该用户名已经存在")
					}else{
						alert("修改成功")
						history.go(0);
					}
				}
			});
		}
	}); 
</script>
</body>
</html>