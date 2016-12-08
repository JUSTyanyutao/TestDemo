<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mxep.model.enums.EnumDisplay" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="关键字管理 - 梦想e拍" />
    </c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
</head>
<c:set var="mainTitle" value="搜索关键字管理" />
<c:set var="subTitle" value="${empty searchKeywords ? '添加关键字' : '保存关键字'}" />

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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/searchKeywords">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${searchKeywords.id}">
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">显示状态:</label>
	                                        	<div class="col-md-8">
													<select class="form-control" name="isDisplay" style="width:20%">
														<c:set value="<%=EnumDisplay.values()%>" var="isDisplay"/>
														<c:choose>
															<c:when test="${searchKeywords.isDisplay eq 0}">
																<option value="0">隐藏</option>
																<option value="1">显示</option>
															</c:when>
															<c:otherwise>
																<option value="1">显示</option>
																<option value="0">隐藏</option>
															</c:otherwise>
														</c:choose>
													</select>
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">关 键 字:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="keywords" id="keywords" placeholder="请输入关键字"
	                                                class="form-control" value="${searchKeywords.keywords}">
	                                         </div>
	                                     </div>

										<div class="form-group">
											<label class="col-md-2 control-label">搜索次数:</label>
											<div class="col-md-8">
												<input type="text" name="count" id="count" placeholder="请输入搜索次数"
													   class="form-control" value="${searchKeywords.count}">
											</div>
										</div>

	                                     <div class="form-group">
	                                            <label class="col-sm-2 control-label">排序:</label>
	                                            <div class="col-sm-8">
		                                            <input id="sort" name="sort" data-ui-slider="" type="text" value="20" data-slider-min="1"
		                                                   data-slider-max="20" data-slider-step="1" data-slider-value="${searchKeywords.sort}"
		                                                   data-slider-orientation="horizontal" class="slider slider-horizontal"
		                                                   data="value: '20'" style="display: none;">
	                                            </div>
	                                     </div>

	                                 </div>
	                                 
	                            </div>
	                            
	                            <a class="btn btn-sm btn-primary " href="javascript:history.go(-1);" role="button">返回</a>
	                            <button type="submit" class="btn btn-submit btn-sm btn-primary "
                                                data-loading-text="${empty faq ? "添加" : "保存"}中...">
                                            ${empty faq ? "添 加" : "保存"}
                                 </button>
                                 <button id="saveAgain" type="submit" class="btn btn-submit btn-sm btn-primary " data-loading-text="保存中...">
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
<script src="${ctx}/static/js/searchKeywords/edit.js"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=c8d499635271ab4f9d449d35911e2cf1"></script>
</body>

</html>