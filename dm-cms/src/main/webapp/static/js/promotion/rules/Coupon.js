

    var rulesCoupon = {

        /** 初始化函数 */
        init: function(rules) {
            this.initData(rules);
            this.couponOperate();
            this.cloneCoupon();
            this.removeCoupon();
        },
        
        initData : function(rules){
        	
        	if(rules){
        		var ruleObjs = JSON.parse(rules);
        		if(ruleObjs){
        			console.log(ruleObjs);
        			console.log(ruleObjs.length);
        			if(ruleObjs.length > 0){
        				var $initTemplate = $("#rules-default-template");
        				var wrapper = $initTemplate.parent();
        				$.each(ruleObjs,function(index,obj){
        					var couponObj = obj.coupon;
        					var appendItem = $initTemplate.clone().removeAttr('id');
        					
        					appendItem.find('[aria-describedby]').each(function(){
        						$(this).attr('aria-describedby',$(this).attr('aria-describedby').replace(/\d+/,index));
        					});
        					appendItem.find('[name]').each(function(){
        						$(this).attr('name',$(this).attr('name').replace(/\d+/,index));
        					});
        					
        					appendItem.find("input[name^='rule_title']").val(couponObj.title);
        					appendItem.find("textarea[name^='rule_desc']").val(couponObj.desc);
        					appendItem.find("input[name^='rule_denomination']").val(couponObj.denomination);
        					appendItem.find("input[name^='rule_restrictionMoney']").val(couponObj.restrictionMoney);
        					appendItem.find("input[name^='rule_expired']").val(couponObj.expired);
        					appendItem.find(".quantity").val(obj.quantity);
        					appendItem.find("input[name^='orderMoney']").val(obj.orderMoney);
        					wrapper.prepend(appendItem);
        				});
        				$initTemplate.find('[name]').each(function(){
        					$(this).attr('name',$(this).attr('name').replace(/\d+/,ruleObjs.length));
        				});
        				$initTemplate.addClass("hide");
        				$initTemplate.find('input').each(function(){
        					$(this).addClass("ignore");
        				});
        			}
        			
        		}
        		
        	}
        	
        },
        
        couponOperate:function(){
        	
        	$(document).on("click",".quantityAdd",function(){
        		var t=$(this).parent().parent().find('.quantity');
        		t.val(parseInt(t.val())+1);
        		if(isNaN(t.val())){
        			t.val(1);
        		}
        	});
        	
        	$(document).on("click",".quantityMin",function(){
        		var t=$(this).parent().parent().find('.quantity'); 
        		t.val(parseInt(t.val())-1);
        		if(parseInt(t.val())<=0 || isNaN(t.val())){ 
        			t.val(1);
        		}
        	});
        	
        	$(document).on("keyup",".quantity",function(){
        		var t=$(this);
        		t.val(parseInt(t.val()));
        		if (isNaN(t.val()) || parseInt(t.val()) <= 0) {
        	        t.val(1);
        	    }
        	    if (t.val(parseInt(t.val())) != t.val()) {
        	        t.val(parseInt(t.val()));
        	    }
        	});
        	
        },
        cloneCoupon:function(){
        	
        	/*动态添加优惠活动规则开始*/
        	$("#addRules").on("click",function(){
        		
        		var $addIcon = $(this);
        		
        		var template = $addIcon.attr('data-template');
        		
        		 //如果元素为隐藏,则将它显现
        		if($(template).is(":hidden")){
        			$(template).find('[class]').each(function(){
        				//console.log($(this));
        				$(this).removeClass("ignore");
        			});
        			$(template).removeClass("hide");
        		       return;
        		}
        		//父容器
        		var wrapper = $(template).parent();
        		
        		var msg="标题,描述,面额,限制金额,有效期必填,不能为空。";
        		var dangerDialog = function(msg){
        			return '<div class="alert alert-danger alert-dismissible fade in" style="margin-bottom:0px">'+
        							   '<a href="" class="close" data-dismiss="alert">&times;</a><strong>警告！</strong>'+msg+
        				   '</div>';
        		};
        		
        		var $validateObj = $(".rules:last-child");
        		//验证是否填写必要内容
        		 //rule_title,rule_desc,rule_denomination,,rule_restrictionMoney,rule_expired
        		var titleVal = $validateObj.find("input[name^='rule_title']").val();
        		var descVal = $validateObj.find("textarea[name^='rule_desc']").val();
        		var denominationVal = $validateObj.find("input[name^='rule_denomination']").val();
        		var restrictionMoneyVal = $validateObj.find("input[name^='rule_restrictionMoney']").val();
        		var expiredVal = $validateObj.find("input[name^='rule_expired']").val();
        		$(".alert.alert-danger").remove();
        		if( !titleVal || !descVal || !denominationVal || !restrictionMoneyVal || !expiredVal){
        			$(dangerDialog(msg)).insertBefore($validateObj);
        			$validateObj.addClass("errBorder");
        			return;
        		}else{ 
        			/* var reg1 = new RegExp("^([1-9][0-9]*)(\.[0-9])?[0-9]*$"); */
        			var reg2 = new RegExp("^([1-9][0-9]*)$");
        			if(!reg2.test(denominationVal) || !reg2.test(restrictionMoneyVal) || !reg2.test(expiredVal)){
        				msg = "面额，限制金额，有效期只能是正整数";
        				$(dangerDialog(msg)).insertBefore($validateObj);
        				$validateObj.addClass("errBorder");
        				return;
        			} 
        			$(".rules").removeClass("errBorder");
        			$(".close").click();
        			var item = $(template).clone().removeAttr('id');
        			var currentLength = wrapper.find(".rules").length;
        			item.find('[id]').each(function(){
        				$(this).attr('id',$(this).attr('id').replace(/\d+/,currentLength));
        			});
        			item.find('[aria-describedby]').each(function(){
        				$(this).attr('aria-describedby',$(this).attr('aria-describedby').replace(/\d+/,currentLength));
        			});
        			item.find('[name]').each(function(){
        				$(this).attr('name',$(this).attr('name').replace(/\d+/,currentLength));
        			});
        			item.find("input").each(function(){
        				$(this).val('');
        			});
        			item.find("textarea").each(function(){
        				$(this).val('');
        			});
        			item.find(".quantity").val("1");
        			item.find(".orderMoney").val("");
        			item.find(".has-error[id$='-error']").remove();
        			item.find(".has-error").removeClass(".has-error");
        			wrapper.append(item);
        		  } 
        	});
        	/*动态添加优惠活动规则结束*/
        	
        },
        removeCoupon : function(){
	        	/*删除优惠活动规则开始*/
	        	$(document).on('panel.remove','.panel.panel-demo',function(event, panel, deferred){
	        	      Dialog.confirm("删除提示", "确认要删除该项活动规则吗？", function(result) {
	        				if(result == true){
	        					deferred.resolve();
	        				}
	        			});
	        	}).on('panel.removed', function(event, panel){
	        	
	        	}); 
	        	/*删除优惠活动规则结束*/
        }
    };

    

