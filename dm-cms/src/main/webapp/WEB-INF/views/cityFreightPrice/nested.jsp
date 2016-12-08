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
            <th>省</th>
            <th>市</th>
            <th>价格</th>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="5">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="cityFreightPrice" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${cityFreightPrice.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${cityFreightPrice.province.name}</td>
                <td>${cityFreightPrice.city.name}</td>
                <td>${cityFreightPrice.freightPrice}</td>
                <td><fmt:formatDate value="${cityFreightPrice.createTime}"  pattern="yyyy-MM-dd HH:mm"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>

