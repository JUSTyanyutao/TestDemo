<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mxep.model.enums.EnumWorkage" %>

<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="title" value="技师管理" />
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
    .wrapper section{
    	position: static;
    }
    #map-container{height:500px;width:100%;}  
    #latitude-error{
    	margin-left: -70px;
    }
    #aroundPoint-error{
    	position: relative;
    	top: 40px;
    }
    
   /*  #pic-error,#areaList-error{
    	margin-left:160px;
    } */
</style>    
</head>
<c:set var="mainTitle" value="技师管理" />
<c:set var="subTitle" value="${empty worker ? '添加技师' : '编辑技师'}" />

<c:set value="<%=EnumWorkage.values()%>" var="workAges"></c:set>
<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp" />
    <section>
        <div class="content-wrapper">
            <h3>${mainTitle}
                <small>${subTitle}</small>
            </h3>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">${subTitle}</div>
                        <div class="panel-body">
                           <form id="form-edit" role="form" method="POST" class="form-horizontal" action="${ctx}/worker/save">
	                                <div class="col-md-12">

	                                     <input type="hidden" id="id" name="id" value="${worker.id}">
	                                     <input type="hidden"  name="memberId" value="${worker.memberId}">
										<input type="hidden" id="url" value="${url}" />
	                                     <%--<input type="hidden" id="city" name="city" />--%>
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">姓名:</label>
	                                         <div class="col-md-8">
												 <input type="text" name="name" id="name" placeholder="请输入姓名"
														class="form-control" value="${worker.name}">
	                                         </div>
	                                     </div>

										<%--<div class="form-group">--%>
											<%--<label class="col-md-2 control-label">区域经理:</label>--%>
											<%--<div class="col-md-8">--%>
												<%--<select id="parentMemberId" name="parentMemberId" class="form-control" data-select="${worker.parentMemberId}">--%>
												<%--</select>--%>
											<%--</div>--%>
										<%--</div>--%>

										<div class="form-group">
											<label class="col-md-2 control-label">城市:</label>
											<div class="col-md-8">
												<select id="city" name="cityId" class="form-control" data-select="${worker.cityId}" >
												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">服务分类:</label>
											<div class="col-md-8">
												<select id="categoryId" name="categoryId" class="form-control"  data-select="${worker.categoryId}">
												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">身份证:</label>
											<div class="col-md-8">
												<input type="text" name="idcard" id="idcard" placeholder="请输入身份证"
													   class="form-control" value="${worker.idcard}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">银行卡号:</label>
											<div class="col-md-8">
												<input type="text" name="bankCardNo" id="bankCardNo" placeholder="请输入银行卡号"
													   class="form-control" value="${worker.bankCardNo}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">开户行:</label>
											<div class="col-md-8">
												<input type="text" name="bankName" id="bankName" placeholder="请输入开户行"
													   class="form-control" value="${worker.bankName}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">从业时间:</label>
											<div class="col-md-8">
												<input  name="workAge" id="workAge" class="form-control" value="${worker.workAge}" />
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">微信号:</label>
											<div class="col-md-8">
												<input type="text" name="wechat" id="wechat" placeholder="请输入微信号"
													   class="form-control" value="${worker.wechat}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">审核状态:</label>
											<div class="col-md-8">
												<label class="form-control">
													<c:if test="${worker.applyStatus == 0}">待审核</c:if>
													<c:if test="${worker.applyStatus == 1}">已审核</c:if>
													<c:if test="${worker.applyStatus == 2}">反驳</c:if>
												</label>
												<input type="hidden" name="applyStatus" value="${worker.applyStatus}" />

											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">得分:</label>
											<div class="col-md-8">
												<input type="text" name="score" id="score" readonly
													   class="form-control" value="${worker.score}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">备注:</label>
											<div class="col-md-8">
												<input type="text" name="remark" id="remark" placeholder="请输入备注"
													   class="form-control" value="${worker.remark}">
											</div>
										</div>

										<div class="form-group" >
											<label class="col-md-2 control-label">性别:</label>
											<div class="col-md-7">
												<input type="radio"
												<c:if test="${worker.sex eq 1}">
													   checked = "true"
												</c:if>
													   value="1"  name="sex">&nbsp;男&nbsp;&nbsp;&nbsp;
												<input type="radio"
														<c:if test="${worker.sex eq 0}">
															checked = "true"
														</c:if>
													   name="sex" value="0" />&nbsp;女
											</div>
										</div>

										<div class="form-group" >
											<label class="col-md-2 control-label">工作状态:</label>
											<div class="col-md-7">
												<input type="radio"
												<c:if test="${worker.workingStatus eq 1}">
													   checked = "true"
												</c:if>
													   value="1"  name="workingStatus">&nbsp;有&nbsp;&nbsp;&nbsp;
												<input type="radio"
														<c:if test="${worker.workingStatus eq 0}">
															checked = "true"
														</c:if>
													   name="workingStatus" value="0" />&nbsp;无
											</div>
										</div>

										<div class="form-group" >
											<label class="col-md-2 control-label">驾驶证:</label>
											<div class="col-md-7">
												<input type="radio"
												<c:if test="${worker.hasDrivingLicense eq 1}">
													   checked = "true"
												</c:if>
													   value="1"  name="hasDrivingLicense">&nbsp;有&nbsp;&nbsp;&nbsp;
												<input type="radio"
														<c:if test="${worker.hasDrivingLicense eq 0}">
															checked = "true"
														</c:if>
													   name="hasDrivingLicense" value="0" />&nbsp;无
											</div>
										</div>

										<div class="form-group" >
											<label class="col-md-2 control-label">开车上路:</label>
											<div class="col-md-7">
												<input type="radio"
												<c:if test="${worker.canDrive eq 1}">
													   checked = "true"
												</c:if>
													   value="1"  name="canDrive">&nbsp;可以&nbsp;&nbsp;&nbsp;
												<input type="radio"
														<c:if test="${worker.canDrive eq 0}">
															checked = "true"
														</c:if>
													   name="canDrive" value="0" />&nbsp;不可以
											</div>
										</div>

										<div class="form-group" >
											<label class="col-md-2 control-label">私家车:</label>
											<div class="col-md-7">
												<input type="radio"
												<c:if test="${worker.hasCar eq 1}">
													   checked = "true"
												</c:if>
													   value="1"  name="hasCar">&nbsp;有&nbsp;&nbsp;&nbsp;
												<input type="radio"
														<c:if test="${worker.hasCar eq 0}">
															checked = "true"
														</c:if>
													   name="hasCar" value="0" />&nbsp;无
											</div>
										</div>


	                                 </div>
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty worker ? "添加" : "保存"}中...">
                                            ${empty worker ? "添 加" : "保存"}
                                 </button>
                            </form> 
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
<script src="${ctx}/static/js/worker/edit.js"></script>
</body>

</html>