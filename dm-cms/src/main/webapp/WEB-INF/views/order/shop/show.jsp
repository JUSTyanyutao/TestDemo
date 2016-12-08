<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.mxep.model.enums.EnumOrderType" %>
<%@ page import="com.mxep.model.enums.EnumServiceOrderStatus" %>
<%@ page import="com.mxep.model.enums.EnumServiceOrderStatus" %>
<%@ page import="com.mxep.model.enums.EnumOrderWorkerStatus" %>
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
<c:set var="orderStatuses" value="<%=EnumServiceOrderStatus.values()%>" />
<!-- 技师  订单状态-->
<c:set var="orderWorkerStatuses" value="<%=EnumOrderWorkerStatus.values()%>" />



<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp" />
    <section>
        <div class="answer-wrapper">
            <h3>${mainTitle}
                <small>${subTitle}</small>
            </h3>
			<button id="btn-order-back" class="btn btn-success" style="margin-bottom:10px;" onclick="window.history.go(-1)">返回</button>
			<c:set var="completeStatus" value="<%=EnumServiceOrderStatus.Completed.getValue()%>"></c:set>
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
				<input type="hidden" id="oid" value="${orderWorker.id}">

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
						<br/>
					</div>
				</div>
			</div>

			<div class="panel panel-default">
				<div class="panel-heading">场地信息</div>
				<!-- START table-responsive-->
				<div class="table-grid table-grid-desktop"  >
					<div class="col">
						<div class="row" >
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">场地城市:</label>
									<div class="col-sm-8">
										<label class="control-label">${order.shop.city.name }</label>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">公司名称:</label>
									<div class="col-sm-8">
										<label class="control-label">${order.shop.name }</label>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">联系人:</label>
									<div class="col-sm-8">
										<label class="control-label">${order.shop.contact }</label>
									</div>
								</div>
							</div>
						</div>
						<br/>
						<div class="row" >
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">场地地址:</label>
									<div class="col-sm-8">
										<label class="control-label">${order.shop.address}</label>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">联系方式:</label>
									<div class="col-sm-8">
										<label class="control-label">${order.shop.phone }</label>
									</div>
								</div>
							</div>
							<%--<div class="col-md-4">--%>
								<%--<div class="form-group">--%>
									<%--<label class="col-sm-4 control-label">联系人:</label>--%>
									<%--<div class="col-sm-8">--%>
										<%--<label class="control-label">${order.shop.contact }</label>--%>
									<%--</div>--%>
								<%--</div>--%>
							<%--</div>--%>
						</div>

					</div>
				</div>
			</div>

			<%--商品 信息--%>

			<div class="panel panel-default">
				<div class="panel-heading">订单商品信息</div>
				<!-- START table-responsive-->
				<div class="table-grid table-grid-desktop"  >
					<div class="col">
						<hr style=" height:2px;border:none;border-top:2px dotted #cfdbe2;" />
						<c:forEach items="${order.orderGoodsList}" var="orderGoods" varStatus="i">
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
							<c:if test="${i.count ne order.orderGoodsList.size()}">
								<hr style=" height:2px;border:none;border-top:2px dotted #cfdbe2;" />
							</c:if>

						</c:forEach>
					</div>
				</div>
				<hr/>
			</div>

			<div class="panel panel-default">
				<div class="panel-heading">订单技师信息</div>
				<!-- START table-responsive-->
				<div class="table-grid table-grid-desktop"  >
					<div class="col">
						<hr style=" height:2px;border:none;border-top:2px dotted #cfdbe2;" />
						<c:forEach items="${order.orderWorkerList}" var="orderWorker" varStatus="i">
							<div class="row" >
								<div class="col-md-4">
									<div class="form-group">
										<label class="col-sm-4 control-label">名称:</label>
										<div class="col-sm-8">
											<label class="control-label">
													${orderWorker.worker.name      }
											</label>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="col-sm-4 control-label">联系方式:</label>
										<div class="col-sm-8">
											<label class="control-label">${orderWorker.worker.member.mobile}</label>
										</div>
									</div>
								</div>
								<%--<div class="col-md-4">--%>
									<%--<div class="form-group">--%>
										<%--<label class="col-sm-4 control-label">分类:</label>--%>
										<%--<div class="col-sm-8">--%>
											<%--<label class="control-label">--%>
													<%--${orderWorker.worker.category.name}--%>
											<%--</label>--%>
										<%--</div>--%>
									<%--</div>--%>
								<%--</div>--%>
							</div>
							<div class="row" >
								<div class="col-md-4">
									<div class="form-group">
										<label class="col-sm-4 control-label">身份:</label>
										<div class="col-sm-8">
											<label class="control-label">
													<c:if test="${orderWorker.worker.type  eq 1}">内部技师</c:if>
													<c:if test="${orderWorker.worker.type  eq 2}">外部技师</c:if>
											</label>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="col-sm-4 control-label">工作时间:</label>
										<div class="col-sm-8">
											<label class="control-label">${orderWorker.worker.workAge}</label>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="col-sm-4 control-label">技师状态:</label>
										<div class="col-sm-8">
											<label class="control-label">
												<c:forEach items="${orderWorkerStatuses}" var="status">
													<c:if test="${orderWorker.status eq status.value}">
														${status.label}
													</c:if>
												</c:forEach>
											</label>
										</div>
									</div>
								</div>
							</div>
							<c:if test="${i.count ne order.orderWorkerList.size()}">
								<hr style=" height:2px;border:none;border-top:2px dotted #cfdbe2;" />
							</c:if>
						</c:forEach>
					</div>
				</div>
				<hr/>
			</div>

			<%--<c:if test="${workerId ne 0}">--%>
			<%--&lt;%&ndash;指派技师  信息&ndash;%&gt;--%>
			<%--<div class="panel panel-default">--%>
				<%--<div class="panel-heading">指派技师</div>--%>
				<%--<div class="table-grid table-grid-desktop"  >--%>
					<%--<div class="form-group">--%>
						<%--<label class="col-md-2 control-label">所有技师:</label>--%>
						<%--<div class="col-md-8">--%>
							<%--<select  name="workerId" id="worker" class="form-control select1" data-select="${workerId}" ></select>--%>
						<%--</div>--%>
						<%--<div class="col-md-2">--%>
							<%--<button  class="btn btn-submit btn-sm btn-primary">确定</button>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--</br>--%>
			<%--</div>--%>
			<%--</c:if>--%>

        </div>
    </section>
    <c:import url="/WEB-INF/layouts/content_footer.jsp" />
</div>

<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>
<script src="${ctx}/static/js/order/shop/show.js"></script>
</body>
</html>