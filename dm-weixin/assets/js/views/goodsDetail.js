define(['zepto', 'underscore', 'backbone', 'echo', 'swiper', 'app/utils', 'app/basket.min',
    'app/favorite.min', 'text!templates/goodsDetail.html'
    ],

    function($, _, Backbone, echo, Swiper, utils, basket, favorite, detail) {
        var $detailPage;
        var $number;
        var price;
        var unit;
        var originalPrice;
        var originalUnit;
        var $sumPrice;
        var $actualPrice;
        var $actualUnit;
        var $commodityIntroduction;
        var $nutritiveValue;
        var $riginalPrice
        var goodsId;
        var nature = 0;

        var goodDetailView = Backbone.View.extend({
            el: $('#goods-detail-page'),
            render: function(goodsId) {
                goodsId = goodsId;
                showPage(this, goodsId);
                utils.showHeader({title : "商品详情"});
            },
            events: {
                // 点击加按钮
                "tap .btn-increase": "increaseOne",
                // 点击减按钮
                "tap .btn-decrease": "decreaseOne",
                // 点击收藏
                "tap .collection": "collectionGoods",
                // 栏目切换
                "tap .nav-pills li": 'toggleTab',
                // 跳转到商品评论页面
                "tap .btn-comment": 'redirectToComment',
                // 跳转到购物车
                "tap .btn-shopping-cart": "redirectToShoppingCart",
                // 跳转到店铺
                "tap .btn-merchant": "redirectToMerchant",
                // 分享商品
                "tap .btn-share": "share",
                 // 跳转到店铺
                //"tap .btn-merchant": "redirectToMerchant"
            },

            share: function() {
                utils.showShareMask($detailPage);
            },

            //跳转到店铺
            redirectToMerchant: function(e) {
                window.location.hash = "#merchant/" + $(e.currentTarget).data('id');
            },

            // 跳转到购物车
            redirectToShoppingCart: function() {
                window.location.hash = "#shoppingCart";
            },

            // 跳转到商品评论页面
            redirectToComment: function() {
                window.location.hash = 'commentDetail/'+goodsId;
            },

            // 切换栏目
            toggleTab: function(e) {
                e.stopImmediatePropagation();

                var $this = $(e.currentTarget);
                // 切换选中效果
                $this.addClass('active').siblings().removeClass("active");
                // 切换tab内容
                var target = $this.data('target');
                if ('content' == target) {
                    $detailPage.find('.tab-content').removeClass('eh-hide').siblings('.tab-description').addClass('eh-hide');
                } else {
                    $detailPage.find('.tab-description').removeClass('eh-hide').siblings('.tab-content').addClass('eh-hide');
                }
            },

            // 点击收藏
            collectionGoods: function(e) {
                var $btn = $(e.currentTarget);
                if ($btn.hasClass('coll-icon-full')) {
                    // 取消收藏
                    favorite.del(goodsId, 1, function(data) {
                        $btn.removeClass("coll-icon-full").find('p').text('收藏');
                        $btn.find('img').attr('src', 'assets/img/xing.png');
                        $.Dialog.success('取消收藏成功');
                    });
                } else {
                    // 收藏
                    favorite.add(goodsId, 1, function(data) {
                        $btn.addClass("coll-icon-full").find('p').text('取消收藏');
                        $btn.find('img').attr('src', 'assets/img/star.png');
                        $.Dialog.success('收藏成功');
                    });
                }
            },

            // +1
            increaseOne: function(e) {
                var $this = $(e.currentTarget);

                basket.increase($this.data('id'), 1, 1, function(data) {
                    $this.siblings().removeClass('eh-hide');
                    $this.siblings(".number").text(data.result);
                    basket.addCart({"id" : $this.data('id'),"quantity" : 1, "type" : 1});
                    $.Dialog.info('已加入菜篮子');
                });
            },

            // -1
            decreaseOne: function(e) {
                var $this = $(e.currentTarget);
                if($this.siblings(".number").text() > 0 ){
                    basket.decrease($this.data('id'), 1, 1, function(data) {
                        $this.siblings(".number").text(data.result);
                        basket.removeCart($this.data('id'));
                        if(data.result > 0){
                            $.Dialog.info('已从菜篮子移除');
                        }
                    });
               }
            }
        });
        /**
         * 展示页面
         */
        var showPage = function(view, id) {
            view.$el.empty();//.append(detail);

            $detailPage = $("#goods-detail-page");

            goodsId     = id;

            utils.showPage(view.$el, function() {
                initGoodsDetail();
            });
        };

        // 初始化商品详情页
        var initGoodsDetail = function() {
            $.ajax({
                url: window.ctx + "mall/detail",
                type: "GET",
                data: {
                    goods_id: goodsId
                },
                dataType: "json",
                success: function(data) {
                    var template = _.template(detail);
                    $detailPage.empty().append(template(data));
                    $number        = $detailPage.find(".number-number");
                    $actualPrice   = $detailPage.find("#actual-price");
                    $actualUnit    = $detailPage.find("#actual-unit");
                    $riginalPrice  = $detailPage.find("#riginal-price");

                    $commodityIntroduction = $detailPage.find("#commodity-introduction");
                    $nutritiveValue = $detailPage.find("#nutritive-value");
                    price          = parseFloat($number.data("price"));
                    unit           = data.result.unit + data.result.unit_name;
                    $sumPrice      = $detailPage.find(".sum-price");
                    nature         = parseInt(data.result.nature);
                    originalPrice  = parseFloat(data.result.original_price);
                    originalUnit   = data.result.original_unit + data.result.original_unit_name;
                    initSet(data);
                    initSwiper();
                }
            });
        };

        //设置商品介绍和营养搭配
        var initSet = function(data){
            $riginalPrice.show();
            var nature = data.result.nature;
            //nature=2是特价商品
            if( nature == 2){
                $riginalPrice.show();
            }else{
                $riginalPrice.hide();
            }
            var description = data.result.description;
            var lists = data.result.nutrition_collocation_lists;
            if( description != "" && lists.length <=0){
                //只有商品介绍
                $commodityIntroduction.show();
                $nutritiveValue.addClass("hide");
                $commodityIntroduction.addClass("show-full");
            }else if(description == "" && lists.length > 0){
                //只有营养搭配
                $detailPage.find('.tab-content').removeClass('eh-hide').siblings('.tab-description').addClass('eh-hide');
                $nutritiveValue.show();
                $commodityIntroduction.hide();
                $commodityIntroduction.removeClass("show-full");
                $nutritiveValue.addClass("show-full");
            }else {
                //都有或者都无
                $nutritiveValue.show();
                $commodityIntroduction.show();
                $commodityIntroduction.removeClass("show-full");
                $nutritiveValue.removeClass("show-full");
            }
        }

        // 更新价格和数量
        var refreshNumberAndPrice = function (number) {
            $number.text(number);

            // 更新商品价格
            if (number > 1) {
                $actualPrice.text("¥" + originalPrice.toFixed(2));
                $actualUnit.text(originalUnit);
            } else {
                $actualPrice.text("¥" + price.toFixed(2));
                $actualUnit.text(unit);
            }

            if (nature == 2 && number >= 2) {
                // 活动商品，用户未购买，且购买数量大于1
                $sumPrice.text("¥" + (price + (number-1) * originalPrice).toFixed(2));
            } else {
                $sumPrice.text("¥" + (number*price).toFixed(2));
            }
        };

        var initEvent = function() {
            echo.init({
                throttle: 1000
            });
        };

        var initSwiper = function() {
            var swiper = new Swiper(".swiper-container", {
                loop: true,
                pagination: '.swiper-pagination'
            });
        };

        return goodDetailView;
    });
