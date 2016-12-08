<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * model - 商品
 */
class Goods_model extends CI_Model {

	public function __construct() {
        parent::__construct();
        $this->load->library("clientapi");
    }

    /**
     * 查询商品的分类信息
     * 
     * @return 商品分类信息
     */
    public function getGoodsCategory() {
		$url = BASE_API_URL . '/goods/categories';
        return $this->clientapi->get($url);
    }

    /**
     * 根绝名称搜索商品信息
     *
     * @param  integer $marketId 菜场编号
     * @param  string  $name     商品名称
     * @param  integer $page     页码
     * @param  integer $pageSize 每页数据量
     * @return 搜索结果
     */
    public function searchGoodsByName($marketId = 0, $name = '', $page = 1, $pageSize = 15) {
        $url = BASE_API_URL . '/goods/search';
        $param = array(
            'market_id' => $marketId,
            'name'      => $name,
            'page'      => $page,
            'page_size' => $pageSize
            );
        return $this->clientapi->get($url, $param);
    }

    /**
     * 查询限时抢购商品
     * 
     * @param  integer $page     页码
     * @param  integer $pageSize 每页纪录数
     * @return 限时抢购商品
     */
    public function getFlashSaleGoods($page = 1, $pageSize = 15) {
        $url = BASE_API_URL.'/goods/flash_sale';
        $param = array(
            'page'        => $page,
            'page_size'   => $pageSize
            );
        return $this->clientapi->get($url, $param);
    }

    /**
     * 查询特价商品
     *
     * @param  integer marketId  市场编号
     * @param  integer $page     页码
     * @param  integer $pageSize 纪录数
     * @return 特价商品
     */
    public function getSpecialGoods($marketId, $page = 1, $pageSize = 15) {
        $url = BASE_API_URL.'/goods/specials';
        $param = array(
            'page'        => $page,
            'page_size'   => $pageSize,
            'market_id'   => $marketId
            );
        return $this->clientapi->get($url, $param);
    }

    /**
     * 查询热门商品
     * 
     * @param  integer $page     页码
     * @param  integer $pageSize 纪录数
     * @return 热门商品
     */
    public function getHotGoods($page = 1, $pageSize = 15) {
        $url = BASE_API_URL.'/goods/hot';
        $param = array(
            'page'        => $page,
            'page_size'   => $pageSize
            );
        return $this->clientapi->get($url, $param);
    }

    /**
     * 查询可能喜欢的商品
     * 
     * @return 可能喜欢的商品
     */
    public function getRecommendGoods() {
        $url = BASE_API_URL.'/goods/recommend';
        return $this->clientapi->get($url);
    }

    /**
     * 商品列表信息
     *
     * @param  string  $accessToken 接口调用凭证
     * @param  integer $categoryId  分类编号
     * @param  integer $marketId    菜场编号
     * @param  integer $merchantId  商户编号
     * @param  integer $page        页码，默认第一页
     * @param  integer $pageSize    每页数据量
     * @param  string  $sort        排序
     * @return 商品列表信息
     */
    public function getGoods($accessToken = '', $categoryId = 1, $marketId = 0, $merchantId = 0, $page = 1, $pageSize = 15, $sort = 'ASC') {
    	$url = BASE_API_URL.'/goods/list';
    	$param = array(
            'access_token' => $accessToken,
            'category_id'  => $categoryId,
            'market_id'    => $marketId,
            'merchant_id'   => $merchantId,
            'page'         => $page,
            'page_size'    => $pageSize,
            'sale_sort'    => $sort
            );
    	return $this->clientapi->get($url, $param);
    }


    /**
     * 查询商品详情
     *
     * @param  string  $accessToken 接口调用凭证
     * @param  integer $goodsId     商品编号
     * @return 商品详情
     */
    public function detail($accessToken = '', $goodsId = 0) {
        $url = BASE_API_URL.'/goods/detail/'.$goodsId.'?access_token='.$accessToken;
        return $this->clientapi->get($url);
    }


}

