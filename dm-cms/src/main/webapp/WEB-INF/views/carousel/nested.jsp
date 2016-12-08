<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<%--<%@ page import="com.mxep.model.log.LogMemberBalance" %>--%>
<%@ page import="com.mxep.model.common.Carousel" %>
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
            <th>缩略图</th>
            <th>显示/隐藏</th>
            <th>投放平台</th>
            <th>链接地址</th>
            <th>创建时间</th>
            <th>最后更新时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="6">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="carousel" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${carousel.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${carousel.id}</td>
                <td>${carousel.name}</td>
                <td>
                    <c:if test="${not empty carousel.pic}">
                        <img  height="50" width= "50"  target="_blank" src=${carousel.pic}>
                    </c:if>
                </td>
                <td>
                    <c:if test="${carousel.isDisplay == 0}">隐藏</c:if>
                    <c:if test="${carousel.isDisplay == 1}">显示</c:if>
                </td>
                <td>
                    <c:if test="${carousel.platform == 1}">微信</c:if>
                    <c:if test="${carousel.platform == 2}">苹果</c:if>
                    <c:if test="${carousel.platform == 3}">微信,苹果</c:if>
                    <c:if test="${carousel.platform == 4}">安卓</c:if>
                    <c:if test="${carousel.platform == 5}">微信,安卓</c:if>
                    <c:if test="${carousel.platform == 6}">苹果,安卓</c:if>
                    <c:if test="${carousel.platform == 7}">全部</c:if>
                </td>
                <td>${carousel.link}</td>
                <td><fmt:formatDate value="${carousel.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${carousel.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
