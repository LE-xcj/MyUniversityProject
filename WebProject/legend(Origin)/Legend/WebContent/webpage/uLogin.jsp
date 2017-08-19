<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link rel="stylesheet" href="css/common_form.css">
<script type="text/javascript" src="../jQuery/jquery-3.1.0.js"></script>

<script type="text/javascript">
	$(function(){
		$("#bt").click(function(){
			var uName=$("input[name=uName]").val();
			var pwd=$("input[name=pwd]").val();
			var code=$("input[name=code]").val();
			$("#input1").html("&nbsp");
			$("#input2").html("&nbsp");
			$("#input3").html("&nbsp");
			if(null==uName||""==uName){
				$("#input1").html("用户名不能为空");
			}else if(null==pwd||""==pwd){
				$("#input2").html("密码不能为空");
			}else if(null==code||""==code){
				$("#input3").html("验证不能为空");
			}else{
				$.ajax({
					url:"/Legend/ULogin",
					data:{"uName":uName, "pwd":pwd, "code":code},
					success:function(data){
						if(-1==data.flag){
							$("#input1").html("该用户不存在");
						}else if(-2==data.flag){
							$("#input1").html("该用户不可用");
						}else if(0==data.flag){
							$("#input2").html("密码错误");
						}else if(1==data.flag){
							
							location.href="userOperate.jsp";
						}else if(2==data.flag){
							$("#input3").html("验证码错误");
						}
						
					}
				})
			}
			
		});
	});

</script>
</head>
<body>
    <header>
        <div class="header-line"></div>
    </header>
    <div class="content">
        <img class="content-logo" src="image/form_logo.png" alt="logo">
        <h1 class="content-title">登录</h1>
        <div class="content-form">
            <form action="/Legend/ULogin">
                <div id="change_margin_1">
                    <input class="user" type="text" name="uName" placeholder="请输入用户名">
                    <p id="input1" style="color:red">&nbsp</p>
                </div>
                <!-- input的value为空时弹出提醒 -->
                <p id="remind_1"></p>
                <div id="change_margin_2" style="margin-buttom:10px">
                    <input class="password" type="password" name="pwd" placeholder="请输入密码">
                     <p id="input2" style="color:red">&nbsp</p>
                </div>
                <!-- input的value为空时弹出提醒 -->
                <p id="remind_2"></p>
                <div style="height:30px">
                	<input type="text" name="code" style="120px;height: 30px; margin-left: -134px;" placeholder="请输入验证码" />
                	<img alt="" src="../CodeServlet" id="code" onclick="reload()" style="position: absolute; margin-left:27px;">
                </div>
                 <p id="input3" style="color:red">&nbsp</p>
                <div id="change_margin_3">
                	<button class="content-form-signup" type="button" id="bt">登录</button>
                </div>
            </form>
        </div>
        <div class="content-login-description">没有账户？</div>
        <div><a class="content-login-link" href="uRegistor.jsp">注册</a></div>
    </div>

</body>

<script type="text/javascript">
	function reload(){
 		var code = $("#code");
		code.attr("src","../CodeServlet?random="+Math.random()) 
	}
</script>
</html>