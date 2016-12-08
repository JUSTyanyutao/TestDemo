var type=1;
$(function() {
	
    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");

    var edit = {

        /** 初始化函数 */
        init: function() {
            this.bindSortSlider();
            this.validateForm();
            this.loadGoods();
            this.changeGoods();
            this.addValidateMethods();
            this.addValidateRuless();
        },

        /** 绑定排序值滑动效果 */
        bindSortSlider: function() {
            $("[data-ui-slider]").slider();
        },
        
        loadGoods:function(){
        	$.ajax({
      		  url: window.ctx + "/select2/getGroupGoods",
      		  dataType:'json',
      		  data:{
      			type:type  
      		  },
      		  success:function(data){
      			  $(".goodsList").select2({
      				  placeholder: {
      					  id: "-1",
      					  text: "请选择参与抢购的菜品",
      					},
      			      data: data,
      			      templateResult: function(state) {
				    	  if (!state.id) {
							  return state.text;
						  }
						  var $state = $(
								'<span><img src="'+state.img+'" style="height:15px;width:18px" /> ' + state.text + '</span>'
						  );
						  return $state;
					  },
//      			      matcher: function (params, data) {
//      			    	  if ($.trim(params.term) === '') {
//      					    return data;
//      					  }
//      			    	  //|| data.py.indexOf((params.term).toUpperCase()) > -1
//      					  if (data.text.indexOf(params.term) > -1 ) {
//      						  return $.extend({}, data, true);
//      					  }
//      					  return null;
//      					},
      			      allowClear: false
      			  });
      			  
      			  
      			  $(".goodsList").each(function(){
      				if($(this).attr("data-selectt")){
      					$(this).val($(this).attr("data-selectt"));
        				  $(this).val($(this).attr("data-selectt")).trigger("change");
        			  }
      			  });
      		  }
      	  })
        	
        },
        
        changeGoods:function(){
        	
        	$(document).on("change",".goodsList",function(){
        		//$(".has-error[id$='-error']").remove();
        		var goodsId = $(this).val();
        		if(!goodsId){
        			goodsId = $(this).parents(".flashSale").find(".goodsId").val();
        		}
        		var $obj = $(this);
        		$.ajax({
        			url:window.ctx + "/goodsFlashSale/findGoods",
        			type:"post",
        			dataType:"json",
        			data:{
        				goodsId:goodsId
        			},
        			success:function(data){
        				//$("#templateId").val(templateId);
        				var $parentObj = $obj.parents(".flashSale");
        				$parentObj.find(".before_price").val(data.price);
        				$parentObj.find(".before_unit").val(data.unit);
        				$parentObj.find(".before_unitName").val(data.unitName);
        				$obj.parents(".flashSale").find(".goodsId").val(goodsId);
        			}
        		})
        	});
        },
        
        /** 添加验证方法 */
        addValidateMethods: function() {
            // 菜品选择不能为空
        	jQuery.validator.addMethod("goodsRequired", function(value, element) {
        		return this.optional(element) || (value != -1);
            }, "请选择参与限时抢购的商品");
        	// 菜品特价只能是整数
        	jQuery.validator.addMethod("plusPrice", function(value, element) {
        		var reg = new RegExp("^(0|[1-9][0-9]*)(\.[0-9])?[0-9]*$");
        		return this.optional(element) || (reg.test(value));
            }, "输入的商品特价只能是正数");
        },
        
        /** 添加验证方法 */
        addValidateRuless: function() {
        	jQuery.validator.addClassRules({
        		selectGoods:{
        			goodsRequired:true
        		},
        		priceVal: {
                	required: true,
                	plusPrice: true,
                },
                numVal:{
                	required:true,
                	digits:true,
                },
                
            });
        },

        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
            	ignore: "",
            	ignore: ".ignore",
                rules: {
                	name: {
                        required: true,
                    },
                    startTime:{
                    	required: true,
                    	date:true,
                    },
                    endTime:{
                    	required: true,
                    	date:true,
                    }
                },
                messages: {
                	name: {
                        required: "请输入抢购活动名称",
                    },
                    startTime:{
                    	required: "请输入开始时间",
                    	date: "输入的时间格式不正确",
                    },
                    endTime:{
                    	required: "请输入结束时间",
                    	date: "输入的时间格式不正确",
                    }
                },
                errorPlacement: function(error, element) {  
                   if(element.hasClass("time")){
                	   error.appendTo(element.parent().parent()); 
                   }else{
                	   error.appendTo(element.parent());
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
                                		location.href = ctx + "/goodsFlashSale/addGoodsFlashSale";
                                	}
                                	else{
                                		location.href = ctx + "/goodsFlashSale";
                                	}
                                }, 1500);
                            } else {
                                validator.showErrors(data.errors);
                                Dialog.danger(data.msg);
								$submitBtn.button("reset");
                            }
                        },
						error : function(){
							$submitBtn.button("reset");
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

//初始化uploadify插件
function initHeadUpload($obj){
	//console.log($obj.html());
	var id = $obj.find(".showPic").next().attr("id");
	$obj.find(".showPic").next().remove();
	$obj.find(".showPic").next().remove();
	$('<input type="file" name="pic_upload" class="pic_upload"  id="'+id+'" />').insertAfter($obj.find(".showPic"));
	$("#"+id).uploadify({
	        'auto'     : true,
	        'buttonText' : '上传缩略图',
	        'fileTypeExts' : '*.gif; *.jpg; *.png',
	        'fileSizeLimit' : '500KB',
	        'swf'      : window.ctx + '/static/libs/uploadify/uploadify.swf',
	        'uploader' : window.ctx + '/attachment/image',
	        'fileObjName' : 'image',
			'fileTypeDesc': 'Image Files',
	        //允许上传的文件后缀
	        'fileTypeExts': '*.gif; *.jpg; *.png',
			'onUploadStart' : function() {
				//this.uploadify("settings", "formData", {"scenicId": id});
				//console.log(this);
			},
			'onUploadError' : function(file, errorCode, errorMsg, errorString) {
				alert('The file ' + file.name
						+ ' could not be uploaded: '
						+ errorString);
			},
			'onUploadSuccess' : function(file, data, response) {
				data = JSON.parse(data);
				var  $showObj = this.wrapper.parent().find(".showPic");
				var $picValObj = this.wrapper.parent().find(".picVal");
				$picValObj.val(data.file_path);
				
				$showObj.html(
						'<div style="margin-bottom:10px"><img  height="200" width= "200" class="filename" src='+data.image_url+data.file_path+'>&nbsp;&nbsp;<a class="btn red del_file" data-filename = "picVal" >删除</a></div>');
			},
	    });

}

//初始化uploadify插件
function initListUpload($obj){
	//console.log($obj.html());
	var id = $obj.find(".showListPic").next().attr("id");
	$obj.find(".showListPic").next().remove();
	$obj.find(".showListPic").next().remove();
	$('<input type="file" name="listPic_upload" class="listPic_upload"  id="'+id+'" />').insertAfter($obj.find(".showListPic"));
	$("#"+id).uploadify({
	        'auto'     : true,
	        'buttonText' : '上传缩略图',
	        'fileTypeExts' : '*.gif; *.jpg; *.png',
	        'fileSizeLimit' : '500KB',
	        'swf'      : window.ctx + '/static/libs/uploadify/uploadify.swf',
	        'uploader' : window.ctx + '/attachment/image',
	        'fileObjName' : 'image',
			'fileTypeDesc': 'Image Files',
	        //允许上传的文件后缀
	        'fileTypeExts': '*.gif; *.jpg; *.png',
			'onUploadStart' : function() {
				//this.uploadify("settings", "formData", {"scenicId": id});
				//console.log(this);
			},
			'onUploadError' : function(file, errorCode, errorMsg, errorString) {
				alert('The file ' + file.name
						+ ' could not be uploaded: '
						+ errorString);
			},
			'onUploadSuccess' : function(file, data, response) {
				data = JSON.parse(data);
				var  $showObj = this.wrapper.parent().find(".showListPic");
				var $picValObj = this.wrapper.parent().find(".listPicVal");
				$picValObj.val(data.file_path);
				
				$showObj.html(
						'<div style="margin-bottom:10px"><img  height="150" width= "150" class="filename" src='+data.image_url+data.file_path+'>&nbsp;&nbsp;<a class="btn red del_file" data-filename = "listPicVal" >删除</a></div>');
			},
	    });

}


//初始化select插件,限时抢购
function initGoodsSelect($obj){
	
	var ids = [];
	$(".goodsList").each(function(){
		 if($(this).val() != -1){
			 ids.push($(this).val());
		 }
	});
	$.ajax({
		url:window.ctx + "/select2/getGroupGoods",
		dataType:'json',
		data:{
			id:ids,
			type:type
		},
		success:function(data){
			var $goodsListObj = $obj.find('.goodsList');
			$goodsListObj.empty();
			$goodsListObj.select2({
				 placeholder: {
					  id: "-1",
					  text: "请选择参与抢购的菜品",
					},
			      data: data,
			      templateResult: function(state) {
			    	  if (!state.id) { return state.text; }
			    	  		var $state = $(
				    	    '<span><img src="'+state.img+'" style="height:15px;width:18px" /> ' + state.text + '</span>'
				    	  );
				    	  return $state;
			    	},
			      allowClear: false
			  });
			$goodsListObj.next().next().remove();
			
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
				        "data-slider-orientation='horizontal' class='slider slider-horizontal'"+
				        "data='value: 20' style='display: none;'>";
	$sliderParent.append(newSlider);
	$obj.find('input.slider').slider();
	
}
