<?php

function praseInput() {
    $postStr = file_get_contents('php://input', 'r');

    if (!empty($postStr)) {
        return simplexml_load_string($postStr, 'SimpleXMLElement', LIBXML_NOCDATA);
    } else {
        return false;
    }
}

function getTextResponse($fromUsername, $toUsername, $time, $contentStr) {
    $textTpl = "<xml>
                <ToUserName><![CDATA[%s]]></ToUserName>
                <FromUserName><![CDATA[%s]]></FromUserName>
                <CreateTime>%s</CreateTime>
                <MsgType><![CDATA[text]]></MsgType>
                <Content><![CDATA[%s]]></Content>
                </xml>";
    return sprintf($textTpl, $fromUsername, $toUsername, $time, $contentStr);
}

function getTransferCustomerService($fromUsername, $toUsername, $time) {
    $tpl = "<xml>
                <ToUserName><![CDATA[%s]]></ToUserName>
                <FromUserName><![CDATA[%s]]></FromUserName>
                <CreateTime>%s</CreateTime>
                <MsgType><![CDATA[transfer_customer_service]]></MsgType>
            </xml>";
    return sprintf($tpl, $fromUsername, $toUsername, $time);
}

function getNewsResponse($fromUsername, $toUsername, $time, $item) {
    $newsTpl = "<xml>
                <ToUserName><![CDATA[%s]]></ToUserName>
                <FromUserName><![CDATA[%s]]></FromUserName>
                <CreateTime>%s</CreateTime>
                <MsgType><![CDATA[news]]></MsgType>
                <ArticleCount>%s</ArticleCount>
                <Articles>
                %s
                </Articles>
                </xml>  ";
    return sprintf($newsTpl, $fromUsername, $toUsername, $time, count($item), implode('', $item));
}

function createItem($title, $des, $pic, $url) {
    $itemTpl = "<item>
                <Title><![CDATA[%s]]></Title>
                <Description><![CDATA[%s]]></Description>
                <PicUrl><![CDATA[%s]]></PicUrl>
                <Url><![CDATA[%s]]></Url>
                </item>";
    return sprintf($itemTpl, $title, $des, $pic, $url);
}

function getWxUnifiedorderRequestXml($nonce_str, $openid, $out_trade_no, $spbill_create_ip, $total_fee, $sign) {
    $itemTpl = "<xml>
                <appid>" . APP_ID . "</appid>
                <attach>支付测试</attach>
                <body>JSAPI支付测试</body>
                <mch_id>" . MCH_ID . "</mch_id>
                <nonce_str>%s</nonce_str>
                <notify_url>" . WX_PAY_NOTIFY_URL . "</notify_url>
                <openid>%s</openid>
                <out_trade_no>%s</out_trade_no>
                <spbill_create_ip>%s</spbill_create_ip>
                <total_fee>%s</total_fee>
                <trade_type>JSAPI</trade_type>
                <sign>%s</sign>
                </xml>  ";
    return sprintf($itemTpl, $nonce_str, $openid, $out_trade_no, $spbill_create_ip, $total_fee, $sign);
}

/**
 * 获取openid信息
 *
 * @return string openid信息
 */
function getOpenid() {
	return get_cookie('api_openid_token');
}


/**
 * 保存openid信息
 * 
 * @param string $openid openid信息
 */
function setOpenid($openid = '') {
	set_cookie('api_openid_token', $openid, COOKIE_EXPIRED_TIME);
}

/**
 * 获取接口访问凭证
 * 
 * @return string 接口访问凭证
 */
function getAccessToken() {
	return get_cookie('api_access_token');
}

/**
 * 保存接口访问凭证
 * 
 * @param string $accessToken 接口访问凭证
 */
function setAccessToken($accessToken = '') {
	//set_cookie('api_access_token', $accessToken, COOKIE_EXPIRED_TIME);
    // 设置7100有效期，防止用无效的access_token请求
    set_cookie('api_access_token', $accessToken, 7100);
}

/**
 * 获取接口置换凭证
 * 
 * @return string 接口置换凭证
 */
function getRefreshToken() {
	return get_cookie('api_refresh_token');
}

/**
 * 保存接口置换凭证
 * 
 * @param string $refreshToken 保存接口置换凭证
 */
function setRefreshToken($refreshToken = '') {
	return set_cookie('api_refresh_token', $refreshToken, COOKIE_EXPIRED_TIME);
}

/**
 * 获取凭证过期时间
 * 
 * @return string 凭证过期时间
 */
function getExpireTime() {
	return get_cookie('api_expire_time');
}

/**
 * 保存凭证过期时间
 * 
 * @param integer $expireTime 凭证过期时间
 */
function setExpireTime($expireTime = 0) {
	return set_cookie('api_expire_time', $expireTime, COOKIE_EXPIRED_TIME);
}

/**
 * 获取市场编号
 * 
 * @return integer 市场编号
 */
function getMarketId() {
    return get_cookie('api_market_id');
}

/**
 * 保存市场编号
 * 
 * @param integer $marketId 市场编号
 */
function setMarketId($marketId = 0) {
    return set_cookie('api_market_id', $marketId, COOKIE_EXPIRED_TIME);
}

/**
 * 判断是否为微信浏览器
 * 
 * @return boolean 微信浏览器返回true
 */
function isWeixin(){ 
    if ( strpos($_SERVER['HTTP_USER_AGENT'], 'MicroMessenger') !== false ) {
        return true;
    }   
    return false;
}

 /**
 * 判断是否是ajax请求
 * 
 * @return boolean 微信浏览器返回true
 */
function isAjax(){
    if(isset($_SERVER["HTTP_X_REQUESTED_WITH"]) && strtolower($_SERVER["HTTP_X_REQUESTED_WITH"])=="xmlhttprequest"){ 
        return TRUE;
    }
    return FALSE;
}

/**
 * 跳转登陆
 */
function redirectLogin() {
    if(isAjax()){
        exit(json_encode(array("redirect_code" =>1, "redirect_url" => site_url("/")."login?redirect_url=".urlencode($_SERVER['HTTP_REQUEST_HASH']))));
    } else {
        header("Location:".site_url("/")."login?redirect_url=".urlencode('http://'.$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI']));
        exit;
    }
}



