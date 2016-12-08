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
            <th>公司名称</th>
            <th>场地地址</th>
            <th>联系人</th>
            <th>联系方式</th>
            <th>类型</th>
            <th>状态</th>
            <th>审核备注</th>
            <th>创建时间</th>

        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="9">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="shop" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${shop.id}" >
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td><a href="${ctx}/shop/street/edit/${shop.id}">${shop.name}</a></td>
                <td>${shop.address}</td>
                <td>${shop.contact}</td>
                <td>${shop.phone}</td>
                <td>
                    <c:if test="${shop.type == 1}">4s店</c:if>
                    <c:if test="${shop.type == 2}">街边店铺</c:if>
                </td>
                <td>
                    <c:if test="${shop.status == 0}">待审核</c:if>
                    <c:if test="${shop.status == 1}">已审核</c:if>
                    <c:if test="${shop.status == 2}">反驳</c:if>
                </td>
                <td class="messageTd"><a href="#" data-toggle="modal" data-target="#myModal" class="messageContent">${shop.remark}</a></td>
                <td><fmt:formatDate value="${shop.createTime}" pattern="yyyy-MM-dd HH:mm"/> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
