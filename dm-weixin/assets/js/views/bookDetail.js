define(['zepto', 'underscore', 'backbone', 'app/utils', 'app/basket', 'text!templates/bookDetail.html'],

	function($, _, Backbone, utils, basket, page) {

		var $page = $("#bookDetail-page");
		var bookId;

		var PageView = Backbone.View.extend({
			el: $page,

			render: function(id) {
				utils.showPage($page, function() {
					initBookDetail(id);
				});


				bookId = id;
			},

			events: {
				// 跳转到商品详情
				"tap .goods-item": "redirectGoodsDetail",
				// 将商品放置到购物车
				"tap .cart-icon": "putInCart",
				// 雇佣大厨
				"tap .btn-hirecook": "hireCook",

				"tap .btn-cook-cart": "redirectToShoppingCart"
			},

			// 跳转到商品详情
			redirectGoodsDetail: function(e) {
				var $this = $(e.currentTarget);

				window.location.hash = 'goodsDetail/' + $this.data('id');
			},

			// 将商品放置到购物车
			putInCart: function(e) {
				e.stopImmediatePropagation();
				var $this = $(e.currentTarget);

				basket.increase($this.data('id'), 1, 1, function() {
						basket.addCart({"id" : $this.data('id'),"quantity" : 1, "type" : 1});
						$.Dialog.info('已加入菜篮子');
				});
			},

			// 雇佣大厨
			hireCook: function() {
				window.location.hash = 'cooks';
			},

			// 跳转到购物车
			redirectToShoppingCart: function(e) {
				var $this = $(e.currentTarget);
				window.location.hash = 'shoppingCart';
			}
		});

		// 初始化菜谱详情图片
		var initBookDetail = function(bookId) {
			$.ajax({
				url: window.ctx + "book/detail",
				type: "GET",
				data: {
					bookId: bookId
				},
				dataType: "json",
				success: function(data) {
					var template = _.template(page);
					$page.empty().append(template(data));
				}
			});
		}

		return PageView;
	});
