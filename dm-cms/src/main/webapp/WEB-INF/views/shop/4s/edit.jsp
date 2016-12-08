<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.mxep.model.enums.EnumWeeks" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="title" value="场地管理" />
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
    #latitude-error{
    	margin-left: -70px;
    }
    #aroundPoint-error{
    	position: relative;
    	top: 40px;
    }
	#pic-error,#areaList-error{
		margin-left:160px;
	}

	.clearfix{
		margin-bottom: 5px;
	}

	.img-margin-left{
		float: right;
	}

	.panel-flash-sale {
		padding: 5px 10px 5px;
	}

   /*  #pic-error,#areaList-error{
    	margin-left:160px;
    } */
</style>    
</head>
<c:set var="mainTitle" value="场地管理" />
<c:set var="subTitle" value="${empty shop ? '添加场地' : '编辑场地'}" />
<c:set var="weeks" value="<%=EnumWeeks.values()%>" />
<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp" />
    <section>
        <div class="content-wrapper">
            <h3>${mainTitle}
                <small>${subTitle}</small>
            </h3>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">${subTitle}</div>
                        <div class="panel-body">
                           <form id="form-edit" role="form" method="POST" class="form-horizontal" action="${ctx}/shop/save">
	                                <div class="col-md-12">

	                                     <input type="hidden" id="id" name="id" value="${shop.id}">
	                                     <%--<input type="hidden"  name="memberShop.id" value="${shop.memberShop.id}">--%>
										<input type="hidden" id="url" value="${url}" />
										<input type="hidden" id="cityId" name="cityName" />

	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">公司名:</label>
	                                         <div class="col-md-8">
												 <input type="text" name="name" id="name" placeholder="请输入公司名"
														class="form-control" value="${shop.name}">
	                                         </div>
	                                     </div>
										<div class="form-group">
											<label class="col-md-2 control-label">联系人:</label>
											<div class="col-md-8">
												<input type="text" name="contact" id="contact" placeholder="请输入联系人"
													   class="form-control" value="${shop.contact}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">联系方式:</label>
											<div class="col-md-8">
												<input type="text" name="phone" id="phone" placeholder="请输入联系方式"
													   class="form-control" value="${shop.phone}">
											</div>
										</div>

										<%--<div class="form-group">--%>
											<%--<label class="col-md-2 control-label">每天最大车辆数:</label>--%>
											<%--<div class="col-md-8">--%>
												<%--<input type="number" name="memberShop.limitCars" id="limitCars" placeholder="请输入每天最大车辆数"--%>
													   <%--class="form-control" value="${shop.memberShop.limitCars}">--%>
											<%--</div>--%>
										<%--</div>--%>

										<div class="form-group">
											<label class="col-md-2 control-label">早上时间:</label>
											<div class="col-md-8">
												<c:set value="${fn:split(shop.serviceTimeAm,',' )}" var="timeAms" />
												<c:forEach items="${weeks}" var="item">
													<%--<label style="margin-left: 0px; margin-right: 5px;">--%>
													<input type="checkbox"  class="serviceTimeAm" name="serviceTimeAm"
														<c:forEach items="${timeAms}" var="timeAm">
															<c:if test="${timeAm eq item.value}">
																checked
															</c:if>
														</c:forEach>
														   value="${item.value}" /> ${item.name} &nbsp;
												</c:forEach>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">下午时间:</label>
											<div class="col-md-8">
												<c:forEach items="${weeks}" var="item">
													<%--<label style="margin-left: 0px; margin-right: 5px;">--%>
													<input type="checkbox" class="serviceTimePm"  name="serviceTimePm"  value="${item.value}"
													<c:forEach items="${fn:split(shop.serviceTimePm,',' )}" var="i">
													<c:if test="${i eq item.value}">
														   checked
													</c:if>
													</c:forEach>
													> ${item.name} &nbsp;
												</c:forEach>
											</div>
										</div>

										<%--<div class="form-group">--%>
											<%--<label class="col-md-2 control-label">选择技师:</label>--%>
											<%--<div class="col-md-8">--%>
												<%--<select  name="workerList" id="member" class="form-control select1" data-select="${shop.memberShop.memberId}" ></select>--%>
											<%--</div>--%>
										<%--</div>--%>

										<div class="form-group">
											<label class="col-md-2 control-label">品牌(车):</label>
											<div class="col-md-8">
												<select  name="carBrandId" id="carBrand" class="form-control select1" data-select="${shop.carBrandId}" ></select>
											</div>
										</div>

										<%--<div class="form-group">--%>
											<%--<label class="col-md-2 control-label">城市:</label>--%>
											<%--<div class="col-md-8">--%>
												<%--<select  id="province"  class="form-control select1" data-select="${shop.city.pid}" ></select>--%>
												<%--<select  name="cityId" id="cityId" class="form-control select1" data-select="${shop.cityId}"--%>
													<%--<c:if test="${empty shop}">style="display: none;" </c:if>--%>
												<%--></select>--%>
											<%--</div>--%>
										<%--</div>--%>

	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">地点:</label>
	                                         	<div class="col-md-7">
	                                        		<input type="text" id="address" name="address" class="form-control" size="20" placeholder="请输入活动地点"
	                                                value="${shop.address}">
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
	                                        		<input type="hidden" id="lng" name="lng" value="${shop.lng}"/>
	                                        		<input type="hidden" id="lat" name="lat" value="${shop.lat}"/>
	                                        	</div>
	                                     </div>

										<div class="form-group">
											<label class="col-md-2 control-label">详情图(750*500):</label>
											<input type="hidden" name="pictures"  id="pictures" value = "${shop.pictures}" >
											<div class="col-md-8">
												<div id="detailPicFileList">
													<c:if test="${not empty shop.pictures}">
														<c:set value="${ fn:split(shop.pictures, ',') }" var="picList" />
														<c:forEach var="pic" items="${picList}">
															<div style="margin-bottom:10px">
																<img  height="200" width= "200" class="filename2" target="_blank" src=${pic }>
																<a class="btn red del_file" data-filename = "${pic}">删除</a>
															</div>
														</c:forEach>
													</c:if>
												</div>
												<input id="detailPic_upload" name="detailPic_upload" type="file" multiple="true">
											</div>
										</div>
	                                 </div>


							   <!--添加参与活动的商品  -->
							   <div class="clearfix">
								   <div class="pull-left">
									   <h5>添加该场地的技师</h5>
								   </div>
								   <div class="btn-group pull-right">
									   <button id="btn-add-flash-goods" type="button" class="btn btn-default btn-sm" data-template="#flashSale-default-template">
										   <em class="fa fa-plus text-gray-dark"></em>
									   </button>
								   </div>
							   </div>

							   <c:if test="${fn:length(shop.shopWorkerList) gt 0}">
								   <c:forEach var="worker" items="${shop.shopWorkerList}" varStatus="i">
									   <div class="panel panel-default panel-flash-sale">
										   <input type="hidden" name="id" value="${worker.id}">
										   <div class="row">
											   <div class="col-sm-12">
												   <a href="javascript:;" data-tool="panel-dismiss" class="pull-right cancel">
													   <em class="fa fa-times"></em>
												   </a>
											   </div>
										   </div>

										   <div class="row" >
											   <div class="col-sm-6">
												   <div class="form-group">
													   <label class="col-sm-3 control-label">技师:</label>
													   <div class="col-sm-9">
														   <select class="goodsList selectGoods member" data-select="${worker.member.id}"    style="width:100%">
														   </select>
														   <%--<input class="goodsId" name="member_id" type="hidden" value="${worker.member.id}"/>--%>
													   </div>
												   </div>
											   </div>
											   <div class="col-sm-6">
												   <div class="form-group">
													   <label class="col-sm-3 control-label">优先级:</label>
													   <div class="col-sm-9">
														   <input  data-ui-slider="" type="text" value="20" data-slider-min="1"
																  data-slider-max="20" data-slider-step="1" data-slider-value="${worker.priority}"
																  data-slider-orientation="horizontal" class="slider slider-horizontal priority"
																  data="value: '20'" style="display: none;">
													   </div>
												   </div>
											   </div>
										   </div>
									   </div>
								   </c:forEach>
							   </c:if>




	                             <a class="btn btn-back btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty shop ? "添加" : "保存"}中...">
                                            ${empty shop ? "添 加" : "保存"}
                                 </button>
                                 <button id="saveAgain" type="submit" class="btn btn-submit btn-sm btn-primary" data-loading-text="保存中...">
                                            保存并添加下一条
                                 </button>
                            </form> 
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
<script src="${ctx}/static/js/shop/4s/edit.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=c8ViDxq9FA8W3TEGrRcEbiLL"></script>

