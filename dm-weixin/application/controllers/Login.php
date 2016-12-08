<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Login extends MY_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model("Api_model");
        $this->load->model('Wx_model');
    }

    /**
     * 登陆页面
     */
	public function index() {

        

		// 获取登录跳转链接
        $data['redirect_url'] = urldecode($this->input->get('redirect_url', ''));
        log_message('debug', '=== ='.$data['redirect_url']);
        if (empty($data['redirect_url'])) {
            // 若无跳转链接，跳转到个人中心
            $data['redirect_url'] = site_url('#personalCenter');
        }
        // 判断是否已经登录
        if (!empty(getAccessToken()) && time() < getExpireTime()) {
            // 已经登录，直接跳转
            header('Location: '.$data['redirect_url']);
            exit();
        }

        $refreshToken = getRefreshToken();
        if(!empty($refreshToken)){
            // refresh_token存在，置换新的access_token
            $res = $this->Api_model->refreshToken($refreshToken);

            if($res['err_code'] == 0){
                // 保存凭证信息
                setAccessToken($res['result']['access_token']);
                setExpireTime(time() + $res['result']['expire_time'] - 50);
                setRefreshToken($res['result']['refresh_token']);

                header('Location: '.$data['redirect_url']);
                exit();                
            }

        }

        // 获取openid信息
        $data['openid'] = isset($this->data['openid']) ? $this->data['openid'] : '';
        $this->loadOpenid();
        $this->load->view('login', $data);
	}

    /**
     * 查询注册协议
     */
    public function agreement() {
        return $this->Api_model->agreement();
    }


    public function loadOpenid(){
        if (isWeixin() && empty(getOpenid())) {
            // 微信浏览器，且openid为空
            $code = $this->input->get('code');
            if (empty($code)) {
                $url = sprintf(WX_AUTH_URL, APP_ID, urlencode('http://'.$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI']));
                header('Location: ' . $url);
                exit();
            }
            // 获取openid，并保存到cookie中
            $openid = $this->Wx_model->getOpenidByCode($code);
            setOpenid($openid);
        }
    }

    /**
     * 发送验证码
     */
	public function sendCode(){
		$this->load->model("member_model");
		exit(json_encode($this->member_model->captcha(
            $this->input->post('mobile'),
            $this->input->post('type')
            )
        ));
	}

    /**
     * 登陆验证
     */
	public function verify(){
        $mobile   = $this->input->post("mobile");
        $password = $this->input->post("password");

        // 接口登陆
		$res = $this->Api_model->login($mobile, $password, getOpenid());

		// 登陆成功以后,存储请求token信息
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
}
