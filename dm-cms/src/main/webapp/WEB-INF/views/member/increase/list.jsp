<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="title" value="会员增长报表管理" />
    </c:import>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
</head>

<c:set var="mainTitle" value="会员增长报表管理" />
<c:set var="subTitle" value="会员增长报表管理" />

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
                        <div class="panel-body table-fit">
                            <form id="pagination-form" class="" method="POST" action="${ctx}/member/increase">
                                <div class="search-group form-inline">
                                    <div id="startDate" class="input-group date">
                                        <input type="text" id="gtCreateTime" name="startDate" class="form-control" value="<fmt:formatDate value='${defaultStartDate}' pattern='yyyy-MM-dd' />" placeholder="请输入起始日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
                                    </div>
                                    <div id="endDate" class="input-group date">
                                        <input type="text" id="ltCreateTime" name="endDate" class="form-control" value="<fmt:formatDate value='${defaultEndDate}' pattern='yyyy-MM-dd' />" placeholder="请输入结束日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
                                    </div>
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
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

<div id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="false" class="modal fade in">
    <div class="modal-dialog">
        <div class="modal-content">

        </div>
    </div>
</div>


<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/js/common/pagination.js"></script>
<script src="${ctx}/static/js/order/sales/list.js"></script>
<script src="${ctx}/static/js/common/timeDay.js"></script>
</body>

</html>