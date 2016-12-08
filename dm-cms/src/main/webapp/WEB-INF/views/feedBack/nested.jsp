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
            <th>反馈人联系方式</th>
            <th>反馈内容</th>
            <th>处理状态</th>
            <th>处理结果</th>
            <th>创建时间</th>
            <th>处理时间</th>
            
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.answer}">
            <tr>
                <td colspan="8" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.answer}" var="feedback" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${feedback.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <%-- <td>${ empty feedback.member.profile ? feedback.member.nickname : feedback.member.profile.realName}</td> --%>
                <td>${feedback.member.mobile}</td>
                <td class="messageTd"><a href="#" data-toggle="modal" data-target="#myModal" class="messageContent">${feedback.answer}</a></td>
                <td>${feedback.status eq 1 ? "待处理" : "<span class='text-success'>已受理</span>"}</td>
                <td>${feedback.result}</td>
                <td><fmt:formatDate value="${feedback.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${feedback.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            	
            </tr>
        </c:forEach>
        </tbody>
    </table>
	
    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>

