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
            <th>区域名称</th>
            <th>描述说明</th>
            <th>省</th>
            <th>市</th>
            <th>区</th>
            <th>排序</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.answer}">
            <tr>
                <td colspan="8">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.answer}" var="areaService" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${areaService.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${areaService.regionName}</td>
                <td>${areaService.regionDesc}</td>
                <td>${empty areaService.province ? '-' : areaService.province.name}</td>
                <td>${empty areaService.county ? '-' : areaService.county.name}</td>
                <td>${empty areaService.city ? '-' : areaService.city.name}</td>
                <td>${areaService.sequence}</td>
                <td>${areaService.status eq 0 ? '禁用' : "<span class='text-success'>可用</span>" }</td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
