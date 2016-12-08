
$(function() {
	
    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");

    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
            this.bindSortSlider();
            this.loadWorker();
            //this.loadMember();
            //this.loadProvince();
            this.loadBrand();
            this.removeWorker();
            this.addWorker();
        },


        /** 绑定排序值滑动效果 */
        bindSortSlider: function() {
            $("[data-ui-slider]").slider();
        },

        loadWorker:function(){
            $.ajax({
                url: window.ctx + "/member/getMemberByRole/3",
                dataType:'json',
                //data:{
                //    type:type
                //},
                success:function(data){
                    $(".selectGoods").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择技师"
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

                    $(".selectGoods").each(function(){
                        if($(this).attr("data-select")){
                            $(this).val($(this).attr("data-select"));
                            $(this).val($(this).attr("data-select")).trigger("change");
                        }
                    });
                }
            })

        },

        // 删除 技师
        removeWorker: function() {
            $("#form-edit").on("click", ".cancel", function() {
                var $btn = $(this);
                Dialog.confirm("删除", "确认要删除次秒杀商品吗？", function(result) {
                    if (result) {
                        $btn.parent().parent().parent().remove();
                    }
                });
            });
        },

        // 添加秒杀商品按钮
        addWorker: function() {
            $("#btn-add-flash-goods").on("click", function() {
                var template = $("#flash-goods-template").text();

                $(".btn-back").before(template);

                var panel = $(".panel-flash-sale:last");
                var random = parseInt(Math.random() * 1000);
                //panel.find("#pic_upload").attr('id', 'pic_upload' + random);
                //panel.find(".isDisplay").attr('name', 'isDisplay' + random);
                initGoodsSelect(panel);
                initSlider(panel);
            });
        },

        loadMember:function(){
            $.ajax({
                url: window.ctx + "/member/getMember",
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

        loadBrand:function(){
            $.ajax({
                url: window.ctx + "/carBrand/getCarBrand",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#carBrand").select2({
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
                    $("#carBrand").val($("#carBrand").attr("data-select")).trigger("change");
                }
            })
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
                            text: "请选择省",
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


        // 获取并验证参数
        getAndVerifyParam: function() {
            var params = {};
            params["id"]        = $("#id").val();
            params["cityName"]        = $("#cityId").val();
            params['name']      = $("#name").val();
            params['contact'] = $('input[name=contact]').val();
            params['phone']   = $('input[name=phone]').val();
            params['serviceTimeAm'] = "";
            params['serviceTimePm'] = "";
            $form.find(".serviceTimeAm:checked").each(function() {
                params['serviceTimeAm'] += $(this).val()+",";
            });
            $form.find(".serviceTimePm:checked").each(function() {
                params['serviceTimePm'] += $(this).val()+",";
            });

            params['carBrandId']      = $("#carBrand").val();
            params['address']      = $("input[name=address]").val();
            params['lng']      = $("input[name=lng]").val();
            params['lat']      = $("input[name=lat]").val();
            params['pictures']      = $("input[name=pictures]").val();


            $(".panel-flash-sale").each(function(i) {
                var $flashGoods = $(this);

                //params["shopWorkerList["+i+"].id"]         = $flashGoods.find("input[name=id]").val();
                params["shopWorkerList["+i+"].memberId"]    = $flashGoods.find(".member").val();
                params["shopWorkerList["+i+"].priority"]      = $flashGoods.find(".priority").val();


            });
            return params;
        },


        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
            	ignore: "",
                rules: {
                    name:{
                    	required:true
                    },
                    contact:{
                    	required:true
                    },
                    phone:{
                    	required:true,
                        mobile:true
                    },
                    carBrandId:{
                    	required:true
                    },
                    address:{
                    	required:true
                    }
                },
                messages: {
                    name:{
                    	required:"请输入活动名称"
                    },
                    contact:{
                        required:"请填写联系人"
                    },
                    phone:{
                        required:"请填写联系方式",
                        mobile:"请输入正确的手机号码"
                    },
                    carBrandId:{
                        required:"请选择车牌"
                    },
                    address:{
                    	required:"请输入活动地址"
                    }
                },
                submitHandler: function() {
                	//$("#aroundPoint").val(JSON.stringify(pointArr));
                    var params = edit.getAndVerifyParam();

                	var buttonObj = this.submitButton;
                	$(buttonObj).button("loading");
                    $.ajax({
                        url: $form.attr("action"),
                        type: "POST",
                        data: params,
                        dataType: "JSON",
                        success: function(data) {
                            if (data.code == 0) {
                                Dialog.success(data.msg, function() {
                                	if($(buttonObj).attr("id") == "saveAgain"){
                                		location.href = ctx + "/shop/4s/edit";
                                	}
                                	else{
                                		location.href = ctx + "/shop/4s/list";
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

    //$("#province").change(function(){
    //    if($("#province").val() == null)
    //    {
    //        return ;
    //    }
    //    //$("#category").attr("data-select","");
    //    $.ajax({
    //        url: window.ctx + "/city/getCityByProvinceId/"+$("#province").val(),
    //        type:"get",
    //        dataType:'json',
    //        success:function(data){
    //            $("#cityId").empty();
    //            $("#cityId").select2({
    //                placeholder: {
    //                    id: "-1",
    //                    text: "请选择市",
    //                },
    //                data: data,
    //                matcher: function (params, data) {
    //                    if ($.trim(params.term) === '') {
    //                        return data;
    //                    }
    //                    if (data.text.indexOf(params.term) > -1) {
    //                        return $.extend({}, data, true);
    //                    }
    //                    return null;
    //                },
    //                allowClear: true
    //            });
    //            $("#cityId").val($("#city").attr("data-select")).trigger("change");
    //            $("#cityId").css("display","display");
    //        }
    //    });
    //});


    $('.btn-submit').click(function(){

        var tempUrl = $("#url").val();
        var detailPicList=$(".filename2");
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

    $("select").each(function(){
    	if($(this).attr("data-select")){
    		$(this).val($(this).attr("data-select")).trigger("change");
    	}
	});


    $("#detailPic_upload").uploadify(
        {
            height : 30,
            auto : true,
            swf : window.ctx + '/static/libs/uploadify/uploadify.swf',
            uploader : window.ctx + '/attachment/uploadImage;jsessionid=${jsessionid}',
            width : 120,
            fileObjName : 'fileData',
            fileTypeDesc: 'Image Files',
            //允许上传的文件后缀
            fileTypeExts: '*.gif; *.jpg; *.jpeg; *.png',
            buttonText : "上传图片",
            debug : false,
            uploadLimit : 10,
            onUploadStart : function() {
            },
            onUploadError : function(file, errorCode, errorMsg, errorString) {
                alert('The file ' + file.name
                    + ' could not be uploaded: '
                    + errorString);
            },
            onUploadSuccess : function(file, data, response) {
                data = eval("(" + data + ")");
                console.log(data);
                $("#detailPicFileList")
                    .append(
                        '<div style="margin-bottom:10px"><img  height="200" width= "200" class="filename2" src='+data.image_url+data.url+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
                delFile(data.url);
                //$("#btn-save-goods").prop("disabled", false);
            }
        });

    delFile();

    function delFile(fileName){
        $(".del_file").off("click").on("click",function(){
            var obj = $(this);
            if(null==fileName || fileName == ""){
                fileName = obj.attr("data-filename");
            }

            obj.parent().remove();

        })
    }
});


//初始化select插件,限时抢购
function initGoodsSelect($obj){

    var ids = [];
    $(".goodsList").each(function(){
        if($(this).val() != -1){
            ids.push($(this).val());
        }
    });
    $.ajax({
        url:window.ctx + "/member/getMemberByRole/3",
        dataType:'json',
        //data:{
        //    id:ids
        //},
        success:function(data){
            var $goodsListObj = $obj.find('.goodsList');
            $goodsListObj.empty();
            $goodsListObj.select2({
                placeholder: {
                    id: "-1",
                    text: "请选择技师"
                },
                data: data,
                templateResult: function(state) {
                    if (!state.id) { return state.text; }
                    var $state = $(
                        '<span>' + state.text + '</span>'
                        // '<span><img src="'+state.img+'" style="height:15px;width:18px" /> ' + state.text + '</span>'
                    );
                    return $state;
                },
                allowClear: false
            });
        }
    })
}

//初始化排序slider插件
function initSlider($obj,current){

    var $sliderObj = $obj.find('input.slider');
    $sliderObj.prev().remove();
    var $sliderParent = $sliderObj.parent();
    $sliderObj.remove();
    var newSlider = "<input name='flashGoods["+current+"].sort' data-ui-slider='' " +
        "type='text' value='20' data-slider-min='1'"+
        "data-slider-max='20' data-slider-step='1' data-slider-value=''"+
        "data-slider-orientation='horizontal' class='slider slider-horizontal priority'"+
        "data='value: 20' style='display: none;'>";
    $sliderParent.append(newSlider);
    $obj.find('input.slider').slider();

}