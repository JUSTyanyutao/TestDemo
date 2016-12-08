define(['zepto', 'underscore', 'backbone', 'app/utils', 'app/weixin', 'text!templates/market.html'],

	function($, _, Backbone, utils, weixin, page) {
		var $page = $("#market-page");
		var $nearByTemplate;
		var $historyTemplate;
		var $historyContainer;
		var $nearByContainer;
		var $recommandMarket;
		var $name;
		var $province;
		var $city;
		var $district;
		var isLocation = 0;//是否得到经纬度 0未得到 1得到

		var PageView = Backbone.View.extend({
			el: $page,

			render: function() {
				utils.showPage(this.$el, function() {
					if (utils.isEmpty($page)) {
						$page.empty().append(page);

						$nearByTemplate   = $page.find("#tpl-nearby-market");
						$historyTemplate  = $page.find("#tpl-history-market");
						$historyContainer = $page.find(".history-container");
						$nearByContainer  = $page.find(".nearby-container");
						$recommandMarket  = $page.find(".recommand-market");
						$name             = $page.find("#name");
						$province         = $page.find("#province");
						$city             = $page.find("#city");
						$district         = $page.find("#district");

						if (utils.isEmpty($nearByContainer)) {
									searchByLocation();
						}
						//searchNearByMarkets(31.2177790000,120.6894670000);

						getProvinces();
						
						getCities(10, function() {
							$city.trigger('change');
						});
						getDistricts(106);
						
						
						if (utils.isEmpty($historyContainer)) {
							searchHistoryMarkets();
						}

					}
				});
			},

			events: {
				"tap .market-item": "changeMarketId",
				"tap .btn-cancel-my": "redirectMain",
				"tap .btn-search": 'searchByName',
				"change #province": 'updateCity',
				"change #city": 'updateDistrict',
				'change #district': 'updateMarket',
				'tap .recommand-market': 'selectMarket'
			},

			selectMarket: function(e) {
				var $this = $(e.currentTarget);
				if ($this.data('marketId') != "" && $this.data('marketId') > 0) {
					window.location.hash = 'main';
				} else {
					searchByLocation();
				}
			},

			updateMarket: function() {
				searchMarketByName();
			},

			updateDistrict: function() {
				var cityId = $city.val();

				getDistricts(cityId);

				searchMarketByName();
			},

			updateCity: function() {
				var provinceId = $province.val();

				getCities(provinceId, function() {
					$city.trigger('change');
				});

				searchMarketByName();
			},

			searchByName: function() {
				var name = $.trim($name.val());
				if (name == '') {
					return $.Dialog.info('请输入菜场名称');
				}
				searchMarketByName();
			},

			redirectMain: function() {
				window.location.hash = "main";
			},

			changeMarketId: function(e) {
				var $this = $(e.currentTarget);
				uploadMarketId($this.data('id'), $this.data('name'));
			}
		});

		// 根据地理位置搜索
		var searchByLocation = function(beforeCallback) {

			weixin.getLocation(function(res) {
                $.ajax({
                    url: window.ctx + "map/MicroMessageToBaidu",
                    type: "GET",
                    data: {
                        longitude: res.longitude,
                        latitude: res.latitude,
                    },
                    dataType: "json",
                    beforeSend: function() {
                    	$recommandMarket.text("定位中...");
                    	typeof beforeCallback == 'function' && beforeCallback();
                    },
                    success: function(data) {
                    	isLocation = 1;
                        searchNearByMarkets(data.latitude, data.longitude);

                    },
                    onError: function() {
                    	isLocation = 0;
                    	$recommandMarket.text("定位失败，请点击重新加载");
                    }
                })
            });
		};

		/**
		 * 搜索历史菜场
		 */
		var searchHistoryMarkets = function() {
			$.ajax({
				url: window.ctx + "/market/histories",
				type: "GET",
				dataType: "json",
				success: function(data) {
					var template = _.template($historyTemplate.html());
					$historyContainer.empty().append(template(data));
				}
			})
		}

		/**
		 * 搜索附近的菜场
		 */
		var searchNearByMarkets = function(latitude, longitude) {
			$.ajax({
				url: window.ctx + "/market/location",
				type: "GET",
				data: {
					latitude: latitude,
					longitude: longitude
				},
				dataType: "json",
				success: function(data) {
					utils.storage.set("currentLocation",data.result.location);
					var template = _.template($nearByTemplate.html());
					$nearByContainer.empty().append(template(data));
					if (data.result.list.length <= 0) {
						$recommandMarket.text('未搜索到附近的菜场');
						return;
					}
					$recommandMarket.text(data.result.list[0].name).data('marketId', data.result.list[0].id);
				}
			});
		};

		/**
		 * 根据名称搜索菜场
		 */
		var searchMarketByName = function() {
			$.ajax({
				url: window.ctx + 'market/name',
				type: 'GET',
				data: {
					name: $.trim($name.val()),
					province_id: $province.val(),
					city_id: $city.val(),
					county_id: $district.val()
				},
				dataType: 'json',
				success: function(data) {
					var template = _.template($nearByTemplate.html());
					$nearByContainer.empty().append(template(data));
					if (data.result.list.length > 0) {
						$recommandMarket.text(data.result.list[0].name);
					}
				}
			})
		}

		/**
		 * 上传市场编号
		 */
		var uploadMarketId = function(marketId, marketName) {
			$.ajax({
				url: window.ctx + "/market/bind",
				type: "GET",
				data: {
					market_id: marketId
				},
				dataType: "json",
				success: function() {
					utils.storage.set('market_name', marketName);
					utils.storage.set('market_id', marketId);
					$.Dialog.info('已为您切换到 ' + marketName);
					$("#main-page").empty();
					$("#mall-page").empty();
					window.location.hash = 'main';
				}
			})
		};

		// 加载省份信息
	var getProvinces = function(callback, provinceId) {
		$.ajax({
			url: window.ctx + "address/openprovinces",
			//url: window.ctx + "address/provinces",
			type: 'GET',
			dataType: "json",
			success: function(data) {
				getCities(10, function() {
								$city.trigger('change');
							});
				var options = ["<option value=''>选择省</option>"];
				for (var i in data.result) {
					options.push("<option value=\""+data.result[i].id+"\" "+(provinceId==data.result[i].id?'selected':'')+">"+data.result[i].name+"</option>");
				}
				$province.empty().append(options.join(""));
				var province=document.getElementById("province"); 
				province.options[1].selected='selected';
				typeof callback == 'function' && callback(data);
			}
		})
	};

	// 加载城市信息
	var getCities = function(provinceId, callback, cityId) {
		if (provinceId == "") {
			$city.empty().append("<option value=''>选择市</option>");
			typeof callback == 'function' && callback();
			return;
		}
		$.ajax({
			url: window.ctx + "address/opencities",
			//url: window.ctx + "address/cities",
			type: 'GET',
			data: {
				provinceId: provinceId
			},
			dataType: "json",
			success: function(data) {
				var options = ["<option value=''>选择市</option>"];
				for (var i in data.result) {
					options.push("<option value=\""+data.result[i].id+"\" "+(cityId==data.result[i].id?'selected':'')+">"+data.result[i].name+"</option>");
				}
				$city.empty().append(options.join(""));
				var city=document.getElementById("city"); 
				city.options[1].selected='selected';
				typeof callback == 'function' && callback(data);
			}
		})
	};

	// 加载区信息
	var getDistricts = function(cityId, callback, districtId) {
		if (cityId == "") {
			$district.empty().append("<option value=''>选择区</option>");
			typeof callback == 'function' && callback();
			return;
		}
		$.ajax({
			url: window.ctx + "address/opendistricts",
			//url: window.ctx + "address/districts",
			type: 'GET',
			data: {
				cityId: cityId
			},
			dataType: "json",
			success: function(data) {
				var options = ["<option value=''>选择区</option>"];
				for (var i in data.result) {
					options.push("<option value=\""+data.result[i].id+"\" "+(districtId==data.result[i].id?'selected':'')+">"+data.result[i].name+"</option>");
				}
				$district.empty().append(options.join(""));
				var district=document.getElementById("district"); 
				//district.options[1].selected='selected';
				district.options[12].selected='selected';
				typeof callback == 'function' && callback(data);
			}
		})
	};



		return PageView;
	});
