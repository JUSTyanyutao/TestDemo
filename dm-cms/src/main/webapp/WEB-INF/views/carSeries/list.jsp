<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" answer="text/html; charset=UTF-8">
<c:import url="/WEB-INF/layouts/header.jsp">
    <c:param name="question" value="车 品牌管理" />
</c:import>
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.sorting{
		background: url('<c:url value="/static/img/sort_both.png"/>') no-repeat center right;
		cursor:pointer;
	}

</style>
</head>
<c:set var="mainTitle" value="系列管理" />
<c:set var="subTitle" value="系列列表" />


<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp" />
    <section>
        <div class="answer-wrapper">
            <h3>${carBrand.name}${mainTitle}
                <small>${carBrand.name}${subTitle}</small>
            </h3>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">${carBrand.name}${subTitle}</div>
                        <div class="panel-body table-fit">

                            <form id="pagination-form" method="POST" action="${ctx}/carSeries/list/${carBrand.id}">
                                <input type="hidden" id="id" value="${carBrand.id}"/>

                                <div class="search-group form-inline">
                                    <input type="text" name="search_LIKE_name" placeholder="系列名称" class="form-control">
                                    <%--<select id="display" name="search_EQ_isDisplay" style="width:20%"></select>--%>
                                    <label class="control-label">系列状态:</label>
                                    <label class="checkbox">&nbsp;
                                        <input type="checkbox" name="search_EQ_status" value="1"  />启用  &nbsp;
                                        <input type="checkbox" name="search_EQ_status" value="0" />禁用
                                    </label>
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                               </div>
                                <div class="action-group">
                                <%--<a href="javascript:;" style="margin-left:15px;" onclick="exportGoods()" class="btn btn-export btn-success pull-right">导出Excel</a>--%>
                                    <button type="button" class="btn btn-add btn-success pull-right">添加</button>
                                    <a href="${ctx}/carBrand/list" class="btn btn-success pull-right">返回</a>

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
<script src="${ctx}/static/js/carSeries/list.js"></script>
</body>
</html>