<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<%@ page import="com.mxep.model.member.MemberPrepaid" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="充值管理 - 怎么吃" />
    </c:import>
    <link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
    <link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
    
    
</head>

<c:set var="mainTitle" value="充值管理" />
<c:set var="subTitle" value="${empty memberPrepaid.id ? '添加充值' : '编辑充值'}" />
<!--充值类型  -->
<c:set var="types" value="<%=MemberPrepaid.MemberPrepaidType.values()%>" />
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
                                    <form id="form-edit" role="form" method="POST" class="form-horizontal"  action="${ctx}/memberPrepaid/saveMemberPrepaid">
                                        <input type="hidden" name="id" value="${memberPrepaid.id}">
                                        
                                        <div class="form-group">
	                                         <label class="col-md-2 control-label">充值金额:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="money" id="money" placeholder="请输入充值金额"
	                                                class="form-control" value="${memberPrepaid.money}">
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">赠送金额:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="giveMoney" id="giveMoney" placeholder="请输入赠送金额"
	                                                class="form-control" value="${memberPrepaid.giveMoney}">
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">充值描述:</label>
	                                         <div class="col-md-8">
	                                         	<textarea  name="desc" id="desc" placeholder="请输入充值描述" class="form-control" style="resize:vertical">${memberPrepaid.desc}</textarea>
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">充值类型:</label>
	                                         <div class="col-md-8">
	                                         	<select id="type" name="type" class="form-control" tabindex="1" data-select="${memberPrepaid.type}">
													<c:forEach items="${types}" var="type">
														<option value="${type.value }">${type.name}</option>
													</c:forEach>
												</select>
	                                         </div>
	                                     </div>
		                                 
                                        
                                        <a href="${ctx}/memberPrepaid" class="btn btn-sm btn-back btn-default">返回</a>

                                        <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty member.id ? "添加" : "编辑"}中...">
                                            ${empty member.id ? "添 加" : "编 辑"}
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
            </div>
        </div>
    </section>
    <c:import url="/WEB-INF/layouts/content_footer.jsp" />
</div>
<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>
<script src="${ctx}/static/js/memberPrepaid/edit.js"></script>
	

</body>

</html>