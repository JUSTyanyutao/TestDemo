define(['zepto', 'underscore', 'backbone',
	'echo',
	'app/utils', 'text!templates/health.html'],
	function($, _, Backbone, Echo, utils, page) {

		var $page = $("#health-page");
		var $healthContainer;
		var $itemTemplate;
		var imageRenderToken;
		var $type;

		var PageView = Backbone.View.extend({
			el: $page,

			render: function(type) {
				utils.showPage($page, function() {
					$type = type;
					if (utils.isEmpty($page)) {
						$page.empty().append(page);
						if(null != $type && typeof($type) !== 'undefined'){
								$(".he-header").hide();
								$page.css("padding-top","0px");
						}
						$healthContainer = $page.find("#health-container");
						$itemTemplate      = $page.find("#item-template");
						//默认加载营养知识
						loadData(2,0);
					}
				});
			},

			events: {
					"tap #health-container .he-heakno-list": "viewDetail",
					"tap .ui-tab-nav li": "changeTab"
			},

			/**
			 * 咨询详情页
			 */
			viewDetail: function(e) {
					var $this = $(e.currentTarget);
					window.location.hash = "activity/detail/" + $this.data("id");
			},

			changeTab : function(e){
					var $this = $(e.currentTarget);
					var type = $this.children().data("type");
					$this.siblings().children().removeClass("current");
					$this.children().addClass("current");
					loadData(type, $this.index());
			}

		});

		var loadData = function($type,$index){
				$.ajax({
					url: window.ctx + "consulting/lists",
					type: "GET",
					dataType: "json",
					data : {type : $type},
					success: function(data) {
							var template = _.template($itemTemplate.html());
							$healthContainer.empty().append(template(data)).show();
							asynLoadImage();
					}
				});
		}

    var asynLoadImage = function() {
          Echo.init({
              throttle: 250,
          });
          if (imageRenderToken == null) {
              imageRenderToken = window.setInterval(function() {
                  Echo.render();
              }, 350);
          }
    };
		return PageView;
});
