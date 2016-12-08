define(['zepto', 'underscore', 'backbone', 'echo','app/utils', 'app/basket.min', 'app/favorite.min', 'frozen', 'text!templates/merchant.html', 'app/weixin'], 

function($, _, Backbone, echo,utils, basket, favorite, Frozen, page, weixin) {

	var $page = $("#merchant-page");
	var merchantId;
	var imageRenderToken = null;
	var $collectNumber;
	var $back;
	var PageView = Backbone.View.extend({
		el: $page,

		render: function(id) {

			merchantId = id;
			utils.showPage(this.$el, function() {
				initMerchant(id);
			});
		},

		events: {
			// 添加到购物车
			"tap .car": "addToBasket",
			// 收藏商品
			"tap .btn-collect": "collectMerchant",
			"tap .he-header"  : "goBack",
			"tap .text-click": "text",
		},

		text: function(){
			console.log(12346);
			 window.location.replace(window.ctx + "#main");
		},

		//
		goBack: function(e){
			console.log(1234);
			 window.location.replace(window.ctx + "#main");
		},

		// 添加到购物车
		addToBasket: function(e) {
			e.stopImmediatePropagation();

			var $this = $(e.currentTarget);

			basket.increase($this.data('id'), 1, 1, function(data) {
	            $.Dialog.info("成功添加到菜篮子");
	        });
		},

		// 收藏商品
		collectMerchant: function(e) {
			e.stopImmediatePropagation();

			var $this = $(e.currentTarget);

			if ($this.hasClass('collected')) {
				// 取消收藏
				favorite.del(merchantId, 4, function(data) {
					$this.removeClass('collected').text('收藏店铺');
					$collectNumber.text(parseInt($collectNumber.text()) - 1);
				});
				
			} else {
				// 收藏
				favorite.add(merchantId, 4, function(data) {
					$this.addClass('collected').text('取消收藏');	
					$collectNumber.text(parseInt($collectNumber.text()) + 1);
				});
			}
		}
	});

	

	// 加载商铺数据
	var initMerchant = function(merchantId) {
		//$("#iclickMe").click();
		
		$.ajax({
			url: window.ctx + 'merchant/detail',
			type: "GET",
			data: {
				merchantId: merchantId
			},
			dataType: "json",
			success: function(data) {
				var template = _.template(page);
				$page.empty().append(template(data));
				asynLoadImage();
				$collectNumber = $page.find(".collect-number");
				//$back		   = $page.find(".he-header");
				var tab = new fz.Scroll('.ui-tab', {
			        role: 'tab',
			        autoplay: false
			    });

				var title = '怎么吃';
				var desc = data.result.name + '，赶快来看看吧';
				var link = window.serverDomain + '/#merchant/' + data.result.id;
				var imgURL = window.serverDomain + '/assets/img/logo_big_small.png';
				var success = function() {
					$.Dialog.info("感谢您的分享");
				};
			    weixin.onMenuShareTimeline(title, link, imgURL, success);
			    weixin.onMenuShareAppMessage(title, desc, link, imgURL, '', '', success);
			    //document.getElementById("clickMe").click();
			    //$("#clickMe").unbind();
			    //document.getElementById("clickMe").unbind();
			}
		});
		
		//document.getElementById("#clickMe").click();
	};

	var asynLoadImage = function() {
            // setTimeout(function() {
                echo.init({
                    throttle: 250,
                });

                if (imageRenderToken == null) {
                    imageRenderToken = window.setInterval(function() {
                        echo.render();
                    }, 350);
                }
            // }, 360);
        };


	return PageView;
});
