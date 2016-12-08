<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fanfou.model.goods.GoodsApproval" %>
<%@ page import="com.mxep.model.goods.Goods" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="申请商品修改管理 - 怎么吃" />
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
<c:set var="mainTitle" value="申请商品修改管理" />
<c:set var="subTitle" value="${empty goodsSpecials ? '添加申请' : '编辑申请'}" />
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
                           <form id="form-edit" role="form" method="POST" class="form-horizontal" action="${ctx}/goodsApproval/saveApply">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${apply.id}">
	                                     <input type="hidden" id="status" name="status" value="${apply.status}">
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">商品选择:</label>
	                                         <div class="col-md-8">
									            <select id="goodsSelect" name="goods.id" style="width:100%" data-select="${apply.goods.id}" <c:if test="${not empty apply.id }">disabled</c:if> ></select>
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">申请类型:</label>
	                                        	<div class="col-md-8">
	                                        		<select id="type" name="type" class="form-control" data-select="${empty apply ? 1 : apply.type}">
	                                        			<c:forEach items="${applyTypes}" var="applyType">
	                                        				<option value="${applyType.value}">${applyType.label}</option>
	                                        			</c:forEach>
	                                        		</select>
	                                        	</div>
	                                     </div>
	                                     
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">商品编号:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="goodsSn" id="goodsSn" placeholder="请输入商品编号"
	                                                class="form-control" value="${apply.goodsSn}">
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">商品名称:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="name" id="name" placeholder="请输入商品名称"
	                                                class="form-control" value="${apply.name}">
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">商品市场价格:</label>
	                                         
	                                         <div class="col-md-3">
	                                         	<input type="text" name="marketPrice" id="marketPrice" placeholder="请输入商品市场价格"
	                                                class="form-control" value="${apply.marketPrice}">
	                                         </div>
	                                         
	                                         <div class="col-md-3">
	                                         	<input type="text" name="marketUnit" id="marketUnit" placeholder="请输入单位数量"
	                                                class="form-control" value="${apply.marketUnit}">
	                                         </div>
	                                         <div class="col-md-2">
												<input type="text" name="marketUnitName" id="marketUnitName" placeholder="请输入单位名称"
	                                                class="form-control" value="${apply.marketUnitName}">
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">商品田园里价格:</label>
	                                         
	                                         <div class="col-md-3">
	                                         	<input type="text" name="farmPrice" id="farmPrice" placeholder="请输入商品田园里价格"
	                                                class="form-control" value="${apply.farmPrice}">
	                                         </div>
	                                         
	                                         <div class="col-md-3">
	                                         	<input type="text" name="farmUnit" id="farmUnit" placeholder="请输入单位数量"
	                                                class="form-control" value="${apply.farmUnit}">
	                                         </div>
	                                         <div class="col-md-2">
												<input type="text" name="farmUnitName" id="farmUnitName" placeholder="请输入单位名称"
	                                                class="form-control" value="${apply.farmUnitName}">
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">商品净重估价:</label>
	                                         
	                                         <div class="col-md-3">
	                                         	<input type="text" name="price" id="price" placeholder="请输入商品净重估价"
	                                                class="form-control" value="${apply.price}">
	                                         </div>
	                                         
	                                         <div class="col-md-3">
	                                         	<input type="text" name="unit" id="unit" placeholder="请输入单位数量"
	                                                class="form-control" value="${apply.unit}">
	                                         </div>
	                                         <div class="col-md-2">
												<input type="text" name="unitName" id="unitName" placeholder="请输入单位名称"
	                                                class="form-control" value="${apply.unitName}">
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">商品状态:</label>
	                                        	<div class="col-md-8">
	                                        		<select id="gooodsStatus"  name="gooodsStatus" class="form-control" data-select="${apply.gooodsStatus}">
														<c:forEach items="${goodsStatuses}" var="gStatus">
															<c:if test="${gStatus.value != -1 }">
																<option value="${gStatus.value }">${gStatus.label}</option>
															</c:if>
														</c:forEach>
													</select>
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">申请备注:</label>
	                                         <div class="col-md-8">
	                                         	<textarea rows="3" name="presentRemark" id="presentRemark" placeholder="请输入申请备注"
	                          								class="form-control" >${apply.presentRemark}</textarea>
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
	                                     
	                                     
	                                 </div>
	                            </div>
	                            
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty apply ? "添加" : "保存"}中...">
                                            ${empty apply ? "添 加" : "保存"}
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
        
		
    </section>
    <c:import url="/WEB-INF/layouts/content_footer.jsp" />
</div>
<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>

<script src="${ctx}/static/js/apply/edit.js"></script>
<script type="text/javascript">
var id="${apply.id}";
var type="${apply.type}";
var status ='${apply.status}';
</script>

</body>

</html>