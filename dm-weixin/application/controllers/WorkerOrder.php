<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class WorkerOrder extends MY_Controller {

    public function __construct() {
        parent::__construct();
    }

	public function index() {

	}

	public function lists(){
		$deliveryStatus = $this->input->get("deliveryStatus");
    $page = $this->input->get("page",1);
    $pageSize = $this->input->get("pageSize",10);
		$accessToken = $this->getAccessToken();
		$res = $this->Api_model->getWorkerOrderList($accessToken, $deliveryStatus, $page, $pageSize);
		echo json_encode($res);
	}


	public function accept(){
		$orderId = $this->input->get("orderId");
		$accessToken = $this->getAccessToken();
		$res = $this->Api_model->acceptOrder($accessToken,$orderId);
		echo json_encode($res);
	}


	public function delivery(){
		$orderId = $this->input->get("orderId");
		$barCode = $this->input->get("barCode");
		$accessToken = $this->getAccessToken();
		$res = $this->Api_model->deliveryOrder($accessToken,$orderId,$barCode);
		echo json_encode($res);
	}

	public function uncheckDelivery(){
		$barCode = $this->input->get("barCode");
		$accessToken = $this->getAccessToken();
		$res = $this->Api_model->uncheckDeliveryOrder($accessToken,$barCode);
		echo json_encode($res);
	}

  public function complete(){
		$orderId = $this->input->post("orderId");
		$accessToken = $this->getAccessToken();
		$res = $this->Api_model->completeDeliveryOrder($accessToken,$orderId);
		echo json_encode($res);
	}
}
