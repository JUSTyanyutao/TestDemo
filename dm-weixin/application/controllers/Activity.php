<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Controller - 活动
 */
class Activity extends MY_Controller {

	public function __construct() {
		parent::__construct();
		$this->load->model('activity_model');
	}

	/**
	 * 活动列表
	 */
	public function lists() {
		echo json_encode($this->activity_model->getActivities(1, 100));
	}

	/**
	 * 活动详情
	 */
	public function detail($id) {
		echo json_encode($this->activity_model->getActivityDetail(
			$id,
			1,
			100
			));
	}
}
