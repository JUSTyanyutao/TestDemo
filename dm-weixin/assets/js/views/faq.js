define(['zepto', 'underscore', 'backbone', 'app/utils', 'text!templates/faq.html'], function($, _, Backbone, utils, page) {

	var $page = $("#faq-page")

	var PageView = Backbone.View.extend({
		el: $("#faq-page"),
		render: function() {
			utils.showPage(this.$el, function() {
				if (utils.isEmpty($page)) {
					$.ajax({
						url: window.ctx + "main/faq",
						type: "GET",
						dataType: "json",
						success: function(data) {
							var template = _.template(page);
							$page.empty().append(template(data));
						}
					})
					
				}
			});
			utils.showHeader({title : "常见问题"});
		},
		events: {
			"tap .ques-dt":	"showFaq"
		},

		showFaq: function(e) {
			var $head = $(e.currentTarget);
			if ($head.find("i").hasClass("rotate")) {
				$head.find("i").removeClass("rotate");
				$head.siblings(".ques-dd").addClass("eh-hide hide");
			} else {
				$head.parent().siblings().each(function() {
					$(this).find(".ques-dt").find("i").removeClass("rotate");
					$(this).find(".ques-dd").addClass("eh-hide hide");
				});

				$head.find("i").addClass("rotate");
				$head.siblings(".ques-dd").removeClass("eh-hide hide");			
			}
		}
	});

	return PageView;
});