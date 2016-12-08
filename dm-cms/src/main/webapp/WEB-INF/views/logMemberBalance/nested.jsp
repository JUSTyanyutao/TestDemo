<%--
  Created by IntelliJ IDEA.
  User: whan
  Date: 10/9/15
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mxep.model.log.LogMemberBalance" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <%--<th>--%>
                <%--<div class="checkbox c-checkbox">--%>
                    <%--<label>--%>
                        <%--<input type="checkbox" class="checkbox-global">--%>
                        <%--<span class="fa fa-check"></span>--%>
                    <%--</label>--%>
                <%--</div>--%>
            <%--</th>--%>
            <th>ID</th>
            <th>创建时间</th>
            <%--<th>状态</th>--%>
            <th>更新时间</th>
            <th>账户余额(梦想币)</th>
            <th>描述</th>
            <th>用户手机号</th>
            <th>消费(梦想币)</th>
            <th>订单ID</th>
            <th>备注</th>
            <th>源头</th>
            <th>收入支出类型</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="11">无余额记录！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="logMemberBalance" varStatus="status">
            <tr>
                <%--<td>--%>
                    <%--<div class="checkbox c-checkbox">--%>
                        <%--<label>--%>
                            <%--<input type="checkbox" class="checkbox-item" name="id" value="${logMemberPoints.id}">--%>
                            <%--<span class="fa fa-check"></span>--%>
                        <%--</label>--%>
                    <%--</div>--%>
                <%--</td>--%>
                <td>${logMemberBalance.id}</td>
                <td><fmt:formatDate value="${logMemberBalance.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                <%--<td>${logMemberBalance.flag}</td>--%>
                <td><fmt:formatDate value="${logMemberBalance.updateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                <td>${logMemberBalance.balance}</td>
                <td>${logMemberBalance.remark}</td>
                <td>${logMemberBalance.member.mobile}</td>
                <td>${logMemberBalance.money}</td>
                <td>${logMemberBalance.orderId}</td>
                <td>${logMemberBalance.remark}</td>
                <%--<td>${logMemberBalance.source}</td>--%>
                <td>
                    <c:choose>
                        <c:when test="${logMemberBalance.source eq 1}">充值</c:when>
                        <c:when test="${logMemberBalance.source eq 2}">消费</c:when>
                        <c:when test="${logMemberBalance.source eq 3}">手工修改</c:when>
                        <c:when test="${logMemberBalance.source eq 4}">积分兑换</c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </td>
                <%--<td>${logMemberBalance.type}</td>--%>
                <td>
                <c:choose>
                    <c:when test="${logMemberBalance.type eq 1}">收入</c:when>
                    <c:when test="${logMemberBalance.type eq 2}">支出</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
