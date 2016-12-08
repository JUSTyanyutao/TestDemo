define(['zepto', 'underscore', 'backbone', 'app/utils', 'text!templates/menus.html'],

	function($, _, Backbone, utils, page) {

		var $page = $("#menus-page");
		var type;

		var PageView = Backbone.View.extend({
			el: $page,

			render: function(param) {
				type = param;
				utils.showPage($page, function() {

					initMenus(1, 200);
				});
			},

			events: {
				// 跳转到菜单详情
				'tap .menu-item': 'redirectDetail'
			},

			// 跳转到菜单详情
			redirectDetail: function(e) {
				var $this = $(e.currentTarget);
				window.location.hash = 'menuDetail/' + $this.data('id') + (type == 'join' ? '/join' : '');
			}
		});


		// 初始化我的菜单
		var initMenus = function(pg, pageSize) {
			if (type == 'join') {

			}
			$.ajax({
				url: type == 'join' ? (window.ctx + "book/join") : (window.ctx + 'book/findMenuList'),
				type: 'GET',
				data: {
					page: pg,
					pageSize: pageSize
				},
				dataType: 'json',
				success: function(data) {
					var template = _.template(page);
					$page.empty().append(template($.extend(data, {type: type})));
				}
			})

		};

		return PageView;
	});
