define(['zepto', 'underscore', 'backbone',
        'swiper', 'echo',
        'app/utils', 'countdown','app/weixin',
        'text!templates/main.html'
    ],

    function($, _, Backbone, Swiper, echo, utils, countdown, weixin,mainTemplate) {
        var $page     = $("#main-page");
        var countDown = null;
        var imageRenderToken = null;
        var $userList;
        var $currentLocation;
        var nearMarketId = null;
        var locationSuccess ;//定位 1成功 0失败
        
        var mainView = Backbone.View.extend({
            el: $page,
            render: function() {
                locationSuccess = 0 ;//初始化为定位失败
                utils.showMenu();
                var that = this;
                loadPage(that);
            },
            events: {
                "tap .goods-detail": "redirectGoodsDetail",
                "tap .goods-flash-sale": "redirectFlashSale",
                "tap .location": "redirectLocationPage",
                "tap .he-index-center .more": "redirectSpecialPage",
                "tap .he-index-list img": "redirectGoodsDetail",
                // 点击进入搜索页面
                "tap .btn-search": 'redirectToSearch',
                // 跳转到店铺
                "tap .btn-merchant": "redirectToMerchant"

            },
            // 点击进入搜索页面
            redirectToSearch: function() {
                window.location.hash = 'search/true';
            },

            redirectLocationPage: function() {
                window.location.hash = "market";
            },

            redirectFlashSale: function() {
                window.location.hash = "mall/flash_sale";
            },

            redirectGoodsDetail: function(e) {
                window.location.hash = "goodsDetail/" + $(e.currentTarget).data("id");
            },

            redirectSpecialPage: function(e){
                window.location.hash = "specials";
            },

            redirectToMerchant: function(e){
                $("#merchant-page").empty();
                window.location.hash = "merchant/" + $(e.currentTarget).data('id');
            }
        });

        var loadPage = function(that) {
             var marketName = utils.storage.get('market_name');
             if (marketName == "" || marketName == null) {//
                // 根据地理位置搜索 
                searchByLocation(that);
                //searchNearByMarkets(31.2549690000,120.7569870000,that )
            }
            if(locationSuccess ==0 ){//定位失败
                toShowPage(that);
            }
           
          // searchNearByMarkets(31.2549690000,120.7569870000,that );
        }

        //定位成功（失败）展示页面
        var toShowPage = function(that){
            var markeUrl = "main";
            var nearData =null;
            if( nearMarketId != null){//定位成功
                markeUrl = "main/near";
                nearData  = {market_id: nearMarketId}
            }
            utils.showPage(that.$el, function() {
                $userList           = $page.find("#users-list-template");
                if (utils.isEmpty($page)) {
                    $.ajax({
                        url: window.ctx + markeUrl,
                        type: "GET",
                        data:nearData,
                        dataType: 'json',
                        success: function(data) {
                            var template = _.template(mainTemplate);
                            that.$el.empty().append(template(data.result));
                            asynLoadImage();
                            bannerEvent();
                            if (countDown != null) {
                                countDown.stop();
                            }
                            initMarketName();
                        }
                    });
                } else {
                    initMarketName();
                }
            });
        }

        /**
         * 初始化菜场名称
         */
        var initMarketName = function() {
            var marketName = utils.storage.get('market_name');
            var currentLocation = utils.storage.get("currentLocation");
            // 没有默认菜场
            if (marketName == "" || marketName == null) {
                $.Dialog.confirm('定位', '您还未选择附近的菜场', function() {
                    window.location.hash = 'market';
                }, '取消', '选菜场');
                return;
            }

            $page.find(".market-name").text(marketName);
            $page.find(".current-location").text(currentLocation);
        };

        // 根据地理位置搜索 searchByLocation
        var searchByLocation = function(that,beforeCallback) {
           // console.log(utils.Navigator.currentPosition()); html5定位
            weixin.getLocation(function(res) {
                $.ajax({
                    url: window.ctx + "map/MicroMessageToBaidu",
                    type: "GET",
                    data: {
                        longitude: res.longitude,
                        latitude: res.latitude,
                    },
                    dataType: "json",
                    beforeSend: function() {
                        //$recommandMarket.text("定位中...");
                        //typeof beforeCallback == 'function' && beforeCallback();
                    },
                    success: function(data) {
                        isLocation = 1;
                        searchNearByMarkets(data.latitude, data.longitude,that);
                        //searchNearByMarkets(31.2549690000,120.7569870000,that );
                    },
                    onError: function() {
                        isLocation = 0;
                        locationSuccess = 0;
                       // $recommandMarket.text("定位失败，请点击重新加载");
                       toShowPage(that);
                    }
                })
           });
        };


        /**
         * 搜索附近的菜场
         */
        var searchNearByMarkets = function(latitude, longitude,that) {
            locationSuccess = 1;
            $.ajax({
                url: window.ctx + "/market/location",
                type: "GET",
                data: {
                    latitude: latitude,
                    longitude: longitude
                },
                dataType: "json",
                success: function(data) {
                    utils.storage.set("currentLocation",data.result.location);
                    //var template = _.template($nearByTemplate.html());
                   // $nearByContainer.empty().append(template(data));

                   //定位成功 附近没有菜场
                    if (data.result.list.length <= 0) {
                        $.Dialog.info('你所在的位置附近没有菜场');
                        locationSuccess = 0;
                        toShowPage(that);
                        return;
                    }
                    nearMarketId = data.result.list[0].id;
                    utils.storage.set('market_name', data.result.list[0].name);

                    //定位成功 展示第一个菜场
                    toShowPage(that);
                }
            });
            
        };


        var asynLoadImage = function() {
            // setTimeout(function() {
                echo.init({
                    throttle: 250,
                });

                if (imageRenderToken == null) {
                    imageRenderToken = window.setInterval(function() {
                        echo.render();
                    }, 350);
                }
            // }, 360);
        };

        var bannerEvent = function() {

            return new Swiper('.swiper-container', {
                autoplay: 3000,
                pagination: '.swiper-pagination',
                paginationClickable: true,
                autoHeight: true,
                onTap: function(swiper) {
                    var href = $(swiper.clickedSlide).data("href");

                    if (href == "") {
                        return;
                    }
                    if (href.indexOf("howeat://") >= 0) {
                        // 伪协议
                        var protocol = href.substr(9);
                        var arr = protocol.split("/");

                        switch(parseInt(arr[0])) {
                            case 1:
                                //菜谱页面
                                window.location.hash = "book";
                                break;
                            case 2:
                                //逛菜场页面
                                window.location.hash = "mall";
                                break;
                            case 3:
                                //充值页面
                                window.location.hash = "recharge";
                                break;
                            case 4:
                                //商品详情
                                window.location.hash = "goodsDetail/" + arr[1];
                        }
                    } else {
                        window.location.href = href;
                    }
                }
            })
        }

        return mainView;
    });
