define(['zepto', 'underscore', 'backbone',
        'app/utils',
        'text!templates/account_balance.html'
    ],
    function($, _, Backbone, utils, accountBalance) {
        var $page;
        var $accountBalanceTpl;


        var accountBalanceView = Backbone.View.extend({
            el: $('#accountBalance-page'),
            render: function() {
                $page = this.$el;
                showPage();
            },
            events : {
                "tap .he-balance-btn" : "submitRefund"
            },

            submitRefund : function(){
                var allowRefundMoney = $("#allowRefundMoney").text();
                if($.isBlank(allowRefundMoney) || parseFloat(allowRefundMoney) <= 0){
                    $.message("warning", {
                        "content": "退款金额不可用"
                    });
                    return;
                }
                $.ajax({
                    url: window.ctx + "personalCenter/refund",
                    type: "POST",
                    dataType: 'json',
                    data : {"refundMoney" : allowRefundMoney},
                    success: function(data) {
                      $.message("warning", {
                          "content": "退款成功!"
                      }, function() {
                          window.location.reload();
                      });
                    }
                });
            }
        });

        var showPage = function() {
            utils.showPage($page,function(){
              $page.empty().append(accountBalance);
              $accountBalanceTpl = $page.find("#account-balance-template");
              getRefundList(function(data){
                  var template = _.template($accountBalanceTpl.html());
                  $page.find("#accountBalancePage").empty().append(template(data)).show();
              });
            });
        }

        var getRefundList = function(callback) {
            $.ajax({
                url: window.ctx + "personalCenter/refundInfo",
                type: "GET",
                dataType: 'json',
                success: function(data) {
                    callback(data.result);
                }
            });
        }


        return accountBalanceView;
    });
