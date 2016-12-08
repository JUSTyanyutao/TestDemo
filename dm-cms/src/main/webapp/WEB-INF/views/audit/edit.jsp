<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fanfou.model.goods.GoodsApproval" %>
<%@ page import="com.mxep.model.goods.Goods" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="审批商品修改管理 - 怎么吃" />
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
</style>    
</head>
<c:set var="mainTitle" value="审批商品修改管理" />
<c:set var="subTitle" value="审批申请详情" />
<!-- 申请类型 -->
<c:set var="applyTypes" value="<%=GoodsApproval.ApprovalType.values()%>" />
<!-- 审批状态 -->
<c:set var="approvalStatus" value="<%=GoodsApproval.ApprovalStatus.values()%>" />
<!--商品状态-->
<c:set var="goodsStatuses" value="<%=Goods.SaleStatus.values()%>" />
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
                           <form id="form-edit" role="form" method="POST" class="form-horizontal" action="${ctx}/goodsApproval/saveAudit">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${apply.id}">
	                                     <c:if test="${apply.status ==1}">
	                                     	<c:set var="className" value="text-info" />
	                                     </c:if>
	                                     <c:if test="${apply.status ==2}">
	                                     	<c:set var="className" value="text-success" />
	                                     </c:if>
	                                     <c:if test="${apply.status == -1}">
	                                     	<c:set var="className" value="text-danger" />
	                                     </c:if>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">审批状态:</label>
	                                         <div class="col-md-8">
									            <label class="control-label ${className}">${apply.statusName}</label>
	                                         </div>
	                                     </div>
	                                     
	                                     <%-- <div class="form-group">
	                                         <label class="col-md-2 control-label">商品选择:</label>
	                                         <div class="col-md-8">
									            <label class="control-label">${apply.goods.name}</label>
	                                         </div>
	                                     </div> --%>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">申请类型:</label>
	                                        	<div class="col-md-8">
	                                        		<label class="control-label">${apply.typeName}</label>
	                                        	</div>
	                                     </div>
	                                     
	                                     <c:if test="${apply.type == 1}">
                                     		<div class="form-group">
		                                         <label class="col-md-2 control-label">商品原编号:</label>
		                                         <div class="col-md-3">
		                                            <label class="control-label">${apply.goods.goodsSn}</label>    
		                                         </div>
		                                         <label class="col-md-2 control-label">修改后的编号:</label>
		                                         <div class="col-md-3">
		                                            <label class="control-label text-success">${apply.goodsSn}</label>    
		                                         </div>
		                                     </div>
		                                     
		                                     <div class="form-group">
		                                         <label class="col-md-2 control-label">商品原名称:</label>
		                                         <div class="col-md-3">
		                                         	<label class="control-label">${apply.goods.name}</label>  
		                                         </div>
		                                         <label class="col-md-2 control-label">修改后的名称:</label>
		                                         <div class="col-md-3">
		                                         	<label class="control-label text-success">${apply.name}</label>  
		                                         </div>
		                                     </div>
		                                     
		                                     <div class="form-group">
		                                         <label class="col-md-2 control-label">原市场价格:</label>
		                                         <div class="col-md-3">
		                                         	<label class="control-label">${apply.goods.marketPrice}/${apply.goods.marketUnit}${apply.goods.marketUnitName}</label>
		                                         </div>
		                                         
		                                         <label class="col-md-2 control-label">修改后的市场价格:</label>
		                                         <div class="col-md-3">
		                                         	<label class="control-label text-success">${apply.marketPrice}/${apply.marketUnit}${apply.marketUnitName}</label>
		                                         </div>
		                                     </div>
		                                     
		                                     <div class="form-group">
		                                         <label class="col-md-2 control-label">原田园里价格:</label>
		                                         <div class="col-md-3">
		                                         	<label class="control-label">${apply.goods.farmPrice}/${apply.goods.farmUnit}${apply.goods.farmUnitName}</label>
		                                         </div>
		                                         
		                                         <label class="col-md-2 control-label">修改后的田园里价格:</label>
		                                         <div class="col-md-3">
		                                         	<label class="control-label text-success">${apply.farmPrice}/${apply.farmUnit}${apply.farmUnitName}</label>
		                                         </div>
		                                     </div>
		                                     
		                                     <div class="form-group">
		                                         <label class="col-md-2 control-label">原净重估价:</label>
		                                         <div class="col-md-3">
		                                         	<label class="control-label">${apply.goods.price}/${apply.goods.unit}${apply.goods.unitName}</label>
		                                         </div>
		                                         <label class="col-md-2 control-label">修改后的净重估价:</label>
		                                         <div class="col-md-3">
		                                         	<label class="control-label text-success">${apply.price}/${apply.unit}${apply.unitName}</label>
		                                         </div>
		                                     </div>
		                                     
		                                     
	                                     </c:if>
	                                     
	                                     <c:if test="${apply.type == 2}">
                                     		<div class="form-group">
		                                         <label class="col-md-2 control-label">商品编号:</label>
		                                         <div class="col-md-8">
		                                            <label class="control-label">${apply.goods.goodsSn}</label>    
		                                         </div>
		                                     </div>
		                                     <div class="form-group">
		                                         <label class="col-md-2 control-label">商品名称:</label>
		                                         <div class="col-md-8">
		                                         	<label class="control-label">${apply.goods.name}</label>
		                                         </div>
		                                     </div>
		                                     
		                                     <div class="form-group">
		                                         <label class="col-md-2 control-label">商品原状态:</label>
	                                        	 <div class="col-md-3">
	                                        		 <label class="control-label">${apply.goods.status == 1 ? '上架' : '下架'}</label>
	                                        	</div>
	                                        	
	                                        	<label class="col-md-2 control-label">修改后的状态:</label>
	                                        	 <div class="col-md-3">
	                                        		 <label class="control-label text-success">${apply.gooodsStatus == 1 ? '上架' : '下架'}</label>
	                                        	</div>
		                                     </div>
		                                     
	                                     </c:if>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">申请备注:</label>
	                                         <div class="col-md-8">
	                                         	<textarea rows="3" name="presentRemark" id="presentRemark" placeholder="请输入申请备注"
	                          								class="form-control" disabled >${apply.presentRemark}</textarea>
	                                         </div>
	                                     </div>
	                                     
	                                     
	                                     
	                                     <c:if test="${!empty apply.id}">
	                                     		<div class="form-group">
			                                         <label class="col-md-2 control-label">创建时间:</label>
			                                        	<div class="col-md-8">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${apply.createTime}'/>" 
			                                        			name="createTime" id="createTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>
			                                     
			                                     <div class="form-group">
			                                         <label class="col-md-2 control-label">更新时间:</label>
			                                        	<div class="col-md-8">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${apply.updateTime}'/>" 
			                                        			name="updateTime" id="updateTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>
	                                     </c:if>
	                                     
	                                     <div class="form-group">
						                    <label class="col-md-2 control-label">审批结果:</label>
						                    <div class="col-md-8">
						                        <label class="radio-inline c-radio">
						                            <input type="radio" name="status" value="2" checked >
						                            <span class="fa fa-check"></span>审批通过</label>
						                        <label class="radio-inline c-radio">
						                            <input type="radio" name="status" value="-1" <c:if test="${apply.status == -1 }">checked</c:if> >
						                            <span class="fa fa-close"></span>拒绝申请</label>
						                            
						                    </div>
						                </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">审批备注:</label>
	                                         <div class="col-md-8">
	                                         	<textarea rows="3" name="approvedRemark" id="approvedRemark" placeholder="请输入审批备注"
	                          								class="form-control" >${apply.approvedRemark}</textarea>
	                                         </div>
	                                     </div>
	                                     
	                                     
	                                 </div>
	                            </div>
	                            
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="审批中...">
                                            审批
                                 </button>
                                 <!-- <button id="saveAgain" type="submit" class="btn btn-submit btn-sm btn-primary" data-loading-text="保存中...">
                                            审批并添加下一条
                                 </button> -->
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
<script src="${ctx}/static/js/audit/edit.js"></script>
<script type="text/javascript">
var id="${apply.id}";
var type="${apply.type}";
var status ='${apply.status}';
if(status == 2 || status == -1){
	$("input").each(function(){
		$(this).attr("disabled",true);
	});
	$("textarea").attr("disabled",true);
	$(".btn-submit").attr("disabled",true);
}



</script>

</body>

</html>