var mapObj;
var marker = [];
var windowsArr = [];
$(function() {

    var $form = $("#form-edit");
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
                    //code: {
                    //    required: true,
                    //    maxlength: 100
                    //},
                    description:{
                    	required: true,
                        maxlength: 100
                    },
                    content:{
                        required: true,
                        maxlength: 500
                    }
                },
                isDisplay: {
                    //code: {
                    //    required: "请选择短信类型",
                    //    maxlength: "问题长度不能超过{0}个字符"
                    //},
                    description:{
                        required: "请输入描述",
                        maxlength: "描述不能超过{0}个字符"
                    },
                    content:{
                        required: "请输入短信内容",
                        maxlength: "问题长度不能超过{0}个字符"
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
                                		 location.href = ctx + "/sms/template/edit";
                                	}
                                	else{
                                		 location.href = ctx + "/sms/template/list";
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
        $(this).val($(this).attr("data-select")).trigger("change");
    });



    //日期－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－
    $('#startTime').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        icons: {
            time: 'fa fa-clock-o',
            date: 'fa fa-calendar',
            up: 'fa fa-chevron-up',
            down: 'fa fa-chevron-down',
            previous: 'fa fa-chevron-left',
            next: 'fa fa-chevron-right',
            today: 'fa fa-crosshairs',
            clear: 'fa fa-trash'
        }
    });

    $('#endTime').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        icons: {
            time: 'fa fa-clock-o',
            date: 'fa fa-calendar',
            up: 'fa fa-chevron-up',
            down: 'fa fa-chevron-down',
            previous: 'fa fa-chevron-left',
            next: 'fa fa-chevron-right',
            today: 'fa fa-crosshairs',
            clear: 'fa fa-trash'
        }
    });
//日期－－－－－－－－－－－－－－－－－－－－－－－－－－－－
});
