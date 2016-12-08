<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.mxep.model.enums.EnumPlatformType" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="部位管理 - 达膜贴膜" />
    </c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
</head>
<c:set var="mainTitle" value="部位管理" />
<c:set var="subTitle" value="${empty carousel ? '添加部位' : '保存部位'}" />

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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/carPart/save">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${carPart.id}">
										<%--<input type="hidden" id="img"  value="${image_url}">--%>
	                                     <%--<div class="form-group">--%>
	                                         <%--<label class="col-md-2 control-label">所属菜场:</label>--%>
	                                         <%--<div class="col-md-8">--%>
	                                         	<%--<select id="marketId" name="marketId" class="form-control" style="width:100%" data-select="${carousel.marketId }">--%>
									            <%--</select>--%>
	                                         <%--</div>--%>
	                                     <%--</div>--%>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">名称:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="name" id="name" placeholder="请输入部位名称"
	                                                class="form-control" value="${carPart.name}">
	                                         </div>
	                                     </div>

										<div class="form-group">
											<label class="col-md-2 control-label">分类:</label>
											<div class="col-md-8">
												<select  name="categoryId" id="categoryId" class="form-control select1" data-select="${carPart.categoryId}" >
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">比例:</label>
											<div class="col-md-8">
												<input type="text" name="priceRatio" id="priceRatio" placeholder="请输入比例"
													   class="form-control" value="${carPart.priceRatio}">
											</div>
										</div>
	                                 </div>

									<div class="form-group" >
										<label class="col-md-2 control-label">状态:</label>
										<div class="col-md-7">
											<input type="radio"
											<c:if test="${carPart.status eq 1}">
												   checked = "true"
											</c:if>
												   value="1"  name="status">&nbsp;启用&nbsp;&nbsp;&nbsp;
											<input type="radio"
													<c:if test="${carPart.status eq 0}">
														checked = "true"
													</c:if>
												   name="status" value="0" />&nbsp;禁用
										</div>
									</div>

	                            </div>
	                            
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty carousel ? "添加" : "保存"}中...">
                                            ${empty carousel ? "添 加" : "保存"}
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
<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>
<script src="${ctx}/static/libs/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/carPart/edit.js"></script>
</body>

</html>