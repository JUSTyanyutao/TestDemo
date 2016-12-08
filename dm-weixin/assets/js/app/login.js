define(['zepto', 'underscore', 'app/utils'],

    function($, _, utils) {

        var $mobile      = $("#mobile");
        var $password    = $("#password");
        var $btnLogin    = $("#btn-login");
        var $btnRegister = $("#btn-register");

        $(function() {
            // 登陆

            var LoginModel = {

                init: function() {
                    this.goRegisterPage();
                    this.bindLogin();
                },

                // 绑定登陆
                bindLogin: function() {
                    document.getElementById("mobile").value="";
                    document.getElementById("password").value="";
                    $btnLogin.on("tap", function() {
                        LoginModel.login();
                    })
                },

                // 验证手机号码
                verifyMobile: function(mobile) {
                    if (mobile == "") {
                        $.Dialog.info("请输入手机号码")
                        return false;
                    }

                    if (!$.isPhone(mobile)) {
                        $.Dialog.info("您输入的手机号码格式不正确")
                        return false;
                    }

                    return true;
                },

                // 验证密码
                verifyPassword: function(password) {
                    if (password == '') {
                        $.Dialog.info("请输入密码");
                        return false;
                    }

                    return true;
                },

                // 登陆
                login: function() {
                    var mobile   = $mobile.val();
                    var password = $password.val();
                    var $self    = $btnLogin;

                    // 验证手机号码和验证码
                    if (!LoginModel.verifyMobile(mobile)) 
                        return;
                    if (!LoginModel.verifyPassword(password))
                        return;

                    $.ajax({
                        url: window.ctx + "/login/verify",
                        type: "POST",
                        dataType: "json",
                        data: {
                            mobile: mobile,
                            password: password
                        },
                        beforeSend: function() {
                            $self.val("登陆中...").addClass("disable");
                        },
                        success: function(data) {
                            $self.val("登陆成功");
                            location.href = $("#redirect").val();
                        },
                        onError: function() {
                            $self.val("登 陆").removeClass("disable");
                        }
                    });
                },

                /**
                 * 跳转到注册页面
                 */
                goRegisterPage: function() {
                    $btnRegister.on("tap", function() {
                        window.location.href = ctx + "register/step1";
                    });
                }


            };

            LoginModel.init();

        });

    });
