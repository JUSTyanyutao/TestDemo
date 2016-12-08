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
                	question: {
                        required: true,
                        maxlength: 100
                    },
                    answer:{
                    	required: true
                    },
                    sort:{
                    	required: true,
                    	digits: true,
                        maxlength: 11
                    }
                },
                messages: {
                    question: {
                        required: "请输入问题",
                        maxlength: "问题长度不能超过{0}个字符"
                    },
                    answer:{
                    	required: "请输入答复"
                    },
                    sort:{
                    	required: "请输入序号",
                    	digits:"请输入正确数字",
                        maxlength: "序号不能超过{0}个字符"
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
                                		 location.href = ctx + "/faq";
                                	}
                                	else{
                                		 location.href = ctx + "/faq/list";
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
