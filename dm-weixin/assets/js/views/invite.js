function GetQueryString(name) {
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
  var r = window.location.search.substr(1).match(reg);
  if (r != null)return unescape(r[2]);
  return null;
}


$(function () {

  define(['zepto', 'underscore', 'backbone',
        'app/utils',
        'text!templates/invite.html'
      ],

      function ($, _, Backbone, utils, tem) {
        var $type;
        var view = Backbone.View.extend({
          el: $('#aboutus-page'),
          render: function (type) {
            var that = this;
            $type = type;
            loadPage(that);
          },

          events: {
            // 领取
            'tap #zmc_invite_receive_btn': "receive",
            // 立即使用按钮
            'tap #zmc_invite_use_btn': "touse",
            'tap #zmc_invite_received_btn': "touse",
            'tap #zmc_invite_toreg_btn': "toReg"
          },
          // 领取
          receive: function () {
            var mobile = $("#zmc_invite_receive_ipt").val();
            var shareId = GetQueryString("share");
            if (mobile == "") {
              $.Dialog.info("请输入手机号码");
              return false;
            }

            if (!$.isPhone(mobile)) {
              $.Dialog.info("您输入的手机号码格式不正确");
              return false;
            }
            if (!shareId) {
              $.Dialog.info("无效的shareId");
              return false;
            }

            $.get("/coupon/receive", {share_id: shareId, mobile: mobile}, function (rsp) {
              var rspJson = JSON.parse(rsp);
              if (rspJson.err_code != 0) {
                $.Dialog.info(rspJson.err_msg);
                return;
              }
              var data = rspJson.result;
              if (data.result_code == 1){
                $("#zmc_invite_receive_wrap").addClass('hidden');

                $("#zmc_invite_receive_success_wrap > p > span").text(rspJson.result.denomination + "元优惠券");
                $("#zmc_invite_receive_success_wrap").removeClass("hidden");
                return;
              } else if (data.result_code == 2) {
                $("#zmc_invite_receive_wrap").addClass('hidden');
                $("#zmc_invite_receive_received_wrap").removeClass("hidden");
                return;
              } else if (data.result_code == 3) {
                $("#zmc_invite_receive_wrap").addClass('hidden');
                $("#zmc_invite_receive_unreg_wrap").removeClass("hidden");
                $("#zmc_invite_receive_unreg_wrap > p > span").text(data.denomination + "元优惠券");
                $("#zmc_invite_receive_unreg_wrap > p > a").html(mobile);
                return;
              }


            });
          },
          // 立即使用
          touse: function () {
            window.location.href = window.ctx;
          },
          // 去注册
          toReg: function () {
            window.location.href = window.ctx + "/register/step1";
          }
        });

        var loadPage = function (that) {
          utils.showPage(that.$el, function () {
            var template = _.template(tem);
            that.$el.empty().append(template());
            if (null != $type && typeof($type) !== 'undefined') {
              $(".he-header").hide();
              $page.css("padding-top", "0px");
            }
          });
        };
        return view;
      });


});


