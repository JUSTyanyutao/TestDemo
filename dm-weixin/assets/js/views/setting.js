define(['zepto', 'underscore', 'backbone', 'app/utils', 'app/weixin', 'text!templates/setting.html'],

	function($, _, Backbone, utils, wx, page) {

		var $page = $("#setting-page");
		var $realname;
		var $mobile;
		var $mail;
		var $market;
		var $signature;
		var $avatar;

		var PageView = Backbone.View.extend({
			el: $page,

			render: function() {
				utils.showPage(this.$el, function() {
					$page.empty().append(page);

					$realname  = $page.find("#realname");
					$mobile    = $page.find("#mobile");
					$mail      = $page.find("#mail");
					$market    = $page.find("#market");
					$signature = $page.find("#signature");
					$avatar    = $page.find("#avatar");

					getMemberInfo();
				});
			},

			events: {
				// 跳转到收货地址
				"tap .btn-address": "redirectAddress",
				// 保存个人信息
				"tap .btn-save": "saveMember",
				// 头像上传
				"tap .btn-avatar": 'uploadAvatar',

				"tap .he-perdata-logout" : 'logout'
			},

			// 上传头像
			uploadAvatar: function() {
					wx.chooseImage(function(localIds) {
						$avatar.attr('src', localIds[0]);
					}, 1);
			},

			logout : function(){
					deleteCookie('sxapi_access_token');
					deleteCookie('sxapi_expire_time');
					deleteCookie('sxapi_refresh_token');
					window.location.hash = "personalCenter";
			},

			// 跳转到收货地址
			redirectAddress: function() {
				window.location.hash = 'addresses';
			},

			//保存个人信息
			saveMember: function() {
				$.ajax({
					url: window.ctx + 'member/update',
					type: 'POST',
					data: {
						signature: $signature.val(),
						name: $realname.val(),
						avatar: $avatar.attr('src')
					},
					success: function(data) {
						$.Dialog.info('保存成功');
					}
				});
			}
		});

		// 加载个人信息
		var getMemberInfo = function() {
			$.ajax({
				url: window.ctx + "/member/info",
				type: "GET",
				dataType: "json",
				success: function(data) {
					$realname.val(data.result.nick_name);
					$mobile.val(data.result.mobile);
					$signature.val(data.result.signature);
					$avatar.attr('src', data.result.avatar_url);
				}
			})
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

		return PageView;
	});
