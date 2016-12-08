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
            <th>标题</th>
            <th>短介绍</th>
            <th>缩略图</th>
            <th>详情图</th>
            <th>类型</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.answer}">
            <tr>
                <td colspan="6">您还未添加过数据！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.answer}" var="activityNutrition" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${activityNutrition.id}">
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${activityNutrition.question}</td>
                <td>${activityNutrition.shortIntro}</td>
                <td>
	                <c:if test="${not empty activityNutrition.pic}">
	                  <img  height="50" width= "50"  target="_blank" src=${image_url }${activityNutrition.pic}>
	                </c:if>  
                </td>
                <td><c:if test="${not empty activityNutrition.detailPic}">
	                  <img  height="50" width= "50"  target="_blank" src=${image_url }${activityNutrition.detailPic}>
	                </c:if>
	            </td>
                <td><c:if test="${activityNutrition.type == 1}">活动</c:if>
				    <c:if test="${activityNutrition.type == 2}">营养知识</c:if>
				    <c:if test="${activityNutrition.type == 3}">如何做菜</c:if>
				</td>
                <td><c:if test="${activityNutrition.status == 0}">关闭</c:if>
				    <c:if test="${activityNutrition.status == 1}">开放</c:if>
				</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
