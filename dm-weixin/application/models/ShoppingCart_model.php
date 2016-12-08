<?php

defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * model - 购物车
 */
class ShoppingCart_model extends CI_Model {

	public function __construct() {
        parent::__construct();
        $this->load->library("clientapi");
    }

    /**
     * 查询购车中的商品信息
     * 
     * @param  string  $accessToken 接口调用凭证
     * @param  integer $page        页码
     * @param  integer $pageSize    每页数据量
     * @return 购物车中的商品信息
     */
    public function getShoppingCarts($accessToken = '', $page = 1, $pageSize = 15 ,$marketId=0) {
 		$url = BASE_API_URL . '/shopping_carts';
        $param = array(
			'access_token' => $accessToken, 
			'page'         => $page,
			'market_id'    => $marketId,
			'page_size'    => $pageSize
        	);
        return $this->clientapi->get($url, $param);
    }

    /**
     * 购物车中的商品数量加指定数量
     *
     * @param  string  $accessToken 接口调用凭证
     * @param  integer $itemId      编号
     * @param  integer $quantity    数量
     * @param  string  $startTime   开始时间
     * @param  integer $type        类型：1商品 2大厨
     * @return 结果
     */
    public function increase($accessToken = '', $itemId = 0, $quantity = 1, $startTime = '', $type = 1) {
        $url = BASE_API_URL . '/shopping_cart/increase?access_token='.$accessToken;
        $param = array(
            'item_id'    => $itemId,
            'quantity'   => $quantity,
            'type'       => $type,
            'start_time' => $startTime
            );
        return $this->clientapi->post($url, $param);
    }

    /**
     * 购物车中的商品数量减指定数量
     *
     * @param  string  $accessToken 接口调用凭证
     * @param  integer $itemId      编号
     * @param  integer $quantity    数量
     * @param  integer $type        类型：1商品 2大厨
     * @return 结果
     */
    public function decrease($accessToken = '', $itemId = 0, $quantity = 1, $type = 1) {
        $url = BASE_API_URL . '/shopping_cart/decrease?access_token='.$accessToken;
        $param = array(
            'item_id' => $itemId,
            'quantity' => $quantity,
            'type' => $type
            );
        return $this->clientapi->post($url, $param);
    }


    /**
     * 更新购物车中的商品数量
     * 
     * @param  string  $accessToken 接口调用凭证
     * @param  integer $goodsId     商品编号
     * @param  integer $quantity    数量
     * @return 更新结果
     */
    public function updateGoodsQuantity($accessToken = '', $goodsId = 0, $quantity = 1) {
    	$url = BASE_API_URL . '/shopping_cart?access_token='.$accessToken;
        $param = array(
			'goods_id' => $goodsId,
			'quantity' => $quantity
        	);
        return $this->clientapi->post($url, $param);
    }

    /**
     * 删除购物车中的商品
     * 
     * @param  string  $accessToken 接口调用凭证
     * @param  integer $ids         编号数组
     * @return 删除结果
     */
    public function delete($accessToken = '', $ids = array()) {
    	$url = BASE_API_URL . '/shopping_cart/delete?access_token='.$accessToken;
        $param = array(
			'ids' => $ids
        	);
        return $this->clientapi->post($url, $param);
    }

}