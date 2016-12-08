$(function() {

    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");



    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
            this.loadCarsize();
            this.loadGoods();
            this.loadCityTemplate();
        },


        /**
         *  获取 漆面贴膜 和 玻璃贴膜的  商品
         */
        loadGoods:function(){
            $.ajax({
                url: window.ctx + "/goods/getServiceGoods",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#goods").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择服务商品",
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
                    $("#goods").val($("#goods").attr("data-select")).trigger("change");
                }
            })
        },

        loadCityTemplate:function(){
            $.ajax({
                url: window.ctx + "/city/template/getCityTemplate",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#cityTemplate").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择城市模版",
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
                    $("#cityTemplate").val($("#cityTemplate").attr("data-select")).trigger("change");
                }
            })
        },



        loadCarsize:function(){
            $.ajax({
                url: window.ctx + "/getLabel/1",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#carSize").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择尺寸",
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
                    $("#carSize").val($("#carSize").attr("data-select")).trigger("change");
                }
            })
        },




        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                    serviceId: {
                        required: true
                    },
                    cityTemplateId: {
                        required: true
                    },
                    carSize: {
                        required: true
                    },
                    price: {
                        required: true
                    }
                },
                messages: {
                    serviceId: {
                        required: "请选择服务商品",
                    },
                    cityTemplateId: {
                        required: "请选择城市模版",
                    },
                    carSize: {
                        required: "请选择尺寸"
                    },
                    price: {
                        required: "请填写价格"
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
                                		location.href = ctx + "/service/price/edit";
                                	}
                                	else{
                                		location.href = ctx + "/service/price/list";
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
});
