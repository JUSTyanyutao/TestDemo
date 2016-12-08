<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fanfou.model.cabinet.SiteCabinet" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="柜子管理 - 怎么吃" />
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
    .wrapper section{
    	position: static;
    }
    #map-container{height:500px;width:100%;}  
    
   /*  #pic-error,#areaList-error{
    	margin-left:160px;
    } */
</style>    
</head>
<c:set var="mainTitle" value="柜子管理" />
<c:set var="subTitle" value="${empty siteCabinet ? '添加柜子' : '编辑柜子'}" />
<!--柜子状态-->
<c:set var="siteCabinetStatuses" value="<%=SiteCabinet.SiteCabinetStatus.values()%>" />

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
                           <form id="form-edit" role="form" method="POST" class="form-horizontal" action="${ctx}/siteCabinet/saveSiteCabinet">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${siteCabinet.id}">
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">所属站点:</label>
	                                         <div class="col-md-8">
									            <select id="stationNo" name="stationNo" style="width:100%" data-select="${siteCabinet.stationNo}" ></select>
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">柜子别名:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="text" name="cabinetAlias" id="cabinetAlias" placeholder="请输入柜子别名"
	                                                class="form-control" value="${siteCabinet.cabinetAlias}">
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">串口序号:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="text" name="serialPort" id="serialPort" placeholder="请输入串口序号"
	                                                class="form-control" value="${siteCabinet.serialPort}">
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">串口波特率:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="text" name="baudrate" id="baudrate" placeholder="请输入串口波特率"
	                                                class="form-control" value="${siteCabinet.baudrate}">
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">箱子数量:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="text" name="boxAmount" id="boxAmount" placeholder="请输入箱子数量"
	                                                class="form-control" value="${siteCabinet.boxAmount}">
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">状态:</label>
	                                        	<div class="col-md-8">
	                                        		<select id="cabinetStatus" name="cabinetStatus" class="form-control" tabindex="1" data-select="${siteCabinet.cabinetStatus}">
														<c:forEach items="${siteCabinetStatuses}" var="siteCabinetStatus">
															<option value="${siteCabinetStatus.value }">${siteCabinetStatus.label}</option>
														</c:forEach>
													</select>
	                                        	</div>
	                                     </div>
	                                     
	                                     
	                                     
	                                     <c:if test="${!empty cabinetStatus.id}">
	                                     		<div class="form-group">
			                                         <label class="col-md-2 control-label">创建时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${siteCabinet.createTime}'/>" 
			                                        			name="createTime" id="createTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>
	                                     </c:if>
	                                     
	                                     
	                                     
	                                     
	                                     
	                                 </div>
	                            
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty siteCabinet ? "添加" : "保存"}中...">
                                            ${empty siteCabinet ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/siteCabinet/edit.js"></script>
</body>

</html>