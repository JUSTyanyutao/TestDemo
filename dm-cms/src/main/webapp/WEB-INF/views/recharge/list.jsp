<%@ page import="com.mxep.model.order.Order" %>
<%@ page import="com.mxep.model.base.Label" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" answer="text/html; charset=UTF-8">
<c:import url="/WEB-INF/layouts/header.jsp">
    <c:param name="question" value="充值记录 - 怎么吃" />
</c:import>
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
</head>
<c:set var="mainTitle" value="充值记录" />
<c:set var="subTitle" value="充值记录列表" />
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

                            <form id="pagination-form" method="POST" action="${ctx}/recharges">
                                <div class="search-group form-inline">
                                     <input type="text" name="search_LIKE_member.mobile" placeholder="手机号" class="form-control">
                                     <input type="text" name="search_LIKE_tradeNo" placeholder="交易号" class="form-control">
									 <div id="beginDate" class="input-group date">
		                                 <input type="text" name="search_GTE_createTime" class="form-control" placeholder="请输入起始日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
	                                  </div>
	                                  <div id="endDate" class="input-group date">
		                                 <input type="text" name="search_LTE_createTime" class="form-control" placeholder="请输入结束日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
	                                  </div>
								 </div>
                                
                                <div class="search-group form-inline">
									  <select name="search_EQ_status" class="form-control" style="width:17%">
										<option value="">请选择充值状态</option>
										<option value="1">等待支付</option>
										<option value="2">支付成功</option>
										<option value="3">支付失败</option>
									 </select>
									
									 <select name="search_EQ_onlinePayId" class="form-control" style="width:17%">
										<option value="">请选择充值来源</option>
										<option value="2">支付宝</option>
										<option value="3">微信支付</option>
									 </select>
									
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                                    <a href="javascript:;" style="margin-left:15px;" onclick="exportRecharges()" class="btn btn-add btn-success pull-right">导出Excel</a>
                                </div>
                                
                                
                                <div class="action-group">
                                    	
                                </div>
                                <div id="pagination-body">

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
<script src="${ctx}/static/js/recharge/list.js"></script>
<script type="text/javascript">

function exportRecharges(){
	var currentHref = window.location.href;
	var newHref = window.ctx+"/recharge/export?fileName=rechargeReport";
	if(currentHref.indexOf("?") > -1){
		newHref = newHref+"&"+currentHref.split("?")[1];
	}
	newHref = newHref+"&"+$("#pagination-form").serialize();
	//console.log(newHref);
	window.location.href = newHref;
	
}


$('#beginDate').datetimepicker({
	format: 'YYYY-MM-DD HH:mm:ss',
	icons: {
        time: 'fa fa-clock-o',
        date: 'fa fa-calendar',
        up: 'fa fa-chevron-up',
        down: 'fa fa-chevron-down',
        previous: 'fa fa-chevron-left',
        next: 'fa fa-chevron-right',
        today: 'fa fa-crosshairs',
        clear: 'fa fa-trash',
        
      }
  });
  
$('#endDate').datetimepicker({
	format: 'YYYY-MM-DD HH:mm:ss',
	icons: {
        time: 'fa fa-clock-o',
        date: 'fa fa-calendar',
        up: 'fa fa-chevron-up',
        down: 'fa fa-chevron-down',
        previous: 'fa fa-chevron-left',
        next: 'fa fa-chevron-right',
        today: 'fa fa-crosshairs',
        clear: 'fa fa-trash',
        
      }
  });
</script>
</body>
</html>
