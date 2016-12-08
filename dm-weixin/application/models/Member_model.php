<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * model - 会员
 */
class Member_model extends CI_Model {

	public function __construct() {
        parent::__construct();
        $this->load->library("clientapi");
    }

	/**
	 * 发送短信验证码
	 *
	 * @param  string $mobile 手机号码
	 * @param  integer $type  类型 1 注册 2忘记密码
	 * @return 发送结果
	 */
	public function captcha($mobile = '', $type = 1) {
		$url = BASE_API_URL.'/member/captcha';
        $param = array(
			'account' => $mobile,
			'type'    => $type
            );
        return $this->clientapi->post($url, $param);
	}

	/**
	 * 查询用户的个人信息
	 * 
	 * @param  string $accessToken 接口调用凭证
	 * @return 个人信息
	 */
	public function getMemberInfo($accessToken = '') {
		$url = BASE_API_URL . '/member';
		$param = array(
			'access_token' => $accessToken
			);

		return $this->clientapi->get($url, $param);
	}

	/**
	 * 更新会员信息
	 */
	public function updateMemberInfo($accessToken = '', $avatar = '', $signature = '', $name = '') {
		$url = BASE_API_URL . '/member/saveMember?access_token=' . $accessToken;
		$param = array(
			'avatar_url' => $avatar,
			'signature' => $signature,
			'nick_name' => $name
			);

		return $this->clientapi->post($url, $param);
	}

	/**
	 * 查询积分兑换比率
	 * 
	 * @return 积分兑换比率
	 */
	public function getRatio($accessToken = '') {
		$url   = BASE_API_URL . '/member/points/ratio';
		$param = array(
			'access_token' => $accessToken
			);
		return $this->clientapi->get($url, $param);
	}

	/**
	 * 忘记密码
	 * 
	 * @param  string $account      账号
	 * @param  string $verification 验证码
	 * @return 验证结果
	 */
	public function forgotPassword($account = '', $verification = '') {
		$url   = BASE_API_URL . '/member/reset/password/step1';
		$param = array(
			'account'      => $account,
			'verification' => $verification
			);
		return $this->clientapi->post($url, $param);
	}

	/**
	 * 重置密码
	 * 
	 * @param  string $token          凭证
	 * @param  string $password       新密码
	 * @param  string $repeatPassword 新密码重复
	 * @return 结果
	 */
	public function resetPassword($token = '', $password = '', $repeatPassword = '') {
		$url   = BASE_API_URL . '/member/reset/password/step2';
		$param = array(
			'password'        => $password,
			'repeat_passwd' => $repeatPassword,
			'token'           => $token
			);
		return $this->clientapi->post($url, $param);
	}


}
