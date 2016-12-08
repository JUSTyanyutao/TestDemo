<?php

defined('BASEPATH') or exit('No direct script access allowed');

/**
 * 地图转换
 */
class Map extends My_Controller {

	public function __construct() {
        parent::__construct();
    }

    /**
     * 微信转百度
     */
    public function MicroMessageToBaidu() {
		$longitude = $this->input->get('longitude');
		$latitude  = $this->input->get('latitude');
        log_message('debug', '转换前的经纬度，latitude=>'.$latitude.',longitude=>'.$longitude);
    	$query = 'http://api.map.baidu.com/geoconv/v1/?coords='.$longitude.','.$latitude.'&from=1&to=5&ak=9uTtAabD0gdofF010TaMjO4s';
        log_message('debug', $query);
    	$result = json_decode(file_get_contents($query));
    	log_message("debug", "经纬度转化，转换后的精度＝".$result->result[0]->x.'纬度＝'.$result->result[0]->y);
    	echo json_encode(array(
    		"longitude" => $result->result[0]->x,
    		"latitude"  => $result->result[0]->y
    		));
    }
}