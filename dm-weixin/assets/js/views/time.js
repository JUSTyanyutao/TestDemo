define(['zepto', 'underscore', 'backbone', 'app/utils', 'text!templates/time.html'], function($, _, Backbone, utils, page) {

	var $page = $("#time-page");
	var $template;
	var $today;

	var PageView = Backbone.View.extend({
		el: $("#time-page"),
		
		render: function() {
			utils.showPage(this.$el, function() {
				if (utils.isEmpty($page)) {
					$page.empty().append(page);
					$template = $page.find("#time-template");
					$today = $page.find("#today");

					var today = new Date();
					$today.text(today.getFullYear() +'-'+ (today.getMonth()+1) +'-'+ today.getDate());


					$.ajax({
						url: window.ctx + "/balance/getTimeList",
						type: "GET",
						dataType: "json",

						success: function(data) {
							var template = _.template($template.html());
							$page.find("#desc").html(data.result.desc);
							$page.find(".delivery-times-lists").empty().append(template(data));
						}
					});
				}
			});
		},
		events: {
			// 点击时间段
			"tap .time-section": "chooseTimeSectionAction"
		},

		// 选择时间段
		chooseTimeSectionAction: function(e) {
			var $this = $(e.currentTarget);
			$page.find(".time-section").find("i").removeClass('state');
			$this.find("i").addClass("state");
			$this.parent().parent("li").addClass("bg-change").siblings("li").removeClass("bg-change");

			// 从localstorage中读出订单数据，并更新派送时间段
			var balance = $.extend({}, JSON.parse(utils.storage.get('balance')), {
				deliveryDate: $this.data('date'),
				deliverySection: $this.data('section')
			});

			utils.storage.set('balance', JSON.stringify(balance));

			// 跳转回到下单页面
			window.setTimeout(function() {
				window.location.replace("#balance");
			}, 100);
		}
	});

	return PageView;
});