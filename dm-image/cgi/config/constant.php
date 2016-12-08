<?php

class Constant {


	public static function getException($name){
		$exception = null;
		switch($name){
			case 'UPLOAD_FILE_NOT_EMPTY':
				$exception =  array("error_code" => 9001, "error_msg" => "upload file cannot be empty!");
				break;
			case 'IMAGE_SIZE_UNDEFINED':
				$exception = array("error_code" => 9002, "error_msg" => "cannot set the image  size.");
				break;
			case 'UNKNOW_ERROR':
				$exception = array("error_code" => 9003, "error_msg" => "upload has an exception occured.");
				break;
			case 'UPLOAD_FILE_ERROR':
				$exception = array("error_code" => 9004, "error_msg" => "upload file has an exception occured.");
				break;
			case 'NOT_EMPTY':
				$exception = array("error_code" => 9005, "error_msg" => "User id cannot be empty!");
				break;
			case 'IMAGE_PROCESS_ERROR':
				$exception = array("error_code" => 9006, "error_msg" => "picture process has an exception occured.");
				break;
			case 'IMAGE_SIZE_ILLEGAL_ERROR':
				$exception = array("error_code" => 90007, "error_msg" => "image size illegal error");
				break;
			default:
				break;
		}
		return $exception;

	}
}

