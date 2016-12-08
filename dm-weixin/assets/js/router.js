// Filename: router.js
define([
    'zepto',
    'underscore',
    'backbone',
    'app/utils',
    'app/basket'
], function($, _, Backbone, utils, basket) {
    var Views = {};
    var app_router;
    var AppRouter = Backbone.Router.extend({

        initialize: function() {
            console.log("Route initialize");
            utils.goBack({});  //初始化返回事件
            redirectFooterEvent();
            basket.showCartPoint();
        },
        routes: {
            // Define some URL routes
            'main': 'loadMainPage',
            'newProduct': 'loadNewProductPage',
            'userOrder': 'loadUserOrderPage',
            'faq': 'loadFaqPage',
            'personalCenter': 'loadPersonalCenterPage',
            'mall(/:type)(/:categroyId)': 'loadMallPage',
            'goodsDetail/:goodsId': 'loadGoodsDetailPage',
            'goodsDesc': 'loadGoodsDescPage',

            'specials': 'loadSpecialsPage',

            'order': 'loadOrderPage',

            'order/:orderStatus': 'loadOrderPage',

            'orderDetail/:orderId': 'loadOrderDetailPage',

            'workerOrder(/:deliveryStatus)' : 'loadWorkerOrderPage',

            'recharge(/:redirectHash)' : 'loadRechargePage',

            'coupon(/:type)': 'loadCouponPage',

            'feedback': 'loadFeedbackPage',

            'accountBalance' : 'accountBalancePage',

            'health(/:type)': 'loadHealthPage',

            'aboutus(/:type)': 'loadAboutusPage',

            'shoppingCart': 'loadShoppingCartPage',

            'balance': 'loadBalancePage',

            'time': 'loadTimePage',

            'address(/:addressId)': 'addressPage',

            'cabinet': 'cabinetPage',

            'search(/:refresh)(/:type)':  'searchPage',

            'balanceDetail': 'balanceDetailPage',

            'record': 'recordPage',

            'favorites': 'favoritesPage',

            'comment': 'commentPage',

            'activity(/:type)': 'activityPage',
            'activity/detail/:activityId': 'activityDetailPage',

            'commentDetail(/:goodsId)': 'commentDetailPage',

            'cookComment(/:cookId)' : 'cookCommentPage',

            'points': 'pointsPage',

            'signin': 'signinPage',

            'market': 'marketPage',

            'merchant/:merchantId': 'merchantPage',

            'cooks': 'cooksPage',

            'cook/:cookId': 'cookPage',

            'setting': 'settingPage',

            'addresses(/:type)': 'addressesPage',

            'success': 'successPage',

            'book': 'bookPage',

            'bookDetail/:bookId': 'bookDetailPage',

            'menus(/:type)': 'menusPage',

            'shareMenu': 'shareMenuPage',

            'menuDetail/:menuId(/:type)': 'menuDetailPage',

            'chooseMenu/:menuId': 'chooseMenuPage',

            'submitMenu/:menuId': 'submitMenuPage',

            'forgotPassword': 'forgotPasswordPage',

            'resetPassword/:token': 'resetPasswordPage',

            'logout' : 'logoutPage',

            'invite' : 'invitePage',

            // Default
            '': 'defaultAction'
        },

        defaultAction: function() {
            if (stopMain != null && !stopMain) {
                utils.showMenu();

                requirejs(['views/main'], function(main) {
                    if (!Views.mainView) {
                        Views.mainView = new main();
                    }
                    Views.mainView.render();
                });
            }
        },

        /* 忘记密码 */
        forgotPasswordPage: function() {
            utils.hideMenu();
            requirejs(['views/forgotPassword'], function(forgotPassword) {
                if (!Views.forgotPasswordView) {
                    Views.forgotPasswordView = new forgotPassword();
                }
                Views.forgotPasswordView.render();
            });
        },

        resetPasswordPage: function(token) {
            utils.hideMenu();
            requirejs(['views/resetPassword'], function(resetPassword) {
                if (!Views.resetPasswordView) {
                    Views.resetPasswordView = new resetPassword();
                }
                Views.resetPasswordView.render(token);
            });
        },

        submitMenuPage: function(menuId) {
            utils.hideMenu();
            requirejs(['views/submitMenu'], function(submitMenu) {
                if (!Views.submitMenuView) {
                    Views.submitMenuView = new submitMenu();
                }
                Views.submitMenuView.render(menuId);
            });
        },

        chooseMenuPage: function(menuId) {
            utils.hideMenu();
            requirejs(['views/chooseMenu'], function(chooseMenu) {
                if (!Views.chooseMenuView) {
                    Views.chooseMenuView = new chooseMenu();
                }
                Views.chooseMenuView.render(menuId);
            });
        },

        menuDetailPage: function(menuId, type) {
            utils.hideMenu();
            requirejs(['views/menuDetail'], function(menuDetail) {
                if (!Views.menuDetailView) {
                    Views.menuDetailView = new menuDetail();
                }
                Views.menuDetailView.render(menuId, type);
            });
        },

        shareMenuPage: function() {
            utils.hideMenu();
            requirejs(['views/shareMenu'], function(shareMenu) {
                if (!Views.shareMenuView) {
                    Views.shareMenuView = new shareMenu();
                }
                Views.shareMenuView.render();
            });
        },

        menusPage: function(type) {
            utils.hideMenu();
            requirejs(['views/menus'], function(menus) {
                if (!Views.menusView) {
                    Views.menusView = new menus();
                }
                Views.menusView.render(type);
            });
        },

        bookPage: function() {
            utils.activeMenu(2);
            utils.showMenu();
            requirejs(['views/book'], function(book) {
                if (!Views.bookView) {
                    Views.bookView = new book();
                }
                Views.bookView.render();
            });
        },

        bookDetailPage: function(bookId) {
            utils.hideMenu();
            requirejs(['views/bookDetail'], function(bookDetail) {
                if (!Views.bookDetailView) {
                    Views.bookDetailView = new bookDetail();
                }
                Views.bookDetailView.render(bookId);
            });
        },

        cookCommentPage: function(cookId) {
            utils.hideMenu();
            requirejs(['views/cookComment'], function(cookComment) {
                if (!Views.cookCommentView) {
                    Views.cookCommentView = new cookComment();
                }
                Views.cookCommentView.render(cookId);
            });
        },

        successPage: function() {
            utils.hideMenu();
            requirejs(['views/success'], function(success) {
                if (!Views.successView) {
                    Views.successView = new success();
                }
                Views.successView.render();
            });

        },

        addressesPage: function(type) {
            utils.hideMenu();
            requirejs(['views/addresses'], function(addresses) {
                if (!Views.addressesView) {
                    Views.addressesView = new addresses();
                }
                Views.addressesView.render(type);
            });
        },

        settingPage: function(){
            utils.hideMenu();
            requirejs(['views/setting'], function(setting) {
                if (!Views.settingView) {
                    Views.settingView = new setting();
                }
                Views.settingView.render();
            });
        },

        merchantPage: function (merchantId) {
            utils.hideMenu();
            requirejs(['views/merchant'], function(merchant) {
                if (!Views.merchantView) {
                    Views.merchantView = new merchant();
                }
                Views.merchantView.render(merchantId);
            });
        },

        cooksPage: function() {
            utils.hideMenu();
            requirejs(['views/cooks'], function(cooks) {
                if (!Views.cooksView) {
                    Views.cooksView = new cooks();
                }
                Views.cooksView.render();
            });
        },

        cookPage: function(cookId) {
            utils.hideMenu();
            requirejs(['views/cook'], function(cook) {
                if (!Views.cookView) {
                    Views.cookView = new cook();
                }
                Views.cookView.render(cookId);
            })
        },

        marketPage: function() {
            utils.hideMenu();
            requirejs(['views/market'], function(market) {
                if (!Views.marketView) {
                    Views.marketView = new market();
                }
                Views.marketView.render(app_router);
            });
        },

        pointsPage: function() {
            utils.hideMenu();
            requirejs(['views/points'], function(points) {
                if (!Views.pointsView) {
                    Views.pointsView = new points();
                }
                Views.pointsView.render();
            });
        },

        signinPage: function() {
            utils.hideMenu();
            requirejs(['views/signin'], function(signin) {
                if (!Views.signinView) {
                    Views.signinView = new signin();
                }
                Views.signinView.render();
            });
        },

        activityPage: function(type) {
            utils.hideMenu();
            requirejs(['views/activity'], function(activity) {
                if (!Views.activityView) {
                    Views.activityView = new activity();
                }
                Views.activityView.render(type);
            });
        },

        activityDetailPage: function(activityId) {
            utils.activeMenu(5);
            utils.hideMenu();
            requirejs(['views/activityDetail'], function(detail) {
                if (!Views.activityDetailView) {
                    Views.activityDetailView = new detail();
                }
                Views.activityDetailView.render(activityId);
            });
        },

        loadGoodsDescPage: function() {
            utils.hideMenu();
            requirejs(['views/goodsDesc'], function(desc) {
                if (!Views.goodsDescView) {
                    Views.goodsDescView = new desc();
                }
                Views.goodsDescView.render();
            });
        },

        cabinetPage: function() {
            requirejs(['views/cabinet'], function(page) {
                if (!Views.cabinetView) {
                    Views.cabinetView = new page();
                }
                Views.cabinetView.render();
            });
        },

        // 问答
        loadFaqPage: function() {
            utils.hideMenu();
            requirejs(['views/faq'], function(page) {
                if (!Views.faqView) {
                    Views.faqView = new page();
                }
                Views.faqView.render();
            });
        },

        loadTimePage: function() {
            utils.hideMenu();
            requirejs(['views/time'], function(time) {
                if (!Views.timeView) {
                    Views.timeView = new time();
                }
                Views.timeView.render();
            });
        },

        loadBalancePage: function() {
            utils.hideMenu();
            requirejs(['views/balance'], function(balance) {
                if (!Views.balanceView) {
                    Views.balanceView = new balance();
                }
                Views.balanceView.render();
            });
        },

        loadMainPage: function(id) {
            utils.showMenu();
            requirejs(['views/main'], function(main) {
                if (!Views.mainView) {
                    Views.mainView = new main();
                }
                Views.mainView.render();
            });
        },

        loadSpecialsPage : function(){
            utils.hideMenu();
            requirejs(['views/specials'], function(specials) {
                if (!Views.specialsView) {
                    Views.specialsView = new specials();
                }
                Views.specialsView.render();
            });
        },

        loadMallPage: function(type, categoryId) {
            utils.showMenu();
            utils.activeMenu(3);
            requirejs(['views/mall'], function(mall) {
                if (!Views.mallView) {
                    Views.mallView = new mall();
                }
                Views.mallView.render(type, categoryId, app_router);
            });
        },

        loadGoodsDetailPage: function(goodsId) {
            utils.hideMenu();
            requirejs(['views/goodsDetail'], function(detail) {
                if(!Views.goodsDetailView) {
                    Views.goodsDetailView = new detail();
                }
                Views.goodsDetailView.render(goodsId);
            });
        },

        loadPersonalCenterPage: function() {
            utils.showMenu();
            requirejs(['views/personalCenter'], function(personal) {
                if (!Views.personalCenterView) {
                    Views.personalCenterView = new personal();
                }
                Views.personalCenterView.render();
            });
        },

        loadOrderPage: function(orderStatus) {
            utils.hideMenu();
            requirejs(['views/order'], function(order) {
                if (!Views.orderView) {
                    Views.orderView = new order();
                }
                Views.orderView.render(orderStatus);
            });
        },

        loadOrderDetailPage: function(orderId){
            requirejs(['views/order'], function(order) {
                if (!Views.orderView) {
                    Views.orderView = new order();
                }
                Views.orderView.loadDetail(orderId);
            });
        },

        loadWorkerOrderPage : function(deliveryStatus){
            utils.hideMenu();
            requirejs(['views/workerOrder'], function(order) {
                if (!Views.workerOrderView) {
                    Views.workerOrderView = new order();
                }
                Views.workerOrderView.render(deliveryStatus);
            });
        },

        loadRechargePage : function(balance){
            utils.hideMenu();
            requirejs(['views/recharge'], function(recharge) {
                if (!Views.rechargeView) {
                    Views.rechargeView = new recharge();
                }
                Views.rechargeView.render(balance);
            });
        },

        loadCouponPage: function(type) {
            utils.hideMenu();
            requirejs(['views/coupon'], function(coupon) {
                if (!Views.couponView) {
                    Views.couponView = new coupon();
                }
                Views.couponView.render(type);
            });
        },

        accountBalancePage: function() {
            utils.hideMenu();
            requirejs(['views/account_balance'], function(accountBalance) {
                if (!Views.accountBalanceView) {
                    Views.accountBalanceView = new accountBalance();
                }
                Views.accountBalanceView.render();
            });
        },


        loadFeedbackPage: function() {
            requirejs(['views/feedback'], function(feedback) {
                if (!Views.feedbackView) {
                    Views.feedbackView = new feedback();
                }
                Views.feedbackView.render();
            });
        },

        favoritesPage: function() {
            utils.hideMenu();
            requirejs(['views/favorites'], function(favorites) {
                if (!Views.favoritesView) {
                    Views.favoritesView = new favorites();
                }
                Views.favoritesView.render();
            });
        },


        loadAboutusPage: function(type) {
            utils.hideMenu();
            requirejs(['views/aboutus'], function(aboutus) {
                if (!Views.aboutusView) {
                    Views.aboutusView = new aboutus();
                }
                Views.aboutusView.render(type);
            });
        },


        // 购物车
        loadShoppingCartPage: function() {
            utils.showMenu();
            utils.activeMenu(4);
            requirejs(['views/shoppingCart'], function(cart) {
                if (!Views.cartView) {
                    Views.cartView = new cart();
                }
                Views.cartView.render();
            });
        },

        searchPage: function(refresh, type) {
            utils.hideMenu();
            requirejs(['views/search'], function(search) {
                if (!Views.searchPage) {
                    Views.searchPage = new search();
                }
                Views.searchPage.render(refresh, type);
                // app_router.navigate('search', {trigger: false, replace: true});
            });
        },

        // 地址
        addressPage: function(addressId) {
            utils.hideMenu();
            requirejs(['views/address'], function(page) {
                if (!Views.addressView) {
                    Views.addressView = new page();
                }
                Views.addressView.render(addressId);
            });
        },

        // 下单商品详情
        balanceDetailPage: function() {
            utils.hideMenu();
            requirejs(['views/balanceDetail'], function(page) {
                if (!Views.balanceDetail) {
                    Views.balanceDetail = new page();
                }
                Views.balanceDetail.render();
            });
        },

        // 收支页面
        recordPage: function() {
            utils.hideMenu();
            requirejs(['views/record'], function(page) {
                if (!Views.record) {
                    Views.record = new page();
                }
                Views.record.render();
            });
        },

        //加载健康咨询
        loadHealthPage : function(type){
            utils.hideMenu();
            requirejs(['views/health'], function(page) {
                if (!Views.health) {
                    Views.health = new page();
                }
                Views.health.render(type);
             });
        },

        // 评论页面
        commentPage: function() {
            utils.hideMenu();
            requirejs(['views/comment'], function(page) {
                if (!Views.comment) {
                    Views.comment = new page();
                }
                Views.comment.render();
            });
        },

        // 评论详情页面
        commentDetailPage: function(goodsId) {
            utils.hideMenu();
            requirejs(['views/commentDetail'], function(page) {
                if (!Views.commentDetail) {
                    Views.commentDetail = new page();
                }
                Views.commentDetail.render(goodsId);
            });
        },

        logoutPage : function(){
            deleteCookie('sxapi_access_token');
            deleteCookie('sxapi_expire_time');
            deleteCookie('sxapi_refresh_token');
        },

        invitePage : function(){
            utils.hideMenu();
            utils.hideHeader();
            requirejs(['views/invite'], function(invite) {
                if (!Views.inviteView) {
                    Views.inviteView = new invite();
                }
                Views.inviteView.render();
            });
        }
    });

    var redirectFooterEvent = function(){
        $(".he-footer #he-index").on("tap",function(){
            window.location.hash = "main";
            $(this).siblings().removeClass("highlight");
            $(this).addClass("highlight");
        });
        $(".he-footer #he-market").on("tap",function(){
            window.location.hash = "mall";
            $(this).siblings().removeClass("highlight");
            $(this).addClass("highlight");
        });
        $(".he-footer #he-shoppingcart").on("tap",function(){
            window.location.hash = "shoppingCart";
            $(this).siblings().removeClass("highlight");
            $(this).addClass("highlight");
        });
        $(".he-footer #he-my").on("tap",function(){
            window.location.hash = "personalCenter";
            $(this).siblings().removeClass("highlight");
            $(this).addClass("highlight");
        });
        $(".he-footer #he-menu").on("tap",function(){
            window.location.hash = "book";
            $(this).siblings().removeClass("highlight");
            $(this).addClass("highlight");
        });
    }

    var initialize = function() {
        app_router = new AppRouter;
        Backbone.history.start();
    };

    var getCookie = function (name) {
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
        else
        return null;
    };

    var deleteCookie = function(name) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval=getCookie(name);
        if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
    };

    return {
        initialize: initialize
    };
});
