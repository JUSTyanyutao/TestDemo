<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="城市运费管理 - 梦想e拍" />
    </c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
</head>
<c:set var="mainTitle" value="城市运费管理" />
<c:set var="subTitle" value="${empty coupon ? '添加城市运费' : '保存城市运费'}" />

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
							<form id="ajaxform" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/city/freight/price/import" target="frameFile">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="col-md-2 control-label">批量导入城市运费:</label>
											<div class="col-md-4">
													<input type="file" name="file" accept="application/msexcel"  id="inputfile">
											</div>
											<div class="col-md-4">
												<div class="has-feedback has-success" >
													<label class="control-label" id="divInfo">

													</label>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
                           <form id="form-edit" role="form" method="POST"   class="form-horizontal" action="${ctx}/city/freight/price/adds">
	                             <a class="btn btn-sm btn-primary" href="${ctx}/city/freight/price/list" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="保存中...">
                                            保存
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
<script src="${ctx}/static/js/cityFreightPrice/batchEdit.js"></script>
<script src="${ctx}/static/js/coupon/jquery.form.js"></script>
<iframe id='frameFile' name='frameFile' style='display:none;' onload="uploadOnload();">

</iframe>
</body>

</html>