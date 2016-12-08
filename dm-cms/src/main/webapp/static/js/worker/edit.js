
$(function() {
	
    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");

    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
            //this.loadMember();
            this.loadCity();
            //this.loadManager();
            this.loadCategory();
        },

        /**
         * 获取 服务 分类
         */
        loadCategory:function(){
            $.ajax({
                url: window.ctx + "/category/getCategoryByType/2",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#categoryId").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择服务分类",
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
                    $("#categoryId").val($("#categoryId").attr("data-select").split(",")).trigger("change");
                }
            })
        },

        /**
         * 获取 区域经理
         */
        loadManager:function(){
            $.ajax({
                url: window.ctx + "/member/getMemberByRole/4",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#parentMemberId").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择区域经理",
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
                        //multiple:true,
                        allowClear: true
                    });
                    $("#parentMemberId").val($("#parentMemberId").attr("data-select")).trigger("change");
                }
            })
        },

        /**
         * 获取 城市 区域
         */
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
                        //multiple:true,
                        allowClear: true
                    });
                    $("#city").val($("#city").attr("data-select")).trigger("change");
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
