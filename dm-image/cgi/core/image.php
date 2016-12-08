<?php

include_once('cgi/util/fileHelper.php');
include_once('cgi/util/httpHelper.php');
include_once('cgi/config/Constant.php');
include_once("FileProcess.php");
include_once("cgi/core/UploadHandler.php");

class Image extends UploadHandler implements FileProcess {

    private $paths;
    private $query;
    private $thumb = false;
    private $create = false;
    private $hashKey;
    private $hashFoler;
    private $imgPath;

    public function __construct($path, $query) {
        $this->paths = FileHelper::path_as_array($path);
        $this->query = $query;
        parent::__construct();
    }

    /*
     * @name 图片上传处理
     * @param filename String file名
     * @param watermark Bool 水印
     * @retrun Mixed
     */

    public function doUpload() {
        if (!empty($_FILES)) {
            //检查是否需要裁剪图片
            $thumbKey = array_pop($this->paths);
            $thumbArray = explode('_', $thumbKey);
            $this->thumb = false;
            if ($thumbArray[0] == 'thumb') {
                $dimen = $thumbArray[1];
                if (isset($dimen)) {
                    $this->thumb = $dimen;
                    if (!stristr($dimen, 'x')) {
                        HttpHelper::sendException(Constant::getException("UPLOAD_FILE_NOT_EMPTY"));
                    }
                }
            }
            //图片上传处理
            $data = array();
            $flag = TRUE;
            foreach ($_FILES as $filename => $v) {
                $this->hashKey = FileHelper::uuid(false);
                $this->hashFolder = FileHelper::getHashFolder($this->hashKey, "img");
                $this->imgPath = IMAGE_FOLDER . $this->hashFolder;
                $data[] = $this->upload($filename);
            }
            echo json_encode($data);
        } else {
            HttpHelper::sendException(Constant::getException("UPLOAD_FILE_NOT_EMPTY"));
        }
    }

    private function upload($filename) {
        if (is_dir($this->imgPath)) {
            $userUploadFolderPath = $this->imgPath;
        } else {
            $userUploadFolderPath = FileHelper::createImageFolder($this->imgPath);
        }

        $config['upload_path'] = $userUploadFolderPath;
        $config['allowed_types'] = 'gif|jpg|png|jpeg';
        $config['file_name'] = $this->hashKey;
        $this->setConfig($config);
        if (!$this->_doUpload($filename)) {
            $exception = Constant::getException("UPLOAD_FILE_ERROR");
            $exception["cid"] = $filename;
            $exception["error_msg"] = $this->display_errors();
            return $exception;
        } else {
            $data = array('upload_data' => $this->getData());
            if (empty($data['upload_data'])) {
                return Constant::getException("UNKNOW_ERROR");
            }

            if ($this->thumb) {
                $dimen = explode('x', $this->thumb);
                $descPath = $this->getSavePath($data);
                $imginfo = $this->resize($data['upload_data']['pic_path'], $this->thumb, $descPath, FALSE);
                $data['upload_data']['file_name'] = $this->hashKey . "_" . $this->thumb . $data['upload_data']['file_ext'];
                $data['upload_data']['width'] = $dimen[0];
                $data['upload_data']['height'] = $dimen[1];
            }

            if ($this->create) {
                $file_path = $data['upload_data']['file_path'];
                $file_c_name = $this->hashKey . '_c.jpg';
                $file_z_name = $this->hashKey . '_z.jpg';

                $this->resize($data['upload_data']['pic_path'], $this->create, $file_path . $file_c_name, FALSE, 1);
                $imginfo = $this->resize($data['upload_data']['pic_path'], SQUARE, $file_path . $file_z_name, FALSE, 1);
            } else {
                $imginfo = $data['upload_data']['pic_path'];
            }

            if (empty($imginfo)) {
                return Constant::getException("IMAGE_PROCESS_ERROR");
            } else {
                $width = $data['upload_data']['width'];
                $height = $data['upload_data']['height'];
                $ret['id'] = $this->hashKey;
                $ret['url'] = IMG_DOMAIN . $this->hashFolder . $data['upload_data']['file_name'];
                $ret['width'] = (isset($width) && is_numeric($width)) ? $width : 0;
                $ret['height'] = (isset($height) && is_numeric($height)) ? $height : 0;
                return $ret;
            }
        }
    }

    private function getSavePath($data) {
        return $data['upload_data']['file_path'] . $this->hashKey . "_" . $this->thumb . $data['upload_data']['file_ext'];
    }

    public function compact($file) {
        
    }

    public function resize($sourcePath, $sizes, $newPath, $isShow = TRUE, $type = 2) {
        if (!class_exists('ImageCrop')) {
            include('ImageCrop.php');
        }
        $size = explode('x', $sizes);
        $img = new MyImageCrop($pic_path, $new_path);
        $imginfo = $img->Crop($size[0], $size[1], $type);
        if ($isShow) {
            $this->cache_send($new_path, filemtime($newPath));
        } else {
            $imginfo['new_path'] = $newPath;
            return $imginfo;
        }
    }

}
