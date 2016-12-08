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
            <th>ID</th>
            <th>交易号</th>
            <th>获得金额</th>
            <th>充值金额</th>
            <th>用户手机号</th>
            <th>时间</th>
            <th>充值来源</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="6">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="recharge" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${recharge.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${recharge.id}</td>
                <td>${recharge.tradeNo}</td>
                <td>${recharge.money}</td>
                <td>${recharge.price}</td>
                <td>${recharge.member.mobile}</td>
                <td><fmt:formatDate value="${recharge.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
                <td>
                	<%-- <c:if test="${recharge.onlinePayId == 2}">支付宝</c:if>
				    <c:if test="${recharge.onlinePayId == 3}">微信支付</c:if> --%>
				    ${ recharge.sourceName}
				</td>  
                <td><c:if test="${recharge.status == 1}">等待支付</c:if>
				    <c:if test="${recharge.status == 2}"><span style="color: blue;">支付成功</span></c:if>
				    <c:if test="${recharge.status == 3}"><span style="color: red;">支付失败</span></c:if>
				</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
