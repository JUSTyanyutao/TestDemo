$(function() {

    var $form = $("#form-edit");

    var list = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
        },


        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
                submitHandler: function() {
                    var buttonObj = this.submitButton;
                    $(buttonObj).button("清除中");
                    $.ajax({
                        url: $form.attr("action"),
                        type: "POST",
                        dataType: "json",
                        success: function(data) {
                            if (data.code == 0) {
                                Dialog.success("清除成功",function(){
                                  location.href = ctx+"/clear/cache"

                                }, 1500);
                            } else {
                                //validator.showErrors(data.errors);
                                //$submitBtn.button("reset");
                                Dialog.danger("清除失败，请稍后再试");
                            }
                        }
                    });

                }
            })
        }

    };
    list.init();
});
