<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="品牌管理 - 达膜" />
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
<c:set var="mainTitle" value="品牌管理" />
<c:set var="subTitle" value="${empty brand ? '添加品牌' : '保存品牌'}" />


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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/brand/save">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${brand.id}">
										<input type="hidden" id="url" value="${url}" />
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">品牌名称:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="name" id="name" placeholder="请输入品牌名称"
	                                                class="form-control" value="${brand.name}">
	                                         </div>
	                                     </div>

	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">排序:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="number" name="sort" id="sort" placeholder="请输入排序"
	                                                class="form-control" value="${brand.sort}">
	                                        	</div>
	                                     </div>
										<div class="form-group" >
											<label class="col-md-2 control-label">状态:</label>
											<div class="col-md-7">
												<input type="radio"
												<c:if test="${brand.status eq 1}">
													   checked = "true"
												</c:if>
													   value="1"  name="status">&nbsp;上架&nbsp;&nbsp;&nbsp;
												<input type="radio"
														<c:if test="${brand.status eq 0}">
															checked = "true"
														</c:if>
													   name="status" value="0" />&nbsp;下架
											</div>
										</div>

										<div class="form-group" >
											<label class="col-md-2 control-label">置顶:</label>
											<div class="col-md-7">
												<input type="radio"
												<c:if test="${brand.recommendFlag eq 1}">
													   checked = "true"
												</c:if>
													   value="1"  name="recommendFlag">&nbsp;置顶&nbsp;&nbsp;&nbsp;
												<input type="radio"
														<c:if test="${brand.recommendFlag eq 0}">
															checked = "true"
														</c:if>
													   name="recommendFlag" value="0" />&nbsp;普通
											</div>
										</div>

	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">列表图(180*180):</label>
	                                         <input type="hidden" name="pic"  id="pic" value = "${brand.pic}" >
	                                        	<div class="col-md-8">
													<div id="picsFileList">
													<c:if test="${not empty brand.pic}">
														<div style="margin-bottom:10px">
															<img  height="200" width= "200" class="filename" target="_blank" src=${image_url }${brand.pic}>
															<a class="btn red del_file" data-filename = "${brand.pic}">删除</a>
														</div>
													</c:if>
										           </div>
									          <input id="pic_upload" name="pic_upload" type="file" multiple="true">
	                                        	</div>
	                                     </div>

	                                     <c:if test="${!empty brand.id}">
	                                     		<div class="form-group">
			                                         <label class="col-md-2 control-label">创建时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${brand.createTime}'/>"
			                                        			name="createTime" id="createTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>

			                                     <div class="form-group">
			                                         <label class="col-md-2 control-label">更新时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${brand.updateTime}'/>"
			                                        			name="updateTime" id="updateTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>
	                                     </c:if>
	                                 </div>
	                            </div>
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty brand ? "添加" : "保存"}中...">
                                            ${empty brand ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/brand/edit.js"></script>
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