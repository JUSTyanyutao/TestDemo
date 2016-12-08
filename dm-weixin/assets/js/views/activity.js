define(['zepto', 'underscore', 'backbone',
	'echo',
	'app/utils', 'text!templates/activity.html'],
	function($, _, Backbone, Echo, utils, page) {

		var $page = $("#activity-page");
		var $activityContainer;
		var $itemTemplate;
		var imageRenderToken;
		var $type;

		var PageView = Backbone.View.extend({
			el: $page,

			render: function(type) {
				utils.showPage($page, function() {
					$type = type;
					if (utils.isEmpty($page)) {
						$page.empty().append(page);
						if(null != $type && typeof($type) !== 'undefined'){
								$(".he-header").hide();
								$page.css("padding-top","0px");
						}
						$activityContainer = $page.find("#activity-container");
						$itemTemplate      = $page.find("#item-template");

						$.ajax({
							url: window.ctx + "consulting/lists",
							type: "GET",
							dataType: "json",
							data : {type : 1},
							success: function(data) {
								var template = _.template($itemTemplate.html());
								$activityContainer.empty().append(template(data));
								asynLoadImage();
							}
						});
					}
				});
			},

			events: {
				"tap #activity-container img": "viewDetail"
			},

			/**
			 * 活动详情页
			 */
			viewDetail: function(e) {
				var $this = $(e.currentTarget);
				window.location.hash = "activity/detail/" + $this.data("id");
			}
		});

    var asynLoadImage = function() {
          Echo.init({
              throttle: 250
          });
          if (imageRenderToken == null) {
              imageRenderToken = window.setInterval(function() {
                  Echo.render();
              }, 350);
          }
    };
		return PageView;
});
