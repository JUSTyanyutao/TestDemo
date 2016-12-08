define(['zepto', 'underscore', 'backbone', 'app/utils', 'text!templates/cabinet.html'],
	function ($, _, Backbone, utils, page) {

	var $page = $("#cabinet-page");
	var $cabinteTemplate;
	var $cabinetContainer;

	var CabinetView = Backbone.View.extend({
		el: $page,
		render: function() {
			utils.showPage($page, function() {
				$page.empty().append(page);

				$cabinteTemplate = $page.find("#cabinet-template");
				$cabinetContainer = $page.find("#cabinetContainer");

				var template = _.template($cabinteTemplate.html());

				// 获取站点信息，判断站点信息是否存在
				var cabinets = utils.storage.get("balance_cabinets");
				//alert(cabinets);
				if (cabinets == null || cabinets == "" || cabinets == "{}") {
					$page.find(".station-list-name").text("历史站点");
					$.ajax({
						url: window.ctx + "/balance/stationHistory",
						type: "GET",
						dataType: "json",
						success: function(data) {
							data.result.list = data.result.data;
							$cabinetContainer.empty().append(template(data));
						}
					});
				} else {
					$cabinetContainer.empty().append(template(JSON.parse(cabinets)));
				}
				utils.showHeader({title : "站点选择"});
			})
		},
		events: {
			// 搜索
			"tap .search-button": "searchCabinet",
			"tap .cabinet-item":  "chooseCabinet"
		},

		// 选择站点
		chooseCabinet: function(e) {
			var $this = $(e.currentTarget);
			var rest    = $this.data("rest");

			if (rest <= 0) {
				$.Dialog.confirm("提示", "站点已满，确认要下单吗?", function() {
					setCabinet($this.data("id"), $this.data("name"), $this.data("address"));
				});
			} else {
				setCabinet($this.data("id"), $this.data("name"), $this.data("address"));
			}
		},

		// 搜索柜子
		searchCabinet: function() {
			var address = $.trim($page.find("#address").val());
			if (address == "") {
				return $.Dialog.info("请输入站点名称");
			}
			$.ajax({
				url: window.ctx + "balance/getCabinetsByName",
				type: "GET",
				data: {
					address: address
				},
				dataType: "json",
				success: function(data) {
					$page.find(".station-list-name").text("搜索结果");
					var template = _.template($cabinteTemplate.html());
					$cabinetContainer.empty().append(template(data));
				}
			});
		}
	});


	// 设置选择的站点
	var setCabinet = function(id, name, address) {
		// 从localstorage中读出订单数据，并更新站点信息
		var balance = $.extend({}, JSON.parse(utils.storage.get("balance")), {
			stationId: id,
			stationName: name,
			stationAddress: address
		});

		utils.storage.set('balance', JSON.stringify(balance));

		// 跳转回到下单页面
		window.setTimeout(function() {
			window.location.replace("#balance");
		}, 100);
	};


	return CabinetView;
});
