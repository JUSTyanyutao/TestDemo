<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class PersonalCenter extends MY_Controller {

    public function __construct() {
        parent::__construct();
    }


	public function index() {

	}

	public function getUser(){
	 	$accessToken = $this->getAccessToken(false);
		if(empty($accessToken)){
			echo json_encode(array("loginCode" => 0, "loginUrl" => $this->getRedirectLoginUrl()));
			exit;
		} else {
			$user = $this->Api_model->getUserInfo($accessToken);
			echo json_encode($user);
			exit;
		}
	}

	/**
	 * 个人收支纪录
	 */
	public function bill() {
		echo json_encode($this->Api_model->bill(
				$this->getAccessToken(),
				1,
				100
			));
	}

  /**
	 * 查找会员退款信息
	 */
	public function refundInfo() {
		echo json_encode($this->Api_model->refundInfo(
				$this->getAccessToken()
			));
	}

  /**
	 * 查找会员退款信息
	 */
	public function refund() {
    $refundMoney = $this->input->post("refundMoney");
		echo json_encode($this->Api_model->refund(
				$this->getAccessToken(),
        $refundMoney
			));
	}

	/**
	 * 查询会员积分明细(免登录)
	 */
	public function pointsNoLogin() {
		$accessToken = $this->getAccessToken(false);
		if (!empty($accessToken)) {
			echo json_encode($this->Api_model->points(
				$accessToken
			));
		} else {
			echo json_encode(array());
		}

	}

	/**
	 * 查询会员积分明细
	 */
	public function points() {
		echo json_encode($this->Api_model->points(
				$this->getAccessToken()
			));
	}

	/**
	 * 积分签到
	 */
	public function signin() {
		echo json_encode($this->Api_model->signin(
				$this->getAccessToken(),
				$this->input->get('signin')
			));
	}

	/**
	 * 获取积分规则
	 */
	public function getPointRules() {
		echo json_encode($this->Api_model->getPointRules());
	}

	/**
	 * 获取积分兑换比例
	 */
	public function getPointRatio() {
		echo json_encode($this->Api_model->getPointRatio());
	}

}
