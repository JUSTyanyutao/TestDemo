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
                	title: {
                        required: true,
                        maxlength: 100
                    },
                    sort:{
                    	required: true,
                    	digits: true,
                        maxlength: 11
                    }
                    
                },
                messages: {
                	title: {
                        required: "请输入FAQ标题",
                        maxlength: "FAQ标题长度不能超过{0}个字符"
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
                                		location.href = ctx + "/faq/point";
                                	}
                                	else{
                                		location.href = ctx + "/faq/pointlist";
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

    $('.btn-submit').click(function(){
		var iconPicIdList=$(".filename");
		var iconPicId = "";
		if(iconPicIdList.length > 0){
			var index = iconPicIdList[0].src.indexOf("/img");
			iconPicId =  iconPicIdList[0].src.substring(index,iconPicIdList[0].src.length) ;
		}
		$("#pics").attr("value",iconPicId);
		return true;
	});  
});
