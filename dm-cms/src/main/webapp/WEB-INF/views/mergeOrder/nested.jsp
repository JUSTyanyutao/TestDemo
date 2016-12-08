<%@ page import="com.fanfou.web.common.bo.Constant" %>
<%--
  Created by IntelliJ IDEA.
  User: whan
  Date: 10/9/15
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <!-- <thead>
        <tr>
            <th>
                <div class="checkbox c-checkbox">
                    <label>
                        <input type="checkbox" class="checkbox-global">
                        <span class="fa fa-check"></span>
                    </label>
                </div>
            </th>
            <th>协议类型</th>
            <th>排序值</th>
            <th>创建时间</th>
        </tr>
        </thead> -->
        <tbody>
        <c:if test="${empty mergeOrders}">
            <tr>
                <td colspan="5" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${mergeOrders}" var="mergeOrder" varStatus="status">
            <tr>
            	<table class="table table-hover">
	           		<thead>
				        <tr class="info">
				            <th>
				                <div class="checkbox c-checkbox">
				                    <label>
				                        <input type="checkbox" class="checkbox-item" value="${status.index+1}" >
				                        <span class="fa fa-check"></span>
				                    </label>
				                </div>
				            </th>
				            <th colspan="7">
				            	<span>配送日期:${mergeOrder.deliveryDate}</span>
				            	<span style="margin-left:60px;">配送时间:${mergeOrder.deliverySection}</span>
				            	<span style="margin-left:60px;">站点名称:${mergeOrder.siteStation.stationName}</span>
			            	</th>
				        </tr>
			        </thead>
			        <tbody>
			        	<tr>
			        		<th></th>
			        		<th>订单编号</th>
				            <th>会员帐号</th>
				            <th>订单条码</th>
				            <th>支付金额</th>
				            <th>订单状态</th>
				            <th>派送状态</th>
				            <th>创建日期</th>
			        	</tr>
			        	<c:forEach items="${mergeOrder.orders}" var="order">
			        		<tr>
			        			<td>
			        				<input class="orderId" type="hidden" value="${order.id}">
			        			</td>
				                <td><a href="${ctx}/order/showOrder/${order.id}">${order.orderSn}</a></td>
				                <td>${order.member.mobile}</td>
				                <td>${empty order.barCode ? '暂无' : order.barCode}</td>
				                <td>${order.paidMoney}</td>
				                <td>${order.statusName}</td>
				                <td>${order.deliveryStatusName}</td>
				                <td><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			                </tr>
			        	</c:forEach>
			        </tbody>
			        
            	</table>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%-- <c:import url="/WEB-INF/layouts/pagination.jsp" /> --%>
</div>
