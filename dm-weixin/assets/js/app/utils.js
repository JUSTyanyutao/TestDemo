define(['zepto'], function($) {

    var ajax = $.ajax;
    $.extend($, {
        isBlank: function(val) {
            return (val == null || val == "" || val == 'undefined');
        },
        isNumber: function(val) {
            var reg = /^\d+$/;
            if (!val.match(reg)) {
                return false;
            }
            return true;
        },
        isPhone: function(val) {
            var reg = /^(13[0-9]|14\d{1}|15\d{1}|17\d{1}|18\d{1})\d{8}$/;
            return reg.test(val);
        },
        ajax: function(param) {
            var loadMask = typeof(param.loadMask) !== 'undefined' ? param.loadMask : true; //是否支持遮罩层
            var success = param.success || function() {};
            var beforeSend = param.beforeSend || function() {};
            var onError = param.onError || null;
            return ajax({
                type: param.type || 'GET',
                url: param.url || "",
                dataType: param.dataType || null,
                data: param.data || {},
                crossDomain: true,
                beforeSend: function(request) {
                    beforeSend(request);
                    //设置header请求
                    // request.setRequestHeader("version", "1.0");
                    // request.setRequestHeader("source", "weixin");
                    request.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
                    request.setRequestHeader('Request-Hash', window.location.href);
                    if (loadMask) {
                        $.Dialog.loading('show');
                    }
                },
                success: function(data) {
                    //处理text/html类型的服务器重定向请求
                    if (!$.isBlank(data) && typeof(data) == 'string' && data.indexOf("redirectCode") !== -1) {
                        data = eval("(" + data + ")");
                        location.href = data.redirectUrl;
                        return;
                    }

                    //处理application/json类型的服务器重定向请求
                    if (data.redirect_code) {
                        window.location.replace(data.redirect_url);
                        return;
                    }



                    if (data.err_code && data.err_code > 0 && data.err_code != 100001) {
                        //TODO 处理错误异常
                        $.message("warning", {
                            content: data.err_msg
                        });

                        if (typeof param.onError == 'function') {
                            param.onError(data);
                        }

                    } else if (data.err_code && data.err_code > 0 && data.err_code == 100001) {
                        $.Dialog.alert('定位菜场', '您还未选择附近的菜场', function() {
                            window.location.hash = 'market';
                        });
                    } else {
                        success(data);
                    }
                },
                error: param.error || function() {

                },
                complete: param.complete || function(xhr, status) {
                    //TODO 完成处理
                    if (loadMask) {
                        $.Dialog.loading('hide');
                    }
                }
            });
        },
        message: function(type, param, callback) {
            var type = type || 'info';
            var content = param.content || "数据请求失败,请重试!";
            var stayTime = param.stayTime || 1500;
            var callback = callback || function() {};

            var toast = $("#message-mask");
            if (toast.length == 0) {
                $("body").append('<div id="message-mask"></div>');
                toast = $("#message-mask");
            }
            toast.empty().append('<div class=message-' + type + '>' + content + '</div>').show();
            setTimeout(function() {
                toast.hide();
                callback();
                toast = null;
            }, stayTime);
        },

        // 对话框
        Dialog: {
            success: function() {
                var msg = typeof arguments[0] == 'string' ? arguments[0] : '已完成';
                var expire = 1200;
                if (typeof arguments[1] == "number") {
                    expire = arguments[1];
                } else if (typeof arguments[0] == "number") {
                    expire = arguments[0];
                }
                var toast = "<div id=\"toast\"><div class=\"weui_mask_transparent\"></div><div class=\"weui_toast\"><i class=\"weui_icon_toast\"></i><p class=\"weui_toast_content\">" + msg + "</p></div></div>";
                $("body").append(toast);
                setTimeout(function() {
                    $("body").children("#toast").remove();
                }, expire);
            },
            warn: function(msg) {
                alert(msg);
            },
            error: function(msg) {
                alert(msg);
            },
            alert: function() {
                var title = "警告";
                var msg = "";
                var callback = function() {};

                if (arguments.length < 1) {
                    throw new Error("Missing parameter!");
                } else if (arguments.length == 1) {
                    msg = arguments[0];
                } else if (arguments.length == 2) {
                    if (typeof arguments[0] == "string" && typeof arguments[1] == "string") {
                        title = arguments[0];
                        msg = arguments[1];
                    } else if (typeof arguments[0] == "string" && typeof arguments[1] == "number") {
                        msg = arguments[0];
                        callback = arguments[1];
                    } else {
                        throw new Error("Invalid parameters!");
                    }
                } else if (arguments.length == 3) {
                    if (typeof arguments[0] == "string" && typeof arguments[1] == "string" && typeof arguments[2] == "function") {
                        title = arguments[0];
                        msg = arguments[1];
                        callback = arguments[2];
                    } else {
                        throw new Error("Invalid parameters!");
                    }
                }
                var template = "<div class=\"weui_dialog_alert\" id=\"dialog_alert\"><div class=\"weui_mask\"></div><div class=\"weui_dialog\"><div class=\"weui_dialog_hd\"><strong class=\"weui_dialog_title\">" + title + "</strong></div><div class=\"weui_dialog_bd\">" + msg + "</div><div class=\"weui_dialog_ft\"><a href=\"javascript:;\" class=\"weui_btn_dialog btn_dialog_confirm primary\">确定</a></div></div></div>";
                $("body").append(template);

                var $alert = $("body").children(".weui_dialog_alert");
                console.log(  $alert.find(".btn_dialog_confirm") );
                $alert.find(".btn_dialog_confirm").click( function(){
                    $alert.remove();
                    callback();
                });
                /*$alert.find("."btn_dialog_confirm).on("tap", function() {
                    console.log("123");
                    $alert.remove();
                    callback();
                });*/

            },
            confirm: function() {
                if (typeof arguments[0] != "string" || typeof arguments[1] != "string" || typeof arguments[2] != "function") {
                    throw new Error("Invalid parameters!");
                }

                var title      = arguments[0];
                var msg        = arguments[1];
                var callback   = arguments[2];
                var cancelName = typeof arguments[3] == "string" ? arguments[3] : "取消";
                var sureName   = typeof arguments[4] == "string" ? arguments[4] : "确定";

                var template = "<div class=\"weui_dialog_confirm\" id=\"dialog_confirm\"><div class=\"weui_mask\"></div><div class=\"weui_dialog\"><div class=\"weui_dialog_hd\"><strong class=\"weui_dialog_title\">" + title + "</strong></div><div class=\"weui_dialog_bd\">" + msg + "</div><div class=\"weui_dialog_ft\"><a href=\"javascript:;\" class=\"weui_btn_dialog btn_dialog_cancel default\">"+ cancelName +"</a><a href=\"javascript:;\" class=\"weui_btn_dialog btn_dialog_confirm primary\">"+ sureName +"</a></div></div></div>";
                $("body").append(template);

                var $confirm = $("body").children(".weui_dialog_confirm");
                $confirm.find(".btn_dialog_confirm").on('tap', function() {
                    callback();
                    $confirm.remove();
                });
                $confirm.find(".btn_dialog_cancel").on("tap", function() {
                    $confirm.remove();
                });

            },
            loading: function() {
                var status = arguments[0] == 'show' ? 'show' : 'hide';
                var loading = "<div id=\"loadingToast\" class=\"weui_loading_toast\"><div class=\"weui_mask_transparent\"></div><div class=\"weui_toast\"><div class=\"weui_loading\"><div class=\"weui_loading_leaf weui_loading_leaf_0\"></div><div class=\"weui_loading_leaf weui_loading_leaf_1\"></div><div class=\"weui_loading_leaf weui_loading_leaf_2\"></div><div class=\"weui_loading_leaf weui_loading_leaf_3\"></div><div class=\"weui_loading_leaf weui_loading_leaf_4\"></div><div class=\"weui_loading_leaf weui_loading_leaf_5\"></div><div class=\"weui_loading_leaf weui_loading_leaf_6\"></div><div class=\"weui_loading_leaf weui_loading_leaf_7\"></div><div class=\"weui_loading_leaf weui_loading_leaf_8\"></div><div class=\"weui_loading_leaf weui_loading_leaf_9\"></div><div class=\"weui_loading_leaf weui_loading_leaf_10\"></div><div class=\"weui_loading_leaf weui_loading_leaf_11\"></div></div><p class=\"weui_toast_content\">数据加载中</p></div></div>";
                if (status == 'show') {
                    $("body").append(loading);
                } else {
                    $("body").children("#loadingToast").remove();
                }
            },
            info: function(msg) {
                $.message("warning", {
                    content: msg,
                    stayTime: 2000
                });
            }
        }
    });

    var goBack = function(param){

        var jumpHash = param.jumpHash || "";
        var jumpUrl = param.jumpUrl || "";
        $(".page").on("tap", ".he-header .he-header-left", function(e) {
            if ($(e.currentTarget).hasClass('btn-custom')) {
                return;
            }
            if (!$.isBlank(jumpHash)) {
                window.location.hash = jumpHash;
                return;
            }
            if (!$.isBlank(jumpUrl)) {
                window.location.href = jumpUrl;
                return;
            }
            history.back();
        });
    }

    var showHeader = function(param) {
        var jumpHash = param.jumpHash || "";
        var jumpUrl = param.jumpUrl || "";
        var title = param.title || "";
        var showSearch = param.showSearch || false;
        var $wxHead = $(".wx-head");
        $wxHead.show();
        $(".he-footer").show();
        $(".wx-head p").text(title);
        $(".wx-head button").off("tap").on("tap", function() {
            if ($.isBlank(jumpUrl)) {
                history.back();
                return;
            }

            if (!$.isBlank(jumpHash)) {
                window.hash = jumpHash;
            }

            if (!$.isBlank(jumpUrl)) {
                window.location = jumpUrl;
            }
        });


        if (showSearch) {
            $wxHead.find(".wx-he-search").removeClass("eh-hide");
        } else {
            $wxHead.find(".wx-he-search").addClass("eh-hide");
        }
    }


    var hideHeader = function() {
        $(".he-footer").hide();
        $(".page").css("padding-top",0);

    }

    var showPage = function(obj, callback) {

        obj.siblings(".page").addClass("eh-hide");
        obj.removeClass("eh-hide");

        if (typeof callback == 'function') {
            callback();
        }
    }

    /**
     * web存储
     */
    var storage = {
        set: function(key, value) {
            return localStorage.setItem(key, value);
        },
        get: function(key) {
            return localStorage.getItem(key);
        },
        remove: function(key) {
            return localStorage.removeItem(key);
        }
    };


    /**
     * 激活菜单
     */
    var activeMenu = function(sort) {
        var obj;
        switch (sort) {
            case 5:
                obj = $("#menu-page #he-my");
                break;
            case 4:
                obj = $("#menu-page #he-shoppingcart");
                break;
            case 3:
                obj = $("#menu-page #he-market");
                break;
            case 2:
                obj = $("#menu-page #he-menu");
                break;
            case 1:
                obj = $("#menu-page #he-index");
            default:
                break;
        }
        obj.siblings().removeClass("highlight");
        obj.addClass("highlight");
    };

    var flyToCart = function(e, url){
        var $this = $(e.currentTarget);
        var offset = $('#he-shoppingcart').offset(),
        flyer = $("<img class=\"u-flyer\" src='"+url+"' />");
        flyer.fly({
            start: {
                left: e.touches[0].pageX,
                top: e.touches[0].pageY
            },
            end: {
                left: offset.left + 22,
                top: offset.top,
                width: 20,
                height: 20
            },
            onEnd : function(){
                $(".u-flyer").remove();
            }
        });
    };

    /**
     * 显示菜单
     */
    var showMenu = function() {
        var $menuPage = $("#menu-page");
        if (storage.get("has_goods") == 1) {
            var $a = $menuPage.find(".he-btn-shoppingcart").find("a");
            if ($a.find(".point").length <= 0) {
                $a.append("<p class=\"point\"></p>");
            }
        }
        $menuPage.removeClass("eh-hide");
    };
    /**
     * 隐藏菜单
     */
    var hideMenu = function() {
        $("#menu-page").addClass("eh-hide");
    };

    var getParam = function(paramName) {
        //获取当前URL
        var local_url = document.location.href;
        //获取要取得的get参数位置
        var get = local_url.indexOf(paramName + "=");
        if (get == -1) {
            return false;
        }
        //截取字符串
        var get_par = local_url.slice(paramName.length + get + 1);
        //判断截取后的字符串是否还有其他get参数
        var nextPar = get_par.indexOf("&");
        if (nextPar != -1) {
            get_par = get_par.slice(0, nextPar);
        }
        return get_par;
    };


    var Navigator = {
        currentPosition: function(success,error) {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function(position) {
                    var coords = position.coords;
                    console.info(coords.latitude + ', ' + coords.longitude);
                    if (typeof success == 'function') {
                        success(coords.longitude, coords.latitude);
                        // callback('120.73301925701467', '31.25901492308543');
                    }
                }, function(error) {
                    switch (error.code) {
                        case error.TIMEOUT:
                            console.info("A timeout occured! Please try again!");
                            break;
                        case error.POSITION_UNAVAILABLE:
                            console.info('We can\'t detect your location. Sorry!');
                            break;
                        case error.PERMISSION_DENIED:
                            console.info('Please allow geolocation access for this to work.');
                            break;
                        case error.UNKNOWN_ERROR:
                            console.info('An unknown error occured!');
                            break;
                    }
                    if (typeof error == 'function') {
                        error(error.code);
                    }
                }, {
                    enableHighAccuracy: true,
                    timeout: 6000,
                    maximumAge: 3000
                });
            } else {
                alert("Your browser does not support Geolocation!");
            }
        }
    };

    // 判断对象内容是否为空
    var isEmpty = function($obj) {
        return $obj.html() ? false : true;
    };

    // 最长字符串
    var maxLength = function(str, length) {
        var s = str;
        if (str.length > length) {
            s = str.substring(0,length) + "...";
        }
        return s;
    };

    var showShareMask = function($page) {
        var mask = '<div class="mask-title"><img src="assets/img/share_menu.png" alt=""/></div>';

        $page.find(".mask-title").remove();
        $page.append(mask);

        $page.find(".mask-title").on("tap", function() {
            $page.find(".mask-title").remove();
        }); 
    };

    return {
        showPage: showPage,
        showHeader : showHeader,
        storage: storage,
        activeMenu: activeMenu,
        getParam: getParam,
        showMenu: showMenu,
        goBack: goBack,
        hideMenu: hideMenu,
        hideHeader: hideHeader,
        Navigator: Navigator,
        isEmpty: isEmpty,
        maxLength: maxLength,
        flyToCart : flyToCart,
        showShareMask: showShareMask
    };

});
