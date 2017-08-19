<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<!-- 表格样式 -->
<script type="text/javascript" src="../jQuery/jquery-3.1.0.js"></script>
<link href="../css/tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript">
	window.read=showUser();
	
	function showUser(){
		$("#tb").html("");
		$.ajax({
			url: "/Legend/UserList",
			data: {},
			type: "post",
			success:function(data){
				if(null!=data){
					for(var i=0; i<data.length;++i){
						var obj=data[i];
						console.info(obj);
						var status="可用";
						if(0==obj.status)
							status="不可用";
						var html="<tr>"
									+"<td>"+obj.uName+"</td>"
									+"<td>"+obj.pwd+"</td>"
									+"<td>"+status+"</td>"
									+"<td><button data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"updateUser('"+obj.uName+"','"+obj.pwd+"','"+obj.status+"');\">修改</button>"
									+"<td><button data-toggle=\"modal\" data-target=\"#myModal2\" onclick=\"deleteUser('"+obj.uName+"');\">删除</button>"+ 
								"</tr>";
								
						console.info("<td><a href=\"javascript:update('"+obj.uName+"');\">修改</a>");
						$("#tb").append(html);
					}
				}
			}
		});
	}
	
	function updateUser(uName,pwd,status){
		console.info(uName);
		console.info(pwd);
		console.info(status);
		$("input[name=newname]").val(uName);
		$("input[name=newpwd]").val(pwd);
		if("1"==status)
			$("#able").attr("checked",true);
		else
			$("#enable").attr("checked",true);
		$("#update").click(function(){
			var newName=$("input[name=newname]").val();
			var newpwd=$("input[name=newpwd]").val();
			var newstatus = $('input:radio:checked').val();//获取选中的radio的值
			console.info(newstatus);
			$.ajax({
				url: "/Legend/UpdateU2",
				data: {"uName":uName, "newName":newName, "newpwd":newpwd, "newstatus":newstatus},
				type: "post",
				success: function(data){
					var newName=$("input[name=newname]").val();
					var newpwd=$("input[name=newpwd]").val();
					console.info(newName);
					console.info(newpwd);
					$("#input1").html("")
					$("#input2").html("")
					if(null==newName||""==newName){
						$("#input1").html("用户名不能为空");
					}else if(null==newpwd||""==newpwd||newpwd.length<6){
						$("#input2").html("密码输入有误");
					}else{
						if(0==data.flag){
							$("#input1").html("该用户名已经存在")
						}else{
							alert("修改成功");
						}
					}
				}
			});
		});
	}
	
	function deleteUser(uName){
		$("#delete").click(function(){
			$.ajax({
				url: "/Legend/Delete",
				data: {"key":uName , "flag":"user"},
				type: "post",
				success:function(data){
					if(1==data.flag){
						alert("删除成功");
					}
				}
			});
		});
	}
</script>
</head>
<body>
	<div>
		<table>
			<thead>
				<tr>
					<td>用户名</td>
					<td>密码</td>
					<td>状态</td>
					<td>修改</td>
					<td>删除</td>
				</tr>
			</thead>
			<tbody id="tb"></tbody>
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
							<label for="recipient-name" class="control-label">用户名:</label> <input
								type="text" class="form-control" name="newname">
							<p id="input1" style="color: red">&nbsp</p>
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">密码:</label> <input
								type="text" class="form-control" name="newpwd" />
							<p id="input2" style="color: red">&nbsp</p>
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">状态:</label> 
							<input type="radio" value=1 name="newstatus" id="able" />可用&nbsp &nbsp &nbsp &nbsp
							<input type="radio" value=0 name="newstatus" id="enable" />禁用
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