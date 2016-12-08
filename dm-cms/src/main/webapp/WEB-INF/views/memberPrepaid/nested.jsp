<%@ page import="com.fanfou.web.common.bo.Constant" %>
<%--
  Created by IntelliJ IDEA.
  User: whan
  Date: 10/9/15
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
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
            <th>充值金额</th>
            <th>赠送金额</th>
            <th>描述</th>
            <th>类型</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.answer}">
            <tr>
                <td colspan="10" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.answer}" var="memberPrepaid" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${memberPrepaid.id}" >
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                
                <td>${memberPrepaid.money}</td>
                <td>${memberPrepaid.giveMoney}</td>
                <td>${memberPrepaid.desc}</td>
                <td>${memberPrepaid.typeName}</td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
