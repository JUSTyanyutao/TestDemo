<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="套餐管理 - 达膜" />
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
<c:set var="mainTitle" value="套餐管理" />
<c:set var="subTitle" value="${empty brand ? '添加套餐' : '保存套餐'}" />


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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/package/save">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${item.id}">
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">套餐名称:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="name" id="name" placeholder="请输入套餐名称"
	                                                class="form-control" value="${item.name}">
	                                         </div>
	                                     </div>


										<div class="form-group">
											<label class="col-md-2 control-label">关联分类:</label>
											<div class="col-md-8">
												<%--<select  id="rootCategory"  class="form-control select1" data-select="${item.category.pid}" ></select>--%>
												<select  name="categoryId" id="category" class="form-control select1" data-select="${item.categoryId}" ></select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">类型:</label>
											<div class="col-md-8">
												<select  name="type" id="type" class="form-control select1" data-select="${item.type}" >
													<option value="1">普通商品</option>
													<option value="2">服务商品</option>
												</select>
											</div>
										</div>

										<div class="form-group" >
											<label class="col-md-2 control-label">状态:</label>
											<div class="col-md-7">
												<input type="radio"
												<c:if test="${item.status eq 1}">
													   checked = "true"
												</c:if>
													   value="1"  name="status">&nbsp;启用&nbsp;&nbsp;&nbsp;
												<input type="radio"
														<c:if test="${item.status eq 0}">
															checked = "true"
														</c:if>
													   name="status" value="0" />&nbsp;禁用
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">备注:</label>
											<div class="col-md-8">
												<input type="text" name="remark" id="remark" placeholder="请输入备注"
													   class="form-control" value="${item.remark}">
											</div>
										</div>
	                                     <%--<div class="form-group">--%>
	                                         <%--<label class="col-md-2 control-label">详情图(750*500):</label>--%>
	                                         <%--<input type="hidden" name="pictures"  id="pictures" value = "${goods.pictures}" >--%>
	                                        	<%--<div class="col-md-8">--%>
													<%--<div id="detailPicFileList">--%>
													<%--<c:if test="${not empty goods.pictures}">--%>
														<%--<c:set value="${ fn:split(goods.pictures, ',') }" var="picList" />--%>
														<%--<c:forEach var="pic" items="${picList}">--%>
															<%--<div style="margin-bottom:10px">--%>
																<%--<img  height="200" width= "200" class="filename2" target="_blank" src=${pic }>--%>
																<%--<a class="btn red del_file" data-filename = "${pic}">删除</a>--%>
															<%--</div>--%>
														<%--</c:forEach>--%>
													<%--</c:if>--%>
										           <%--</div>--%>
									          <%--<input id="detailPic_upload" name="detailPic_upload" type="file" multiple="true">--%>
	                                        	<%--</div>--%>
	                                     <%--</div>--%>


	                                     <c:if test="${!empty item.id}">
	                                     		<div class="form-group">
			                                         <label class="col-md-2 control-label">创建时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${item.createTime}'/>"
			                                        			name="createTime" id="createTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>

			                                     <div class="form-group">
			                                         <label class="col-md-2 control-label">更新时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${item.updateTime}'/>"
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
                                                data-loading-text="${empty item ? "添加" : "保存"}中...">
                                            ${empty item ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/package/edit.js"></script>
</body>
</html>