package com.mxep.web.controller.goods;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.SysConstant;
import com.mxep.model.goods.CarBrand;
import com.mxep.model.goods.Category;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.CarBrandService;
import com.mxep.web.service.CategoryService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller - 车 品牌
 */
@Controller
@RequestMapping("/carBrand")
public class CarBrandController extends WebController {

	@Autowired
	private CarBrandService carBrandService;


	/**
	 *  查找 所有可用的  品牌
	 * @return
     */
	@RequestMapping(value = "/getCarBrand", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> getCarBrand()
	{
		List<Map<String,Object>> list = new ArrayList<>();
		List<CarBrand> carBrandList = carBrandService.findCarBrand();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择");
		for(CarBrand c:carBrandList)
		{
			Map<String,Object> map =new  HashMap<>();
			map.put("id",c.getId());
			map.put("text",c.getName());
			list.add(map);
		}
		return list;
	}




	/**  品牌列表 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "carBrand/list";
	}


	/** 品牌列表 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public String agreements(Pagination pagination,
							 HttpServletRequest request, Model model,
							 @RequestParam(value = "search_EQ_status",required = false)Integer[] status){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag", 1);
		searchParams.remove("EQ_status");
		if(null != status && status.length > 0)
		{
			searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
		}
		model.addAttribute("page", carBrandService.list(pagination, searchParams));
		return "carBrand/nested";
	}

	/** 删除 车 品牌*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		return carBrandService.deleteCarBrand(ids);
	}

	/** 新建  车 的 品牌 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		return "carBrand/edit";
	}



	/** 编辑 车 的品牌 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id")Integer id, Model model) {
		CarBrand carBrand = carBrandService.get(id);
		model.addAttribute("carBrand", carBrand);
		return "carBrand/edit";
	}

	/**
	 * 启用 车的  品牌
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id[]")Integer[] ids){
		return carBrandService.enable(ids);
	}

	/**
	 * 禁用 车的 品牌
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public JsonMap disable(@RequestParam("id[]")Integer[] ids){
		return carBrandService.disable(ids);
	}

	/** 保存 车 的  品牌*/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonMap save(CarBrand carBrand) {
		carBrandService.save(carBrand);
		return new JsonMap(0, "保存成功");
	}



}

