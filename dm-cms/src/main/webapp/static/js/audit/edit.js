$(function() {
	
    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");
    

    var edit = {

        /** 初始化函数 */
        init: function() {
            this.bindSortSlider();
            this.addValidateMethods();
            this.validateForm();
            this.changeType();
            this.loadGoods();
            this.changeGoods();
        },

        /** 绑定排序值滑动效果 */
        bindSortSlider: function() {
            $("[data-ui-slider]").slider();
        },
        
        changeType:function(){
        	$("#type").on("change",function(){
        		if($(this).val() == 1){
        			$("input:not(:hidden)").each(function(){
        				$(this).attr("disabled",false);
        				$(this).removeClass("ignore");
        				
        			});
        			$("#gooodsStatus").attr("disabled",true);
        		}else{
        			$("input:not(:hidden)").each(function(){
        				$(this).attr("disabled",true);
        				$(this).addClass("ignore");
        			});
        			$("#gooodsStatus").attr("disabled",false);
        		}
        	});
        },
        loadGoods:function(){
        	$.ajax({
      		  url: window.ctx + "/select2/getGroupGoods",
      		  dataType:'json',
      		  success:function(data){
      			  
      			  $("#goodsSelect").select2({
      				  placeholder: {
      					  id: "-1",
      					  text: "请选择需要修改的商品",
      					},
      			      data: data,
      			      templateResult: function(state) {
				    	  if (!state.id) { return state.text; }
				    	  		var $state = $(
					    	    '<span><img src="'+state.img+'" style="height:15px;width:18px" /> ' + state.text + '</span>'
					    	  );
					    	  return $state;
				    	},	
//      			      matcher: function (params, data) {
//      			    	  if ($.trim(params.term) === '') {
//      					    return data;
//      					  }
//      			    	  //|| data.py.indexOf((params.term).toUpperCase()) > -1
//      					  if (data.text.indexOf(params.term) > -1 ) {
//      						  return $.extend({}, data, true);
//      					  }
//      					  return null;
//      					},
      			      allowClear: false
      			  });
      			  
      			  if($("#goodsSelect").attr("data-select")){
      				  $("#goodsSelect").val($("#goodsSelect").attr("data-select")).trigger("change");
      			  }
      		  }
      	  })
        	
        },
        changeGoods:function(){
        	$("#goodsSelect").on("change",function(){
        		$.ajax({
        			url:window.ctx+"/specialGoods/findGoods",
        			type:"post",
        			data:{
        				goodsId:$(this).val()
        			},
        			dataType:"json",
        			success:function(data){
        				if(id){
        					if($("#type").val() == 1){
        						$("#gooodsStatus").val(data.status);
        					}else{
        						$("#goodsSn").val(data.goodsSn);
                				$("#name").val(data.name);
            					$("#marketPrice").val(data.marketPrice);
                				$("#marketUnit").val(data.marketUnit);
                				$("#marketUnitName").val(data.marketUnitName);
                				$("#price").val(data.price);
                				$("#unit").val(data.unit);
                				$("#unitName").val(data.unitName);
                				$("#farmPrice").val(data.farmPrice);
                				$("#farmUnit").val(data.farmUnit);
                				$("#farmUnitName").val(data.farmUnitName);
        					}
        				}else{
        					
        					$("#goodsSn").val(data.goodsSn);
            				$("#name").val(data.name);
        					$("#marketPrice").val(data.marketPrice);
            				$("#marketUnit").val(data.marketUnit);
            				$("#marketUnitName").val(data.marketUnitName);
            				$("#price").val(data.price);
            				$("#unit").val(data.unit);
            				$("#unitName").val(data.unitName);
            				$("#farmPrice").val(data.farmPrice);
            				$("#farmUnit").val(data.farmUnit);
            				$("#farmUnitName").val(data.farmUnitName);
            				$("#gooodsStatus").val(data.status);
        					
        				}
        				
        			}
        		})
        	});
        },
        
        /** 添加验证方法 */
        addValidateMethods: function() {
            // 商品选择不能为空
        	jQuery.validator.addMethod("goodsRequired", function(value, element) {
        		return this.optional(element) || (value != -1);
            }, "请选择需要修改的商品");
        	// 商品价格只能是整数
        	jQuery.validator.addMethod("plusPrice", function(value, element) {
        		var reg = new RegExp("^(0|[1-9][0-9]*)(\.[0-9])?[0-9]*$");
        		return this.optional(element) || (reg.test(value));
            }, "输入的商品特价只能是正数");
        },

        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
            	ignore: ".ignore",
                rules: {
                	"goods.id":{
                		goodsRequired:true
                	},
                	goodsSn: {
                        required: true,
                        maxlength: 50
                    },
                    name: {
                        required: true,
                        maxlength: 60
                    },
                    marketPrice: {
                        required: true,
                        plusPrice: true
                    },
                    marketUnit: {
                    	required: true,
                        digits: true,
                    },
                    marketUnitName: {
                    	required: true,
                        maxlength: 15
                    },
                    farmPrice: {
                        required: true,
                        plusPrice: true
                    },
                    farmUnit: {
                    	required: true,
                        digits: true,
                    },
                    farmUnitName: {
                    	required: true,
                        maxlength: 15
                    },
                    price: {
                        required: true,
                        plusPrice: true
                    },
                    unit: {
                        required: true,
                        digits: true,
                    },
                    unitName: {
                        required: true,
                        maxlength: 15
                    },
                },
                messages: {
                	"goods.id":{
                		goodsRequired:"请选择需要修改的商品"
                	},
                	goodsSn: {
                        required: "请输入商品编号",
                        maxlength: "商品编号长度不能超过{0}个字符"
                    },
                    name: {
                        required: "请输入商品名称",
                        maxlength: "名称长度不能超过{0}个字符"
                    },
                    marketPrice: {
                        required: "请输市场价格",
                        plusPrice: "市场价格只能是正数"
                    },
                    marketUnit: {
                        required: "请输入市场价格的单位数量",
                        plusPrice: "市场价格的单位数量只能是正整数"
                    },
                    marketUnitName: {
                        required: "请输入市场价格的单位名称",
                        plusPrice: "市场价格的单位名称长度不能超过{0}个字符"
                    },
                    farmPrice: {
                        required: "请输入田园里价格",
                        plusPrice: "田园里价格只能是正数"
                    },
                    farmUnit: {
                        required: "请输入田园里价格的单位数量",
                        plusPrice: "田园里价格的单位数量只能是正整数"
                    },
                    farmUnitName: {
                        required: "请输入田园里价格的单位名称",
                        plusPrice: "田园里价格的单位名称长度不能超过{0}个字符"
                    },
                    price: {
                        required: "请输入净重估价",
                        plusPrice: "净重估价只能是正数"
                    },
                    unit: {
                        required: "请输入净重估价的单位数量",
                        digits: "净重估价的单位数量只能是正整数",
                    },
                    unitName: {
                        required: "请输入净重估价的单位名称",
                        maxlength: "净重估价的单位名称长度不能超过{0}个字符"
                    },
                    shortIntro: {
                        maxlength: "商品短介绍长度不能超过{0}个字符"
                    },
                    goodsDiscount: {
                        required: "请输入商品折扣系数",
                        plusPrice: "商品折扣系数只能是正数"
                    },
                },
                submitHandler: function() {
                	var buttonObj = this.submitButton;
                	$(buttonObj).button("loading");
                	if(status != 1){
                		Dialog.danger("申请已被审核,无法修改");
                		$submitBtn.button("reset");
                		return;
                	}
                    $.ajax({
                        url: $form.attr("action"),
                        type: "POST",
                        data: $form.serialize(),
                        dataType: "JSON",
                        success: function(data) {
                        	console.log(data);
                            if (data.errType == 0) {
                                Dialog.success(data.errMsg, function() {
                                	
                                	location.href = ctx + "/goodsApproval/auditList";
                                }, 1500);
                            } else {
                                validator.showErrors(data.errors);
                                $submitBtn.button("reset");
                                Dialog.danger(data.errMsg);
                            }
                        }
                    });

                }
            })
        }
    };

    edit.init();
    $("select").each(function(){
    	if($(this).attr("data-select")){
    		$(this).val($(this).attr("data-select")).trigger("change");
    	}
	});

});
