<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Controller - 活动
 */
class Consulting extends MY_Controller {

	public function __construct() {
		parent::__construct();
		$this->load->model('consulting_model');
	}

	/**
	 * 活动列表
	 */
	public function lists() {
    $type = $this->input->get("type", 1);
		echo json_encode($this->consulting_model->getConsultings($type, 1, 100));
	}

	/**
	 * 活动详情
	 */
	public function detail($id) {
		echo json_encode($this->consulting_model->getConsultingDetail(
			$id,
			1,
			100
			));
	}
}
