<?php

//weixin API
define('WX_SEND_URL', 'https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s');
define('WX_ACCESS_TOKEN_URL', 'https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s');
define('WX_OAUTH2_ACCESS_TOKEN_URL', 'https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code');
define('WX_UPLOAD_URL', 'http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s');
define('WX_DOWNLOAD_URL', 'http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s');
define('WX_CREATE_MENU_URL', 'https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s');
define('WX_USER_INFO_URL', 'https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN');
define('WX_QR_URL', 'https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s');
define('WX_DOWNLOAD_QR_URL', 'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s');
define("WX_GET_MESSAGE_TEMPLATE", "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=%s");
define("WX_SEND_MESSAGE_TEMPLATE", "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s");
define("WX_AUTH_URL", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=ehfarm#wechat_redirect"); //微信授权认证
define('WX_GET_USER', 'https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s&next_openid=%s');
define("WX_API_TICKET","https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=%s");
define('WX_UNIFIED_ORDER', 'https://api.mch.weixin.qq.com/pay/unifiedorder'); //统一下单


define('WX_PAY_NOTIFY_URL', WEIXIN_BASE_API_URL . '/payNotify/wx');

/*
 * MicroMessage Constant
 */
define('APP_ID', 'wx2d05bf8c1b824d86');
define('APP_SECRET', '261e2d0234630f4fdca1c9c2d2b87692');

//define('APP_ID', 'wxd2bff322d0c25d5f');
//define('APP_SECRET', '5383aade955f01939562613bef7a2c44');

define('MCH_ID', 1341190201); //商户ID
