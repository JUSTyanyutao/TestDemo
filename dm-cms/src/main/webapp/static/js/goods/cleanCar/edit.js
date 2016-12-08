var mapObj;
var marker = [];
var windowsArr = [];
$(function() {

    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");
    var $container = $("#goods-container");



    var edit = {

        /** 初始化函数 */
        init: function() {
            this.bindSortSlider();
            this.validateForm();
            //this.loadCategory();
            //this.loadFilmBrand();
            this.loadCarsize();
            //this.loadAttrType();
            //this.loadAttrFeatures();
            //this.loadCarParts();
            this.loadPackage();
        },




        /** 绑定排序值滑动效果 */
        bindSortSlider: function() {
            $("[data-ui-slider]").slider();
        },


        /**
         *  获取 套餐
         */
        loadPackage:function(){
            $.ajax({
                url: window.ctx + "/package/getPackage",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#packages").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择套餐",
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
                    $("#packages").val($("#packages").attr("data-select").split(",")).trigger("change");
                }
            })
        },

        /**
         *  获取 分类
         */
        loadCategory:function(){
            $.ajax({
                url: window.ctx + "/category/getCategory",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#category").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择分类",
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
                    $("#category").val($("#category").attr("data-select")).trigger("change");
                }
            })
        },

        /**
         * 获取  品牌
         */
        loadFilmBrand:function(){
            $.ajax({
                url: window.ctx + "/brand/getFilmBrand",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#filmBrand").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择品牌",
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
                    $("#filmBrand").val($("#filmBrand").attr("data-select")).trigger("change");
                }
            })
        },

        /**
         * 获取  车的 尺寸 大小
         */
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


        /**
         * 获取 商品类型
         */
        loadAttrType:function(){
            $.ajax({
                url: window.ctx + "/attribute/value/getAttributeValueByAttrbuteId/2",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#attrType").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择商品类型",
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
                    $("#attrType").val($("#attrType").attr("data-select").split(",")).trigger("change");
                }
            })
        },

        /**
         * 获取 商品 特性
         */
        loadAttrFeatures:function(){
            $.ajax({
                url: window.ctx + "/attribute/value/getAttributeValueByAttrbuteId/1",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#attrFeatures").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择商品特性",
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
                    $("#attrFeatures").val($("#attrFeatures").attr("data-select").split(",")).trigger("change");
                }
            })
        },


        /**
         * 获取 部位
         */
        loadCarParts:function(){
            $.ajax({
                url: window.ctx + "/carPart/getCarPart",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#carParts").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择部位",
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
                    $("#carParts").val($("#carParts").attr("data-select").split(",")).trigger("change");
                }
            })
        },










        /**
         * 绑定删除商品事件
         */
        bindRemoveGoodsAction: function() {
            $container.on("click", ".btn-remove", function() {
                var $this = $(this);
                Dialog.confirm("删除", "确认要删除此商品吗？", function(result) {
                    if (result) {
                        $this.parent().parent().parent().parent().remove();

                        if ($container.find(".goods-item").length <= 0) {
                            $container.append("<div class=\"text-center\">赶快来添加活动商品吧！</div><fieldset></fieldset><i></i>")
                        }
                    }
                });
            });
        },


        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                    name: {
                        required: true,
                        maxlength: 60
                    },
                    packages:{
                        required : true
                    },
                    shortIntro: {
                        required: true
                    },
                    price: {
                        required: true
                    }
                },
                messages: {
                    name: {
                        required: "请输入名称",
                        maxlength: "名称长度不能超过{0}个字符"
                    },
                    packages:{
                        required : "请选择套餐"
                    },
                    shortIntro: {
                        required: "请输入短介绍"
                    },
                    price: {
                        required: "请输入售价"
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
                                		location.href = ctx + "/goods/cleanCar/edit";
                                	}
                                	else{
                                		location.href = ctx + "/goods/cleanCar/list";
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

    $('.btn-submit').click(function(){

        var tempUrl = $("#url").val();
        var picsList1=$(".filename1");
        var picsList2=$(".filename2");
        $("#recommendPic").attr("value",picsList1.attr("src"));
        $("#thumb").attr("value",picsList2.attr("src"));


		var detailPicList=$(".filename3");
		var detailPics = "";
		if(detailPicList.length >0){
			for(var i=0;i<detailPicList.length;i++){ //循环为每个img设置
				var index = detailPicList[i].src.indexOf("/img");
				detailPics =  detailPics + tempUrl + detailPicList[i].src.substring(index,detailPicList[i].src.length) + ",";
			}
			detailPics = detailPics.substring(0,detailPics.length - 1);
		}
		$("#pictures").attr("value",detailPics);
		return true;
	});
});
