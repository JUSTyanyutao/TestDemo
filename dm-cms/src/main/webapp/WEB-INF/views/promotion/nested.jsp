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
            <th>活动名称</th>
            <th>活动类型</th>
            <th>奖品类型</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>排序</th>
            <th>状态</th>
            <th>创建时间</th>
            <th>更新时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.answer}">
            <tr>
                <td colspan="10">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.answer}" var="promotion" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${promotion.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${promotion.name}</td>
                <td>${promotion.promotionTypeName}</td>
                <td>${promotion.rewardTypeName}</td>
                <td><fmt:formatDate value="${promotion.startTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${promotion.endTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>${promotion.sort}</td>
                <td>${promotion.status eq 0 ? '下架' : "<span class='text-success'>上架</span>" }</td>
                <td><fmt:formatDate value="${promotion.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatDate value="${promotion.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
