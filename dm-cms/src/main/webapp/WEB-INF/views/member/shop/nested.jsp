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
            <th>
                <div class="checkbox c-checkbox">
                    <label>
                        <input type="checkbox" class="checkbox-global">
                        <span class="fa fa-check"></span>
                    </label>
                </div>
            </th>
            <th>公司名</th>
            <th>联系人</th>
            <th>联系方式</th>
            <th>状态</th>
            <th>每天最大车辆数</th>
            <th>创建时间</th>
            <th>更新时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="7" class="text-center">没有查询到场地信息！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="item" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${item.id}" >
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${item.shop.name}</td>
                <td>${item.contact}</td>
                <td>${item.phone}</td>
                <td>
                    <c:if test="${item.shop.status == 0}">待审核</c:if>
                    <c:if test="${item.shop.status == 1}">已审核</c:if>
                    <c:if test="${item.shop.status == 2}">反驳</c:if>
                </td>
                <td>${item.limitCars}</td>
                <td><fmt:formatDate value="${item.createTime}"  pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${item.updateTime}"  pattern="yyyy-MM-dd HH:mm"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
