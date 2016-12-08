<%@ page import="com.fanfou.web.common.bo.Constant" %>
<%--
  Created by IntelliJ IDEA.
  User: whan
  Date: 10/9/15
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
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
            <th>评论人联系方式</th>
            <th>评论内容</th>
            <th>商品名称</th>
            <th>评论分数</th>
            <th>子评论</th>
            <th>图片</th>
            <th>处理状态</th>
            <th>处理结果</th>
            <th>创建时间</th>
            <th>处理时间</th>
            
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.answer}">
            <tr>
                <td colspan="11" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.answer}" var="comment" varStatus="status">
        <input type="hidden" id="image_url" name="image_url" value="${image_url}">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${comment.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${comment.member.mobile}</td>
                <td class="messageTd"><a href="#" data-toggle="modal" data-target="#myModal" class="messageContent">${comment.answer}</a></td>
                <td>${comment.goods.name}</td>
                <td>${comment.score}</td>
                <td>
                    <c:choose>
                        <c:when test="${empty comment.parent}">
                            <a href="${ctx}/comments?parentId=${comment.id}">查看子评论</a>
                        </c:when>
                        <c:otherwise>
                                                                                  无
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${!empty comment.pics}">
                           <a href="#" class="show-map" onclick="showPics('${comment.pics}')">查看图片</a>
                        </c:when>
                        <c:otherwise>
                                                                                  无
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${comment.status eq 1 ? "未受理" : "<span class='text-success'>已受理</span>"}</td>
                <td>${comment.result}</td>
                <td><fmt:formatDate value="${comment.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${comment.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            	
            </tr>
        </c:forEach>
        </tbody>
    </table>
	
    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>

