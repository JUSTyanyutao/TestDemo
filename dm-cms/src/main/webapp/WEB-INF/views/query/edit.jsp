<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fanfou.model.cabinet.SiteStation" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="站点管理 - 怎么吃" />
    </c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>

<style type="text/css">
    .uploadify-button {
        border: none;
        padding: 0;
    }
    .wrapper section{
    	position: static;
    }
    #map-container{height:500px;width:100%;}  
    
   /*  #pic-error,#areaList-error{
    	margin-left:160px;
    } */
</style>    
</head>
<c:set var="mainTitle" value="站点管理" />
<c:set var="subTitle" value="${empty siteStation ? '添加站点' : '编辑站点'}" />
<!--站点状态-->
<c:set var="siteStationStatuses" value="<%=SiteStation.EmStationStatus.values()%>" />

<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp" />
    <section>
        <div class="answer-wrapper">
            <h3>${mainTitle}
                <small>${subTitle}</small>
            </h3>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">${subTitle}</div>
                        <div class="panel-body">
                           <form id="form-edit" role="form" method="POST" class="form-horizontal" action="${ctx}/siteStation/saveSiteStation">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="sid" name="sid" value="${siteStation.sid}">
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">所属区域:</label>
	                                         <div class="col-md-8">
									            <select id="areaServiceId" name="areaServiceId" style="width:100%" data-select="${siteStation.areaServiceId}" ></select>
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">站点名称:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="text" name="stationName" id="stationName" placeholder="请输入站点名称"
	                                                class="form-control" value="${siteStation.stationName}">
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">站点编号:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="text" name="stationNo" id="stationNo" placeholder="请输入站点编号"
	                                                class="form-control" value="${siteStation.stationNo}">
	                                        	</div>
	                                     </div>
	                                     
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">是否有柜子:</label>
	                                        	<div class="col-md-8">
	                                        		<select id="hasCabinet" name="hasCabinet" class="form-control" tabindex="1" data-select="${siteStation.hasCabinet}">
														<option value="0">没有柜子</option>
														<option value="1">有柜子</option>
													</select>
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">站点地址:</label>
	                                         	<div class="col-md-7">
	                                        		<input type="text" id="stationAddress" name="stationAddress" class="form-control" size="20" placeholder="请输入站点地址"
	                                                value="${siteStation.stationAddress}">
	                                                <div id="searchResultPanel" style="border:1px solid #C0C0C0;height:auto; display:none;"></div>
	                                        	</div>
	                                        	<div class="col-md-1">
	                                        		<a href="javascript:;" class="btn  btn-success btn-lg search-address">搜索</a>
	                                        	</div>
	                                     </div>
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label"></label>
	                                        	<div class="col-md-8">
	                                        		<div id="map-container"></div>
	                                        		<!-- 隐藏 -->
	                                        		<input type="hidden" id="lngitude" name="lngitude" value="${siteStation.lngitude}"/>
	                                        		<input type="hidden" id="latitude" name="latitude" value="${siteStation.latitude}"/>
	                                        		<input type="hidden" id="aroundPoint" name="aroundPoint" value="${siteStation.aroundPoint}"/>
	                                        		
	                                        		
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">服务距离(km):</label>
	                                        	<div class="col-md-8">
	                                        		<input id="distance" type="text" name="distance" placeholder="请输入站点服务距离"
	                                                class="form-control" value="${siteStation.distance}">
	                                        	</div>
	                                     </div>
	                                     
	                                     <c:if test="${!empty goodsHot.id}">
	                                     		<div class="form-group">
			                                         <label class="col-md-2 control-label">创建时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${siteStation.createTime}'/>" 
			                                        			name="createTime" id="createTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>
			                                     
			                                     <div class="form-group">
			                                         <label class="col-md-2 control-label">更新时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${siteStation.updateTime}'/>" 
			                                        			name="updateTime" id="updateTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>
	                                     </c:if>
	                                     
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">状态:</label>
	                                        	<div class="col-md-8">
	                                        		<select id="stationStatus" name="stationStatus" class="form-control" tabindex="1" data-select="${siteStation.stationStatus}">
														<c:forEach items="${siteStationStatuses}" var="siteStationStatus">
															<option value="${siteStationStatus.value }">${siteStationStatus.name}</option>
														</c:forEach>
													</select>
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                            <label class="col-md-2 control-label">排序:</label>
	                                            <div class="col-md-8">
		                                            <input id="sort" name="sort" data-ui-slider="" type="text" value="20" data-slider-min="1"
		                                                   data-slider-max="20" data-slider-step="1" data-slider-value="${siteStation.sort}"
		                                                   data-slider-orientation="horizontal" class="slider slider-horizontal"
		                                                   data="value: '20'" style="display: none;">
	                                            </div>
	                                     </div>
	                                     
	                                 </div>
	                            
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty siteStation ? "添加" : "保存"}中...">
                                            ${empty siteStation ? "添 加" : "保存"}
                                 </button>
                                 <button id="saveAgain" type="submit" class="btn btn-submit btn-sm btn-primary" data-loading-text="保存中...">
                                            保存并添加下一条
                                 </button>
                            </form> 
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
        
		
    </section>
    <c:import url="/WEB-INF/layouts/content_footer.jsp" />
