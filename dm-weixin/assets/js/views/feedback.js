define(['zepto', 'underscore', 'backbone',
        'app/utils',
        'text!templates/feedback.html'
    ],

    function($, _, Backbone, utils, feedback) {

        var feedbackView = Backbone.View.extend({
            el: $('#feedback-page'),
            render: function() {
                var that = this;
                loadPage(that);
                utils.showHeader({title : "投诉建议"});
            }
        });

        var loadPage = function(that) {
            var template = _.template(feedback);
            that.$el.empty().append(template());
            utils.showPage(that.$el);
            submitEvent();
        }

        var submitEvent = function() {
            $(".sure-button").on("tap", function() {
                var content = $("#content").val();
                if ($.isBlank(content)) {
                    $.message("warning", {"content" : "反馈内容不能为空"});
                    return;
                }
                $.ajax({
                    url: window.ctx + "feedback/submit",
                    type: "POST",
                    dataType: 'json',
                    data : {"content" : content},
                    success: function(data) {
                        $.message("success", {"content" : "反馈成功，感谢您的参与~"});
                    }
                });
            });
        }
        return feedbackView;
    });
