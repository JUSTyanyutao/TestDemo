define(['zepto', 'underscore', 'backbone', 'echo', 'app/utils', 'app/basket', 'mobiscroll', 'text!templates/cook.html'],

	function($, _, Backbone, echo, utils, basket, Mobiscroll, page) {

		var $page = $("#cook-page");
		var $cookPanel;
		var $fireCookBtn;
		var cookId;

		var PageView = Backbone.View.extend({
			el: $page,

			render: function(id) {
				utils.showPage($page, function() {
					// if (utils.isEmpty($page)) {
						getCookInfo(id);
						cookId = id;
					// }
				});
			},

			events: {
				// 雇佣大橱
				"tap .btn-hire-cook": 'hireCook',
				// 关闭面板
				"tap .btn-cancel": 'closePanel',
				// 选择时间
				"tap .hour-option": 'chooseHour',
				// 雇佣大厨
				"tap .btn-confirm-cook": "confirmCook",
				// 评论
				"tap .btn-comments": "redirectComments",
				// 菜谱详情
				"tap .cookbook-item": "redirectCookbookDetail",
				// 验证上门时间
				"change #startTime": "validateStartTime"
			},

			// 验证上门时间
			validateStartTime: function(e) {
				var $this = $(e.currentTarget);
				var startTime = $this.val();

				if (new Date(startTime.replace(/-/g, '/')) - new Date().getTime() <= 0) {
					$.Dialog.info("您选择的上门日起不能晚于当前时间");
					$this.val("");
				}
			},

			// 菜谱详情
			redirectCookbookDetail: function(e) {
				var $this = $(e.currentTarget);
				window.location.hash = 'bookDetail/' + $this.data('id');
			},

			// 评论
			redirectComments: function() {
				window.location.hash = 'cookComment/'+cookId;
			},

			// 雇佣大橱
			hireCook: function(e) {
				$fireCookBtn.hide();
				$cookPanel.show();
			},

			// 关闭面板
			closePanel: function() {
				$fireCookBtn.show();
				$cookPanel.hide();
			},

			// 选择时间
			chooseHour: function(e) {
				var $this = $(e.currentTarget);
				$this.addClass('selected').siblings().removeClass('selected');
			},

			// 雇佣大厨
			confirmCook: function() {
				var param = {};

				param.cookId    = cookId;
				param.startTime = $("#startTime").val();
				param.hour      = $(".hour-option.selected").data('hour');

				if (param.startTime == '') {
					return $.Dialog.info("请选择上门时间");
				}
				if (param.hour == '' || param.hour == null || param.hour == undefined) {
					return $.Dialog.info('请选择上门小时');
				}

				basket.increase(param.cookId, param.hour, 2, function(data){
					$.Dialog.info('雇佣成功');
					$fireCookBtn.show();
					$cookPanel.hide();
					window.location.hash = "shoppingCart";
				}, param.startTime);
			}
		});

		// 查询大厨信息
		var getCookInfo = function(cookId) {
			$.ajax({
				url: window.ctx + "cook/info",
				type: "GET",
				data: {
					cookId: cookId
				},
				dataType: 'json',
				success: function(data) {
					var template = _.template(page);
					$page.empty().append(template(data));

					$cookPanel = $page.find(".cook-panel");
					$fireCookBtn = $page.find(".btn-fire-cook");


					var currYear = (new Date()).getFullYear();
				    var opt={};
				    opt.datetime = {preset : 'datetime'};
				    opt.default = {
				        theme: 'android-ics light', //皮肤样式
				        display: 'modal', //显示方式
				        mode: 'scroller', //日期选择模式
				        dateFormat: 'yyyy-mm-dd',
				        lang: 'zh',
				        showOnFocus: true,
				        showNow: true,
				        nowText: "今天",
				        startYear: currYear - 10, //开始年份
				        endYear: currYear + 10 //结束年份
				    };
				    var optDateTime = $.extend(opt.datetime, opt.default);
				    jQuery("#startTime").mobiscroll(optDateTime);

				    initEcho();
				}
			})
		};

		var initEcho = function() {
            setTimeout(function() {
                echo.init({
                    throttle: 1000
                });
            }, 360);

        };

		return PageView;
	});
