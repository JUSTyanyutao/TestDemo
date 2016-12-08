<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mxep.model.enums.EnumOrderType" %>
<%@ page import="com.mxep.model.enums.EnumOrderStatus" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="企业订单管理" />
    </c:import>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
</head>

<c:set var="mainTitle" value="企业订单管理" />
<c:set var="subTitle" value="企业订单列表" />

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
                            <form id="pagination-form" class="" method="POST" action="${ctx}/order/shop/list">
                                <%-- <input type="hidden" name="pid" value="${pid}"> --%>
                                <div class="search-group form-inline">
                                    
                                    <!-- search_EQ_goodsId  -->
                                    <input type="text" name="search_LIKE_orderSn" placeholder="订单编号" class="form-control">
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
                                </div>
                                
                                <div class="search-group form-inline">
                                    <!--订单类型-->
                                    <c:set var="orderTypes" value="<%=EnumOrderType.values()%>" />
                                    <!--订单状态-->
                                    <c:set var="orderStatuses" value="<%=EnumOrderStatus.values()%>" />

									<%--<input type="text" name="search_LIKE_goodsName" placeholder="商品名称" class="form-control">--%>
                                    <input type="text" name="search_LIKE_member.mobile" placeholder="会员帐号" class="form-control">
                                    
                                    <select class="form-control" name="search_EQ_status" style="width:20%">
                                    	<option value="">请选择订单状态</option>
                                    	<c:forEach items="${orderStatuses}" var="status">
                                    		<option value="${status.value}">${status.label}</option>
                                    	</c:forEach>
                                    </select>
                                    
                                    <button type="button" class="btn btn-primary btn-search" style="margin-left:20px;">搜索</button>
                                    
                                    
                           
                                </div>
                                
                                <div class="search-group form-inline">
                                	<div style="height:50px;">
                                
                                    	<a href="javascript:;" style="margin-left:15px;" id="export" class="btn btn-add btn-success pull-right">导出Excel</a>
                                    	<%--<a href="javascript:;" style="margin-left:15px;"  class="btn btn-updateOrderSn btn-success pull-right">指派技师</a>--%>

                                		<%--<a href="javascript:;" style="margin-left:15px;" onclick="exportOrdersByMember()" class="btn btn-add btn-success pull-right">分客户订单导出Excel</a>--%>
                                	</div>
                                </div>
                                <div class="action-group">
                                    <%-- <a href="${ctx}/member/addMember" class="btn btn-add btn-success pull-right">添加订单</a> --%>
                                    <!-- <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="menu" class="dropdown-menu animated swing">
                                            <li><a href="#" class="btn-edit-action">编辑</a>
                                            </li>
                                            <li><a href="#" class="btn-delete-action">删除</a>
                                            </li>
                                        </ul>
                                    </div> -->
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

<!-- Modal Large 发货  -->
<%--<div id="solveContent" tabindex="-1" role="dialog"  aria-hidden="false" class="modal fade">--%>
	<%--<div class="modal-dialog modal-md">--%>
		<%--<div class="modal-content">--%>
			<%--<div class="modal-header">--%>
				<%--<button type="button" data-dismiss="modal" aria-label="Close" class="close">--%>
					<%--<span aria-hidden="true">&times;</span>--%>
				<%--</button>--%>
				<%--<h4 id="myModalLabelLarge" class="modal-title">指派技师</h4>--%>
			<%--</div>--%>
			<%--<form id="solveForm" method="POST">--%>
				<%--<div class="modal-body" >--%>
					<%--<div class="form-group">--%>
						<%--<label for="message-text" class="control-label">订单单号:</label>--%>
						<%--<input name="orderSn"  class="form-control" id="orderSn" />--%>
						<%--<input type="hidden" id="oid" name="oid" />--%>
					<%--</div>--%>

				<%--</div>--%>
				<%--<div class="modal-footer">--%>
					<%--<button id="cancleSolve" type="button" data-dismiss="modal" class="btn btn-default">取消</button>--%>
					<%--<button id="confirmsolve" type="submit" class="btn btn-submit btn-primary modal-add" data-loading-text="确认中...">确认</button>--%>
				<%--</div>--%>
			<%--</form>--%>
		<%--</div>--%>
	<%--</div>--%>
<%--</div>--%>
<!-- Modal Large-->



<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/js/common/pagination.js"></script>
<script src="${ctx}/static/js/order/shop/list.js"></script>
<script src="${ctx}/static/js/common/timeSecond.js"></script>


</body>
</html>