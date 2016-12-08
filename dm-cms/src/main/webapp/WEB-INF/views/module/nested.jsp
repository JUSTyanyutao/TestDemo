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
            <th>按钮名称</th>
            <th>分类</th>
            <th>状态</th>
            <th>链接</th>
            <th>图标</th>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="7">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="module" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${module.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${module.name}</td>
                <td>
                    ${module.categoryIds}
                </td>
                <td>
                    <c:if test="${module.status eq 1}"><span class='text-success'>启用</span></c:if>
                    <c:if test="${module.status eq 0}">禁用</c:if>
                </td>
                <td>${module.link}</td>
                <td>
                    <c:if test="${not empty module.icon}">
                        <img  height="50" width= "50"  target="_blank" src=${module.icon}>
                    </c:if>
                    <c:if test="${empty module.icon}">
                        -
                    </c:if>
                </td>
                <td><fmt:formatDate value="${module.createTime}"  pattern="yyyy-MM-dd HH:mm"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>

