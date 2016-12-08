<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.mxep.model.goods.Goods" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" answer="text/html; charset=UTF-8">
<c:import url="/WEB-INF/layouts/header.jsp">
    <c:param name="question" value="按钮管理" />
</c:import>
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.sorting{
		background: url('<c:url value="/static/img/sort_both.png"/>') no-repeat center right;
		cursor:pointer;
	}

</style>
</head>
<c:set var="mainTitle" value="按钮管理" />
<c:set var="subTitle" value="按钮列表" />


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

                            <form id="pagination-form" method="POST" action="${ctx}/module/list">
                                <div class="search-group form-inline">
                                    <input type="text" name="search_LIKE_name" placeholder="按钮名称" class="form-control">
                                    <div id="startDate" class="input-group date">
                                        <input type="text" id="gtCreateTime" name="search_GTE_createTime" class="form-control" value="<fmt:formatDate value='${defaultStartDate}' pattern='yyyy-MM-dd 00:00' />" placeholder="请输入起始日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
                                    </div>
                                    <div id="endDate" class="input-group date">
                                        <input type="text" id="ltCreateTime" name="search_LTE_createTime" class="form-control" value="<fmt:formatDate value='${defaultEndDate}' pattern='yyyy-MM-dd 23:59' />" placeholder="请输入结束日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
                                    </div>
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                               </div>
                                <div class="search-group form-inline">
                                    <label class="control-label">按钮状态:</label>
                                    <label class="checkbox">&nbsp;
                                        <input type="checkbox" name="search_EQ_status" value="1"  />启用  &nbsp;
                                        <input type="checkbox" name="search_EQ_status" value="0" />禁用
                                    </label>
                                </div>
                                <div class="action-group">
                                <a href="javascript:void(0);" id="export" style="margin-left:15px;"  class="btn btn-export btn-success pull-right">导出Excel</a>
                                <button type="button" class="btn btn-add btn-success pull-right">添加</button>
                                    <!-- <a href="javascript:;" class="btn btn-discount btn-success pull-right" data-toggle="modal" data-target="#discountContent">全场折扣</a> -->
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="goods" class="dropdown-menu animated swing">
                                            <li><a href="#" class="btn-edit-action">编辑</a>
                                            </li>
                                            <li><a href="#" class="btn-delete-action">删除</a>
                                            </li>
                                            <li class="divider"></li>
                                            <li><a href="javascript:void(0);" class="btn-enable">启用</a>
                                            </li>
                                            <li><a href="javascript:void(0);" class="btn-disable">禁用</a>
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
<script src="${ctx}/static/js/module/list.js"></script>
<script src="${ctx}/static/js/common/timeDay.js"></script>
</body>
</html>