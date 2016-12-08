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
            <th>优惠券面额</th>
            <th>限制金额</th>
            <th>手机号</th>
            <th>创建时间</th>
            <th>生效时间</th>
            <th>使用时间</th>
            <th>过期时间</th>
            <th>状态</th>
            
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="10" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="coupon" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${coupon.id}" >
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${coupon.id}</td>
                <td>${coupon.denomination}</td>
                <td>${coupon.restrictionMoney}</td>
                <td>${coupon.member.mobile}</td>
                <td><fmt:formatDate value="${coupon.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${coupon.activationTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${coupon.usedTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${coupon.expireTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><c:if test="${coupon.status == -1}">无效</c:if>
                    <c:if test="${coupon.status == -2}">过期</c:if>
                    <c:if test="${coupon.status == 0}">待激活</c:if>
                    <c:if test="${coupon.status == 1}">已激活</c:if>
                    <c:if test="${coupon.status == 2}">已使用</c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
