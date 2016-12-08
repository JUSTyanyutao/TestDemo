$(function() {
	
    var $form         = $("#form-edit");
    var $submitBtn    = $(".btn-submit");
    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
            this.addValidateMethods();
            this.bindSortSlider();
        },
        
        /** 绑定排序值滑动效果 */
        bindSortSlider: function() {
            $("[data-ui-slider]").slider();
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
            	ignore:"",
                rules: {
                    content: {
                        required: true,
                    }
                },
                messages: {
                	content: {
                    	required: "请编辑协议内容",
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
                                		location.href = window.ctx +"/agreement/addAgreement";
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