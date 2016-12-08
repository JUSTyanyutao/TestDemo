<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="活动营养知识管理管理 - 怎么吃" />
    </c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
</head>
<c:set var="mainTitle" value="活动营养知识管理管理" />
<c:set var="subTitle" value="${empty activityNutrition ? '添加活动营养知识管理' : '保存活动营养知识管理'}" />

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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/activityNutrition">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${activityNutrition.id}">
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">类型:</label>
	                                        	<div class="col-md-8">
	                                        		<label class="radio-inline c-radio">
		                                                <input type="radio" name="type" value="1"
		                                                       ${activityNutrition.type eq 1 ? 'checked' : ''}>
		                                                <span class="fa fa-circle"></span>活动
		                                            </label>
		                                            <label class="radio-inline c-radio">
		                                                <input type="radio" name="type" value="2"
		                                                       ${activityNutrition.type eq 2 ? 'checked' : ''}>
		                                                <span class="fa fa-circle"></span>营养知识
		                                            </label>
		                                            <label class="radio-inline c-radio">
		                                                <input type="radio" name="type" value="3"
		                                                       ${activityNutrition.type eq 3 ? 'checked' : ''}>
		                                                <span class="fa fa-circle"></span>如何做菜
		                                            </label>
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">标题:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="question" id="question" placeholder="请输入标题"
	                                                class="form-control" value="${activityNutrition.question}">
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">短介绍:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="text" name="shortIntro" id="shortIntro" placeholder="请输入短介绍"
	                                                class="form-control" value="${activityNutrition.shortIntro}">
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">列表图(710*308):</label>
	                                         <input type="hidden" name="pic"  id="pic" value = "${activityNutrition.pic}" >
	                                        	<div class="col-md-8">
													<div id="picsFileList">
													<c:if test="${not empty activityNutrition.pic}">
														<div>
															<img  height="200" width= "200" class="filename" target="_blank" src=${image_url }${activityNutrition.pic}>
															<a class="btn red del_file" data-filename = "${activityNutrition.pic}">删除</a>
														</div>
													</c:if>
										           </div> 	   
									          <input id="pic_upload" name="pic_upload" type="file" multiple="true">
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">详情图(750*390):</label>
	                                         <input type="hidden" name="detailPic"  id="detailPic" value = "${activityNutrition.detailPic}" >
	                                        	<div class="col-md-8">
													<div id="detailPicsFileList">
													<c:if test="${not empty activityNutrition.detailPic}">
														<div>
															<img  height="200" width= "200" class="filename2" target="_blank" src=${image_url }${activityNutrition.detailPic}>
															<a class="btn red del_file" data-filename = "${activityNutrition.detailPic}">删除</a>
														</div>
													</c:if>
										           </div> 	   
									          <input id="datailPic_upload" name="datailPic_upload" type="file" multiple="true">
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">详情内容:</label>
	                                         <div class="col-md-8">
	                                         	<textarea rows="3" name="answer" id="answer" placeholder="请编辑详情内容"
	                          								class="form-control" >${activityNutrition.answer}</textarea>
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">状态:</label>
	                                        	<div class="col-md-8">
	                                        		<select id="status" name="status" class="form-control" tabindex="1">
														<c:choose>
														    <c:when test="${activityNutrition.status == 0}">
																<option value="1" >开放</option>
																<option value="0" selected>关闭</option>
															</c:when>
															<c:otherwise>
																<option value="0" >关闭</option>
																<option value="1" selected>开放</option>
															</c:otherwise>
														</c:choose>
													</select>
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                            <label class="col-sm-2 control-label">排序</label>
	                                            <div class="col-sm-8">
		                                            <input id="sort" name="sort" data-ui-slider="" type="text" value="20" data-slider-min="1"
		                                                   data-slider-max="20" data-slider-step="1" data-slider-value="${activityNutrition.sort}"
		                                                   data-slider-orientation="horizontal" class="slider slider-horizontal"
		                                                   data="value: '20'" style="display: none;">
	                                            </div>
	                                     </div>
	                                     
	                                 </div>
	                            </div>
	                            
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty activityNutrition ? "添加" : "保存"}中...">
                                            ${empty activityNutrition ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/activityNutrition/edit.js"></script>
<script>

$(function(){
	
	var editor = new Editor("#answer");
	
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
					$("#picsFileList")
							.html(
									'<div><img  height="200" width= "200" class="filename" src='+data.image_url+data.link+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
					delFile(data.link);
					//$("#btn-save-goods").prop("disabled", false);
				}
			});
	
	$("#datailPic_upload").uploadify(
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
					$("#detailPicsFileList")
							.html(
									'<div><img  height="200" width= "200" class="filename2" src='+data.image_url+data.link+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
					delFile(data.link);
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