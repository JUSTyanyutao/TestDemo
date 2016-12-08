<?php

if (!defined('BASEPATH')) {
    exit('No direct script access allowed');
}

class Wx_model extends CI_Model {

    private $token;
    private $weclome_content = "欢迎使用怎么吃生活服务平台!";

    public function __construct() {
        parent::__construct();
        $this->load->library("wxapi");
        $this->token = $this->wxapi->getAccessToken();
    }

    public function sendTemplateMsg($param) {
        $url = sprintf(WX_SEND_MESSAGE_TEMPLATE, $this->token);
        $json = $this->wxapi->wx($url, $param);
        return $json;
    }

    public function userInfo($wx_id) {
        $url = sprintf(WX_USER_INFO_URL, $this->token, $wx_id);
        $json = $this->wxapi->wx($url);
        $userInfo = $this->resolve($json, true);
        return $userInfo;
    }

    /**
     * 创建微信底部菜单接口
     * @param type $data
     */
    public function createMenu($data) {
        $url = sprintf(WX_CREATE_MENU_URL, $this->token);
        $json = $this->wxapi->wx($url, $data);
        $this->resolve($json);
    }

    /**
     * 查询微信账号信息
     *
     * @param  string $nextOpenid 下一个微信openid
     * @return 微信账号信息
     */
    public function getUsers($nextOpenid = '') {
        $url = sprintf(WX_GET_USER, $this->token, $nextOpenid);
        $json = $this->wxapi->wx($url);
        return $this->resolve($json, true);
    }

    /**
     * 同步微信账号信息
     *
     * @param  string $wx_id 微信编号
     * @return
     */
    public function syncUser($wx_id = '') {
        if (!empty($wx_id)) {
            $info = $this->userInfo($wx_id);
            $data = array(
                'wx_id' => $wx_id,
                'nickname' => $info['nickname'],
                'sex' => $info['sex'],
                'avatar_url' => $info['headimgurl'],
                'create_time' => date('Y-m-d H:i:s', $info['subscribe_time']),
                'follow_status' => 1
            );
            $this->db->insert('wx_user', $data);
        }
    }

    public function requestQrcode($sceneId) {

        $url = sprintf(WX_QR_URL, $this->token);
        $param = array("action_name" => "QR_LIMIT_SCENE", "action_info" => array("scene" => array("scene_id" => $sceneId)));
        $res = $this->wxapi->wx($url, $param);
        if (empty($res["errcode"])) {
            $path = $this->downloadQrcodeImage($res["ticket"], $sceneId);
            if (!empty($path)) {
                $data = array("scene_id" => $sceneId, "ticket" => $res["ticket"], "qrcode_url" => $res["url"], "url" => $path);
                $this->createQrcodeInfo($sceneId, $data);
            }
        } else {
            echo $res["errmsg"];
            exit;
        }
    }

    public function downloadQrcodeImage($ticket, $sceneId) {
        $url = sprintf(WX_DOWNLOAD_QR_URL, $ticket);
        $res = $this->wxapi->download($url, $sceneId);
        if (!empty($res)) {
            $path = $res["path"];
            return $path;
        }
        return NULL;
    }

    public function createQrcodeInfo($sceneId, $data) {
        $this->db->where("scene_id", $sceneId);
        $query = $this->db->get("sw_qrcode");
        $rows = $query->row_array();
        $data["qrcode_type"] = 1;
        if (!empty($rows)) {
            $this->db->update('sw_qrcode', array("scene_id" => $sceneId), $data);
        } else {
            $data["create_time"] = date('Y-m-d H:i:s');
            $this->db->insert('sw_qrcode', $data);
        }
    }

    public function send($wx_id, $content, $type = 1) {
        if (empty($wx_id) || empty($content) || empty($type)) {
            echo json_encode(array('error' => 1, 'msg' => 'parameter error!'));
            exit;
        }
        $sendUrl = sprintf(WX_SEND_URL, $this->token);
        if ($type == 1) {
            $data = array(
                'touser' => $wx_id,
                'msgtype' => 'text',
                'text' => array(
                    'content' => $content,
                ),
            );
            $json = $this->wxapi->wx($sendUrl, $data);
        } elseif ($type == 2) {
            $json = $this->upload($content);
            $data = array(
                'touser' => $wx_id,
                'msgtype' => 'image',
                'image' => array(
                    'media_id' => $json['media_id'],
                ),
            );
            $json = $this->wxapi->wx($sendUrl, $data);
        }

        $this->resolve($json);
    }