<%--增加的面板--%>
<script id="flash-goods-template" type="text/template">
	<div class="panel panel-default panel-flash-sale">
		<div class="row">
			<div class="col-sm-12">
				<a href="javascript:;" data-tool="panel-dismiss" class="pull-right cancel">
					<em class="fa fa-times"></em>
				</a>
			</div>
		</div>

		<div class="row" >
			<div class="col-sm-6">
				<div class="form-group">
					<label class="col-sm-3 control-label">技师:</label>
					<div class="col-sm-9">
						<select class="goodsList selectGoods member"    style="width:100%">
						</select>
						<%--<input class="goodsId" name="member_id" type="hidden" value="${worker.member.id}"/>--%>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label class="col-sm-3 control-label">优先级:</label>
					<div class="col-sm-9">
						<input  data-ui-slider="" type="text" value="20" data-slider-min="1"
							   data-slider-max="20" data-slider-step="1"
							   data-slider-orientation="horizontal" class="slider slider-horizontal priority"
							   data="value: '20'" style="display: none;">
					</div>
				</div>
			</div>
		</div>
	</div>
</script>

<script type="text/javascript"> 

//百度地图API功能
function G(id) {
	return document.getElementById(id);
}

var pointArr = [];
var map = new BMap.Map("map-container");


