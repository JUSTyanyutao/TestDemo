<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.fanfou.model.member.Agreement" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="合并订单管理 - 怎么吃" />
    </c:import>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
</head>

<c:set var="mainTitle" value="合并订单管理" />
<c:set var="subTitle" value="合并订单列表" />
<!--协议类型  -->
<c:set var="types" value="<%=Agreement.AgreementType.values()%>" />
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
                            <form id="pagination-form" class="" method="POST" action="${ctx}/mergeOrder">
                                <div class="search-group form-inline">
                                
                                    <select class="form-control" name="search_EQ_stationId" style="width:20%">
                                    	<option value="">请选择站点</option>
                                    	<c:forEach items="${siteStations}" var="siteStation">
                                    		<option value="${siteStation.sid}">${siteStation.stationName}</option>
                                    	</c:forEach>
                                    </select>
                           
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                                </div>
                                <div class="action-group">
                                    <%-- <a href="${ctx}/agreement/addAgreement" class="btn btn-add btn-success pull-right">添加协议</a> --%>
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="menu" class="dropdown-menu animated swing">
                                            <li><a href="#" class="btn-merge-action">订单合并</a>
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
<script src="${ctx}/static/js/mergeOrder/list.js"></script>
</body>

</html>