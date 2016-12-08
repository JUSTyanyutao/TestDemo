<?php

if (!defined('BASEPATH')) {
    exit('No direct script access allowed');
}

/**
 * 调用服务器后端的API接口
 */
class Clientapi {

    private $timeout = 15;
    private $ch;
    private $data;
    protected $platformType = PLATEFORM_TYPE;
    protected $platformVersion = PLATEFORM_VERSION;
    protected $appVersion = APP_VERSION;


    private function init() {
        $this->ch = curl_init();
        curl_setopt($this->ch, CURLOPT_POST, 1);
        curl_setopt($this->ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($this->ch, CURLOPT_TIMEOUT, $this->timeout);
        $header = array(
            'Content-Type: application/json',
            'version: '.$this->platformVersion.'',
            'source: '.$this->platformType.''
        );
        curl_setopt($this->ch, CURLOPT_HTTPHEADER, $header);
    }
       

    public function post($url, $param = array()) {
        try {
            self::init();
            $this->data = $param;
            curl_setopt($this->ch, CURLOPT_URL, $url);
            $requestData = json_encode($this->data);
            log_message('debug', 'Request url: [ ' . $url .' ]');
            log_message("debug", "Request data:" . json_encode($param, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT | JSON_UNESCAPED_SLASHES));
            curl_setopt($this->ch, CURLOPT_POSTFIELDS, $requestData);

            $result = curl_exec($this->ch);
            if ($this->ch != null) {
                curl_close($this->ch);
            }
            $responseData = json_decode($result, TRUE);
            log_message("debug", "Response data:" . json_encode($responseData, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT | JSON_UNESCAPED_SLASHES));
        } catch (HttpException $e) {
            log_message("error", "请求命连接异常", $e);
        } catch (Exception $e) {
            log_message("error", "未知错误", $e);
        }
        return $this->parse($responseData);
    }

    public function get($url, $param = array()) {
        try {
            $this->ch = curl_init();
            if(count($param) > 0 ){
                $url = $url . "?" . $this->getUriString($param);
            }
            curl_setopt($this->ch, CURLOPT_URL, $url);
            curl_setopt($this->ch, CURLOPT_RETURNTRANSFER, 1);
            curl_setopt($this->ch, CURLOPT_TIMEOUT, $this->timeout);
            $header = array(
                'Content-Type: application/json',
                'version: '.$this->appVersion.'',
                'source: '.$this->platformType.''
            );
            curl_setopt($this->ch, CURLOPT_HTTPHEADER, $header);
            log_message('debug', 'Request url: [ ' . $url .' ]');
            log_message("debug", "Request data:" . json_encode($param, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT | JSON_UNESCAPED_SLASHES));
            $result = curl_exec($this->ch);
            if ($this->ch != null) {
                curl_close($this->ch);
            }
            $response = json_decode($result, TRUE);
            log_message("debug", "Response data:" . json_encode($response, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT | JSON_UNESCAPED_SLASHES));

            //return $response;
            return $this->parse($response);
        } catch (HttpException $e) {
            log_message("error", "请求服务器连接异常", $e);
        } catch (Exception $e) {
            log_message("error", "无法获取请求服务器数据", $e);
        }
        return NULL;
    }

    public function getUriString($param) {
        $str = "";
        if (isset($param)) {
            foreach ($param as $key => $value) {
                $str.=$key . "=" . urlencode($value) . "&";
            }
        }
        return $str;
    }

    /*
     * @name 解析接口返回的数据
     * @param $data Array
     * @return Mixed
     */

    private function parse($data) {
        if (is_array($data) && isset($data['err_code'])) {
            if ($data['err_code'] == 0) {
                return $data;
            } else if ($data['err_code'] == 20001) {
                log_message('error', 'code：20001 缺少access_token');
                redirectLogin();
            } else if ($data['err_code'] == 20002) {
                log_message('error', 'code：20002 无效access_token');
                setAccessToken('');
                redirectLogin();
            } else if ($data['err_code'] == 20003) {
                log_message('error', 'code：20003 无效的refresh_token');
                setRefreshToken('');
                redirectLogin();
            }

            log_message("error", "获取接口出现异常: " . $data['err_msg']);
            return $data;
        } 
        return array("err_code" =>1,"err_msg" =>"返回数据格式错误");
    }

}
