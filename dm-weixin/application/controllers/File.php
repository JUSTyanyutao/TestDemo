<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * 文件上传
 */
class File extends MY_Controller {

	public function __construct() {
        parent::__construct();
    }

    private $timeout = 15;

    private $ch;

    public function index() {
    	$this->ch = curl_init();
        curl_setopt($this->ch, CURLOPT_POST, 1);
        curl_setopt($this->ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($this->ch, CURLOPT_TIMEOUT, $this->timeout);
        $header = array(
            'multipart/form-data'
        );
        curl_setopt($this->ch, CURLOPT_HTTPHEADER, $header);
        curl_setopt($this->ch, CURLOPT_URL, IMAGE_DOMAIN . '/upload?ftype=image');
        curl_setopt($this->ch, CURLOPT_BINARYTRANSFER, true);
        $data = file_get_contents($_FILES['file']['tmp_name']);
        curl_setopt($this->ch, CURLOPT_POSTFIELDS, array('a'=>new CURLFile('/Users/whan/Downloads/affd356ab1d6079eb9.jpg')));

        $result = curl_exec($this->ch);
        if ($this->ch != null) {
            curl_close($this->ch);
        }
        echo $result;
    }

}

