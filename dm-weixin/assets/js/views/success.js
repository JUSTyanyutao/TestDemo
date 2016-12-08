define(['zepto', 'underscore', 'backbone', 'app/utils', 'app/weixin', 'text!templates/success.html'],

    function ($, _, Backbone, utils, wx, page) {
      function getShare() {
        var result = JSON.parse(utils.storage.get('order_result'));
        var orderId = result.order_id;
        $.get("/coupon/share", {order_id: orderId}, function (result) {
          var date = JSON.parse(result);
          if (date.err_code != 0) {
            $.Dialog.info(date.err_msg);
            return;
          }
          wx.onMenuShareTimeline(date.result.share_title, date.result.share_link, date.result.share_img);
          wx.onMenuShareAppMessage(date.result.share_title, date.result.share_desc, date.result.share_link, date.result.share_img);
          wx.onMenuShareQQ(date.result.share_title, date.result.share_desc, date.result.share_link, date.result.share_img);
          wx.onMenuShareQZone(date.result.share_title, date.result.share_desc, date.result.share_link, date.result.share_img);
        });
      }

      var $page = $("#success-page");

      var PageView = Backbone.View.extend({
        el: $page,

        render: function () {
          utils.showPage($page, function () {
            var result = JSON.parse(utils.storage.get('order_result'));
            var template = _.template(page);
            $page.empty().append(template(result));
            utils.storage.set("balance", "{}");
          });

          getShare();
        },

        events: {
          "tap .btn-custom": "backToCart",
          "tap #he_paysuc_partake_btn": "share"
        },

        backToCart: function (e) {
          e.stopImmediatePropagation();
          window.location.hash = "shoppingCart";
        },
        share: function() {
          utils.showShareMask($("#success-page"));
        }

      });

      return PageView;
    }
);