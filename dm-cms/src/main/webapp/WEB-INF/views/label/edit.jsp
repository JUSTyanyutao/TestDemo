<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.mxep.model.enums.EnumLabelType" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="标签管理 - 怎么吃" />
    </c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
</head>
<c:set var="mainTitle" value="标签管理" />
<c:set var="subTitle" value="${empty label ? '添加标签' : '保存标签'}" />
<!-- 标签类型 -->
<c:set var="labelTypes" value="<%=EnumLabelType.values()%>" />

<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp" />
    <section>
        <div class="answer-wrapper">
            <h3>${mainTitle}
                <small>${subTitle}</small>
            </h3>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">${subTitle}</div>
                        <div class="panel-body">
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/label">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${label.id}">
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">标签名称:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="name" id="name" placeholder="请输入标签标题"
	                                                class="form-control" value="${label.name}">
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">标签值:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="text" name="value" id="value" placeholder="请输入标签值"
	                                                class="form-control" value="${label.value}">
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">标签类型:</label>
	                                        	<div class="col-md-8">
	                                        		<select id="type" name="type" class="form-control" tabindex="1">
														<c:forEach items="${labelTypes}" var="labelType" varStatus="s">
															<option value="${labelType.value}"
															<c:if test="${label.type eq labelType.value}">
																selected="selected"
															</c:if>
															>${labelType.name}</option>
														</c:forEach>
													</select>
	                                        	</div>
	                                     </div>
	                                 </div>
	                            </div>
	                            
	                            <a class="btn btn-sm btn-primary " href="javascript:history.go(-1);" role="button">返回</a>
	                            <button type="submit" class="btn btn-submit btn-sm btn-primary "
                                                data-loading-text="${empty label ? "添加" : "保存"}中...">
                                            ${empty label ? "添 加" : "保存"}
                                 </button>
                                 <button id="saveAgain" type="submit" class="btn btn-submit btn-sm btn-primary " data-loading-text="保存中...">
                                            保存并添加下一条
                                        </button>
                            </form> 
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
        
		
    </section>
    <c:import url="/WEB-INF/layouts/content_footer.jsp" />
</div>
<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>
<script src="${ctx}/static/libs/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/label/edit.js"></script>
</html>