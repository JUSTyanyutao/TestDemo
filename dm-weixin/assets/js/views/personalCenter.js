define(['zepto', 'underscore', 'backbone',
        'app/utils',
        'text!templates/personalCenter.html'
    ],

    function($, _, Backbone, utils, personalCenter) {

        var $page = $("#personalCenter-page");

        var view = Backbone.View.extend({
            el: $('#personalCenter-page'),
            render: function() {
                var that = this;
                loadPage(that);
            },
            events : {
                // "tap #btn-logout": "logout",
                "tap .btn-setting": 'settingPage',
                "tap .logged" : "login"
            },
            settingPage: function() {
                window.location.hash = 'setting';
            },
            logout: function() {
                deleteCookie('sxapi_access_token');
                deleteCookie('sxapi_expire_time');
                deleteCookie('sxapi_refresh_token');
                window.location.reload();
            },
            login : function(e){
                var $this = $(e.currentTarget);
                window.location.href = $this.data("url");
            }
        });


        var getPersonData = function(callback){
          $.ajax({
              url: window.ctx + "personalCenter/getUser",
              type: "GET",
              dataType: 'json',
              success: function(data) {
                  typeof callback == 'function' && callback(data);
              }
            });
        }

        var loadPage = function(that) {
              utils.showPage(that.$el,function(){
                  getPersonData(function(data){
                      var template  = _.template(personalCenter);
                      that.$el.empty().append(template(data));
                      redirectEvent();
                  })
              });

                    // // 获取会员积分
                    // $.ajax({
                    //     url: window.ctx + "personalCenter/pointsNoLogin",
                    //     type: "GET",
                    //     dataType: "json",
                    //     success: function(data) {
                    //         $page.find("#points").text(data.result.total_points);
                    //     }
                    // });
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

        /**
        * 初始化跳转事件
        */
        var redirectEvent = function(){
            $page.find("#workerOrder").on("tap",function(){
                window.location.hash = "workerOrder";
            });
            $page.find("#recharge").on("tap",function(){
                window.location.hash = "recharge";
            });
            $page.find("#record").on("tap",function(){
                window.location.hash = "record";
            });
            $page.find("#coupon").on("tap",function(){
                window.location.hash = "coupon";
            });
            $page.find("#favorites").on("tap",function(){
                window.location.hash = "favorites";
            });
            $page.find("#health").on("tap",function(){
                window.location.hash = "health";
            });
            $page.find("#aboutus").on("tap",function(){
                window.location.hash = "aboutus";
            });
        }

        return view;
    });
