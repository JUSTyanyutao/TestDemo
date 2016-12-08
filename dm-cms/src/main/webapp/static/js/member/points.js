if($("input[name=selectWay]").val() == 1){
	$("#memberMobile").show();
}else{
	$("#memberMobile").hide();
}
$(function() {
	
    var $form         = $("#form-edit");
    var $submitBtn    = $(".btn-submit");
    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
            this.loadMobileSelectTags();
        },
        
        loadMobileSelectTags:function(){
        	/** 获取手机号的下拉列表开始  */
        	$.ajax({
        		  url: window.ctx + "/member/getMobileList",
        		  dataType:'json',
        		  success:function(data){
        			  
        			  $("#mobileList").select2({
        				  placeholder: {
        					  id: "-1",
        					  text: "请选择手机号",
        					},
        				  separator: ",",
        			      data: data,
        			      matcher: function (params, data) {
        					  if ($.trim(params.term) === '') {
        					    return data;
        					  }
        					  if (data.text.indexOf(params.term) > -1 ) {
        						  return $.extend({}, data, true);
        					  }
        					  return null;
        					},
        				  multiple: true,
        			      allowClear: false
        			  }); 
        		  }
        	  })
        },
        
        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                	mobileList: {
                        required: true,
                    },
                   
                    totalPoints: {
                        required: true,
                        digits: true,
                        maxlength: 13
                    }
                },
                messages: {
                	mobileList: {
                        required: "请输入手机号码",
                    },
                   
                    totalPoints: {
                    	required: "请输入积分",
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
                                	location.href = ctx + "/member";
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
    
    $("input[name=selectWay]").click(function(){
    	if($(this).val() == 1){
    		$("#memberMobile").show();
    		
    	}else{
    		$("#memberMobile").hide();
    	}
    });
    
    $('.btn-submit').click(function(){
    	$("#mobile").val($("#mobileList").val());
    	return true;
    });
});