<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Data_model extends CI_Model {


    public function __construct() {
        parent::__construct();
        $this->authUser = "";
        $this->authPassword = "";
    }

    public function login($user, $password){
        //worker_type_id为1时运营者身份
        $sql = "select a.wid, a.password from eh_worker a where a.account = ? and a.worker_type_id =1";
        $query = $this->db->query($sql,array($user));
        $result = $query->row();
        if(!empty($result)){
            return $password == $result->password;
        }
        return FALSE;
    }


    public function getStations($user, $password, $key){
        $sql = "select a.sid as station_id,a.station_name,a.station_address
                from site_station a where a.station_status =2 ";
        if(!empty($key)){
            $sql .= " and (a.station_name like '%".$key."%' or a.station_address like '%".$key."%') ";
        }
        $query = $this->db->query($sql);
        return $query->result();
    }

    public function getOrders($user, $password, $stationId){
        $sql = "select a.id as order_id, a.order_sn, a.delivery_status,a.delivery_date,a.delivery_section
                from eh_order a where a.station_id = ? and a.delivery_status in (1,2)";
        $query = $this->db->query($sql,array($stationId));
        $result = $query->result();
        return $result;
    }


    public function getOrderDetail($user, $password, $orderId){
        $sql = "select a.id as order_id,a.order_sn,a.bar_code,a.total_price,a.paid_money,
                a.delivery_date,a.delivery_section,a.member_id,a.station_id
                from eh_order a where a.id = ? ";
        $query = $this->db->query($sql,array($orderId));
        $order  = $query->row();

        $sql = "select b.id as goods_id,b.quantity,b.sale_price,b.weight,b.unit,b.unit_name,b.name,b.thumb
        ,b.actual_price,b.actual_weight
         from eh_order_goods b where b.order_id = ?";
        $query = $this->db->query($sql,array($order ->order_id));
        $order->orderGoods = $query->result();

        $sql = "select mobile from eh_member where id = ?";
        $member = $this->db->query($sql,array($order->member_id));
        $order->mobile = $member->row()->mobile;
        //查询站点名称
        $sql = "select station_address from site_station where sid = ?";
        $member = $this->db->query($sql,array($order->station_id));
        $order->station_address = $member->row()->station_address;
        return $order;
    }

    public function updateOrder($user, $password, $orderId, $goods){
        $arr = explode(",", $goods);
        foreach ($arr as $value) {
            $arr1 = explode("||", $value);
            $sql = "select b.* from eh_order_goods b where b.order_id = ? and b.goods_id = ?";
            $query = $this->db->query($sql,array((int)$orderId, (int)$arr1[0]));
            $orderGoods = $query->row();
            if($orderGoods){
               $this->db->where('id', $orderGoods->id);
               $actualPrice = ($orderGoods->sale_price / $orderGoods->weight) * $arr1[1];
               $this->db->update('eh_order_goods', array("actual_weight" => $arr1[1],"actual_price" => $actualPrice));
            }
        }
        return TRUE;
    }



}
