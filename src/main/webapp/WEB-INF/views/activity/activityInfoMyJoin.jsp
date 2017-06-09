<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活动详情</title>
</head>
<body>
<tr>
    <td>活动类别</td>
    <td>${activity.category.topCategory}</td>
    <td>${activity.category.secondaryCategory}</td>
</tr>
<tr>
    <td>活动内容</td>
    <td colspan="2">${activity.content}</td>
</tr>
<tr>
    <td>活动位置</td>
    <td >${activity.activityLocation.locationDescription}</td>
    <td><button value="地图"></button></td>
</tr>
<tr>
    <td>活动人数</td>
    <td>${activity.minNum}</td>
    <td><c:if test="${activity.maxNum!=null}">-${activity.maxNum}</c:if></td>
</tr>

<tr>
    <td>开始时间</td>
    <td colspan="2">${activity.startTime}</td>
</tr>
<c:if test="${activity.endTime!=null}">
    <tr>
        <td>结束时间</td>
        <td colspan="2">${activity.endTime}</td>
    </tr>
</c:if>
<tr>
    <td>备注</td>
    <td colspan="2">${activity.comment}</td>
</tr>
</table>
<button onclick="shareActivity(${activity.activityId})" value="分享"></button>
<button onclick="deleteActivity(${activity.activityId})" value="退出"></button>
</body>
</html>