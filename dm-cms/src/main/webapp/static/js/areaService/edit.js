
$(function() {
	
    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");

    var edit = {

        /** 初始化函数 */
        init: function() {
            this.bindSortSlider();
            this.validateForm();
            this.addValidateMethods();
            this.loadProvince();
            this.loadCounty();
            this.loadCity();
        },

        /** 绑定排序值滑动效果 */
        bindSortSlider: function() {
            $("[data-ui-slider]").slider();
        },
        
        loadProvince:function(){
        	$.ajax({
      		  url: window.ctx + "/select2/getProvinceList",
      		  dataType:'json',
      		  success:function(data){
      			  
      			  $("#provinceSelect").select2({
      				  placeholder: {
      					  id: "-1",
      					  text: "请选择所在省份",
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
      			  
      			  $("#citySelect").select2({
		  				  placeholder: {
		  					  id: "-1",
		  					  text: "请选择所在市",
		  					},
		      			});
	    		 $("#countySelect").select2({
		  				  placeholder: {
		  					  id: "-1",
		  					  text: "请选择所在区",
		  					},
	    			});
      			  
      			  if($("#provinceSelect").attr("data-select")){
      				  $("#provinceSelect").val($("#provinceSelect").attr("data-select")).trigger("change");
      			  }
	      		  
      		  }
      	  })
        	
        },
        
        loadCity:function(){
        	$("#provinceSelect").on("change",function(){
        		$.ajax({
            		  url: window.ctx + "/select2/getCitysByProvince/"+$(this).val(),
            		  dataType:'json',
            		  success:function(data){
            			  $("#citySelect").empty();
            			  $("#citySelect").select2({
            				  placeholder: {
            					  id: "-1",
            					  text: "请选择所在市",
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
            			  
            			  $("#countySelect").empty();
            			  $("#countySelect").select2({
	    		  				  placeholder: {
	    		  					  id: "-1",
	    		  					  text: "请选择所在区",
	    		  					},
	    	      			});
            			  
            			  if($("#citySelect").attr("data-select")){
            				  $("#citySelect").val($("#citySelect").attr("data-select")).trigger("change");
            			  }
            			  
            		  }
            	  })
        		
        		
        	});
        },
        
        loadCounty:function(){
        	$("#citySelect").on("change",function(){
        		$.ajax({
            		  url: window.ctx + "/select2/getCountysByCity/"+$(this).val(),
            		  dataType:'json',
            		  success:function(data){
            			  $("#countySelect").empty();
            			  $("#countySelect").select2({
            				  placeholder: {
            					  id: "-1",
            					  text: "请选择所在区",
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
            			  
            			  if($("#countySelect").attr("data-select")){
            				  $("#countySelect").val($("#countySelect").attr("data-select")).trigger("change");
            			  }
            		  }
            	  })
        		
        		
        	});
        },
        /** 添加验证方法 */
        addValidateMethods: function() {
        	jQuery.validator.addMethod("provinceRequired", function(value, element) {
        		return this.optional(element) || (value != -1);
            }, "");
        },
        
        

        /** 验证表单字段 */
        validateForm: function() {
            var validator = $form.validate({
            	ignore: "",
                rules: {
                	"province.id": {
                		provinceRequired: true,
                    },
                    regionName:{
                    	required: true,
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
                	"province.id": {
                		provinceRequired: "请选择所在省份",
                    },
                    regionName:{
                    	required: "请输入区域名称",
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
                	$("#goodsId").attr("name","goods.id");
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
                                		location.href = ctx + "/areaService/addAreaService";
                                	}
                                	else{
                                		location.href = ctx + "/areaService";
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
