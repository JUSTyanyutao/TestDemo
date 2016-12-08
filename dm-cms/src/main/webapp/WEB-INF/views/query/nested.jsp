<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>

<!-- <div class="row" style="padding:10px">
               <div class="col-sm-2">
                  START panel
                  <div class="panel panel-primary">
                     <div class="panel-heading">
                        <div class="row">
                           <div class="col-xs-4">
                              <img src="http://192.168.1.180:88/img/a/81/b9/a81b956937508a0f38.jpg" class='media-object img-responsive img-circle'/>
                           </div>
                           <div class="col-xs-8">
                              <div class="text-sm">蔬菜豆菇</div>
                           </div>
                        </div>
                     </div>
                     END panel
                  </div>
               </div>
               
               <div class="col-sm-2">
                  START panel
                  <div class="panel panel-danger">
                     <div class="panel-heading">
                        <div class="row">
                           <div class="col-xs-4">
                              <img src="http://192.168.1.180:88/img/a/81/b9/a81b956937508a0f38.jpg" class='media-object img-responsive img-circle'/>
                           </div>
                           <div class="col-xs-8">
                              <div class="text-sm">厨房用品</div>
                           </div>
                        </div>
                     </div>
                     END panel
                  </div>
               </div>
               
            </div>
            END row -->
            
		
		
		<!--备注信息展示开始  -->
	               <div class="panel-heading">
	                  <h4 class="panel-question"></h4>
	               </div>
	               <!-- Default external events list-->
	               <div class="external-events-trash">
	                  <div class="panel-body">
                  <!-- START panel tab-->
                  <div role="tabpanel" class="panel panel-transparent">
                     <!-- Nav tabs-->
                     <ul role="tablist" class="nav nav-tabs nav-justified">
                     	<c:if test="${not empty orderGoodsQuerys}">
                     		<c:forEach items="${ orderGoodsQuerys}" var="orderGoodsQuery" varStatus="num">
                     			<li role="presentation" <c:if test="${num.index == 0 }">class=" active"</c:if> >
		                           <a href="#categroy${orderGoodsQuery.id}" aria-controls="categroy${orderGoodsQuery.id}" role="tab" data-toggle="tab" class="bb0">${orderGoodsQuery.name}</a>
		                        </li>
                     		</c:forEach>
                     	</c:if>
                     </ul>
                     <!-- Tab panes-->
                     <div class="tab-answer p0 bg-white">
                     	<c:if test="${not empty orderGoodsQuerys}">
                     		<c:forEach items="${ orderGoodsQuerys}" var="orderGoodsQuery" varStatus="num">
		                        <div id="categroy${orderGoodsQuery.id}" role="tabpanel" class="tab-pane <c:if test='${num.index == 0 }'>active</c:if>">
                           					<!-- START table responsive-->
				                           <div class="table-responsive">
				                              <table class="table table-bordered table-hover table-striped">
				                                 <thead>
				                                    <tr>
				                                       <th>商品种类</th>
				                                       <th>商品名称</th>
				                                       <th>商品需求量</th>
				                                       <th>每份重量</th>
				                                       <th>份数</th>
				                                       <th>商品总价格</th>
				                                       <th>商品实际价格</th>
				                                       <th>商品实际重量</th>
				                                    </tr>
				                                 </thead>
				                                 <tbody>
				                                    	<c:forEach items="${orderGoodsQuery.countOrderGoods}" var="countGoods">
				                                    		<tr>
				                                    			<td>${countGoods.categroyName }</td>
				                                    			<td>${countGoods.goodsName }</td>
				                                    			<td>${countGoods.totalNeedWeight}${countGoods.unitName}</td>
				                                    			<td>${countGoods.unit}${countGoods.unitName}</td>
				                                    			<td>${countGoods.totalQuantity}</td>
				                                    			<td>￥${countGoods.totalMoney }</td>
				                                    			<td>￥${countGoods.actualPrice }</td>
				                                    			<td>${countGoods.actualWeight}</td>
				                                    		</tr>
				                                    	</c:forEach>
				                                 </tbody>
				                              </table>
				                           </div>
                        		</div>
		                        
                     		</c:forEach>
                     	</c:if>
                        
                           
                        </div>
                     </div>
                  </div>
                  <!-- END panel tab-->
               </div>
	                  
	        




