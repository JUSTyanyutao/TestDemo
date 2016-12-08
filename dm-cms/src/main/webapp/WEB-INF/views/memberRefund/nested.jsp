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
            <th>手机号</th>
            <th>用户名称</th>
            <th>金额</th>
            <th>处理状态</th>
            <th>处理结果</th>
            <th>创建时间</th>
            <th>处理时间</th>
            
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.answer}">
            <tr>
                <td colspan="11" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.answer}" var="memberRefund" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${memberRefund.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${memberRefund.member.mobile}</td>
                <td>${memberRefund.member.nickname}</td>
                <td>${memberRefund.money}</td>
                <td>${memberRefund.status eq 1 ? "未受理" : "<span class='text-success'>已受理</span>"}</td>
                <td>${memberRefund.remark}</td>
                <td><fmt:formatDate value="${memberRefund.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${memberRefund.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            	
            </tr>
        </c:forEach>
        </tbody>
    </table>
	
    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>

