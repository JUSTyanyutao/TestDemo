define(['zepto', 'underscore', 'backbone', 'echo', 'app/utils', 'app/basket.min', 'countdown', 'fly',
    'text!templates/mall.html'
    ],

    function($, _, Backbone, echo, utils, basket, countdown, fly, mall) {

        var $mallPage = $("#mall-page");
        var $goodsContinar;
        var $goodsCategoryTpl;
        var $goodsItemTpl;
        var $goodsFlashSaleTpl;
        var $goodsSpecialsTpl;
        var $goodsHotTpl;
        var countDowns = [];
        var selectedCategoryId = null;
        var router = router;
        var isHistory = 0;//0 history 1
        var mallView = Backbone.View.extend({
            el: $('#mall-page'),
            render: function(type, categoryId, app_router) {
                router = app_router;
                utils.showPage($mallPage, function() {
                    if (utils.isEmpty($mallPage)) {
                        $mallPage.empty().append(mall);

                        $goodsContinar     = $mallPage.find(".he-marind-list");
                        $goodsCategoryTpl  = $mallPage.find("#tpl-goods-category");
                        $goodsItemTpl      = $mallPage.find("#tpl-goods-items");
                        $goodsFlashSaleTpl = $mallPage.find("#tpl-goods-flash-sale");
                        $goodsSpecialsTpl  = $mallPage.find("#tpl-goods-specials");
                        $goodsHotTpl       = $mallPage.find("#tpl-goods-hot");

                        initGoodsCatgory();
                    }
                });
                $mallPage.on("touchstart",".btn-increase",function(e){
                    addToBasket(e);
                })
            },
            events: {
                // 添加到购物车
                // "touchstart .btn-increase":     "addToBasket",
                // 从购物中删除
                "tap .btn-decrease":     "removeToBasket",
                // 跳转到商品详情
                "tap .goods-item":       "redirectGoodsDetail",
                // 点击分类
                "tap .category-item":    "tapFirstCategory",
                // 点击二级分类
                "tap .second-category":  "tapSecondCategory",
                // 点击三级分类
                "change #third-category": "tapThirdCategory",
                // 点击排序
                "change #hot-sort":          "sortBy",
                // 点击进入搜索页面
                "tap .btn-search":       'redirectToSearch'
            },

            // 点击进入搜索页面
            redirectToSearch: function() {
                window.location.hash = 'search/true';
            },

            // 排序
            sortBy: function(e) {
                isHistory;
                var $this = $(e.currentTarget);
                var categoryId = 0;
                var $category = $mallPage.find(".category-item.zmc_act");
                if ($category.html()) {
                    categoryId = $category.data('id');
                    var $secondCategory = $category.siblings().find(".active");
                    if ($secondCategory.html()) {
                        categoryId = $secondCategory.data('id');
                    }
                }
                if (categoryId >0 && isHistory ==0 ) {
                    loadGoods(categoryId, $this.val(), function() {

                    });
                }
                if(categoryId >0 && isHistory ==1){
                    loadMerchantGoods(categoryId,$this.val());
                }


                // if ($this.hasClass("up")) {
                //     // 已选中，恢复
                //     $this.removeClass("up");
                //     var sortName = $this.data("name");
                //     loadGoods(selectedCategoryId, sortName, "ASC");
                // } else {
                //     var sortName = $this.data("name");

                //     loadGoods(selectedCategoryId, sortName, "DESC", function() {
                //         $mallPage.find("li.sort").each(function(i, obj) {
                //             $(obj).attr("data-name") == sortName && $(obj).addClass("up");
                //         });
                //     });


                //     // 未选中，选中
                // }
            },

            // 添加到购物车
            addToBasket: function(e) {
                e.stopImmediatePropagation();

                var $this = $(e.currentTarget);

                basket.increase($this.data('id'), 1, 1, function(data) {
                    $.Dialog.info("添加成功");
                    $this.siblings().removeClass('eh-hide');
                    $this.siblings(".number").text(data.result);
                    flyToCart(e);
                });
            },

            // 从购物车中删除
            removeToBasket: function(e) {
                e.stopImmediatePropagation();

                var $this = $(e.currentTarget);
                var id = $this.data('id');
                basket.decrease(id, 1, 1, function(data) {
                    basket.removeCart(id);
                    if (data.result == 0) {
                        $this.addClass("eh-hide").siblings(".number").addClass("eh-hide");
                        return;
                    }
                    $this.siblings(".number").text(data.result);
                });
            },

            // 跳转到商品详情
            redirectGoodsDetail: function(e) {
                var $this = $(e.currentTarget);

                if ($this.hasClass("counting")) {
                    return $.Dialog.info("亲，活动还未开始，请耐心等待");
                }

                if ($this.children("img").hasClass("empty-mark")) {
                    return $.Dialog.info("亲，已售罄了哦！");
                }

                window.location.hash = "goodsDetail/" + $(e.currentTarget).data('id');
            },

            // 点击分类
            tapFirstCategory: function(e) {
                var $this = $(e.currentTarget);

                if (!$this.hasClass("zmc_act")) {
                    $mallPage.find(".category-item.zmc_act").removeClass("zmc_act").siblings(".panel-collapse").removeClass("in");
                    $this.addClass("zmc_act").siblings(".panel-collapse").addClass("in");
                    $("#hot-sort").val("ASC");
                }

                if ($this.hasClass('btn-history')) {
                    loadHistories();
                    $mallPage.find(".second-category.active").removeClass("active").find('span').remove();
                    $mallPage.find("#third-category").empty().append("<option value=\"\">品种</option>");

                    if (!$this.siblings().html()) {
                        loadHistoryMerchants();
                    }
                } else {
                    $("#mall-page").find(".second-category").removeClass('active');
                    loadGoods($this.data("id"), '', "ASC");
                }
            },

            // 点击二级分类
            tapSecondCategory: function(e) {
                var $this = $(e.currentTarget);

                if (!$this.hasClass("active")) {
                    $mallPage.find(".second-category.active").removeClass("active").find('span').remove();
                    $this.addClass("active").prepend('<span></span>');

                    var id = $this.data('id');
                    var childs = JSON.parse(localStorage.getItem("third-category-" + id));

                    var options = ["<option value=\"\">品种</option>"];
                    for (var i in childs) {
                        options.push("<option value=\""+ childs[i].id +"\">"+ childs[i].name +"</option>")
                    }

                    $mallPage.find("#third-category").empty().append(options.join("\n"));

                    if ($this.hasClass('btn-merchant')) {
                      //  alert("btn-merchant id" + id);
                        isHistory = 1;
                        loadMerchantGoods(id);

                    } else {
                        isHistory = 0;
                        loadGoods(id);    
                    }
                }

                router.navigate("mall");

                // $mallPage.find(".li-category-span").each(function() {
                //     $(this).removeClass("ontext");
                // })
                // $this.addClass("ontext").parent().prev().addClass("on").siblings(".li-category").removeClass("on");
                // loadGoods($this.data("id"));

            },

            // 点击三级分类
            tapThirdCategory: function(e) {
                var $this = $(e.currentTarget);
                var id = $this.val();

                console.info(id);

                if (id == "" || id == null) {
                    // 查询二级分类
                    loadGoods($mallPage.find(".second-category.active").data('id'));
                    return;
                }

                loadGoods(id);
            }
        });

        // var loadGoodsList = function(type, categoryId) {
        //     if (type == 'flash_sale' || type == null) {
        //         loadFlashSaleGoods();
        //         $mallPage.find(".menu-flash-sale").addClass("on");
        //     } else if (type == 'specials') {
        //         loadSpecialsGoods();
        //         $mallPage.find(".menu-specials").addClass("on");
        //     } else if (type == 'hot') {
        //         loadHotGoods();
        //         $mallPage.find(".menu-hot").addClass("on");
        //     } else {
        //         var categoryId = utils.storage.get("category_id");
        //         if (categoryId > 0) {
        //             $(".li-category-span").each(function(i, obj) {
        //                 if ($(obj).data('id') == categoryId) {
        //                     $(obj).addClass("ontext").parent().parent().removeClass("eh-hide").prev().addClass("on");
        //                 }
        //             })
        //             loadGoods(categoryId);
        //         } else {
        //             $mallPage.find(".menu-flash-sale").addClass("on");
        //             loadFlashSaleGoods();
        //         }
        //     }
        // }

        /**
         * 加载商品分类
         */
        var initGoodsCatgory = function() {
            if (utils.isEmpty($mallPage.find("#myAccordion"))) {
                $.ajax({
                    url: window.ctx + "mall/goodsCategory",
                    type: "GET",
                    dataType: "json",
                    success: function(data) {
                        var template  = _.template($goodsCategoryTpl.html());
                        $mallPage.find("#myAccordion").empty().append(template(data));//.height($(window).height() - 110 + "px");

                        triggerCategory();

                        $mallPage.find(".left-panel").height($(window).height() - 50 - $(".he-footer").height() + 'px');
                    }
                });
            }
        };

        // 触发分类
        var triggerCategory = function () {
            $mallPage.find(".category-item").eq(1).addClass("zmc_act");
            //$mallPage.find(".category-item").eq(1).trigger('tap');
            $mallPage.find(".second-category").first().trigger('tap');
        };


        // var initGoodsCatgory = function(type, categoryId) {
        //     if (utils.isEmpty($mallPage.find(".tab-type"))) {
        //         $.ajax({
        //             url: window.ctx + "mall/goodsCategory",
        //             type: "GET",
        //             dataType: "json",
        //             success: function(data) {
        //                 var template  = _.template($goodsCategoryTpl.html());
        //                 $mallPage.find(".tab-type").empty().append(template(data));
        //                 $mallPage.find(".tab-ty-wrap").height($(window).height() - $(".tab-fix").height() - $mallPage.find(".tab-first").height() - 70 + "px");
        //                 loadGoodsList(type, categoryId);
        //             }
        //         });
        //     } else if (type == "specials" || type == "flash_sale" || type == "hot") {
        //         $mallPage.find(".li-category").removeClass("on").each(function() {
        //             $(this).next(".fruts-options").addClass('eh-hide').find("div>span").removeClass("ontext");
        //         });
        //         loadGoodsList(type, categoryId);
        //     }
        // };

        /**
         * 加载商品列表信息
         */
        var loadGoods = function(category_id, sort, callback) {
            selectedCategoryId = category_id;
            $.ajax({
                url: window.ctx + 'mall/goods',
                type: "GET",
                data: {
                    category_id: category_id,
                    sort: $("#hot-sort").val()
                },
                dataType: "json",
                success: function(data) {
                    var template  = _.template($goodsItemTpl.html());
                    $goodsContinar.empty().append(template(data));
                    initEcho();
                    typeof callback == "function" && callback();
                }
            })
        };

        /**
         * 加载店铺商品信息
         */
        var loadMerchantGoods = function(merchantId, callback) {
            $.ajax({
                url: window.ctx + 'merchant/goods',
                type: "GET",
                data: {
                    merchantId: merchantId,
                },
                dataType: "json",
                success: function(data) {
                    var sort = $("#hot-sort").val();
                    if(sort == "DESC"){
                        console.log("sort " + sort);
                        data.result.data.reverse();
                    }
                    var template  = _.template($goodsItemTpl.html());
                    $goodsContinar.empty().append(template(data));
                    initEcho();
                    typeof callback == "function" && callback();
                }
            });
        };

        /**
         * 加载历史商店商品
         */
        var loadHistories = function() {
            $.ajax({
                url: window.ctx + "mall/hisitories",
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    var template  = _.template($goodsItemTpl.html());
                    $goodsContinar.empty().append(template(data));
                    initEcho();
                }
            });
        };

        /**
         * 加载历史商家
         */
        var loadHistoryMerchants = function() {
            $.ajax({
                url: window.ctx + 'merchant/histories',
                type: 'GET',
                dataType: 'json',
                data : {
                    market_id:utils.storage.get('market_id'),
                    page : 1, 
                    pageSize : 5
                },
                success: function(data) {
                    var merchants = [];
                    var res = data.result.data;
                    for (var i in res) {
                        merchants.push("<li class=\"second-category btn-merchant\" data-id=\""+res[i].id+"\">"+res[i].name+"</li>");
                    }
                    $mallPage.find(".btn-history").parent().find(".collapse").empty();
                     $mallPage.find(".btn-history").parent().append("<div class=\"collapse panel-collapse in \"><div class=\"panel-body\"><ul class=\"he-list\">"+merchants.join('')+"</ul></div></div>");
                    
                }
            })
        }

        // /**
        //  * 载入限时抢购商品
        //  */
        // var loadFlashSaleGoods = function() {
        //     $.ajax({
        //         url: window.ctx + "mall/flashSale",
        //         type: "GET",
        //         dataType: "json",
        //         success: function(data) {
        //             var template = _.template($goodsFlashSaleTpl.html());
        //             $goodsContinar.empty().append(template(data));

        //             initEcho();

        //             if (countDowns.length > 0) {
        //                 countDowns.forEach(function(e) {
        //                     e != null && e.stop();
        //                 });
        //             }
        //             countDowns = [];

        //             $goodsContinar.find(".countdown").each(function(i, element) {
        //                 var $wrap       = $(element);

        //                 // 获取时间参数
        //                 var endTime     = $wrap.data("endtime");
        //                 var startTime   = $wrap.data("starttime");
        //                 var currentTime = $wrap.data("currenttime");

        //                 // 十、分、秒对象
        //                 var $hour       = $wrap.find(".hour");
        //                 var $minute     = $wrap.find(".min");
        //                 var $second     = $wrap.find(".secon");

        //                 // 判断活动是否开始
        //                 if (Date.parse(startTime.replace(/-/g, "/")) - Date.parse(currentTime.replace(/-/g, "/")) <= 0) {
        //                     // 活动已经开始
        //                     countDowns.push(
        //                         countdown.init(endTime, function(hour, minute, second) {
        //                             $hour.text()   != hour   && $hour.text(hour);
        //                             $minute.text() != minute && $minute.text(minute);
        //                             $second.text(second);
        //                         }, function() {
        //                             $wrap.parent().parent().next("ul").children("li").each(function(i, element) {
        //                                 $(element).append("<img src=\"/assets/images/empty.png\" class=\"empty-mark\">");
        //                             });
        //                         }).start()
        //                     );
        //                 } else {
        //                     // 活动还未开始
        //                     $wrap.siblings(".countdown-label").text("距离开始");

        //                     // 遍历活动所属商品设置为不能点击到详情页面
        //                     $wrap.parent().parent().next("ul").children("li").each(function(i, element) {
        //                         $(element).addClass("counting").find(".btn-cart").addClass("counting");
        //                     });

        //                     countDowns.push(
        //                         countdown.init(startTime, function(hour, minute, second) {
        //                             $hour.text()   != hour   && $hour.text(hour);
        //                             $minute.text() != minute && $minute.text(minute);
        //                             $second.text(second);
        //                         }, function() {
        //                             $wrap.parent().parent().next("ul").children("li").each(function(i, element) {
        //                                 $(element).removeClass("counting").find(".btn-cart").removeClass("counting");
        //                             });
        //                         }).start()
        //                     );

        //                 }



        //             });
        //         }
        //     });
        // };

        /**
         * 载入特价商品
         */
        // var loadSpecialsGoods = function() {
        //     $.ajax({
        //         url: window.ctx + "mall/specials",
        //         type: "GET",
        //         dataType: "json",
        //         success: function(data) {
        //             var template = _.template($goodsSpecialsTpl.html());
        //             $goodsContinar.empty().append(template(data));
        //             initEcho();
        //         }
        //     })
        // };

        /**
         * 载入热门商品
         */
        // var loadHotGoods = function() {
        //     $.ajax({
        //         url: window.ctx + "mall/hot",
        //         type: "GET",
        //         dataType: "json",
        //         success: function(data) {
        //             var template = _.template($goodsHotTpl.html());
        //             $goodsContinar.empty().append(template(data));
        //             initEcho();
        //         }
        //     })
        // };

        var initEcho = function() {
            setTimeout(function() {
                echo.init({
                    throttle: 1000
                });
            }, 360);

        };

        var addToBasket =  function(e) {
              e.stopImmediatePropagation();
              var $this = $(e.currentTarget);
              var id = $this.data('id');
              basket.increase(id, 1, 1, function(data) {
                  $this.siblings().removeClass('eh-hide');
                  $this.siblings(".number").text(data.result);
                  var url = $this.parents(".goods-item").find(".left").attr("src");
                  utils.flyToCart(e,url);
                  basket.addCart({"id" : id,"quantity" : 1, "type" : 1});
              });
          };

        return mallView;
    });
