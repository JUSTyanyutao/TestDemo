<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Api_model extends CI_Model {

    public function __construct() {
        parent::__construct();
        $this->load->library("clientapi");
    }

    /**
     * 签到获取积分
     *
     * @param  string $accessToken 接口调用凭证
     * @return 签到结果
     */
    public function signin($accessToken = '', $signin = 0) {
        $url = BASE_API_URL . '/member/signin?access_token=' . $accessToken . '&is_signin=' . $signin;
        return $this->clientapi->post($url);
    }

    /**
     * 查询积分明细
     *
     * @param  string $accessToken 接口调用凭证
     * @return 会员积分明细
     */
    public function points($accessToken = '') {
        $url = BASE_API_URL . '/member/points/list?access_token=' . $accessToken;
        return $this->clientapi->get($url);
    }

    /**
     * 获取积分规则
     * @return 积分规则
     */
    public function getPointRules() {
        $url = BASE_API_URL . '/member/points/rule';
        return $this->clientapi->get($url);
    }

    /**
     * 获取积分兑换比率
     * @return 积分兑换比率
     */
    public function getPointRatio() {
        $url = BASE_API_URL . '/member/points/ratio';
        return $this->clientapi->get($url);
    }

    /**
    *
    * 用户会员相关
    */
    public function login($account, $password, $openid) {
        $url = BASE_API_URL . "/member/login";
        return $this->clientapi->post(
            $url,
            array(
                "account" => $account,
                "password" => $password,
                "wx_openid" => $openid
            )
        );
    }

    /**
     * 用户注册第一步
     *
     * @param string $mobile 手机号码
     * @param string $captcha 验证码
     * @return mixed 注册结果
     */
    public function registerStep1($mobile = '', $captcha = '') {
        $url = BASE_API_URL . '/member/register';
        $param = array(
            'account' => $mobile,
            'verification' => $captcha
        );
        return $this->clientapi->post($url, $param);
    }

    /**
     * 用户注册第二步
     *
     * @param string $password 密码
     * @param string $repeatPassword 重复密码
     * @param string $token 凭证
     * @return mixed 注册结果
     */
    public function registerStep2($password = '', $repeatPassword = '', $token = '', $openid = '') {
        $url = BASE_API_URL . '/member/register/password';
        $param = array(
            'password'      => $password,
            'repeat_passwd' => $repeatPassword,
            'token'         => $token,
            'wx_openid'     => $openid
        );
        return $this->clientapi->post($url, $param);
    }

    /**
     * 注册协议
     */
    public function agreement() {
        $url = BASE_API_URL . '/agreement/registration';
        return $this->clientapi->get($url);
    }

    /**
     * 查询个人收支纪录
     *
     * @param  string  $accessToken 接口调用协议
     * @param  integer $page        页码
     * @param  integer $pageSize    页码数量
     * @return 收支纪录
     */
    public function bill($accessToken = '', $page = 1, $pageSize = 15) {
        $url = BASE_API_URL . '/member/bill';
        $param = array(
            'access_token' => $accessToken,
            'page'         => $page,
            'page_size'    => $pageSize
            );
        return $this->clientapi->get($url, $param);
    }


    public function refundInfo($accessToken = ''){
        $url = BASE_API_URL . '/member/refund/list';
        $param = array(
            'access_token' => $accessToken
            );
        return $this->clientapi->get($url, $param);
    }

    public function refund($accessToken = '',$refundMoney){
        $url = BASE_API_URL . '/member/refund?access_token='.$accessToken."&refund_money=".$refundMoney;
        return $this->clientapi->post($url);
    }

    /**
     * 查询Faq
     */
    public function getFaqs() {
        $url = BASE_API_URL . '/faq/list';
        return $this->clientapi->get($url);
    }

    /**
     * 搜索热门关键字
     *
     * @return 热门关键字
     */
    public function hotKey() {
        $url = BASE_API_URL . '/goods/hot_key';
        return $this->clientapi->get($url);
    }

    /**
     * 搜索热门关键字
     * @return 热门关键字
     */
    public function hotKey2() {
        $url = BASE_API_URL . '/search/list';
        return $this->clientapi->get($url);
    }

    /**
     * 保存关键字
     */
    public function searchAdd($goodsId) {
        $url = BASE_API_URL.'/search/add/'.$goodsId;
        return $this->clientapi->post($url);
    }

    /**
     *
     * 置换api接口token令牌
     */
    public function refreshToken($refresh_token) {
        $url = BASE_API_URL . "/member/refresh_token";
        return $this->clientapi->get($url,array("refresh_token"=>$refresh_token));
    }


    public function getUserInfo($accessToken){
        $param = array("access_token" => $accessToken);
        return $this->clientapi->get(BASE_API_URL."/member",$param);
    }


    public function getRechargePrepaid($accessToken){
        $param = array("access_token" => $accessToken);
        return $this->clientapi->get(BASE_API_URL."/recharge/prepaid",$param);
    }

    public function getRechargeMoney($accessToken,$money){
        $param = array("access_token" => $accessToken,"money" => $money);
        return $this->clientapi->get(BASE_API_URL."/recharge/get_recharge",$param);
    }

    public function saveFeedback($accessToken,$content){
        $param = array("content"=>$content);
        return $this->clientapi->post(BASE_API_URL."/feedback?access_token=".$accessToken,$param);
    }


    public function getCouponList($access_token,$page_no,$page_size) {
        $url = BASE_API_URL . "/member/coupons";
        $param = array("access_token"=>$access_token,"page_no"=>$page_no,"page_size"=>$page_size);
        return $this->clientapi->get($url,$param);
    }


    public function getMainPageData($marketId = '') {
        $url = BASE_API_URL . "/home/main";
        $param = array(
                'market_id' => $marketId
            );
        return $this->clientapi->get($url, $param);
    }


    public function getMainPageDataNear($marketId = '') {
        $url = BASE_API_URL . "/home/main";
        $param = array(
                'market_id' => $marketId
            );
        return $this->clientapi->get($url, $param);
    }


    public function getOrderList($access_token,$order_status = NULL,$page,$page_size) {
        $url = BASE_API_URL . "/order/list";
        $param = array("access_token"=>$access_token,"order_status" => $order_status,"page"=>$page, "page_size"=>$page_size);
        return $this->clientapi->get($url,$param);
    }

    public function getOrderDetail($access_token,$order_id) {
        $url = BASE_API_URL . "/order/detail/".$order_id;
        $param = array("access_token"=>$access_token);
        return $this->clientapi->get($url,$param);
    }

    public function cancelOrder($access_token,$order_id,$cook_id,$merchant_id) {
        $url = BASE_API_URL . "/order/cancel/".$order_id."?access_token=".$access_token."&cook_id=".$cook_id."&merchant_id=".$merchant_id;
        return $this->clientapi->post($url);
    }

    public function reminderOrder($access_token,$order_id) {
        $url = BASE_API_URL . "/order/reminder/".$order_id."?access_token=".$access_token;
        return $this->clientapi->post($url);
    }

    /**
     * 分享优惠券
     * @param $access_token
     * @param $order_id
     * @return mixed
     */
    public function shareCoupon($access_token,$order_id) {
        $url = BASE_API_URL . "/order/share_coupon/".$order_id."?access_token=".$access_token;
        return $this->clientapi->post($url);
    }
    public function receiveOrder($access_token,$order_id) {
        $url = BASE_API_URL . "/order/finish/".$order_id."?access_token=".$access_token;
        return $this->clientapi->post($url);
    }



    public function deleteOrder($access_token,$order_id) {
        $url = BASE_API_URL . "/order/delete/".$order_id."?access_token=".$access_token;
        return $this->clientapi->post($url);
    }


    /****************工人相关接口**************/

    public function getWorkerOrderList($access_token,$delivery_status = NULL,$page,$page_size) {
        $url = BASE_API_URL . "/worker/order/list";
        $param = array("access_token"=>$access_token,"delivery_status" => $delivery_status,"page"=>$page,"page_size"=>$page_size);
        return $this->clientapi->get($url,$param);
    }


    public function acceptOrder($access_token,$order_id) {
        $url = BASE_API_URL . "/worker/order/accept/".$order_id."?access_token=".$access_token;
        return $this->clientapi->post($url);
    }

    public function deliveryOrder($access_token, $order_id, $bar_code) {
        $url = BASE_API_URL . "/worker/order/delivery/".$order_id."/".$bar_code."?access_token=".$access_token;
        return $this->clientapi->post($url);
    }

    public function uncheckDeliveryOrder($access_token, $bar_code) {
        $url = BASE_API_URL . "/worker/order/delivery/".$bar_code."?access_token=".$access_token;
        return $this->clientapi->post($url);
    }

    public function completeDeliveryOrder($access_token, $order_id) {
        $url = BASE_API_URL . "/worker/order/finish/".$order_id."?access_token=".$access_token;
        return $this->clientapi->post($url);
    }

    /****************工人相关接口**************/


    public function getOrderLogs($access_token,$order_id) {
        $url = BASE_API_URL . "/order/log/".$order_id;
        $param = array("access_token"=>$access_token);
        return $this->clientapi->get($url,$param);
    }



    /**
     * [addComment 添加商品评论]
     * @param  [type] $access_token [description]
     * @param  [type] $param        [description]
     * @return [type]               [description]
     */
    public function addComment($access_token,$param) {
        $url = BASE_API_URL . "/comment/add?access_token=" . $access_token;
        return $this->clientapi->post($url,$param);
    }

    /**
     * [addComment 添加大厨评论]
     * @param  [type] $access_token [description]
     * @param  [type] $param        [description]
     * @return [type]               [description]
     */
    public function addCookComment($access_token,$param) {
        $url = BASE_API_URL . "/comment/cook/add?access_token=" . $access_token;
        return $this->clientapi->post($url,$param);
    }


     /**
     * [getComments 查询商品的评论列表]
     * @param  [type] $access_token [description]
     * @param  [type] $param        [description]
     * @return [type]               [description]
     */
    public function getComments($goodsId, $page, $pageSize, $status = 1) {
        $url = BASE_API_URL . "/comment/list/".$goodsId;
        $param = array("page" => $page,"pageSize"=>$pageSize,"status"=>$status);
        return $this->clientapi->get($url,$param);
    }


    /**
     * [getLabels 查询标签]
     * @param  [type] $type [description]
     * @return [type]       [description]
     */
    public function getLabels($type){
        $url = BASE_API_URL . "/label/list";
        $param = array("status" => $type);
        return $this->clientapi->get($url,$param);
    }


    /**
     * [orderPackage 订单打包接口,给称重程序使用]
     * @param  [type] $orderId    [description]
     * @param  [type] $orderGoods [description]
     * @return [type]             [description]
     */
    public function orderPackage($orderId,$orderGoods){
        $url = BASE_API_URL . "/order/package/".$orderId;
        return $this->clientapi->post($url,$orderGoods);
    }

    /**
     * 查询大厨评论
     *
     * @param  integer $cookId   大厨编号
     * @param  integer $status   评论状态
     * @param  integer $page     页码
     * @param  integer $pageSize 每页个数
     * @return 评论
     */
    public function getCookComments($cookId = 0, $status = 1, $page = 1, $pageSize = 15) {
        $url = BASE_API_URL . '/comment/cook/list/' . $cookId;
        $param = array(
            'status'    => $status,
            'page'      => $page,
            'page_size' => $pageSize
            );
        return $this->clientapi->get($url, $param);
    }


    /**
     * 根据名称搜索商品信息、商家和菜谱数据
     *
     * @param  integer $marketId 菜场编号
     * @param  string  $keyword 搜索关键词
     * @param  integer  $searchType 搜索类型
     * @param  integer $page     页码
     * @param  integer $pageSize 每页数据量
     * @return 搜索结果
     */
    public function searchByName($marketId = 0, $keyword = '', $searchType=1, $page = 1, $pageSize = 15) {
        $url = BASE_API_URL . '/home/search/list';
        $param = array(
            'market_id' => $marketId,
            'search_type' => $searchType,
            'keyword'      => $keyword,
            'page'      => $page,
            'page_size' => $pageSize
            );
        return $this->clientapi->get($url, $param);
    }

}
