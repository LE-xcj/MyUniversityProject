<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<!-- 模态框的样式链接 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>  
<script type="text/javascript" src="../jQuery/jquery-3.1.0.js"></script>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">		
</head>
<body>
  <div class="modal-dialog" role="document">  
        <div class="modal-content">  
            <div class="modal-header">  
                <h4 class="modal-title" id="exampleModalLabel">修改信息</h4>  
            </div>  
            <div class="modal-body">  
                <form action="">  
                    <div class="form-group">  
                        <label for="recipient-name" class="control-label">用户名:</label>  
                        <input type="text" class="form-control" name="uName" >  
                        <p id="input1" style="color:red">&nbsp</p>
                    </div>  
                    <div class="form-group">  
                        <label for="message-text" class="control-label">密码:</label>  
                        <input type="text" class="form-control" name="pwd" />  
                         <p id="input2" style="color:red">&nbsp</p>
                    </div>  
                    <div>
                    	<label for="message-text" class="control-label">状态:</label>  
                    	&nbsp &nbsp 
                    	<input type="radio" name="status" value=1 />可用
                    	&nbsp &nbsp 
                    	<input type="radio" name="status" value=0 />不可用
                    	 <p id="input3" style="color:red">&nbsp</p>
                    </div>
                </form>  
            </div>  
            <div class="modal-footer">  
                <button type="button" class="btn btn-primary" id="add">确认</button>  
            </div>  
        </div>  
    </div> 	
</body>

<script type="text/javascript">
	$("#add").click(function(){
		var uName=$("input[name=uName]").val();
		var pwd=$("input[name=pwd]").val();
		var status = $('input:radio:checked').val();//获取选中的radio的值
		$("#input1").html("&nbsp");
		$("#input2").html("&nbsp");
		$("#input3").html("&nbsp");
		if(null==uName||""==uName){
			$("#input1").html("不能为空");
		}else if(null==pwd||""==pwd){
			$("#input2").html("不能为空");
		}else if(null==status||""==status){
			$("#input3").html("不能为空");
		}else{
			$.ajax({
				url: "../AddUser",
				type: "post",
				data: {"uName":uName, "pwd":pwd, "status":status},
				success:function(data){
					if(0==data.flag){
						$("#input1").html("该用户已经存在");
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