define(['zepto', 'underscore', 'backbone',
        'app/utils',
        'text!templates/coupon.html'
    ],

    function($, _, Backbone, utils, coupon) {
        var type;

        var couponView = Backbone.View.extend({
            el: $('#coupon-page'),
            render: function(t) {
                var that = this;
                loadPage(that);
                type = t;
            },

            events: {
                "tap .coupon-item": "selected"
            },

            selected: function(e) {
                if ('balance' == type) {
                    var $this    = $(e.currentTarget);
                    var couponId = $this.data("id");
                    var name     = $this.data("name");

                    // 从storage中读取下单信息
                    var balance = JSON.parse(utils.storage.get("balance"));

                    verifyCoupon(couponId, balance.deliveryWayId, balance.list, function() {
                        // 更新storage中的balance信息
                        balance = $.extend(balance, {
                            couponId: couponId,
                            couponName: name
                        });
                        utils.storage.set("balance", JSON.stringify(balance));

                        window.location.replace("#balance");
                    });    
                }
            }
        });

        var loadPage = function(that) {
            utils.showPage(that.$el,function(){
                getCouponList(function(data) {
                    var template  = _.template(coupon);
                    that.$el.empty().append(template(data));
                });
            });
        };

        /**
         * 校验优惠券
         */
        var verifyCoupon = function(couponId, deliveryWayId, list, callback) {
            $.ajax({
                url: window.ctx + "balance/verifyCoupon",
                type: "GET",
                dataType: "json",
                data: {
                    couponId: couponId,
                    deliveryWayId: deliveryWayId,
                    list: list
                },
                success: function(data) {
                    if (typeof callback == "function") {
                        callback();
                    }
                }
            });
        };

        var getCouponList = function(callback) {
            $.ajax({
                url: window.ctx + "coupon/getList",
                type: "GET",
                dataType: 'json',
                success: function(data) {
                    callback(data.result);
                }
            });
        };
        return couponView;
    });
