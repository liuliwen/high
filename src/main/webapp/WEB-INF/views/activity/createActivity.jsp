<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>创建活动</title>
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&key=6JMBZ-NUBK5-MNSI6-QJRCX-IGX6Z-RHFRI"></script>
<script type="text/javascript" src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/laydate/laydate.js"></script>
<script>
	function getLocation() {
		var geolocation = new qq.maps.Geolocation("WVXBZ-36MRW-FVCRF-R5TIP-3TVLV-DFBVG", "myapp");
		geolocation.getLocation(showPosition, null, null);
	}
	function showPosition(position) {
		//设置地图中心点
		var myLatlng = new qq.maps.LatLng(position.lat, position.lng);
		//定义工厂模式函数
		var myOptions = {
			zoom : 13, //设置地图缩放级别
			center : myLatlng, //设置中心点样式
			mapTypeId : qq.maps.MapTypeId.ROADMAP
		//设置地图样式详情参见MapType
		}

		var map = new qq.maps.Map(document.getElementById('mapContainer'),
				myOptions);
		//创建marker  标记所在位置
		var marker = new qq.maps.Marker({
			position : myLatlng,
			map : map
		});
		qq.maps.event.addListener(map,'click',
			function(event) {
				var lng = event.latLng.getLng();
				var lat = event.latLng.getLat();
				var url = "http://apis.map.qq.com/ws/geocoder/v1/?";
				var data = {
					key : "WVXBZ-36MRW-FVCRF-R5TIP-3TVLV-DFBVG",//开发密钥
					location : lat + "," + lng,//位置坐标
					get_poi : "1",//是否返回周边POI列表：1.返回；0不返回(默认)
					coord_type : "1",//输入的locations的坐标类型,1 GPS坐标
					parameter : {
						"scene_type" : "tohome",
						"poi_num" : 20
					},//附加控制功能
					output : "jsonp"
				}
				$.ajax({
					type : 'get',
					url : url,
					dataType : 'jsonp',
					data : data,
					success : function(data, textStatus) {
						if (data.status == 0) {
							var address = data.result.formatted_addresses.recommend;
							var locationDescription =data.result.address;
							var locationLongitude = data.result.location.lng;
							var locationLatitude =  data.result.location.lat;
							$("#locationDescription").val(locationDescription);
							$("#locationLongitude").val(locationLongitude);
							$("#locationLatitude").val(locationLatitude);
						} else {
							alert("系统错误，请联系管理员！")
						}
					},
					error : function() {
						alert("系统错误，请联系管理员！")
					}
				});
			});
		function jsoncallback(data) {
		}

	};

	//页面加载完成，从后台获取顶级分类
	$(document).ready(function() {
		appendDataForTopCategory();
		getLocation();
	});

	function appendDataForTopCategory() {
		$.ajax({
			type : "POST", //使用post方法访问后台  
			url : "${pageContext.request.contextPath}/category/findAllTopCategoryName.do", //要访问的后台地址  
			success : function(result) {//result为返回的数据  
				$("#selTopCat").append(
						$("<option value=\"0\">－选择分类－</option>"));
				for (var i = 0; i < result.length; i++) {
					$("#selTopCat").append($("<option value=\""+result[i]+"\">"
									+ result[i] + "</option>"));
				}
			}
		});
	}
	var category;
	function loadSecCat(value) {
		document.getElementById("selSecCat").options.length = 0;
		$.ajax({
			type : "POST", //使用post方法访问后台  
			url : "${pageContext.request.contextPath}/category/findAllSecCate.do", //要访问的后台地址  
			data : value,
			success : function(result) {//result为返回的数据  
				category = result;
				$("#selSecCat").append(
						$("<option value=\"0\">－选择分类－</option>"));
				for (var i = 0; i < result.length; i++) {
					$("#selSecCat").append($("<option value=\""+result[i].secondaryCategory+"\">"
											+ result[i].secondaryCategory
											+ "</option>"));
				}
			}
		});
	}
	function getCategoryId(id) {
		$("#categotyId").val(category[id].id);
	}

	function checkData() {
		// 		var a = $("#deadline");
		// 		var time =a.val();
		// 		alert(time);
		document.getElementById("deadline").value = $("#deadline").val();
		document.getElementById("endTime").value = $("#deadline").val();
		// 		document.getElementById
	}
</script>
</head>
<body>
	<form:form modelAttribute="activity" action="${pageContext.request.contextPath }/activity/createActivity.do" method="post" >
	<fieldset>
		<table>
			<tr>
				<!-- 页面加载之后 从后台 获取类别选项，onchange函数根据当前选择的值从后台获取次级分类 -->
				<td>活动类别</td>
				<td>
					<form:select path="category.topCategory" id="selTopCat" cssErrorClass="errorBox" onchange="loadSecCat(this.options[this.options.selectedIndex].value)">
					</form:select>
					<form:errors path="category.topCategory"></form:errors>
				</td>
					<td>
					<!-- onchange 函数触发，给隐藏域 categotyId赋值--> 
					<form:select path="category.secondaryCategory"  id="selSecCat" onchange="getCategoryId(this.options[this.options.selectedIndex].index)">
						<option value="0">－选择分类－</option>
					</form:select>
					</td>
				<td><form:hidden path="categotyId" id="categotyId"/></td>
			</tr>
			<tr>
				<td>输入活动内容</td>
				<td colspan="3"> <form:input path="content" cssErrorClass="errorBox" /></td>
			</tr>
			<tr>
				<%
					//用户选择活动位置信息
				%>
				<td>活动位置</td>
<!-- 				<td><input type="button" onClick="getLocation()" value="选择位置"></td> -->
				<td>
					<form:hidden path="activityLocation.locationDescription" id="locationDescription"/>
					<form:hidden path="activityLocation.longitude" id="locationLongitude"/>
					<form:hidden path="activityLocation.latitude" id="locationLatitude"/>
				</td>
			</tr>
			<tr>
				<td colspan='3'><div id="mapContainer" style="width: 500px; height: 300px"></div></td>
			</tr>
			<tr>
				<td>活动人数</td>
				<td><form:input cssErrorClass="errorBox" placeholder="最少人数" path="minNum"/><form:errors path="minNum"/></td>
				
				<td><form:input cssErrorClass="errorBox" placeholder="最多人数" path="maxNum"/></td>
				<td></td>
			</tr>
			<tr>
				<!-- 用户选择，再选择日期，然后deadlineTime 赋值。 利用js的时间插件 -->
				<td>开始时间</td>
				<td><form:input id="deadline" path="deadline"
					onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/></td>
			</tr>
			<tr>
				<!-- 用户选择，再选择日期，然后deadlineTime 赋值 -->
				<td>结束时间</td>
				<td><form:input path="endTime"
					onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/></td>
			</tr>
			<tr>
				<td colspan='3'><textarea rows="5" name="comment">备注</textarea></td>
			</tr>
			<tr>
				<!-- 触发 数据验证 -->
				<td><input type="submit" value="提交" onclick="checkData()"></td>
			</tr>
		</table>
		</fieldset>
	</form:form>
</body>
</html>