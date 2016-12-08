<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * 市场
 */
class Market extends MY_Controller {

 	public function __construct() {
        parent::__construct();
        $this->load->model("market_model");
    }

    /**
     * 历史菜场
     */
    public function histories() {
    	echo json_encode($this->market_model->findHistories(
    		$this->getAccessToken(false),
    		1,
    		100
    		));
    }

    /**
     * 根据经纬度搜索菜场
     */
    public function location() {
    	echo json_encode($this->market_model->findByLocation(
    		$this->input->get('latitude'),
    		$this->input->get('longitude')
    		));
    }

    /**
     * 根据名称搜索经纬度
     */
    public function name() {
    	echo json_encode($this->market_model->findByName(
    		$this->input->get('name'),
    		$this->input->get('city_id'),
    		$this->input->get('county_id'),
    		$this->input->get('province_id')
    		));
    }

    /**
     * 选定菜场
     */
    public function bind() {
        setMarketId($this->input->get('market_id'));
        echo '{}';
    }

}