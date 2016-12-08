<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="参数管理" />
    </c:import>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    	.messageTd{
    		    max-width: 200px;
			    white-space: nowrap;
			    overflow: hidden;
			    text-overflow: ellipsis;
    	}
    </style>
</head>

<c:set var="mainTitle" value="参数管理" />
<c:set var="subTitle" value="参数列表" />

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
                            <form id="pagination-form" class="" method="POST" action="${ctx}/param/list">
                                <%-- <input type="hidden" name="pid" value="${pid}"> --%>
                                <div class="search-group form-inline">
                                    <input type="text" name="search_LIKE_paramDesc" placeholder="要搜索的参数名称" class="form-control">
                                    <input type="text" name="search_LIKE_key" placeholder="要搜索的参数变量" class="form-control">
                                    <input type="text" name="search_LIKE_value" placeholder="要搜索的参数值" class="form-control">
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                                </div>
                                <div class="action-group">
                                    <a href="javascript:void(0);" class="btn btn-add btn-success pull-right">添加</a>
                                     <%--<a href="${ctx}/cityMessage/addCm" class="btn btn-add btn-success pull-right">添加</a>--%>
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="menu" class="dropdown-menu animated swing">
                                            <!-- <li><a href="#" class="btn-edit-action">处理</a>
                                            </li> -->
                                            <li><a href="#" class="btn-edit-action">编辑</a>
                                            </li>
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

<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/js/common/pagination.js"></script>
<script src="${ctx}/static/js/param/list.js"></script>
<script type="text/javascript">
	
	$(document).on("click",".messageContent",function(){
		$("#myModal .modal-body").html($(this).html());
	}); 

</script>
</body>

</html>