<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="城市模版管理 - 达膜" />
    </c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
<style type="text/css">
    .uploadify-button {
        border: none;
        padding: 0;
    }
    .has-feedback .form-control{
    	padding-right:0px;
    }
</style> 
</head>
<c:set var="mainTitle" value="城市模版管理" />
<c:set var="subTitle" value="${empty cityTemplate ? '添加城市模版' : '保存城市模版'}" />


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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/city/template/save">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${cityTemplate.id}">
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">城市模版名称:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="name" id="name" placeholder="请输入城市模版名称"
	                                                class="form-control" value="${cityTemplate.name}">
	                                         </div>
	                                     </div>

										<div class="form-group">
											<label class="col-md-2 control-label">城市:</label>
											<div class="col-md-8">
												<select type="text" name="cities" id="city"
													   class="form-control" data-select="${cityTemplate.cities}"></select>
											</div>
										</div>

										<div class="form-group" >
											<label class="col-md-2 control-label">状态:</label>
											<div class="col-md-7">
												<input type="radio"
												<c:if test="${cityTemplate.status eq 1}">
													   checked = "true"
												</c:if>
													   value="1"  name="status">&nbsp;启用&nbsp;&nbsp;&nbsp;
												<input type="radio"
														<c:if test="${cityTemplate.status eq 0}">
															checked = "true"
														</c:if>
													   name="status" value="0" />&nbsp;禁用
											</div>
										</div>
	                                 </div>
	                            </div>
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty cityTemplate ? "添加" : "保存"}中...">
                                            ${empty cityTemplate ? "添 加" : "保存"}
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


    </section>
    <c:import url="/WEB-INF/layouts/content_footer.jsp" />
</div>
<c:import url="/WEB-INF/layouts/footer.jsp" />
<c:import url="/WEB-INF/layouts/simditor.jsp" />


<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>
<script src="${ctx}/static/libs/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/cityTemplate/edit.js"></script>
</body>
</html>