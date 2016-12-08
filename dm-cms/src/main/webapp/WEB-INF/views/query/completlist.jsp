<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.mxep.model.order.Order" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" answer="text/html; charset=UTF-8">
<c:import url="/WEB-INF/layouts/header.jsp">
    <c:param name="question" value="已完成订单汇总管理 - 怎么吃" />
</c:import>
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<style type="text/css">


</style>
</head>

<c:set var="mainTitle" value="已完成订单汇总管理" />
<c:set var="subTitle" value="已完成订单汇总列表" />
<!--订单配送完成状态-->
<c:set var="completedStatus" value="<%=Order.OrderStatus.Completed.value%>" />
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

                            <form id="pagination-form" method="POST" action="${ctx}/completeQuery">
                                <div class="search-group form-inline">
                                
                                	<input type="hidden" name="search_EQ_status" value="${completedStatus}">
                                	
                                	<select class="form-control" name="search_EQ_stationId" style="margin-left:10px;">
                                    	<option value="">请选择站点</option>
                                    	<c:forEach items="${siteStations}" var="siteStation">
                                    		<option value="${siteStation.sid}">${siteStation.stationName}</option>
                                    	</c:forEach>
                                    </select>
                                    
                                    <div id="beginDate" class="input-group date" >
		                                 <input type="text" name="search_GTE_createTime" value="<fmt:formatDate value='${currentDate}' pattern='yyyy-MM-dd 00:00' />"
		                                 		class="form-control" placeholder="请输入起始日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
	                               </div>
	                               <div id="endDate" class="input-group date">
		                                 <input type="text" name="search_LTE_createTime" value="<fmt:formatDate value='${currentDate}' pattern='yyyy-MM-dd HH:mm' />"
		                                 		class="form-control" placeholder="请输入结束日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
	                               </div>
                                    <div id="deliveryDate" class="input-group date">
                                        <input type="text" name="search_EQ_deliveryDate" value="' pattern='yyyy-MM-dd' />"
                                               class="form-control" placeholder="请输入派送日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
                                    </div>
                                    
                                    

                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                                </div>
                                <div class="action-group">
                                    <a href="javascript:;" onclick="exportOrderGoods()" class="btn btn-add btn-success pull-right">导出Excel</a>
                                    <%-- <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="goods" class="dropdown-menu animated swing">
                                            <li><a href="#" class="btn-edit-action">编辑</a>
                                            </li>
                                            <li><a href="#" class="btn-delete-action">删除</a>
                                            </li>
                                            <li><a href="javascript:void(0);" class="btn-cabinet">柜子管理</a>
                                            </li>
                                            <li class="divider"></li>
                                            <li><a href="javascript:void(0);" class="btn-enable">解禁</a>
                                            </li>
                                            <li><a href="javascript:void(0);" class="btn-disable">禁用</a>
                                            </li>
                                            
                                            
                                        </ul>
                                    </div> --%>
                                </div>
                                <div id="pagination-body" style="margin-top:50px;">

                                </div>
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

/*****验证查询条件**********/






function exportOrderGoods(){
	var currentHref = window.location.href;
	var newHref = window.ctx+"/orderGoodsQuery/export?fileName=compltedOrdersReport&"+$("#pagination-form").serialize();
	if(currentHref.indexOf("?") > -1){
		newHref = newHref+"&"+currentHref.split("?")[1];
	}
	window.location.href = newHref;
	
}


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