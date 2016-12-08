<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mxep.model.enums.EnumWorkage" %>
<%@ page import="com.mxep.model.enums.EnumWorkerApplyStatus" %>

<%@include file="/WEB-INF/layouts/tag.jsp" %>

<c:set value="<%=EnumWorkage.values()%>" var="workAges"></c:set>
<c:set value="<%=EnumWorkerApplyStatus.values()%>" var="statuses"></c:set>
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
            <th>手机</th>
            <th>姓名</th>
            <th>身份证</th>
            <th>开户行</th>
            <th>银行卡号</th>
            <th>城市</th>
            <th>经理</th>
            <th>内部/外部</th>
            <th>审核状态</th>
            <th>审核备注</th>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="12">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="worker" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${worker.id}" >
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td><a href="${ctx}/worker/edit/${worker.id}">${worker.member.mobile}</a></td>
                <td>${worker.name}</td>
                <td>${worker.idcard}</td>
                <td>${worker.bankName}</td>
                <td>${worker.bankCardNo}</td>
                <td>${worker.city.name}</td>
                <td>${worker.parentMember.mobile}</td>
                <td>
                    <c:if test="${worker.type == 1}">内部技师</c:if>
                    <c:if test="${worker.type == 2}">外部技师</c:if>
                </td>
                <td>
                    <%--<c:forEach items="${statuses}" var="data">--%>
                        <%--<c:if test="${ worker.applyStatus eq data.value } ">--%>
                            <%--${data.name}/${data.value}/${worker.applyStatus}--%>
                        <%--</c:if>--%>
                    <%--</c:forEach>--%>

                    <c:if test="${worker.applyStatus == 0}">待审核</c:if>
                    <c:if test="${worker.applyStatus == 1}">已审核</c:if>
                    <c:if test="${worker.applyStatus == 2}">反驳</c:if>
                    <c:if test="${worker.applyStatus == 3}">取消资格</c:if>
                </td>
                <td class="messageTd"><a href="#" data-toggle="modal" data-target="#myModal" class="messageContent">${worker.remark}</a></td>
                <td><fmt:formatDate value="${worker.createTime}" pattern="yyyy-MM-dd HH:mm"/> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
