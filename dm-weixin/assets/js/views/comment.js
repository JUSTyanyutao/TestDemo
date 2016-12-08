define(['zepto', 'underscore',
        'backbone', 'app/utils',
        'app/weixin',
        'app/score', 'text!templates/comment.html'
    ],
    function($, _, Backbone, utils, wx, score, page) {
        var $page;
        var $star;
        var images = {
            localId: [],
            serverId: []
        };

        var commentView = Backbone.View.extend({
            el: $("#comment-page"),
            render: function() {
                $page = this.$el;
                utils.showPage($page, function() {
                    $page.empty().append(page);
                    $star = $page.find(".score");
                    var comment = utils.storage.get("orderComment");
                    comment = comment != '' ? JSON.parse(comment) : {};
                    $page.find(".he-evaluate-details img").attr("src", comment.thumb);
                    $page.find("#orderId").val(comment.orderId);
                    if(comment.type == 1){
                        $page.find("#goodsId").val(comment.id);
                    } else {
                        $page.find("#cookId").val(comment.id);
                    }
                    $page.find(".he-evaluate-details .name").text(comment.name);
                    $page.find(".he-evaluate-details .element").text(comment.price);
                    $page.find(".he-evaluate-details .number").text(comment.unit);
                    score.init($star);
                    initLabel();
                })
            },
            events: {
                //发表评论
                "tap .he-btn-evaluate": "publishCommit",
                "tap .score i": "selectedStar",
                "tap #choose-comment-img": "chooseCommentImage",
                "tap .comment-label span": "selectedLabel",
                "tap #uploadImg" : "chooseCommentImage"
            },

            selectedStar: function(e) {
                var $this = $(e.currentTarget);
                score.selected($star, $this.index());
            },

            selectedLabel: function(e) {
                var $this = $(e.currentTarget);
                $this.toggleClass("highlight");
            },

            chooseCommentImage: function() {
                wx.chooseImage(function(localIds) {
                    images.localId = localIds;
                    //预览图片
                    for (var i = 0; i < localIds.length; i++) {
                        $page.find("#choose-comment-img").before('<img src=' + localIds[i] + ' />');
                    }
                });
            },

            //发表评论
            publishCommit: function(e) {
                var $this = $(e.currentTarget);
                var param = {};
                param.orderId = $page.find("#orderId").val();
                param.cookId = $page.find("#cookId").val();
                param.goodsId = $page.find("#goodsId").val();
                param.score = score.getValue($star);
                if (param.score <= 0) {
                    $.message("warning", {
                        content: "去评个星级吧~"
                    });
                    return;
                }
                var data = [];
                $page.find(".comment-label span").each(function() {
                    if ($(this).hasClass("highlight")) {
                        data.push($(this).data("id"));
                    }
                });
                param.labels = data.join(",");
                var content = $page.find("#content");
                if ($.isBlank(content.val())) {
                    $.message("warning", {
                        content: "评论内容不能为空~"
                    });
                    return;
                }
                param.content = content.val();
                //如果有图片则上传图片后再提交评论
                if (images.localId.length > 0) {
                    uploadCommentImage(function() {
                        param.serverId = images.serverId.join(",");
                        finishCommentHandler(param);
                    });
                } else {
                    finishCommentHandler(param);
                }
            }
        });

        var uploadCommentImage = function(callback) {
            var length = images.localId.length;
            if (length > 0) {
                var i = 0;
                images.serverId = [];

                function upload() {
                    wx.uploadImage(images.localId[i], function(serverId) {
                        images.serverId.push(serverId);
                        ++i < length ? upload() : callback();
                    });
                }
                upload();
            }
        };

        var finishCommentHandler = function(param) {
            $.ajax({
                url: window.ctx + "comment/add",
                type: "POST",
                dataType: 'json',
                data: param,
                success: function(data) {
                    $.message("info", {
                        content: "提交成功"
                    }, function() {
                        window.location.replace(window.ctx + "#order/5");
                    });
                }
            });
        };

        var initLabel = function() {
            $.ajax({
                url: window.ctx + "comment/labels",
                type: "GET",
                dataType: 'json',
                success: function(data) {
                    var label = $page.find(".comment-label").empty().append("评价标签");
                    var result = data.result.list;
                    if (result && result.length > 0) {
                        for (var i = 0; i < result.length; i++) {
                            label.append("<span data-id=" + result[i].id + ">" + result[i].name + "</span>");
                        }
                    }
                }
            });
        };
        return commentView;
    });
