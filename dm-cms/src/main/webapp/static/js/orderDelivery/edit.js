$(function() {
	
    var $form         = $("#form-edit");
    var $submitBtn    = $(".btn-submit");
    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
            //this.addValidateMethods();
        },
        
        
        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
            	ignore: "",  
                rules: {
                	mobile: {
                        required: true,
                        mobile: true
                    },
                    nickName: {
                        required: true,
                    },
                    platType: {
                        required: true
                    }
                    
                },
                messages: {
                	mobile: {
                        required: "请输入手机号码",
                        mobile: "请输入正确的手机号码"
                    },
                    nickName: {
                        required: "请输入昵称",
                    },
                    platType: {
                    	required: "请选择来源平台"
                    }
                },
                
              submitHandler: function() {
                  $("#platType").attr("name","profile.platType");
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
                                		location.href = window.ctx +"/member/addMember";
                                	}else{
                                		location.href = $(".btn-back").attr("href");
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
    
});