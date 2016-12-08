define(['zepto', 'underscore', 'backbone', 'app/utils', 'app/basket.min', 'echo', 'text!templates/shoppingCart.html'],

    function($, _, Backbone, utils, basket, echo, shoppingCart) {

        var $page = $("#shoppingCart-page");
        var $cartContainer;
        var $cartTemplate;
        var $totalPrice;

        // 商品总价
        var totalPrice = 0;

        var pageView = Backbone.View.extend({
            el: $page,

            render: function() {
                utils.showPage($page, function() {
                    if (utils.isEmpty($page)) {
                        $page.empty().append(shoppingCart);

                        $cartContainer = $page.find(".cart-container");
                        $cartTemplate  = $page.find("#cart-template");
                        $totalPrice    = $page.find(".total-price");

                        initShoppingCartData();
                    } else {
                        initShoppingCartData();
                    }
                });
            },

            initialize: function() {},

            events : {
                // 购物车商品减
                "tap .btn-sub"       : 'subGoodsQuantity',

                // 购物车商品加
                "tap .btn-plus"      : 'plusGoodsQuantity',

                // 去下单
                "click .btn-bill"      : 'redirectBalance',

                // 选大厨
                'tap .btn-cooks'     : 'redirectCooks',

                'tap .more'          : 'redirectCooks'
            },

            // 选大厨
            redirectCooks: function() {
                window.location.hash = 'cooks';
            },

            // 去下单
            redirectBalance: function(e) {
                // var goods    = []
                // var total    = 0;
                // var number   = 0;
                // var sumPrice = '';
                // $goodsContainer.find(".number-number").each(function(i, obj) {
                //     // 每类商品的个数
                //     number = parseInt($(obj).text());
                //     sumPrice = $(obj).parent().siblings(".sum-price").data('sum-price');
                //     goods.push({
                //         id: $(obj).data('id'),
                //         quantity: number,
                //         pic: $(obj).data('pic'),
                //         currentPrice: $(obj).data('current-price'),
                //         name: $(obj).data('name'),
                //         sumPrice: sumPrice,
                //         price: $(obj).data('price')
                //     });
                //     // 总数量累加
                //     total += number;
                // });
                // var carts = {
                //     goods: goods,
                //     total: total,
                //     sum: totalPrice
                // };
                if (parseFloat($totalPrice.data("totalPrice")) == 0) {
                    $.Dialog.info("亲，你的菜篮子是空的");
                    return;
                }
                $("#balance-page").empty();
                utils.storage.set("balance", "{}");
                window.location.hash = 'balance';
            },

            // 商品数量减1
            subGoodsQuantity: function(e) {
                e.stopImmediatePropagation();

                var $this = $(e.currentTarget);

                basket.decrease($this.data('id'), 1, $this.data('type'), function(data) {
                    basket.removeCart($this.data('id'));
                    if (data.result == 0) {
                        initShoppingCartData();
                        return;
                    }
                    $this.siblings(".number").text(data.result);
                    // updateTotalPrice();
                    initShoppingCartData();
                });
            },

            // 商品数量加1
            plusGoodsQuantity: function(e) {
                e.stopImmediatePropagation();

                var $this = $(e.currentTarget);

                basket.increase($this.data('id'), 1, $this.data('type'), function(data) {
                    $this.siblings(".number").text(data.result);
                    basket.addCart({"id" : $this.data('id'),"quantity" : 1, "type" : 1});
                    // updateTotalPrice();
                    initShoppingCartData();
                });
            }
        });

        /**
         * 加载购物车数据
         */
        var initShoppingCartData = function(callback) {
            var  marketId = utils.storage.get('market_id');
            $.ajax({
                url: window.ctx + "shoppingCart",
                type: "GET",
                dataType: "json",
                data:{marketId:marketId},
                success: function(data) {
                    if(typeof(data.loginSuccess) != "undefined" && data.loginSuccess ==0){
                        //未登录进入购物车
                    }else{
                        var template  = _.template($cartTemplate.html());
                        $cartContainer.empty().append(template(data));

                        initCartStorage(data);

                        totalPrice = data.result.total_price;

                        $totalPrice.text("￥" + totalPrice.toFixed(2)).data('totalPrice', totalPrice.toFixed(2));

                        // updateTotalPrice();

                        typeof callback == "function" && callback();

                        initEvent();  
                    }
                    
                }
            });
        };

        var initCartStorage = function(data){
            var merchants = data.result.merchants;
            var cooks = data.result.cooks;
            var i = 0, j = 0;
            basket.clearCart();
            for (i = 0; i < merchants.length; i ++ ) {
                for(j = 0; j < merchants[i].goods.length; j ++ ){
                    basket.addCart({"id" : merchants[i].goods[j].goods_id,"quantity" : merchants[i].goods[j].quantity, "type" : 1});
                }
            }

            for (i = 0; i < cooks.length; i ++ ) {
                basket.addCart({"id" : cooks[i].id,"quantity" : cooks[i].quantity, "type" : 2});
            }
        };

        // 更新购物车总价
        var updateTotalPrice = function() {
            totalPrice = 0;
            $page.find(".item-number").each(function(i, obj) {
                totalPrice += parseFloat($(obj).data("price")) * parseInt($(obj).text());
            });

            $totalPrice.text("￥" + totalPrice.toFixed(2)).data('totalPrice', totalPrice.toFixed(2));
        };

        var initEvent = function() {
            setTimeout(function() {
                echo.init({
                    throttle: 1000
                });
            }, 300);

        };

        return pageView;
    });
