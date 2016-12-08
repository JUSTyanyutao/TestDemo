define(['zepto', 'underscore', 'backbone', 'echo', 'app/utils',
    'text!templates/balance.html', 'app/weixin'
    ],

    function($, _, Backbone, echo, utils, page, weixin) {
        var $page = $("#balance-page");
        var $addressContainer;
        var $addressTemplate;
        var $goodsNumber;
        var $frozenMoney;
        var $balanceMoney;
        var $points;
        var $pointsMoney;
        var $totalPrice;
        var $discountMoney;
        var $actualMoney;
        var marketId = null ;//菜场ID

        var balance = {};
        var ratio;
        var limitRatio;

        var BalanceView = Backbone.View.extend({
            el: $('#balance-page'),

            render: function() {
                utils.showPage($page, function() {
                     marketId = utils.storage.get('market_id');
                    if (utils.isEmpty($page)) {
                        $page.empty().append(page);

                        $addressContainer = $page.find(".address-container");
                        $addressTemplate  = $page.find("#address-template");
                        $goodsNumber      = $page.find(".goods-number");
                        $frozenMoney      = $page.find('.frozen-money');
                        $balanceMoney     = $page.find(".balance-money");
                        $points           = $page.find('.points');
                        $pointsMoney      = $page.find(".points-money");
                        $totalPrice       = $page.find('.total-price');
                        $discountMoney    = $page.find(".discount-money");
                        $actualMoney      = $page.find(".actual-money");
                        $freightMoney     = $page.find(".freight-money");

                       

                        initBalanceFromStorage();
                        getRatio(function() {
                            initDefaultAddress();
                            initShoppingCarts(function(){
                                initMemberInfo(function() {
                                    initDeliveryWays(function() {
                                        refreshPayMoney();
                                    });
                                });
                            });
                            initCouponts();


                        });
                    } else {
                        initBalanceFromStorage();
                        initDefaultAddress();
                        refreshPayMoney();
                    }

                });
            },

            events: {
                //
                "tap .address-container" : "redirectAddress",
                // 选择优惠券
                "tap .btn-coupon": "chooseCoupon",
                // 选择派送时间
                "tap .choose-time": "chooseTimeAction",
                // 是否使用积分
                "tap .status": "usePoints",
                // 点击下单按钮
                "tap .submit": "createOrder",

                "tap .he-orset-btn" : "recharge"
                // // 跳转到订单详情
                // "tap .goods-detail": "redirectGoodsDetail",
            },

            // 选择地址
            redirectAddress: function() {
                window.location.hash = 'addresses/balance';
            },

            // 选择优惠券
            chooseCoupon: function() {
                window.location.hash = "coupon/balance";
            },

            // 选择派送时间
            chooseTimeAction: function() {
                window.location.hash = "time";
            },

            recharge : function(){
                window.location.hash = "recharge/balance";
            },

            // 是否使用积分
            usePoints: function(e) {
                var $this = $(e.currentTarget);
                if ($this.hasClass("selected")) {
                    $this.removeClass("selected");
                } else {
                    $this.addClass("selected");
                }
                refreshPayMoney();
            },

            // redirectBalanceDetail: function() {
            //     window.location.hash = "balanceDetail";
            // },

            // redirectGoodsDetail: function(e) {
            //     var $this = $(e.currentTarget);
            //     window.location.replace("#orderDetail/" + $this.data('id'));
            // },

            // 下单
            createOrder: function() {
                // 下单验证
                if (!balance.deliveryWayId) {
                    return $.Dialog.info("请选择一种派送方式");
                }
                if (!balance.addressId) {
                    return $.Dialog.info("请先选择收货地址");
                }
                if (!balance.deliveryDate) {
                    return $.Dialog.info("请选择配送时间");
                }

                $.ajax({
                    url: window.ctx + "balance/createOrder",
                    type: "POST",
                    dataType: "json",
                    data: {
                        couponId:        balance.couponId,
                        deliveryWayId:   balance.deliveryWayId,
                        deliveryDate:    balance.deliveryDate,
                        deliverySection: balance.deliverySection,
                        list:            balance.list,
                        remark:          $.trim($page.find("#comment").val()),
                        stationId:       balance.stationId,
                        usePoints:       getUsePoints(),
                        addressId:       balance.addressId,
                        cookInfo:        balance.cookInfo
                    },
                    success: function(data) {
                        $.Dialog.info("亲，下单成功啦！");
                        utils.storage.set("balance", "{}");
                        utils.storage.remove('balance_coupons');
                        utils.storage.remove('shoppingCartNum');
                        utils.storage.remove('shoppingCart');
                        utils.storage.set('order_result', JSON.stringify(data.result));
                        window.location.replace('#success');
                    }
                });
            }
        });

        /**
         * 加载收货地址
         */
        var initDefaultAddress = function() {
            if (balance.addressId == null) {
                var url = window.ctx + 'address/getDefault';
            } else {
                var url = window.ctx + 'address/get?addressId=' + balance.addressId;
            }

            $.ajax({
                url: url,
                type: "GET",
                dataType: "json",
                success: function(data) {
                    if (data.result == null) {
                        $.Dialog.confirm('提示', '您还未填写任何收货地址', function() {
                            window.location.hash = 'addresses/balance';
                        }, '暂不', '去填写');
                        return;
                    }
                    data.result.market_name = utils.storage.get('market_name');
                    var template = _.template($addressTemplate.html());
                    $addressContainer.empty().append(template(data));

                    updateBalance({addressId: data.result.id});
                },
                onError: function(data) {
                    if (data.err_code == 40101) {
                        $.Dialog.confirm('提示', '您还未填写任何收货地址', function() {
                            window.location.hash = 'addresses/balance';
                        }, '暂不', '去填写');
                    }
                }
            })
        };

        // 初始化购物车信息
        var initShoppingCarts = function(callback) {
            var marketId = utils.storage.get("market_id")
            $.ajax({
                url: window.ctx + 'shoppingCart',
                type: "GET",
                dataType: "json",
                data: { marketId: marketId},
                success: function(data) {
                    $goodsNumber.text(data.result.total);
                    $totalPrice.text('¥'+data.result.total_price.toFixed(2)).data('price', data.result.total_price);

                    // prepare goods list
                    var lists = [];
                    for (var i in data.result.merchants) {
                        var merchantId = data.result.merchants[i].id;
                        for (var j in data.result.merchants[i].goods) {
                            var goods = data.result.merchants[i].goods[j];

                            lists.push(merchantId + "," + goods.goods_id + "," + goods.quantity);
                        }
                    }
                    updateBalance({list: lists.join(";")});

                    // prepare cook list
                    var cookInfo = '';
                    var cookList = '';
                    if (data.result.cooks.length > 0) {
                        var cook = data.result.cooks[0];
                        cookInfo = cook.id+','+cook.start_time+','+cook.quantity;
                        cookList = cook.id+','+cook.quantity;
                    }
                    updateBalance({cookInfo: cookInfo, cookList: cookList});

                    typeof callback !== 'undefined' && callback();
                }
            })
        };

        // 从localstorage中读取数据
        var initBalanceFromStorage = function() {
            balance = utils.storage.get("balance");
            balance = balance != null ? JSON.parse(balance) : {};

            // 更新派送时间
            if (balance.deliveryDate && balance.deliverySection) {
                $page.find(".choose-time").find(".center").text(balance.deliveryDate + " " + balance.deliverySection);
            }

            // 优惠券信息
            if (balance.couponId) {
                $page.find(".btn-coupon").children("span").text(balance.couponName);
            }

            if (balance.stationName) {
                $page.find(".hotel").text(balance.stationName);
            }

            if (balance.stationAddress) {
                $page.find(".payment-address").find("p").text(balance.stationAddress);
            }
        };

        // 初始化优惠券
        var initCouponts = function() {
            $.ajax({
                url: window.ctx + "coupon/getList",
                type: "GET",
                dataType: "json",
                success: function(data) {
                    $page.find("#coupon-count").text(data.result.total);
                    utils.storage.set("balance_coupons", JSON.stringify(data));
                }
            });
        };

        // 更新下单信息
        var updateBalance = function(property) {
            balance = utils.storage.get("balance");
            balance = balance != null ? JSON.parse(balance) : {};
            balance = $.extend(balance, property);
            utils.storage.set("balance", JSON.stringify(balance));
        };

        // 加载会员信息
        var initMemberInfo = function(callback) {
            $.ajax({
                url: window.ctx + 'member/info',
                type: "GET",
                dataType: 'json',
                success: function(data) {
                    $balanceMoney.text('¥' + data.result.balance.toFixed(2));
                    $frozenMoney.text('¥' + data.result.frozen_funds.toFixed(2));
                    // 计算积分使用上限
                    // var limitPoints = parseInt(parseFloat($totalPrice.data('price')) * limitRatio);
                    // $points.text(limitPoints > data.result.points ? data.result.points : limitPoints);
                    // $pointsMoney.text(($points.text()*ratio).toFixed(2));

                    typeof callback == 'function' && callback();
                }
            });
        };

        // 加载收送方式数据
        var initDeliveryWays = function(callback) {
            $.ajax({
                url: window.ctx + "balance/getDeliveryWays",
                type: "GET",
                dataType: "json",
                success: function(data) {
                    updateBalance({deliveryWayId: data.result.data[0].id});

                    typeof callback == 'function' && callback();
                }
            })
        };

        // 获取积分兑换比率
        var getRatio = function(callback) {
            $.ajax({
                url: window.ctx + 'member/getRatio',
                type: "GET",
                dataType: "json",
                success: function(data) {
                    ratio      = data.result.ratio_value;
                    limitRatio = data.result.limit_use_ratio;

                    typeof callback == 'function' && callback(data);
                }
            });
        };

        // 获取使用积分
        var getUsePoints = function() {
            var $status = $page.find(".status");
            if (!$status.hasClass("selected")) {
                return 0;
            }

            return parseInt($points.text());
        };

        // 刷新支付金额
        var refreshPayMoney = function() {
            $.ajax({
                url: window.ctx + "balance/getActualPrice",
                type: "POST",
                dataType: "json",
                data: {
                    couponId: balance.couponId,
                    deliveryWayId: balance.deliveryWayId,
                    list: balance.list,
                    marketId: marketId,
                    usePoints: getUsePoints(),
                    cookInfo: balance.cookList
                },
                success: function(data) {
                    $discountMoney.text("¥"+data.result.discount_money);
                    $actualMoney.text("¥" + data.result.total_money);
                    $freightMoney.text("¥" + data.result.freight);
                    $points.text(data.result.available_points);
                    $pointsMoney.text((data.result.available_points*data.result.ratio).toFixed(2));
                }
            });
        };

        return BalanceView;
    });
