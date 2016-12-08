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
                        maxlength: 50
                    },
                    
                    sort:{
                    	required: true,
                    	digits: true,
                        maxlength: 11
                    }
                },
                messages: {
                	title: {
                        required: "请输入标题",
                        maxlength: "标题长度不能超过{0}个字符"
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
                                		location.href = ctx + "/activityNutrition";
                                	}
                                	else{
                                		location.href = ctx + "/activityNutritions";
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
    	
		var picsList=$(".filename");
		var pics = "";
		if(picsList.length >0){
			var index = picsList[0].src.indexOf("/img");
			pics =  picsList[0].src.substring(index,picsList[0].src.length) ;
		}
		$("#pic").attr("value",pics);
		
		var detailPicList=$(".filename2");
		var detailPics = "";
		if(detailPicList.length >0){
			var index = detailPicList[0].src.indexOf("/img");
			detailPics =  detailPicList[0].src.substring(index,detailPicList[0].src.length) ;
		}
		$("#detailPic").attr("value",detailPics);
		return true;
	});  
});
