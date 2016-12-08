define(['zepto', 'underscore', 'backbone', 'app/utils', 'app/basket.min', 'text!templates/search.html'],
	function($, _, Backbone, utils, basket, page) {

		var $page = $("#search-page");
		var $historyKeyContainer;
		var $historyKeyTemplate;
		var $searchContainer;
		var $goodsItemTemplate;
		var $merchantItemTemplate;
		var $cookbookItemTemplate;
		var $type;
		var $keyword;

		var pageView = Backbone.View.extend({
			el: $page,

			render: function(refresh, searchType) {
				utils.showPage($page, function() {
					if (refresh) {
						$page.empty();
					}

					if (utils.isEmpty($page)) {
						$page.empty().append(page);

						$historyKeyContainer  = $page.find(".history-key-container");
						$historyKeyTemplate   = $page.find("#history-key-template");
						$searchContainer      = $page.find(".search-container");
						$goodsItemTemplate    = $page.find("#goods-item-template");
						$merchantItemTemplate = $page.find("#merchant-item-template");
						$cookbookItemTemplate = $page.find("#cookbook-item-template");
						$type                 = $page.find("#type");
						$keyword              = $page.find("#keyword");

						if(typeof searchType !== 'undefined' && searchType == 3){
								$type.val(searchType);
						}
						SearchKeyword.init();
						showKeywordsHistory();
					}
				});
			},

			events: {
				// 返回
				'tap .btn-cancel': 'back',
				// 搜索
				'tap .btn-search': 'search',
				// 监听关键字
				'input #keyword': 'listenKeyword',
				// 搜索历史关键字
				'tap .history-keyword': 'searchByHistory',
				// 跳转到商品详情
				'tap .goods-item': 'redirectToGoodsDetail',
				// 跳转到店铺页面
				'tap .merchant-item': 'redirectToMerchant',
				// 跳转到菜谱详情页面
				'tap .book-item': 'redirectToCookbook'
			},

			// 跳转到店铺页面
			redirectToMerchant: function(e) {
				var $this = $(e.currentTarget);

				window.location.hash = 'merchant/' + $this.data('id');
			},

			// 跳转到菜谱
			redirectToCookbook: function(e) {
				var $this = $(e.currentTarget);
				window.location.hash = 'bookDetail/' + $this.data('id');
			},

			// 监听关键字
			listenKeyword: function(e) {
				var $this = $(e.currentTarget);

				if ($.trim($this.val()) == '') {
					$searchContainer.addClass('eh-hide');
					$historyKeyContainer.removeClass("eh-hide");
				} else {
					$searchContainer.empty().removeClass('eh-hide');
					$historyKeyContainer.addClass("eh-hide");
				}
			},

			// 跳转到商品详情
			redirectToGoodsDetail: function(e) {
				var $this = $(e.currentTarget);
				window.location.hash = 'goodsDetail/' + $this.data('id');
			},

			// 搜索历史关键字
			searchByHistory: function(e) {
				var $this = $(e.currentTarget);

				$type.val($this.data('type'));
				$keyword.val($this.data('keyword'));

				$keyword.trigger('input');
				$page.find(".btn-search").trigger('tap');

			},

			// 搜索
			search: function() {
				var type    = $type.val();
				var keyword = $.trim($keyword.val());

				if (keyword == '') {
					$.Dialog.info('请输入搜索的关键字');
					return;
				}

				// searchByKeyword(keyword, type, function(){
				// 		// 保存关键字
				// 		SearchKeyword.set(type, keyword);
				// });
				if (type == 1) {
					searchByGoods(keyword, function() {
						// 保存关键字
						SearchKeyword.set(type, keyword);
					});
				} else if (type == 2) {
					searchByShop(keyword, function() {
						// 保存关键字
						SearchKeyword.set(type, keyword);
					});
				} else {
					searchByCookbook(keyword, type, function(){
							// 保存关键字
							SearchKeyword.set(type, keyword);
					});
				}
			},

			// 返回
			back: function() {
				window.history.go(-1);
			}

		});

		//菜谱搜索
		var searchByCookbook = function(keyword, type, callback){
				$.ajax({
					url: window.ctx + "book/searchByName",
					type: 'GET',
					data: {
							name: keyword
					},
					dataType: 'json',
					success: function(data) {
						var template = _.template($cookbookItemTemplate.html());
						$searchContainer.empty().append(template(data));
						typeof callback == 'function' && callback(data);
					}
			});
		};

		// 商品搜索
		var searchByGoods = function(keyword, callback) {
			$.ajax({
				url: window.ctx + "mall/searchByName",
				type: 'GET',
				data: {
					name: keyword
				},
				dataType: 'json',
				success: function(data) {
					var template = _.template($goodsItemTemplate.html());
					$searchContainer.empty().append(template(data));

					typeof callback == 'function' && callback(data);
				}
			});
		};

		// 店铺搜索
		var searchByShop = function(keyword, callback) {
			$.ajax({
				url: window.ctx + 'merchant/search',
				type: 'GET',
				data: {
					name: keyword
				},
				dataType: 'json',
				success: function(data) {
					var template = _.template($merchantItemTemplate.html());
					$searchContainer.empty().append(template(data));

					typeof callback == 'function' && callback(data);
				}
			});
		};

		// 显示搜素关键字
		var showKeywordsHistory = function() {
			var template = _.template($historyKeyTemplate.html());
			$historyKeyContainer.empty().append(template(SearchKeyword.findAll()));
		};

		// 搜索关键字
		var SearchKeyword = {
			/**
			 * 关键字
			 * [
			 * 	{
			 * 		type: 1,
			 * 		keyword: '大白菜'
			 * 	},
			 * 	{
			 * 		type: 2,
			 * 		keyword: '方洲邻里中心鲜蔬店'
			 * 	},
			 * 	...
			 * ]
			 */
			keywords: [],

			/**
			 * 初始化
			 */
			init: function() {
				var json = utils.storage.get('search_keywords');
				if (json == '' || json == null) {
					return [];
				}
				this.keywords = JSON.parse(json);
			},

			/**
			 * 查询所有关键字
			 */
			findAll: function() {
				return {keywords: this.keywords};
			},

			/**
			 * 保存搜索关键
			 *
			 * @param {[type]} type 类型
			 * @param {[type]} keyword 关键字
			 */
			set: function(type, keyword) {
				// 先检查是否搜索过此关键字，存在则停止后续操作
				for (var i in this.keywords) {
					if (this.keywords[i].type == type
						&& this.keywords[i].keyword == keyword) {
						return;
					}
				}

				// 将新的搜索放置到数组开头
				this.keywords.unshift({type: type, keyword: keyword});

				// 若大于5个，则删除数组末尾的一个
				if (this.keywords.length > 5) {
					this.keywords.pop();
				}

				// 存储到localstorage中
				utils.storage.set('search_keywords', JSON.stringify(this.keywords));

				return this;
			}

		};


		return pageView;
});
