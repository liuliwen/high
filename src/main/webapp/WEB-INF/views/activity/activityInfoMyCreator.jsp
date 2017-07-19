<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活动详情</title>
    <script type="application/javascript" src="${pageContext.request.contextPath}/resource/js/jquery/jquery.js"></script>
    <script type="application/javascript">
        function deleteParticipate(obj,activityId,userId){
            var table = document.getElementById("activityInfo");
            var tr = obj.parentNode.parentNode;
            tr.parentNode.removeChild(tr);
            alert("删除成功！！！");
            //利用ajax从数据库中删除
            $.ajax({
                type:"GET",
                url:"${pageContext.request.contextPath}/activity/deleteParticipateById.do?activityId="+activityId+"&userId="+userId,
                dataType:"json",
                success: function (result) {
                    if(result==true){
                        var table = document.getElementById("activityInfo");
                        var tr = obj.parentNode.parentNode;
                        tr.parentNode.removeChild(tr);
                        alert("删除成功！！！");
                    }else{
                        alert("删除失败！！！");
                    }
                }
                error:function () {
                    alert("网络错误！！！");
                }
            });
        }
    </script>
</head>
<body>
<table id="activityInfo">
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
    <td>是否公开</td>
    <td><c:if test="${activity.isPublic}">公开</c:if><c:if test="${!activity.isPublic}">不公开</c:if></td>
</tr>
<c:if test="${activity.distance!=null}">
    <tr>
        <td>距离限制</td>
        <td>${activity.distance}</td>
    </tr>
</c:if>
<tr>
    <td>开始时间</td>
    <td>${activity.startTime}</td>
</tr>
<c:if test="${activity.endTime!=null}">
    <tr>
        <td>结束时间</td>
        <td>${activity.endTime}</td>
    </tr>
</c:if>
<tr>
    <td>备注</td>
    <td>${activity.comment}</td>
</tr>
<c:if test="${activity.participate!=null && activity.participate.size()>0}">
    <c:forEach var="${activity.participate}" items="user">
        <tr>
            <td><img src="${user.userPhoto}" alt="头像"></td>
            <td>${user.userName}</td>
            <td><button value="删除" onclick="deleteParticipate(this,${activity.activityId},${user.userId})"></button></td>
        </tr>
    </c:forEach>
</c:if>
</table>
<button onclick="shareActivity(${activity.activityId})" value="分享"></button>
<button onclick="deleteActivity(${activity.activityId})" value="删除"></button>
</body>
</html>