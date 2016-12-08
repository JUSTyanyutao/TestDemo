<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="短信推送管理 - 达膜贴膜" />
    </c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
</head>
<c:set var="mainTitle" value="短信推送管理" />
<c:set var="subTitle" value="${empty sms ? '添加短信推送' : '保存短信推送'}" />


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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/sms/save">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${sms.id}">
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-sms">推送模版:</label>
	                                         <div class="col-md-8">
	                                         	<select  name="smsTemplateId" id="smsTemplateId"
	                                                class="form-control" data-select="${sms.smsTemplateId}"></select>
	                                         </div>
	                                     </div>

                                        <div class="form-group">
                                            <label class="col-md-2 control-sms">推送模版:</label>
                                            <div class="col-md-8">
                                                <select  name="memberId" id="memberId"
                                                        class="form-control" data-select="${sms.memberId}"></select>
                                            </div>
                                        </div>
	                                     

	                            </div>
	                            
	                            <a class="btn btn-sm btn-primary " href="javascript:history.go(-1);" role="button">返回</a>
	                            <button type="submit" class="btn btn-submit btn-sm btn-primary "
                                                data-loading-text="${empty sms ? "添加" : "保存"}中...">
                                            ${empty sms ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/sms/edit.js"></script>
</html>