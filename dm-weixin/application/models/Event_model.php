<?php

if (!defined('BASEPATH')) {
    exit('No direct script access allowed');
}

class Event_model extends CI_Model {

    public function __construct() {
        parent::__construct();
    }

    public function response() {
        switch ($this->userInput->Event) {
            //关注
            case 'SCAN':
                $this->scan();
                break;
            case 'subscribe':
                $this->subscribe();
                break;

            //取消关注
            case 'unsubscribe':
                $this->unsubscribe();
                break;

            //保存用户地理位置
            case 'LOCATION':
                $this->saveLocation();
                break;

            //点击事件
            case 'CLICK':
                switch ($this->userInput->EventKey) {
                    case "ehfarm":
                        break;
                }
                break;
        }
    }

    /**
     * 微信二维码扫描
     */
    private function scan() {
        //$scene_id = substr($this->userInput->EventKey, 8);
        //log_message('info', 'scene_id is [ '.$scene_id.' ]');
    }

    /**
     * 关注微信时调用(扫描二维码关注和手动查询账号关注)
     */
    private function subscribe() {
        // 判断是否为扫二维码事件
        if (!empty($this->userInput->EventKey) && strncmp($this->userInput->EventKey, 'qrscene_', 8) === 0) {
            $this->userInput->EventKey = substr($this->userInput->EventKey, 8);
            $this->scan();
        }

        log_message('debug', '微信关注事件， Event Key = '. $this->userInput->EventKey);

        $this->load->model('wx_model');
        $userInfo = $this->wx_model->userInfo($this->userInput->FromUserName);
        $data = array(
            'wx_id'            => (string) $this->userInput->FromUserName,
            'nickname'         => $userInfo['nickname'],
            'sex'              => $userInfo['sex'],
            'avatar_url'       => $userInfo['headimgurl'],
            'create_time'      => date('Y-m-d H:i:s'),
            'follow_status'    => 1,
            'expire_time'      => time() + 86400 * 2
        );

        // 判断是否为推广二维码
        if (strncmp($this->userInput->EventKey, 'market_', 7) === 0) {
            // 是推广二维码
            $data['member_market_id'] = substr($this->userInput->EventKey, 7);
        }

        log_message("debug", "create user info");
        $this->createUserInfo($this->userInput->FromUserName, $data);

        // 返回关注后的提示内容
        echo $this->wx_model->subscribeResponse($this->userInput->FromUserName, $this->userInput->ToUserName);
    }

    private function unsubscribe() {
        $this->db->where('wx_id', (string) $this->userInput->FromUserName);
        $this->db->update('wx_user', array(
            "follow_status" => 0,
            "expire_time" => 0
        ));
        //清除缓存
        $this->load->helper('cookie');
        delete_cookie("openid");
    }

    private function saveLocation() {
        $this->db->where('wx_id', (string) $this->userInput->FromUserName);
        $address = "";
        $this->db->update('wx_user', array('longitude' => $this->userInput->Longitude, 'latitude' => $this->userInput->Latitude, 'location' => $address));
        log_message("debug", "save custome(".$this->userInput->FromUserName.") location, longitude: ".$this->userInput->Longitude."   latitude:".$this->userInput->Latitude);
    }

    //------------------------------------------------------------------------

    public function getUserInfo($wx_id) {
        $this->db->where('wx_id', (string) $wx_id);
        $query = $this->db->get('wx_user');
        return $query->row_array();
    }

    /**
     * 创建微信用户
     *
     * 判断用户是否存在，用户不存在，则创建，用户存在，则更新
     *
     * @param  [type] $wx_id 微信id
     * @param  [type] $data  用户信息
     * @return [type]        [description]
     */
    public function createUserInfo($wx_id, $data) {
        $wx_id = (string) $wx_id;

        $old = $this->getUserInfo($wx_id);
        if (empty($old)) {
            $this->db->where('wx_id', $wx_id);
            $this->db->insert('wx_user', $data);
        } else {
            unset($data['member_market_id']);

            $this->db->where('wx_id', $wx_id);
            $this->db->update('wx_user', $data);
        }

        return $this->db->affected_rows();
    }

    //更新用户过期时间
    public function updateUserExpire() {
        if ($this->userInput->MsgType != 'event' || ($this->userInput->Event != 'SCAN' && $this->userInput->Event != 'subscribe' && $this->userInput != 'LOCATION')) {
            $expire = time() + 86400 * 2;
            $this->db->where('wx_id', (string) $this->userInput->FromUserName);
            $this->db->update('wx_user', array('expire_time' => $expire));
        }
    }

    public function createWeixinMenu() {

    }

}
