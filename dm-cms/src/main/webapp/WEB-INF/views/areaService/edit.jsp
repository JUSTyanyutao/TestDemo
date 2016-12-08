<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fanfou.model.cabinet.SiteAreaService" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="区域管理 - 怎么吃" />
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
   /*  #pic-error,#areaList-error{
    	margin-left:160px;
    } */
</style>    
</head>
<c:set var="mainTitle" value="区域管理" />
<c:set var="subTitle" value="${empty areaService ? '添加区域' : '编辑区域'}" />
<!--区域状态-->
<c:set var="areaServiceStatuses" value="<%=SiteAreaService.AreaServiceStatus.values()%>" />

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
                           <form id="form-edit" role="form" method="POST" class="form-horizontal" action="${ctx}/areaService/saveAreaService">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${areaService.id}">
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">区域名称:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="text" name="regionName" id="regionName" placeholder="请输入区域名称"
	                                                class="form-control" value="${areaService.regionName}">
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">描述说明:</label>
	                                        	<div class="col-md-8">
	                                                <textarea rows="3" name="regionDesc" id="regionDesc" placeholder="请输入描述说明" class="form-control">${areaService.regionDesc}</textarea>
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">所在省:</label>
	                                         <div class="col-md-8">
									            <select id="provinceSelect" name="province.id" style="width:100%" data-select="${areaService.province.id}" ></select>
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">所在市:</label>
	                                         <div class="col-md-8">
									            <select id="citySelect" name="city.id" style="width:100%" data-select="${areaService.city.id}" ></select>
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">所在区:</label>
	                                         <div class="col-md-8">
									            <select id="countySelect" name="county.id" style="width:100%" data-select="${areaService.county.id}" ></select>
	                                         </div>
	                                     </div>
	                                     
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">状态:</label>
	                                        	<div class="col-md-8">
	                                        		<select id="status" name="status" class="form-control" tabindex="1" data-select="${areaService.status}">
														<c:forEach items="${areaServiceStatuses}" var="asStatus">
															<option value="${asStatus.value }">${asStatus.label}</option>
														</c:forEach>
													</select>
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                            <label class="col-md-2 control-label">排序:</label>
	                                            <div class="col-md-8">
		                                            <input id="sequence" name="sequence" data-ui-slider="" type="text" value="20" data-slider-min="1"
		                                                   data-slider-max="20" data-slider-step="1" data-slider-value="${areaService.sequence}"
		                                                   data-slider-orientation="horizontal" class="slider slider-horizontal"
		                                                   data="value: '20'" style="display: none;">
	                                            </div>
	                                     </div>
	                                     
	                                 </div>
	                            </div>
	                            
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty areaService ? "添加" : "保存"}中...">
                                            ${empty areaService ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/areaService/edit.js"></script>
</body>

</html>