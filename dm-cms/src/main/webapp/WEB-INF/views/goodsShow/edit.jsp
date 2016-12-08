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
<c:set var="mainTitle" value="大家说管理" />
<c:set value="<%=EnumWorkage.values()%>" var="workAges"></c:set>
<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp" />
    <section>
        <div class="content-wrapper">
            <h3>${mainTitle}</h3>
			<button id="btn-order-back" class="btn btn-success" style="margin-bottom:10px;" onclick="window.history.go(-1)">返回</button>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">${subTitle}</div>
                        <div class="panel-body">
                           <form id="form-edit" role="form" method="POST" class="form-horizontal" >
	                                <div class="col-md-12">
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">会员:</label>
	                                         <div class="col-md-8">
												 <input type="text"
														class="form-control" value="${goodsShow.member.mobile}" readonly>
	                                         </div>
	                                     </div>
										<div class="form-group">
											<label class="col-md-2 control-label">商品:</label>
											<div class="col-md-8">
												<input type="text"
													   class="form-control" value="${goodsShow.goods.name}" readonly>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">评论内容:</label>
											<div class="col-md-8">
												<input type="text"
													   class="form-control" value="${goodsShow.comment}" readonly>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">推荐/普通</label>
											<div class="col-md-8">
												<input type="text" class="form-control" value=
														'<c:if test="${goodsShow.recommend == 1}">置顶</c:if><c:if test="${goodsShow.recommend == 0}">普通</c:if>'
													 readonly  />
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">审核状态:</label>
											<div class="col-md-8">
												<input type="text" class="form-control" value=
												'<c:if test="${goodsShow.status == 0}">待审核</c:if><c:if test="${goodsShow.status == 1}">已审核</c:if><c:if test="${goodsShow.status == 2}">反驳</c:if>' readonly />
											</div>
										</div>

										<%--<div class="form-group">--%>
											<%--<label class="col-md-2 control-label">评论时间</label>--%>
											<%--<div class="col-md-8">--%>
												<%--<input type="text" class="form-control"--%>
													   <%--value=<fmt:formatDate value="${goodsShow.commentTime}" pattern="yyyy-MM-dd HH:mm"/>>--%>
											<%--</div>--%>
										<%--</div>--%>

										<div class="form-group">
											<label class="col-md-2 control-label">场地得分:</label>
											<div class="col-md-8">
												<input type="text"  readonly
													   class="form-control" value="${goodsShow.shopScore}星">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">技师得分:</label>
											<div class="col-md-8">
												<input type="text"  readonly
													   class="form-control" value="${goodsShow.workerScore}星">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">商品得分:</label>
											<div class="col-md-8">
												<input type="text"  readonly
													   class="form-control" value="${goodsShow.goodsScore}星">
											</div>
										</div>
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
</body>

</html>