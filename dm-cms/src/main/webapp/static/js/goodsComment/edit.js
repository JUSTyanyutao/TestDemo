
$(function() {
	
    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");

    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
            this.loadMember();
        },


        loadMember:function(){
            $.ajax({
                url: window.ctx + "/member/getMemberByRole/2",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#member").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择会员",
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
                    $("#member").val($("#member").attr("data-select")).trigger("change");
                }
            })
        },


        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
            	ignore: "",
                rules: {
                    name:{
                    	required:true,
                    },
                    address:{
                    	required:true
                    }
                },
                messages: {
                    name:{
                    	required:"请输入活动名称"
                    },
                    address:{
                    	required:"请输入活动地址"
                    }
                },
                submitHandler: function() {
                	//$("#aroundPoint").val(JSON.stringify(pointArr));
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
                                		location.href = ctx + "/worker/edit";
                                	}
                                	else{
                                		location.href = ctx + "/worker/list";
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
        return true;
    });

    $("select").each(function(){
    	if($(this).attr("data-select")){
    		$(this).val($(this).attr("data-select")).trigger("change");
    	}
	});



});
