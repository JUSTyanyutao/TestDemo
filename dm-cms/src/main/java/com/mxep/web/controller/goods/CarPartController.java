package com.mxep.web.controller.goods;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.SysConstant;
import com.mxep.model.goods.CarPart;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.CarPartService;
import com.mxep.web.service.CarPartService;
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
 * Controller - 部位
 */
@Controller
@RequestMapping("/carPart")
public class CarPartController extends WebController {

	@Autowired
	private CarPartService carPartService;
	
	/**
	 * 获取所有可用的部位
	 * @return
	 */
	@RequestMapping(value = "/getCarPart", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getCarPart() {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择部位");
		list.add(objTemp);
		List<CarPart> carPartList = carPartService.findAll();
		for(CarPart a: carPartList)
		{
			Map<String, Object> temp = Maps.newHashMap();
			temp.put("id",a.getId());
			temp.put("text",a.getName());
			list.add(temp);
		}
		return list;
	}
	

	/** 部位列表 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model,
					   @RequestParam(value = "pid",required = false)Integer pid) {
		model.addAttribute("pagination", pagination);
		model.addAttribute("pid",pid);
		return "carPart/list";
	}


	/** 部位列表 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public String agreements(Pagination pagination, HttpServletRequest request, Model model){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		model.addAttribute("page", carPartService.list(pagination, searchParams));
		return "carPart/nested";
	}

	/** 删除部位*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		return carPartService.deleteCarPart(ids);
	}

	/** 新建部位*/
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		return "carPart/edit";
	}



	/** 编辑部位*/
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id")Integer id, Model model) {
		CarPart carPart = carPartService.get(id);
		model.addAttribute("carPart", carPart);
		return "carPart/edit";
	}

	/**
	 * 启用部位
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id[]")Integer[] ids){
		return carPartService.enable(ids);
	}

	/**
	 * 禁用部位
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public JsonMap disable(@RequestParam("id[]")Integer[] ids){
		return carPartService.disable(ids);
	}

	/** 保存部位*/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonMap save(CarPart carPart) {
		carPartService.save(carPart);
		return new JsonMap(0, "保存成功");
	}



}

