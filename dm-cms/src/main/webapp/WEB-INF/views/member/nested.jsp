<%--
  Created by IntelliJ IDEA.
  User: whan
  Date: 10/9/15
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<%@page import="com.mxep.model.enums.EnumMembeRole" %>
<%@page import="com.mxep.model.enums.EnumPlatform" %>
<%@ page import="com.mxep.model.enums.EnumWorkerApplyStatus" %>
<c:set value="<%=EnumMembeRole.values()%>" var="roles" />
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
            <th>手机号码</th>
            <th>真实姓名</th>
            <th>昵称</th>
            <th>角色</th>
            <th>性别</th>
            <th>余额</th>
            <th>积分</th>
            <th>来源平台</th>
            <th>注册时间</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="10" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="member" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${member.id}" >
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>

                <td>${member.mobile}</td>
                <td>${empty member.profile.realName ? '-' : member.profile.realName}</td>
                <td>${empty member.profile.nickname ? '-' : member.profile.nickname}</td>
                <th>
                    <c:forEach var="role" items="${roles}">
                        <c:if test="${role.value eq member.role}">
                            ${role.name}
                        </c:if>
                    </c:forEach>
                </th>
                <td>${member.profile.gender eq 1 ? '男' : (member.profile.gender eq 2 ? '女' : '未知')}</td>
                <td>${member.balance}</td>
                <td>${member.points}</td>
                <td>
                    <c:forEach items="<%=EnumPlatform.values()%>" var="item">
                        <c:if test="${member.profile.platform eq item.value}">
                            ${item.name}
                        </c:if>
                    </c:forEach>
                </td>
                <td><fmt:formatDate value="${member.registerTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>${member.status eq 1 ? "<span class='text-success'>可用</span>" : "禁用"}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
