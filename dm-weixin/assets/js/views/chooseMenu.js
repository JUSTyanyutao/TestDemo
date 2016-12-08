define(['zepto', 'underscore', 'backbone', 'app/utils', 'frozen', 'app/menus', 'text!templates/chooseMenu.html'],

	function($, _, Backbone, utils, Frozen, Menus, page) {

		var $page = $("#choose-menu-page");
		var $categoryTemplate;
		var $categoryContainer;
		var $bookContainer;
		var $bookListTemplate;
		var tab = false;
		var menuId;

		var PageView = Backbone.View.extend({
			el: $page,

			render: function(id) {
				utils.showPage($page, function() {
					menuId = id;

					$page.empty().append(page);

					$categoryTemplate  = $page.find("#category-template");
					$categoryContainer = $page.find(".ui-tab-nav");
					$bookContainer     = $page.find(".ui-tab-content");
					$bookListTemplate  = $page.find("#book-list-template");

					initBookCategories();

					initMenu();
				});
			},

			events: {
				// 选择菜谱
				"tap .book-item": 'selectBook',
				// 下一步，提交菜单
				'tap .btn-next': 'redirectToSubmit'
			},

			// 下一步，提交菜单
			redirectToSubmit: function() {
				if (Menus.getBookIds() == '') {
					$.Dialog.info("请至少选择一个菜谱");
					return;
				}
				window.location.hash = 'submitMenu/' + menuId;
			},

			// 选择菜谱
			selectBook: function(e) {
				var $this = $(e.currentTarget);


				if ($this.find(".hook").length > 0) {
					return $.Dialog.info("该菜谱已经被选择");
				}

				if ($this.find(".mask").html()) {
					$this.find(".mask").remove();
					Menus.remove($this.data('id'));
				} else {
					$this.append("<div class=\"mask\"><img src=\"assets/img/sure@3x.png\" alt=\"\"/></div>")
					Menus.choose(
						$this.parent().parent().data('id'),
						$this.parent().parent().data('name'),
						$this.data('id'),
						$this.data('name'),
						$this.data('pic')
						);
				}


			}
		});


		// 初始化菜谱分类
		var initBookCategories = function() {
			$.ajax({
				url: window.ctx + 'book/categories',
				type: 'GET',
				dataType: 'json',
				success: function(data) {
					var template = _.template($categoryTemplate.html());
					$categoryContainer.empty().append(template(data));

					var lis = [];
					for (var i = 0; i < data.result.length; i++) {
						lis.push("<li data-name=\""+data.result[i].name+"\" data-id=\""+ data.result[i].id +"\" class='list-"+data.result[i].id+"'></li>");
					}
					$bookContainer.empty().append(lis.join(""));

					if (tab) {
						tab.destroy();
					}

					tab = new fz.Scroll('.ui-tab', {
				        role: 'tab',
				        autoplay: false
				    });

				    tab.on("beforeScrollStart", function(from, to) {
					    	if (from != to) {
					    		var categoryId = $categoryContainer.find("li").eq(to).data("id");
					    		getBookList(menuId, categoryId, 1, 200);
					    	}
				    });

				    // 加载第一个分类
				    getBookList(menuId, $categoryContainer.find("li").eq(0).data('id'), 1, 200);
				}
			});
		};

		// 查询菜谱列表
		var getBookList = function(shareId, categoryId, page, pageSize) {
			if (!utils.isEmpty($bookContainer.find(".list-" + categoryId))) {
				return;
			}
			$.ajax({
				url: window.ctx + "book/findShareList",
				type: "GET",
				data: {
					shareId: shareId,
					categoryId: categoryId,
					page: page,
					pageSize: pageSize
				},
				dataType: "json",
				success: function(data) {
					var template = _.template($bookListTemplate.html());
					$bookContainer.find(".list-"+categoryId).empty().append(template({ids: Menus.getBookIds(), data: data}));
				}
			});
		};

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

					if (data.result.status == 2) {
						$.Dialog.alert('提示', '客人您好，当前点菜时间已经结束，下次记得早点~', function() {
							window.location.replace('#menuDetail/' + menuId);
						});
						return;
					}
				}
			});
		};

		return PageView;
	});
