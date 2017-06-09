<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery/jquery.js"></script>
	<script type="text/javascript">
		function checkData() {
//			var userId = $("#userId").value;
            var userId = document.getElementById("userId").value;
			alert(userId);
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/user/isRegister.do?userId="+userId,
				success:function (result) {
					if(result == false){
						$("#userForm").submit();
					}else{
					    //跳转到登录
                        window.location.replace("${pageContext.request.contextPath}/user/login.do?userId="+userId);
					}
                },
                error:function () {
					alert("网络错误！");
                }
			});
        }
	</script>
</head>
<body>
login
<form action="${pageContext.request.contextPath }/user/registerAndLogin.do" method="post" id="userForm" enctype="multipart/form-data">
	用户id:<input name="userId" id="userId" type="text"><br>
	密码：<input name="password" type="password"><br>
	头像:<input type="file" name="photo"><br>
	<button name="提交" value="提交" onclick="checkData()"></button>

</form>
</body>
</html>