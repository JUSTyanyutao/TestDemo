define(['zepto', 'underscore',
'backbone', 'app/utils',
'text!templates/activityDetail.html'],
	function($, _, Backbone, utils, page) {

		var $page = $("#activity-detail-page");
		var $activityDetailContainer;
		var $detailTemplate;

		var PageView = Backbone.View.extend({
			el: $page,

			render: function(id) {
				utils.showPage($page, function() {
					$page.empty().append(page);

					$activityDetailContainer  = $page.find("#activity-detail-container");
					$detailTemplate           = $page.find("#detail-template");

					$.ajax({
						url: window.ctx + "consulting/detail/" + id,
						type: "GET",
						dataType: "json",
						success: function(data) {
							var template = _.template($detailTemplate.html());
							$activityDetailContainer.empty().append(template(data));
						}
					});
				});
			}
		});

		return PageView;
});
