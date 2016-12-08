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
            <th>收货人</th>
            <th>手机号码</th>
            <th>省</th>
            <th>市</th>
            <th>区</th>
            <th>街道地址</th>
            <th>默认</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="7" class="text-center">没有查询到地址信息！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="item" varStatus="status">
            <tr>
                <%--<td>--%>
                    <%--<div class="checkbox c-checkbox">--%>
                        <%--<label>--%>
                            <%--<input type="checkbox" class="checkbox-item" name="id" value="${item.id}" >--%>
                            <%--<span class="fa fa-check"></span>--%>
                        <%--</label>--%>
                    <%--</div>--%>
                <%--</td>--%>
                <td>${item.consignee}</td>
                <td>${item.mobile}</td>
                <td>${item.province.name}</td>
                <td>${item.city.name}</td>
                <td>${item.district.name}</td>
                <td>${item.street}</td>
                <td>
                    <c:choose>
                        <c:when test="${item.isDefault eq 1}">
                            <span class="text-success">是</span>
                        </c:when>
                        <c:otherwise>
                            否
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
