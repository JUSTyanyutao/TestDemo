<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="商品评论管理 - 怎么吃" />
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

<c:set var="mainTitle" value="商品评论管理" />
<c:set var="subTitle" value="商品评论列表" />

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
                            <form id="pagination-form" class="" method="POST" action="${ctx}/comments">
                                <input type="hidden" name="parentId" value="${parentId}">
                                <div class="search-group form-inline">
                                    <input type="text" name="search_LIKE_member_mobile" placeholder="评论人联系方式" class="form-control">
                                    <input type="text" name="search_LIKE_goods_name" placeholder="商品名称" class="form-control">
                                     <select name="search_EQ_status" class="form-control" style="width:17%">
										<option value="">请选择审核状态</option>
										<option value="1">未受理</option>
										<option value="2">已受理</option>
									 </select>
                                    
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                                </div>
                                <div class="action-group">
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="menu" class="dropdown-menu animated swing">
                                            <!-- <li><a href="#" class="btn-edit-action">处理</a>
                                            </li> -->
                                            <li><a href="#" class="btn-solve-action">处理</a>
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

<!-- Modal Large  -->
		<div id="solveContent" tabindex="-1" role="dialog"  aria-hidden="false" class="modal fade">
		   <div class="modal-dialog modal-md">
		      <div class="modal-answer">
		         <div class="modal-header">
		            <button type="button" data-dismiss="modal" aria-label="Close" class="close">
		               <span aria-hidden="true">&times;</span>
		            </button>
		            <h4 id="myModalLabelLarge" class="modal-question">处理商品评论</h4>
		         </div>
		         <form id="solveForm" method="POST">
			         <div class="modal-body" >
			         		
						          <div class="form-group">
						            <label for="message-text" class="control-label">评论结果:</label>
						            <textarea name="result"  class="form-control" id="result"></textarea>
						          </div>
					         
			         </div>
			         <div class="modal-footer">
			            
			            <button id="cancleSolve" type="button" data-dismiss="modal" class="btn btn-default">取消</button>
			            <button id="confirmsolve" type="submit" class="btn btn-submit btn-primary modal-add" data-loading-text="确认中...">确认</button>
			         </div>
		         </form> 
		      </div>
		   </div>
		</div>
		<!-- Modal Large-->


<!-- Modal-->
   <div id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" class="modal fade">
      <div class="modal-dialog">
         <div class="modal-answer">
            <div class="modal-header">
               <button type="button" data-dismiss="modal" aria-label="Close" class="close">
                  <span aria-hidden="true">&times;</span>
               </button>
               <h4 id="myModalLabel" class="modal-question">评论内容详情</h4>
            </div>
            <div class="modal-body" style="word-wrap: break-word;">...</div>
         </div>
      </div>
   </div>
   <!-- Modal-->
   
  <div class="modal fade" aria-hidden="true" aria-labelledby="myModalLabelLarge" role="dialog" tabindex="-1" id="showPics" style="display: none;">
      <div class="modal-dialog modal-lg">
         <div class="modal-answer">
            <div class="modal-header">
               <button class="close" aria-label="Close" data-dismiss="modal" type="button">
                  <span aria-hidden="true">×</span>
               </button>
               <h4 class="modal-question">图片预览</h4>
            </div>
            <div class="modal-body" style="height:350px;">
            <div id="showPicsContainer">
            </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" data-dismiss="modal" type="button">关闭</button>
            </div>
         </div>
      </div>
   </div>


<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/js/common/pagination.js"></script>
<script src="${ctx}/static/js/comment/list.js"></script>
<script type="text/javascript">
	
	$(document).on("click",".messageContent",function(){
		$("#myModal .modal-body").html($(this).html());
	}); 

</script>
</body>

</html>