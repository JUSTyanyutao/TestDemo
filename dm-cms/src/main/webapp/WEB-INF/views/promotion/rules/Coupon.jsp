<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mxep.model.GlobalConstant" %>
<%@ page import="com.mxep.model.common.Promotion" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<body>


	<!--添加参与活动的商品  -->
                           <div class="form-group"> 
                           		<div class="col-md-1"> </div>  
                           		<div class="col-md-9">    
					                 <div class="clearfix">
					                    <div class="pull-left">
					                       <h4>活动规则</h4>
					                    </div>
					                    <div class="btn-group pull-right">
					                       <button id="addRules" type="button" class="btn btn-default btn-sm" data-template="#rules-default-template">
					                          <em class="fa fa-plus text-gray-dark"></em>
					                       </button>
					                       
					                    </div>
					                 </div>
               
			                 <div class="panel panel-default">
			                    <div class="panel-body">
			                    	<div id="rules-default-template" class="panel panel-default panel-demo rules ">
			                    		<div class="row">
			                    			<div  style="height:20px;">
			                   										<a href="javascript:;" data-tool="panel-dismiss" class="pull-right cancle">
			                             			<em class="fa fa-times"></em>
			                          			</a>
			               										</div>
			                    		</div>
			                    		
			                    		<div class="form-group">
			                                      <label class="col-md-2 control-label">标题:</label>
			                                     	<div class="col-md-8">
			                                     		<input type="text" data-msg-required="请输入标题" name="rule_title[0]" placeholder="请输入规则标题"
			                                             class="form-control required">
			                                     	</div>
			                                  </div>
			                                 
			                                  <div class="form-group">
			                                      <label class="col-md-2 control-label">描述:</label>
			                                     	<div class="col-md-8">
			                                     		<textarea  name="rule_desc[0]"  placeholder="请输入规则描述"
			                                             class="form-control"></textarea>
			                                     	</div>
			                                  </div>
			                                  
			                                   <div class="form-group">
			                                      <label class="col-md-2 control-label">满足订单金额(下单金额):</label>
			                                     	<div class="col-md-8">
			                                     		<input  name="orderMoney"  type="text" data-msg-required="请输入订单金额" placeholder="请输入订单金额" data-msg-digits="订单金额只能是整数"
			                                             class="form-control required digits">
			                                     	</div>
			                                     	若为注册,则填写0
			                                  </div>
			                                  
			                                   <div class="form-group">
			                                      <label class="col-md-2 control-label">面额:</label>
			                                     	<div class="col-md-3">
			                                     		<input type="text" data-msg-required="请输入面额" data-msg-plusPrice="面额只能是正数" name="rule_denomination[0]" placeholder="请输入规则面额"
			                                             class="form-control amountVal">
			                                     	</div>
			                                    	<label class="col-md-2 control-label">限制金额:</label>
			                                     	<div class="col-md-3">
			                                     		<input type="text" data-msg-required="请输入限制金额" data-msg-plusPrice="限制金额只能是正数" name="rule_restrictionMoney[0]" placeholder="请输入规则限制金额"
			                                             class="form-control amountVal">
			                                     	</div>	
			                                     	
			                                  </div>
			                    		
			                    		 <div class="form-group">
			                    				<label class="col-md-2 control-label">有效期(天数):</label>
			                    				<div class="col-md-3">
			                                     		<input type="text" data-msg-required="请输入有效期" data-msg-digits="有效期只能是正整数"  name="rule_expired[0]"  placeholder="请输入规则有效期"
			                                             class="form-control numVal">
			                                     	</div>	
			                    				<label class="col-md-2 control-label">数量:</label>
			                                     	<div class="col-md-4">
				                     				<div class="col-md-2" style="position:relative;top:2px;">
					                     				<button type="button" class="btn btn-default btn-sm quantityMin">
								                           <em class="fa fa-minus text-gray-dark"></em>
								                        </button>
							                        </div>
							                        <div class="col-md-5">
							                        	<input type="text"  class="form-control quantity" value="1">
							                        </div>
							                        <div class="col-md-2" style="position:relative;top:2px;right:23px;">
								                        <button type="button" class="btn btn-default btn-sm quantityAdd">
								                           <em class="fa fa-plus text-gray-dark"></em>
								                        </button>
							                        </div>
			                                      	 </div>
			                    		 </div>
			                    	</div>
			                    </div>
			                 </div>
              	 </div>
               </div>
<!--添加活动规则  -->



</body>