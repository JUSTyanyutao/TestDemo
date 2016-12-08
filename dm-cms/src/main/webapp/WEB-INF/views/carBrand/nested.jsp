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
            <th>品牌名称</th>
            <th>品牌首字母</th>
            <th>图标</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="6">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="carBrand" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${carBrand.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td><a href="${ctx}/carSeries/list/${carBrand.id}" >${carBrand.name}</a> </td>
                <td>${carBrand.firstChar}</td>
                <td>
                    <c:if test="${not empty carBrand.logoUrl}">
                        <img  height="50" width= "50"  target="_blank" src=${carBrand.logoUrl}>
                    </c:if>
                    <c:if test="${empty carBrand.logoUrl}">
                        -
                    </c:if>
                </td>
                <td>${carBrand.status eq 0? "禁用":"启用"}</td>
                <td>操作</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>


