<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Controller - 会员
 */
class Member extends MY_Controller {

	public function __construct() {
		parent::__construct();
		$this->load->model('member_model');
	}

	/**
	 * 查询会员信息
	 */
	public function info() {
		echo json_encode($this->member_model->getMemberInfo(
			$this->getAccessToken()
			));
	}

	/**
	 * 更新会员信息
	 */
	public function update() {
		echo json_encode($this->member_model->updateMemberInfo(
			$this->getAccessToken(),
			$this->input->post('avatar'),
			$this->input->post('signature'),
			$this->input->post('name')
			));
	}

	/**
	 * 查询积分兑换比率
	 */
	public function getRatio() {
		echo json_encode($this->member_model->getRatio(
				$this->getAccessToken()
			));
	}

	/**
	 * 忘记密码
	 */
	public function forgotPassword() {
		echo json_encode($this->member_model->forgotPassword(
				$this->input->post('account'),
				$this->input->post('verification')
			));
	}

	/**
	 * 重置密码
	 */
	public function resetPassword() {
		echo json_encode($this->member_model->resetPassword(
				$this->input->post('token'),
				$this->input->post('password'),
				$this->input->post('repeatPassword')
			));
	}

}