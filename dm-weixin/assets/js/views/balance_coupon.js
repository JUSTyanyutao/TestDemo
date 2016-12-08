define(['zepto', 'underscore', 'backbone', 'app/utils', 'text!templates/balance_cupon.html'], 
	function($, _, Backbone, utils, page) {

	var $page = $("#balance-coupon-page");

	var PageView = Backbone.View.extend({
		el: $("#balance-coupon-page"),
		render: function() {
			utils.showPage(this.$el, function() {
				if (utils.isEmpty($page)) {
					$page.empty().append(page);
					initCoupon();
					utils.showHeader({title : "选择优惠券"});
				}
			});
		},
		events: {
			// 点击优惠券
			"tap .coupon-item": "chooseCouponAction"	
		},

		// 选择优惠券
		chooseCouponAction: function(e) {
			var $this    = $(e.currentTarget);
			var couponId = $this.data("id");
			var name     = $this.data("name");

			// 从storage中读取下单信息
			var balance = JSON.parse(utils.storage.get("balance"));

			verifyCoupon(couponId, balance.deliveryWayId, balance.list, function() {
				// 更新storage中的balance信息
				balance = $.extend(balance, {
					couponId: couponId,
					couponName: name
				});
				utils.storage.set("balance", JSON.stringify(balance));

				window.location.replace("#balance");
			});
		}
	});

	// 初始化优惠券
	var initCoupon = function() {
		var template = _.template($page.find("#tpl-coupon").html());
		$page.find(".coupons-lists").append(template(JSON.parse(utils.storage.get("balance_coupons"))));
	};

	/**
	 * 校验优惠券
	 */
	var verifyCoupon = function(couponId, deliveryWayId, list, callback) {
		$.ajax({
			url: window.ctx + "balance/verifyCoupon",
			type: "GET",
			dataType: "json",
			data: {
				couponId: couponId,
				deliveryWayId: deliveryWayId,
				list: list
			},
			success: function(data) {
				if (typeof callback == "function") {
					callback();
				}
			}
		});
	}

	return PageView;
});