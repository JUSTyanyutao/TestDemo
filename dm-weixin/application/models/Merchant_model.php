<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * model - 商户
 */
class Merchant_model extends CI_Model {

    public function __construct() {
        parent::__construct();
        $this->load->library("clientapi");
    }

    /**
     * 查询商户详情
     * 
     * @param  integer $merchantId 商户编号
     * @return mixed               商户详情信息
     */
    public function detail($accessToken = '', $merchantId = 0) {
        $url = BASE_API_URL . '/merchant/detail/' . $merchantId . '?access_token=' . $accessToken;
        return $this->clientapi->get($url);
    }

    /**
     * 根据名称搜索
     * 
     * @param  integer $marketId 市场编号
     * @param  string  $name     名称
     * @param  integer $page     页码
     * @param  integer $pageSize 每页数据量
     * @return 搜索结果
     */
    public function searchByName($marketId = 0, $name = '', $page = 1, $pageSize = 15) {
        $url = BASE_API_URL . '/merchant/search';
        $param = array(
                'market_id' => $marketId,
                'name'      => $name,
                'page'      => $page,
                'page_size' => $pageSize
            );
        return $this->clientapi->get($url, $param);
    }

    /**
     * 历史商家
     * 
     * @param  string  $accessToken 接口调用凭证
     * @param  integer $page        页码
     * @param  integer $pageSize    每页数据量
     * @return 历史商家
     */
    public function histories($accessToken = '',$marketId = 0, $page = 1, $pageSize = 15) {
        $url = BASE_API_URL . '/member/history_merchant';
        $param = array(
            'page'         => $page,
            'page_size'    => $pageSize,
            'access_token' => $accessToken,
            'market_id'    => $marketId
            );
        return $this->clientapi->get($url, $param);
    }

}