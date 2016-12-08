<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * model - 活动
 */
class Consulting_model extends CI_model {

	public function __construct() {
		parent::__construct();
		$this->load->library("clientapi");
	}

	/**
	 * 获取活动咨询列表
	 * @param  integer $page     页码
	 * @param  integer $pageSize 每页商品数量
	 * @return 活动列表
	 */
	public function getConsultings($type = 1, $page = 1, $pageSize = 15) {
		$url   = BASE_API_URL . '/act_nutrition/list';
		$param = array(
  			'page'      => $page,
  			'page_size' => $pageSize,
        'type' => $type
			);
		return $this->clientapi->get($url, $param);
	}

	/**
	 * 获取活动详情
	 * @param  [type]  $activityId 活动编号
	 * @param  integer $page       页码
	 * @param  integer $pageSize   每页数量
	 * @return 活动详情
	 */
	public function getConsultingDetail($activityId, $page = 1, $pageSize = 15) {
		$url = BASE_API_URL . '/act_nutrition/detail/' . $activityId;
		$param = array(
			'page'      => $page,
			'page_size' => $pageSize
			);
		return $this->clientapi->get($url, $param);
	}

}
