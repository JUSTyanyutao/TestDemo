define(['zepto', 'underscore', 'backbone', 'app/utils', 'text!templates/resetPassword.html'], 

	function($, _, Backbone, utils, page) {

		var $page = $("#reset-password-page");
		var $password;
		var $repeatPasswd;
		var token;

		var PageView = Backbone.View.extend({
			el: $page,

			render: function(a) {
				utils.showPage($page, function() {
					$page.empty().append(page);

					$password     = $page.find("#password");
					$repeatPasswd = $page.find("#repeatPasswd");

					token = a;
				});
			},

			events: {
				// 找回密码
				"tap .btn-confirm": 'resetPassword',
				// 登陆页面
                "tap .btn-signin": "redirectToLogin"
			},

			// 登陆页面
			redirectToLogin: function() {
				window.location.href = window.ctx + "login";
			},

			//找回密码
			resetPassword: function(e) {
				var $self    = $(e.currentTarget);
				var password = $password.val();
				var repeat   = $repeatPasswd.val();

                // 验证参数
                if (!verifyPassword(password, repeat))
                    return;

                $.ajax({
                    url: window.ctx + "/member/resetPassword",
                    type: "POST",
                    dataType: "json",
                    data: {
                        password: password,
                        repeatPassword: repeat,
                        token: token
                    },
                    beforeSend: function() {
                        $self.val("修改中...").addClass("disable");
                    },
                    success: function(data) {
                        if (data.err_code == 0) {
                            $self.val("确 定");
                            $.Dialog.info("找回密码成功，请登陆...");
                            window.setTimeout(function() {
                                location.href = ctx + "login";
                            }, 1800);
                        }
                    },
                    onError: function(data) {
                        $self.val("确 定").removeClass("disable");
                    },
                });
			}
		});

		// 验证密码
		var verifyPassword = function(password, repeat) {
            if (password == "") {
                $.Dialog.info("请输入您的密码");
                return false;
            }

            if (repeat == "") {
                $.Dialog.info("请再次输入您的密码");
                return false;
            }

            if (password != repeat) {
                $.Dialog.info("您前后两次输入的密码不一致");
                return false;
            }

            return true;
        }

		return PageView;
});