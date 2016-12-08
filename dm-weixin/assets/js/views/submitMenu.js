define(['zepto', 'underscore', 'backbone', 'app/utils', 'app/menus', 'text!templates/submitMenu.html'],

	function($, _, Backbone, utils, Menus, page) {

		var $page = $("#submit-menu-page");
		var menuId;

		var PageView = Backbone.View.extend({
			el: $page,

			render: function(id) {
				utils.showPage($page, function() {
					menuId = id;

					var template = _.template(page);
					$page.empty().append(template({menus: Menus.get()}));

					initMenu();
				});
			},

			events: {
				// 编辑菜谱
				'tap .btn-edit': 'editBooks',
				// 删除菜谱
				'tap .btn-delete': 'deleteBook',
				// 保存菜单
				'tap .btn-submit': 'submitMenu'
			},

			// 保存菜单
			submitMenu: function() {
				var ids = Menus.getBookIds();
				$.ajax({
					url: window.ctx + 'book/submitMenu',
					type: 'POST',
					data: {
						menuId: menuId,
						bookIds: ids
					},
					dataType: 'json',
					success: function(data) {
						$.Dialog.info('点菜成功');
						Menus.clear();
						window.location.replace('#menuDetail/' + menuId);
					}
				});
			},

			// 编辑菜谱
			editBooks: function(e) {
				var $this = $(e.currentTarget);

				if ($this.hasClass('red')) {
					$this.text('确定').removeClass('red');
					$this.parent().siblings().addClass('edit');
				} else {
					$this.text('删除').addClass('red');
					$this.parent().siblings().removeClass('edit');
				}
			},

			// 删除菜谱
			deleteBook: function(e) {
				var $this = $(e.currentTarget);
				Menus.remove($this.data('id'));
				if ($this.parent().siblings().length == 1) {
					$this.parent().parent().parent().remove();
					return;
				}
				$this.parent().remove();

			}	
		});

		var initMenu = function() {
			$.ajax({
				url: window.ctx + 'book/menuDetail',
				type: 'GET',
				data: {
					menuId: menuId
				},
				dataType: 'json',
				success: function(data) {
					$page.find(".menu-title").text(data.result.title);
					$page.find(".label-start-time").text(data.result.dinner_time);

					if (data.result.status == 2) {
						$.Dialog.alert('提示', '对不起，当前宴请已经结束', function() {
							window.location.replace('#menuDetail/' + menuId);
						});
						return;
					}
				}
			});
		};

		return PageView;
	});