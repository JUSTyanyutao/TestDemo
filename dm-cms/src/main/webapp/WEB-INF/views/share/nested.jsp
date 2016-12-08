<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<%@ page import="com.mxep.model.share.Share" %>
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
            <th>竞拍品标题</th>
            <th>商品名称</th>
            <th>会员手机</th>
            <th>描述</th>
            <th>反馈内容</th>
            <th>图片</th>
            <th>审核状态</th>
            <th>创建时间</th>
            <th>最后更新时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="9">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="share" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${share.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${share.auction.title}</td>
                <td>${share.goods.name}</td>
                <td>${share.member.mobile}</td>
                <td class="messageTd"><a href="#" data-toggle="modal" data-target="#myModal" class="messageContent">${share.desc}</a></td>
                <td class="messageTd"><a href="#" data-toggle="modal" data-target="#myModal" class="messageContent">${share.comment}</a></td>
                <td>
                    <c:if test="${not empty share.pic}">
                        <img  height="50" width= "50"  target="_blank" src=${share.pic}>
                    </c:if>
                </td>
                <td >
                    <c:if test="${share.checkStatus == -1}">审核失败</c:if>
                    <c:if test="${share.checkStatus == 0}">待审核</c:if>
                    <c:if test="${share.checkStatus == 1}">审核成功</c:if>
                </td>
                <td><fmt:formatDate value="${share.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${share.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
