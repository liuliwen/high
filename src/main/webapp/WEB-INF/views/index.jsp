<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&key=6JMBZ-NUBK5-MNSI6-QJRCX-IGX6Z-RHFRI"></script>
	<script type="text/javascript" src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/jquery/jquery.js"></script>
    <script type="text/javascript">
    	function createActivity(){
    		window.location.replace("${pageContext.request.contextPath}/activity/activityInput.do");
    	}
    	function searchActivity() {
    		var geolocation = new qq.maps.Geolocation("6JMBZ-NUBK5-MNSI6-QJRCX-IGX6Z-RHFRI", "myapp");
    		var pos;
    		var options = {
    				timeout : 8000
    		};
    		geolocation.getLocation(function(position){
    			$("#longitude").attr("value",position.lng);
    			$("#latitude").attr("value",position.lat);
    			document.getElementById("search").submit(); 
    		},null,options);
    	}
    </script>
  </head>
  
  <body>
  	<form id="search" action="${pageContext.request.contextPath}/activity/searchActivity.do" method="post" >
  		<input type="hidden" name="longitude" id="longitude" >
  		<input type="hidden" name="latitude" id="latitude" >
  		<input type="button" value="寻找活动" onclick="searchActivity()">
  	</form>
    <button type="button" onclick="createActivity()" >创建活动</button>
    <button type="button" onclick="myCreateActivity()" >我创建的</button>
    <button type="button" onclick="myJoinActivity()" >我参加的</button>
    <button type="button" onclick="myInfo()" >个人中心</button>
  </body>
</html>
