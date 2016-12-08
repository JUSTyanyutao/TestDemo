
$(function() {
	
    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");

    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
            //this.loadMember();
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
                                		location.href = ctx + "/shop/street/edit";
                                	}
                                	else{
                                		location.href = ctx + "/shop/street/list";
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
