define(['zepto', 'underscore', 'backbone', 'app/utils', 'text!templates/points.html'], 

function($, _, Backbone, utils, page) {

	var $page = $("#points-page");
	var $points;
	var $desc;
	var $template;
	var $container;


	var PageView = Backbone.View.extend({
		el: $page,

		render: function() {
			utils.showPage($page, function() {
				$page.empty().append(page);
				$points    = $page.find("#title").find("span");
				$desc      = $page.find("#desc");
				$template  = $page.find("#item-template");
				$container = $page.find("#points-container");

				$.ajax({
					url: window.ctx + "personalCenter/points",
					type: "GET",
					dataType: "json",
					success: function(data) {
						$points.text(data.result.total_points);
						if (data.result.remark) {
							$desc.text(data.result.remark);
						}

						var template = _.template($template.html());
						$container.empty().append(template(data.result));
						// var member_points_list = [
						// 	{create_time: "12-06 12:14", points: 5, points_remark: "订单积分", use_type: 1},
						// 	{create_time: "12-10 16:34", points: 15, points_remark: "积分抵扣", use_type: 2}
						// ]
						// $container.empty().append(template({member_points_list:member_points_list}));
					}
				})
			});
			utils.showHeader({title: "蕲农币明细"});
		}
	});


	return PageView;
});