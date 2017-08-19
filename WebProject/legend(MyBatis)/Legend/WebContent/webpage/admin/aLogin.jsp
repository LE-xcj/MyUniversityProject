<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台登录</title>
<link href="../css/assets/css/bootstrap.css" rel="stylesheet" />
<link href="../css/assets/css/bootstrap.css" rel="stylesheet" />
<link href="../css/assets/css/font-awesome.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
<script type="text/javascript" src="../jQuery/jquery-3.1.0.js"></script>

<script type="text/javascript">
	$(function(){
		$("#bt").click(function(){
			var aName=$("input[name=aName]").val();
			var apwd=$("input[name=apwd]").val();
			$.ajax({
				url:"/Legend/ALoginServlet",
				data:{"aName":aName,"apwd":apwd},
				success:function(data){
					$("#input1Tip").html("&nbsp");
					$("#input2Tip").html("&nbsp")
					if(-1==data.flag){
						$("#input1Tip").html("该登录名不存在");
					}else if(0==data.flag){
						$("#input2Tip").html("密码错误");
					}else if(1==data.flag){						
											
						var status;
						var auto;
						if($("#box").prop("checked")){
							status = 1;
						}else{
							status = 0;
						}
						
						if($("#box2").prop("checked")){
							auto = 1
						}else{
							auto = 0;
						}	
						
						$.ajax({
							url: "/Legend/RemenberPwd",
							data: {"aName": aName, "apwd": apwd, "status": status, "auto": auto},
							type: "post",
							success: function(data){}
						});
						location.href="backStage.jsp";
					}else if(2==data.flag){					
						$("#input1Tip").html("不能为空");
					}else if(3==data.flag){
						$("#input2Tip").html("不能为空")
					}
				}
			});
		});
	});
	
</script>

<%
	String aName = "";
	String apwd = "";
	String status ="";
	String auto = "";
	Cookie[] cookies = request.getCookies();
	if(null!=cookies){
		for(Cookie c : cookies){
			if("aName".equals(c.getName())){
				aName = c.getValue();
			}else if("apwd".equals(c.getName())){
				apwd = c.getValue();
			}else if("status".equals(c.getName())){
				status = c.getValue();
			}else if("auto".equals(c.getName())){
				auto = c.getValue();
			}
			
		}
	}
	System.out.println(aName+" "+apwd+" "+status+" "+auto);
%>



</head>
<body>
	<div class="container">
		<div class="row text-center ">
			<div class="col-md-12">
				<br />
				<br />
				<h2 style="color:red">Binary Admin : Login</h2>

				<h5>( Login yourself to get access )</h5>
				<br />
			</div>
		</div>
		<div class="row ">

			<div
				class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> Enter Details To Login </strong>
					</div>
					<div class="panel-body">
						<form action="/Legend/ALoginServlet">
							<br />
							<div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-tag"></i></span>
								<input type="text" class="form-control"
									placeholder="Your Username " name="aName" id="name"/>
							</div>
							<p id="input1Tip" style="margin: -13px 0 0 45px; color:red;">&nbsp</p>
							<div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								<input type="password" class="form-control"
									placeholder="Your Password" name="apwd"/>
							</div>
							<p id="input2Tip" style="margin: -13px 0 0 45px; color:red;">&nbsp</p>
							<div class="form-group">
								<label class="checkbox-inline"> <input type="checkbox" value="1" id="box" />
									Remember me
								</label> <span class="pull-right"> <a href="#">Forget
										password ? </a>
								</span>
							</div>
							
							<div class="form-group">
								<label class="checkbox-inline"> 
								<input type="checkbox" value="1" id="box2" />
									auto login
								</label>
							</div>
							
							<button type="button" id="bt" class="btn btn-primary" style="width:325px">登录</button>
							<!-- <a href="#" class="btn btn-primary ">Login Now</a> -->
							<hr />
							Not register ? <a href="#">click here </a>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">	
	var aName = '<%= aName %>';
	var apwd = '<%= apwd %>';
	var status = '<%= status %>';
	var auto = '<%= auto%>';
	console.info(status);	
	console.info(aName);
	console.info(apwd);
	if("1"==status){
		$("input[name=aName]").val(aName);
		$("input[name=apwd]").val(apwd);
		$("#box").attr("checked",true);
	}else{
		$("input[name=aName]").val("");
		$("input[name=apwd]").val("");
		$("#box").attr("checked",false);
	}
	/* if("0"==auto){
		if("1"==status){
			$("input[name=aName]").val(aName);
			$("input[name=apwd]").val(apwd);
			$("#box").attr("checked",true);
		}else{
			$("input[name=aName]").val("");
			$("input[name=apwd]").val("");
			$("#box").attr("checked",false);
		}
	}else {
		/* location.href="backStage.jsp"; 
	} */

</script>
</html>