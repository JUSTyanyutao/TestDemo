<?php

include('cgi/Crop.php');
include("cgi/core/Image.php");
include("cgi/core/Audio.php");
include("cgi/core/Video.php");

/**
 * 控制中心，所有的业务分发将从这里开始
 */
class ControlCenter {

    public static function createUpload($path, $query) {
        $fileProcess = NUll;
        $ftype = $query['ftype'];
        switch ($ftype) {
            case "image" : $fileProcess = new Image($path, $query);
                break;
            case "audio" :$fileProcess = new Audio($path, $query);
                break;
            case "video": $fileProcess = new Video($path, $query);
                break;
            default : $fileProcess = new Image($path, $query);
                break;
        }
        $fileProcess->doUpload();
    }

    public static function createCrop() {
        
    }

}
