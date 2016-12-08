<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.mxep.model.enums.EnumShareCheck" %>
<%@ page import="com.mxep.model.enums.EnumPlatformType" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" answer="text/html; charset=UTF-8">
<c:import url="/WEB-INF/layouts/header.jsp">
    <c:param name="question" value="晒单管理 - 梦想e拍" />
</c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
</head>
<c:set var="mainTitle" value="晒单管理" />
<c:set var="subTitle" value="晒单列表" />
<style type="text/css">
    .messageTd{
        max-width: 200px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
</style>

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

                            <form id="pagination-form" method="POST" action="${ctx}/shares">
                                <div class="search-group form-inline">
                                    <input type="text" name="search_LIKE_auction.question" placeholder="竞拍品标题" class="form-control">
                                    <input type="text" name="search_LIKE_goods_name" placeholder="商品" class="form-control">
                                    <input type="text" name="search_LIKE_member.mobile" placeholder="会员手机号" class="form-control">
                                    <select class="form-control" name="search_EQ_checkStatus" style="width:20%">
                                        <c:set value="<%=EnumShareCheck.values()%>" var="statuses"/>
                                        <option value="">请选择审核状态</option>
                                        <c:forEach items="${statuses}" var="status">
                                            <option value="${status.value}">${status.label}</option>
                                        </c:forEach>
                                    </select>
                                    <div id="startDate" class="input-group date">
                                        <input id="gtCreateTime"  type="text" name="search_GTE_createTime" class="form-control" value="<fmt:formatDate value='${defaultStartDate}' pattern='yyyy-MM-dd 00:00' />" placeholder="请输入起始日期">
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
                                    <%--<button type="button" class="btn btn-add btn-success pull-right">添加</button>--%>
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="carousel" class="dropdown-menu animated swing">
                                            <li><a href="javascript:void(0)" class="btn-checked-action">审核</a>
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


<!-- Modal Large  -->
<div id="solveContent" tabindex="-1" role="dialog"  aria-hidden="false" class="modal fade">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" data-dismiss="modal" aria-label="Close" class="close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 id="myModalLabelLarge" class="modal-title">审核反馈</h4>
            </div>
            <form id="solveForm" method="POST">
                <div class="modal-body" >

                    <div class="form-group">
                        <label for="comment" class="control-label">反馈内容:</label>
                        <textarea name="comment"  class="form-control" id="comment"></textarea>
                        <input type="hidden" id="id" name="id" />
                        <label for="sort" class="control-label">排序:</label>
                        <input id="sort" name="sort" data-ui-slider="" type="text" value="50" data-slider-min="1"
                               data-slider-max="50" data-slider-step="1" data-slider-value="${share.sort}"
                               data-slider-orientation="horizontal" class="slider slider-horizontal"
                               data="value: '50'" style="display: none;">
                    </div>


                </div>
                <div class="modal-footer">
                    <button id="cancleSolve" type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button id="rejectsolve" type="submit" class="btn btn-submit btn-primary modal-add" data-loading-text="驳回中...">驳回</button>
                    <button id="confirmsolve" type="submit" class="btn btn-submit btn-primary modal-add" data-loading-text="通过中...">通过</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal Large-->



<!-- Modal-->
<div id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" data-dismiss="modal" aria-label="Close" class="close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 id="myModalLabel" class="modal-title">内容详情</h4>
            </div>
            <div class="modal-body" style="word-wrap: break-word;">...</div>
        </div>
    </div>
</div>
<!-- Modal-->

<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>
<script src="${ctx}/static/js/common/pagination.js"></script>
<script src="${ctx}/static/js/share/list.js"></script>
<script src="${ctx}/static/js/common/timeDay.js"></script>
</body>
</html>