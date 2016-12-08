<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Main extends MY_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model("Api_model");

    }


	public function index() {
		$result = $this->Api_model->getMainPageData($this->getMarketId());
		// log_message("debug","---------".json_encode($result));
        echo json_encode($result);
        exit;
	}

	public function near() {
		$result = $this->Api_model->getMainPageDataNear($this->input->get('market_id'));
		// log_message("debug","---------".json_encode($result));
        echo json_encode($result);
        exit;
	}

	/**
	 * 查询热门关键字
	 *
	 * @return 热门关键字
	 */
	public function getHotKey() {
		echo json_encode($this->Api_model->hotKey2());
	}


  /**
   * 全局搜索关键词
   *
   * @return 返回搜索结果列表
   */
  public function searchByName() {
    echo json_encode($this->Api_model->searchByName(
            $this->getMarketId(),
            $this->input->get('keyword'),
            $this->input->get('searchType'),
            1,
            20
        ));
  }

	/**
	 * 保存关键字
	 */
	public function searchAdd($goodsId) {
		echo json_encode($this->Api_model->searchAdd($goodsId));
	}


	/**
	 * 查询常见问题解答
	 *
	 * @return 常见问题解答
	 */
	public function faq() {
		echo json_encode($this->Api_model->getFaqs());
	}
}
