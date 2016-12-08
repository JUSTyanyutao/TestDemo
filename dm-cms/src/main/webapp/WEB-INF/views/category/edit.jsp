<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="分类" />
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
<c:set var="mainTitle" value="分类管理" />
<c:set var="subTitle" value="${empty auctionCategory ? '添加分类' : '保存分类'}" />


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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/category/save">
	                            <div class="row">
	                                <div class="col-md-12">
										<input type="hidden" id="id" name="id" value="${category.id}">
										<input type="hidden" id="link"  value="${link}">

										<c:if test="${empty category.id}">
										<div class="form-group">
											<label class="col-md-2 control-label">父菜单</label>
											<div class="col-md-8">
											<select name="pid" id="rootCategory" class="form-control">
												<option value="0">根分类</option>
												<c:forEach items="${rootCategoryList}" var="root">
													<option value="${root.id}"
														${category.category.id eq root.id ? "selected" : ""}
														${root.id eq pid ? "selected" : ""}>${root.name}</option>
												</c:forEach>
											</select>
											</div>
										</div>
										</c:if>
										<c:if test="${!empty category.id}">
										<div class="form-group">
											<label class="col-md-2 control-label">父菜单</label>
											<div class="col-md-8">
												<input type="hidden" name="pid"  class="form-control" value="${category.pid}">
												<input   class="form-control" value="${category.pid eq 0 ?"根菜单":category.category.name}" readonly />
											</div>
										</div>
										</c:if>

										<div class="form-group">
											<label class="col-md-2 control-label">分类名称:</label>
											<div class="col-md-8">
												<input type="text" name="name" id="name" placeholder="请输入分类名称"
	                                                class="form-control" value="${category.name}">
	                                         </div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">排序:</label>
											<div class="col-md-7">
												<input type="number" name="sort" id="sort" placeholder="请输入优先级"
													class="form-control" value="${category.sort}">
											</div>
										</div>
										<c:if test="${empty category.id}">
										<div class="form-group" id="statusDiv1" >
											<label class="col-md-2 control-label">类型:</label>
											<div class="col-md-7">
												<input type="radio" name="type" value="1"  >&nbsp;普通商品&nbsp;&nbsp;&nbsp;
												<input type="radio" name="type" value="2" />&nbsp;服务商品
											</div>
										</div>
										</c:if>

										<%--<div class="form-group" id="statusDiv1" style="display: none;">--%>
											<%--<label class="col-md-2 control-label">类型:</label>--%>
											<%--<div class="col-md-7">--%>
												<%--<input type="radio" name="type" value="1"  >&nbsp;普通商品&nbsp;&nbsp;&nbsp;--%>
												<%--<input type="radio" name="type" value="2" />&nbsp;服务商品--%>
											<%--</div>--%>
										<%--</div>--%>

										<c:if test="${!empty category.id}">
										<div class="form-group" id="statusDiv3">
											<label class="col-md-2 control-label">类型:</label>
											<div class="col-md-7">
												<input type="hidden"  name="type" value="${category.type}" />
												<input type="text"  name="type" class="form-control"  value=${category.type eq 1?"普通商品":"服务商品"} readonly />
											</div>
										</div>
										</c:if>

										<div class="form-group" id="statusDiv2" style="display: none;">
											<label class="col-md-2 control-label">类型:</label>
											<div class="col-md-7">
												<input type="hidden" id="type1" name="type" value="${category.type}" />
												<input type="text" id="type2" name="type" class="form-control"  value=${category.type eq 1?"普通商品":"服务商品"} readonly />
											</div>
										</div>


										<div class="form-group" >
											<label class="col-md-2 control-label">状态:</label>
											<div class="col-md-7">
												<select type="text" name="isDisplay" id="isDisplay"
														class="form-control" value="${category.isDisplay}">
													<c:choose>
														<c:when test="${category.isDisplay eq 0}">
															<option value="0">禁用</option>
															<option value="1">启用</option>
														</c:when>
														<c:otherwise>
															<option value="1">启用</option>
															<option value="0">禁用</option>
														</c:otherwise>
													</c:choose>
												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">图标(180*180):</label>
											<input type="hidden" name="icon"  id="icon" value = "${category.icon}" >
											<div class="col-md-8">
												<div id="picsFileList">
													<c:if test="${not empty category.icon}">
														<div style="margin-bottom:10px">
															<img  height="200" width= "200" class="filename" target="_blank" src=${category.icon}>
															<a class="btn red del_file" data-filename = "${category.icon}">删除</a>
														</div>
													</c:if>
												</div>
												<input id="pic_upload" name="pic_upload" type="file" multiple="true">
											</div>
										</div>

	                                     <c:if test="${!empty category.id}">
	                                     		<div class="form-group">
			                                         <label class="col-md-2 control-label">创建时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${category.createTime}'/>"
			                                        			name="createTime" id="createTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>
			                                     <div class="form-group">
			                                         <label class="col-md-2 control-label">更新时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${category.updateTime}'/>"
			                                        			name="updateTime" id="updateTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>
	                                     </c:if>
	                                 </div>
	                            </div>
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty auction ? "添加" : "保存"}中...">
                                            ${empty auction ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/category/edit.js"></script>
</body>
</html>