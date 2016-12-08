define(['zepto', 'underscore', 'backbone',
        'app/utils',
        'dropload',
        'text!templates/order.html'
    ],
    function($, _, Backbone, utils, _dropload, order) {

        var $page;
        var $orderList;
        var $orderDetail;
        var $orderListContent;
        var $orderDetailTpl;
        var $currentOrderList;  //当前加载的列表对象
        var $orderStatus = 2;   //页面默认加载的订单状态
        var $dropload;   //滚动下拉对象
        var $pageSize = 3;
        var $pageNum = 1;

        var orderView = Backbone.View.extend({
            el: $('#order-page'),
            render: function(orderStatus) {
                $page = this.$el;
                $orderStatus = orderStatus || $orderStatus;
                $pageNum = 1;
                showPage();
            },

            loadDetail: function(orderId) {
                $page = this.$el;
                showDetailPage(orderId);
            }
        });

        var dropload = {
            init : function(){
                $dropload = $('.content').dropload({
                      scrollArea : window,
                      loadDownFn : function(me){
                          if($pageNum == 1){
                              $currentOrderList.empty();
                          }
                          getOrderData();
                      },
                      loadUpFn : function(me){
                          $pageNum = 1;
                          $currentOrderList.empty();
                          getOrderData();
                      }
                });
            },

            lock : function(){
                $dropload.lock();
            },

            reload : function(){
                $dropload.resetload();
            },

            reset : function(flag){
                $pageNum = 1;
                flag = flag || false;
                $dropload.unlock("down");
                $dropload.noData(flag);
                $dropload.resetload();
            }
        };

        var showPage = function() {
            utils.showPage($page, function() {
                $page.empty().append(order);
                $orderListContent = $page.find(".childcontent");
                $orderList = $page.find("#order-list");
                $orderDetail = $page.find("#order-detail");
                $orderDetailTpl = $page.find("#order-detail-template")
                var index = 0;
                //默认加载待配送订单
                if($orderStatus == 100) {
                    index = 0;
                }else {
                    index = $orderStatus - 1; 
                }
               
                $currentOrderList = $orderListContent.eq(index);
                dropload.init();  //初始化dropload插件
                // getOrderData();
                //初始化默认选中的tab
                $page.find(".order-tab-lists li").children().removeClass("order-on");
                $page.find(".order-tab-lists li").eq(index).children().addClass("order-on");
                Event.init();
            });
        }

        var showDetailPage = function(orderId) {
            utils.showPage($page, function() {
                $page.empty().append(order);
                $orderListContent = $page.find(".childcontent");
                $orderList = $page.find("#order-list");
                $orderDetail = $page.find("#order-detail");
                $orderDetailTpl = $page.find("#order-detail-template")

                getOrderDetailData(orderId, $orderStatus);
                Event.init();
            });
        }

        /**
        * 加载订单列表数据
        */
        var getOrderData = function() {
            $.ajax({
                url: window.ctx + "order/show",
                type: "GET",
                loadMask : false,
                dataType: 'json',
                data: {
                    "orderStatus": $orderStatus,
                    "pageSize" : $pageSize,
                    "page" : $pageNum
                },
                success: function(data) {
                    var result = data.result;
                    if(result.data.length > 0 ){
                        var template = _.template($("#order_list_template").html());
                        $orderListContent.hide();
                        $currentOrderList.append(template(result)).show();
                        $pageNum++;
                        $dropload.noData(false);
                        $dropload.resetload();
                        $dropload.unlock();
                    } else {
                        $dropload.noData(true);
                        $dropload.resetload();
                        $dropload.lock("down");
                    }
                }
            });
        }
        /**
        * 加载订单详情数据
        */
        var getOrderDetailData = function(orderId, callback) {
            $.ajax({
                url: window.ctx + "order/detail",
                type: "GET",
                dataType: 'json',
                data: {
                    "orderId": orderId
                },
                success: function(data) {
                    if (typeof callback == 'function') {
                        callback(data);
                    } else {
                        var template = _.template($orderDetailTpl.html());
                        $orderDetail.empty().append(template(data.result)).show();
                        $orderList.hide();
                        $(".he-footer").hide();
                    }
                    $page.on("tap",".he-header .he-header-back",function(){
                        $orderDetail.hide();
                        $orderList.show();
                        window.location.replace(window.ctx + "#order");
                    })
                }
            });
        }

        var Event = {

            init: function() {
                this.orderListTab();
                this.showOrderDetail();
                this.cancelOrder();
                this.removeOrder();
                this.reminderOrder();
                this.receiveOrder();
                this.orderDetailTab();
                this.commentOrder();
            },

            receiveOrder : function(){
              $page.on("tap", ".btn-receive-order", function() {
                  var orderId = $(this).parents(".order").attr("data-id");
                  $currentOrderList = $orderListContent.eq(3);
                  $.ajax({
                      url: window.ctx + "order/receive",
                      type: "GET",
                      dataType: 'json',
                      data: {
                          "orderId": orderId
                      },
                      success: function(data) {
                          $.message("warning", {
                              "content": "确认收货成功，谢谢光临~"
                          }, function() {
                              $currentOrderList.empty();
                              dropload.reset();
                          });
                      }
                  });
              });
            },

            cancelOrder: function() {
                $page.on("tap", ".btn-cancel-order", function() {
                    var cookId = $(this).data("cookid") || null;
                    var merchantId = $(this).data("merchantid") || null;
                    var orderId = $(this).parents(".order").attr("data-id");
                    var $currentOrderList = $orderListContent.eq(1);
                    $.Dialog.confirm('操作提示', '您确定要取消订单？', function() {
                      $.ajax({
                          url: window.ctx + "order/cancel",
                          type: "GET",
                          dataType: 'json',
                          data: {
                              "orderId": orderId,
                              "cookId" : cookId,
                              "merchantId" : merchantId
                          },
                          success: function(data) {
                              $.message("info", {
                                  "content": "取消成功!"
                              }, function() {
                                  $currentOrderList.empty();
                                  dropload.reset();
                              });
                          }
                      });
                    }, '取消', '确定');
                });
            },

            reminderOrder: function() {
                $page.on("tap", ".btn-reminder-order", function() {
                    var orderId = $(this).parents(".order").attr("data-id");
                    $.ajax({
                        url: window.ctx + "order/reminder",
                        type: "GET",
                        dataType: 'json',
                        data: {
                            "orderId": orderId
                        },
                        success: function(data) {
                            $.message("info", {
                                "content": "发送提醒配送成功!"
                            });
                        }
                    });
                });
            },

            removeOrder: function() {
                $page.on("tap", ".btn-remove-order", function() {
                    var orderId = $(this).parents(".order").attr("data-id");
                    $orderStatus = 4;
                    $currentOrderList = $orderListContent.eq(3);
                    $.ajax({
                        url: window.ctx + "order/delete",
                        type: "GET",
                        dataType: 'json',
                        data: {
                            "orderId": orderId
                        },
                        success: function(data) {
                            $.message("info", {
                                "content": "删除成功!"
                            }, function() {
                                $currentOrderList.empty();
                                dropload.reset();
                            });
                        }
                    });
                });
            },

            commentOrder: function() {
                $page.on("tap", ".btn-comment-order", function() {
                    var obj = $(this).parents(".he-myorder-details");
                    var name = obj.data("name");
                    var thumb = obj.data("thumb");
                    var id = obj.data("id");
                    var type = obj.data("type"); //1:商品 2:大厨
                    var price = obj.find(".price").text();
                    var unit = obj.find(".number").text();
                    var orderId = obj.parents(".order").data("id");
                    var comment =  {
                        orderId : orderId,
                        name : name,
                        id : id,
                        type : type,
                        thumb : thumb,
                        unit : unit,
                        price : price
                    };
                    utils.storage.set("orderComment",JSON.stringify(comment));
                    location.hash = "comment";
                })
            },

            orderListTab: function() {
                $orderList.on("tap", ".order-tab-lists li", function() {
                    var that = $(this);
                    that.siblings().children().removeClass("order-on");
                    that.children().addClass("order-on");

                    var index = $(this).index();
                    var orderStatus = that.children().attr("data-status");
                    $currentOrderList = $orderListContent.eq(index);
                    $orderListContent.hide();
                    $currentOrderList.empty().show();

                    $orderStatus = orderStatus;
                    dropload.reset();
                });
            },

            showOrderDetail: function() {
                //点击查看订单详情
                $orderList.on("tap", ".order", function(e) {
                    var tagName = e.target.nodeName.toLowerCase();
                    if (tagName !== 'button' && tagName !== 'a') {
                        var orderId = $(this).attr("data-id");
                        getOrderDetailData(orderId);
                    }
                });
            },

            orderDetailTab: function() {
                $orderDetail.on("tap", ".order-tab-lists li", function() {
                    $(this).siblings().children().removeClass("order-on");
                    $(this).children().addClass("order-on");

                    var index = $(this).index();
                    $orderDetail.find(".childcontent2").hide()
                    $orderDetail.find(".childcontent2").eq(index).show();
                });
            }
        };
        return orderView;
    });
