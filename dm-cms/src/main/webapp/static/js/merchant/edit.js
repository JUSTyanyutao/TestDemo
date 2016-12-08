$(function() {
	
    var $form         = $("#form-edit");
    var $submitBtn    = $(".btn-submit");
    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
            this.loadFoodMarket();
        },
        
        loadFoodMarket:function(){
        	$.ajax({
        		url: window.ctx + "/foodMarket/getEnableFoodMarket",
        		dataType:"json",
        		success:function(data){
        			$("#marketId").select2({
        				  placeholder: {
        					  id: "-1",
        					  text: "请选择商品所属菜场",
        					},
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
        			      allowClear: false
        			  });
        			  
        			  if($("#marketId").attr("data-select")){
        				  $("#marketId").val($("#marketId").attr("data-select").split(",")).trigger("change");
        			  }
        			  $("#marketId").trigger("change");
        		}
        	})
        },
        
        
        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
            	ignore: "",  
                rules: {
                	mobilephone: {
                        required: true,
                        mobile: true
                    },
                    name: {
                        required: true,
                    }
                    
                },
                messages: {
                	mobilephone: {
                        required: "请输入手机号码",
                        mobile: "请输入正确的手机号码"
                    },
                    name: {
                        required: "请输入名称",
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
                                		location.href = window.ctx +"/merchant/addMerchant";
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
    
    $('.btn-submit').click(function(){
    	
    	var picsList=$(".filename");
		var pics = "";
		if(picsList.length >0){
			var index = picsList[0].src.indexOf("/img");
			pics =  picsList[0].src.substring(index,picsList[0].src.length) ;
		}
		$("#pic").attr("value",pics);
		
		var idcardPicList=$(".filename2");
		var idcardPics = "";
		if(idcardPicList.length >0){
			var index = idcardPicList[0].src.indexOf("/img");
			idcardPics =  idcardPicList[0].src.substring(index,idcardPicList[0].src.length) ;
		}
		$("#idcardPic").attr("value",idcardPics);
		
		var businessLicensePicList=$(".filename3");
		var businessLicensePics = "";
		if(businessLicensePicList.length >0){
			var index = businessLicensePicList[0].src.indexOf("/img");
			businessLicensePics =  businessLicensePicList[0].src.substring(index,businessLicensePicList[0].src.length) ;
		}
		$("#businessLicensePic").attr("value",businessLicensePics);
		
		
		var contractPicList=$(".filename4");
		var contractPics = "";
		if(contractPicList.length >0){
			var index = contractPicList[0].src.indexOf("/img");
			contractPics =  contractPicList[0].src.substring(index,contractPicList[0].src.length) ;
		}
		$("#contractPic").attr("value",contractPics);
		
		return true;
	}); 
    
});