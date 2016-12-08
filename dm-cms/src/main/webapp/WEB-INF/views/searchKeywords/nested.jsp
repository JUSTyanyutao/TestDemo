<%--
  Created by IntelliJ IDEA.
  User: whan
  Date: 10/9/15
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<%@ page import="com.mxep.model.search.SearchKeywords" %>
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
            <th>搜索关键字</th>
            <th>搜索次数</th>
            <th>显示/隐藏</th>
            <th>创建时间</th>
            <th>最后更新时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="6">您还未添加过关键字！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="searchrKeywords" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${searchrKeywords.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${searchrKeywords.id}</td>
                <td>${searchrKeywords.keywords}</td>
                <td>${searchrKeywords.count}</td>
                <td>
                    <c:if test="${searchrKeywords.isDisplay == 1}">显示</c:if>
                    <c:if test="${searchrKeywords.isDisplay == 0}">隐藏</c:if>
                </td>
                <td><fmt:formatDate value="${searchrKeywords.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${searchrKeywords.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
