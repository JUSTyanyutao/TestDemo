<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Controller - 商城
 */
class Mall extends MY_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model('merchant_model');
        $this->load->model("goods_model");
    }

    /**
     * 历史商户
     */
    public function hisitories() {
        // 获取5条历史商户信息
        $merchants = $this->merchant_model->histories($this->getAccessToken(), 1, 5);

        $goods = array();
        foreach ($merchants['result']['data'] as $key => $data) {
            $detail = $this->merchant_model->detail($this->getAccessToken(), $data['id']);
            $merchantGoods = array();
            if (is_array($detail['result']['new_lists'])) {
                $merchantGoods = array_merge($merchantGoods, $detail['result']['new_lists']);  
            }
            if (is_array($detail['result']['special_lists'])) {
                $merchantGoods = array_merge($merchantGoods, $detail['result']['special_lists']);
            }

            foreach ($merchantGoods as $key => $item) {
                $merchantGoods[$key]['merchant_name'] = $detail['result']['name'];
            }

            $goods = array_merge($goods, $merchantGoods);
        }   

        foreach ($goods as $key => $item) {
            $goods[$key]['thumb']           = $item['pic'];
            $goods[$key]['shop_cart_count'] = 0;
            $goods[$key]['id']              = $item['goods_id'];
            $goods[$key]['name']            = $item['goods_name'];
        }

        log_message('debug', json_encode($goods, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT | JSON_UNESCAPED_SLASHES));

        echo json_encode(array('result' => array('data' => $goods)));
    }
    
    /**
     * 查询商品的分类信息
     */
    public function goodsCategory() {
    	echo json_encode($this->goods_model->getGoodsCategory());
    }

    /** 
     * 根绝名称搜索商品信息
     */
    public function searchByName() {
        echo json_encode($this->goods_model->searchGoodsByName(
                $this->getMarketId(),
                $this->input->get('name'),
                1,
                25
            ));
    }

    /**
     * 查询商品列表信息
     */
    public function goods() {
    	echo json_encode($this->goods_model->getGoods(
                $this->getAccessToken(false),
    			$this->input->get('category_id'),
                $this->getMarketId(),
                $this->input->get('merchant_id'),
    			1,
    			100,
    			$this->input->get('sort')
    		));
    }

    /**
     * 查询商品详情
     */
    public function detail() {
        echo json_encode($this->goods_model->detail(
            $this->getAccessToken(false),
            $this->input->get('goods_id')
            ));
    }

    /**
     * 查询限时抢购商品
     */
    public function flashSale() {
        echo json_encode($this->goods_model->getFlashSaleGoods(1, 100));
    }

    /**
     * 查询特价商品
     */
    public function specials() {
        echo json_encode($this->goods_model->getSpecialGoods($this->getMarketId(), 1, 100));
    }

    /**
     * 查询热门商品
     */
    public function hot() {
        echo json_encode($this->goods_model->getHotGoods(1, 100));
    }

    /**
     * 查询可能喜欢的商品
     */
    public function recommend() {
        echo json_encode($this->goods_model->getRecommendGoods());
    }

    /**
     * 索引页
     */
	public function index() {
        $this->load->view('mall',$this->data);
	}

}
