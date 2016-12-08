<?php

defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * model - 订单
 */
class Order_model extends CI_Model {

	public function __construct() {
        parent::__construct();
        $this->load->library("clientapi");
    }

    /**
     * 校验优惠券
     * 
     * @param  string  $accessToken   接口调用凭证
     * @param  integer $couponId      优惠券编号
     * @param  integer $deliveryWayId 派送方式
     * @param  string  $goods         商品信息
     * @return 校验结果
     */
    public function verifyCoupon($accessToken = '', $couponId = 0, $deliveryWayId = 0, $goods = '') {
    	$url = BASE_API_URL . '/order/check_coupon';
        $param = array(
            'access_token'    => $accessToken,
			'coupon_id'       => $couponId,
			'delivery_way_id' => $deliveryWayId,
			'list'            => $goods
        	);
        return $this->clientapi->get($url, $param);
    }

    /**
     * 查询用户下单的历史站点
     * 
     * @param  string $accessToken 接口调用凭证
     * @return 历史站点信息
     */
    public function stationHistory($accessToken = '') {
        $url = BASE_API_URL . '/member/station?access_token='.$accessToken;
        return $this->clientapi->get($url);
    }

    /**
     * 创建订单
     * 
     * @param  string  $accessToken     接口调用凭证
     * @param  integer $couponId        优惠券编号
     * @param  integer $deliveryWayId   派送方式编号
     * @param  string  $deliveryDate    派送日起
     * @param  string  $deliverySection 派送时间段
     * @param  string  $list            商品信息
     * @param  string  $remark          备注
     * @param  integer $stationId       站点编号
     * @param  integer $usePoints       是用积分
     * @param  integer $addressId       地址编号
     * @param  integer $marketId        市场编号
     * @param  string  $cookInfo        厨师信息
     * @return 下单结果
     */
    public function createOrder($accessToken = '', $couponId = 0, $deliveryWayId = 0, $deliveryDate = '', 
                                $deliverySection = '', $list = '', $remark = '', $stationId = 0, $usePoints = 0,
                                $addressId = 0, $marketId = 0, $cookInfo = '') {
        $url = BASE_API_URL . '/order/save?access_token='.$accessToken;
        $param = array(
            'address_id'       => $addressId,
            'coupon_id'        => $couponId,
            'delivery_way_id'  => $deliveryWayId,
            'delivery_date'    => $deliveryDate,
            'delivery_section' => $deliverySection,
            'goods_list'       => $list,
            'remark'           => $remark,
            'station_id'       => $stationId,
            'use_points'       => $usePoints,
            'market_id'        => $marketId,
            'cook_info'        => $cookInfo
            );  
        return $this->clientapi->post($url, $param);
    }

    /**
     * 获取实际价格
     * 
     * @param  string  $accessToken   接口调用凭证
     * @param  integer $couponId      优惠券编号
     * @param  integer $deliveryWayId 派送方式
     * @param  string  $goods         商品信息
     * @param  integer $usePoints     是用积分
     * @param  string  $cookInfo      大厨信息
     * @return 实际价格
     */
    public function getActualPrice($accessToken = '', $couponId = 0, $deliveryWayId = 0, $goods = '',  $marketId = 0, $usePoints = 0, $cookInfo = '') {
    	$url = BASE_API_URL . '/order/get_pay_money';
        $param = array(
            'access_token'    => $accessToken,
			'coupon_id'       => $couponId,
			'delivery_way_id' => $deliveryWayId,
			'goods_list'      => $goods,
            'market_id'       => $marketId,
            'use_points'      => $usePoints,
            'cook_info'       => $cookInfo
        	);
        return $this->clientapi->get($url, $param);
    }

    /**
     * 获取派送方式
     * 
     * @return 派送方式
     */
    public function getDeliveryWays() {
    	$url = BASE_API_URL.'/order/delivery';
     	return $this->clientapi->get($url);
    }

    /**
     * 根据经纬度获取周边的柜子
     * 
     * @param  string $longitude 经度
     * @param  string $latitude  纬度
     * @return 搜索结果
     */
    public function getCabinetList($longitude = '', $latitude = '') {
    	$url = BASE_API_URL.'/order/cabinet_list';
    	$param = array(
    		'location' => $latitude.','.$longitude
    		);
		return $this->clientapi->get($url, $param);
    }

    /**
     * 根绝名称搜索查询柜子
     * 
     * @param  string  $address    地址
     * @return 柜子信息
     */
    public function searchCabinetByName($address = '') {
    	$url = BASE_API_URL.'/order/search_cabinet';      
    	$param = array(
			'address' => $address
    		);
    	return $this->clientapi->get($url, $param);
    }

    /**
     * 获取配送时间列表
     * 
     * @return 配送时间列表
     */
    public function getTimeList() {
        $url = BASE_API_URL.'/order/get_time_list';

        return $this->clientapi->get($url);
    }


    /**
     *  分享优惠券
     */
    public function shareCoupon($accessToken ="",$orderId=0) {
        $url = BASE_API_URL . '/order/share_coupon?access_token='.$accessToken.'&order_id='.$orderId;
        return $this->clientapi->get($url);
    }
    /**
     *  领取优惠券
     */
    public function receiveCoupon($shareId =0,$mobile="") {
        $url = BASE_API_URL . '/order/receive_coupon?share_id='.$shareId.'&mobile='.$mobile;
        return $this->clientapi->get($url);
    }
}