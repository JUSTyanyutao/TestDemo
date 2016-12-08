<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Controller - 余额
 */
class Balance extends My_Controller {

	public function __construct() {
        parent::__construct();
		$this->load->model("order_model");
    }

    /** 
     * 获取收送方式
     */
    public function getDeliveryWays() {
    	echo json_encode($this->order_model->getDeliveryWays());
    }

    /**
     * 历史站点
     */
    public function stationHistory() {
        echo json_encode($this->order_model->stationHistory($this->getAccessToken()));
    }

    /**
     * 根据经纬度搜索柜子
     */
    public function getCabinets() {
        echo json_encode($this->order_model->getCabinetList(
                $this->input->get('longitude'),
                $this->input->get('latitude')
            ));
    }

    /**
     * 根绝名称搜索柜子
     */
    public function getCabinetsByName() {
        echo json_encode($this->order_model->searchCabinetByName(
                $this->input->get('address')
            ));
    }

    /**
     * 查询实际支付价格
     */
    public function getActualPrice() {
        echo json_encode($this->order_model->getActualPrice(
                $this->getAccessToken(),
                $this->input->post('couponId'),
                $this->input->post('deliveryWayId'),
                $this->input->post('list'),
                $this->input->post('marketId'),
                $this->input->post('usePoints'),
                $this->input->post('cookInfo')
            ));
    }

    /**
     * 下单
     */
    public function createOrder() {
        echo json_encode($this->order_model->createOrder(
                $this->getAccessToken(),
                $this->input->post('couponId'),
                $this->input->post('deliveryWayId'),
                $this->input->post('deliveryDate'),
                $this->input->post('deliverySection'),
                $this->input->post('list'),
                $this->input->post('remark'),
                $this->input->post('stationId'),
                $this->input->post('usePoints'),
                $this->input->post('addressId'),
                $this->getMarketId(),
                $this->input->post('cookInfo')
            ));
    }

    /**
     * 校验优惠券使用
     */
    public function verifyCoupon() {
        echo json_encode($this->order_model->verifyCoupon(
                $this->getAccessToken(),
                $this->input->get('couponId'),
                $this->input->get('deliveryWayId'),
                $this->input->get('list')
            ));
    }

    /**
     * 根据名称搜索柜子
     */
    public function searchCabinetsByName() {
        echo json_encode($this->order_model->searchCabinetByName(
                $this->input->get('address'),
                $this->input->get('cityId'),
                $this->input->get('countyId'),
                $this->input->get('provinceId')
            ));
    }

    /**
     * 获取配送时间列表
     */
    public function getTimeList() {
        echo json_encode($this->order_model->getTimeList());
    }

}