</div>
<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>
<script src="${ctx}/static/js/siteStation/edit.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=c8ViDxq9FA8W3TEGrRcEbiLL"></script>
<script type="text/javascript"> 

var aroundPoints = '${siteStation.aroundPoint}';

//百度地图API功能
function G(id) {
	return document.getElementById(id);
}

var pointArr = [];
var map = new BMap.Map("map-container");


var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
	{"input" : "stationAddress"
	,"location" : map}
);

//编辑时，显示站点地址
if($("#stationAddress").val()){
	ac.setInputValue($("#stationAddress").val());
	if($("#lngitude").val() && $("#latitude").val()){
		var pp = new BMap.Point($("#lngitude").val(), $("#latitude").val());
		initMap(pp,BMap_Symbol_SHAPE_STAR);
	}else{
		map.centerAndZoom("苏州",13);                   // 初始化地图,设置城市和地图级别。
	}
}else{
	map.centerAndZoom("苏州",13);                   // 初始化地图,设置城市和地图级别。
}

/*编辑时，显示周围点的情况*/
if(aroundPoints){
	pointArr = JSON.parse(aroundPoints);
	if(pointArr.length > 0){
		for(var i = 0 ; i<pointArr.length; i++){
			initAroundPoint(pointArr[i]);
		}
	}
}

//创建地址解析器实例
var myGeo = new BMap.Geocoder();	
	
ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});
	

var myValue;
ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		//console.log("coming");
		setPlace();
});

$(".search-address").click(function(){
	var b = $("#stationAddress").val();
	if(b){
		// 将地址解析结果显示在地图上,并调整地图视野
		myGeo.getPoint(b, function(point){
			if (point) {
				console.log(point);
				$("#lngitude").val(point.lng);
				$("#latitude").val(point.lat);
				initMap(point,BMap_Symbol_SHAPE_STAR);
			}else{
				Dialog.warning("你选择的地址没有解析到结果!");
			}
		},"苏州市");
	}else{
		Dialog.warning("地址不能为空");
		return;
		
	}
	
	
});

function setPlace(){
		//map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			$("#lngitude").val(pp.lng);
			$("#latitude").val(pp.lat);
			initMap(pp,BMap_Symbol_SHAPE_STAR);
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	}
	
	/* function  dragging(){
		var b = marker.getPosition();
		var pp = new BMap.Point(b.lng, b.lat);
		initMap(pp);
	} */
	
	function centerDragend(){
		var b = marker.getPosition();
		var pp = new BMap.Point(b.lng, b.lat);
		myGeo.getLocation(pp, function(rs){
	          $("#stationAddress").val(rs.address);
			});   
		$("#lngitude").val(b.lng);
		$("#latitude").val(b.lat);
		initMap(pp,BMap_Symbol_SHAPE_STAR);
		pointArr = [];
		
	}
	
	function aroundDragging(){
		
		
		
	}
	
	
