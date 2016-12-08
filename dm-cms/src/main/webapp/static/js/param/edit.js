var mapObj;
var marker = [];
var windowsArr = [];
$(function() {

    var $form = $("#param-form-edit");
    var $submitBtn = $(".btn-submit");
    

    
    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
        },

        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                	key: {
                        required: true,
                        maxlength: 50
                    },
                    value: {
                        required: true,
                        maxlength: 50
                    },
                    paramDesc: {
                        required: true,
                        maxlength: 50
                    }
                },
                messages: {
                    key: {
                        required: "请输入参数变量",
                        maxlength: "产品分类名称长度不能超过{0}个字符"
                    },
                    value: {
                        required: "请输入参数值",
                        maxlength: "产品分类名称长度不能超过{0}个字符"
                    },
                    paramDesc: {
                        required: "请输入参数名称",
                        maxlength: "产品分类名称长度不能超过{0}个字符"
                    }
                },
                submitHandler: function(form) {
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
                                		location.href = ctx + "/param/edit";
                                	}
                                	else{
                                		location.href = ctx + "/param/list";
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

    //$("select").each(function(){
    	//if($(this).attr("data-select")){
    	//	$(this).val($(this).attr("data-select")).trigger("change");
    	//}
	//});

    //$('.btn-submit').click(function(){
	//	return true;
	//});
});
