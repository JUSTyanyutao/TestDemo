var mapObj;
var marker = [];
var windowsArr = [];
$(function() {

    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");
    var $container = $("#goods-container");



    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
            this.loadAttribute();
        },

        loadAttribute:function(){
            if($("#attribute") == null)
            {
                return ;
            }
            $.ajax({
                url: window.ctx + "/attribute/getAttribute",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#attribute").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择属性",
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
                }
            })
        },


        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                    attrValue: {
                        required: true,
                        maxlength: 60
                    },
                    status: {
                    	required: true
                    }
                    //remark: {
                    //    required: true
                    //}
                },
                messages: {
                    name: {
                        required: "请输入特性值",
                        maxlength: "特性值不能超过{0}个字符"
                    },
                    status: {
                        required: "请选择状态"
                    }
                    //remark: {
                    //    required: "请填写说明"
                    //}
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
                                		location.href = ctx + "/attribute/value/edit";
                                	}
                                	else{
                                		location.href = ctx + "/attribute/value/list";
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

    $('#color').ColorPicker({
            onSubmit: function(hsb, hex, rgb, el) {
                $(el).val(hex);
                $(el).ColorPickerHide();
            },
            onBeforeShow: function () {
                $(this).ColorPickerSetColor(this.value);
            }
        })
        .bind('keyup', function(){
            $(this).ColorPickerSetColor(this.value);
        });




    $("select").each(function(){
    	if($(this).attr("data-select")){
    		$(this).val($(this).attr("data-select")).trigger("change");
    	}
	});

    $('.btn-submit').click(function(){
		return true;
	});
});
