<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.mxep.model.goods.Goods" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="商品管理 - 达膜贴膜" />
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
<c:set var="mainTitle" value="商品管理" />
<c:set var="subTitle" value="${empty goods ? '添加商品' : '保存商品'}" />


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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/goods/save">
	                            <div class="row">
	                                <div class="col-md-12">
										<input type="hidden" id="id" name="id" value="${goods.id}">
										<input type="hidden" id="url" value="${url}" />
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">商品名称:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="name" id="name" placeholder="请输入商品名称"
	                                                class="form-control" value="${goods.name}">
	                                         </div>
	                                     </div>

										<%--<div class="form-group">--%>
											<%--<label class="col-md-2 control-label">分类:</label>--%>
											<%--<div class="col-md-8">--%>
												<%--&lt;%&ndash;<select  name="categoryId" id="category" class="form-control select1" data-select="${goods.categoryId}" ></select>&ndash;%&gt;--%>
												<input type="hidden" value="4" name="categoryId" />
												<%--<input  value="车膜" class="form-control" disabled />--%>

											<%--</div>--%>
										<%--</div>--%>

										<div class="form-group">
											<label class="col-md-2 control-label">品牌:</label>
											<div class="col-md-8">
												<select  name="filmBrandId" id="filmBrand" class="form-control select1" data-select="${goods.filmBrandId}" ></select>
											</div>
										</div>

										<%--<div class="form-group">--%>
											<%--<label class="col-md-2 control-label">商品类型:</label>--%>
											<%--<div class="col-md-8">--%>
												<%--<select  name="attrType" id="attrType" class="form-control select1" data-select="${goods.attrType}" ></select>--%>
											<%--</div>--%>
										<%--</div>--%>

										<div class="form-group">
											<label class="col-md-2 control-label">商品特性:</label>
											<div class="col-md-8">
												<select  name="attrFeatures" id="attrFeatures" class="form-control select1" data-select="${goods.attrFeatures}" ></select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">套餐:</label>
											<div class="col-md-8">
												<select  name="packages" id="packages" class="form-control select1" data-select="${packageList}" ></select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">车(尺寸):</label>
											<div class="col-md-8">
												<select  name="carSize" id="carSize" class="form-control select1" data-select="${goods.carSize}" ></select>
											</div>
										</div>

										<%--<div class="form-group">--%>
											<%--<label class="col-md-2 control-label">车的部位:</label>--%>
											<%--<div class="col-md-8">--%>
												<%--<select  name="carParts" id="carParts" class="form-control select1" data-select="${goods.carParts}" ></select>--%>
											<%--</div>--%>
										<%--</div>--%>

										<div class="form-group" >
											<label class="col-md-2 control-label">商品类型:</label>
											<div class="col-md-7">
												<input type="hidden" name="type" value="1" />
												<input type="text" class="form-control"  value="普通商品" disabled />
											</div>
										</div>

										<div class="form-group" >
											<label class="col-md-2 control-label">适用用户群:</label>
											<div class="col-md-7">
												<input type="hidden" value="1" name="userType" readonly />
												<label class="form-control">普通用户</label>
												<%--<input type="radio"--%>
												<%--<c:if test="${goods.userType eq 1}">--%>
												<%--checked = "true"--%>
												<%--</c:if>--%>
												<%--value="1"  name="userType">&nbsp;普通用户&nbsp;&nbsp;&nbsp;--%>
												<%--<input type="radio"--%>
												<%--<c:if test="${goods.userType eq 2}">--%>
												<%--checked = "true"--%>
												<%--</c:if>--%>
												<%--name="userType" value="0" />&nbsp;企业用户--%>
											</div>
										</div>

	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">商品售价:</label>
	                                         <div class="col-md-8">
	                                         	<input type="number" name="price" id="price" placeholder="请输入商品售价"
	                                                class="form-control"  value="${goods.price}">
	                                         </div>
	                                     </div>

										<div class="form-group">
											<label class="col-md-2 control-label">商品原价:</label>
											<div class="col-md-8">
												<input type="number" name="originalPrice" id="originalPrice" placeholder="请输入商品原价"
													   class="form-control"  value="${goods.originalPrice}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">短介绍:</label>
											<div class="col-md-8">
												<input  name="shortIntro" id="shortIntro" placeholder="请输入短介绍"
													   class="form-control"  value="${goods.shortIntro}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">排序:</label>
											<div class="col-md-8">
												<input name="sort" data-ui-slider="" type="text" value="20" data-slider-min="1"
													   data-slider-max="20" data-slider-step="1" data-slider-value="${goods.sort}"
													   data-slider-orientation="horizontal" class="slider slider-horizontal"
													   data="value: '20'" style="display: none;">
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">置顶图(180*180):</label>
											<input type="hidden" name="recommendPic"  id="recommendPic" value = "${goods.recommendPic}" >
											<div class="col-md-8">
												<div id="picsFileList1">
													<c:if test="${not empty goods.recommendPic}">
														<div style="margin-bottom:10px">
															<img  height="200" width= "200" class="filename1" target="_blank" src=${goods.recommendPic}>
															<a class="btn red del_file" data-filename = "${goods.recommendPic}">删除</a>
														</div>
													</c:if>
												</div>
												<input id="pic_upload1" name="pic_upload" type="file" multiple="true">
											</div>
										</div>

	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">列表图(180*180):</label>
	                                         <input type="hidden" name="thumb"  id="thumb" value = "${goods.thumb}" >
	                                        	<div class="col-md-8">
													<div id="picsFileList2">
													<c:if test="${not empty goods.thumb}">
														<div style="margin-bottom:10px">
															<img  height="200" width= "200" class="filename2" target="_blank" src=${goods.thumb}>
															<a class="btn red del_file" data-filename = "${goods.thumb}">删除</a>
														</div>
													</c:if>
										           </div>
									          <input id="pic_upload2" name="pic_upload" type="file" multiple="true">
	                                        	</div>
	                                     </div>

	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">详情图(750*500):</label>
	                                         <input type="hidden" name="pictures"  id="pictures" value = "${goods.pictures}" >
	                                        	<div class="col-md-8">
													<div id="detailPicFileList">
													<c:if test="${not empty goods.pictures}">
														<c:set value="${ fn:split(goods.pictures, ',') }" var="picList" />
														<c:forEach var="pic" items="${picList}">
															<div style="margin-bottom:10px">
																<img  height="200" width= "200" class="filename3" target="_blank" src=${pic }>
																<a class="btn red del_file" data-filename = "${pic}">删除</a>
															</div>
														</c:forEach>
													</c:if>
										           </div>
									          <input id="detailPic_upload" name="detailPic_upload" type="file" multiple="true">
	                                        	</div>
	                                     </div>

	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">商品详情:</label>
	                                         <div class="col-md-8">
	                                         	<%--<textarea rows="3" name="goodsInfo.detailContent" id="detailContent" placeholder="请编辑HTML结构内容"--%>
	                          								<%--class="form-control" >${goods.goodsInfo.detailContent}</textarea>--%>
												<script type="text/plain" id="detailContent" name="goodsInfo.detailContent">
													${goods.goodsInfo.detailContent}
												</script>
											 </div>
	                                     </div>

										<div class="form-group">
											<label class="col-md-2 control-label">规格详情:</label>
											<div class="col-md-8">
												<script type="text/plain" id="specContent" name="goodsInfo.specContent">
													${goods.goodsInfo.specContent}
												</script>
	                                         	<%--<textarea rows="3" name="goodsInfo.specContent" id="specContent" placeholder="请编辑HTML结构内容"--%>
														  <%--class="form-control" >${goods.goodsInfo.specContent}</textarea>--%>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">服务流程:</label>
											<div class="col-md-8">
												<script type="text/plain" id="serviceProcessContent" name="goodsInfo.serviceProcessContent">
													${goods.goodsInfo.serviceProcessContent}
												</script>
	                                         	<%--<textarea rows="3" name="goodsInfo.serviceProcessContent" id="serviceProcessContent" placeholder="请编辑HTML结构内容"--%>
														  <%--class="form-control" >${goods.goodsInfo.serviceProcessContent}</textarea>--%>
											</div>
										</div>

	                                     <c:if test="${!empty goods.id}">
	                                     		<div class="form-group">
			                                         <label class="col-md-2 control-label">创建时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${goods.createTime}'/>"
			                                        			name="createTime" id="createTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>

			                                     <div class="form-group">
			                                         <label class="col-md-2 control-label">更新时间:</label>
			                                        	<div class="col-md-6">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${goods.updateTime}'/>"
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
                                                data-loading-text="${empty goods ? "添加" : "保存"}中...">
                                            ${empty goods ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/goods/common/edit.js"></script>
<script src="${ctx}/static/third/ueditor/ueditor.config.js"></script>
<script src="${ctx}/static/third/ueditor/editor_api.js"></script>
<script src="${ctx}/static/js/common/baiduEditor.js"></script>
<script>

$(function(){

	$("#pic_upload1").uploadify(
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
					$("#picsFileList1")
							.html(
									'<div style="margin-bottom:10px"><img  height="200" width= "200" class="filename1" src='+data.image_url+data.url+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
					delFile(data.url);
					//$("#btn-save-goods").prop("disabled", false);
				}
			});

	$("#pic_upload2").uploadify(
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
					$("#picsFileList2")
							.html(
									'<div style="margin-bottom:10px"><img  height="200" width= "200" class="filename2" src='+data.image_url+data.url+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
					delFile(data.url);
					//$("#btn-save-goods").prop("disabled", false);
				}
			});

	$("#detailPic_upload").uploadify(
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
					$("#detailPicFileList")
							.append(
									'<div style="margin-bottom:10px"><img  height="200" width= "200" class="filename3" src='+data.image_url+data.url+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
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