define(['zepto', 'underscore', 'backbone', 'app/utils', 'text!templates/record.html'], function($, _, Backbone, utils, page) {

	var $page = $("#record-page");
	var $template;

	var PageView = Backbone.View.extend({
		el: $("#record-page"),

		render: function() {
			utils.showPage(this.$el, function() {
				if (utils.isEmpty($page)) {
					$page.empty().append(page);

					$template = $page.find("#template-record");

					$.ajax({
						url: window.ctx + "/personalCenter/bill",
						type: "GET",
						dataType: "json",

						success: function(data) {
							//console.info(data);
							var template = _.template($template.html());
							$page.find("#record-container").empty().append(template(data.result));
						}
					});
				}
			});
		},
		events: {
			"tap .record-item"  : "detail"
		},

		detail: function(e) {
			var $this = $(e.currentTarget);
			var source = $this.data("source");

			if (source == 2) {
				// 订单
				window.location.hash = "orderDetail/" + $this.data("goods-id");
			}

		}
	});

	return PageView;
});
