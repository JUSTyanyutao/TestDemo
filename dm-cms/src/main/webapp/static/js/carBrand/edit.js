$(function() {

    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");



    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
        },


        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                	name: {
                        required: true,
                        maxlength: 30
                    },
                    firstChar:{
                        required:true,
                        maxlength:1
                    },
                    status:{
                        required:true
                    },
                },
                messages: {
                	name: {
                        required: "请输入名称",
                        maxlength: "名称长度不能超过{0}个字符"
                    },
                    firstChar:{
                        required:"请输入品牌首字母",
                        maxlength:"首字母"
                    },
                    status:{
                        required:"请选择状态"
                    },
                },
                submitHandler: function() {
                    var parent=/^[A-Z]{1}$/;
                    if(!parent.test($("#firstChar").val()))
                    {
                        alert("格式不正确");
                        return;
                    }

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
                                		location.href = ctx + "/carBrand/edit";
                                	}
                                	else{
                                		location.href = ctx + "/carBrand/list";
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

        var picsList=$(".filename");
        $("#logoUrl").attr("value",picsList.attr("src"));
		return true;
	});


    $("#pic_upload").uploadify(
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
                $("#picsFileList")
                    .html(
                        '<div style="margin-bottom:10px"><img  height="200" width= "200" class="filename" src='+data.image_url+data.url+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
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
