<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mxep.model.enums.EnumCouponStatus" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="优惠券管理 " />
    </c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link href="${ctx}/static/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
</head>
<c:set var="mainTitle" value="优惠券管理" />
<c:set var="subTitle" value="${empty coupon ? '添加优惠券' : '保存优惠券'}" />

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
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/member/coupon/save">
	                            <div class="row">
	                                <div class="col-md-12">
	                                     <input type="hidden" id="id" name="id" value="${coupon.id}">
	                                     <input type="hidden" id="memberId" name="memberId" value="${memberId}">
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">用户:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="mobile" id="mobile" 
	                                                class="form-control" value="${mobile}" disabled="disabled">
	                                         </div>
	                                     </div>
	                                     
	                                      <div class="form-group">
	                                         <label class="col-md-2 control-label">标题:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="title" id="question" placeholder="请输入优惠券标题"
	                                                class="form-control" value="${coupon.title}">
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">面额:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="denomination" id="denomination" placeholder="请输入优惠券面额"
	                                                class="form-control" value="${coupon.denomination}">
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">限制金额:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="text" name="restrictionMoney" id="restrictionMoney" placeholder="请输入优惠券限制金额"
	                                                class="form-control" value="${coupon.restrictionMoney}">
	                                        	</div>
	                                     </div>
	                                    
	                                    <%--<c:if test="${empty coupon.id}"> --%>
		                                     <%--<div class="form-group">--%>
		                                         <%--<label class="col-md-2 control-label">优惠券个数:</label>--%>
		                                        	<%--<div class="col-md-8">--%>
		                                        		<%--<input type="text" name="count" id="count" placeholder="请输入优惠券个数"--%>
		                                                <%--class="form-control" value="">--%>
		                                        	<%--</div>--%>
		                                     <%--</div>--%>
	                                     <%--</c:if>--%>
	                                    
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">过期时间:</label>
	                                        	<div class="col-md-8">
	                                        		<div id="expireDate" class="input-group date">
	                                               	 	 <c:set var="expireTime" value="<fmt:formatDate pattern='yyyy-MM-dd hh:mm' value='${coupon.expireTime}'/>"/>
						                                 <input type="text" value="${expireTime}" name="expireTime" class="form-control" placeholder="请输入过期时间">
						                                 <span class="input-group-addon">
						                                    <span class="fa fa-calendar"></span>
						                                 </span>
					                                 </div>
	                                        	</div>
	                                     </div>
	                                     
	                                      <div class="form-group">
	                                         <label class="col-md-2 control-label">描述:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="desc" id="desc" placeholder="请输入优惠券描述"
	                                                class="form-control" value="${coupon.desc}">
	                                         </div>
	                                     </div>
	                                     
	                                      <c:if test="${ !empty coupon.id}"> 
		                                     <div class="form-group">
		                                         <label class="col-md-2 control-label">状态:</label>
		                                         <div class="col-md-8">
													 <c:set value="<%=EnumCouponStatus.values()%>" var="statusList"   ></c:set>
		                                         	<select id="status" name="status" class="form-control" tabindex="1">
														<c:forEach items="${statusList}" var="status"
															varStatus="s">
															<option value="${status.value}"
																<c:if test="${status.value eq coupon.status}">selected</c:if>
															>${status.label}</option>
														</c:forEach>	
													</select>
		                                         </div>
		                                     </div>
	                                     </c:if>
	                                 </div>
	                            </div>
	                            
	                             <a class="btn btn-sm btn-primary" href="${ctx}/member/coupon/${memberId}" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty coupon ? "添加" : "保存"}中...">
                                            ${empty coupon ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/coupon/edit.js"></script>
<script type="text/javascript">
$('#expireDate').datetimepicker({
	format: 'YYYY-MM-DD HH:mm:ss',
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