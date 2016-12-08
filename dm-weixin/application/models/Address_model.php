<?php

defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model － 地址
 */
class Address_model extends CI_Model {

	public function __construct() {
        parent::__construct();
        $this->load->library("clientapi");
    }

    /**
     * 查询配送地址
     *
     * @param  string  $accessToken 接口调用凭证
     * @param  integer $page        页码
     * @param  integer $pageSize    每页数据量
     * @return 派送地址信息
     */
    public function getAddressList($accessToken = '', $page = 1, $pageSize = 15) {
    	$url = BASE_API_URL . '/member/addresses';
        $param = array(
			'access_token' => $accessToken,
			'page'         => $page,
			'page_size'    => $pageSize
        	);
        return $this->clientapi->get($url, $param);
    }


    /**
     * 查询收货地址信息
     *
     * @param  string  $accessToken 接口调用凭证
     * @param  integer $addressId   地址编号
     * @return 收货地址信息
     */
    public function get($accessToken = '', $addressId = 0) {
        $url = BASE_API_URL . '/member/address/' . $addressId . '?access_token='.$accessToken;
        return $this->clientapi->get($url);
    }

    /**
     * 保存收货地址
     *
     * @param  string  $accessToken   接口调用凭证
     * @param  string  $id            编号
     * @param  string  $consignee     收货人
     * @param  string  $mobile        手机号码
     * @param  integer $provinceId    省份编号
     * @param  integer $cityId        城市编号
     * @param  integer $districtId    区编号
     * @param  string  $streetAddress 街道地址
     * @param  boolean $isDefault     是否默认地址
     * @param  string  $latitude      纬度
     * @param  string  $longitude     经度
     * @return 保存结果
     */
    public function save($accessToken = '', $id = '', $consignee = '', $mobile = '', $provinceId = 0,
        $cityId = 0, $districtId = 0, $streetAddress = '', $isDefault = false, $latitude = '', $longitude = '') {

        $url = BASE_API_URL . '/member/address?access_token=' . $accessToken;
        $param = array(
            'id' => $id,
            'consignee' => $consignee,
            'mobile' => $mobile,
            'province_id' => $provinceId,
            'city_id' => $cityId,
            'district_id' => $districtId,
            'street_address' => $streetAddress,
            'is_default' => $isDefault,
            'latitude' => $latitude,
            'longitude' => $longitude
            );
        return $this->clientapi->post($url, $param);
    }

    /**
     * 删除收货地址
     *
     * @param  string  $accessToken 接口调用凭证
     * @param  integer $addressId   收货地址编号
     * @return 删除结果
     */
    public function delete($accessToken = '', $addressId = 0) {
        $url = BASE_API_URL . '/member/address/delete/' . $addressId .'?access_token=' .$accessToken;
        return $this->clientapi->post($url);
    }

    /**
     * 设置默认地址
     *
     * @param string  $accessToken 接口调用凭证
     * @param integer $addressId   收货地址编号
     * @return 设置结果
     */
    public function setDefault($accessToken = '', $addressId = 0) {
        $url = BASE_API_URL . '/member/address/default/' . $addressId . '?access_token=' . $accessToken;
        return $this->clientapi->get($url);
    }

    /**
     * 获取省列表
     *
     * @return 省信息
     */
    public function getProvinces() {
        $url = BASE_API_URL . '/area/province';
        return $this->clientapi->get($url);
    }

    /**
     * 获取开放省列表
     *
     * @return 省信息
     */
    public function getOpenProvinces() {
        $url = BASE_API_URL . '/area/province/open';
        return $this->clientapi->get($url);
    }

    /**
     * 加载城市信息
     *
     * @param  integer $provinceId 省编号
     * @return 城市信息
     */
    public function getCities($provinceId = 0) {
        $url = BASE_API_URL . '/area/city/' . $provinceId;
        return $this->clientapi->get($url);
    }

    /**
     * 加载开放城市信息
     *
     * @param  integer $provinceId 省编号
     * @return 城市信息
     */
    public function getOpenCities($provinceId = 0) {
        $url = BASE_API_URL . '/area/city/open/' . $provinceId;
        return $this->clientapi->get($url);
    }

    /**
     * 加载区信息
     *
     * @param  integer $cityId 城市编号
     * @return 区信息
     */
    public function getDistricts($cityId = 0) {
        $url = BASE_API_URL . '/area/country/' . $cityId;
        return $this->clientapi->get($url);
    }

    /**
     * 加载开放区信息
     *
     * @param  integer $cityId 城市编号
     * @return 区信息
     */
    public function getOpenDistricts($cityId = 0) {
        $url = BASE_API_URL . '/area/country/open/' . $cityId;
        return $this->clientapi->get($url);
    }


    /**
     * 查询默认收货地址
     *
     * @param  string $accessToken 接口调用凭证
     * @return 默认收货地址
     */
    public function getDefault($accessToken = '') {
        $url = BASE_API_URL . '/member/address/default';
        $param = array(
            'access_token' => $accessToken
            );
        return $this->clientapi->get($url, $param);
    }




}
