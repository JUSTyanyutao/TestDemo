<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="短信模版管理 - 达膜贴膜" />
    </c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
</head>
<c:set var="mainTitle" value="短信模版管理" />
<c:set var="subTitle" value="${empty smsTemplate ? '添加短信模版' : '保存短信模版'}" />


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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/sms/template/save">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${smsTemplate.id}">
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-smsTemplate">模版代码:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="code" id="code" placeholder="请输入短信模版代码"
	                                                class="form-control" value="${smsTemplate.code}">
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-smsTemplate">短信模版内容:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="text" name="content" id="content" placeholder="请输入短信模版内容"
	                                                class="form-control" value="${smsTemplate.content}">
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-smsTemplate">短信模版描述:</label>
	                                        	<div class="col-md-8">
													<input type="text" name="description" id="description" placeholder="请输入短信模版描述"
														   class="form-control" value="${smsTemplate.description}">
	                                        	</div>
	                                     </div>
	                                 </div>
	                            </div>
	                            
	                            <a class="btn btn-sm btn-primary " href="javascript:history.go(-1);" role="button">返回</a>
	                            <button type="submit" class="btn btn-submit btn-sm btn-primary "
                                                data-loading-text="${empty smsTemplate ? "添加" : "保存"}中...">
                                            ${empty smsTemplate ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/sms/template/edit.js"></script>
</html>