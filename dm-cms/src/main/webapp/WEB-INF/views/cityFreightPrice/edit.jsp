<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="城市运费管理 - 达膜" />
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
<c:set var="mainTitle" value="城市运费管理" />
<c:set var="subTitle" value="${empty cityFreightPrice ? '添加城市运费' : '保存城市运费'}" />


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
                           <form id="form-edit" role="form" method="POST"  class="form-horizontal" action="${ctx}/city/freight/price/save">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${cityFreightPrice.id}">

										<div class="form-group">
											<label class="col-md-2 control-label">省:</label>
											<div class="col-md-8">
												<select  name="provinceId" id="province"
													   class="form-control" data-select="${cityFreightPrice.provinceId}"></select>
											</div>
										</div>
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">市:</label>
	                                         <div class="col-md-8">
	                                         	<select  name="cityId" id="city"
	                                                class="form-control" data-select="${cityFreightPrice.cityId}"></select>
	                                         </div>
	                                     </div>


	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">价格:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="number" name="freightPrice" id="freightPrice" placeholder="请输入价格"
	                                                class="form-control" value="${cityFreightPrice.freightPrice}">
	                                        	</div>
	                                     </div>

	                                     <c:if test="${!empty cityFreightPrice.id}">
	                                     		<div class="form-group">
			                                         <label class="col-md-2 control-label">创建时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${cityFreightPrice.createTime}'/>"
			                                        			name="createTime" id="createTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>
	                                     </c:if>
	                                     <%--<div class="form-group">--%>
	                                         <%--<label class="col-md-2 control-label">状态:</label>--%>
	                                        	<%--<div class="col-md-8">--%>
	                                        		<%--<select id="status" name="status" class="form-control" tabindex="1" data-select="${goods.status}">--%>
														<%--<c:forEach items="${goodsStatuses}" var="goodsStatus">--%>
															<%--<option value="${goodsStatus.value }">${goodsStatus.label}</option>--%>
														<%--</c:forEach>--%>
													<%--</select>--%>
	                                        	<%--</div>--%>
	                                     <%--</div>--%>
	                                 </div>
	                            </div>
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty cityFreightPrice ? "添加" : "保存"}中...">
                                            ${empty cityFreightPrice ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/cityFreightPrice/edit.js"></script>
</body>
</html>