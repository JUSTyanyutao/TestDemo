define(['zepto', 'underscore', 'backbone',
        'app/utils',
        'text!templates/recharge.html'
    ],

    function($, _, Backbone, utils, recharge) {
        var $page;
        var $redirectHash;
        var view = Backbone.View.extend({
            el: $('#recharge-page'),
            render: function(redirectHash) {
                var that = this;
                $redirectHash = redirectHash;
                loadPage(that);
                utils.showHeader({title : "账户充值"});
            },

            events: {

                "tap .he-myrech-money div": 'selectRechargeMoney',

                "tap .he-myrech-payment li": 'selectPaymentType',

                "tap .he-myrech-btn": 'recahrge'
            },

            selectRechargeMoney: function(e) {
                var that = $(e.currentTarget);
                var rechargeMoney = that.data("money") ? that.data("money") : 0;
                $("#recharge_money").val(rechargeMoney);
                that.siblings().removeClass("selected");
                $page.find(".he-myrech-recharge-give").text(that.data('desc'));
                that.addClass("selected")
                if(parseInt(rechargeMoney) === 0){
                    $page.find(".he-myrech-recharge").show();
                } else {
                    $page.find(".he-myrech-recharge").hide();
                }
            },

            selectPaymentType: function(e) {
                var that = $(e.currentTarget);
                if (that.index() > 0) {
                    that.siblings().removeClass("choice");
                    that.addClass("choice");
                    var paymentType = that.data("payment-type");
                }
            },

            recahrge: function(e) {
                var rechargeMoney = $("#recharge_money").val();
                if ($.isBlank(rechargeMoney)) {
                    $.message('warning', {
                        "content": "请选择充值金额"
                    });
                    return;
                }

                if (parseFloat(rechargeMoney) === 0) {
                    $.message('warning', {
                        "content": "充值金额必须大于0"
                    });
                    return;
                }
                var paymentType = $page.find(".choice").data("payment-type");
                switch (parseInt(paymentType)) {
                    case 1:
                        weixinPay(rechargeMoney);
                        break;
                    case 2:
                        alipay();
                        break;
                    default:
                        break;
                }
            }
        });

        //支付宝支付
        var alipay = function() {
            $.message('warning', {
                content: "支付宝暂不可用"
            });
        };

        //微信支付
        var weixinPay = function(rechargeMoney) {
            $.ajax({
                url: window.ctx + "recharge/get_recharge_money/" + rechargeMoney,
                type: "GET",
                dataType: "json",
                success: function(data) {
                    var url = $.param(data.result.recharge) + "&redirectHash=" + $redirectHash;
                    window.location.replace(window.ctx + "pay/wxpay/recharge?" + url);
                }
            })
        };


        var getRechargeData = function(callback) {
            $.ajax({
                url: window.ctx + "recharge/prepaid_list",
                type: "GET",
                dataType: "json",
                success: function(data) {
                    callback(data);
                }
            });
        }

        var showUser = function(){
             $.ajax({
                url: window.ctx + "personalCenter/getUser",
                type: "GET",
                dataType: 'json',
                success: function(data) {
                   $page.find("#userBalance").text(data.result.balance.toFixed(2));
                }
            });
        }

        var loadPage = function(that) {
            utils.showPage(that.$el, function() {
                $page = that.$el;
                getRechargeData(function(data) {
                    var template = _.template(recharge);
                    $page.empty().append(template(data));
                    var moneyList = data.result.list;
                    if (moneyList && moneyList.length > 0) {
                        var tips = "充" + moneyList[0].money + "送" + moneyList[0].give_money + "元";
                        $page.find(".he-myrech-recharge-give").text(tips);
                    }
                    showUser();
                });
            });
        }

        return view;
    });
