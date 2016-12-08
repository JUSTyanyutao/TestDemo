<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model - 菜谱
 */
class Book_model extends CI_Model {

	public function __construct() {
		parent::__construct();
		$this->load->library('clientapi');
	}

	/**
	 * 查询菜谱分类
	 *
	 * @return 菜谱分类
	 */
	public function getCategories() {
		$url = BASE_API_URL . '/cookbook/cate';
		return $this->clientapi->get($url);
	}

	/**
	 * 查询菜谱列表
	 *
	 * @param  integer $categoryId 菜谱分类编号
	 * @param  integer $page       页码
	 * @param  integer $pageSize   数据量
	 * @return 菜谱列表
	 */
	public function findList($categoryId = 0, $page = 1, $pageSize = 15) {
		$url = BASE_API_URL . '/cookbook/list/' . $categoryId;
		$param = array(
			'page' => $page,
			'page_size' => $pageSize
			);
		return $this->clientapi->get($url, $param);
	}

	/**
	 * 查询菜谱详情
	 *
	 * @param  integer $bookId 菜谱编号
	 * @return 菜谱详情
	 */
	public function detail($bookId = 0) {
		$url = BASE_API_URL . '/cookbook/detail/' . $bookId;
		return $this->clientapi->get($url);
	}

	/**
	 * 菜单详情
	 *
	 * @param  string  $accessToken 接口调用凭证
	 * @param  integer $menuId      菜单编号
	 * @return 菜单详情
	 */
	public function menuDetail($accessToken = '', $menuId = 0) {
		$url = BASE_API_URL . '/cookbook/my/detail/' . $menuId . '?access_token=' .$accessToken;
		return $this->clientapi->get($url);
	}

	/**
	 * 查询分享的菜谱列表
	 *
	 * @param  integer $categoryId 菜谱分类编号
	 * @param  integer $shareId    分享编号
	 * @param  integer $page       页码
	 * @param  integer $pageSize   数据量
	 * @return 分享的菜谱列表
	 */
	public function findShareList($categoryId = 0, $shareId = 0, $page = 1, $pageSize = 15) {
		$url = BASE_API_URL . '/cookbook/share/list/' . $categoryId;
		$param = array(
			'share_id'  => $shareId,
			'page'      => $page,
			'page_size' => $pageSize
			);
		return $this->clientapi->get($url, $param);
	}

	/**
	 * 根据名称搜索菜谱
	 *
	 * @param  string  $name     菜谱名称
	 * @param  integer $page     页码
	 * @param  integer $pageSize 个数
	 * @return 搜索结果
	 */
	public function searchByName($name = '', $page = 1, $pageSize = 15) {
		$url = BASE_API_URL . '/cookbook/search';
		$param = array(
			'name'      => $name,
			'page'      => $page,
			'page_size' => $pageSize
			);
		return $this->clientapi->get($url, $param);
	}

	/**
	 * 查询用户菜单列表
	 *
	 * @param  string  $accessToken 接口调用凭证
	 * @param  integer $page        页码
	 * @param  integer $pageSize    数据量
	 * @return 菜单列表
	 */
	public function findMenuList($accessToken = '', $page = 1, $pageSize = 15) {
		$url = BASE_API_URL . '/cookbook/my/list';
		$param = array(
			'access_token' => $accessToken,
			'page'         => $page,
			'page_size'    => $pageSize
			);
		return $this->clientapi->get($url, $param);
	}

	/**
	 * 分享
	 *
	 * @param  string $accessToken 接口调用凭证
	 * @param  string $dinnerTime  宴请时间
	 * @param  string $intro       介绍
	 * @param  string $title       主题
	 * @return 分享结果
	 */
	public function share($accessToken = '', $dinnerTime = '', $intro = '', $title = '') {
		$url = BASE_API_URL . '/cookbook/share?access_token=' . $accessToken;
		$param = array(
			'dinner_time' => $dinnerTime,
			'intro'       => $intro,
			'title'       => $title
			);
		return $this->clientapi->post($url, $param);
	}

	/**
	 * 点菜
	 *
	 * @param  string  $accessToken 接口调用平整
	 * @param  integer $menuId      宴请编号
	 * @param  string  $bookIds     菜谱编号
	 * @return 点菜结果
	 */
	public function submitMenu($accessToken = '', $menuId = 0, $bookIds = '') {
		$url   = BASE_API_URL . '/cookbook/share/save?access_token=' . $accessToken;
		$param = array(
				'cookbook_list' => $bookIds,
				'share_id'      => $menuId
			);
		return $this->clientapi->post($url, $param);
	}

	/**
	 * 删除菜谱
	 *
	 * @param  string  $accessToken 接口调用凭证
	 * @param  integer $bookId      菜单编号
	 * @return 删除结果
	 */
	public function delete($accessToken = '', $bookId = 0) {
		$url = BASE_API_URL . '/cookbook/share/delete/' . $bookId . '?access_token=' . $accessToken;
		return $this->clientapi->post($url);
	}

	/**
	 * 我参与的菜单
	 *
	 * @param  string  $accessToken 接口调用凭证
	 * @param  integer $page        页码
	 * @param  integer $pageSize    数据量
	 * @return 菜单信息
	 */
	public function join($accessToken = '', $page = 1, $pageSize = 15) {
		$url = BASE_API_URL . '/cookbook/join/list';
		return $this->clientapi->get($url, array(
				'access_token' => $accessToken,
				'page'         => $page,
				'page_size'    => $pageSize
			));
	}

}
