<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/WEB-INF/layouts/header.jsp">
    <c:param name="title" value="商品评论管理" />
</c:import>
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
</head>
<c:set var="mainTitle" value="商品评论管理" />
<c:set var="subTitle" value="商品评论列表" />
<style type="text/css">
    .messageTd{
        max-width: 100px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
</style>
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
                        <div class="panel-body table-fit">

                            <form id="pagination-form" method="POST" action="${ctx}/goods/comment/list">
                                <div class="search-group form-inline">
                                    <%--<input type="text" name="search_LIKE_name" placeholder="公司名称" class="form-control">--%>
                                    <%--<input type="text" name="search_LIKE_address" placeholder="技师地址" class="form-control">--%>
                                    <div id="beginDate" class="input-group date">
                                        <input type="text" name="search_GTE_createTime" class="form-control" value="<fmt:formatDate value='${defaultStartDate}' pattern='yyyy-MM-dd 00:00' />" placeholder="请输入起始日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
                                    </div>
                                    <div id="endDate" class="input-group date">
                                        <input type="text" name="search_LTE_createTime" class="form-control" value="<fmt:formatDate value='${defaultStartDate}' pattern='yyyy-MM-dd 00:00' />" placeholder="请输入结束日期">
		                                 <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
                                    </div>
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                                </div>
                                <div class="search-group form-inline">
                                    <label class="control-label">审核状态:</label>
                                    <label class="checkbox">&nbsp;
                                        <input type="checkbox" name="search_EQ_status" value="0"  />待审核  &nbsp;
                                        <input type="checkbox" name="search_EQ_status" value="1" />已审核   &nbsp;
                                        <input type="checkbox" name="search_EQ_status" value="2" />反驳
                                    </label>
                                </div>


                                <div class="action-group">
                                    <a href="javascript:void(0)" class="btn btn-solve-action btn-success pull-right">审核</a>
                                    <a href="javascript:void(0)" class="btn btn-recommend-action btn-success pull-right">置顶</a>
                                    <a href="javascript:void(0)" class="btn btn-disRecommend-action btn-success pull-right">取消置顶</a>
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="foodMarket" class="dropdown-menu animated swing">
                                            <%--<li><a href="#" class="btn-edit-action">编辑</a>--%>
                                            <%--</li>--%>
                                            <li><a href="#" class="btn-delete-action">删除</a>
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

<!-- Modal Large   审核弹框 -->
<div id="solveContent" tabindex="-1" role="dialog"  aria-hidden="false" class="modal fade">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" data-dismiss="modal" aria-label="Close" class="close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 id="myModalLabelLarge" class="modal-title">审核</h4>
            </div>
            <form id="solveForm" method="POST">
                <div class="modal-body" >
                    <div class="form-group">
                        <%--<label for="message-text" class="control-label">审核结果:</label>--%>
                        <%--<textarea name="remark"  class="form-control" id="remark"></textarea>--%>
                        <input type="hidden" id="id" name="id" />
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="cancleSolve" type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button id="rebutSolve"  type="submit"  data-loading-text="反驳中..." class="btn btn-default solveBtn">反驳</button>
                    <button id="confirmsolve"  type="submit" class="btn btn-submit btn-primary modal-add solveBtn" data-loading-text="确认中...">确认</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal Large-->

<!-- Modal-->
<div id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" data-dismiss="modal" aria-label="Close" class="close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 id="myModalLabel" class="modal-title">内容详情</h4>
            </div>
            <div class="modal-body" style="word-wrap: break-word;">...</div>
        </div>
    </div>
</div>
<!-- Modal-->


<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/js/common/pagination.js"></script>
<script src="${ctx}/static/js/goodsComment/list.js"></script>
<script src="${ctx}/static/js/common/timeDay.js"></script>

<script type="text/javascript">

    $(document).on("click",".messageContent",function(){
        $("#myModal .modal-body").html($(this).html());
    });

</script>
</body>
</html>