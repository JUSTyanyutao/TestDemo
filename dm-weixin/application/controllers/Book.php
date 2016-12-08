<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * 菜谱
 */
class Book extends MY_Controller {

	public function __construct() {
		parent::__construct();
		$this->load->model('book_model');
	}

	/**
	 * 菜谱分类
	 */
	public function categories() {
		echo json_encode($this->book_model->getCategories());
	}

	/**
	 * 查询菜谱列表
	 */
	public function findList() {
		echo json_encode($this->book_model->findList(
				$this->input->get('categoryId'),
				$this->input->get('page'),
				$this->input->get('pageSize')
			));
	}

	/**
	 * 菜谱详情
	 */
	public function detail() {
		echo json_encode($this->book_model->detail(
				$this->input->get('bookId')
			));
	}

	/**
	 * 菜单详情
	 */
	public function menuDetail() {
		echo json_encode($this->book_model->menuDetail(
				$this->getAccessToken(),
				$this->input->get('menuId')
			));
	}

	/**
	 * 查询分享菜谱列表
	 */
	public function findShareList() {
		echo json_encode($this->book_model->findShareList(
				$this->input->get('categoryId'),
				$this->input->get('shareId'),
				$this->input->get('page'),
				$this->input->get('pageSize')
			));
	}

	/**
	 * 根据名称搜索菜谱
	 */
	public function searchByName() {
		echo json_encode($this->book_model->searchByName(
				$this->input->get('name'),
				$this->input->get('page'),
				$this->input->get('pageSize')
			));
	}

	/**
	 * 查询用户菜单列表
	 */
	public function findMenuList() {
		echo json_encode($this->book_model->findMenuList(
				$this->getAccessToken(),
				$this->input->get('page'),
				$this->input->get('pageSize')
			));
	}

	/**
	 * 分享
	 */
	public function share() {
		echo json_encode($this->book_model->share(
				$this->getAccessToken(),
				$this->input->post('dinnerTime'),
				$this->input->post('intro'),
				$this->input->post('title')
			));
	}

	/**
	 * 点菜
	 */
	public function submitMenu() {
		echo json_encode($this->book_model->submitMenu(
				$this->getAccessToken(),
				$this->input->post('menuId'),
				$this->input->post('bookIds')
			));
	}

	/**
	 * 删除菜单
	 */
	public function delete() {
		echo json_encode($this->book_model->delete(
				$this->getAccessToken(),
				$this->input->post('bookId')
			));
	}

	/**
	 * 我参与的菜谱
	 */
	public function join() {
		echo json_encode($this->book_model->join(
				$this->getAccessToken(),
				$this->input->get('page'),
				$this->input->get('pageSize')
			));
	}


}
