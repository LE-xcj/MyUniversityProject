<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<!-- 表格样式 -->
<script type="text/javascript" src="../jQuery/jquery-3.1.0.js"></script>
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript">
	
	window.read=showSiFu();
	
	function updateSiFu(sIP,sName,roadType,detail){
		console.info(sIP);
		console.info(sName);
		console.info(roadType);
		console.info(detail);
		$("input[name=newname]").val(sName);
		$("input[name=newroad]").val(roadType);
		$("#ta").val(detail);
		$("#update").click(function(){
			var newName=$("input[name=newname]").val();
			var newRoad=$("input[name=newroad]").val();
			var newDetail=$("#ta").val();
			console.info(newDetail);
			$("#input1").html("")
			$("#input2").html("")
			if(null==newName||""==newName){
				$("#input1").html("服务器名不能为空");
			}else if(null==newRoad||""==newRoad){
				$("#input2").html("不能为空");
			}else if(null==newDetail||""==newDetail){
				$("#input3").html("不能为空");
			}else{
				console.info(sName);
				$.ajax({
					url: "../UpdateSiFu",
					data: {"sIP":sIP, "newname":newName, "newroad":newRoad, "newDetail":newDetail, "oldName":sName},
					type: "post",
					success: function(data){
						if(0==data.flag){
							$("#input1").html("服务器名已经存在");
						}else{				
							alert("修改成功");
							history.go(0);
						}
					}
				})			
			}
			
		});
	}
	
	function deleteSiFu(sIP){
		$("#delete").click(function(){
			$.ajax({
				url: "../Delete",
				data: {"key":sIP , "flag":"sifu"},
				type: "post",
				success:function(data){
					if(1==data.flag){
						alert("删除成功");
					}
				}
			});
		});
	}
	function showSiFu(){
		$("#tb").html("");
		$.ajax({
			url: "../SiFuList",
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
									+"<td><button data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"updateSiFu('"+obj.sIP+"','"+obj.sName+"','"+obj.roadType+"','"+obj.detail+"');\">修改</button>"
									+"<td><button data-toggle=\"modal\" data-target=\"#myModal2\" onclick=\"deleteSiFu('"+obj.sIP+"');\">删除</button>"+ 
								"</tr>";
						console.info(+"<td><button data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"updateSiFu('"+obj.sIP+"','"+obj.sName+"','"+obj.roadType+"','"+obj.detail+"');\">修改</button>");
						$("#tb").append(html);
					}
					
				}
			}
		});
	}
	

</script>
</head>
<body>
	<div>
		<table>
			<thead>
				<tr>
					<td>服务器名</td>
					<td>服务器IP</td>
					<td>开机时间</td>
					<td>线路类别</td>
					<td>详细版本介绍</td>
					<td>客户QQ</td>
					<td>游戏主页</td>
					<td>修改</td>
					<td>删除</td>
				</tr>
			</thead>
						
			<tbody id="tb" ></tbody>
		</table>
	</div>
	
	
		<!-- 模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">修改信息</h4>
				</div>
				<div class="modal-body">
					<form action="">
						<div class="form-group">
							<label for="recipient-name" class="control-label">服务器名:</label> <input
								type="text" class="form-control" name="newname">
							<p id="input1" style="color: red">&nbsp</p>
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">线路类别:</label> <input
								type="text" class="form-control" name="newroad" />
							<p id="input2" style="color: red">&nbsp</p>
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">详细版本介绍:</label>
							<br />
							<textarea id="ta" rows="10" cols="75"></textarea> 
							<p id="input3" style="color: red">&nbsp</p>
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
	
		<!-- 模态框 -->
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">删除用户</h4>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="delete">确认删除</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>

<!-- 模态框的样式链接 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>  
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">		

</html>