<?php
/**
 * Description of MY_Controller
 * 继承CI控制器,实现一些公用的处理
 * @author ranfi
 */
class MY_Controller extends CI_Controller {

    protected $menu = "main";
    protected $data = array();

    public function __construct() {
        parent::__construct();
        $this->load->model("Api_model");
        $this->data["openid"]     = $this->getOpenid();
        if(isWeixin()){
            $this->data["signPackage"]   = $this->getSignPackage();
        }
    }

    public function getSignPackage(){
        $this->load->library("jssdk");
        return $this->jssdk->getSignPackage();
    }

    /**
     * 获取微信用户的openid
     *
     * @return [type] [description]
     */
    public function getOpenid() {
        return $this->session->userdata('openid');
    }

    /**
     * 获取菜场编号
     *
     * @return integer 菜场编号
     */
    public function getMarketId() {
        $marketId = getMarketId();

        if (empty($marketId)) {
            exit(json_encode(array('err_code' => 100001, 'err_msg' => '请先择一个默认的菜场')));
        }

        return $marketId;
    }

    /**
     * 获取API访问的token令牌
     *
     * @return token令牌
     */
    public function getAccessToken($isRedirect = true){

        // 查询凭证信息
        $accessToken  = getAccessToken();
        $refreshToken = getRefreshToken();
        $expireTime   = getExpireTime();
        $time         = time();

        // 若access_token存在，则返回
        if(!empty($accessToken) && $expireTime >= $time){
            return $accessToken;
        }

        // 判断refresh_token是否存在，不存在则跳转登陆页面
        if(empty($refreshToken)){
            if (!$isRedirect)  return "";
            redirectLogin();
        }


        // refresh_token存在，置换新的access_token
        $res = $this->Api_model->refreshToken($refreshToken);


        if($res['err_code'] == 0){
            // 保存凭证信息
            setAccessToken($res['result']['access_token']);
            setExpireTime(time() + $res['result']['expire_time'] - 50);
            setRefreshToken($res['result']['refresh_token']);

            return $res['result']['access_token'];
        }

        if (!$isRedirect)  return "";

        redirectLogin();
    }


    protected function getRedirectLoginUrl() {
        $url = "";
        if(isWeixin()){
            $this->url = sprintf(WX_AUTH_URL, APP_ID, urlencode(site_url("/") . "login?redirect_url=".urlencode($_SERVER['HTTP_REQUEST_HASH'])));
        } else {
            $this->url = site_url("/")."login?redirect_url=".urlencode($_SERVER['HTTP_REQUEST_HASH']);
        }
        return $this->url;
    }

}