//初始化地图覆盖物	
function initMap(point,shape){
	map.clearOverlays();    //清除地图上所有覆盖物
	map.centerAndZoom(point, 14);
	marker= new BMap.Marker(point, {
		  // 初始化五角星symbol
		  icon: new BMap.Symbol(shape, {
		    scale: 1,
		    fillColor: "red",
		    fillOpacity: 0.8
		  })
		});
	
	marker.enableDragging();//支持拖动
	//marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	//画圆
	 var circle = new BMap.Circle(point,2000,{fillColor:"red",strokeColor:"red", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
	
	//marker.addEventListener("dragging",dragging);
	marker.addEventListener("dragend",centerDragend);
	map.addOverlay(circle);
	map.addOverlay(marker);    //添加标注
	
	//创建地图的右键菜单
	var mapMenu=new BMap.ContextMenu();
	mapMenu.addItem(new BMap.MenuItem('添加点',addMarker,100));
	map.addContextMenu(mapMenu);
	
	var temp_mapMenu=new BMap.ContextMenu();
	temp_mapMenu.addItem(new BMap.MenuItem('添加点',addMarker,100));
	circle.addContextMenu(temp_mapMenu);
	
	
}	

/* function showInfo(e){
	alert(e.point.lng + ", " + e.point.lat);
}
map.addEventListener("click", showInfo); */

//添加多个点
function addMarker(e){
	var pp = new BMap.Point(e.lng, e.lat);
	pointArr.push(pp);
	initAroundPoint(pp);
}

var polygon;
function initAroundPoint(point){
	//map.clearOverlays();
	var aroundMarker= new BMap.Marker(point, {
		  // 初始化五角星symbol
		  icon: new BMap.Symbol(BMap_Symbol_SHAPE_CIRCLE, {
		    scale: 5,
		    strokeWeight: 1,
		    fillColor: 'green',
		    fillOpacity: 0.8
		  })
		});
	 aroundMarker.enableDragging();//支持拖动
	 aroundMarker.addEventListener("dragend",function(marker){
			pointArr[indexOfArray(marker.target.HA,pointArr)] = marker.point;
			map.removeOverlay(polygon);
			polygon = new BMap.Polygon(pointArr, {strokeColor:"#f50704",fillColor:"", strokeWeight:3, strokeOpacity:0,fillOpacity:0,});
	   		map.addOverlay(polygon);
	 });
	 aroundMarker.addEventListener("dragging",aroundDragging);
	 map.addOverlay(aroundMarker);
	 
	 
	 //右键删除覆盖物
	 var removeMarker = function(e,ee,marker){
		 pointArr.splice(indexOfArray(marker.getPosition(),pointArr),1);
		 map.removeOverlay(marker);
	 }
	 
	 //创建覆盖物的右键菜单
	 var markerMenu=new BMap.ContextMenu();
	 markerMenu.addItem(new BMap.MenuItem('删除',removeMarker.bind(aroundMarker),100));
	 aroundMarker.addContextMenu(markerMenu);
	 map.removeOverlay(polygon);
	 console.log(polygon);
	 if(pointArr.length > 1){
		polygon = new BMap.Polygon(pointArr, {strokeColor:"#f50704",fillColor:"", strokeWeight:3, strokeOpacity:0,fillOpacity:0,});
   		map.addOverlay(polygon);
	 }
	 
	
}
/**获取删除点的位置*/
function indexOfArray(obj,array){
	for(var i = 0;i < array.length;i++){
		if((obj.lng == array[i].lng) && (obj.lat == array[i].lat)){
			return i;
		}
	}
}
	
</script> 
</body>

</html>