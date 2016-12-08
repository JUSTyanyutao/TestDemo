

    var rulesFish = {

        /** 初始化函数 */
        init: function(rules) {
            this.loadGoods(rules);
            this.changeGoods();
        },
        
        loadGoods:function(rules){
        	$.ajax({
      		  url: window.ctx + "/select2/getGroupGoods",
      		  dataType:'json',
      		  success:function(data){
      			  
      			  $("#goodsSelect").select2({
      				  placeholder: {
      					  id: "-1",
      					  text: "请选择菜品",
      					},
      			      data: data,
      			      templateResult: function(state) {
				    	  if (!state.id) { return state.text; }
				    	  		var $state = $(
					    	    '<span><img src="'+state.img+'" style="height:15px;width:18px" /> ' + state.text + '</span>'
					    	  );
					    	  return $state;
				    	},
				    	tokenSeparators: [',', ' '],
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
      			  
      			  	console.log(rules);
	      			if(rules){
	      				var ruleObjs = JSON.parse(rules);
	            		if(ruleObjs){
	            			console.log(ruleObjs);
	            			console.log(ruleObjs.length);
	            			if(ruleObjs.length > 0){
	            				var goodsIds=[];
	            				$.each(ruleObjs,function(index,obj){
	            						
	            					goodsIds.push(obj.goodsId);
	            				});
	            				$("#goodsSelect").val(goodsIds).trigger("change");
	            				
	            			}
	            		}	
	      			
	      			}
      		  }
      	  })
        	
        },
        changeGoods:function(){
        	var tempText="";
        	var exgistGoods= [];
        	$("#goodsSelect").on("change",function(){
        		
        		var goodsArray = $(this).val();
        		if(goodsArray && goodsArray.length > 0){
        			var  html = "";
        			$.each(goodsArray,function(index,obj){
        				console.log(obj);
        				html += "<li class='active'>"+
                                 	"<div>"+
	                                 	"<span class='label label-green pull-right'>" +
                                 			"<a class='goodsQuantity' data-id='"+obj+"' href='#' data-pk='1' class='editable editable-click'>1</a>" +
                     					"</span>"+
	                                    "<span>"+$("#goodsSelect").find("option[value="+obj+"]").text()+"</span>"+
                                 	"</div>"+
                                 "</li>";
        			});
        			$("#goodsWrapper .nav").empty();
        			$("#goodsWrapper .nav").append(html);
        			
        			//Xeditable----------------------------------- 
        			$.fn.editableform.buttons =
        			    '<button type="submit" class="btn btn-primary btn-sm editable-submit">'+
        			      '<i class="fa fa-fw fa-check"></i>'+
        			    '</button>'+
        			    '<button type="button" class="btn btn-default btn-sm editable-cancel">'+
        			      '<i class="fa fa-fw fa-times"></i>'+
        			    '</button>';
        			
        			//Xeditable----------------------------------- 
        			$('.goodsQuantity').editable({
        			      type: 'text',
        			      pk: 1,
        			      name: 'username',
        			      title: '修正数量',
        			      placement: 'right',
        			      validate: function(value) {
        			      	var reg = new RegExp("^[1-9][0-9]*$");
        			          if($.trim(value) === '') return '数量不能为空';
        			          else if(!reg.test(value)) return '只能输入大于0的整数';
        			       },
        			       success: function(response, newValue) {
        			    	   console.log("newValue:"+newValue);
        			    	   console.log($(this).attr("data-id"));
        			    	   exgistGoods.push({
        			    		   goodsId:$(this).attr("data-id"),
        			    		   quantity:newValue
        			    	   });
        			    	   console.log(JSON.stringify(exgistGoods));
        			       }
        			});
        			//Xeditable----------------------------------- 
        		}else{
        			$("#goodsWrapper .nav").empty();
        		}
        		
        	});
        },
    };

    

