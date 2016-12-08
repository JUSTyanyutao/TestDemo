<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class FileHelper {
    
    

    public static function path_as_array($path) {
        if ($path) {
            $path_array = explode('/', $path);
            foreach ($path_array as $value) {
                if ($value) {
                    $array[] = $value;
                }
            }
            return $array;
        }
    }

    public static function create_guid_section($characters) {
        $return = '';
        for ($i = 0; $i < $characters; $i++) {
            $return .= dechex(mt_rand(0, 15));
        }
        return $return;
    }

    /**
     * 生成hash值
     *
     * @param bool $more
     * @param integer $len
     * @return
     */
    public static function uuid($more = true, $len = 5) {
        if ($more) {
            return uniqid(md5(mt_rand()), true);
        } else {
            return uniqid(FileHelper::create_guid_section($len), false);
        }
    }


    public static function getFilePath($dir, $key, $url = FALSE, $fileext = '.jpg') {

        $firstFolder = substr($key, 0, 1);
        $secondFolder = substr($key, 1, 2);
        $lastFolder = substr($key, 3, 2);
        $path = $dir . $firstFolder . '/' . $secondFolder . '/' . $lastFolder . '/' . $key . $fileext;
        die($path);
        if ($url) {
            return IMG_DOMAIN . 'img/' . $firstFolder . '/' . $secondFolder . '/' . $lastFolder . '/' . $key . $fileext;
        }

        if (file_exists($path)) {
            return $dir . $firstFolder . '/' . $secondFolder . '/' . $lastFolder . '/';
        } else {
            return false;
        }
    }

    /**
     * 获取图片所在文件夹
     *
     * @return
     */
    public static function getHashFolder($key, $prefixFolder = "") {
        $firstFolder = substr($key, 0, 1);
        $secondFolder = substr($key, 1, 2);
        $lastFolder = substr($key, 3, 2);
        return $prefixFolder . "/" . $firstFolder . '/' . $secondFolder . '/' . $lastFolder . '/';
    }

    /**
     *
     * 根据用户ID取整和取余，拼装图片存放目录
     *
     */
    public static function getHashFolderByUid($uid, $mod_num) {

        $firstFolder = (int) ($uid / $mod_num);
        $secondFolder = $uid % $mod_num;
        return $firstFolder . '/' . $secondFolder . '/';
    }

    /**
     * 创建用户一系列文件夹
     *
     * @return
     */
    public static function createImageFolder($imgPath) {
        /* 创建用户图片文件夹 */
        mkdir($imgPath, 0755, true);

        return $imgPath;
    }

}
