<%--
  Created by IntelliJ IDEA.
  User: whan
  Date: 10/9/15
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mxep.model.enums.EnumPaymentType" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
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
            <th>ID</th>
            <th>订单号</th>
            <th>金额</th>
            <th>帐号</th>
            <th>结果</th>
            <th>类型</th>
            <th>支付时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="8">您还未添加短信！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="payment" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${payment.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${payment.id}</td>
                <td>${payment.orderSn}</td>
                <td>${payment.payAmount}</td>
                <td>${payment.payAccount}</td>
                <td>${payment.status}</td>
                <td >
                    <c:forEach items="<%=EnumPaymentType.values()%>" var="item">
                        <c:if test="${payment.payType eq item.value}">${item.label}</c:if>
                    </c:forEach>
                </td>
                <td><fmt:formatDate value="${payment.payTime}" pattern="yyyy-MM-dd HH:mm"/> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
