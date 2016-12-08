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
            <th>商品编号</th>
            <th>商品名称</th>
            <th>申请类型</th>
            <th>申请人员</th>
            <th>申请备注</th>
            <th>审批人员</th>
            <th>审批备注</th>
            <th>审批状态</th>
            <th>创建时间</th>
            <th>更新时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.answer}">
            <tr>
                <td colspan="11">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.answer}" var="apply" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${apply.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${apply.goodsSn}</td>
                <td>${apply.name}</td>
                <td>${apply.typeName}</td>
                <td>${apply.presentUser.mobile}</td>
                <td>${apply.presentRemark}</td>
                <td>${apply.approvedUser.mobile}</td>
                <td>${apply.approvedRemark}</td>
                <td>${apply.status eq 1 ? '待审批' : (apply.status eq 2 ? "<span class='text-success'>审批通过</span>" : "<span class='text-danger'>拒绝申请</span>") }</td>
                <td><fmt:formatDate value="${apply.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatDate value="${apply.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
