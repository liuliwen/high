<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/7
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除成功</title>
    <script type="application/javascript">
        function load(){
            setTimeout("redirect",3000);
        }
        function redirect(){
            window.location.href="${pageContext.request.contextPath}/views/toIndex.do";
        }
    </script>
</head>
<body>
删除成功！
</body>
</html>
