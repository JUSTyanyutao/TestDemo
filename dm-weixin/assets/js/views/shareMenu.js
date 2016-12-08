define(['zepto', 'underscore', 'backbone', 'app/utils', 'app/weixin', 'mobiscroll', 'text!templates/shareMenu.html'],

	function($, _, Backbone, utils, weixin, Mobiscroll, page) {

		var $page = $("#share-menu-page");

		var PageView = Backbone.View.extend({
			el: $page,

			render: function() {
				utils.showPage($page, function() {
					var template = _.template(page);
					$page.empty().append(template());

					initDinnerTime();
				});
			},

			events: {
				// 分享给微信好友
				'tap .share-weixin': 'shareWeixin',
				// 分享到朋友圈
				'tap .share-timeline': 'shareTimeline',
				// 分享给QQ好友
				'tap .share-qq': 'shareQQ',
				// 分享到QQ空间
				'tap .share-zone': 'shareZone',
				// 分享
				'tap .btn-share-book': 'prepareShare'
			},

			// 分享
			prepareShare: function() {
				share(function(data) {
					$.Dialog.alert('提示', '创建宴请成功，快去分享吧！', function() {
						window.location.replace('#menuDetail/' + data.result.id);
					});
				});
			},

			// 分享给微信好友
			shareWeixin: function() {
				share(function(data) {

				});
			},

			// 分享到朋友圈
			shareTimeline: function() {

			},

			// 分享给QQ好友
			shareQQ: function() {

			},

			// 分享到QQ空间
			shareZone: function() {

			}
		});

		// 分享
		var share = function(callback) {
			var title      = $page.find("#title").val();
			var dinnerTime = $page.find("#dinnerTime").val();

			if (title == "") {
				return $.Dialog.info("请输入宴会名称");
			}

			if (dinnerTime == "") {
				return $.Dialog.info("请选择宴会开始时间");
			}

			if (new Date().getTime() - new Date(dinnerTime).getTime() >= 0) {
				return $.Dialog.info("亲，宴会开始时间不能晚于当前时间");
			}

			$.ajax({
				url: window.ctx + "book/share",
				type: "POST",
				data: {
					title: title,
					dinnerTime: dinnerTime
				},
				dataType: "json",
				success: function(data) {
					typeof callback == 'function' && callback(data);
				}
			})
		};

		//  初始化宴请时间
		var initDinnerTime = function() {
				var date = new Date();
				var currYear = date.getFullYear();
				var currMonth = date.getMonth();
				var currDay = date.getDay();
		    var opt = {};
		    opt.datetime = {preset : 'datetime'};
		    opt.default = {
		        theme: 'android-ics light', //皮肤样式
		        display: 'bottom', //显示方式
		        mode: 'scroller', //日期选择模式
		        dateFormat: 'yyyy-mm-dd',
		        lang: 'zh',
		        showOnFocus: true,
		        showNow: true,
		        nowText: "今天",
		        startYear: currYear, //开始年份
				startMonth: currMonth,
		        endYear: currYear + 1 //结束年份
		    };
		    var optDateTime = $.extend(opt.datetime, opt.default);
		    jQuery("#dinnerTime").mobiscroll(optDateTime);
		};

		return PageView;
	});
