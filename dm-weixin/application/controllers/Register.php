<?php

defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Created by IntelliJ IDEA.
 * User: whan
 * Date: 5/6/16
 * Time: 10:47 PM
 */
class Register extends MY_Controller
{

    public function __construct() {
        parent::__construct();
        $this->load->model("Api_model");
//        $this->load->model('Wx_model');
    }

    /**
     * 注册第一步
     */
    public function step1() {
        $this->load->view('register/step1', $this->data);
    }

    /**
     * 注册第一步
     */
    public function step1Action() {
        echo json_encode($this->Api_model->registerStep1(
            $this->input->post('mobile'),
            $this->input->post('captcha')
        ));
    }

    /**
     * 注册第二步
     */
    public function step2() {
        $this->data['token'] = $this->input->get('token');
        $this->load->view('register/step2', $this->data);
    }

    /**
     * 注册第二步
     */
    public function step2Action() {
        $res = $this->Api_model->registerStep2(
            $this->input->post('password'),
            $this->input->post('repeat_password'),
            $this->input->post('token'),
            getOpenid()
        );

        // 注册成功以后,存储请求token信息
        if ($res["err_code"] == 0) {
            $data = $res["result"];

            setAccessToken($data["access_token"]);
            setRefreshToken($data["refresh_token"]);
            setExpireTime(time() + $data["expire_time"] - 50);

            // set_cookie("api_access_token", $data["access_token"], COOKIE_EXPIRED_TIME);
            // set_cookie("api_expire_time", time() + $data["expire_time"], COOKIE_EXPIRED_TIME);
            // set_cookie("api_refresh_token", $data["refresh_token"], COOKIE_EXPIRED_TIME);
        }
        echo json_encode($res);
        exit;
    }

    /**
     * 注册协议
     */
    public function agreement() {
        // $this->data['result'] = $this->Api_model->agreement();

        $this->load->view('register/agreement', $this->data);
    }

}