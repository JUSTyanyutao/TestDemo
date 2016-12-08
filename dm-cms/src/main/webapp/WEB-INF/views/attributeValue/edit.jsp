<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="特性管理 - 达膜" />
    </c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/third/colorPicker/css/colorpicker.css" rel="stylesheet" type="text/css" />
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
<c:set var="mainTitle" value="商品属性管理" />
<c:set var="subTitle" value="${empty attributeValue ? '添加特性' : '保存特性'}" />


<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp" />
    <section>
        <div class="answer-wrapper">
            <h3>${mainTitle}
                <small>${subTitle}</small>
            </h3>
            <div >
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">${subTitle}</div>
                        <div class="panel-body">
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/attribute/value/save">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${attributeValue.id}">
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">属性值:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="attrValue" id="attrValue" placeholder="请输入特性值"
	                                                class="form-control" value="${attributeValue.attrValue}">
	                                         </div>
	                                     </div>

										<div class="form-group">
											<label class="col-md-2 control-label">颜色:</label>
											<div class="col-md-8">
												<input type="text" maxlength="6" size="6" id="color" name="color" value="${attributeValue.color}" class="form-control" />
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">百分比:</label>
											<div class="col-md-8">
												<input type="number" maxlength="2" size="2"  name="percentum" value="${attributeValue.percentum}" class="form-control" />
											</div>
										</div>

										<c:if test="${empty attributeValue}">
										<div class="form-group">
											<label class="col-md-2 control-label">类型:</label>
											<div class="col-md-8">
												<select  name="attrId" id="attribute" class="form-control select1" data-select="${attributeValue.attributeId}" ></select>
											</div>
										</div>
										</c:if>

										<c:if test="${!empty attributeValue}">
											<div class="form-group">
												<label class="col-md-2 control-label">类型:</label>
												<div class="col-md-8">
													<input   class="form-control" value="${attributeValue.attribute.name}" readonly />
													<input  type="hidden"  name="attrId" class="form-control" value="${attributeValue.attrId}"  />
												</div>
											</div>
										</c:if>

										<div class="form-group" >
											<label class="col-md-2 control-label">状态:</label>
											<div class="col-md-7">
												<input type="radio"
												<c:if test="${attributeValue.status eq 1}">
													   checked = "true"
												</c:if>
													   value="1"  name="status">&nbsp;启用&nbsp;&nbsp;&nbsp;
												<input type="radio"
														<c:if test="${attributeValue.status eq 0}">
															checked = "true"
														</c:if>
													   name="status" value="0" />&nbsp;禁用
											</div>
										</div>
										<%--<div class="form-group">--%>
											<%--<label class="col-md-2 control-label">备注:</label>--%>
											<%--<div class="col-md-8">--%>
												<%--<input type="text" name="remark" id="remark" placeholder="请输入备注"--%>
													   <%--class="form-control" value="${attributeValue.remark}">--%>
											<%--</div>--%>
										<%--</div>--%>

	                                     <c:if test="${!empty attributeValue.id}">
	                                     		<div class="form-group">
			                                         <label class="col-md-2 control-label">创建时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${attributeValue.createTime}'/>"
			                                        			name="createTime" id="createTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>

			                                     <div class="form-group">
			                                         <label class="col-md-2 control-label">更新时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${attributeValue.updateTime}'/>"
			                                        			name="updateTime" id="updateTime" class="form-control" readonly>
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
                                                data-loading-text="${empty attributeValue ? "添加" : "保存"}中...">
                                            ${empty attributeValue ? "添 加" : "保存"}
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
<script src="${ctx}/static/third/colorPicker/js/colorpicker.js"></script>
<script src="${ctx}/static/libs/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/attributeValue/edit.js"></script>
</body>
</html>