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
            <th>技师名称</th>
            <%--<th>技师电话</th>--%>
            <th>服务日期</th>
            <th>早上的单数</th>
            <th>下午的单数</th>
            <%--<th>状态</th>--%>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="6">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="workerStatistics" varStatus="status">
            <tr>
                <td>${workerStatistics.worker.name}</td>
                <td>${workerStatistics.serviceDate}</td>
                <td>${workerStatistics.amOrderNum}</td>
                <td>${workerStatistics.pmOrderNum}</td>
                <%--<td>${workerStatistics.worker.name}</td>--%>
                <%--<td>--%>
                    <%--<c:if test="${workerStatistics.status eq 1}"><span class='text-success'>启用</span></c:if>--%>
                    <%--<c:if test="${workerStatistics.status eq 0}">禁用</c:if>--%>
                <%--</td>--%>
                <td><fmt:formatDate value="${workerStatistics.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>

