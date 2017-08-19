<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>后台管理系统</title>
<script type="text/javascript" src="../jQuery/jquery-3.1.0.js"></script>
<!-- BOOTSTRAP STYLES-->
<link href="../css/assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="../css/assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="../css/assets/css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

<script src="../css/assets/js/jquery-1.10.2.js"></script>

<script src="../css/assets/js/bootstrap.min.js"></script>

<script src="../css/assets/js/jquery.metisMenu.js"></script>

<script src="../css/assets/js/custom.js"></script>
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-cls-top " role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">Binary admin</a>
		</div>
		<div
			style="color: white; padding: 15px 50px 5px 50px; float: right; font-size: 16px;">
			Last access : 30 May 2014 &nbsp; <a href="javascript:back()"
				class="btn btn-danger square-btn-adjust">Logout</a>
		</div>
		</nav>
		<!-- /. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">
				<li class="text-center"><img src="../css/assets/img/find_user.png"
					class="user-image img-responsive" /></li>


				<li><a href="table.html"> <i class="fa fa-table fa-3x"></i>私服管理
						<span class="fa arrow"></span>
				</a>

					<ul class="nav nav-second-level">
						<li><a href="javascript:showSifuOperate(1);" data-toggle="modal" data-target="#myModal">添加私服</a></li>
						<li><a href="javascript:showSifuOperate(0);">修改/删除私服</a></li>
					</ul></li>

				<li><a href="form.html"> <i class="fa fa-edit fa-3x"></i>用户管理
						<span class="fa arrow"></span>
				</a>
					<ul class="nav nav-second-level">
						 <li><a href="javascript:showUserOperate(1);" data-toggle="modal" data-target="#myModal2">添加用户</a></li>
						<!-- <li><a href="">添加用户<button data-toggle="modal" data-target="#myModal" id="updateImfo"></button></a></li> -->
						<li><a href="javascript:showUserOperate(0);">修改/删除用户</a></li>
					</ul></li>

			</ul>

		</div>

		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<!-- /. ROW  -->
				<iframe id="innerFrame" style="width:1010px; height:1200px" src="changeSiFu.jsp"></iframe>
				<hr />

			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
	<!-- /. WRAPPER  -->
	
	
	<!-- 模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">  
	 <div class="modal-dialog" role="document">  
        <div class="modal-content">  
            <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">添加私服</h4>  
            </div>  
            <div class="modal-body">  
                <form action="">  
                    <div class="form-group">  
                        <label for="recipient-name" class="control-label">IP地址:</label>  
                        <input type="text" class="form-control" name="sIP" >  
                        <p id="input1" style="color:red">&nbsp</p>
                    </div>  
                    <div class="form-group">  
                        <label for="message-text" class="control-label">服务器名:</label>  
                        <input type="text" class="form-control" name="sName" />  
                         <p id="input2" style="color:red">&nbsp</p>
                    </div>  
                    <div>
                    	<label for="message-text" class="control-label">线路:</label>  
                    	 <input type="text" class="form-control" name="roadType" />  
                    </div>
                                        <div>
                    	<label for="message-text" class="control-label">介绍:</label>  
                    	 <input type="text" class="form-control" name="detail" />  
                    </div>
                                        <div>
                    	<label for="message-text" class="control-label">qq:</label>  
                    	 <input type="text" class="form-control" name="qq" />  
                    </div>
                    <div>
                    	<label for="message-text" class="control-label">详情:</label>  
                    	 <input type="text" class="form-control" name="address" />  
                    </div>
                </form>  
            </div>  
            <div class="modal-footer">  
                <button type="button" class="btn btn-primary" id="addsifu">确认</button>  
                <button type="reset"  class="btn btn-primary">重置</button>
            </div>  
        </div>  
    </div> 	
   </div>
   
   
   <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">  
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
                        <label for="recipient-name" class="control-label">用户名:</label>  
                        <input type="text" class="form-control" name="uName" >  
                        <p id="input11" style="color:red">&nbsp</p>
                    </div>  
                    <div class="form-group">  
                        <label for="message-text" class="control-label">密码:</label>  
                        <input type="text" class="form-control" name="pwd" />  
                         <p id="input22" style="color:red">&nbsp</p>
                    </div>  
                    <div>
                    	<label for="message-text" class="control-label">状态:</label>  
                    	&nbsp &nbsp 
                    	<input type="radio" name="status" value=1 />可用
                    	&nbsp &nbsp 
                    	<input type="radio" name="status" value=0 />不可用
                    	 <p id="input33" style="color:red">&nbsp</p>
                    </div>
                </form>  
            </div>  
            <div class="modal-footer">  
                <button type="button" class="btn btn-primary" id="adduser">确认</button>  
            </div>  
        </div>  
    </div> 	
   </div>
</body>


<script type="text/javascript">
		function showSifuOperate(flag){
			
/* 			if(1==flag){
				$("#innerFrame").attr("src","addSiFu.jsp");
				
			}else if(0==flag){
				
			} */
			$("#innerFrame").attr("src","changeSiFu.jsp");
		}
		
		function showUserOperate(flag){
			console.info(flag);
/* 			if(1==flag){
				$("#innerFrame").attr("src","addUser.jsp");
			}else if(0==flag){
				
			} */
			$("#innerFrame").attr("src","changeUser.jsp");
		}
		
		function back(){
			console.info("back");
		}
</script>

<script type="text/javascript">
	$("#addsifu").click(function(){
		var sIP = $("input[name=sIP]").val();
		var sName = $("input[name=sName]").val();
		var roadType = $("input[name=roadType]").val();
		var detail = $("input[name=detail]").val();
		var qq = $("input[name=qq]").val();
		var address = $("input[name=address]").val();
		if(null==sIP||""==sIP){
			$("#input1").html("IP地址不能为空");
		}else if(null==sName||""==sName){
			$("#input2").html("服务器名字不能为空");
		}else{
			$.ajax({
				url: "/	Legend/AddSiFu",
				type: "post",
				data: {"sIP":sIP, "sName":sName, "roadType":roadType, "detail":detail, "qq": qq, "address":address},
				success:function(data){
					if(1==data.flag){
						$("#input1").html("该IP地址已经存在");
					}else{
						alert("添加成功！");
						history.go(0);
					}
				}
			});
		}
	});
</script>

<script type="text/javascript">
	$("#adduser").click(function(){
		var uName=$("input[name=uName]").val();
		var pwd=$("input[name=pwd]").val();
		var status = $('input:radio:checked').val();//获取选中的radio的值
		$("#input11").html("&nbsp");
		$("#input22").html("&nbsp");
		$("#input33").html("&nbsp");
		if(null==uName||""==uName){
			$("#input11").html("不能为空");
		}else if(null==pwd||""==pwd){
			$("#input22").html("不能为空");
		}else if(null==status||""==status){
			$("#input33").html("不能为空");
		}else{
			$.ajax({
				url: "/Legend/AddUser",
				type: "post",
				data: {"uName":uName, "pwd":pwd, "status":status},
				success:function(data){
					if(0==data.flag){
						$("#input11").html("该用户已经存在");
					}else{
						alert("插入成功");
						history.go(0);
					}
				}
			});
		}
	});
	
</script>

</html>