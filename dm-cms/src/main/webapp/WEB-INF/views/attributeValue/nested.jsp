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
            <th>特性值</th>
            <th>特性类型</th>
            <%--<th>标注</th>--%>
            <th>状态</th>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="6">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="attributeValue" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${attributeValue.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${attributeValue.attrValue}</td>
                <td>${attributeValue.attribute.name}</td>
                <td>
                    <c:if test="${attributeValue.status eq 1}"><span class='text-success'>启用</span></c:if>
                    <c:if test="${attributeValue.status eq 0}">禁用</c:if>
                </td>
                <td><fmt:formatDate value="${attributeValue.createTime}"  pattern="yyyy-MM-dd HH:mm"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>

