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
            <th>ID</th>
            <th>问题类型</th>
            <th>问题</th>
            <th>答复</th>
            <th>创建时间</th>
            <th>最后更新时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="6">您还未添加过QA！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="faq" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${faq.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${faq.id}</td>
                <td >
                    <c:if test="${faq.type == 1}">常见问题</c:if>
                    <c:if test="${faq.type == 2}">积分说明</c:if>
                </td>
                <td class="messageTd"><a href="#" data-toggle="modal" data-target="#myModal" class="messageContent">${faq.question}</a></td>
                <td class="messageTd"><a href="#" data-toggle="modal" data-target="#myModal" class="messageContent">${faq.answer}</a></td>
                <td><fmt:formatDate value="${faq.createTime}" pattern="yyyy-MM-dd HH:mm"/> </td>
                <td><fmt:formatDate value="${faq.updateTime}" pattern="yyyy-MM-dd HH:mm"/> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
