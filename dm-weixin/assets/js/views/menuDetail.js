define(['zepto', 'underscore', 'backbone', 'app/utils', 'app/weixin', 'text!templates/menuDetail.html'],

	function($, _, Backbone, utils, weixin, page) {

		var $page = $("#menu-detail-page");
		var booId;
		var type;

		var PageView = Backbone.View.extend({
			el: $page,

			render: function(menuId, param2) {
				booId = menuId;
				type  = param2;
				utils.showPage($page, function() {
					initMenuDetail(menuId)
				});
			},

			events: {
				// 点击查看菜谱详情
				'tap .book-item': 'redirectToDetail',
				// 删除菜单
				'tap .menu-delete': 'deleteMenu',
				// 关闭遮罩
				'tap .mask-title': 'hideMask'
			},

			hideMask: function(e) {
				var $this = $(e.currentTarget);

				$this.hide();
			},

			deleteMenu: function(e) {
				$.Dialog.confirm('提示', '确认要删除此菜单吗?', function() {
					$.ajax({
						url: window.ctx + 'book/delete',
						type: 'POST',
						data: {
							bookId: booId
						},
						dataType: 'json',
						success: function() {
							$.Dialog.info("删除成功");
							window.history.go(-1);
						}
					});
				}, '放弃', '坚决删除');
			},

			// 点击查看菜谱详情
			redirectToDetail: function(e) {
				window.location.hash = 'bookDetail/' + $(e.currentTarget).data('id');
			}
		});

		var initMenuDetail = function(menuId) {
			$.ajax({
				url: window.ctx + 'book/menuDetail',
				type: 'GET',
				data: {
						menuId: menuId
				},
				dataType: 'json',
				success: function(data) {
					var template = _.template(page);
					$page.empty().append(template($.extend(data, {type: type})));
					bindShareData(data.result.title, menuId);
				}
			});
		};

		var bindShareData = function(title, menuId) {
			var title = title;
			var link = window.serverDomain + '#chooseMenu/' + menuId;
			var desc = '快来点餐吧！';
			var imgURL = window.serverDomain + 'assets/img/logo_big_small.png';
			var success = function() {
				$.Dialog.info('分享成功！');
			};
			var cancel = function() {
				$.Dialog.info("真遗憾...");
			};

			// 分享至微信朋友圈
			weixin.onMenuShareTimeline(title, link, imgURL, success, cancel);
			// 分享至微信好友
			weixin.onMenuShareAppMessage(title, desc, link, imgURL, 'link', '', success, cancel);
			// 分享至QQ
			weixin.onMenuShareQQ(title, desc, link, imgURL, success, cancel);
			// 分享至QQ空间
			weixin.onMenuShareQZone(title, desc, link, imgURL, success, cancel);
		};

		return PageView;
	});
