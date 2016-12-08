<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.mxep.model.enums.EnumMembeRole" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="技师管理 - 达膜" />
    </c:import>
    <link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
    <link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
    
    
</head>

<c:set var="mainTitle" value="技师管理" />
<c:set var="subTitle" value="${empty member.id ? '添加技师' : '编辑技师'}" />
<c:set var="roles" value="<%=EnumMembeRole.values()%>" />
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
                                    <form id="form-edit" role="form" method="POST" class="form-horizontal"  action="${ctx}/worker/saveWorker">
										<div class="row">
											<%--<div class="col-md-6">--%>
												<%--<div class="form-group">--%>
													<%--<label class="col-sm-3 control-label">区域经理:</label>--%>
													<%--<div class="col-sm-9">--%>
														<%--<select id="parentMemberId" name="parentMemberId" class="form-control" data-select="${cityList}">--%>
														<%--</select>--%>
													<%--</div>--%>
												<%--</div>--%>
											<%--</div>--%>
										</div>

										<div class="row">
		                                  	<div class="col-md-6">
				                                <div class="form-group">
				                                      <label class="col-sm-3 control-label">手机号码:</label>
				                                      <div class="col-sm-9">
				                                      	<input value="${member.mobile}" placeholder="请输入手机号码"  id="mobile" name="mobile" class="form-control" type="text">
				                                      </div>
				                                </div>
		                                  	</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-sm-3 control-label">真实名称:</label>
													<div class="col-sm-9">
														<input value="${member.profile.realName}"  placeholder="请输入真实名称"  id="profile" name="profile.realName" class="form-control" type="text">
													</div>
												</div>
											</div>
		                               </div>

		                               <div class="row">
										   <div class="col-md-6">
											   <div class="form-group">
												   <label class="col-sm-3 control-label">昵称:</label>
												   <div class="col-sm-9">
													   <input value="${member.profile.nickname}"  placeholder="请输入昵称"  id="profile.nickname" name="profile.nickname" class="form-control" type="text">
												   </div>
											   </div>
										   </div>
										   <div class="col-md-6">
											   <div class="form-group">
												   <label class="col-sm-3 control-label">选择城市:</label>
												   <div class="col-sm-9">
													   <select id="city" name="cityId" class="form-control" >
													   </select>
												   </div>
											   </div>
										   </div>
		                               </div>
		                               
		                               <div class="row">
										   <div class="col-md-6">
											   <div class="form-group">
												   <label class="col-sm-3 control-label">状态:</label>
												   <div class="col-sm-9">
													   <label class="radio-inline c-radio">
														   <input type="radio" name="status" value="1" checked
														   ${member.status eq 1 ? 'checked' : ''}>
														   <span class="fa fa-circle"></span>可用
													   </label>
													   <label class="radio-inline c-radio">
														   <input type="radio" name="status" value="0"
														   ${member.status eq 0 ? 'checked' : ''}>
														   <span class="fa fa-circle"></span>禁用
													   </label>
												   </div>
											   </div>
										   </div>
										   <div class="col-md-6">
											   <div class="form-group">
												   <label class="col-sm-3 control-label">性别:</label>
												   <div class="col-sm-9">
													   <label class="radio-inline c-radio">
														   <input type="radio" name="profile.gender" value="1" checked
														   ${member.profile.gender eq 1 ? 'checked' : ''}>
														   <span class="fa fa-circle"></span>男
													   </label>
													   <label class="radio-inline c-radio">
														   <input type="radio" name="profile.gender" value="2"
														   ${member.profile.gender eq 2 ? 'checked' : ''}>
														   <span class="fa fa-circle"></span>女
													   </label>
												   </div>
											   </div>
										   </div>
		                               </div>
		                               
		                               <div class="row">
										   <div class="col-md-6">
											   <div class="form-group">
												   <label class="col-sm-3 control-label">技师身份:</label>
												   <div class="col-sm-9">
													   <input  class="form-control" value="技师" disabled  />
													   <input type="hidden" name="role" value="3"   />
												   </div>
											   </div>
										   </div>
		                                  	<div class="col-md-6">
		                                  	</div>
		                               </div>
		                               
		                               <div class="row">
										   <div class="col-md-6">
											   <div class="form-group">
												   <label class="col-sm-3 control-label">身份证:</label>
												   <div class="col-sm-9">
													   <input type="text" name="idcard" id="idcard" placeholder="请输入身份证"
															  class="form-control" value="${worker.idcard}">
												   </div>
											   </div>
										   </div>
										   <div class="col-md-6">
											   <div class="form-group">
												   <label class="col-sm-3 control-label">银行卡号:</label>
												   <div class="col-sm-9">
													   <input type="text" name="bankCardNo" id="bankCardNo" placeholder="请输入银行卡号"
															  class="form-control" value="${worker.bankCardNo}">
												   </div>
											   </div>
										   </div>
									   </div>

										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-sm-3 control-label">开户行:</label>
													<div class="col-sm-9">
														<input type="text" name="bankName" id="bankName" placeholder="请输入开户行"
															   class="form-control" value="${worker.bankName}">
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-sm-3 control-label">从业时间:</label>
													<div class="col-sm-9">
														<input id="workAge" name="workAge" class="form-control" value="${worker.workge}" />
													</div>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-sm-3 control-label">微信号:</label>
													<div class="col-sm-9">
														<input type="text" name="wechat" id="wechat" placeholder="请输入微信号"
															   class="form-control" value="${worker.wechat}">
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-sm-3 control-label">备注:</label>
													<div class="col-sm-9">
														<input type="text" name="remark" id="remark" placeholder="请输入备注"
															   class="form-control" value="${worker.remark}">
													</div>
												</div>
											</div>
											<input type="hidden" name="applyStatus" value="1" />
										</div>

										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-sm-3 control-label">工作类型:</label>
													<div class="col-sm-9">
														<input type="radio"
														<c:if test="${worker.workType eq 1}">
															   checked = "true"
														</c:if>
															   value="1"  name="workType">&nbsp;洗车&nbsp;&nbsp;&nbsp;
														<input type="radio"
																<c:if test="${worker.workType eq 2}">
																	checked = "true"
																</c:if>
															   name="workType" value="0" />&nbsp;贴膜
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-sm-3 control-label">服务分类:</label>
													<div class="col-sm-9">
														<select id="categoryId" name="categoryId" class="form-control">
														</select>
													</div>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-sm-3 control-label">工作状态:</label>
													<div class="col-sm-9">
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
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-sm-3 control-label">驾驶证:</label>
													<div class="col-sm-9">
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
											</div>
										</div>

										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-sm-3 control-label">开车上路:</label>
													<div class="col-sm-9">
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
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-sm-3 control-label">私家车:</label>
													<div class="col-sm-9">
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
										</div>
										<br/>
		                                 
                                        
                                        <a href="javascript:history.go(-1);" class="btn btn-sm btn-back btn-default">返回</a>

                                        <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty member.id ? "添加" : "编辑"}中...">
                                            ${empty member.id ? "添 加" : "编 辑"}
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
<script src="${ctx}/static/js/worker/add.js"></script>
</body>

</html>