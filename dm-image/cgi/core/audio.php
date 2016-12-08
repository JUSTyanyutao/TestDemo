<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class Audio extends UploadHandler implements FileProcess{
    
    
    public function compact($file) {
        echo "success!";
    }

    public function doUpload() {
        echo "success!";
    }

    public function resize($sourcePath, $sizes, $newPath, $isShow, $type){
    	echo "success!";
    }

}

