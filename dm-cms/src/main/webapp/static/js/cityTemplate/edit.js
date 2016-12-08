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
            this.loadCity();
        },




        loadCity:function(){
            $.ajax({
                url: window.ctx + "/city/getCity",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#city").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择城市",
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
                        multiple:true,
                        allowClear: true
                    });
                    $("#city").val($("#city").attr("data-select").split(",")).trigger("change");
                }
            })
        },


        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                    name: {
                        required: true,
                        maxlength: 60
                    },
                    cities: {
                        required: true
                    },
                    status: {
                    	required: true
                    }
                },
                messages: {
                    name: {
                        required: "请输入名称",
                        maxlength: "名称长度不能超过{0}个字符"
                    },
                    status: {
                        required: "请选择状态"
                    },
                    cities: {
                        required: "请选择城市"
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
                                		location.href = ctx + "/city/template/edit";
                                	}
                                	else{
                                		location.href = ctx + "/city/template/list";
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

    $('.btn-submit').click(function(){
		return true;
	});
});
