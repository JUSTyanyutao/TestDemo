<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.mxep.model.enums.EnumMembeRole" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="区域经理管理 - 达膜" />
    </c:import>
    <link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
    <link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
    
    
</head>

<c:set var="mainTitle" value="区域经理管理" />
<c:set var="subTitle" value="${empty member.id ? '添加区域经理' : '编辑区域经理'}" />
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
                                    <form id="form-edit" role="form" method="POST" class="form-horizontal"  action="${ctx}/member/saveManagerMember">
                                        <input type="hidden" name="id" value="${member.id}">
                                        <input type="hidden" name="profile.id" value="${member.profile.id}">
										<input type="hidden" name="profile.memberId" value="${member.profile.memberId}">

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

											<%--<div class="col-md-6">--%>
												<%--<div class="form-group">--%>
													<%--<label class="col-sm-3 control-label">登陆密码:</label>--%>
													<%--<div class="col-sm-9">--%>
														<%--<input value="${member.password }" placeholder="请输入登陆密码"  id="password" name="password" class="form-control" type="password">--%>
													<%--</div>--%>
												<%--</div>--%>
											<%--</div>--%>
		                               </div>

		                               <div class="row">
										   <div class="col-md-6">
											   <div class="form-group">
												   <label class="col-sm-3 control-label">选择区域:</label>
												   <div class="col-sm-9">
													   <select id="city" name="city" class="form-control" data-select="${cityList}">
													   </select>
												   </div>
											   </div>
										   </div>
		                                  	
		                                  	<%--<div class="col-md-6">--%>
				                               	<%--<div class="form-group">--%>
				                                      <%--<label class="col-sm-3 control-label">来源平台:</label>--%>
				                                      <%--<div class="col-sm-9">--%>
				                                      	<%--<select id="platType" name="profile.platform" class="form-control" data-select="${member.profile.platform}">--%>
				                                      		<%--<option value="">请选择来源平台</option>--%>
		                                        			<%--<c:forEach items="${sourceMap}" var="source">--%>
		                                        				<%--<option value="${source.key}">${ source.value}</option>--%>
		                                        			<%--</c:forEach>--%>
	                                            		<%--</select>--%>
				                                      <%--</div>--%>
				                                <%--</div>--%>
		                                  	<%--</div>--%>
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
				                                      <label class="col-sm-3 control-label">区域经理身份:</label>
				                                      <div class="col-sm-9">
														  <input  class="form-control" value="区域经理" disabled  />
														  <input type="hidden" name="role" value="4"   />
														  <%--<input type="hidden" value="${member.profile.appVersion }"  placeholder="请输入应用版本号"  id="appVersion" name="profile.appVersion" class="form-control" type="text">--%>
														  <%--<select name="role" id="role" class="form-control">--%>
															  <%--<c:forEach items="${roles}" var="role">--%>
																  <%--<option value="${role.value}"--%>
																  	<%--<c:if test="${role.value eq member.role}">selected="selected"</c:if>--%>
																  <%-->${role.name}</option>--%>
															  <%--</c:forEach>--%>
														  <%--</select>--%>
													  </div>
				                                </div>
		                                  	</div>
		                               </div>
		                               
		                               <div class="row">
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
		                                  	
		                                  	<div class="col-md-6">
				                               	<div class="form-group">
				                                      <label class="col-sm-3 control-label">注册时间:</label>
				                                      <div class="col-sm-9">
				                                      	<input value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${member.registerTime}'/>" id="registerTime" name="registerTime" class="form-control" readonly type="text">
				                                      	
				                                      </div>
				                                </div>
		                                  	</div>
		                               </div>
		                               
		                               <div class="row">
		                                  	<div class="col-md-6">
				                                <!-- <div class="form-group">
				                                      <label class="col-sm-3 control-label">状态:</label>
				                                      <div class="col-sm-9">
				                                      	<input type="text" placeholder="编辑子页面后的保存地址"  id="content_url" class="form-control">
				                                      </div>
				                                </div> -->
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
				                                      <label class="col-sm-3 control-label">更新时间:</label>
				                                      <div class="col-sm-9">
				                                      	<input value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${member.updateTime}'/>" id="updateTime" name="updateTime" class="form-control" readonly type="text">
				                                      </div>
				                                </div>
		                                  	</div>
		                               </div><br/>
		                                 
                                        
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
<script src="${ctx}/static/js/member/manager/edit.js"></script>
</body>

</html>