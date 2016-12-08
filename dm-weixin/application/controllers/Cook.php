<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * 大厨
 */
class Cook extends MY_Controller {

	public function __construct() {
		parent::__construct();
		$this->load->model('cook_model');
	}

	/**
	 * 查询大厨列表
	 */
	public function lists() {
		echo json_encode($this->cook_model->findList(
			$this->getAccessToken(false),
			$this->getMarketId(),
			1,
			100
			));
	}

	/**
	 * 查询大厨信息
	 */
	public function info() {
		echo json_encode($this->cook_model->findOne(
			$this->input->get('cookId')
			));
	}

}