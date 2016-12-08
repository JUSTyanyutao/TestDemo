<?php

if (!defined('JSAPI_ROOT')) {
    define('JSAPI_ROOT', dirname(__FILE__) . '/');
    require(JSAPI_ROOT . 'WxPay/WxPayHelper.php');
}
include_once("Wxapi.php");

class Jsapicall {

    public function __construct() {

        $this->commonUtil = new CommonUtil();
        $this->wxPayHelper = new WxPayHelper();
    }

    public function create_pay_request($out_trade_no, $openid, $total_fee = 1, $clientIp = "127.0.0.1") {
        $this->wxPayHelper->setParameter("appid", APP_ID);
        $this->wxPayHelper->setParameter("body", "生鲜商品");
        $this->wxPayHelper->setParameter("mch_id", MCH_ID);
        $this->wxPayHelper->setParameter("nonce_str", $this->commonUtil->create_noncestr());
        $this->wxPayHelper->setParameter("out_trade_no", $out_trade_no);
        $this->wxPayHelper->setParameter("total_fee", $total_fee);
        $this->wxPayHelper->setParameter("openid", $openid);
        $this->wxPayHelper->setParameter("fee_type", "CNY");
        $this->wxPayHelper->setParameter("notify_url", WX_PAY_NOTIFY_URL);
        $this->wxPayHelper->setParameter("spbill_create_ip", $clientIp);
        $this->wxPayHelper->setParameter("trade_type", "JSAPI");
        $this->wxPayHelper->setParameter("sign", $this->wxPayHelper->create_md5_sign());
        $data = $this->wxPayHelper->parseXml();
        $wxapi = new Wxapi();
        $result = $wxapi->postXml(WX_UNIFIED_ORDER, $data);
        $prepayId = "";
        if ($result->result_code == "SUCCESS") {
            $prepayId = $result->prepay_id;
        }
        return $this->wxPayHelper->create_biz_package($prepayId);
    }

}
