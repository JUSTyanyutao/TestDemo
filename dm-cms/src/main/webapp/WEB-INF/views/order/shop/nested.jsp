
<%--
  Created by IntelliJ IDEA.
  User: whan
  Date: 10/9/15
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mxep.model.enums.EnumOrderType" %>
<%@ page import="com.mxep.model.enums.EnumServiceOrderStatus" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>

<!--订单类型-->
<c:set var="orderTypes" value="<%=EnumOrderType.values()%>" />
<!--订单状态-->
<c:set var="orderStatuses" value="<%=EnumServiceOrderStatus.values()%>" />
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>
                <div class="checkbox c-checkbox">
                    <label>
                        <input type="checkbox" class="checkbox-global">
                        <span class="fa fa-check"></span>
                    </label>
                </div>
            </th>
            <th>序号</th>
            <th>订单编号</th>
            <%--<th>商品名称</th>--%>
            <th>订单金额</th>
            <th>场地</th>
            <th>服务时间</th>
            <th>城市</th>
            <th>服务分类</th>
            <%--<th>优惠券金额</th>--%>
            <th>会员帐号</th>
            <th>会员名称</th>
            <th>订单状态</th>
            <th>订单平台</th>
            <th>创建日期</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="10" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="order" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${order.id}" >
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${status.index+1 }</td>
                <td><a href="${ctx}/order/showShopOrder/${order.id}">${order.orderSn}</a></td>
                    <%--<td>${order.goodsName}</td>--%>
                <td>${order.payPrice}</td>
                <td>${order.shop.name}</td>
                <td>${order.serviceDate}/${order.serviceTime}</td>
                <td>${order.city.name}</td>
                <td>${order.category.name}</td>
                <td>${order.member.mobile}</td>
                <td>${order.member.profile.nickname}</td>
                <td>
                    <c:forEach items="${orderStatuses}" var="item">
                        <c:if test="${order.status == item.value}">
                            ${item.label}
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:if test="${order.platType eq 1}">安卓</c:if>
                    <c:if test="${order.platType eq 2}">IOS</c:if>
                    <c:if test="${order.platType eq 3}">微信</c:if>
                </td>
                <td><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <%-- <td><a href="#" data-toggle="modal" data-id="${order.id}" data-sequence=""  class="btn btn-success package">打包</a></td> --%>
            </tr>
        </c:forEach>


        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
<script type="text/javascript">
 $(".package").each(function(i){
	$(this).attr("data-sequence",i+1);
});  
</script>
