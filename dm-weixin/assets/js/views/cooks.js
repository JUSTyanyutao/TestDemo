define(['zepto', 'underscore', 'backbone', 'echo', 'app/utils', 'text!templates/cooks.html'],

	function($, _, Backbone, echo, utils, page){

		var $page = $("#cooks-page");

		var PageView = Backbone.View.extend({
			el: $page,

			render: function() {
				utils.showPage(this.$el, function() {
					// if (utils.isEmpty($page)) {
						getCookList();
					// }
				});
			},

			events: {
				// 跳转到大厨详情页
				"tap .cook-item": "redirectToDetail"
			},

			// 跳转到大厨详情页
			redirectToDetail: function(e) {
				var $this = $(e.currentTarget);
				var cookId = $this.data('id');

				window.location.hash = 'cook/' + cookId;
			}

		});

		// 查询大厨列表信息
		var getCookList = function() {
			$.ajax({
				url: window.ctx + "/cook/lists",
				type: "GET",
				dataType: "json",
				success: function(data) {
					var template = _.template(page);
					$page.empty().append(template(data));
					initEcho();
				}
			});
		};

		var initEcho = function() {
            setTimeout(function() {
                echo.init({
                    throttle: 1000
                });
            }, 360);

        };


		return PageView;
	});
