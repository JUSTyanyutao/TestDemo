var mapObj;
var marker = [];
var windowsArr = [];
$(function() {

    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");

    var edit = {

        /** 初始化函数 */
        init: function() {
            this.bindSortSlider();
            this.validateForm();
        },

        /** 绑定排序值滑动效果 */
        bindSortSlider: function() {
            $("[data-ui-slider]").slider();
        },

        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                	limitStartDate: {
                		required: true,
                    	date:true
                    },
                    limitEndDate:{
                    	required: true,
                    	date:true
                    }
                },
                messages: {
                	limitStartDate: {
                		required: "请输入限制起始时间",
                    	date: "输入的时间格式不正确"
                    },
                    limitEndDate:{
                    	required: "请输入限制过期时间",
                    	date: "输入的时间格式不正确"
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
                                		location.href = ctx + "/deliveryLimitDate";
                                	}
                                	else{
                                		location.href = ctx + "/deliveryLimitDates";
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

});
