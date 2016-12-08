<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Controller - 收藏
 */
class Favorite extends My_Controller {

	public function __construct() {
        parent::__construct();
        $this->load->model("favorite_model");
    }

	/**
	 * 查询收藏
	 */
	public function getList() {
		echo json_encode(
			$this->favorite_model->getList(
						$this->getAccessToken()
				));
	}

    /**
     * 添加收藏
     */
    public function add() {
    	echo json_encode(
    		$this->favorite_model->add(
    			$this->getAccessToken(),
    			$this->input->post('favId'),
                $this->input->post('type')
    			));
    }

    /**
     * 删除收藏
     */
    public function del() {
		echo json_encode(
			$this->favorite_model->del(
				$this->getAccessToken(),
				$this->input->post('favId'),
                $this->input->post('type')
				));
    }
}
