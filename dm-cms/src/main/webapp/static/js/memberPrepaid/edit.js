$(function() {
	
    var $form         = $("#form-edit");
    var $submitBtn    = $(".btn-submit");
    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
            this.addValidateMethods();
        },
        
        /** 添加验证方法 */
        addValidateMethods: function() {
        	// 金额只能是整数
        	jQuery.validator.addMethod("plusPrice", function(value, element) {
        		var reg = new RegExp("^(0|[1-9][0-9]*)(\.[0-9])?[0-9]*$");
        		return this.optional(element) || (reg.test(value));
            }, "");
        },
        
        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                	money: {
                        required: true,
                        plusPrice:true
                    },
                    giveMoney: {
                        required: true,
                        plusPrice:true
                    },
                    desc: {
                        required: true,
                        maxlength: 128
                    }
                    
                },
                messages: {
                	money: {
                        required: "请输入充值金额",
                        plusPrice: "充值金额只能是正数"
                    },
                    giveMoney: {
                        required: "请输入赠送金额",
                        plusPrice:"赠送金额只能是正数"
                    },
                    desc: {
                    	required: "请输入充值描述",
                		maxlength: "充值描述长度不能超过{0}个字符"	
                    }
                },
                
              submitHandler: function() {
            	  var buttonObj = this.submitButton;
                  $(buttonObj).button("loading");
                    $.ajax({
                        url: $form.attr("action"),
                        type: "POST",
                        data: $form.serialize(),
                        dataType: "JSON",
                        success: function(data) {
                            if (data.code == 0) {
                            	Dialog.success(data.msg, function() {
                                	if($(buttonObj).attr("id") == "saveAgain"){
                                		location.href = window.ctx +"/memberPrepaid/addMemberPrepaid";
                                	}else{
                                		location.href = $(".btn-back").attr("href");
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