<?php

defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * model - 大厨
 */
class Cook_model extends CI_Model {

	public function __construct() {
		parent::__construct();
		$this->load->library('clientapi');
	}

	/**
	 * 查询大厨列表
	 * 
	 * @param  string  $accessToken 接口调用凭证
	 * @param  integer $marketId    市场编号
	 * @param  integer $page        页码
	 * @param  integer $pageSize    每页数据量
	 * @return 大厨信息
	 */
	public function findList($accessToken = '', $marketId = 0, $page = 1, $pageSize = 15) {
		$url = BASE_API_URL . '/cooks';
		$params = array(
			'access_token' => $accessToken,
			'market_id'    => $marketId,
			'page'         => $page,
			'page_size'    => $pageSize
			);
		return $this->clientapi->get($url, $params);
	}

	/**
	 * 查询大厨信息
	 * 
	 * @param  integer $cookId 大厨编号
	 * @return 大厨信息
	 */
	public function findOne($cookId = 0) {
		$url = BASE_API_URL . '/cook/' .$cookId;
		return $this->clientapi->get($url);
	} 

}