<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<link rel="stylesheet" href="../css/userReg/common_form.css">
<script type="text/javascript" src="../jQuery/jquery-3.1.0.js"></script>
</head>
<script type="text/javascript">
	$(function(){
		$("#bt").click(function(){
			var uName=$("input[name=uName]").val();
			var upwd=$("input[name=upwd]").val();
			console.info(uName);
			console.info(upwd);
			
			$.ajax({
				url:"/Legend/RegistorServlet",
				data:{"uName":uName,"upwd":upwd},
				success:function(data){
					$("#input1").html("&nbsp");
					$("#input2").html("&nbsp");
					if(1==data.flag){
						$("#input1").html("该用户已经存在");
					}else if(2==data.flag){
						$("#input1").html("用户名不能为空");
					}else if(0==data.flag){
						location.href="registorSuccess.html"
					}
	
					if(null==upwd||upwd.length<6){
						$("#input2").html("密码输入有误");
					}
				}
			});
			
		});
	});
</script>
<body>
    <header>
        <div class="header-line"></div>
    </header>
    <div class="content">
        <img class="content-logo" src="../image/form_logo.png" alt="logo">
        <h1 class="content-title">创建账户</h1>
        <div class="content-form">
            <form action="RegistorServlet">
                <div id="change_margin_1" >
                    <input class="user" type="text" name="uName" placeholder="请输入用户名" >
                </div>
                <!-- input的value为空时弹出提醒 -->
                <p id="input1" style="font-size: 6px; margin-right: 48px; color: red;">&nbsp</p>
                <div id="change_margin_2">
                    <input class="password" type="password" name="upwd" placeholder="密码长度不能小于6">
                </div>
                <!-- input的value为空时弹出提醒 -->
                <p id="input2" style="font-size: 6px; margin-right: 48px; color: red;">&nbsp</p>
                <div id="change_margin_3">
                	<button class="content-form-signup" id="bt" type="button">创建账户</button>
                </div>
            </form>
        </div>
        <div class="content-login-description">已经拥有账户？</div>
        <div><a class="content-login-link" href="uLogin.jsp">登录</a></div>
    </div>
</body>
</html>