<?php

if (!defined('BASEPATH')) {
    exit('No direct script access allowed');
}

class Api extends CI_Controller {

    function __construct() {
        parent::__construct();
        $this->load->model("data_model");
    }

    public function index() {

        $data = array("id" =>1,"goodsName" => "大白菜", "price" => 1.78,"unit" => 500, "unitName" => "g","quality" =>6, "barcode" =>"6032953300429");

        echo json_encode($data);
        exit;
    }

    /**
     * 用户登录认证
     * @return [type] [description]
     */
    public function login(){
    	$user = $this->input->get_post("user");
    	$password = $this->input->get_post("password");
        log_message("debug","user and password:" . $user.",".$password);
        if(empty($user) || empty($password)){
            echo json_encode(array("status" =>1,"msg" =>"用户名或者密码为空"));
            exit;
        }
        $flag = $this->data_model->login($user,$password);
    	if($flag){
    		echo json_encode(array("status" =>0,"msg" =>"登录成功"));
    	} else {
			echo json_encode(array("status" =>1,"msg" =>"登录用户名或者密码错误"));
    	}
    	exit;
    }


    /**
     * 获取可用的站点列表
     * @return [type] [description]
     */
    public function stations(){
    	$user = $this->input->get("user");
    	$password = $this->input->get("password");

        $flag = $this->data_model->login($user,$password);
        if(!$flag){
            echo json_encode(array("status" =>1,"msg" =>"登录用户名或者密码错误"));
            exit;
        }
    	$key = $this->input->get("key");
    	$stations = $this->data_model->getStations($user, $password, $key);
    	echo json_encode(array("status" =>0 ,"stations" => $stations));
    }

     /**
     * 用户订单列表
     * @return [type] [description]
     */
    public function orders(){
    	$user = $this->input->get("user");
    	$password = $this->input->get("password");
        $flag = $this->data_model->login($user,$password);
        if(!$flag){
            echo json_encode(array("status" =>1,"msg" =>"登录用户名或者密码错误"));
            exit;
        }

    	$stationId = $this->input->get("station_id");
    	$orderList = $this->data_model->getOrders($user,$password,$stationId);
    	echo json_encode(array("status" =>0,"orders" =>$orderList));
    	exit;
    }

     /**
     * 获取订单详情
     * @return [type] [description]
     */
    public function orderDetail(){
    	$user = $this->input->get("user");
    	$password = $this->input->get("password");

        $flag = $this->data_model->login($user,$password);
        if(!$flag){
            echo json_encode(array("status" =>1,"msg" =>"登录用户名或者密码错误"));
            exit;
        }
    	$orderId = $this->input->get("order_id");
    	$order = $this->data_model->getOrderDetail($user,$password,$orderId);
    	echo json_encode(array("status" =>0,"order" =>$order));
    	exit;
    }

     /**
     * 更新称重后的商品订单数据
     * @return [type] [description]
     */
    public function updateOrder(){
    	$user = $this->input->get_post("user");
    	$password = $this->input->get_post("password");
        log_message("debug","user and password.".$user."-----".$password);
        $flag = $this->data_model->login($user,$password);

        if(!$flag){
            echo json_encode(array("status" =>1,"msg" =>"登录用户名或者密码错误"));
            exit;
        }
    	$orderId = $this->input->get_post("order_id");
    	$goods = $this->input->get_post("goods");
        $orderGoods = array();
        if(!empty($goods)){
            foreach (explode(",", $goods) as $good) {
                $arr = explode("||", $good);
                $goodsId = $arr[0];
                $weight = $arr[1];
                $orderGoods[] = array("id"=>$goodsId,"actualWeight" => $weight);
            }
        }
        $this->load->model("api_model");
        $res = $this->api_model->orderPackage($orderId,$orderGoods);
    	// $this->data_model->updateOrder($user,$password,$orderId,$goods);
        if($res["err_code"] == 0){
            echo json_encode(array("status" =>0,"msg" =>"回写成功"));
        }else{
            echo json_encode(array("status" =>1,"msg" =>"回写失败"));
        }
    	exit;
    }



}
