<?php
defined('BASEPATH') OR exit('No direct script access allowed');


define('APP_VERSION', '1.0');
define('PLATEFORM_TYPE', 'weixin');
define('PLATEFORM_VERSION', '1.0');
//cookie过期时间,这里设置为5年相当于永不过期
define('COOKIE_EXPIRED_TIME', 5*365*24*3600);

define('ASSETS_VERSION', '1.0.1');

define('WX_MOBILE_SITE', "http://weixin.zenmechi.cc/");
// define('WEIXIN_BASE_API_URL', 'http://115.159.227.219:8088/fanfou-api');
// define('BASE_API_URL', 'http://115.159.227.219:8088/fanfou-api');
define('WEIXIN_BASE_API_URL', 'http://weixin.zenmechi.cc:8088/ydd-api');
define('BASE_API_URL', 'http://weixin.zenmechi.cc:8088/ydd-api');
define('IMAGE_DOMAIN', 'http://image.hexianhui.com');
