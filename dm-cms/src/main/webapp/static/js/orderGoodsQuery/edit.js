
$(function() {
	
    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");

    var edit = {

        /** 初始化函数 */
        init: function() {
            this.bindSortSlider();
            this.validateForm();
            this.addValidateMethods();
            this.loadAreaService();
        },

        /** 绑定排序值滑动效果 */
        bindSortSlider: function() {
            $("[data-ui-slider]").slider();
        },
        
        loadAreaService:function(){
        	$.ajax({
      		  url: window.ctx + "/areaService/findAreaServices",
      		  dataType:'json',
      		  success:function(data){
      			  
      			  $("#areaServiceId").select2({
      				  placeholder: {
      					  id: "-1",
      					  text: "请选择服务区域",
      					},
      			      data: data,
      			      matcher: function (params, data) {
      			    	 if ($.trim(params.term) === '') {
     					    return data;
     					  }
     					  if (data.text.indexOf(params.term) > -1 || data.py.indexOf((params.term).toUpperCase()) > -1) {
     						  return $.extend({}, data, true);
     					  }
      					  return null;
      					},
      			      allowClear: false
      			  });
      			  
      			  
      			  if($("#areaServiceId").attr("data-select")){
      				  $("#areaServiceId").val($("#areaServiceId").attr("data-select")).trigger("change");
      			  }
	      		  
      		  }
      	  })
        	
        },
        /** 添加验证方法 */
        addValidateMethods: function() {
            // 菜品选择不能为空
        	jQuery.validator.addMethod("goodsRequired", function(value, element) {
        		return this.optional(element) || (value != -1);
            }, "");
        	// 菜品特价只能是整数
        	jQuery.validator.addMethod("plusPrice", function(value, element) {
        		var reg = new RegExp("^([1-9][0-9]*)\.?[0-9]*$");
        		return this.optional(element) || (reg.test(value));
            }, "");
        },
        
        

        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
            	ignore: "",
                rules: {
                	goodsSelect: {
                		goodsRequired: true,
                    },
                    specialPrice:{
                    	required: true,
                    	plusPrice: true,
                    },
                    pic:{
                    	required:true,
                    },
                    unit:{
                    	required: true,
                    	digits: true,
                    },
                    unitName:{
                    	required:true
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
                	goodsSelect: {
                		goodsRequired: "请选择参与特价的商品",
                    },
                    specialPrice:{
                    	required: "请输入商品特价",
                    	plusPrice:"输入的商品特价只能是正数",
                    },
                    pic:{
                    	required:"请上传特价图片"
                    },
                    unit:{
                    	required: "请输入单位数量",
                    	digits: "单位数量只能是正整数",
                    },
                    unitName:{
                    	required:"请输入单位名称"
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
                submitHandler: function() {
                	console.log(JSON.stringify(pointArr));
                	$("#aroundPoint").val(JSON.stringify(pointArr));
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
                                		location.href = ctx + "/siteStation/saveSiteStation";
                                	}
                                	else{
                                		location.href = ctx + "/siteStation";
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
