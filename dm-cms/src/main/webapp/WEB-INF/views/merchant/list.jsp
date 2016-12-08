<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.fanfou.model.merchant.Merchant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="商家管理 - 怎么吃" />
    </c:import>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
</head>
<c:set var="mainTitle" value="商家管理" />
<c:set var="subTitle" value="商家列表" />
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
                            <form id="pagination-form" class="" method="POST" action="${ctx}/merchant">
                                <div class="search-group form-inline">
                                   <input type="text" name="search_LIKE_mobilephone" placeholder="商家手机" class="form-control">
                                   <input type="text" name="search_LIKE_name" placeholder="商家名称" class="form-control">
                                   <div id="beginDate" class="input-group date">
		                                 <input type="text" name="search_GTE_createTime" class="form-control" placeholder="注册起始日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
	                               </div>
	                               <div id="endDate" class="input-group date">
		                                 <input type="text" name="search_LTE_createTime" class="form-control" placeholder="注册结束日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
	                               </div>
	                              </div> 
	                             <div class="search-group form-inline">  
	                             <c:set var="merchantStatuses" value="<%=Merchant.EmMerchantStatus.values()%>" />
	                               <select class="form-control" name="search_EQ_status" style="width:20%">
                                    	<option value="">请选择状态</option>
                                    	<c:forEach items="${merchantStatuses}" var="status">
                                    		<option value="${status.value}">${status.name}</option>
                                    	</c:forEach>
                                    </select>
                                    <select id="foodMarketSelect" name="search_EQ_marketId" style="width:20%"></select>
                                   <button type="button" class="btn btn-primary btn-search">搜索</button>
                                </div>
                                <div class="action-group">
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="menu" class="dropdown-menu animated swing">
                                            <li><a href="#" class="btn-edit-action">编辑</a>
                                            </li>
                                            <li class="divider"></li>
                                            <li><a href="javascript:void(0);" class="btn-approval">审批</a>
                                            </li>
                                            <li><a href="javascript:void(0);" class="btn-disable">禁用</a>
                                            </li>
                                            <li><a href="javascript:void(0);" class="btn-enable">解禁</a>
                                            </li>
                                            <li class="divider"></li>
                                            <li><a href="javascript:void(0);" class="btn-order">订单</a>
                                            </li>
                                            <li><a href="javascript:void(0);" class="btn-goods">商品</a>
                                            </li>
                                        </ul>
                                    </div>
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
<script src="${ctx}/static/js/merchant/list.js"></script>
<script type="text/javascript">

$('#beginDate').datetimepicker({
	format: 'YYYY-MM-DD',
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
	format: 'YYYY-MM-DD',
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