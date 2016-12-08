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
            <th>分类名称</th>
            <th>父分类</th>
            <th>排序</th>
            <th>分类状态</th>
            <th>图标</th>
            <%--<th>创建时间</th>--%>
            <%--<th>更新时间</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="6">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="category" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${category.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <td><c:out value="${category.category.name}" default="-" /> </td>
                <td>${category.sort}</td>
                <td>${category.isDisplay eq 0? "禁用":"启用"}</td>
                <td>
                    <c:if test="${not empty category.icon}">
                        <img  height="50" width= "50"  target="_blank" src=${category.icon}>
                    </c:if>
                    <c:if test="${empty category.icon}">
                        -
                    </c:if>
                </td>
                <%--<td><fmt:formatDate value="${category.createTime}"  pattern="yyyy-MM-dd HH:mm"/></td>--%>
                <%--<td><fmt:formatDate value="${category.updateTime}"  pattern="yyyy-MM-dd HH:mm"/></td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>


