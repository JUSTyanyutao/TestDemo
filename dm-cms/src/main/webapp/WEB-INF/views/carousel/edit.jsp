<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.mxep.model.enums.EnumPlatformType" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="轮播图管理 - 梦想e拍" />
    </c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
</head>
<c:set var="mainTitle" value="轮播图管理" />
<c:set var="subTitle" value="${empty carousel ? '添加轮播图' : '保存轮播图'}" />

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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/carousel">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${carousel.id}">
										<input type="hidden" id="img"  value="${image_url}">
	                                     <%--<div class="form-group">--%>
	                                         <%--<label class="col-md-2 control-label">所属菜场:</label>--%>
	                                         <%--<div class="col-md-8">--%>
	                                         	<%--<select id="marketId" name="marketId" class="form-control" style="width:100%" data-select="${carousel.marketId }">--%>
									            <%--</select>--%>
	                                         <%--</div>--%>
	                                     <%--</div>--%>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">名称:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="name" id="name" placeholder="请输入轮播图名称"
	                                                class="form-control" value="${carousel.name}">
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">链接:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="text" name="link" id="link" placeholder="请输入轮播图链接"
	                                                class="form-control" value="${carousel.link}">
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">图片(750*400):</label>
	                                         <input type="hidden" name="pic"  id="pic" value = "${carousel.pic}" >
											 <input type="hidden" name="msg" id="msg">
	                                        	<div class="col-md-8">
													<div id="picsFileList">
													<c:if test="${not empty carousel.pic}">
														<div>
															<img  height="200" width= "200" class="filename" target="_blank" src=${carousel.pic}>
															<a class="btn red del_file" data-filename = "${carousel.pic}">删除</a>
														</div>
													</c:if>
										           </div> 	   
									          <input id="pic_upload" name="pic_upload" type="file" multiple="true">
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">状态:</label>
	                                        	<div class="col-md-8">
	                                        		<select id="isDisplay" name="isDisplay" class="form-control">
														<option value="1"  <c:if test="${carousel.isDisplay eq 1}">selected</c:if>    >显示</option>
														<option value="0" <c:if test="${carousel.isDisplay eq 0}">selected</c:if> >隐藏</option>
													</select>
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">投放平台:</label>
	                                        	<div class="col-md-8">
	                                        		<select id="platform" name="platform" class="form-control" date-select="${carousel.platform}" tabindex="1">

														<c:set value="<%=EnumPlatformType.values()%>" var="platforms"/>
													   <c:forEach items="${platforms}" var="platform" varStatus="s">
															<%--<option value="${platform.value}"${platform.selected}>${platform.name}</option>--%>
														   <option value="${platform.value}"   <c:if test="${carousel.platform eq platform.value}">selected</c:if> >
														   		${platform.name}
														   </option>
														</c:forEach>	
													</select>
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                            <label class="col-sm-2 control-label">排序</label>
	                                            <div class="col-sm-8">
		                                            <input id="sort" name="sort" data-ui-slider="" type="text" value="20" data-slider-min="1"
		                                                   data-slider-max="20" data-slider-step="1" data-slider-value="${carousel.sort}"
		                                                   data-slider-orientation="horizontal" class="slider slider-horizontal"
		                                                   data="value: '20'" style="display: none;">
	                                            </div>
	                                     </div>
	                                     
	                                 </div>
	                            </div>
	                            
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty carousel ? "添加" : "保存"}中...">
                                            ${empty carousel ? "添 加" : "保存"}
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
<script src="${ctx}/static/libs/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/carousel/edit.js"></script>
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
									'<div><img  height="200" width= "200" class="filename" src='+data.image_url+data.url+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
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