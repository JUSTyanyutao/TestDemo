package com.mxep.web.controller.goods;

import com.mxep.model.goods.CarModel;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.CarModelService;
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
 * Controller - 车 型号
 */
@Controller
@RequestMapping("/carModel")
public class CarModelController extends WebController {

	@Autowired
	private CarModelService carModelService;

	@Autowired
	private CarSeriesService carSeriesService;


	/**  型号列表 */
	@RequestMapping(value="/list/{id}", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model,@PathVariable("id")Integer id) {
		model.addAttribute("pagination", pagination);
		model.addAttribute("carSeries",carSeriesService.get(id));
		return "carModel/list";
	}


	/** 型号列表 */
	@RequestMapping(value="/list/{id}",method = RequestMethod.POST)
	public String agreements(Pagination pagination,
							 HttpServletRequest request, Model model,
							 @PathVariable("id")Integer id,
							 @RequestParam(value = "search_EQ_status",required = false)Integer[] status){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_flag", 1);
		searchParams.put("EQ_seriesId",id);
		searchParams.remove("EQ_status");
		if(null != status && status.length > 0)
		{
			searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
		}
		model.addAttribute("page", carModelService.list(pagination, searchParams));
		return "carModel/nested";
	}

	/** 删除 车 型号*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		return carModelService.deleteCarModel(ids);
	}

	/** 新建  车 的 型号 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String add(@RequestParam("id")Integer id,Model model)
	{
		model.addAttribute("id",id);
		return "carModel/edit";
	}


	/** 编辑 车 的型号 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id")Integer id, Model model) {
		CarModel carModel = carModelService.get(id);
		model.addAttribute("carModel", carModel);
		return "carModel/edit";
	}

	/**
	 * 启用 车的  型号
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id[]")Integer[] ids){
		return carModelService.enable(ids);
	}

	/**
	 * 禁用 车的 型号
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public JsonMap disable(@RequestParam("id[]")Integer[] ids){
		return carModelService.disable(ids);
	}

	/** 保存 车 的  型号*/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonMap save(CarModel carModel) {
		carModelService.save(carModel);
		return new JsonMap(0, "保存成功");
	}



}

