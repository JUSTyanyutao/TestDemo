<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="车 品牌" />
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
<c:set var="mainTitle" value="车 品牌 管理" />
<c:set var="subTitle" value="${empty carBrand ? '添加品牌' : '保存品牌'}" />


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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/carBrand/save">
	                            <div class="row">
	                                <div class="col-md-12">
										<input type="hidden" id="id" name="id" value="${carBrand.id}">
										
										<div class="form-group">
											<label class="col-md-2 control-label">品牌名称:</label>
											<div class="col-md-8">
												<input type="text" name="name" id="name" placeholder="请输入品牌名称"
	                                                class="form-control" value="${carBrand.name}">
	                                         </div>
										</div>

										<div class="form-group" >
											<label class="col-md-2 control-label">品牌首字母:</label>
											<div class="col-md-7">
												<input type="text" id="firstChar" name="firstChar" class="form-control" value="${carBrand.firstChar}" />
											</div>
										</div>

										<c:if test="${!empty carBrand}">
										<div class="form-group" >
											<label class="col-md-2 control-label">状态:</label>
											<div class="col-md-7">
												<input type="radio"
												<c:if test="${carBrand.status eq 1}">
													   checked = "true"
												</c:if>
													   value="1"  name="status">&nbsp;启用&nbsp;&nbsp;&nbsp;
												<input type="radio"
														<c:if test="${worker.status eq 0}">
															checked = "true"
														</c:if>
													   name="status" value="0" />&nbsp;禁用
											</div>
										</div>
										</c:if>

										<c:if test="${empty carBrand}">
											<div class="form-group" >
												<label class="col-md-2 control-label">状态:</label>
												<div class="col-md-7">
													<input type="radio" checked = "true" value="1"  name="status">&nbsp;启用&nbsp;&nbsp;&nbsp;
													<input type="radio" name="status" value="0" />&nbsp;禁用
												</div>
											</div>
										</c:if>

										<div class="form-group">
											<label class="col-md-2 control-label">图标(180*180):</label>
											<input type="hidden" name="logoUrl"  id="logoUrl" value = "${carBrand.logoUrl}" >
											<div class="col-md-8">
												<div id="picsFileList">
													<c:if test="${not empty carBrand.logoUrl}">
														<div style="margin-bottom:10px">
															<img  height="200" width= "200" class="filename" target="_blank" src=${carBrand.logoUrl}>
															<a class="btn red del_file" data-filename = "${carBrand.logoUrl}">删除</a>
														</div>
													</c:if>
												</div>
												<input id="pic_upload" name="pic_upload" type="file" multiple="true">
											</div>
										</div>

	                                 </div>
	                            </div>
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty carBrand ? "添加" : "保存"}中...">
                                            ${empty carBrand ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/carBrand/edit.js"></script>
</body>
</html>