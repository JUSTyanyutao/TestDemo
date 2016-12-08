<?php

/**
 * Description of wxpay
 *
 * @author ranfi
 */
class Wxpay extends MY_Controller {

    public function __construct() {
        parent::__construct();
    }

    /**
     * 在线账户余额充值
     */
    public function recharge() {
        $rechargeSn = $this->input->get_post("recharge_sn");
        $price = $this->input->get_post("price");
        $redirectHash = $this->input->get_post("redirectHash");
        $this->load->library('jsapicall');
        $clientIp = $this->input->ip_address();
        $openid = getOpenid();
        $data = $this->jsapicall->create_pay_request($rechargeSn, $openid, floatval($price) * 100, $clientIp);
        $data["order_sn"] = $rechargeSn;
        $data["signPackage"] = $this->getSignPackage();
        $data["redirectHash"] = $redirectHash;
        $this->load->view("wxpay", $data);
    }


    /*
     * 订单支付
     */

    public function go() {
        $orderId = $this->input->get_post("orderId");
        $out_trade_no = $this->input->get_post("orderSn");
        $total_fee = $this->input->get_post("total_fee");
        $this->load->library('jsapicall');
        $clientIp = $this->input->ip_address();
        $this->load->helper('cookie');
        $openid = get_cookie("openid");
        $data = $this->jsapicall->create_pay_request($out_trade_no, $openid, floatval($total_fee) * 100, $clientIp);
        $data["orderSn"] = $out_trade_no;
        $data["type"] = 2;
        $data["orderId"] = $orderId;
        $this->load->view("pay/wxpay", $data);
    }

}
