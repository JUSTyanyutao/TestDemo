define(['zepto', 'backbone', 'underscore', 'app/utils', 'text!templates/forgotPassword.html'], 

	function($, Backbone, _, utils, page) {

		var $page = $("#forgot-password-page");
		var $mobile;
		var $captcha;

		var PageView = Backbone.View.extend({
			el: $page,

			render: function() {
				utils.showPage($page, function() {
					$page.empty().append(page);

					$mobile  = $page.find("#mobile");
					$captcha = $page.find("#captcha");
				});
			},

			events: {
				// 跳转到密码重置页面
				"tap .btn-next": "redirectToResetPage",
				// 点击发送短信验证码
				"tap .btn-captcha": "sendCaptchaCode"
			},

			redirectToResetPage: function(e) {
                var mobile  = $mobile.val();
                var captcha = $captcha.val();
                var $self   = $(e.currentTarget);

                // 验证手机号码和验证码
                if (!verifyMobile(mobile)) 
                    return;
                if (!verifyCaptcha(captcha)) 
                    return;

                $.ajax({
                    url: window.ctx + "member/forgotPassword",
                    type: "POST",
                    dataType: "json",
                    data: {
                        account: mobile,
                        verification: captcha
                    },
                    beforeSend: function() {
                        $self.val("验证中...").addClass("disable");
                    },
                    success: function(data) {
                        if (data.err_code == 0) {
                            $self.val("跳转中...");
                            window.location.hash = "resetPassword/" + data.result;
                        }
                    },
                    onError: function() {
                        $self.val("下一步").removeClass("disable");
                    }
                });
			},

			// 发送短信验证码
			sendCaptchaCode: function(e) {
				var $self  = $(e.currentTarget);
                var mobile = $mobile.val();

                // 验证手机号码
                if (!verifyMobile(mobile)) 
                    return;

                $.ajax({
                    url: window.ctx + "login/sendCode",
                    type: "POST",
                    data: {
                        mobile: mobile,
                        type: 2
                    },
                    dataType: 'json',
                    beforeSend: function() {
                        $self.addClass("disabled");
                    },
                    success: function(data) {
                        $.Dialog.success("发送成功");
                        countDown($self);
                    }
                });
			}
		});

		// 倒计时
		var countDown = function($btn) {
            var timeout = 60;

            $btn.off('tap');

            var handler = window.setInterval(function() {
                --timeout;
                if (timeout <= 0) {
                    $btn.text("获取验证码").removeClass("disabled").on("tap", LoginModel.sendCaptchaCode);
                    return window.clearInterval(handler);
                }
                $btn.text(timeout + "秒");
            }, 1000)
		};

		// 验证手机号码
		var verifyMobile = function(mobile) {
            if (mobile == "") {
                $.Dialog.info("请输入手机号码")
                return false;
            }

            if (!$.isPhone(mobile)) {
                $.Dialog.info("您输入的手机号码格式不正确")
                return false;
            }

            return true;
        };

        // 验证验证码
        var verifyCaptcha = function(captcha) {
        	if (captcha == '') {
        		$.Dialog.info('请输入验证码');
        		return false;
        	}

        	if (!/^\d{4}$/.test(captcha)) {
                $.Dialog.info("验证码必须为4位数字");
                return false;
            }

        	return true;
        };

		return PageView;
	});