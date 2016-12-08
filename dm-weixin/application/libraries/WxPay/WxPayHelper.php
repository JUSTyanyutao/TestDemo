<?php

/**
 *
 */
include_once("CommonUtil.php");
include_once("WxPay.config.php");
include_once("SDKRuntimeException.class.php");
include_once("MD5SignUtil.php");

class WxPayHelper {

    var $parameters; //cft 参数

    function __construct() {

    }

    function setParameter($parameter, $parameterValue) {
        $this->parameters[CommonUtil::trimString($parameter)] = CommonUtil::trimString($parameterValue);
    }

    function getParameter($parameter) {
        return $this->parameters[$parameter];
    }

    protected function create_noncestr($length = 16) {
        $chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        $str = "";
        for ($i = 0; $i < $length; $i++) {
            $str.= substr($chars, mt_rand(0, strlen($chars) - 1), 1);
            //$str .= $chars[ mt_rand(0, strlen($chars) - 1) ];
        }
        return $str;
    }

    function check_cft_parameters() {
        if ($this->parameters["body"] == null ||
                $this->parameters["out_trade_no"] == null || $this->parameters["total_fee"] == null || $this->parameters["fee_type"] == null ||
                $this->parameters["notify_url"] == null || $this->parameters["spbill_create_ip"] == null
        ) {
            return false;
        }
        return true;
    }

    protected function get_cft_package() {
        try {
            if (null == APPKEY || "" == APPKEY) {
                throw new SDKRuntimeException("密钥不能为空！" . "<br>");
            }
            $commonUtil = new CommonUtil();
            ksort($this->parameters);
            $unSignParaString = $commonUtil->formatQueryParaMap($this->parameters, false);
            $paraString = $commonUtil->formatQueryParaMap($this->parameters, true);
            $md5SignUtil = new MD5SignUtil();
            return $paraString . "&sign=" . $md5SignUtil->sign($unSignParaString, $commonUtil->trimString(APPKEY));
        } catch (SDKRuntimeException $e) {
            die($e->errorMessage());
        }
    }

    protected function get_pay_sign($param) {
        try {
            if (APPKEY == "") {
                throw new SDKRuntimeException("APPKEY为空！" . "<br>");
            }
            $commonUtil = new CommonUtil();
            $bizString = $commonUtil->formatQueryParaMap($param, false);
            $md5SignUtil = new MD5SignUtil();
            return $md5SignUtil->sign($bizString, APPKEY);
        } catch (SDKRuntimeException $e) {
            die($e->errorMessage());
        }
    }

    /**
     * 创建MD5密钥
     * @param type $key
     * @param type $value
     * @return type
     */
    public function create_md5_sign() {
        $commonUtil = new CommonUtil();
        $unSignParaString = $commonUtil->formatQueryParaMap($this->parameters, false);
        $md5SignUtil = new MD5SignUtil();
        return $md5SignUtil->sign($unSignParaString, APPKEY);
    }

    //生成jsapi支付请求json
    /*
      "appId" : "wxf8b4f85f3a794e77", //公众号名称，由商户传入
      "timeStamp" : "189026618", //时间戳这里随意使用了一个值
      "nonceStr" : "adssdasssd13d", //随机串
      "package" : 统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
      //扩展字段，由商户传入
      "signType" : "SHA1", //微信签名方式:sha1
      "paySign" : "7717231c335a05165b1874658306fa431fe9a0de" //微信签名
     */
    function create_biz_package($prepayId) {
        try {

            if ($this->check_cft_parameters() == false) {
                throw new SDKRuntimeException("生成package参数缺失！" . "<br>");
            }
            $nativeObj["appId"] = APPID;
            $nativeObj["package"] = "prepay_id=" . $prepayId;
            $nativeObj["timeStamp"] = strval(time());
            $nativeObj["nonceStr"] = $this->create_noncestr();
            $nativeObj["signType"] = SIGNTYPE;
            $nativeObj["paySign"] = $this->get_pay_sign($nativeObj);
            return $nativeObj;
        } catch (SDKRuntimeException $e) {
            die($e->errorMessage());
        }
    }

    public function parseXml() {
        $commonUtil = new CommonUtil();
        return $commonUtil->arrayToXml($this->parameters);
    }

}
