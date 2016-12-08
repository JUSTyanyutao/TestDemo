define(['zepto', 'underscore', 'backbone', 'app/utils', 'frozen', 'text!templates/book.html'],

	function($, _, Backbone, utils, Frozen, page) {

		var $page = $("#book-page");
		var $categoryTemplate;
		var $categoryContainer;
		var $bookContainer;
		var $bookListTemplate;
		var tab = false;

		var PageView = Backbone.View.extend({
			el: $page,

			render: function() {
				utils.showPage($page, function() {
					if (utils.isEmpty($page)) {
						$page.empty().append(page);

						$categoryTemplate  = $page.find("#category-template");
						$categoryContainer = $page.find(".ui-tab-nav");
						$bookContainer     = $page.find(".ui-tab-content");
						$bookListTemplate  = $page.find("#book-list-template");

						initBookCategories();

					} else {

					}
				});
			},

			events: {
				// 点击宴请
				"tap .btn-share": "toggleMenu",
				// 点击菜谱
				"tap .book-item": "viewDetail",
				// 跳转到我的菜单
				"tap .menus": "redirectMenus",
				// 分享菜单
				"tap .share-menu": "redirectShareMenu",
				// 我参会的
				'tap .menus-participate' : 'redirectJoinMenus',

				'tap .he-book-search' : 'redirectSearch'
			},

			// 我参会的
			redirectJoinMenus: function() {
				window.location.hash = 'menus/join';
			},

			// 分享菜单
			redirectShareMenu: function() {
				window.location.hash = 'shareMenu';
			},

			// 跳转到我的菜单
			redirectMenus: function() {
				window.location.hash = 'menus';
			},
			//跳转到菜谱搜索
			redirectSearch:function(){
					window.location.hash = 'search/1/3';
			},

			// 点击宴请，切换面板
			toggleMenu: function() {
				var $share = $page.find(".he-menuind-eject");
				if ($share.css("display")=="none"){
			        $share.show();
			    } else {
			        $share.hide();
			    }
			},

			// 点击菜谱，查看详情
			viewDetail: function(e) {
				var $this = $(e.currentTarget);
				$page.find(".he-menuind-eject").hide();
				window.location.hash = 'bookDetail/' + $this.data('id');
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
						lis.push("<li class='list-"+data.result[i].id+"'></li>");
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
				    		getBookList(categoryId, 1, 200);
				    	}
				    });

				    // 加载第一个分类
				    getBookList($categoryContainer.find("li").eq(0).data('id'), 1, 200);
				}
			});
		};

		// 查询菜谱列表
		var getBookList = function(categoryId, page, pageSize) {
			if (!utils.isEmpty($bookContainer.find(".list-" + categoryId))) {
				return;
			}
			$.ajax({
				url: window.ctx + "book/findList",
				type: "GET",
				data: {
					categoryId: categoryId,
					page: page,
					pageSize: pageSize
				},
				dataType: "json",
				success: function(data) {
					var template = _.template($bookListTemplate.html());
					$bookContainer.find(".list-"+categoryId).empty().append(template(data));
				}
			});
		}

		return PageView;
	});
