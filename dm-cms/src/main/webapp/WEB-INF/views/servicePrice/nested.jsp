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
            <th>商品名称</th>
            <th>商品分类</th>
            <th>尺寸</th>
            <th>价格</th>
            <th>城市模版</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="5">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="servicePrice" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${servicePrice.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${servicePrice.goods.name} </td>
                <td>${servicePrice.goods.category.name} </td>
                <td>${servicePrice.carSize}</td>
                <td>${servicePrice.price}</td>
                <td>${servicePrice.cityTemplate.name}</td>
                <td>${servicePrice.status eq 0? "禁用":"启用"}</td>
                <td>操作</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>


