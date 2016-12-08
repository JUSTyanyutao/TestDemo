$(function() {
	
    var $form         = $("#form-edit");
    var $submitBtn    = $(".btn-submit");
    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
            this.loadCity();
            //this.loadManager();
            this.loadCategory();
            //this.addValidateMethods();
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
                    //$("#categoryId").val($("#categoryId").attr("data-select")).trigger("change");
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
                    //$("#city").val($("#city").attr("data-select")).trigger("change");
                }
            })
        },
        
        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
            	ignore: "",  
                rules: {
                    parentMemberId: {
                        required: true,
                    },
                	mobile: {
                        required: true,
                        mobile: true
                    },
                    cityId: {
                        required: true
                    },
                    categoryId: {
                        required: true
                    }
                },
                messages: {
                    parentMemberId: {
                        required: "请选择区域经理"
                    },
                    mobile: {
                        required: "请输入手机号码",
                        mobile: "请输入正确的手机号码"
                    },
                    cityId: {
                        required: "请选择城市"
                    },
                    categoryId: {
                        required: "请选择分类"
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


    //$("#platType").val($("#platType").attr("data-select")).trigger("change");
    
});