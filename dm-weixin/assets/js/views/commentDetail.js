define(['zepto', 'underscore', 'backbone', 'app/utils', 'text!templates/commentDetail.html'],

function($, _, Backbone, utils, page) {

    var $page = $("#commentDetail-page");
    var goodsId;
    var $labelContainer;
    var $commentsTemplate;
    var $commentsContainer;

    var PageView = Backbone.View.extend({
        el: $page,
        render: function(id) {
            goodsId = id;

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

            getComments(goodsId, status, 1, 1000);
        }
    });


    // 查询评论信息
    var getComments = function(goodsId, status, page, pageSize) {
        $.ajax({
            url: window.ctx + "/comment/lists",
            type: "GET",
            data: {
                goodsId: goodsId,
                status: status,
                page: page,
                pageSize: pageSize
            },
            dataType: "json",
            success: function(data) {
                var template = _.template($commentsTemplate.html());
                //全部
                var totalNum = 0;
                //好评
                var goodNum = 0;
                //中评
                var moderateNum = 0;
                //差评
                var negativeNum = 0;
                //晒单
                var baskNum = 0;
                $commentsContainer.empty().append(template(data));
                /*var totalNum = data.result.good_num + data.result.moderate_num + data.result.negative_num + data.result.bask_num;
                $page.find("#totalComments").html("("+totalNum+")");
                $page.find("#goodComments").html("("+data.result.good_num+")");
                $page.find("#middleComments").html("("+data.result.moderate_num+")");
                $page.find("#negativeComments").html("("+data.result.negative_num+")");
                $page.find("#baskComments").html("("+ data.result.bask_num + ")");*/

                if(data.result.good_num !=null){
                    goodNum = data.result.good_num;
                }
                if(data.result.moderate_num != null){
                    moderateNum = data.result.moderate_num;
                }
                if(data.result.negative_num != null){
                    negativeNum = data.result.negative_num;
                }
                if(data.result.bask_num != null){
                    baskNum = data.result.bask_num;
                }
                totalNum = goodNum + moderateNum + negativeNum + baskNum;
                $page.find("#totalComments").html("("+totalNum+")");
                $page.find("#goodComments").html("("+goodNum+")");
                $page.find("#middleComments").html("("+moderateNum+")");
                $page.find("#negativeComments").html("("+negativeNum+")");
                $page.find("#baskComments").html("("+ baskNum + ")");
            }
        });
    };


    return PageView;
});
