<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="优惠券管理 - 怎么吃" />
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
							<form id="ajaxform" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/coupon/export" target="frameFile">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="col-md-2 control-label">批量导入用户:</label>
											<div class="col-md-4">
													<input type="file" name="file" accept="application/msexcel"  id="inputfile">
											</div>
											<div class="col-md-4">
												<div class="has-feedback has-success" >
													<label class="control-label" id="divInfo">

													</label>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
                           <form id="form-edit" role="form" method="POST"  enctype="multipart/form-data" class="form-horizontal" action="${ctx}/member/coupon/saveBatch">
	                            <div class="row">
	                                <div class="col-md-12">
	                                      <div class="form-group">
	                                         <label class="col-md-2 control-label">标题:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="question" id="question" placeholder="请输入优惠券标题"
	                                                class="form-control" >
	                                         </div>
	                                     </div>
										<input type="hidden"  name="memberPhone" value="">
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">面额:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="denomination" id="denomination" placeholder="请输入优惠券面额"
	                                                class="form-control" >
	                                         </div>
	                                     </div>
	                                     
	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">限制金额:</label>
	                                        	<div class="col-md-8">
	                                        		<input type="text" name="restrictionMoney" id="restrictionMoney" placeholder="请输入优惠券限制金额"
	                                                class="form-control" >
	                                        	</div>
	                                     </div>

	                                     <div class="form-group">
	                                         <label class="col-md-2 control-label">过期时间:</label>
	                                        	<div class="col-md-8">
	                                        		<div id="expireDate" class="input-group date">
	                                               	 	 <c:set var="expireTime" />
						                                 <input type="text"  name="expireTime" class="form-control" placeholder="请输入过期时间">
						                                 <span class="input-group-addon">
						                                    <span class="fa fa-calendar"></span>
						                                 </span>
					                                 </div>
	                                        	</div>
	                                     </div>
	                                     
	                                      <div class="form-group">
	                                         <label class="col-md-2 control-label">描述:</label>
	                                         <div class="col-md-8">
	                                         	<input type="text" name="descpt" id="descpt" placeholder="请输入优惠券描述"
	                                                class="form-control" >
	                                         </div>
	                                     </div>
	                                     
	                                 </div>
	                            </div>
	                            
	                             <a class="btn btn-sm btn-primary" href="${ctx}/member/list" role="button">返回</a>
	                             <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty coupon ? "添加" : "保存"}中...">
                                            ${empty coupon ? "添 加" : "保存"}
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
<script src="${ctx}/static/js/coupon/batchEdit.js"></script>
<script src="${ctx}/static/js/coupon/jquery.form.js"></script>E
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
  
$(function () {
	$("#inputfile").change(function(){
			var options = {
			//target: '#output',          //把服务器返回的内容放入id为output的元素中
			//beforeSubmit: showRequest,  //提交前的回调函数
			//提交后的回调函数
//			link: ctx + "/coupon/export",                 //默认是form的action， 如果申明，则会覆盖
			type: "post",               //默认是form的method（get or post），如果申明，则会覆盖
			data:$('#ajaxform').serialize(),
			dataType: "text",           //html(默认), xml, script, json...接受服务端返回的类型
			//clearForm: true,          //成功提交后，清除所有表单元素的值
			//resetForm: true,          //成功提交后，重置所有表单元素的值
			timeout: 3000,		   //限制请求的时间，当请求大于3秒后，跳出请求
			success: function(responseText, statusText){
				alert(responseText+":"+statusText);
			}
		};

		/*$("#ajaxform").ajaxForm({
			link: "/coupon/export",
			type:"post",
			enctype:'multipart/form-data',
			data:$('#ajaxform').serialize(),
			success:function(data, statusText) {
				alert(data);
			},error:function(data) {
				alert("出错！");
			}
		});*/
		$('#ajaxform').submit();
	});
});

function save(){
	if(memberPhones.length==0){
		alert("请先导入数据");
		return;
	}else {
		$.ajax({
			link: ctx + "/member/enable",
			type: "POST",
			data: {
				memberPhones: memberPhones
			},
			dataType:"json",
			success: function(data) {
				if (data.code == 0) {
					Dialog.success(data.msg);
					Pagination.reload();
				}
			}
		})
	}

}
var memberPhones = [];
function uploadOnload() {
    var body = $(window.frames['frameFile'].document.body);   // iframe中的body
    var data = body.context.textContent;
    var dataJson;
	var j = 0 ;
    if(data.length!=0){

        dataJson = JSON.parse(data);
        for(var i=1; i<dataJson.length; i++)
        {
        	if(dataJson[i][0].length>0){
				j++;
				memberPhones.push(dataJson[i][0]);
				//
			}
        }
    }
    if(j!=0){
		$("#form-edit").append('<input type="hidden"  name="memberPhone" value="'+memberPhones+'">');
		$("#divInfo").html("你已导入 "+j+" 条数据");
	}

}
</script>
<iframe id='frameFile' name='frameFile' style='display:none;' onload="uploadOnload();">

</iframe>
</body>

</html>