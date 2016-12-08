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
            this.bindSortSlider();
            this.validateForm();
            this.loadCategory();
        },




        /** 绑定排序值滑动效果 */
        bindSortSlider: function() {
            $("[data-ui-slider]").slider();
        },


        loadCategory:function(){
            $.ajax({
                url: window.ctx + "/category/getRootCategory",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#rootCategory").select2({
                        placeholder: {
                            id: "-1",
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
                    $("#rootCategory").val($("#rootCategory").attr("data-select")).trigger("change");
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
                    sort: {
                        required: true
                    },
                    status: {
                    	required: true
                    },
                    recommendFlag: {
                        required: true
                    }
                },
                messages: {
                    name: {
                        required: "请输入名称",
                        maxlength: "名称长度不能超过{0}个字符"
                    },
                    sort: {
                        required: "请输入优先级"
                    },
                    status: {
                        required: "请选择状态"
                    },
                    recommendFlag: {
                        required: "请选择置顶"
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
                                		location.href = ctx + "/brand/edit";
                                	}
                                	else{
                                		location.href = ctx + "/brand/list";
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


        var picsList=$(".filename");
        $("#pic").attr("value",picsList.attr("src"));
		return true;
	});
});
