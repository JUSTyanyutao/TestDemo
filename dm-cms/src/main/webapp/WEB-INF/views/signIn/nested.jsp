<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<%--<%@ page import="com.mxep.model.share.Share" %>--%>
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
            <%--<th>会员</th>--%>
            <th>会员手机</th>
            <th>签到日期</th>
            <th>连续签到次数</th>
            <th>创建时间</th>
            <th>最后更新时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="5">未查询到数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="signIn" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${signIn.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <%--<td>${signIn.member}</td>--%>
                <td>${signIn.member.mobile}</td>
                <td><fmt:formatDate value="${signIn.signInDate}" pattern="yyyy-MM-dd" /></td>
                <td>${signIn.continuedTimes}</td>
                <td><fmt:formatDate value="${signIn.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                <td><fmt:formatDate value="${signIn.updateTime}" pattern="yyyy-MM-dd HH:mm" /></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
