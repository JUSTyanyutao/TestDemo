confirmsolve<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="参数管理" />
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
<c:set var="mainTitle" value="参数管理" />
<c:set var="subTitle" value="${empty sysParamPo ? '添加参数' : '保存参数'}" />


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
                           <form id="param-form-edit" role="form" method="POST"  class="form-horizontal" action="${ctx}/param/save">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${params.id}">
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">参数变量:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="key" id="key" placeholder="请输入参数变量"
	                                                class="form-control" value="${params.key}">
	                                         </div>
	                                     </div>
										<div class="form-group">
											<label class="col-md-2 control-label">参数值:</label>
											<div class="col-md-8">
												<input type="text" name="value" id="value" placeholder="请输入参数值"
													   class="form-control" value="${params.value}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">参数名称:</label>
											<div class="col-md-8">
												<input type="text" name="paramDesc" id="desc" placeholder="请输入参数名称"
													   class="form-control" value="${params.paramDesc}">
											</div>
										</div>
										<%--
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">协议内容:</label>
	                                         <div class="col-md-8">
	                                         	<textarea rows="3" name="answer" id="agreementPo_content" placeholder="请编辑HTML结构内容"
	                          								class="form-control" >${agreementPo.answer}</textarea>
	                                         </div>
	                                     </div>
	                                     --%>
	                                     <%--<c:if test="${!empty sysParamPo.id}">--%>
	                                     		<%--<div class="form-group">--%>
			                                         <%--<label class="col-md-2 control-label">创建时间:</label>--%>
			                                        	<%--<div class="col-md-6">--%>
			                                        		<%--<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${sysParamPo.createTime}'/>"--%>
			                                        			<%--name="createTime" id="createTime" class="form-control" readonly>--%>
			                                        	<%--</div>--%>
			                                     <%--</div>--%>
			                                     <%----%>
			                                     <%--<div class="form-group">--%>
			                                         <%--<label class="col-md-2 control-label">更新时间:</label>--%>
			                                        	<%--<div class="col-md-6">--%>
			                                        		<%--<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${sysParamPo.updateTime}'/>"--%>
			                                        			<%--id="updateTime" class="form-control" readonly>--%>
			                                        	<%--</div>--%>
			                                     <%--</div>--%>
	                                     <%--</c:if>--%>
	                                 </div>
	                            </div>

	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty sysParamPo ? "添加" : "保存"}中...">
                                            ${empty sysParamPo ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/param/edit.js"></script>
<script>

	<%--
$(function(){
	
	var editor = new Editor("#agreementPo_content");  --%>
	
	<%--$("#pic_upload").uploadify(--%>
			<%--{--%>
				<%--height : 30,--%>
				<%--auto : true,--%>
				<%--swf : window.ctx + '/static/libs/uploadify/uploadify.swf',--%>
				<%--uploader : window.ctx + '/attachment/uploadImage;jsessionid=${jsessionid}',--%>
				<%--width : 120,--%>
				<%--fileObjName : 'fileData',--%>
				<%--fileTypeDesc: 'Image Files',--%>
		        <%--//允许上传的文件后缀--%>
		        <%--fileTypeExts: '*.gif; *.jpg; *.jpeg; *.png',--%>
				<%--buttonText : "上传图片",--%>
				<%--debug : false,--%>
				<%--uploadLimit : 10,--%>
				<%--onUploadStart : function() {--%>
				<%--},--%>
				<%--onUploadError : function(file, errorCode, errorMsg, errorString) {--%>
					<%--alert('The file ' + file.name--%>
							<%--+ ' could not be uploaded: '--%>
							<%--+ errorString);--%>
				<%--},--%>
				<%--onUploadSuccess : function(file, data, response) {--%>
					<%--data = eval("(" + data + ")");--%>
					<%--$("#picsFileList")--%>
							<%--.html(--%>
									<%--'<div style="margin-bottom:10px"><img  height="200" width= "200" class="filename" src='+data.image_url+data.link+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');--%>
					<%--delFile(data.link);--%>
					<%--//$("#btn-save-goods").prop("disabled", false);--%>
				<%--}--%>
			<%--});--%>
	<%----%>
	<%--$("#detailPic_upload").uploadify(--%>
			<%--{--%>
				<%--height : 30,--%>
				<%--auto : true,--%>
				<%--swf : window.ctx + '/static/libs/uploadify/uploadify.swf',--%>
				<%--uploader : window.ctx + '/attachment/uploadImage;jsessionid=${jsessionid}',--%>
				<%--width : 120,--%>
				<%--fileObjName : 'fileData',--%>
				<%--fileTypeDesc: 'Image Files',--%>
		        <%--//允许上传的文件后缀--%>
		        <%--fileTypeExts: '*.gif; *.jpg; *.jpeg; *.png',--%>
				<%--buttonText : "上传图片",--%>
				<%--debug : false,--%>
				<%--uploadLimit : 10,--%>
				<%--onUploadStart : function() {--%>
				<%--},--%>
				<%--onUploadError : function(file, errorCode, errorMsg, errorString) {--%>
					<%--alert('The file ' + file.name--%>
							<%--+ ' could not be uploaded: '--%>
							<%--+ errorString);--%>
				<%--},--%>
				<%--onUploadSuccess : function(file, data, response) {--%>
					<%--data = eval("(" + data + ")");--%>
					<%--$("#detailPicFileList")--%>
							<%--.append(--%>
									<%--'<div style="margin-bottom:10px"><img  height="200" width= "200" class="filename2" src='+data.image_url+data.link+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');--%>
					<%--delFile(data.link);--%>
					<%--//$("#btn-save-goods").prop("disabled", false);--%>
				<%--}--%>
			<%--});--%>
	<%----%>
	<%--delFile();--%>
	<%----%>
	<%--function delFile(fileName){--%>
		<%--$(".del_file").off("click").on("click",function(){--%>
			<%--var obj = $(this);--%>
			<%--if(null==fileName || fileName == ""){--%>
				<%--fileName = obj.attr("data-filename");--%>
			<%--}--%>
		<%----%>
			<%--obj.parent().remove();--%>
			<%----%>
		<%--})--%>
	<%--}--%>

	<%--
});   --%>
</script>
<script type="text/javascript">

</script>
</body>

</html>