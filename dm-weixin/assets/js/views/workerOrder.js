define(['zepto', 'underscore', 'backbone',
        'app/utils',
        'app/weixin',
        'dropload',
        'text!templates/workerOrder.html'
    ],
    function($, _, Backbone, utils, wx, _dropload, order) {

        var $page;
        var $workerOrderList;
        var $workerOrderListContent;
        var $workerOrderDetail;
        var $workerOrderListTab;
        var $currentOrderListPage; //当前加载的列表对象
        var $deliveryStatus = 2; //派送状态
        var $dropload;   //滚动下拉对象
        var $pageSize = 3;
        var $pageNum = 1;

        var view = Backbone.View.extend({
            el: $('#worker-order-page'),
            render: function(deliveryStatus) {
                $page = this.$el;
                $deliveryStatus = deliveryStatus || $deliveryStatus;
                $pageNum = 1;
                loadPage();
            }
        });

        var loadPage = function() {
            utils.showPage($page, function() {
                $page.empty().append(order);

                $workerOrderList = $page.find("#worker-order-list");
                $workerOrderListTab = $page.find(".order-tab-lists li");
                $workerOrderListContent = $page.find(".childcontent");
                $workerOrderDetail = $page.find("#worker-order-detail");

                //默认初始化待接单
                var index = $deliveryStatus - 2;
                $currentOrderListPage = $workerOrderListContent.eq(index);
                dropload.init();  //初始化dropload插件
                $page.find(".order-tab-lists li").children().removeClass("order-on");
                $page.find(".order-tab-lists li").eq(index).children().addClass("order-on");
                Event.init($workerOrderList);
            });
        }

        var dropload = {
            init : function(){
                $dropload = $('.content').dropload({
                      scrollArea : window,
                      loadDownFn : function(me){
                          if($pageNum == 1){
                              $currentOrderListPage.empty();
                          }
                          getWorkerOrderData(me);
                      },
                      loadUpFn : function(me){
                          $pageNum = 1;
                          $currentOrderListPage.empty();
                          getWorkerOrderData(me);
                      }
                });
            },

            reload : function(){
                $dropload.resetload();
            },

            reset : function(flag){
                $pageNum = 1;
                flag = flag || false;
                $dropload.unlock('down');
                $dropload.noData(flag);
                $dropload.resetload();
            }
        };

        var getWorkerOrderData = function() {
            $.ajax({
                url: window.ctx + "workerOrder/lists",
                type: "GET",
                loadMask : false,
                dataType: 'json',
                data: {
                    "deliveryStatus": $deliveryStatus,
                    "page" : $pageNum,
                    "pageSize" : $pageSize
                },
                success: function(data) {
                    var result = data.result;
                    if(result.data.length > 0 ){
                        var template = _.template($("#worker-order-list-template").html());
                        $workerOrderListContent.hide();
                        $currentOrderListPage.append(template(result)).show();
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

        var getWorkerOrderDetailData = function(orderId, callback) {
            $.ajax({
                url: window.ctx + "order/detail",
                type: "GET",
                dataType: 'json',
                data: {
                    "orderId": orderId
                },
                success: function(data) {
                    callback(data);
                }
            });
        }

        /**
         * [订单列表状态流转时的选项卡切换]
         * @param  {[type]} index [列表选项卡的索引序号]
         * @return {[type]}       [当前选中的选项卡对象]
         */
        var switchTab = function(index) {
            var currentTab = $workerOrderListTab.eq(index).find("span");
            $workerOrderListTab.find("span").removeClass("order-on");
            currentTab.addClass("order-on");
            return currentTab;
        }

        /**
         * [工人接单请求逻辑]
         * @param  {[type]} orderId      [当前订单ID]
         * @param  {[type]} index        [订单列表选项卡的索引序号]
         * @param  {[type]} currentOrder [当前操作订单的页面对象]
         * @return {[type]}              [description]
         */
        var acceptOrderHandler = function(orderId, index, currentOrder) {
            $.ajax({
                url: window.ctx + "workerOrder/accept",
                type: "GET",
                dataType: 'json',
                data: {
                    "orderId": orderId
                },
                success: function(data) {
                    $.message("warning", {
                        "content": "接单成功"
                    }, function() {
                        var currentTab = switchTab(index); //切换选项卡
                        $currentOrderListPage = $workerOrderListContent.eq(index);
                        $deliveryStatus = currentTab.data("status"); //派送状态
                        $currentOrderListPage.empty();
                        dropload.reset();
                    });
                }
            });
        }

        /**
         * [订单派送]
         * @param  {[type]} orderId      [当前订单ID]
         * @param  {[type]} barCode      [当前扫描条码]
         * @param  {[type]} index        [切换选项卡]
         * @param  {[type]} currentOrder [当前订单对象]
         * @return {[type]}              [description]
         * @deprecated [配送订单扫码成功后，不需要对当前点击的订单做校验，一切以扫码订单为准]
         */
        var deliveryOrderHandler = function(orderId, barCode, index, currentOrder) {
            $.ajax({
                url: window.ctx + "workerOrder/delivery",
                type: "GET",
                dataType: 'json',
                data: {
                    "orderId": orderId,
                    "barCode": barCode
                },
                success: function(data) {
                    $.message("warning", {
                        "content": "派单已开始,抓紧派送吧~"
                    }, function() {
                        //切换选项卡
                        var currentTab = switchTab(index);
                        $currentOrderListPage = $workerOrderListContent.eq(index);
                        $deliveryStatus = currentTab.data("status"); //派送状态
                        $currentOrderListPage.empty();
                        dropload.reset();
                    });
                }
            });
        }

        /**
         * [扫码派送订单，不对当前点击订单做校验]
         * @param  {[type]} barCode      [当前扫描条码]
         * @param  {[type]} index        [切换选项卡]
         * @return {[type]}              [description]
         */
        var uncheckDeliveryOrder = function(barCode, index) {
            $.ajax({
                url: window.ctx + "workerOrder/uncheckDelivery",
                type: "GET",
                dataType: 'json',
                data: {
                    "barCode": barCode
                },
                success: function(data) {
                    var orderId = data.result.orderId;
                    var orderSn = data.result.orderSn;
                    $.message("warning", {
                        "content": "订单【"+orderSn+"】派单成功, 请及时配送~"
                    }, function() {
                        //切换选项卡
                        var currentTab = switchTab(index);
                        var currentOrderList = $workerOrderListContent.eq(index);
                        $deliveryStatus = currentTab.data("status"); //派送状态
                        $currentOrderListPage.empty();
                        dropload.reset();
                    });
                }
            });
        }

        /**
         * [removeDeliveredOrder 删除已配送完成的订单]
         * @param  {[type]} orderId [订单ID]
         * @param { type} [currentOrderList] [当前订单列表对象]
         * @return {[type]}         [description]
         */
        var removeDeliveredOrder = function(orderId,currentOrderList){
            var id;
            currentOrderList.find(".bold-border").each(function(){
                id = $(this).find(".commodity-lists").attr("data-id");
                //如果相等则移除
                if(orderId == id){
                    $(this).remove();
                    return false;
                }
            });
        }

        var Event = {

            init: function() {
                this.workerOrderListTab();
                this.showWorkerOrderDetail();
                this.acceptOrder();
                this.deliveryOrder();
                this.completeDeliveryOrder();
                this.workerOrderDetailTab();
            },

            acceptOrder: function() {
                $page.on("tap", ".btn-accept-order", function(e) {
                    if (e.target.nodeName.toLowerCase() !== 'button') {
                        return;
                    }
                    var orderId = $(this).parents(".order").attr("data-id");
                    var currentOrder = $(this).parents(".order");
                    acceptOrderHandler(orderId, 1, currentOrder);
                });
            },

            invokeScanQRCode: function(callback) {
                //判断是否支持微信对象
                wx.scanQRCode(1, function(result) {
                    var arr = result.split(",");
                    result = arr.length > 1 ? arr[1] : arr[0];
                    if (!$.isNumber($.trim(result))) {
                        $.message("warning", {
                            "content": "条码格式不对,请扫描正确的条形码"
                        });
                    } else {
                        callback($.trim(result));
                    }
                })
            },


            deliveryOrder: function(barCode) {
                //手动派送
                $page.on("tap", ".btn-manual-delivery", function() {
                    var that = $(this);
                    var orderId = that.parents(".order").data("id");
                    var currentOrder = that.parents(".order");
                    var content = "<input type=\"text\" id=\"dialog_barcode\" style=\"width:99%;height:40px\" />";
                    $.Dialog.confirm("请手动输入订单条码", content, function() {
                        var barcode = $('#dialog_barcode').val();
                        console.log(barcode);
                        if ($.isBlank(barcode)) {
                            $.message("warning", {
                                content: '条码不能为空'
                            });
                            return;
                        }
                        uncheckDeliveryOrder(barcode, 2);
                    });
                });

                //订单扫码派送
                $page.on("tap", ".btn-scancode-delivery", function() {
                    var that = $(this);
                    var currentOrder = that.parent(".order");
                    var orderId = that.parent(".order").data("id");
                    Event.invokeScanQRCode(function(barCode) {
                        // deliveryOrder(orderId, barCode, 2, currentOrder);
                        uncheckDeliveryOrder(barCode, 2);
                    });
                });
            },

            completeDeliveryOrder : function(){
              //订单扫码派送
              $page.on("tap", ".btn-complete-delivery", function() {
                  var that = $(this);
                  var currentOrder = that.parents(".order");
                  var orderId = currentOrder.data("id");
                  $.ajax({
                      url: window.ctx + "workerOrder/complete",
                      type: "POST",
                      dataType: 'json',
                      data: {
                          "orderId": orderId
                      },
                      success: function(data) {
                          $.message("warning", {
                              "content": "派单完成，等待用户确认~"
                          }, function() {
                              var index  = 3;
                              //切换选项卡
                              var currentTab = switchTab(index);
                              $currentOrderListPage = $workerOrderListContent.eq(index);
                              $deliveryStatus = currentTab.data("status"); //派送状态
                              $currentOrderListPage.empty();
                              dropload.reset();
                          });
                      }
                  });
              });
            },

            workerOrderListTab: function() {
                $workerOrderList.on("tap", ".order-tab-lists li", function() {
                    var that = $(this).find("span");
                    $(this).parent().find("span").removeClass("order-on");
                    that.addClass("order-on");

                    var index = $(this).index();
                    var deliveryStatus = that.attr("data-status");
                    $currentOrderListPage = $workerOrderListContent.eq(index);
                    $workerOrderListContent.hide();
                    $currentOrderListPage.show();
                    $deliveryStatus = deliveryStatus;
                    dropload.reset();
                });
            },
            //点击列表查看详情
            showWorkerOrderDetail: function() {
                $workerOrderList.on("tap", ".order", function(e) {
                    if (e.target.nodeName.toLowerCase() == 'button') {
                        return;
                    }
                    var orderId = $(this).data("id");
                    getWorkerOrderDetailData(orderId, function(data) {
                        var template = _.template($("#worker-order-detail-template").html());
                        $workerOrderDetail.empty().append(template(data.result)).show();
                        $workerOrderList.hide();
                        $page.on("tap",".he-header .he-header-back",function(){
                            $workerOrderDetail.hide();
                            $workerOrderList.show();
                            window.location.replace(window.ctx + "#workerOrder");
                        })
                    });
                });
            },

            workerOrderDetailTab: function() {
                $workerOrderDetail.on("tap", ".order-tab-lists li", function() {
                    $(this).parent().find("span").removeClass("order-on");
                    $(this).find("span").addClass("order-on");

                    $workerOrderDetail.find(".childcontent2").hide()
                    $workerOrderDetail.find(".childcontent2").eq($(this).index()).show();
                });
            }
        };
        return view;
    });
