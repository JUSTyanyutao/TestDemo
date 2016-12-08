<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mxep.model.enums.EnumWorkage" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>

<c:set value="<%=EnumWorkage.values()%>" var="workAges"></c:set>
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
            <th>会员</th>
            <th>商品</th>
            <th>评论内容</th>
            <th>推荐</th>
            <th>审核状态</th>
            <th>评论时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="7">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="goodsShow" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${goodsShow.id}" >
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td><a href="${ctx}/goods/show/edit/${goodsShow.id}">${goodsShow.member.mobile}</a></td>
                <td>${goodsShow.goods.name}</td>
                <td class="messageTd"><a href="#" data-toggle="modal" data-target="#myModal" class="messageContent">${goodsShow.comment}</a></td>
                <td>
                    <c:if test="${goodsShow.recommend == 1}">置顶</c:if>
                    <c:if test="${goodsShow.recommend == 0}">普通</c:if>
                </td>
                <td>
                    <c:if test="${goodsShow.status == 0}">待审核</c:if>
                    <c:if test="${goodsShow.status == 1}">已审核</c:if>
                    <c:if test="${goodsShow.status == 2}">反驳</c:if>
                </td>
                <td><fmt:formatDate value="${goodsShow.createTime}" pattern="yyyy-MM-dd HH:mm"/> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
