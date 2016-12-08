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
            this.loadCategory();
        },

        loadCategory:function(){
            $.ajax({
                url: window.ctx + "/category/getCategory",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#category").select2({
                        placeholder: {
                            id: "0",
                            text: "请选择分类",
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
                    $("#category").val($("#category").attr("data-select")).trigger("change");
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
                    status: {
                    	required: true
                    },
                    remark: {
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
                    remark: {
                        required: "请填写说明"
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
                                		location.href = ctx + "/package/edit";
                                	}
                                	else{
                                		location.href = ctx + "/package/list";
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


    //$("#rootCategory").change(function(){
    //    if($("#rootCategory").val() == null)
    //    {
    //        return ;
    //    }
    //    $.ajax({
    //        url: window.ctx + "/category/getCategory/"+$("#rootCategory").val(),
    //        type:"get",
    //        dataType:'json',
    //        success:function(data){
    //            $("#category").empty();
    //            $("#category").select2({
    //                placeholder: {
    //                    id: "-1",
    //                    text: "请选择分类",
    //                },
    //                data: data,
    //                matcher: function (params, data) {
    //                    if ($.trim(params.term) === '') {
    //                        return data;
    //                    }
    //                    if (data.text.indexOf(params.term) > -1) {
    //                        return $.extend({}, data, true);
    //                    }
    //                    return null;
    //                },
    //                allowClear: true
    //            });
    //            $("#category").val($("#category").attr("data-select")).trigger("change");
    //        }
    //    });
    //});


    $("select").each(function(){
    	if($(this).attr("data-select")){
    		$(this).val($(this).attr("data-select")).trigger("change");
    	}
	});

    $('.btn-submit').click(function(){
		return true;
	});
});
