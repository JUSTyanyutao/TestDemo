<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.mxep.model.order.Order" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" answer="text/html; charset=UTF-8">
<c:import url="/WEB-INF/layouts/header.jsp">
    <c:param name="question" value="订单统计管理 - 怎么吃" />
</c:import>
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<style type="text/css">


</style>
</head>

<c:set var="mainTitle" value="订单统计管理" />
<c:set var="subTitle" value="订单统计列表" />
<!--订单待包装状态-->
<c:set var="packageStatus" value="<%=Order.DeliveryStatus.PendingPackage.value%>" />
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
                        <div class="panel-body table-fit">
                            <form id="pagination-form" class="form-inline" method="POST" action="${ctx}/orderGoodsQuery">
                                <div class="form-group">
                                    <input type="hidden" id="search_EQ_deliveryStatus" name="search_EQ_deliveryStatus" value="${seatch_EQ_deliveryStatus}">
                                	<select class="form-control" name="search_EQ_stationId" style="margin-left:10px;">
                                    	<option value="">请选择站点</option>
                                    	<c:forEach items="${siteStations}" var="siteStation">
                                    		<option value="${siteStation.sid}">${siteStation.stationName}</option>
                                    	</c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <div id="beginDate" class="input-group date" >
		                                 <input type="text" name="search_GTE_createTime" value="<fmt:formatDate value='${currentDate}' pattern='yyyy-MM-dd 00:00' />"
		                                 		class="form-control" placeholder="请输入起始日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
	                               </div>
                                </div>
                                <div class="form-group">
	                               <div id="endDate" class="input-group date">
		                                 <input type="text" name="search_LTE_createTime" value="<fmt:formatDate value='${currentDate}' pattern='yyyy-MM-dd 23:59' />"
		                                 		class="form-control" placeholder="请输入结束日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
	                               </div>
                                </div>
                                <div class="form-group">
                                    <div id="deliveryDate" class="input-group date">
                                        <input type="text" name="search_EQ_deliveryDate" value="' pattern='yyyy-MM-dd' />"
                                               class="form-control" placeholder="请输入派送日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <select class="form-control" name="search_EQ_deliverySection" style="margin-left:10px;">
                                        <option value="">请选择派送时间</option>
                                        <option value="09:30-12:00">上午</option>
                                        <option value="16:00-18:00">下午</option>
                                    </select>
                                </div>
                                <button type="button" class="btn btn-primary btn-search">搜索</button>

                                <div class="action-group">
                                    <a class="btn btn-export btn-success pull-right">导出Excel</a>
                                    <a data-deliveryStatus="1" class="btn btn-order-search <c:choose><c:when test="${!search_EQ_deliveryStatus || search_EQ_deliveryStatus == 1}">btn-success</c:when><c:otherwise>btn-default</c:otherwise></c:choose>">待打包订单量</a>
                                    <a data-deliveryStatus="2"  class="btn btn-order-search <c:choose><c:when test="${search_EQ_deliveryStatus && search_EQ_deliveryStatus == 2}">btn-success</c:when><c:otherwise>btn-default</c:otherwise></c:choose>">已打包订单量</a>
                                    <a data-deliveryStatus="3"  class="btn btn-order-search <c:choose><c:when test="${search_EQ_deliveryStatus && search_EQ_deliveryStatus == 3}">btn-success</c:when><c:otherwise>btn-default</c:otherwise></c:choose>">配送中订单量</a>
                                    <a data-deliveryStatus="4"  class="btn btn-order-search <c:choose><c:when test="${search_EQ_deliveryStatus && search_EQ_deliveryStatus == 4}">btn-success</c:when><c:otherwise>btn-default</c:otherwise></c:choose>">已配送订单量</a>
                                    <a data-deliveryStatus="5" class="btn btn-order-search <c:choose><c:when test="${search_EQ_deliveryStatus && search_EQ_deliveryStatus == 5}">btn-success</c:when><c:otherwise>btn-default</c:otherwise></c:choose>">已完成订单量</a>
                                </div>
                                <div id="pagination-body" style="margin-top:10px;"></div>
                                <input type="hidden" name="page" value="${pagination.page}">
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
<script src="${ctx}/static/js/common/pagination.js"></script>
<script src="${ctx}/static/js/orderGoodsQuery/list.js"></script>
<script type="text/javascript">
//日期－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－
$('#beginDate').datetimepicker({
	format: 'YYYY-MM-DD HH:mm',
	icons: {
        time: 'fa fa-clock-o',
        date: 'fa fa-calendar',
        up: 'fa fa-chevron-up',
        down: 'fa fa-chevron-down',
        previous: 'fa fa-chevron-left',
        next: 'fa fa-chevron-right',
        today: 'fa fa-crosshairs',
        clear: 'fa fa-trash'
      }
  });

$('#deliveryDate').datetimepicker({
    format: 'YYYY-MM-DD',
    icons: {
        time: 'fa fa-clock-o',
        date: 'fa fa-calendar',
        up: 'fa fa-chevron-up',
        down: 'fa fa-chevron-down',
        previous: 'fa fa-chevron-left',
        next: 'fa fa-chevron-right',
        today: 'fa fa-crosshairs',
        clear: 'fa fa-trash'
    }
});
  
$('#endDate').datetimepicker({
	format: 'YYYY-MM-DD HH:mm',
	icons: {
        time: 'fa fa-clock-o',
        date: 'fa fa-calendar',
        up: 'fa fa-chevron-up',
        down: 'fa fa-chevron-down',
        previous: 'fa fa-chevron-left',
        next: 'fa fa-chevron-right',
        today: 'fa fa-crosshairs',
        clear: 'fa fa-trash'
      }
  });
//日期－－－－－－－－－－－－－－－－－－－－－－－－－－－－ 


</script>

</body>
</html>