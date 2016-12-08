<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Feedback extends MY_Controller {

    public function __construct() {
        parent::__construct();
    }

	public function index() {
		
	}  

	public function submit(){
		$accessToken = $this->getAccessToken();
		$content = $this->input->post("content");
		$res = $this->Api_model->saveFeedback($accessToken,$content);
		echo json_encode($res);
		exit;
	}
}
