<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.fanfou.model.cabinet.SiteCabinet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" answer="text/html; charset=UTF-8">
<c:import url="/WEB-INF/layouts/header.jsp">
    <c:param name="question" value="箱柜管理 - 怎么吃" />
</c:import>
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.c-checkbox{margin-left:10px}

.boxContainer{
		position:relative;
}
.boxStyle{
	border-radius:5px;
	width:120px;
	height:100px;
	padding:10px;
	margin:10px;
	box-shadow:4px 4px 6px #666; 
}

.box-invalid{
	background-color: #AAAAAA;
    color: #ffffff !important;
}
.box-free{
	background-color: #37bc9b;
    color: #ffffff !important;
}
.box-used{
	background-color: #ff902b;
    color: #ffffff !important;
}
.box-locked{
	background-color: #CCCC33;
    color: #ffffff !important;
}
.box-forbid{
	background-color: #131e26;
    color: #ffffff !important;
}
.box-exception{
	background-color: #f05050;
    color: #ffffff !important;
}

.notShow{
	background-color: #EEEEEE;
}

.openStyle{
	transform-origin: 0 24%;
	transform: scale(0.95,1) skew(0deg,9deg);
	box-shadow:4px 4px 6px white; 
}
.openShadow{
	width: 100%;
    height: 100%;
    z-index: 0;
    box-shadow: 1px 3px 9px #666 inset;
    position: absolute;
    top: 0px;
    transform: scale(0.8);
    left: -4px;
    border-radius: 3px;
}
.icon-social-dropbox{
	transform: scale(2,2);
}

</style>
</head>
<c:set var="mainTitle" value="箱柜管理" />
<c:set var="subTitle" value="箱柜列表" />
<!--柜子状态-->
<c:set var="siteCabinetStatuses" value="<%=SiteCabinet.SiteCabinetStatus.values()%>" />
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

                            <form id="pagination-form" method="POST" action="${ctx}/siteCabinet">
                                <div class="search-group form-inline">
                                	<div class="col-sm-3">
                                		<select name="search_EQ_stationNo" class="form-control" style="width:100%" data-select="${siteStation.stationNo}">
											<option value="">请选择柜子所在站点</option>
											<c:forEach items="${siteStationList}" var="siteStation">
												<option value="${siteStation.stationNo }" <c:if test="${siteStation.stationNo == siteStation.stationNo}">selected</c:if> >${siteStation.stationName}</option>
											</c:forEach>
										</select>
                                	</div>
                                	
                                	<div class="col-sm-3">
                                		<select name="search_EQ_cabinetStatus" class="form-control" style="width:100%">
											<option value="">请选择柜子状态</option>
											<c:forEach items="${siteCabinetStatuses}" var="siteCabinetStatus">
												<c:if test="${siteCabinetStatus.value != -1 }">
													<option value="${siteCabinetStatus.value }">${siteCabinetStatus.label}</option>
												</c:if>
											</c:forEach>
										</select>
                                	</div>

                                    <button type="button" class="btn btn-primary btn-search" style="margin-left:10px">搜索</button>
                                </div>
                               
                                <div id="pagination-body" style="margin-top:30px">

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
<script src="${ctx}/static/js/siteCabinet/list.js"></script>

</body>
</html>