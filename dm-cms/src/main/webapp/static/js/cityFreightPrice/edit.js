var mapObj;
var marker = [];
var windowsArr = [];
$(function() {

    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");



    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
            this.loadProvince();

        },

        loadProvince:function(){
            $.ajax({
                url: window.ctx + "/province/getProvince",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#province").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择省份",
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
                    $("#province").val($("#province").attr("data-select")).trigger("change");
                }
            })
        },


        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                    provinceId: {
                        required: true,
                    },
                    cityId: {
                        required: true
                    },
                    freightPrice: {
                        required: true
                    }
                },
                messages: {
                    provinceId: {
                        required: "请选择省份",
                    },
                    cityId: {
                        required: "请选择市"
                    },
                    freightPrice: {
                        required: "请输入运费"
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
                                		location.href = ctx + "/city/freight/price/edit";
                                	}
                                	else{
                                		location.href = ctx + "/city/freight/price/list";
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


    $("#province").change(function(){
        if($("#province").val() == null)
        {
            return ;
        }
        $.ajax({
            url: window.ctx + "/city/getCityByProvinceId/"+$("#province").val(),
            type:"get",
            dataType:'json',
            success:function(data){
                $("#city").empty();
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
                    allowClear: true
                });
                $("#city").val($("#city").attr("data-select")).trigger("change");
            }
        });
    });

    $("select").each(function(){
    	if($(this).attr("data-select")){
    		$(this).val($(this).attr("data-select")).trigger("change");
    	}
	});

    $('.btn-submit').click(function(){
		return true;
	});
});
