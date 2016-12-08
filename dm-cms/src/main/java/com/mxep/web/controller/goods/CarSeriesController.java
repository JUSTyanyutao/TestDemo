package com.mxep.web.controller.goods;

import com.mxep.model.goods.CarSeries;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.CarBrandService;
import com.mxep.web.service.CarSeriesService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Controller - 车 系列
 */
@Controller
@RequestMapping("/carSeries")
public class CarSeriesController extends WebController {

	@Autowired
	private CarSeriesService carSeriesService;


	@Autowired
	private CarBrandService carBrandService;

	/**  系列列表 */
	@RequestMapping(value="/list/{id}", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model,@PathVariable("id")Integer id) {
		model.addAttribute("pagination", pagination);
		model.addAttribute("carBrand",carBrandService.get(id));
		return "carSeries/list";
	}


	/** 系列列表 */
	@RequestMapping(value="/list/{id}",method = RequestMethod.POST)
	public String agreements(Pagination pagination,
							 HttpServletRequest request, Model model,
							 @PathVariable("id")Integer id,
							 @RequestParam(value = "search_EQ_status",required = false)Integer[] status){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag", 1);
		searchParams.put("EQ_brandId",id);
		searchParams.remove("EQ_status");
		if(null != status && status.length > 0)
		{
			searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
		}
		model.addAttribute("page", carSeriesService.list(pagination, searchParams));
		return "carSeries/nested";
	}

	/** 删除 车 系列*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		return carSeriesService.deleteCarSeries(ids);
	}

	/** 新建  车 的 系列 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String add(@RequestParam("id")Integer id, Model model) {
		model.addAttribute("id",id);
		return "carSeries/edit";
	}



	/** 编辑 车 的系列 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id")Integer id, Model model) {
		CarSeries carSeries = carSeriesService.get(id);
		model.addAttribute("carSeries", carSeries);
		return "carSeries/edit";
	}

	/**
	 * 启用 车的  系列
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id[]")Integer[] ids){
		return carSeriesService.enable(ids);
	}

	/**
	 * 禁用 车的 系列
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public JsonMap disable(@RequestParam("id[]")Integer[] ids){
		return carSeriesService.disable(ids);
	}

	/** 保存 车 的  系列*/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonMap save(CarSeries carSeries) {
		carSeriesService.save(carSeries);
		return new JsonMap(0, "保存成功");
	}



}

