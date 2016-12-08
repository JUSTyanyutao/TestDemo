<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="商家管理 - 怎么吃" />
    </c:import>
    <link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
    <link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
    
    
</head>

<c:set var="mainTitle" value="商家管理" />
<c:set var="subTitle" value="${empty merchant.id ? '添加商家' : '编辑商家'}" />

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
                            <div class="row">
                                <div class="col-md-12">
                                    <form id="form-edit" role="form" method="POST" class="form-horizontal"  action="${ctx}/merchant/saveMerchant">
                                        <input type="hidden" name="id" value="${merchant.id}">
                                        
                                        <div class="row">
		                                  	<div class="col-md-6">
				                                <div class="form-group">
				                                      <label class="col-sm-3 control-label">手机号码:</label>
				                                      <div class="col-sm-9">
				                                      	<input value="${merchant.mobilephone}" placeholder="请输入手机号码" readonly id="mobilephone" name="mobilephone" class="form-control" type="text">
				                                      </div>
				                                </div>
		                                  	</div>
		                                  	
		                                  	<div class="col-md-6">
				                               	<div class="form-group">
				                                      <label class="col-sm-3 control-label">商户名称:</label>
				                                      <div class="col-sm-9">
				                                      	<input value="${merchant.name}" placeholder="请输入商户名称"  id="name" name="name" class="form-control" type="text">
				                                      </div>
				                                </div>
		                                  	</div>
		                               </div>
		                               
		                               <div class="row">

			                                <div class="col-md-6">
			                                  	 <div class="form-group">
			                                         <label class="col-sm-3 control-label">联系人:</label>
			                                         <div class="col-sm-9">
			                                         	<input value="${merchant.contact}"  id="contact" name="contact" class="form-control" type="text">
			                                         </div>
			                                     </div>
			                                </div>   
		                                  	
		                                  	<div class="col-md-6">
				                               	<div class="form-group">
				                                      <label class="col-sm-3 control-label">摊位:</label>
				                                      <div class="col-sm-9">
				                                      	<input value="${merchant.boothNo}"  placeholder=""  id="boothNo" name="boothNo" class="form-control" type="text">
				                                      </div>
				                                </div>
		                                  	</div>
		                               </div>
		                               
		                               <div class="row">

			                                <div class="col-md-6">
			                                  	 <div class="form-group">
			                                         <label class="col-sm-3 control-label">所属菜场:</label>
			                                         <div class="col-sm-9">
			                                         	<select id="marketId" name="marketId" class="form-control" data-select="${merchant.marketId }">
											            </select>
			                                         </div>
			                                     </div>
			                                </div>   
		                                  	
		                                  	<div class="col-md-6">
				                               	<div class="form-group">
				                               	    <label class="col-sm-3 control-label">销售额:</label>
					                                      <div class="col-sm-3">
					                                      	<input value="${empty merchant.sale ? '0.00': merchant.sale}" readonly   id="sale" name="sale" class="form-control" type="text">
					                                </div>
					                                      
				                               		 <label class="col-sm-3 control-label">可提取现金:</label>
				                                      <div class="col-sm-3">
				                                      	<input value="${empty merchant.withdrawCash ? '0.00': merchant.withdrawCash}" readonly   id="withdrawCash" name="withdrawCash" class="form-control" type="text">
				                                      </div>
				                                </div>
		                                  	</div>
		                               </div>
		                               
		                               
		                               <div class="row">
		                                  	<div class="col-md-6">
				                                <div class="form-group">
				                                     <label class="col-sm-3 control-label">销售量:</label>
				                                      <div class="col-sm-3">
				                                      	<input value="${empty merchant.salesVolume ? '0.00' : merchant.salesVolume}" readonly   id="salesVolume" name="salesVolume" class="form-control" type="text">
				                                      </div>
				                                      
				                                        <label class="col-sm-3 control-label">收藏数量:</label>
				                                      <div class="col-sm-3">
				                                      	<input value="${merchant.collectionNum}"  placeholder="" readonly  id="collectionNum" name="collectionNum" class="form-control" type="text">
				                                      </div>
				                                      
				                                </div>
		                                  	</div>
		                                  	
		                                  	
		                                  	<div class="col-md-6">
				                                <div class="form-group">
				                                     <label class="col-sm-3 control-label">总评分:</label>
				                                      <div class="col-sm-3">
				                                      	<input value="${empty merchant.score ? '0' : merchant.score}" readonly   id="score" name="score" class="form-control" type="text">
				                                      </div>
				                                      
				                                        <label class="col-sm-3 control-label">评论总数量:</label>
				                                      <div class="col-sm-3">
				                                      	<input value="${merchant.commentCount}"  placeholder="" readonly  id="commentCount" name="commentCount" class="form-control" type="text">
				                                      </div>
				                                      
				                                </div>
		                                  	</div>
		                                </div>
		                                  
		                              <div class="row">  	
		                                 <div class="col-md-6">
		                                   <div class="form-group">
	                                         <label class="col-sm-3 control-label">缩略图:</label>
	                                         <input type="hidden" name="pic"  id="pic" value = "${merchant.pic}" >
	                                        	<div class="col-sm-9">
													<div id="picsFileList">
													<c:if test="${not empty merchant.pic}">
														<div style="margin-bottom:10px">
															<img  height="200" width= "200" class="filename" target="_blank" src=${image_url }${merchant.pic}>
														    <a class="btn red del_file" data-filename = "${merchant.pic}">删除</a>
														</div>
													</c:if>
										           </div> 
										           <input id="pic_upload" name="pic_upload" type="file" multiple="true">	   
	                                        	</div>
	                                       </div>
	                                     </div>
	                                     
	                                    <div class="col-md-6">
		                                   <div class="form-group">
	                                         <label class="col-sm-3 control-label">身份证图片:</label>
	                                         <input type="hidden" name="idcardPic"  id="idcardPic" value = "${merchant.idcardPic}" >
	                                        	<div class="col-sm-9">
													<div id="idcardPicFileList">
													<c:if test="${not empty merchant.idcardPic}">
														<div style="margin-bottom:10px">
															<img  height="200" width= "200" class="filename2" target="_blank" src=${image_url }${merchant.idcardPic}>
														    <a class="btn red del_file" data-filename = "${merchant.idcardPic}">删除</a>
														</div>
													</c:if>
										           </div>
										           <input id="idcardPic_upload" name="idcardPic_upload" type="file" multiple="true"> 	   
	                                        	</div>
	                                       </div>
	                                     </div>
	                                     
		                               </div>
		                               
		                               
		                               <div class="row">  	
		                                 <div class="col-md-6">
		                                   <div class="form-group">
	                                         <label class="col-sm-3 control-label">营业执照:</label>
	                                         <input type="hidden" name="businessLicensePic"  id="businessLicensePic" value = "${merchant.businessLicensePic}" >
	                                        	<div class="col-sm-9">
													<div id="businessLicensePicFileList">
													<c:if test="${not empty merchant.businessLicensePic}">
														<div style="margin-bottom:10px">
															<img  height="200" width= "200" class="filename3" target="_blank" src=${image_url }${merchant.businessLicensePic}>
														    <a class="btn red del_file" data-filename = "${merchant.businessLicensePic}">删除</a>
														</div>
													</c:if>
										           </div> 
										           <input id="businessLicensePic_upload" name="businessLicensePic_upload" type="file" multiple="true">	   
	                                        	</div>
	                                       </div>
	                                     </div>
	                                     
	                                    <div class="col-md-6">
		                                   <div class="form-group">
	                                         <label class="col-sm-3 control-label">合同图片:</label>
	                                         <input type="hidden" name="contractPic"  id="contractPic" value = "${merchant.contractPic}" >
	                                        	<div class="col-sm-9">
													<div id="contractPicFileList">
													<c:if test="${not empty merchant.contractPic}">
														<div style="margin-bottom:10px">
															<img  height="200" width= "200" class="filename4" target="_blank" src=${image_url }${merchant.contractPic}>
														    <a class="btn red del_file" data-filename = "${merchant.contractPic}">删除</a>
														</div>
													</c:if>
										           </div>
										           <input id="contractPic_upload" name="contractPic_upload" type="file" multiple="true"> 	   
	                                        	</div>
	                                       </div>
	                                     </div>
	                                     
		                               </div>
		                               
		                               <div class="row">
		                                  	<div class="col-md-6">
				                                <div class="form-group">
				                                      <label class="col-sm-3 control-label">商家介绍:</label>
				                                      <div class="col-sm-9">
				                                      <textarea rows="3" name="intro" id="intro" placeholder="请输入商家介绍"
	                          								class="form-control" >${merchant.intro}</textarea>
				                                      </div>
				                                </div>
		                                  	</div>
		                                  	
		                               </div>
		                               
		                               <div class="row">
		                               	
		                               </div><br/>
                                        <a href="${ctx}/merchant" class="btn btn-sm btn-back btn-default">返回</a>
                                        <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty merchant.id ? "添加" : "编辑"}中...">
                                            ${empty merchant.id ? "添 加" : "编 辑"}
                                        </button>
                                    </form>
                                </div>
                            </div>
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
<script src="${ctx}/static/js/merchant/edit.js"></script>

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
					$("#picsFileList")
							.html(
									'<div><img  height="200" width= "200" class="filename" src='+data.image_url+data.link+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
					delFile(data.link);
					//$("#btn-save-goods").prop("disabled", false);
				}
			});
	
	
	$("#idcardPic_upload").uploadify(
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
					$("#idcardPicFileList")
							.html(
									'<div><img  height="200" width= "200" class="filename2" src='+data.image_url+data.link+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
					delFile(data.link);
					//$("#btn-save-goods").prop("disabled", false);
				}
			});
	
	
	$("#businessLicensePic_upload").uploadify(
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
					$("#businessLicensePicFileList")
							.html(
									'<div><img  height="200" width= "200" class="filename3" src='+data.image_url+data.link+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
					delFile(data.link);
					//$("#btn-save-goods").prop("disabled", false);
				}
			});
	
	$("#contractPic_upload").uploadify(
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
					$("#contractPicFileList")
							.html(
									'<div><img  height="200" width= "200" class="filename4" src='+data.image_url+data.link+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
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

<script type="text/javascript">
	$(function(){
		$("select").each(function(){
			$(this).val($(this).attr("data-select")).trigger("change");
		});	
	});
	//var imgs = document.getElementsByTagName('img');
	//for(var i = 0;i < imgs.length; i++){
	//	imgs[i].style.width = "100%";
	//}
	
</script>

</body>

</html>