    public function create($data) {
        $this->db->insert('sw_wx_message', $data);
        if ($this->db->affected_rows() == 1) {
            $id = $this->db->insert_id();
            if ($id) {
                return $id;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * [downloadAndUploadServer 下载微信资源并上传服务器]
     * @param  [type] $media_id [description]
     * @return [type]           [description]
     */
    public function downloadAndUploadServer($media_id) {
        $url = sprintf(WX_DOWNLOAD_URL, $this->token, $media_id);
        $in= $this->wxapi->downloadWeixinResource($url);

        $json = $this->wxapi->postStream(BASE_API_URL."/upload", $in);
        $filePath = $this->resolve($json, true);
        $result = $filePath["result"];
        if(count($result) > 0){
            return $result[0]["url"];
        }
        return "";
    }


    public function download($media_id) {
        $url = sprintf(WX_DOWNLOAD_URL, $this->token, $media_id);
        $path = getPath(uuid(false));
        $json = $this->wxapi->download($url, $path);
        $filePath = $this->resolve($json, true);
        return $filePath['url'];
    }

    public function getWxUser($wx_id) {
        $this->db->where("wx_id", (string) $wx_id);
        $query = $this->db->get('wx_user');
        $row = $query->row_array();
        return $row;
    }

    /**
     * OAUTH2认证，根据code获取用户的access_token
     * @param type $code
     * @return string
     */
    public function getOpenidByCode($code) {
        $url = sprintf(WX_OAUTH2_ACCESS_TOKEN_URL, APP_ID, APP_SECRET, $code);
        $json = $this->wxapi->wx($url);
        if (isset($json['errcode'])) {
            return "";
        } else {
            $accessToken = $json["access_token"];
            $expiresIn = $json["expires_in"];
            $refreshToken = $json["refresh_token"];
            $openid = $json["openid"];
            $this->db->where('open_id', $openid);
            $query = $this->db->get('he_member_sns', 1);
            $row = $query->row_array();
            $time = time();
            if (!empty($row)) {
                $this->db->where('open_id', $openid);
                $this->db->update('he_member_sns', array("sns_type" => 1,
                    "expire_at" => $expiresIn, "access_token" => $accessToken, "refresh_token" => $refreshToken, "update_time" => $time));
            } else {
                $this->db->insert('he_member_sns', array("sns_type" => 1, "open_id" => $openid,
                    "expire_at" => $expiresIn, "access_token" => $accessToken, "refresh_token" => $refreshToken, "update_time" => $time));
            }
            return $openid;
        }
    }

    private function upload($file, $type = 'image') {
        $url = sprintf(WX_UPLOAD_URL, $this->token, $type);
        if (!is_file($file)) {
            echo json_encode(array('error' => 1, 'msg' => 'file not existed!'));
            exit;
        }
        $json = $this->wxapi->file($url, $file);
        return $this->resolve($json, true);
    }

    private function resolve($json, $return = false) {
        if (isset($json['errcode']) && !empty($json['errcode'])) {
            echo json_encode(array('error' => 1, 'msg' => $json['errmsg']));
            exit;
        } else {
            if ($return) {
                return $json;
            } else {
                echo json_encode(array('error' => 0));
                exit;
            }
        }
    }

    public function sendMsgToUs() {
        $keyword = "";
        switch ($this->userInput->MsgType) {
            case 'text' :
                $category = 1;
                $keyword = $this->userInput->Content;
                break;

            case 'image' :
                $category = 2;
                break;

            case 'voice' :
                $category = 3;
                break;

            case 'video' :
                $category = 4;
                break;
        }
        $result = $this->searchKeywordResponse($keyword, $this->userInput->FromUserName, $this->userInput->ToUserName);
        if (isset($result)) {
            echo $result;
            exit;
        }
        echo "";
    }

    public function searchKeywordResponse($keyword, $fromUserName, $toUserName) {
        $where = "keyword = '" . $keyword . "'";
        $this->db->where($where);
        $query = $this->db->get("wx_msg_response");
        $rows = $query->row_array();
        $msgType = "text";
        $content = $this->weclome_content;
        if (!empty($rows)) {
            $msgType = $rows["msg_type"];
            $content = $rows["content"];
            return $this->responseMsg($fromUserName, $toUserName, $msgType, $content);
        } else {
            //return getTextResponse($fromUserName, $toUserName, time(), $content);
            return getTransferCustomerService($fromUserName, $toUserName, time());
        }
    }

    public function subscribeResponse($fromUserName, $toUserName) {
        $this->db->where('status', 1);
        $query = $this->db->get("wx_msg_subscribe");
        $rows = $query->row_array();
        if (!empty($rows)) {
            $msgType = $rows["msg_type"];
            $content = $rows["content"];
            return $this->responseMsg($fromUserName, $toUserName, $msgType, $content);
        }
        return getTextResponse($fromUserName, $toUserName, time(), $this->weclome_content);
    }

    private function responseMsg($fromUserName, $toUserName, $msgType, $jsonContent) {
        $responseMsg = "";
        switch ($msgType) {
            case 'text' :
                $jsonData = json_decode($jsonContent);
                $content = $jsonData->content;
                $url = $jsonData->url;
                if (!empty($url)) {
                    $content = "<a href = '" . $url . "'>" . $content . "</a>";
                }
                $msg = sprintf($content);
                $responseMsg = getTextResponse($fromUserName, $toUserName, time(), $msg);
                break;
            case 'news':
                $jsonData = json_decode($jsonContent);
                $items = $jsonData->items;
                $itemXml = array();
                foreach ($items as $item) {
                    $itemXml[] = createItem($item->title, $item->desc, $item->picUrl, $item->url);
                }
                $responseMsg = getNewsResponse($fromUserName, $toUserName, time(), $itemXml);
                break;
        }
        return $responseMsg;
    }

    /**
     * 判断微信用户是否存在
     * @param  [type] $wx_id [description]
     * @return [type]        [description]
     */
    public function isExists($wx_id) {
        $this->db->where('wx_id', $wx_id);
        $query = $this->db->get('wx_user');
        return $query->num_rows() > 0;
    }

}
