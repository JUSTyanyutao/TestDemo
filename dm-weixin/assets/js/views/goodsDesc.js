define(['zepto', 'underscore', 'backbone', 'app/utils', 'text!templates/goodsDesc.html'], 

function($, _, Backbone, utils, page) {

	var $page = $("#goods-desc-page");
	var $frame;

	var PageView = Backbone.View.extend({
		el: $page,

		render: function() {
			utils.showPage($page, function() {

				$page.empty().append(page);

				$frame = $page.find(".goods-desc-frame");

				$frame.attr("src", utils.storage.get("goods-href"));

			});
			utils.showHeader({title: "商品详情"});
		}

	});


	return PageView;
});