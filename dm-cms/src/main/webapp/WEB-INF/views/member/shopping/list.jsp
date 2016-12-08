<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="会员管理 - 达膜" />
    </c:import>
</head>

<c:set var="mainTitle" value="会员管理" />
<c:set var="subTitle" value="用户购物车" />

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
                            <form id="pagination-form" class="" method="POST" action="${ctx}/member/shoppingCar">

                                <%--<input type="hidden" name="search_EQ_memberId" value="${uid}">--%>

                                <div class="search-group form-inline">
                                </div>
                                <%--<a href="javascript:;" style="margin-left:5px; margin-bottom: 5px;" onclick="exportRecords()" class="btn btn-success">导出Excel</a>--%>
                                <div class="action-group">
                                    <a href="${ctx}/member" class="btn btn-sm btn-back btn-primary pull-right">返回</a>
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="menu" class="dropdown-menu animated swing">
                                            <%--<li><a href="#" class="btn-edit-action">编辑</a></li>--%>
                                            <li><a href="#" class="btn-delete-action">删除</a></li>
                                            <%--<li class="divider"></li>--%>
                                            <%--<li><a href="javascript:void(0);" class="btn-disable">禁用</a></li>--%>
                                            <%--<li><a href="javascript:void(0);" class="btn-enable">解禁</a></li>--%>
                                        </ul>
                                    </div>
                                </div>
                                <div id="pagination-body">

                                </div>

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
<script src="${ctx}/static/js/member/shopping/list.js"></script>
</body>
</html>