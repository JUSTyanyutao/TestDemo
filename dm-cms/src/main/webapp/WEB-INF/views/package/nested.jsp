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
            <th>服务名称</th>
            <th>分类</th>
            <th>服务类型</th>
            <th>标注</th>
            <th>状态</th>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="5">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="item" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${item.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${item.name}</td>
                <td>${item.category.name}</td>
                <td>
                    <c:if test="${item.type eq 1}"><span class='text-success'>普通商品</span></c:if>
                    <c:if test="${item.type eq 2}"><span class='text-success'>服务商品</span></c:if>
                </td>
                <td>${item.remark}</td>
                <td>
                    <c:if test="${item.status eq 1}"><span class='text-success'>启用</span></c:if>
                    <c:if test="${item.status eq 0}">禁用</c:if>
                </td>
                <td><fmt:formatDate value="${item.createTime}"  pattern="yyyy-MM-dd HH:mm"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>

