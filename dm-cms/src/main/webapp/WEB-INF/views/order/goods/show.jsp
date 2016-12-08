<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.mxep.model.enums.EnumOrderType" %>
<%@ page import="com.mxep.model.enums.EnumOrderStatus" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="订单管理 - 达膜" />
    </c:import>
    <%--<jsp:include page="../orderDelivery/print_barcode.jsp"/>--%>
    <link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
    <link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
	<link rel="stylesheet" href="${ctx}/static/third/timeline/style.css"> <!-- Resource style -->



</head>

<c:set var="mainTitle" value="订单管理" />
<c:set var="subTitle" value="订单详情" />
<!--订单类型-->
<c:set var="orderTypes" value="<%=EnumOrderType.values()%>" />
<!--订单状态-->
<c:set var="orderStatuses" value="<%=EnumOrderStatus.values()%>" />


<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp" />
    <section>
        <div class="answer-wrapper">
            <h3>${mainTitle}
                <small>${subTitle}</small>
            </h3>
			<button id="btn-order-back" class="btn btn-success" style="margin-bottom:10px;" onclick="window.history.go(-1)">返回</button>
			<c:set var="completeStatus" value="<%=EnumOrderStatus.Completed.getValue()%>"></c:set>
			<c:if test="${order.status eq completeStatus}">
				<button  class="btn btn-add btn-success" style="margin-bottom:10px;">订单已完成</button>
			</c:if>
            <div class="panel panel-default">
               <div class="panel-heading">订单基本信息</div>
               <!-- START table-responsive-->
               <div class="table-grid table-grid-desktop"  >
                  <div class="col">
	               	 <div class="row" >
						<div class="col-md-4">
							<div class="form-group">
								<label class="col-sm-4 control-label">订单编号:</label>
								<div class="col-sm-7">
									<label class="control-label">${order.orderSn }</label>
								</div>
							 </div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="col-sm-4 control-label">订单状态:</label>
								<div class="col-sm-7">
									<label class="control-label">
										<c:forEach items="${orderStatuses}" var="item">
											<c:if test="${order.status == item.value}">
												${item.label}
											</c:if>
										</c:forEach>
									</label>
								</div>
							 </div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="col-sm-4 control-label">订单类型:</label>
								<div class="col-sm-7">
									<label class="control-label">
										<c:if test="${order.orderType eq 1}">安卓</c:if>
										<c:if test="${order.orderType eq 2}">IOS</c:if>
										<c:if test="${order.orderType eq 3}">微信</c:if>
									</label>
								</div>
							 </div>
						</div>
					 </div>

					  <div class="row">
						  <div class="col-md-4">
							  <div class="form-group">
								  <label class="col-sm-4 control-label">收货人:</label>
								  <div class="col-sm-7">
									  <label class="control-label">
										  ${order.memberAddress.consignee}
									  </label>
								  </div>
							  </div>
						  </div>
						  <div class="col-md-4">
							  <div class="form-group">
								  <label class="col-sm-4 control-label">收货号码:</label>
								  <div class="col-sm-7">
									  <label  class="control-label">
										  ${order.memberAddress.mobile}
									  </label>
								  </div>
							  </div>
						  </div>
						  <div class="col-md-4">
							  <div class="form-group">
								  <label class="col-sm-4 control-label">收货地址:</label>
								  <div class="col-sm-7">
									  <label class="control-label">
										  ${order.memberAddress.province.name}${order.memberAddress.city.name}${order.memberAddress.district.name}${order.memberAddress.street}
									  </label>
								  </div>
							  </div>
						  </div>
					  </div>

                      <div class="row" >
						  <div class="col-md-4">
							  <div class="form-group">
								  <label class="col-sm-4 control-label">订单总价:</label>
								  <div class="col-sm-7">
									  <label class="control-label">${order.totalPrice }</label>
								  </div>
							  </div>
						  </div>

                        	<div class="col-md-4">
                        		<div class="form-group">
                              		<label class="col-sm-4 control-label">实际支付金额:</label>
                              		<div class="col-sm-7">
                              			<label class="control-label">${order.payPrice}</label>
                              		</div>
                       			 </div>
                        	</div>

                      </div>
					  <div class="row">
						  <div class="col-md-4">
							  <div class="form-group">
								  <label class="col-sm-4 control-label">创建日期:</label>
								  <div class="col-sm-7">
									  <label class="control-label">
										  <fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${order.createTime}'/>
									  </label>
								  </div>
							  </div>
						  </div>
						  <div class="col-md-4">
							  <div class="form-group">
								  <label class="col-sm-4 control-label">更新日期:</label>
								  <div class="col-sm-7">
									  <label id="updateTimeLabel" class="control-label">
										  <fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${order.updateTime}'/>
									  </label>
								  </div>
							  </div>
						  </div>
					  </div>

                      <div class="row" >
                        	<div class="col-md-8">
                        		<div class="form-group">
                              		<label class="col-sm-2 control-label">订单备注:</label>
                              		<div class="col-sm-10">
                              			<label id="remarkLabel" class="control-label">${empty order.remark ? '无' : order.remark }</label>
										<%--<button id="remarkBtn" class="btn btn-primary pull-right">修改备注</button>--%>
                              		</div>
                       			 </div>
                        	</div>
                      </div>
                      <br/>
	               </div>
               </div>
            </div>


			<div class="panel panel-default">
				<input type="hidden" id="orderId" value="${order.id}">
				<div class="panel-heading">会员基本信息</div>
				<!-- START table-responsive-->
				<div class="table-grid table-grid-desktop"  >
					<div class="col">
						<div class="row" >
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">会员帐号:</label>
									<div class="col-sm-8">
										<label class="control-label">${order.member.mobile }</label>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">姓名:</label>
									<div class="col-sm-8">
										<label class="control-label">${order.member.profile.realName }</label>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">昵称:</label>
									<div class="col-sm-8">
										<label class="control-label">${order.member.profile.nickname }</label>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<%--商品  信息--%>

			<div class="panel panel-default">
				<div class="panel-heading">订单商品信息</div>
				<!-- START table-responsive-->
				<div class="table-grid table-grid-desktop"  >
					<div class="col">
						<c:forEach items="${order.orderGoodsList}" var="orderGoods">
						<div class="row" >
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">商品名称:</label>
									<div class="col-sm-8">
										<label class="control-label">
											${orderGoods.goodsName      }
										</label>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">售价:</label>
									<div class="col-sm-8">
										<label class="control-label">${orderGoods.goods.price}</label>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">分类:</label>
									<div class="col-sm-8">
										<label class="control-label">
												${orderGoods.goods.category.name}
										</label>
									</div>
								</div>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
				<hr/>
			</div>

        </div>
    </section>
    <c:import url="/WEB-INF/layouts/content_footer.jsp" />
</div>

<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>
<script src="${ctx}/static/js/order/goods/show.js"></script>
</body>
</html>