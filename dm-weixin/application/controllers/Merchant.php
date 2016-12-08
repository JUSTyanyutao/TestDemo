<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Controller - 商户
 */
class Merchant extends MY_Controller {

	public function __construct() {
		parent::__construct();
		$this->load->model('merchant_model');
	}

	/**
	 * 查询商户信息
	 */
	public function detail() {
		echo json_encode($this->merchant_model->detail(
			$this->getAccessToken(false),
			$this->input->get('merchantId')
			));
	}

	/**
	 * 加载商户商品信息
	 */
	public function goods() {
		// 查询店铺信息
		$merchant = $this->merchant_model->detail(
			$this->getAccessToken(false), 
			$this->input->get('merchantId'));

		// 商品信息
		$goods = array();
		if (is_array($merchant['result']['new_lists'])) {
            $goods = array_merge($goods, $merchant['result']['new_lists']);  
        }
        if (is_array($merchant['result']['special_lists'])) {
            $goods = array_merge($goods, $merchant['result']['special_lists']);
        }

        foreach ($goods as $key => $item) {
            $goods[$key]['thumb']           = $item['pic'];
            $goods[$key]['shop_cart_count'] = 0;
            $goods[$key]['id']              = $item['goods_id'];
            $goods[$key]['name']            = $item['goods_name'];
            $goods[$key]['merchant_name']   = $merchant['result']['name'];
        }

        echo json_encode(array('result' => array('data' => $goods)));
	}

	/**
	 * 历史商家
	 */
	public function histories() {
		echo json_encode($this->merchant_model->histories(
				$this->getAccessToken(),
				$this->input->get('market_id'),
				$this->input->get('page'),
				$this->input->get('pageSize')
			));
	}

	/**
	 * 搜索
	 */
	public function search() {
		echo json_encode($this->merchant_model->searchByName(
				$this->getMarketId(),
				$this->input->get('name'),
				1,
				25
			));
	}

}