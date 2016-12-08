<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--<%@ page import="com.mxep.model.enums.EnumFaqType" %>--%>
<%@ page import="com.mxep.model.log.LogMemberBalance" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" answer="text/html; charset=UTF-8">
<c:import url="/WEB-INF/layouts/header.jsp">
    <c:param name="question" value="会员余额记录 - 梦想e拍" />
</c:import>
</head>
<c:set var="mainTitle" value="会员余额记录" />
<c:set var="subTitle" value="会员余额记录列表" />
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

                            <form id="pagination-form" method="POST" action="${ctx}/logMemberBalance/list">
                                <div class="search-group form-inline">
                                    <input type="text" name="search_LIKE_member.mobile" placeholder="用户手机号" class="form-control">
                                    <input type="text" name="search_LIKE_orderId" placeholder="订单ID" class="form-control">
                                    <div id="startDate" class="input-group date">
                                        <input id="gtCreateTime" type="text" name="search_GTE_createTime" class="form-control" value="<fmt:formatDate value='${defaultStartDate}' pattern='yyyy-MM-dd 00:00' />" placeholder="请输入起始日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
                                    </div>
                                    <div id="endDate" class="input-group date">
                                        <input id="ltCreateTime" type="text" name="search_LTE_createTime" class="form-control" value="<fmt:formatDate value='${defaultEndDate}' pattern='yyyy-MM-dd 23:59' />" placeholder="请输入结束日期">
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
<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/js/common/pagination.js"></script>
<script src="${ctx}/static/js/logMemberPoints/list.js"></script>
<script src="${ctx}/static/js/common/timeSecond.js"></script>
</body>
</html>