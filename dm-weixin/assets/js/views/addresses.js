define(['zepto', 'underscore', 'backbone', 'app/utils', 'text!templates/addresses.html'], 

	function($, _, Backbone, utils, page) {

		var $page = $("#addresses-page");
		var $container;
		var $addressTemplate;
		var type;

		var PageView = Backbone.View.extend({
			el: $page,

			render: function(t) {
				utils.showPage($page, function() {
					$page.empty().append(page);

					type = t;

					$container = $page.find(".addresses-container");
					$addressTemplate = $page.find("#address-template");

					getAddresses();
				});
			},

			events: {
				// 添加地址
				"tap .btn-add-address": "addAddress",
				// 编辑地址
				'tap .address-item': 'editAddress'
			},

			editAddress: function(e) {
				var addressId = $(e.currentTarget).data('id');

				if (type == 'balance') {
					// 从localstorage中读出订单数据，并更新派送时间段
					var balance = $.extend({}, JSON.parse(utils.storage.get('balance')), {
						addressId: addressId
					});

					utils.storage.set('balance', JSON.stringify(balance));
					
					window.location.replace('#balance');
				} else {
					window.location.hash = 'address/' + addressId;
				}
			},

			// 添加地址
			addAddress: function() {
				window.location.hash = "address";
			}			
		});

		// 加载收货地址
		var getAddresses = function() {
			$.ajax({
				url: window.ctx + "/address",
				type: 'GET',
				dataType: "json",
				success: function(data) {
					var template = _.template($addressTemplate.text());
					$container.empty().append(template(data));
				}
			})
		};

		return PageView;
});