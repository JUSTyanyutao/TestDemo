<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="按钮管理 - 达膜" />
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
<c:set var="mainTitle" value="按钮管理" />
<c:set var="subTitle" value="${empty brand ? '添加按钮' : '保存按钮'}" />


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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/module/save">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${module.id}">
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">按钮名称:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="name" id="name" placeholder="请输入按钮名称"
	                                                class="form-control" value="${module.name}">
	                                         </div>
	                                     </div>

										<div class="form-group" >
											<label class="col-md-2 control-label">类型:</label>
											<div class="col-md-7">
												<input type="radio"
												<c:if test="${module.link != 2}">
													   checked = "true"
												</c:if>
													   value="1"  name="link">&nbsp;普通商品&nbsp;&nbsp;&nbsp;
												<input type="radio"
														<c:if test="${module.link eq 2}">
															checked = "true"
														</c:if>
													   name="link" value="2" />&nbsp;服务商品
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">分类:</label>
											<div class="col-md-8">
												<select  name="categoryIds" id="categoryIds" multiple="multiple" class="form-control select1" data-select="${module.categoryIds}" >
												</select>
											</div>
										</div>



										<div class="form-group" >
											<label class="col-md-2 control-label">状态:</label>
											<div class="col-md-7">
												<input type="radio"
												<c:if test="${module.status eq 1}">
													   checked = "true"
												</c:if>
													   value="1"  name="status">&nbsp;启用&nbsp;&nbsp;&nbsp;
												<input type="radio"
														<c:if test="${module.status eq 0}">
															checked = "true"
														</c:if>
													   name="status" value="0" />&nbsp;禁用
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">排序:</label>
											<div class="col-md-8">
												<input type="number" name="sort" id="sort" placeholder="请输入排序"
													   class="form-control" value="${module.sort}">
											</div>
										</div>


										<div class="form-group">
											<label class="col-md-2 control-label">图标(180*180):</label>
											<input type="hidden" name="icon"  id="icon" value = "${module.icon}" >
											<div class="col-md-8">
												<div id="picsFileList">
													<c:if test="${not empty module.icon}">
														<div style="margin-bottom:10px">
															<img  height="200" width= "200" class="filename" target="_blank" src=${module.icon}>
															<a class="btn red del_file" data-filename = "${module.icon}">删除</a>
														</div>
													</c:if>
												</div>
												<input id="pic_upload" name="pic_upload" type="file" multiple="true">
											</div>
										</div>


	                                     <c:if test="${!empty module.id}">
	                                     		<div class="form-group">
			                                         <label class="col-md-2 control-label">创建时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${module.createTime}'/>"
			                                        			name="createTime" id="createTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>

			                                     <div class="form-group">
			                                         <label class="col-md-2 control-label">更新时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${module.updateTime}'/>"
			                                        			name="updateTime" id="updateTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>
	                                     </c:if>
	                                 </div>
	                            </div>
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty module ? "添加" : "保存"}中...">
                                            ${empty module ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/module/edit.js"></script>
<script>

	$(function(){

		$("#pic_upload").uploadify(
				{
					height : 30,
					auto : true,
					swf : window.ctx + '/static/libs/uploadify/uploadify.swf',
					uploader : window.ctx + '/attachment/uploadImage;jsessionid=${jsessionid}',
					width : 120,
					fileObjName : 'fileData',
					fileTypeDesc: 'Image Files',
					//允许上传的文件后缀
					fileTypeExts: '*.gif; *.jpg; *.jpeg; *.png',
					buttonText : "上传图片",
					debug : false,
					uploadLimit : 10,
					onUploadStart : function() {
					},
					onUploadError : function(file, errorCode, errorMsg, errorString) {
						alert('The file ' + file.name
								+ ' could not be uploaded: '
								+ errorString);
					},
					onUploadSuccess : function(file, data, response) {
						data = eval("(" + data + ")");
						console.log(data);
						$("#picsFileList")
								.html(
										'<div style="margin-bottom:10px"><img  height="200" width= "200" class="filename" src='+data.image_url+data.url+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
						delFile(data.url);
						//$("#btn-save-goods").prop("disabled", false);
					}
				});
		delFile();

		function delFile(fileName){
			$(".del_file").off("click").on("click",function(){
				var obj = $(this);
				if(null==fileName || fileName == ""){
					fileName = obj.attr("data-filename");
				}

				obj.parent().remove();

			})
		}

	});
</script>
</body>
</html>