
var type=2;
$(function() {
	
    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");

    var edit = {

        /** 初始化函数 */
        init: function() {
            this.bindSortSlider();
            this.validateForm();
            this.addValidateMethods();
            this.addValidateRuless();
        },

        /** 绑定排序值滑动效果 */
        bindSortSlider: function() {
            $("[data-ui-slider]").slider();
        },
        
        /** 添加验证方法 */
        addValidateMethods: function() {
            // 菜品选择不能为空
        	jQuery.validator.addMethod("goodsRequired", function(value, element) {
        		console.log("value:"+value);
        		return this.optional(element) || (value != null);
            }, "");
        	// 菜品特价只能是整数
        	jQuery.validator.addMethod("plusPrice", function(value, element) {
        		var reg = new RegExp("^([1-9][0-9]*)(\.[0-9])?[0-9]*$");
        		return this.optional(element) || (reg.test(value));
            }, "输入的商品特价只能是正数");
        },
        
        
        /** 添加验证方法 */
        addValidateRuless: function() {
        	jQuery.validator.addClassRules({
        		amountVal: {
                	required: true,
                	plusPrice: true,
                },
                numVal:{
                	required:true,
                	digits:true,
                },
                
            });
        },

        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
            	ignore: "",
            	ignore: ".ignore",
                rules: {
                	goodsSelect:{
                		required:true,
                    },
                    unit:{
                    	required: true,
                    	digits: true,
                    },
                    name:{
                    	required:true
                    },
                    startTime:{
                    	required: true,
                    	date:true,
                    },
                    endTime:{
                    	required: true,
                    	date:true,
                    }
                },
                messages: {
                	goodsSelect:{
                		required:"请选择商品"
                    },
                    unit:{
                    	required: "请输入单位数量",
                    	digits: "单位数量只能是正整数",
                    },
                    name:{
                    	required:"请输入活动名称"
                    },
                    startTime:{
                    	required: "请输入开始时间",
                    	date: "输入的时间格式不正确",
                    },
                    endTime:{
                    	required: "请输入结束时间",
                    	date: "输入的时间格式不正确",
                    }
                },
                submitHandler: function() {
                	var buttonObj = this.submitButton;
                	$(buttonObj).button("loading");
                	
                	var coupons =[];
                	
                	$(".rules").each(function(){
                		var $obj = $(this);
                		var coupon = {};
                		var quantity = $obj.find(".quantity").val();
                		var orderMoney = $obj.find("input[name^='orderMoney']").val();
                		coupon.title = $obj.find("input[name^='rule_title']").val();
                		coupon.desc = $obj.find("textarea[name^='rule_desc']").val();
                		coupon.denomination = $obj.find("input[name^='rule_denomination']").val()
                		coupon.restrictionMoney = $obj.find("input[name^='rule_restrictionMoney']").val();
                		coupon.expired = $obj.find("input[name^='rule_expired']").val();
                		if(coupon.title && coupon.desc && coupon.denomination && coupon.restrictionMoney && coupon.expired){
                			coupons.push({
                				quantity: quantity,
                				orderMoney: orderMoney,
                        		coupon: coupon
                        	});
                		}
                	});
                	
                	var fishs =[];
                	$(".goodsQuantity").each(function(){
                		var goodId = $(this).attr("data-id");
                		var quantity = $(this).html();	
                		fishs.push({
                			goodsId:goodId,
                			quantity:quantity
                		})
                	});
                	
                	var rulesVal = "";
                	if($("#rewardType").val() == 1){
                		rulesVal = JSON.stringify(coupons);
                	}else if($("#rewardType").val() == 2){
                		rulesVal = JSON.stringify(fishs);
                	}
                	$("#rules").val(rulesVal);
                	
                	
                    $.ajax({
                        url: $form.attr("action"),
                        type: "POST",
                        data: $form.serialize(),
                        dataType: "JSON",
                        success: function(data) {
                            if (data.code == 0) {
                                Dialog.success(data.msg, function() {
                                	if($(buttonObj).attr("id") == "saveAgain"){
                                		location.href = ctx + "/promotion/addPromotion";
                                	}
                                	else{
                                		location.href = ctx + "/promotion";
                                	}
                                }, 1500);
                            } else {
                                validator.showErrors(data.errors);
                                $submitBtn.button("reset");
                                Dialog.danger(data.msg);
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
