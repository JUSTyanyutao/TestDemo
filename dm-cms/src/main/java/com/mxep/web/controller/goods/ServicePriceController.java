package com.mxep.web.controller.goods;

import com.mxep.model.goods.CarModel;
import com.mxep.model.goods.ServicePrice;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.CarModelService;
import com.mxep.web.service.CarSeriesService;
import com.mxep.web.service.ServicePriceService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Controller - 服务 价格
 */
@Controller
@RequestMapping("/service/price")
public class ServicePriceController extends WebController {

	@Autowired
	private ServicePriceService servicePriceService;

	

	/**  服务价格列表 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "servicePrice/list";
	}


	/** 服务价格列表 */
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
		model.addAttribute("page", servicePriceService.list(pagination, searchParams));
		return "servicePrice/nested";
	}

	/** 删除 服务价格*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		return servicePriceService.deleteServicePrice(ids);
	}

	/** 新建  服务价格 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String add(Model model)
	{
		return "servicePrice/edit";
	}


	/** 编辑 服务价格 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id")Integer id, Model model) {
		ServicePrice servicePrice = servicePriceService.get(id);
		model.addAttribute("servicePrice", servicePrice);
		return "servicePrice/edit";
	}

	/**
	 * 启用 服务价格
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id[]")Integer[] ids){
		return servicePriceService.enable(ids);
	}

	/**
	 * 禁用  服务价格
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public JsonMap disable(@RequestParam("id[]")Integer[] ids){
		return servicePriceService.disable(ids);
	}

	/** 保存  服务价格*/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonMap save(ServicePrice servicePrice) {
		servicePriceService.save(servicePrice);
		return new JsonMap(0, "保存成功");
	}



}

