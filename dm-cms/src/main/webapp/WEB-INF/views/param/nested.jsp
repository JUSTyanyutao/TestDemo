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
            <th>参数名称</th>
            <th>参数变量</th>
            <th>参数值</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="4" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="sysParam" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${sysParam.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${sysParam.paramDesc}</td>
                <td>${sysParam.key}</td>
                <td>${sysParam.value}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
	
    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>

