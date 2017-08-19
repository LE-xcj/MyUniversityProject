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
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="assets/css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

<script src="assets/js/jquery-1.10.2.js"></script>

<script src="assets/js/bootstrap.min.js"></script>

<script src="assets/js/jquery.metisMenu.js"></script>

<script src="assets/js/custom.js"></script>
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
				<li class="text-center"><img src="assets/img/find_user.png"
					class="user-image img-responsive" /></li>


				<li><a href="table.html"> <i class="fa fa-table fa-3x"></i>私服管理
						<span class="fa arrow"></span>
				</a>

					<ul class="nav nav-second-level">
						<li><a href="javascript:showSifuOperate(1);">添加私服</a></li>
						<li><a href="javascript:showSifuOperate(0);">修改/删除私服</a></li>
					</ul></li>

				<li><a href="form.html"> <i class="fa fa-edit fa-3x"></i>用户管理
						<span class="fa arrow"></span>
				</a>
					<ul class="nav nav-second-level">
						 <li><a href="javascript:showUserOperate(1);">添加用户</a></li>
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
	
</body>


<script type="text/javascript">
		function showSifuOperate(flag){
			
			if(1==flag){
				$("#innerFrame").attr("src","addSiFu.jsp");
				
			}else if(0==flag){
				$("#innerFrame").attr("src","changeSiFu.jsp");
			}
		}
		
		function showUserOperate(flag){
			console.info(flag);
			if(1==flag){
				$("#innerFrame").attr("src","addUser.jsp");
			}else if(0==flag){
				$("#innerFrame").attr("src","changeUser.jsp");
			}
		}
		
		function back(){
			console.info("back");
		}
</script>
</html>