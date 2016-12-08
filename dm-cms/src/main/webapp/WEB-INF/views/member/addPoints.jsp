<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="会员管理 - 怎么吃" />
    </c:import>
    <link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
    <link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
    
    
</head>

<c:set var="mainTitle" value="会员管理" />
<c:set var="subTitle" value="${empty member.id ? '添加积分' : '保存'}" />

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
                                    <form id="form-edit" role="form" method="POST" class="form-horizontal"  action="${ctx}/member/savePoints">
                                       <div class="row">
		                                  	<div class="col-md-10">
				                                <div class="form-group">
				                                <label class="col-sm-3 control-label">添加方式:</label>
		                                            <div class="col-sm-9">
		                                            <label class="radio-inline c-radio">
		                                                <input type="radio" name="selectWay" value="1" checked >
		                                                <span class="fa fa-circle"></span>多选
		                                            </label>
		                                            <label class="radio-inline c-radio">
		                                                <input type="radio" name="selectWay" value="2" >
		                                                <span class="fa fa-circle"></span>全部
		                                            </label>
		                                            </div>
		                                        </div>
		                                  	</div>
		                               </div>
                                        
                                       <div class="row" id = "memberMobile">
		                                  	<div class="col-md-10">
				                                <div class="form-group">
				                                      <label class="col-sm-3 control-label">手机号码:</label>
				                                      <div class="col-sm-9">
                                                      <input type="hidden" id="mobile" name="mobile" class="form-control" />
	                                        		    <select id="mobileList" name="mobileList" class="form-control" style="width:100%">
                                                        </select>				                                      </div>
				                                </div>
		                                  	</div>
		                               </div>
		                               
		                                <div class="row">
		                                  	<div class="col-md-10">
				                                <div class="form-group">
				                                <label class="col-sm-3 control-label">操作方式:</label>
		                                            <div class="col-sm-9">
		                                            <label class="radio-inline c-radio">
		                                                <input type="radio" name="handleWay" value="1" checked >
		                                                <span class="fa fa-circle"></span>增加
		                                            </label>
		                                            <label class="radio-inline c-radio">
		                                                <input type="radio" name="handleWay" value="2" >
		                                                <span class="fa fa-circle"></span>扣除
		                                            </label>
		                                            </div>
		                                        </div>
		                                  	</div>
		                               </div>
		                               
		                               <div class="row">
		                                  	<div class="col-md-10">
				                                <div class="form-group">
				                                      <label class="col-sm-3 control-label">积分:</label>
				                                      <div class="col-sm-9">
				                                      	<input  placeholder="请输入积分"  id="totalPoints" name="totalPoints" class="form-control" type="text">
				                                      </div>
				                                </div>
		                                  	</div>
		                               </div>
		                               
		                               <br/>
                                        
                                       <a href="${ctx}/member" class="btn btn-sm btn-back btn-default">返回</a>
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
<script src="${ctx}/static/js/member/points.js"></script>
<script type="text/javascript">
</script>
</body>
</html>