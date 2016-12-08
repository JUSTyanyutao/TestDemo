<?php

if (!defined('BASEPATH')) {
    exit('No direct script access allowed');
}

class Wx extends CI_Controller {

    private $TOKEN = "howeat";

    function __construct() {
        parent::__construct();
    }

    public function valid() {
        $echoStr = $this->input->get("echostr");
        if ($this->checkSignature()) {
            echo $echoStr;
            exit;
        } else {
            echo "access deny!";
            exit;
        }
    }

    public function index() {

        // $this->valid();
        $this->userInput = praseInput();
        if (!$this->userInput) {
            echo "no request data";
            exit;
        }
        $this->load->model('event_model');
        $this->load->model('wx_model');
        //关注、菜单等事件处理
        if ($this->userInput->MsgType == 'event') {
            log_message('debug', "+++++++++++++++++  Listen Event +++++++++++++++++++++++");
            //更新过期时间
            $this->event_model->updateUserExpire();
            $this->event_model->response();
        }
        //发送文本、图片等我们的微信公众号
        else {
            //更新过期时间
            $this->event_model->updateUserExpire();
            $this->wx_model->sendMsgToUs();
        }
    }

    private function checkSignature() {
        $signature = $this->input->get("signature");
        $timestamp = $this->input->get("timestamp");
        $nonce = $this->input->get("nonce");

        $token = $this->TOKEN;
        $tmpArr = array($token, $timestamp, $nonce);
        sort($tmpArr, SORT_STRING);
        $tmpStr = sha1(implode($tmpArr));

        if ($tmpStr == $signature) {
            return true;
        } else {
            return false;
        }
    }

    //菜单相关
    public function createMenu() {
        $data = array(
            'button' => array(

                array(
                    'type' => 'view',
                    'name' => '逛菜场',
                    'url'  => WX_MOBILE_SITE.'/#main'
                    ),
                array(
                    'type' => 'view',
                    'name' => '找大厨',
                    'url'  => WX_MOBILE_SITE.'/#cooks'
                    ),
                array(
                    'name' => '我的',
                    'sub_button' => array(
                        array(
                            'type' => 'view',
                            'name' => '会员中心',
                            'url'  => WX_MOBILE_SITE.'/#personalCenter'
                            ),
                        // array(
                        //     'type' => 'view',
                        //     'name' => '我的订单',
                        //     'url'  => WX_MOBILE_SITE.'/#order'
                        //     ),
                        array(
                            'type' => 'view',
                            'name' => '我要充值',
                            'url'  => WX_MOBILE_SITE.'/#recharge'
                            ),
                        array(
                            'type' => 'view',
                            'name' => '我的优惠券',
                            'url'  => WX_MOBILE_SITE.'/#coupon'
                            ),
                        array(
                            'type' => 'view',
                            'name' => '下载APP',
                            'url'  => 'http://a.app.qq.com/o/simple.jsp?pkgname=com.ruitukeji.zmc'
                            ),
                        array(
                            'type' => 'view',
                            'name' => '关于我们',
                            'url'  => WX_MOBILE_SITE.'/#aboutus/1'
                            )
                        )
                    )
                )
            );
        $this->load->model('wx_model');
        $this->wx_model->createMenu($data);
    }

}
