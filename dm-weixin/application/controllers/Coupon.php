<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Coupon extends MY_Controller
{

    public function __construct()
    {
        parent::__construct();
        $this->load->model("order_model");
    }

    public function index()
    {

    }


    public function getList()
    {
        $accessToken = $this->getAccessToken();
        $res = $this->Api_model->getCouponList($accessToken, 1, 100);
        echo json_encode($res);
        exit;
    }

    /**
     * 分享优惠券
     */
    public function share(){
        $orderId = $this->input->get("order_id");
        $accessToken = $this->getAccessToken();
        $res = $this->order_model->shareCoupon($accessToken, $orderId);
        echo json_encode($res);
    }

    public function receive()
    {
        $shareId = $this->input->get("share_id");
        $mobile = $this->input->get("mobile");
        $res = $this->order_model->receiveCoupon($shareId,$mobile);
        echo json_encode($res);
    }
}
