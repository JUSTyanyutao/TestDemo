package com.mxep.web.controller.goods;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.SysConstant;
import com.mxep.model.goods.Category;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.CategoryService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Controller - 分类
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends WebController {

	@Autowired
	private CategoryService categoryService;


	/**
	 *  根据 type 获取所有可用的 1级分类
	 * @return
	 */
	@RequestMapping(value = "/getCategoryByType/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getCategoryByType(@PathVariable("id")Integer id) {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择分类");
		list.add(objTemp);
		List<Category> categoryList = categoryService.findByType(id);
		for(Category a: categoryList)
		{
			Map<String, Object> temp = Maps.newHashMap();
			temp.put("id",a.getId());
			temp.put("text",a.getName());
			list.add(temp);
		}
		return list;
	}


	/**
	 * 获取所有可用的分类
	 * @return
	 */
	@RequestMapping(value = "/getCategory", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getCategory() {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择分类");
		list.add(objTemp);
		List<Category> categoryList = categoryService.findAll();
		for(Category a: categoryList)
		{
			Map<String, Object> temp = Maps.newHashMap();
			temp.put("id",a.getId());
			temp.put("text",a.getName());
			list.add(temp);
		}
		return list;
	}

	/**
	 * 获取所有可用的根分类
	 * @return
	 */
	@RequestMapping(value = "/getRootCategory", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getRootCategory() {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择分类");
		list.add(objTemp);
		List<Category> categoryList = categoryService.findAllRootCategory();
		for(Category a: categoryList)
		{
			Map<String, Object> temp = Maps.newHashMap();
			temp.put("id",a.getId());
			temp.put("text",a.getName());
			list.add(temp);
		}
		return list;
	}

	/**
	 * 根据根分类  获取 二级分类
	 * @return
	 */
	@RequestMapping(value = "/getCategory/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getCategory(@PathVariable("id")Integer id) {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择分类");
		list.add(objTemp);
		List<Category> categoryList = categoryService.findAllCategory(id);
		for(Category a: categoryList)
		{
			Map<String, Object> temp = Maps.newHashMap();
			temp.put("id",a.getId());
			temp.put("text",a.getName());
			list.add(temp);
		}
		return list;
	}


	/**
	 * 根据id  获取分类
	 * @return
	 */
	@RequestMapping(value = "/getCategoryById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Category getCategoryById(@PathVariable("id")Integer id) {
		return categoryService.get(id);
	}


	/**
	 * 获取分类状态
	 * @return
	 */
	@RequestMapping(value = "/getStatus", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getStatus() {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		Map<String, Object> objTemp1 = Maps.newHashMap();
		Map<String, Object> objTemp2 = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择分类状态");
		list.add(objTemp);
		objTemp1.put("id",0);
		objTemp1.put("text","禁用");
		list.add(objTemp1);
		objTemp2.put("id",1);
		objTemp2.put("text", "启用");
		list.add(objTemp2);
		return list;
	}

	/** 分类列表 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model,
					   @RequestParam(value = "pid",required = false)Integer pid) {
		model.addAttribute("pagination", pagination);
		model.addAttribute("pid",pid);
		return "category/list";
	}


	/** 分类列表 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public String agreements(Pagination pagination,
							 HttpServletRequest request, Model model,
							 @RequestParam(value = "search_EQ_isDisplay",required = false)Integer[] isDisplay,
							 @RequestParam(value = "pid",required = false)Integer pid){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag", 1);
		searchParams.remove("EQ_isDisplay");
		if(null == pid)
		{
			pid = 0;
		}
		searchParams.put("EQ_pid", pid);
		if(null != isDisplay && isDisplay.length > 0)
		{
			searchParams.put("IN_isDisplay", org.apache.commons.lang3.StringUtils.join(isDisplay, ","));
		}
		model.addAttribute("page", categoryService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "sort")));
		return "category/nested";
	}

	/** 删除分类*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		return categoryService.deleteCategory(ids);
	}

	/** 新建分类*/
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		model.addAttribute("rootCategoryList", categoryService.findRootCategoryList());
		return "category/edit";
	}



	/** 编辑分类*/
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id")Integer id, Model model) {
		Category category = categoryService.get(id);
		model.addAttribute("category", category);
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		model.addAttribute("rootCategoryList", categoryService.findRootCategoryList());
		return "category/edit";
	}

	/**
	 * 启用分类
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id[]")Integer[] ids){
		return categoryService.enable(ids);
	}

	/**
	 * 禁用分类
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public JsonMap disable(@RequestParam("id[]")Integer[] ids){
		return categoryService.disable(ids);
	}

	/** 保存分类*/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonMap save(Category category) {
		category.setFlag( (byte) 1);
		categoryService.save(category);
		return new JsonMap(0, "保存成功");
	}



}

