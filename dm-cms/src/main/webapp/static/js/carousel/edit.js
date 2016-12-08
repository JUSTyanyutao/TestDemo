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
            //this.loadFoodMarket();
        },

        
        /** 绑定排序值滑动效果 */
        bindSortSlider: function() {
            $("[data-ui-slider]").slider();
        },

        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                	name: {
                        required: true,
                        maxlength: 30
                    },
                    link: {
                        required: true,
                        //digits: true
                    },
                    pic:{
                    	required: true,
                    	digits: true,
                        maxlength: 30
                    }
                },
                messages: {
                	name: {
                        required: "请输入名称",
                        maxlength: "名称长度不能超过{0}个字符"
                    },
                    link: {
                        required: "链接不能为空",
                        digits: "链接不能为空"
                    },
                    pic:{
                    	required: "请上传图片",
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
                                		location.href = ctx + "/carousel";
                                	}
                                	else{
                                		location.href = ctx + "/carousels";
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
    //    $(this).val($(this).attr("date-select"));
    //});

    $('.btn-submit').click(function(){
    	var tempUrl = $("#img").val();
		var picsList=$(".filename");
		var pics = "";
        if(picsList.length>0){
            var index = picsList[0].src.indexOf("/img");
            pics =  picsList[0].src.substring(index,picsList[0].src.length) ;
        }
        if(pics != "" && pics != null){
            $("#pic").attr("value",tempUrl+pics);
        }
        else
        {
            $("#pic").attr("value","");
        }
		return true;
	});  
});
