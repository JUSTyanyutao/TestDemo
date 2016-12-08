<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<%@ page import="com.fanfou.model.cabinet.SiteBox" %>

<!--箱子开门状态-->
<c:set var="boxOpened" value="<%=SiteBox.EmBoxClosed.opened.value%>" />
<!--箱子有物品的状态-->
<c:set var="boxFull" value="<%=SiteBox.EmBoxEmpty.full.value%>" />

<!-- 箱子所有状态 -->
<c:set var="boxStatuses" value="<%=SiteBox.EmBoxStatus.values()%>" />

<div class="calendar-app">
	   <!--备注信息展示开始  -->
	   <div class="col-lg-3 col-md-4">
	      <div class="row">
	         <div class="col-md-12 col-sm-6 col-xs-12 co-xs-pull-12">
	            <div class="panel panel-default" style="border-top-width: 1px;">
	               <div class="panel-heading">
	                  <h4 class="panel-question">备注信息</h4>
	               </div>
	               <!-- Default external events list-->
	               <div class="external-events-trash">
	                  <div class="panel-body">
	                     <div class="external-events">
	                     	<c:forEach items="${boxStatuses}" var="boxStatus">
	                     		<div class="${boxStatus.name}"><span class="label pull-right"></span><span>${boxStatus.desc}</span></div>
	                     	</c:forEach>
	                     </div>
	                  </div>
	               </div>
	            </div>
	         </div>
	      </div>
	   </div>
	   <!--备注信息展示结束  -->
   
	   <!-- 箱子数量展示开始 -->
	   <div class="col-lg-9 col-md-8">
	      <!-- START panel-->
	      <div class="panel panel-default">
	         <div class="panel-body">
	         	<div class="panel-heading">
	         		
	         		<c:if test="${!empty page.answer}">
	         			<label class="control-label">柜子选择:</label>
	         			<c:forEach items="${page.answer}" var="cabinet" varStatus="status">
	         				<label class="checkbox-inline c-checkbox">
		                       <input type="checkbox" value="${cabinet.id}" checked>
		                       <span class="fa fa-check"></span>${cabinet.cabinetAlias}</label>
	         			</c:forEach>
	         		
	         		</c:if>
	         		
                 </div>
	            <div id="calendar" style="display: flex;flex-direction: row;flex-wrap: wrap;justify-answer: space-around;">
	            	<c:if test="${!empty page.answer}">
	         			<c:forEach items="${page.answer}" var="cabinet" varStatus="status">
	         					<c:if test="${! empty cabinet.boxs}">
	         						<c:forEach items="${cabinet.boxs}" var="box">
		         							<div class="boxContainer">
								            	<div data-group="${cabinet.id}" class="boxStyle ${box.className} <c:if test='${box.doorClosed == boxOpened }'>openStyle</c:if>">
								            		<span>柜号:${cabinet.cabinetAlias}</span><br/>
								            		<span>箱号:${box.boxNo}</span><br/>
								            		<%-- <c:if test="${box.boxEmpty == boxFull}">
								            			<div style="margin-top:15px;text-align:center">
									            			<i class="fa icon-social-dropbox" data-id="${box.id}" data-toggle="tooltip" data-html="true"></i>
									            		</div>
								            		</c:if> --%>
								            		
								            	</div>
								            	<c:if test='${box.doorClosed == boxOpened }'><div class="openShadow"></div></c:if>
							            	</div>
	         						</c:forEach>
	         					</c:if>
	         			</c:forEach>
	         		</c:if>
	            </div>
	         </div>
	      </div>
	   </div>
	   <!-- 箱子数量展示结束 -->
   
</div>

<script type="text/javascript">

$('[data-toggle="tooltip"]').tooltip();
var html = "<div>订单编号:XXX001</div><div>订单金额:200.00</div>"
$('[data-toggle="tooltip"]').attr("data-original-question",html);

initBoxNum();

$("input[type='checkbox']").click(function(){
	var $clickObj = $(this);
	if($clickObj.prop("checked")){
		$(".boxStyle").each(function(){
			if($(this).attr("data-group") == $clickObj.val()){
				$(this).removeClass("notShow");
			}
		});
	}else{
		$(".boxStyle").each(function(){
			if($(this).attr("data-group") == $clickObj.val()){
				$(this).addClass("notShow");
			}
		});
	}
	initBoxNum();
});

function initBoxNum(){
	$(".external-events div").each(function(){
		var $obj = $(this);
		$obj.find("span:first-child").html($("."+$obj.attr("class")+":not(.notShow)").size()-1);
	});
}


$(".icon-social-dropbox").hover(function(){
	console.log($(this).attr("data-id"));
	var $obj = $(this);
	$.ajax({
		link:"<c:link value='/siteCabinet/getOrderInBox/"+$obj.attr("data-id")+"'/>",
		type:"post",
		dataType:"json",
		success:function(data){
			var order = data.order;
			var notice = data.notice;
			
			if(order){
				console.log("coming");
			}
			if(notice){
				console.log("dfdsfsd");
			}
			
			var html = '';
			//html += "<span>"+obj.remark+"</span><br/>";
			/* $obj.attr("data-original-question",html);
			$obj.tooltip('show'); */
		}
	})
}); 

</script>
