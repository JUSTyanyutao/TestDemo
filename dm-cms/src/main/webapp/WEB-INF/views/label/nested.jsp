<%@ page import="com.mxep.model.enums.EnumLabelType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>

<!-- 标签类型 -->
<c:set var="labelTypes" value="<%=EnumLabelType.values()%>" />

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
            <th>名称</th>
            <th>值</th>
            <th>类型</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="5">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="label" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${label.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${label.id}</td>
                <td>${label.name}</td>
                <td>${label.value}</td>
                <td>
                    <c:forEach items="${labelTypes}" var="type">
                        <c:if test="${type.value eq label.type}">
                            ${type.name}
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
