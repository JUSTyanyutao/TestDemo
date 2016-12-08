define(['zepto', 'underscore', 'backbone', 'app/utils', 'text!templates/signin.html'],
function($, _, Backbone, utils, page) {

	var $page = $("#signin-page")
	var $todayPoints;
	var $signedDays;
	var $totalPoints;
	var $ruleContainer;

	var PageView = Backbone.View.extend({
		el: $page,
		render: function() {
			utils.showPage($page, function() {
				$page.empty().append(page);

				$signedDays    = $page.find(".days");
				$totalPoints   = $page.find(".totalPoints");
				$todayPoints   = $page.find(".todayPoints");

				signin(0);
				event.sign();

				// 积分规则
				$.ajax({
					url: window.ctx + "/personalCenter/getPointRules",
					type: "GET",
					dataType: "json",
					success: function(data) {
						$page.find(".he-sign-footer").find(".content").empty().append(data.result.content);
					}
				})
			});
		}
	});

	var event = {
			sign : function(){
				$page.on("tap",".he-sign-top .sign",function(){
						signin(1);
				});
			}
	}
	var signin = function(signin) {
			$.ajax({
				url: window.ctx + "/personalCenter/signin",
				data: {
					signin: signin
				},
				type: "GET",
				dataType: "json",
				success: function(data) {
					var result = data.result;
					$signedDays.text(result.signin_times);
					$totalPoints.text(result.total_points);
					$todayPoints.text(result.points);

					if (result.has_signed) {
						$page.find(".sign").text("已签到");
						$page.off("tap",".he-sign-top .sign");
						$page.find(".tomorrowPoints").text(result.tomorrow_points);
					} else {
						$page.find(".tomorrowPoints").text(result.tomorrow_points);
					}
				}
			});
	};
	return PageView;
});
