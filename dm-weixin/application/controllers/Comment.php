<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Comment extends MY_Controller {

    public function __construct() {
        parent::__construct();
    }

	public function index() {

	}

	public function add(){
		$accessToken = $this->getAccessToken();
		$content = $this->input->post("content");
		$goodsId = $this->input->post("goodsId");
    $cookId = $this->input->post("cookId");
    $orderId = $this->input->post("orderId");
		$labels = $this->input->post("labels");
		$parentId = $this->input->post("parentId");
		$serverId = $this->input->post("serverId");
		$score = $this->input->post("score");

		$parent_id = empty($parentId) ? 0 : $parentId;

		//先上传pics
		$pics = $this->downloadWeixinResource($serverId);
		log_message("debug","upload pics".$pics);

		$labelsArr = array();
		if(!empty($labels)){
			foreach (explode(",", $labels) as $key => $value) {
				$labelsArr[0] = array("id" => $value);
			}
		}
		$param = array("content" => $content,
      "order_id"=>$orderId,
			"goods_id"=>$goodsId,
      "cook_id"=>$cookId,
			"parent_id" =>$parentId,
			"score" => $score*2,
			"labels" => $labelsArr,
			"pics" => $pics
		);
    $res = null;
    if(!empty($cookId)){
	      $this->res = $this->Api_model->addCookComment($accessToken,$param);
    }else{
      	$this->res = $this->Api_model->addComment($accessToken,$param);
    }
		echo json_encode($this->res);
		exit;
	}


	/**
	 * [downloadWeixinResource 根据微信服务器地址下载资源,同时上传到自己服务器]
	 * @param  [type] $serverIds [description]
	 * @return [type]            [description]
	 */
	private function downloadWeixinResource($serverIds) {
        $pics = "";
        if (!empty($serverIds)) {
        	$this->load->model("wx_model");
            foreach (explode(",", $serverIds) as $serverId) {
                $res = $this->wx_model->downloadAndUploadServer($serverId);
                log_message("debug","-----".json_encode($res));
                if (!empty($res)) {
                    $pics .= $res . ",";
                }
            }
        }
        return $pics;
    }


	public function lists(){
		$goodsId = $this->input->get("goodsId");
		$page = $this->input->get("page");
		$pageSize = $this->input->get("pageSize");
		$status = $this->input->get("status");
		$res = $this->Api_model->getComments($goodsId,$page,$pageSize,$status);
		echo json_encode($res);
		exit;
	}

	/**
	 * 大厨评论
	 */
	public function cookComments() {
		echo json_encode($this->Api_model->getCookComments(
				$this->input->get('cookId'),
				$this->input->get('status'),
				$this->input->get('page'),
				$this->input->get('pageSize')
			));
	}

	public function labels(){
		$type = 4;
		$res = $this->Api_model->getLabels($type);
		echo json_encode($res);
		exit;
	}
}
