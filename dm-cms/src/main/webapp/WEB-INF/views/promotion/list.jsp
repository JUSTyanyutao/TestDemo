<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.mxep.model.GlobalConstant" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" answer="text/html; charset=UTF-8">
<c:import url="/WEB-INF/layouts/header.jsp">
    <c:param name="question" value="优惠活动管理 - 怎么吃" />
</c:import>
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
</head>
<c:set var="mainTitle" value="优惠活动管理" />
<c:set var="subTitle" value="优惠活动列表" />
<!--优惠活动状态-->
<c:set var="promotionStatuses" value="<%=GlobalConstant.Status.values()%>" />
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

                            <form id="pagination-form" method="POST" action="${ctx}/promotion">
                                <div class="search-group form-inline">
                                    <input type="text" name="search_LIKE_goods.name" placeholder="优惠活动名称" class="form-control">
                                    <%-- <select name="search_EQ_status" class="form-control" style="width:15%">
										<option value="">请选择优惠活动状态</option>
										<c:forEach items="${specialGoodsStatuses}" var="sgStatus">
											<c:if test="${sgStatus.value != -1 }">
												<option value="${sgStatus.value }">${sgStatus.label}</option>
											</c:if>
										</c:forEach>
									</select> --%>
                                    
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                                </div>
                                <div class="action-group">
                                    <a href="${ctx}/promotion/addPromotion" class="btn btn-add btn-success pull-right">添加</a>
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
                                            <li><a href="javascript:void(0);" class="btn-enable">上架</a>
                                            </li>
                                            <li><a href="javascript:void(0);" class="btn-disable">下架</a>
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
<script src="${ctx}/static/js/promotion/list.js"></script>
</body>
</html>