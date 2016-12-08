<%--
  Created by IntelliJ IDEA.
  User: whan
  Date: 10/9/15
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mxep.model.enums.EnumSmsStatus" %>
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
            <th>用户</th>
            <th>模版代码</th>
            <%--<th>短信状态</th>--%>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="6">您还未添加短信！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="sms" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${sms.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${sms.id}</td>
                <td>${sms.member.mobile}</td>
                <td>${sms.smsTemplate.code}</td>
                <%--<td >--%>
                    <%--<c:forEach items="<%=EnumSmsStatus.values()%>" var="item">--%>
                        <%--<c:if test="${sms.status eq item.value}">${item.label}</c:if>--%>
                    <%--</c:forEach>--%>
                <%--</td>--%>
                <td><fmt:formatDate value="${sms.createTime}" pattern="yyyy-MM-dd HH:mm"/> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
