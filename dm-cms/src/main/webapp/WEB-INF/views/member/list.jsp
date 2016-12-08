<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.mxep.model.enums.EnumMembeRole" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="会员管理 - 达膜" />
    </c:import>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
</head>
<c:set var="roles" value="<%=EnumMembeRole.values()%>" />
<c:set var="mainTitle" value="会员管理" />
<c:set var="subTitle" value="会员列表" />

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
                        <div class="panel-body table-fit">
                            <form id="pagination-form" class="" method="POST" action="${ctx}/member">
                                <%-- <input type="hidden" name="pid" value="${pid}"> --%>
                                <div class="search-group form-inline">
                                    <input type="text" name="search_LIKE_mobile" placeholder="会员手机" class="form-control">
                                    <input type="text" name="search_LIKE_profile.nickname" placeholder="会员手机" class="form-control">
                                    <%--<select name="search_EQ_role" id="role" class="form-control">--%>
                                        <%--<option value="">请选择会员角色</option>--%>
                                        <%--<c:forEach items="${roles}" var="role">--%>
                                            <%--<option value="${role.value}">${role.name}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                    <div id="startDate" class="input-group date">
		                                 <input type="text" id="gtCreateTime" name="search_GTE_registerTime" class="form-control" placeholder="注册起始日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
	                               </div>
	                               <div id="endDate" class="input-group date">
		                                 <input type="text" id="ltCreateTime" name="search_LTE_registerTime" class="form-control" placeholder="注册结束日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
	                               </div>
                           
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                                </div>
                                <div class="action-group">
                                	<%--<a href="javascript:;" style="margin-left:15px;" onclick="exportMembers()" class="btn btn-success pull-right">导出Excel</a>--%>
                                    <a href="${ctx}/member/addMember" class="btn btn-add btn-success pull-right">添加会员</a>
                                    <a href="javascript:void(0)" class="btn btn-changeManager btn-success pull-right">转变为区域经理</a>
                                    <a href="javascript:void(0)" class="btn btn-changeShoper btn-success pull-right">转变为企业用户</a>
                                    <%--<a href="${ctx}/member/addPoints" class="btn btn-add btn-success pull-right">添加积分</a>--%>
                                    <a href="javascript:void (0)" class="btn btn-add btn-success pull-right btn-car">车</a>
                                    <%--<a href="javascript:void (0)" class="btn btn-add btn-success pull-right btn-shop">场地</a>--%>
                                    <%--<a href="javascript:void (0)" class="btn btn-add btn-success pull-right btn-shoppingCar-action">购物篮</a>--%>
                                    <a href="javascript:void (0)" class="btn btn-add btn-success pull-right btn-address">会员地址</a>
                                    <%--<a href="${ctx}/member/addBatch" class="btn btn-add btn-success pull-right">批量添加优惠券</a>--%>
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="menu" class="dropdown-menu animated swing">
                                            <li><a href="#" class="btn-edit-action">编辑</a>
                                            </li>
                                            <li class="divider"></li>
                                            <li><a href="javascript:void(0);" class="btn-disable">禁用</a>
                                            </li>
                                            <li><a href="javascript:void(0);" class="btn-enable">解禁</a>
                                            </li>
                                            
                                        </ul>
                                    </div>
                                </div>
                                <div id="pagination-body">

                                </div>
                                <input type="hidden" name="page" value="${pagination.page}">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <c:import url="/WEB-INF/layouts/content_footer.jsp" />
</div>

<!-- Modal Large   选择区域变为区域经理 -->
<div id="managerContent" tabindex="-1" role="dialog"  aria-hidden="false" class="modal fade">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" data-dismiss="modal" aria-label="Close" class="close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4  class="modal-title">转变区域经理</h4>
            </div>
            <form id="managerForm" method="POST">
                <div class="modal-body" >
                    <div class="form-group">
                        <select id="city" name="city" class="form-control" ></select>
                        <input type="hidden" id="managerIds" name="managerIds" />
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="managerCancle" type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button id="managerConfirm"  type="submit" class="btn btn-submit btn-primary modal-add solveBtn" data-loading-text="确认中...">确认</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal Large-->

<!-- Modal Large   选择区域变为企业用户-->
<div id="shoperContent" tabindex="-1" role="dialog"  aria-hidden="false" class="modal fade">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" data-dismiss="modal" aria-label="Close" class="close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4  class="modal-title">转变企业用户</h4>
            </div>
            <form id="shoperForm" method="POST">
                <div class="modal-body" >
                    <div class="form-group">
                        <select id="shop" name="shop" class="form-control" ></select>
                        <input type="hidden" id="shoperIds" name="shoperIds" />
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="shoperCancle" type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button id="shoperConfirm"  type="submit" class="btn btn-submit btn-primary modal-add solveBtn" data-loading-text="确认中...">确认</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal Large-->


<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/js/common/pagination.js"></script>
<script src="${ctx}/static/js/member/list.js"></script>
<script src="${ctx}/static/js/common/timeDay.js"></script>

</body>

</html>