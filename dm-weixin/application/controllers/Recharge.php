<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Recharge extends MY_Controller {

    public function __construct() {
        parent::__construct();
    }

	public function index() {

	}

	public function prepaid_list(){
		$res = $this->Api_model->getRechargePrepaid($this->getAccessToken());
		echo json_encode($res);
	}

	public function get_recharge_money($money){
		$res = $this->Api_model->getRechargeMoney($this->getAccessToken(),$money);
		echo json_encode($res);
	}
}