map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
	{"input" : "address"
	,"location" : map}
);

//编辑时，显示菜市场地址
if($("#address").val()){
	ac.setInputValue($("#address").val());
	if($("#lng").val() && $("#lat").val()){
		var pp = new BMap.Point($("#lng").val(), $("#lat").val());
		initMap(pp,BMap_Symbol_SHAPE_STAR);
	}else{
		map.centerAndZoom("苏州",13);                   // 初始化地图,设置城市和地图级别。
	}
}else{
	map.centerAndZoom("苏州",13);                   // 初始化地图,设置城市和地图级别。
}



//创建地址解析器实例
var myGeo = new BMap.Geocoder();	
	
ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			//$("#city").val(_value.city);
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
//			$("#city").val(_value.city);
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});
	

var myValue;
ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
	var _value = e.item.value;
	myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
//	$("#city").val(_value.city);
	G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
	//console.log("coming");
	pointArr = [];
	setPlace();
});

$(".search-address").click(function(){
	var b = $("#address").val();
	if(b){
		// 将地址解析结果显示在地图上,并调整地图视野
		
		myGeo.getPoint(b, function(point){
			if (point) {
				$("#lng").val(point.lng);
				$("#lat").val(point.lat);
				pointArr = [];
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
			$("#lng").val(pp.lng);
			$("#lat").val(pp.lat);
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
	          $("#address").val(rs.address);
			});   
		$("#lng").val(b.lng);
		$("#lat").val(b.lat);
		initMap(pp,BMap_Symbol_SHAPE_STAR);
		pointArr = [];
		
	}
	
	
//初始化地图覆盖物	
var cirlePoint;
var circle;
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

	var myAddress = new BMap.Geocoder();

	myAddress.getLocation(point, function(rs){
		$("#cityId").val(rs.addressComponents.city);
	});
	//marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	//画圆
	var distance;
	
	if($("#distance").val()){
		distance = $("#distance").val()*1000;
	}else{
		distance = 2000;
	}
	
	 circle = new BMap.Circle(point,distance,{fillColor:"red",strokeColor:"red", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
	 cirlePoint = point;
	
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
	//initAroundPoint(pp);
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
	 //aroundMarker.addEventListener("dragging",aroundDragging);
	 map.addOverlay(aroundMarker);
	 
	 
	 //右键删除覆盖物
	 var removeMarker = function(e,ee,marker){
		 pointArr.splice(indexOfArray(marker.getPosition(),pointArr),1);
		 
		 map.removeOverlay(marker);
		 map.removeOverlay(polygon);
		 if(pointArr.length >= 4)
			 pointArr = sortPointArray(pointArr);
		polygon = new BMap.Polygon(pointArr, {strokeColor:"#f50704",fillColor:"", strokeWeight:3, strokeOpacity:0,fillOpacity:0,});
   		map.addOverlay(polygon);			 
		 
	 }
	 
	 //创建覆盖物的右键菜单
	 var markerMenu=new BMap.ContextMenu();
	 markerMenu.addItem(new BMap.MenuItem('删除',removeMarker.bind(aroundMarker),100));
	 aroundMarker.addContextMenu(markerMenu);
	 map.removeOverlay(polygon);
	 if(pointArr.length > 1){
		 if(pointArr.length >= 4){
			 pointArr = sortPointArray(pointArr);
		 }
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
/* console.log(circle);
map.removeOverlay(circle); */

$("#distance").keyup(function(){
	var obj = $(this);
	var currentVal = obj.val();
	if(/[^\d\.]/.test(obj.val())){//替换非数字字符  
        currentVal = obj.val().replace(/[^\d\.]/g,'');  
        $(this).val(currentVal);
        
     }else{
    	 if(cirlePoint){
         	 map.removeOverlay(circle);
    		 circle = new BMap.Circle(cirlePoint,currentVal*1000,{fillColor:"red",strokeColor:"red", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
    		 map.addOverlay(circle);
    	 }
     }
	
});

function sortPointArray(pointArr){
		var points=[];
		var firstPoint = pointArr[0];
		points.push(firstPoint);
		for(var i = 0;i< pointArr.length-2;i++){
			var nextPoint = pointArr[i+1];
			var dis = map.getDistance(new BMap.Point(pointArr[i].lng,pointArr[i].lat),new BMap.Point(pointArr[i+1].lng,pointArr[i+1].lat));
			for(var j=i+2;j<pointArr.length;j++){
				var tempDis = map.getDistance(new BMap.Point(pointArr[i].lng,pointArr[i].lat),new BMap.Point(pointArr[j].lng,pointArr[j].lat));
				if(dis > tempDis){
					dis = tempDis;
					nextPoint = pointArr[j];
					var tempPoint = pointArr[j];
					pointArr[j] = pointArr[i+1];
					pointArr[i+1] = tempPoint;
				}
			}
			points.push(nextPoint);
		}
		points.push(pointArr[pointArr.length-1]);
		return points;
}


</script>

</body>

</html>