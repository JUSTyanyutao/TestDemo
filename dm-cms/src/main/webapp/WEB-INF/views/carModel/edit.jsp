<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="车型管理" />
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
<c:set var="mainTitle" value="车型管理" />
<c:set var="subTitle" value="${empty carModel ? '添加车型' : '保存车型'}" />


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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/carModel/save">
	                            <div class="row">
	                                <div class="col-md-12">

										<c:if test="${empty carModel}">
											<input type="hidden" id="seriesId" name="seriesId" value="${id}">
										</c:if>
										<c:if test="${!empty carModel}">
											<input type="hidden" id="seriesId" name="seriesId" value="${carModel.seriesId}">
										</c:if>
										<input type="hidden" id="id" name="id" value="${carModel.id}">
										
										<div class="form-group">
											<label class="col-md-2 control-label">车型名称:</label>
											<div class="col-md-8">
												<input type="text" name="name" id="name" placeholder="请输入品牌名称"
	                                                class="form-control" value="${carModel.name}">
	                                         </div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">尺寸:</label>
											<div class="col-md-8">
												<select name="carSize" id="carSize" class="form-control" data-select="${carModel.carSize}">

												</select>
											</div>
										</div>


										<c:if test="${!empty carModel}">
											<div class="form-group" >
												<label class="col-md-2 control-label">状态:</label>
												<div class="col-md-7">
													<input type="radio"
													<c:if test="${carModel.status eq 1}">
														   checked = "true"
													</c:if>
														   value="1"  name="status">&nbsp;启用&nbsp;&nbsp;&nbsp;
													<input type="radio"
															<c:if test="${carModel.status eq 0}">
																checked = "true"
															</c:if>
														   name="status" value="0" />&nbsp;禁用
												</div>
											</div>
										</c:if>

										<c:if test="${empty carModel}">
											<div class="form-group" >
												<label class="col-md-2 control-label">状态:</label>
												<div class="col-md-7">
													<input type="radio" checked = "true" value="1"  name="status">&nbsp;启用&nbsp;&nbsp;&nbsp;
													<input type="radio" name="status" value="0" />&nbsp;禁用
												</div>
											</div>
										</c:if>

	                                 </div>
	                            </div>
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty carModel ? "添加" : "保存"}中...">
                                            ${empty carModel ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/carModel/edit.js"></script>
</body>
</html>