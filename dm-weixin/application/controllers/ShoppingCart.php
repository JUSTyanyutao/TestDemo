<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Controller - 购物车
 */
class ShoppingCart extends My_Controller {

	public function __construct() {
        parent::__construct();
		$this->load->model("shoppingCart_model");
    }

    /**
     * 购物车
     */
    public function index() {
        $accessToken = $this->getAccessToken(false);
        if(empty($accessToken)){
            echo json_encode(array("loginSuccess" => 0));
            exit;
        } else {
            $marketId = $this->input->get('marketId');
            echo json_encode($this->shoppingCart_model->getShoppingCarts($this->getAccessToken(), 1, 100,$marketId));
            exit;
        }
    	
    }

    /**
     * 更新购物车中的商品数量
     */
    public function updateGoodsQuantity() {
    	echo json_encode($this->shoppingCart_model->updateGoodsQuantity(
    			$this->getAccessToken(),
    			$this->input->post('goodsId'),
    			$this->input->post('quantity')
    		));
    }

    /**
     * 购物车中的商品数量减1
     */
    public function decrease() {
        echo json_encode($this->shoppingCart_model->decrease(
                $this->getAccessToken(), 
                $this->input->post('itemId'),
                $this->input->post('quantity'),
                $this->input->post('type')
            ));
    }

    /**
     * 购物车中的商品数量加1
     */
    public function increase() {
        echo json_encode($this->shoppingCart_model->increase(
                $this->getAccessToken(), 
                $this->input->post('itemId'),
                $this->input->post('quantity'),
                $this->input->post('startTime'),
                $this->input->post('type')
            ));
    }

    /**
     * 删除购物车中的商品
     */
    public function delete() {
    	echo json_encode($this->shoppingCart_model->delete(
    			$this->getAccessToken(),
    			array($this->input->post('id'))
    		));
    }

}
