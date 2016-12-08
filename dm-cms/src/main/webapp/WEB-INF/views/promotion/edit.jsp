<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mxep.model.GlobalConstant" %>
<%@ page import="com.mxep.model.common.Promotion" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="优惠活动管理 - 怎么吃" />
    </c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/third/bootstrap-tagsinput/dist/bootstrap-tagsinput.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
<style type="text/css">
    .uploadify-button {
        border: none;
        padding: 0;
    }
    .rules{
	    	padding:5px 20px 0px;
	    	margin-top:5px;
	    	min-height:200px;
	    }
    .has-feedback .form-control{
    	padding-right:0px;
    }
    .errBorder{
	    	border:1px solid red;
	    }
	.active > div{
		position: relative;
	    display: block;
	    padding: 10px 15px;
	    border-radius: 4px;
	    color: #ffffff;
    	background-color: #5d9cec;
	}  
	.goodsQuantity{
	    color: #ffffff;
	}  
	.form-horizontal .editable{
		padding-top:0px;
	} 
	.active{
		padding:5px;
	} 
	#goodsWrapper{
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
		justify-answer: space-around;
		padding-left:40px
	}
	.active .label{
		position:relative;left:6px;
	}
	
	.popover-question{
		background-color:#5d9cec
	}
	#goodsSelect-error{
		margin-left:155px;
	}
	
	    
	
	    
</style>    
</head>
<c:set var="mainTitle" value="优惠活动管理" />
<c:set var="subTitle" value="${empty promotion ? '添加优惠活动' : '编辑优惠活动'}" />
<!--优惠活动状态-->
<c:set var="promotionStatuses" value="<%=GlobalConstant.Status.values()%>" />
<!--优惠活动类型-->
<c:set var="promotionTypes" value="<%=Promotion.PromotionType.values()%>" />
<!--奖品类型-->
<c:set var="rewardTypes" value="<%=Promotion.RewardType.values()%>" />

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
                           <form id="form-edit" role="form" method="POST" class="form-horizontal" action="${ctx}/promotion/savePromotion">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${promotion.id}">
	                                     <input type="hidden" id="rules" name="rules" value="${promotion.rules}">
	                                     <c:if test="${ not empty promotion }">
	                                     	<input type="hidden" name="promotionType" value="${promotion.promotionType}">
	                                     	<input type="hidden" name="rewardType" value="${promotion.rewardType}">
	                                     </c:if>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">活动名称:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="text" name="name" id="name" placeholder="请输入活动名称"
	                                                class="form-control" value="${promotion.name}">
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">活动类型:</label>
	                                        	<div class="col-md-8">
	                                                <select <c:if test="${ not empty promotion }">disabled</c:if> class="form-control" name="promotionType" id="promotionType" data-select="${empty promotion ? 1 : promotion.promotionType }">
	                                                	<c:forEach items="${ promotionTypes}" var="promotionType">
	                                                			<option data-flag="${promotionType.flag}" value="${promotionType.value }">${promotionType.label}</option>
	                                                	</c:forEach>
	                                                </select>
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">奖品类型:</label>
	                                        	<div class="col-md-8">
	                                        		<select <c:if test="${ not empty promotion }">disabled</c:if> class="form-control" name="rewardType" id="rewardType" data-select="${promotion.rewardType}">
	                                                	<c:forEach items="${rewardTypes}" var="rewardType">
	                                                			<option value="${rewardType.value }">${rewardType.label}</option>
	                                                	</c:forEach>
	                                                </select>
	                                        	</div>
	                                     </div>
	                                     
	                                     <div id="ruleContent"></div>
	                                     
	                                     <div class="form-group">
	                                         	<label class="col-md-2 control-label">开始时间:</label>
	                                        	<div class="col-md-8">
	                                               	 <div id="beginDate" class="input-group date">
	                                               	 	 <c:set var="startTime" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm' value='${promotion.startTime}'/>"/>
						                                 <input type="text" value="${startTime}" name="startTime" class="form-control" placeholder="请输入起始时间">
						                                 <span class="input-group-addon">
						                                    <span class="fa fa-calendar"></span>
						                                 </span>
					                                 </div>
	                                        	</div>
	                                     </div>
	                                     
	                                    <div class="form-group">
	                                         	<label class="col-md-2 control-label">结束时间:</label>
	                                        	<div class="col-md-8">
	                                               	 <div id="endDate" class="input-group date">
	                                               	 	<c:set var="endTime" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm' value='${promotion.endTime}'/>"/>
						                                 <input type="text" value="${endTime}" name="endTime" class="form-control" placeholder="请输入结束时间">
						                                 <span class="input-group-addon">
						                                    <span class="fa fa-calendar"></span>
						                                 </span>
					                                 </div>
	                                        	</div>
	                                     </div>
	                                     
	                                     <c:if test="${!empty promotion.id}">
	                                     		<div class="form-group">
			                                         <label class="col-md-2 control-label">创建时间:</label>
			                                        	<div class="col-md-8">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${promotion.createTime}'/>" 
			                                        			name="createTime" id="createTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>
			                                     
			                                     <div class="form-group">
			                                         <label class="col-md-2 control-label">更新时间:</label>
			                                        	<div class="col-md-8">
			                                        		<input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm:ss' value='${promotion.updateTime}'/>" 
			                                        			name="updateTime" id="updateTime" class="form-control" readonly>
			                                        	</div>
			                                     </div>
	                                     </c:if>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">活动状态:</label>
	                                        	<div class="col-md-8">
	                                        		<select id="status" name="status" class="form-control" tabindex="1" data-select="${promotion.status}">
														<c:forEach items="${promotionStatuses}" var="promotionStatus">
															<option value="${promotionStatus.value }">${promotionStatus.name}</option>
														</c:forEach>
													</select>
	                                        	</div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                            <label class="col-sm-2 control-label">活动排序:</label>
	                                            <div class="col-sm-8">
		                                            <input id="sort" name="sort" data-ui-slider="" type="text" value="20" data-slider-min="1"
		                                                   data-slider-max="20" data-slider-step="1" data-slider-value="${promotion.sort}"
		                                                   data-slider-orientation="horizontal" class="slider slider-horizontal"
		                                                   data="value: '20'" style="display: none;">
	                                            </div>
	                                     </div>
	                                     
	                                 </div>
	                            </div>
	                            
	                             <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty goods ? "添加" : "保存"}中...">
                                            ${empty goods ? "添 加" : "保存"}
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
<script id="coupon-html" type="text/template">
	<c:import url="/WEB-INF/views/promotion/rules/Coupon.jsp" />
