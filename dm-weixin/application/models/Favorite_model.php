<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * model - 收藏
 */
class Favorite_model extends CI_Model {

	public function __construct() {
        parent::__construct();
        $this->load->library("clientapi");
    }

		/**
     *  收藏查询
     *
     * @param  string  $accessToken 接口调用凭证
     * @return 收藏结果
     */
    public function getList($accessToken = '') {
        $url = BASE_API_URL.'/favorite/list?access_token='.$accessToken;
        return $this->clientapi->get($url);
    }

    /**
     * 添加收藏
     *
     * @param  string  $accessToken 接口调用凭证
     * @param  integer $favId       编号
     * @param  integer $type        类型： 1菜品 2菜谱 3大厨 4商家
     * @return 收藏结果
     */
    public function add($accessToken = '', $favId = 0, $type = 1) {
        $url = BASE_API_URL.'/favorite/add?access_token='.$accessToken;
        $param = array(
            'fav_id' => $favId,
            'type'   => $type
            );
        return $this->clientapi->post($url, $param);
    }

    /**
     * 删除收藏
     *
     * @param  string  $accessToken 接口调用凭证
     * @param  integer $favId       编号
     * @param  integer $type        类型
     * @return 删除结果
     */
    public function del($accessToken = '', $favId = 0, $type = 1) {
        $url = BASE_API_URL.'/favorite/delete?access_token='.$accessToken;
        $param = array(
            'fav_id' => $favId,
            'type' => $type
            );
        return $this->clientapi->post($url, $param);
    }

}
