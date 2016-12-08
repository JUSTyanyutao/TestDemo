define(['zepto', 'underscore', 'backbone', 'app/utils', 'text!templates/cookComment.html'],

function($, _, Backbone, utils, page) {

    var $page = $("#cookComment-page");
    var cookId;
    var $labelContainer;
    var $commentsTemplate;
    var $commentsContainer;

    var PageView = Backbone.View.extend({
        el: $page,
        render: function(id) {
            cookId = id;

            utils.showPage($page, function() {
                $page.empty().append(page);

                $labelContainer    = $page.find(".he-marcom-title");
                $commentsTemplate  = $page.find("#comments-template");
                $commentsContainer = $page.find(".comments-container");

                $page.find(".he-marcom-title p").first().trigger('tap');
            });
        },

        events: {
            // 切换评论类型
            "tap .he-marcom-title p": "tapLabel"
        },

        // 切换评论类型
        tapLabel: function(e) {
            var $this = $(e.currentTarget);
            var status = $this.data('id');

            $this.addClass('selected').siblings().removeClass('selected');

            getComments(cookId, status, 1, 1000);
        }
    });


    // 查询评论信息
    var getComments = function(cookId, status, page, pageSize) {
        $.ajax({
            url: window.ctx + "/comment/cookComments",
            type: "GET",
            data: {
                cookId: cookId,
                status: status,
                page: page,
                pageSize: pageSize
            },
            dataType: "json",
            success: function(data) {
                var template = _.template($commentsTemplate.html());
                $commentsContainer.empty().append(template(data));
            }
        });
    };

    
    return PageView;
});
