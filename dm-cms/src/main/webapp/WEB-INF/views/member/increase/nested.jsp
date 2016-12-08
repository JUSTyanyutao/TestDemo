<%--
  Created by IntelliJ IDEA.
  member: whan
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
            <%--<th>--%>
                <%--<div class="checkbox c-checkbox">--%>
                    <%--<label>--%>
                        <%--<input type="checkbox" class="checkbox-global">--%>
                        <%--<span class="fa fa-check"></span>--%>
                    <%--</label>--%>
                <%--</div>--%>
            <%--</th>--%>
            <th>时间</th>
            <th>增长人数</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="2" class="text-center">没有查询到结果！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="member" varStatus="status">
            <tr>
                <%--<td>--%>
                    <%--<div class="checkbox c-checkbox">--%>
                        <%--<label>--%>
                            <%--<input type="checkbox" class="checkbox-item" name="id" value="${order.id}">--%>
                            <%--<span class="fa fa-check"></span>--%>
                        <%--</label>--%>
                    <%--</div>--%>
                <%--</td>--%>
                <td><c:out value="${member.day1}" default="-" /></td>
                <td><c:out value="${member.num}" default="-" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
<button type="button" class="btn btn-success pull-right">总人数:${total}</button>