<?php

defined('BASEPATH') or exit('No direct script access allowed');

/**
 * Controller - 地址
 */
class Address extends My_Controller {

	public function __construct() {
        parent::__construct();
		$this->load->model("address_model");
    }

    /**
     * 查询收送地址
     */
    public function index() {
    	echo json_encode($this->address_model->getAddressList(
    			$this->getAccessToken(),
 				1,
 				100
    		));
    }

    /**
     * 查询默认收货地址
     */
    public function getDefault() {
        echo json_encode($this->address_model->getDefault(
                $this->getAccessToken()
            ));
    }

    /**
     * 查询收货地址信息
     */
    public function get() {
        echo json_encode($this->address_model->get(
            $this->getAccessToken(),
            $this->input->get('addressId')
            ));
    }

    /**
     * 保存收货地址
     */
    public function save() {
        echo json_encode($this->address_model->save(
            $this->getAccessToken(),
            $this->input->post('id'),
            $this->input->post('consignee'),
            $this->input->post('mobile'),
            $this->input->post('provinceId'),
            $this->input->post('cityId'),
            $this->input->post('districtId'),
            $this->input->post('streetAddress'),
            $this->input->post('isDefault'),
            $this->input->post('latitude'),
            $this->input->post('longitude')
            ));
    }

    /**
     * 删除收货地址
     */
    public function delete() {
        echo json_encode($this->address_model->delete(
            $this->getAccessToken(),
            $this->input->get('addressId')
            ));
    }

    /**
     * 设置默认收货地址
     */
    public function defaults() {
        echo json_encode($this->address_model->setDefault(
            $this->getAccessToken(),
            $this->input->get('addressId')
            ));
    }

    /**
     * 加载省信息
     */
    public function provinces() {
        echo json_encode($this->address_model->getProvinces());
    }

    /**
     * 加载开放省信息
     */
    public function openprovinces() {
        echo json_encode($this->address_model->getOpenProvinces());
    }

    /**
     * 加载城市信息
     */
    public function cities() {
        echo json_encode($this->address_model->getCities(
                $this->input->get('provinceId')
            ));
    }

    /**
     * 加载开放城市信息
     */
    public function opencities() {
        echo json_encode($this->address_model->getOpenCities(
                $this->input->get('provinceId')
            ));
    }


    /**
     * 加载区信息
     */
    public function districts() {
        echo json_encode($this->address_model->getDistricts(
                $this->input->get('cityId')
            ));
    }

    /**
     * 加载开放区信息
     */
    public function opendistricts() {
        echo json_encode($this->address_model->getOpenDistricts(
                $this->input->get('cityId')
            ));
    }





}
