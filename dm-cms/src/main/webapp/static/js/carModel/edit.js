$(function() {

    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");



    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
            this.loadCarsize();
        },



        loadCarsize:function(){
            $.ajax({
                url: window.ctx + "/getLabel/1",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#carSize").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择尺寸",
                        },
                        data: data,
                        matcher: function (params, data) {
                            if ($.trim(params.term) === '') {
                                return data;
                            }
                            if (data.text.indexOf(params.term) > -1) {
                                return $.extend({}, data, true);
                            }
                            return null;
                        },
                        allowClear: true
                    });
                    $("#carSize").val($("#carSize").attr("data-select")).trigger("change");
                }
            })
        },


        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                	name: {
                        required: true,
                        maxlength: 30
                    },
                    carSize: {
                        required: true
                    }
                },
                messages: {
                	name: {
                        required: "请输入名称",
                        maxlength: "名称长度不能超过{0}个字符"
                    },
                    carSize: {
                        required: "请选择尺寸"
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
                                		location.href = ctx + "/carModel/edit";
                                	}
                                	else{
                                		location.href = ctx + "/carModel/list/"+$("#seriesId").val();
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
