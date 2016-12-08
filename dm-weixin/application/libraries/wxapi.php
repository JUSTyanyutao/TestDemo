<?php
defined('BASEPATH') OR exit('No direct script access allowed');

!defined('WXAPI_ROOT') || define('WXAPI_ROOT', dirname(__FILE__) . '/');

/**
 * 微信api
 * User: whan
 * Date: 11/13/15
 * Time: 3:47 PM
 */
class Wxapi {

    /**
     * 超时时间
     */
    private $timeout = 15;

    private $appId;

    private $appSecret;

    public function __construct() {
        $this->CI = &get_instance();
        $this->CI->load->driver('cache');

        $this->appId = APP_ID;
        $this->appSecret = APP_SECRET;
    }

    /**
     * 请求微信接口
     *
     * @param string $url 请求地址
     * @param array $param 请求参数
     * @return mixed 返回结果
     */
    public function wx($url = '', $param = array()) {
        log_message("debug", "response url:" . $url);
        $ch = curl_init($url);
        curl_setopt($ch, CURLOPT_TIMEOUT, $this->timeout);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
        if (!empty($param)) {
            curl_setopt($ch, CURLOPT_POST, true);
            curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($param,JSON_UNESCAPED_UNICODE));
            log_message("debug", "request data:" . json_encode($param,JSON_UNESCAPED_UNICODE));
        }
        $result = curl_exec($ch);
        log_message("debug", "response data:" . $result);
        if ($ch != null) {
            curl_close($ch);
        }
        $json = json_decode($result, true);
        return $json;
    }

    /**
     * Post xml数据
     *
     * @param string $url 请求地址
     * @param string $xmlData xml数据
     * @return SimpleXMLElement 结果对象
     */
    public function postXml($url = '', $xmlData = '') {
        $ch = curl_init($url);
        $header[] = "Content-type: text/xml";
        curl_setopt($ch, CURLOPT_TIMEOUT, $this->timeout);
        curl_setopt($ch, CURLOPT_SSLVERSION, CURL_SSLVERSION_TLSv1);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $header);
        curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
        if (!empty($xmlData)) {
            curl_setopt($ch, CURLOPT_POST, true);
            curl_setopt($ch, CURLOPT_POSTFIELDS, $xmlData);
        }
        $response = curl_exec($ch);
        log_message("debug", "response data:" . $response);
        if ($ch != null) {
            curl_close($ch);
        }
        return simplexml_load_string($response, 'SimpleXMLElement', LIBXML_NOCDATA);
    }

    /**
     * 上传文件
     *
     * @param string $url 地址
     * @param $file
     * @return mixed
     */
    public function file($url = '', $file) {
        $data = array(
            'media' => '@' . $file,
        );
        $ch = curl_init($url);
        curl_setopt($ch, CURLOPT_TIMEOUT, $this->timeout);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
        $result = curl_exec($ch);
        if ($ch != null) {
            curl_close($ch);
        }

        $json = json_decode($result, true);
        return $json;
    }


     /**
     * 下载微信服务器资源并上传自己服务器
     *
     * @param string $url 下载地址
     * @param string $uuid
     * @return array|mixed
     */
    public function downloadWeixinResource($url = '') {
        $ch = curl_init($url);
        curl_setopt($ch, CURLOPT_TIMEOUT, $this->timeout);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        $result = curl_exec($ch);
        curl_close($ch);
        return $result;
    }


    /**
     * 下载
     *
     * @param string $url 下载地址
     * @param string $uuid
     * @return array|mixed
     */
    public function download($url = '', $uuid = '') {
        $ch = curl_init($url);
        curl_setopt($ch, CURLOPT_TIMEOUT, $this->timeout);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        $result = curl_exec($ch);
        $head = curl_getinfo($ch);
        curl_close($ch);
        if ($head['content_type'] != 'text/plain') {
            $arr = explode('/', $head['content_type']);
            $fileExt = '.' . $arr[1];
            $filePath = FILE_BASE_PAHT . $uuid . $fileExt;
            $tp = fopen($filePath, 'a');
            fwrite($tp, $result);
            fclose($tp);
            return array('path' => $filePath);
        } else {
            $json = json_decode($result, true);
            return $json;
        }
    }

    /**
     * 获取微信access_token
     *
     * @param bool|false $refresh 强制刷新
     * @return mixed
     */
    public function getAccessToken($refresh = false) {

        // 判断是否支持缓存
        if (!$this->CI->cache->redis->is_supported()) {

            // redis缓存失效,保存到文件中
            $data = json_decode(file_get_contents(WXAPI_ROOT . "access_token.json"));
            if ($data->expire_time < time()) {
                $url = sprintf(WX_ACCESS_TOKEN_URL, $this->appId, $this->appSecret);
                $res = json_decode($this->httpGet($url));
                $access_token = $res->access_token;
                if ($access_token) {
                    $data->expire_time = time() + 7000;
                    $data->access_token = $access_token;
                    $fp = fopen(WXAPI_ROOT."access_token.json", "w");
                    fwrite($fp, json_encode($data));
                    fclose($fp);
                }
            } else {
                $access_token = $data->access_token;
            }

            log_message('debug', '服务器不支持redis缓存, access_token = '.$access_token);
            return $access_token;
        }

        // 从redis中存取
        $accessToken = $this->CI->cache->redis->get(REDIS_ACCESS_TOKEN);
        log_message('debug', 'access_token = '.$accessToken);
        if ($refresh || empty($accessToken)) {

            // 调用微信服务器接口获取access_token
            $url = sprintf(WX_ACCESS_TOKEN_URL, $this->appId, $this->appSecret);
            $res = json_decode($this->httpGet($url));

            // 判断是否成功获取access_token
            if (empty($res->access_token)) {
                log_message('error', '获取access_token失败, [errcode = '.$res->errcode.', errmsg = '.$res->errmsg.']');
                show_error('服务器忙,数据请求失败!', 200, '服务器忙');
            }

            // 保存到redis中
            $this->CI->cache->redis->save(REDIS_ACCESS_TOKEN, $res->access_token, $res->expires_in - 100);
            $accessToken = $res->access_token;
        } else {
            log_message('debug', '从缓存中读取access_token = '.$accessToken);
        }

        return $accessToken;
    }

    /**
     * 获取微信api_ticket
     *
     *
     * @param bool|false $refresh 强制刷新
     * @return mixed
     */
    public function getJsApiTicket($refresh = false) {

        // 判断是否支持缓存
        if (!$this->CI->cache->redis->is_supported()) {
            // redis缓存失效,保存到文件中
            $data = json_decode(file_get_contents(WXAPI_ROOT . "jsapi_ticket.json"));
            if ($data->expire_time < time()) {
                $url = sprintf(WX_API_TICKET, $this->getAccessToken());
                $res = json_decode($this->httpGet($url));
                $ticket = $res->ticket;
                if ($ticket) {
                    $data->expire_time = time() + 7000;
                    $data->jsapi_ticket = $access_token;
                    $fp = fopen(WXAPI_ROOT."jsapi_ticket.json", "w");
                    fwrite($fp, json_encode($data));
                    fclose($fp);
                }
            } else {
                $ticket = $data->jsapi_ticket;
            }

            log_message('debug', '服务器不支持redis缓存, ticket = '.$ticket);
            return $ticket;
        }

        // 从redis中存取
        $ticket = $this->CI->cache->redis->get(REDIS_API_TICKET);
        log_message('debug', 'ticket = '.$ticket);
        if ($refresh || empty($ticket)) {

            $url = sprintf(WX_API_TICKET, urlencode($this->getAccessToken()));
            $res = json_decode($this->httpGet($url));

            // 判断是否成功获取access_token
            if (empty($res->ticket)) {
                if ($res->errcode == '42001') {
                    // access_token过期，删除access_token
                    $this->CI->cache->redis->delete(REDIS_ACCESS_TOKEN);
                }
                log_message('error', '获取ticket失败, [errcode = '.$res->errcode.', errmsg = '.$res->errmsg.']');
                show_error('服务器忙,数据请求失败!', 200, '服务器忙');
            }

            // 保存到redis中
            $this->CI->cache->redis->save(REDIS_API_TICKET, $res->ticket, $res->expires_in - 100);
            $ticket = $res->ticket;
        } else {
            log_message('debug', '从缓存中读取ticket = '.$ticket);
        }

        return $ticket;
    }

    /**
     * [postMultipart 上传文件]
     * @param  [type] $url  [description]
     * @param  [type] $data [description]
     * @return [type]       [description]
     */
    public function postMultipart($url, $data) {
        $curl = curl_init();
        $header[] = "Content-type: multipart/form-data";
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_TIMEOUT, 500);
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, false);
        curl_setopt($curl, CURLOPT_HEADER, $header);
        curl_setopt($curl, CURLOPT_POST, true);
        curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
        curl_setopt($curl, CURLOPT_URL, $url);
        $res = curl_exec($curl);
        curl_close($curl);
        return $res;
    }


    public function postStream($url, $data){
        $boundary = "---------------------".substr(md5(rand(0,32000)), 0, 10);
        $content = "--$boundary\n";
        $content .= "Content-Disposition: form-data; name=\"weixin\"; filename=\"commentImage.jpg\"\n";
        $content .= "Content-Type: image/jpeg\n";
        $content .= "Content-Transfer-Encoding: binary\n\n";
        $content .= $data."\n";
        $content .= "--$boundary--\n";

        $opts = array(
            'http' => array(
                'method' => 'POST',
                'header' => 'Content-Type:multipart/form-data;boundary='.$boundary,
                'content' => $content
            )
        );
        $context = stream_context_create($opts);
        $response = file_get_contents($url, false, $context);
        $ret = json_decode($response, true);
        return $ret;
    }

    /**
     * 用于获取access_token
     */
    private function httpGet($url) {
        $curl = curl_init();
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_TIMEOUT, 500);
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, false);
        curl_setopt($curl, CURLOPT_URL, $url);

        $res = curl_exec($curl);
        curl_close($curl);

        return $res;
    }
}
