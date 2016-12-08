define(['zepto', 'underscore', 'backbone',
'app/utils', 'text!templates/specials_list.html'],
	function($, _, Backbone, utils, page) {
		var $page = $("#specials-page");
		var $specialsContainer;
		var $itemTemplate;

		var PageView = Backbone.View.extend({
			el: $page,
			render: function() {
				utils.showPage($page, function() {
					if (utils.isEmpty($page)) {
						$page.empty().append(page);

						$specialsContainer = $page.find("#specials-container");
						$itemTemplate      = $page.find("#item-template");

						$.ajax({
							url: window.ctx + "mall/specials",
							type: "GET",
							dataType: "json",
							success: function(data) {
								var template = _.template($itemTemplate.html());
								$specialsContainer.empty().append(template(data));
							}
						});
					}
				});
			},

			events: {
				"tap .he-indspe-list li": "viewDetail"
			},

			/**
			 * 活动详情页
			 */
			viewDetail: function(e) {
				var $this = $(e.currentTarget);
				window.location.hash = "goodsDetail/" + $this.data("id");
			}
		});

		return PageView;
});
