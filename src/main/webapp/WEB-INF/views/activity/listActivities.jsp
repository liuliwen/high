<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>寻找活动</title>
</head>
<body>
activities
<c:forEach items="${activities.activityList }" var="activity">
	${activity.comment}
	${activity.content}
	${activity.startTime}
</c:forEach>
</body>
</html>