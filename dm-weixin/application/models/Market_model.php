<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * model - 菜市场
 */
class Market_model extends CI_Model {

	public function __construct() {
        parent::__construct();
        $this->load->library("clientapi");
    }

    /**
     * 查询历史站点
     * 
     * @param  string  $accessToken 接口调用凭证
     * @param  integer $page        页码
     * @param  integer $pageSize    每页记录数
     * @return 历史站点信息
     */
    public function findHistories($accessToken = '', $page = 1, $pageSize = 15) {
    	$url = BASE_API_URL . '/market/history/list';
    	$params = array(
			'access_token' => $accessToken,
			'page'         => $page,
			'page_size'    => $pageSize
    		);
    	return $this->clientapi->get($url, $params);
    }

    /**
     * 根据经纬度查询菜场信息
     * 
     * @param  string $latitude  纬度
     * @param  string $longitude 经度
     * @return 菜场信息
     */
    public function findByLocation($latitude = '', $longitude = '') {
    	$url = BASE_API_URL . '/market/list';
    	$params = array(
    		'location' => $latitude.','.$longitude
    		);
    	return $this->clientapi->get($url, $params);
    }

    /**
     * 根据名称查询菜场
     * 
     * @param  string  $name       名称
     * @param  integer $cityId     城市
     * @param  integer $countyId   区
     * @param  integer $provinceId 省份
     * @return 
     */
    public function findByName($name = '', $cityId = 0, $countyId = 0, $provinceId = 0) {
    	$url = BASE_API_URL . '/market/search';
    	$params = array(
    		'address' => $name,
    		'city_id' => $cityId,
    		'county_id' => $countyId,
    		'province_id' => $provinceId
    		);
    	return $this->clientapi->get($url, $params);
    }
}