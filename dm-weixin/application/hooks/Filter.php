<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * 全局过滤器
 *
 * User: whan
 * Date: 11/13/15
 * Time: 2:35 PM
 */
class Filter {

    /**
     * 匿名可访问的URL地址
     * @var type
     */
    private $anonFilterUrls = array('index','login', 'register/*', 'activity', 'comment/lists', 'cook/lists',
        'cook/info', 'book/categories','consulting','book/findList', 'book/detail', 'book/findShareList',
        'book/searchByName', 'merchant/detail', 'market/histories', 'market/location', 'market/name',
        'mall', 'goodsDetail/*',
        'market/bind','activity', 'activity', 'login/agreement','main','shoppingCart','personalCenter', 'mall/getGoodsCategory',
        'wx', 'api', 'map/MicroMessageToBaidu', 'address/provinces', 'address/cities', 'address/districts', 'member/forgotPassword', 'member/resetPassword',
        'coupon');
    private $CI;

    public function __construct(){
        $this->CI = &get_instance();
    }

    /**
     * 检测访问URL是否允许匿名访问
     * @param type $url
     * @return boolean
     */
    function checkRequestUrl($url) {
        if (!empty($this->anonFilterUrls)) {
            foreach ($this->anonFilterUrls as $value) {
                if (strpos($value, $url) !== FALSE) {
                    return TRUE;
                }
            }
        }
        return FALSE;
    }

    public function filter() {
        $accessToken = getAccessToken();
        //检测是否允许匿名访问
        if (empty($accessToken)) {
            $url = $this->CI->uri->rsegment(1);
            if (empty($url) || $this->checkRequestUrl($url)) {
                return;
            } else {
                redirectLogin();
            }
        } else {
            return;
        }
    }

    /**
     * 微信钩子
     *
     * 检查到微信浏览器，判断是否已经获取用户的openid信息，若无，则通过OAuth认证获取用户的openid信息
     */
    public function weixin() {
        log_message('debug', 'weixin hook');
        if (isWeixin() && empty(getOpenid())) {
            log_message('debug', "This is a mm request");
            // 微信浏览器，且openid为空
            $code = $this->CI->input->get('code');
            if (empty($code)) {
                $url = sprintf(WX_AUTH_URL, APP_ID, urlencode('http://'.$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI']));
                header('Location: ' . $url);
                exit();
            }

            // 获取openid，并保存到cookie中
            $this->CI->load->model('wx_model');
            $openid = $this->CI->wx_model->getOpenidByCode($code);
            setOpenid($openid);
        }
    }

}
