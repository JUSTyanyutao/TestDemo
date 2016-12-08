<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Order extends MY_Controller {

    public function __construct() {
        parent::__construct();
    }

	public function index() {

	}

	public function show(){
		$orderStatus = $this->input->get("orderStatus");
    $page = $this->input->get("page",1);
    $pageSize = $this->input->get("pageSize",10);
		$accessToken = $this->getAccessToken();
		$res = $this->Api_model->getOrderList($accessToken, $orderStatus, $page, $pageSize);
		echo json_encode($res);
	}

	public function cancel(){
		$orderId = $this->input->get("orderId");
    $cookId = $this->input->get("cookId");
    $merchantId = $this->input->get("merchantId");
		$accessToken = $this->getAccessToken();
		$res = $this->Api_model->cancelOrder($accessToken, $orderId,$cookId,$merchantId);
		echo json_encode($res);
	}

  public function reminder(){
    $orderId = $this->input->get("orderId");
    $accessToken = $this->getAccessToken();
    $res = $this->Api_model->reminderOrder($accessToken, $orderId);
    echo json_encode($res);
  }

  public function receive(){
		$orderId = $this->input->get("orderId");
		$accessToken = $this->getAccessToken();
		$res = $this->Api_model->receiveOrder($accessToken, $orderId);
		echo json_encode($res);
	}


	public function delete(){
		$orderId = $this->input->get("orderId");
		$accessToken = $this->getAccessToken();
		$res = $this->Api_model->deleteOrder($accessToken, $orderId);
		echo json_encode($res);
	}

	public function detail(){
		$orderId = $this->input->get("orderId");
		$accessToken = $this->getAccessToken();
		$res = $this->Api_model->getOrderDetail($accessToken, $orderId);
		echo json_encode($res);
	}

	public function log(){
		$orderId = $this->input->get("orderId");
		$accessToken = $this->getAccessToken();
		$res = $this->Api_model->getOrderLogs($accessToken, $orderId);
		echo json_encode($res);
	}

}
