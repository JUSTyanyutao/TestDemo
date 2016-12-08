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
            <th>ID</th>
            <th>名称</th>
            <th>分类</th>
            <th>价格比例</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="5">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="carPart" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${carPart.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${carPart.id}</td>
                <td>${carPart.name}</td>
                <td>${carPart.category.name}</td>
                <td>${carPart.priceRatio}</td>
                <td>
                    <c:if test="${carPart.status eq 1}"><span class="text-success">启用</span></c:if>
                    <c:if test="${carPart.status eq 0}">禁用</c:if>
                </td>
                <%--<td><fmt:formatDate value="${carPart.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
