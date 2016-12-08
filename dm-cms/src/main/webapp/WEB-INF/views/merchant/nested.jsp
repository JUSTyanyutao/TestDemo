<%@ page import="com.fanfou.web.common.bo.Constant" %>

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
            <th>手机号码</th>
            <th>联系人</th>
            <th>商户名称</th>
            <th>所属菜场</th>
            <th>摊位</th>
            <th>销售额</th>
            <th>销售量</th>
            <th>收藏数量</th>
            <th>注册时间</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.answer}">
            <tr>
                <td colspan="10" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.answer}" var="merchant" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${merchant.id}" >
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${merchant.mobilephone}</td>
                <td>${merchant.contact}</td>
                <td>${empty merchant.name ? '-' : merchant.name}</td>
                <td>${merchant.market.name}</td>
	            <td>${merchant.boothNo}</td>
	            <td>${merchant.sale}</td>
	            <td>${merchant.salesVolume}</td>
	            <td>${merchant.collectionNum}</td>
                <td><fmt:formatDate value="${merchant.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>${merchant.merchantStatusName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
