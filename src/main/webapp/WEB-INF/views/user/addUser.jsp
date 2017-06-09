<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加用户</title>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/user/addUser.do" method="post" enctype="multipart/form-data">
    	用户名：<input name="userName" type="text"/>
    	<input name="sex" type="radio" value="1"/>男
    	<input name="sex" type="radio" value="0"/>女
        用户头像：<input type="file" name="photo"/>
    	<input type="submit" >提交
    </form>
  </body>
</html>
