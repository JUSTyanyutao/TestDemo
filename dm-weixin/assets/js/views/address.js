define(['zepto', 'underscore', 'backbone', 'app/utils', 'text!templates/address.html'], function($, _, Backbone, utils, page) {

	var $page = $("#address-page");
	var addressId;
	var $consignee;
	var $mobile;
	var $provinceId;
	var $cityId;
	var $districtId;
	var $streetAddress;
	var $defaultStatus;

	var PageView = Backbone.View.extend({
		el: $("#address-page"),

		render: function(id) {
			addressId = id;

			utils.showPage(this.$el, function() {
				$page.empty().append(page);

				$consignee     = $page.find('#consignee');
				$mobile        = $page.find('#mobile');
				$provinceId    = $page.find("#provinceId");
				$cityId 	   = $page.find("#cityId");
				$districtId    = $page.find("#districtId");
				$streetAddress = $page.find('#streetAddress');
				$defaultStatus = $page.find(".status");

				if (id == null || id == '') {
					getProvinces(function() {
						getCities($provinceId.val(), function() {
							getDistricts($cityId.val());
						});
					}, 10);
				} else {
					getAddressInfo(id);
				}
			});
		},

		events: {
			// 点击默认状态
			'tap .btn-default-status': 'toggleDefault',
			// 保存地址
			'tap .btn-save': "save",
			// 更新城市列表
			"change #provinceId": "updateCity",
			// 更新区列表
			'change #cityId': 'updateDistrict',
			// 删除地址
			'tap .he-address-delete': 'deleteAddress'
		},

		// 删除收获地址
		deleteAddress: function() {
			$.Dialog.confirm('警告', '确认要删除此地址吗?', function() {
				$.ajax({
					url: window.ctx + "address/delete",
					type: "GET",
					data: {
						addressId: addressId
					},
					dataType: "json",
					success: function(data) {
						$.Dialog.info('删除成功');
						window.history.go(-1);
					}
				});
			}, '放弃', '确定');
		},

		// 更新城市列表
		updateCity: function(e) {
			var $this = $(e.currentTarget);

			getCities($this.val(), function () {
				$cityId.trigger('change');
			});
		},

		// 更新区列表
		updateDistrict: function(e) {
			var $this = $(e.currentTarget);

			getDistricts($this.val());
		},

		// 点击默认状态
		toggleDefault: function(e) {
			if ($defaultStatus.hasClass('selected')) {
				$defaultStatus.removeClass('selected');
			} else {
				$defaultStatus.addClass('selected');
			}
		},

		// 保存地址
		save: function() {
			saveAddress();
		}	
	});

	// 保存收货地址
	var saveAddress = function() {
		// 检查收货人
		var consignee = $.trim($consignee.val());
		if (consignee == '') {
			return $.Dialog.info("请填写收货人信息");
		}

		// 检查联系方式
		var mobile = $.trim($mobile.val());
		if (mobile == '') {
			return $.Dialog.info('请填写联电话');
		}

		// 验证省市区
		var provinceId = $provinceId.val();
		var cityId     = $cityId.val();
		var districtId = $districtId.val();
		if (provinceId == '' || cityId == '' || districtId == '') {
			return $.Dialog.info('请选择省市区信息');
		}

		// 街道地址
		var streetAddress = $.trim($streetAddress.val());
		if (streetAddress == '') {
			return $.Dialog.info("请填写街道地址");
		}

		$.ajax({
			url: window.ctx + '/address/save',
			type: 'POST',
			data: {
				id: addressId,
				consignee: consignee,
				mobile: mobile,
				provinceId: provinceId,
				cityId: cityId,
				districtId: districtId,
				streetAddress: streetAddress,
				isDefault: $defaultStatus.hasClass('selected')
			},
			dataType: 'json',
			success: function(data) {
				if (data.err_code == 0) {
					$.Dialog.info("保存成功");
					window.history.go(-1);
				}
			}
		});


	};

	// 加载地址信息
	var getAddressInfo = function(addressId) {
		$.ajax({
			url: window.ctx + "/address/get",
			type: 'GET',
			data: {
				addressId: addressId
			},
			dataType: "json",
			success: function(data) {
				$consignee.val(data.result.consignee);
				$mobile.val(data.result.mobile);
				$streetAddress.val(data.result.street_address);
				if (data.result.is_default) {
					$defaultStatus.addClass("selected");
				}

				getProvinces(function() {
					getCities($provinceId.val(), function() {
						getDistricts($cityId.val(), function() {

						}, data.result.district_id);
					}, data.result.city_id);
				}, data.result.province_id);
			}
		})
	};

	// 加载省份信息
	var getProvinces = function(callback, provinceId) {
		$.ajax({
			url: window.ctx + "address/provinces",
			type: 'GET',
			dataType: "json",
			success: function(data) {
				var options = [];
				for (var i in data.result) {
					options.push("<option value=\""+data.result[i].id+"\" "+(provinceId==data.result[i].id?'selected':'')+">"+data.result[i].name+"</option>");
				}
				$provinceId.empty().append(options.join(""));
				typeof callback == 'function' && callback(data);
			}
		})
	};

	// 加载城市信息
	var getCities = function(provinceId, callback, cityId) {
		$.ajax({
			url: window.ctx + "address/cities",
			type: 'GET',
			data: {
				provinceId: provinceId
			},
			dataType: "json",
			success: function(data) {
				var options = [];
				for (var i in data.result) {
					options.push("<option value=\""+data.result[i].id+"\" "+(cityId==data.result[i].id?'selected':'')+">"+data.result[i].name+"</option>");
				}
				$cityId.empty().append(options.join(""));
				var province=document.getElementById("cityId"); 
				province.options[4].selected='selected';
				typeof callback == 'function' && callback(data);
			}
		})
	};

	// 加载区信息
	var getDistricts = function(cityId, callback, districtId) {
		$.ajax({
			url: window.ctx + "address/districts",
			type: 'GET',
			data: {
				cityId: cityId
			},
			dataType: "json",
			success: function(data) {
				var options = [];
				for (var i in data.result) {
					options.push("<option value=\""+data.result[i].id+"\" "+(districtId==data.result[i].id?'selected':'')+">"+data.result[i].name+"</option>");
				}
				$districtId.empty().append(options.join(""));
				typeof callback == 'function' && callback(data);
			}
		})
	};

	return PageView;

});