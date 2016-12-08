<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="会员优惠券管理 - 怎么吃" />
    </c:import>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
</head>

<c:set var="mainTitle" value="会员优惠券管理" />
<c:set var="subTitle" value="会员优惠券列表" />

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
                        <input type="hidden" name="memberId" id="memberId" value="${memberId}">
                        <div class="panel-body table-fit">
                            <form id="pagination-form" class="" method="POST" action="${ctx}/member/coupon/${memberId}">
                                <div class="search-group form-inline">
                                    <div id="beginDate" class="input-group date">
		                                 <input type="text" name="search_GTE_createTime" class="form-control" placeholder="请输入创建起始日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
	                               </div>
	                               <div id="endDate" class="input-group date">
		                                 <input type="text" name="search_LTE_createTime" class="form-control" placeholder="请输入创建结束日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
	                               </div>
	                               
	                               <div id="usedBeginDate" class="input-group date">
		                                 <input type="text" name="search_GTE_usedTime" class="form-control" placeholder="请输入使用起始日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
	                               </div>
	                               <div id="usedEndDate" class="input-group date">
		                                 <input type="text" name="search_LTE_usedTime" class="form-control" placeholder="请输入使用结束日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
	                               </div>
	                                <select name="search_EQ_status" class="form-control" style="width:17%">
										<option value="">请选择状态</option>
										<option value="-1">无效</option>
										<option value="-2">已过期</option>
										<option value="0">待激活</option>
										<option value="1">已激活</option>
										<option value="2">已使用</option>
									 </select>
                           
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                                </div>
                                <div class="action-group">
                                    <a href="${ctx}/member/coupon/add/${memberId}" class="btn btn-add btn-success pull-right">添加优惠券</a>
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="menu" class="dropdown-menu animated swing">
                                            <li><a href="#" class="btn-edit-action">编辑</a>
                                            </li>
                                            <li><a href="#" class="btn-update">设为无效</a>
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
<script src="${ctx}/static/js/coupon/list.js"></script>
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
  
$('#usedBeginDate').datetimepicker({
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
  
$('#usedEndDate').datetimepicker({
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