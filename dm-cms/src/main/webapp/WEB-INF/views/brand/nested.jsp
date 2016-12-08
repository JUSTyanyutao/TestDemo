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
            <th>品牌名称</th>
            <th>置顶</th>
            <th>状态</th>
            <th>缩略图</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="6">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="brand" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${brand.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${brand.name}</td>
                <td>
                    <c:if test="${brand.recommendFlag eq 1}"><span class='text-success'>置顶</span></c:if>
                    <c:if test="${brand.recommendFlag eq 0}">普通</c:if>
                </td>
                <td>
                    <c:if test="${brand.status eq 1}"><span class='text-success'>上架</span></c:if>
                    <c:if test="${brand.status eq 0}">下架</c:if>
                </td>
                <td>
	                <c:if test="${not empty brand.pic}">
	                  <img  height="50" width= "50"  target="_blank" src=${brand.pic}>
	                </c:if>  
	                <c:if test="${empty brand.pic}">
	                  -
	                </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>

