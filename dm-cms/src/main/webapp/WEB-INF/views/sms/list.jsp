<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.mxep.model.enums.EnumSmsStatus" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="短信推送管理 - 达膜贴膜" />
    </c:import>
</head>
<c:set var="mainTitle" value="短信推送管理" />
<c:set var="subTitle" value="短信推送列表" />
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

                            <form id="pagination-form" method="POST" action="${ctx}/sms/list">
                                <div class="search-group form-inline">

                                    <input type="text" name="search_LIKE_mobile" placeholder="手机号码" class="form-control">

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
                                <div class="action-group">
                                    <button type="button" class="btn btn-add btn-success pull-right">添加</button>
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="question" class="dropdown-menu animated swing">
                                            <li><a href="#" class="btn-edit-action">编辑</a>
                                            </li>
                                            <li><a href="#" class="btn-delete-action">删除</a>
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
<script src="${ctx}/static/js/sms/list.js"></script>
<script src="${ctx}/static/js/common/timeDay.js"></script>
</body>
</html>