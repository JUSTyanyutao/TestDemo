<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.fanfou.model.member.Agreement" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="协议管理 - 怎么吃" />
    </c:import>
    <link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
    <link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
    
    
</head>

<c:set var="mainTitle" value="协议管理" />
<c:set var="subTitle" value="${empty agreement.id ? '添加协议' : '编辑协议'}" />
<!--协议类型  -->
<c:set var="types" value="<%=Agreement.AgreementType.values()%>" />
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
                            <div class="row">
                                <div class="col-md-12">
                                    <form id="form-edit" role="form" method="POST" class="form-horizontal"  action="${ctx}/agreement/saveAgreement">
                                        <input type="hidden" name="id" value="${agreement.id}">
                                        
                                        <div class="form-group">
	                                         <label class="col-md-2 control-label">协议类型:</label>
	                                         <div class="col-md-8">
	                                         	<select id="type" name="type" class="form-control" tabindex="1" data-select="${agreement.type}">
													<c:forEach items="${types}" var="type">
														<option value="${type.value }">${type.name}</option>
													</c:forEach>
												</select>
	                                         </div>
	                                     </div>
                                        
                                        
                                        <div class="form-group">
	                                         <label class="col-md-2 control-label">协议内容:</label>
	                                         <div class="col-md-8">
	                                         	<textarea rows="3" name="answer" id="agreement_content" placeholder="请编辑协议内容"
	                          								class="form-control" >${agreement.answer}</textarea>
	                                         </div>
	                                     </div>
                                        
                                       	<div class="form-group">
	                                            <label class="col-sm-2 control-label">排序:</label>
	                                            <div class="col-sm-9">
		                                            <input id="sort" name="sort" data-ui-slider="" type="text" value="20" data-slider-min="1"
		                                                   data-slider-max="20" data-slider-step="1" data-slider-value="${agreement.sort}"
		                                                   data-slider-orientation="horizontal" class="slider slider-horizontal"
		                                                   data="value: '20'" style="display: none;">
	                                            </div>
	                                     </div>
	                                     
	                                     <c:if test="${!empty agreement.id}">
	                                     		<div class="form-group">
			                                         <label class="col-md-2 control-label">创建时间:</label>
			                                        	<div class="col-md-8">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${agreement.createTime}'/>" 
			                                        			name="createTime" id="createTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>
			                                     
			                                     <div class="form-group">
			                                         <label class="col-md-2 control-label">更新时间:</label>
			                                        	<div class="col-md-8">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${agreement.updateTime}'/>" 
			                                        			name="updateTime" id="updateTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>
	                                     </c:if>
	                                     
	                                     
		                                 
                                        
                                        <a href="${ctx}/agreement" class="btn btn-sm btn-back btn-default">返回</a>

                                        <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty agreement.id ? "添加" : "编辑"}中...">
                                            ${empty agreement.id ? "添 加" : "编 辑"}
                                        </button>
                                        
                                        <button id="saveAgain" type="submit" class="btn btn-submit btn-sm btn-primary" data-loading-text="保存中...">
                                            保存并添加下一条
                                        </button>
                                        
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <c:import url="/WEB-INF/layouts/content_footer.jsp" />
</div>

<c:import url="/WEB-INF/layouts/footer.jsp" />
<c:import url="/WEB-INF/layouts/simditor.jsp" />
<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>
<script src="${ctx}/static/js/agreement/edit.js"></script>
<script type="text/javascript">

var editor = new Editor("#agreement_content");

</script>
	

</body>

</html>