</script>
<script id="fish-html" type="text/template">
	<c:import url="/WEB-INF/views/promotion/rules/Fish.jsp" />
</script>
<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>
<script src="${ctx}/static/third/bootstrap-tagsinput/dist/bootstrap-tagsinput.min.js"></script>
<script src="${ctx}/static/js/promotion/edit.js"></script>

<script src="${ctx}/static/js/promotion/rules/Coupon.js"></script>
<script src="${ctx}/static/js/promotion/rules/Fish.js"></script>



<script type="text/javascript">

var id = '${promotion.rules}';
var rules = '${promotion.rules}';

/***活动类型更改***/
$("#promotionType").on("change",function(){
	
	$("#rewardType").val($(this).find("option:selected").attr("data-flag")).trigger("change");
});


/*****奖品类型更改*********/
$("#rewardType").on("change",function(){
		var rewardVal = $(this).val();
		if(rewardVal == 1){
			$("#ruleContent").html($("#coupon-html").html());
			rulesCoupon.init(rules);
		}else if(rewardVal == 2){
			$("#ruleContent").html($("#fish-html").html());
			rulesFish.init(rules);
		}
});


$('#beginDate').datetimepicker({
	format: 'YYYY-MM-DD HH:mm',
	icons: {
        time: 'fa fa-clock-o',
        date: 'fa fa-calendar',
        up: 'fa fa-chevron-up',
        down: 'fa fa-chevron-down',
        previous: 'fa fa-chevron-left',
        next: 'fa fa-chevron-right',
        today: 'fa fa-crosshairs',
        clear: 'fa fa-trash',
        
      }
  });
  
$('#endDate').datetimepicker({
	format: 'YYYY-MM-DD HH:mm',
	icons: {
        time: 'fa fa-clock-o',
        date: 'fa fa-calendar',
        up: 'fa fa-chevron-up',
        down: 'fa fa-chevron-down',
        previous: 'fa fa-chevron-left',
        next: 'fa fa-chevron-right',
        today: 'fa fa-crosshairs',
        clear: 'fa fa-trash',
        
      }
  });


</script>
</body>

</html>