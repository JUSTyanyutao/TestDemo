package com.mxep.web.controller.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.SysConstant;
import com.mxep.model.common.CityTemplate;
import com.mxep.model.goods.FilmBrand;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.service.CityTemplateService;
import com.mxep.web.service.FilmBrandService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Controller - 城市模版
 */
@Controller
@RequestMapping("/city/template")
public class CityTemplateController extends WebController {

	@Autowired
	private CityTemplateService cityTemplateService;


	/**
	 * 获取所有模版
	 * @return
	 */
	@RequestMapping(value = "/getCityTemplate", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getCityTemplate() {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择");
		list.add(objTemp);
		List<CityTemplate> cityTemplateList = cityTemplateService.findAll();
		for (CityTemplate g : cityTemplateList) {
			Map<String, Object> obj = Maps.newHashMap();
			obj.put("id", g.getId());
			obj.put("text", g.getName());
			list.add(obj);
		}
		return list;
	}


	/** 模版列表 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "cityTemplate/list";
	}


	/** 模版列表 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public String agreements(Pagination pagination,
							 HttpServletRequest request, Model model,
							 @RequestParam(value = "search_EQ_status" , required = false)Integer[] status){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.remove("EQ_status");
		if(null != status && status.length > 0)
		{
			searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
		}
		model.addAttribute("page", cityTemplateService.list(pagination, searchParams));
		return "cityTemplate/nested";
	}

	//导出Excel
	@RequestMapping(value = "/export")
	public ModelAndView export(HttpServletRequest request,@RequestParam(value = "search_EQ_status" , required = false)Integer[] status) {
		ModelAndView model = new ModelAndView();
		try {
			model = getExcelView(request);
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
			searchParams.remove("EQ_status");
			if(null != status && status.length > 0)
			{
				searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
			}
			searchParams.put("EQ_flag",1);
			model.addObject("result", cityTemplateService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	/** 删除模版*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		cityTemplateService.delete(ids);
		return new JsonMap(0,"删除成功");
	}


	/** 新建模版*/
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "cityTemplate/edit";
	}

	/** 编辑模版*/
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id")Integer id, Model model) {
		CityTemplate cityTemplate = cityTemplateService.get(id);
		model.addAttribute("cityTemplate",cityTemplate);
		return "cityTemplate/edit";
	}

	/**
	 * 启用模版
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id[]")Integer[] ids){
		return cityTemplateService.enable(ids);
	}

	/**
	 * 禁用模版
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public JsonMap disable(@RequestParam("id[]")Integer[] ids){
		return cityTemplateService.disable(ids);
	}

	/** 保存物品*/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonMap save(CityTemplate cityTemplate) {
		cityTemplateService.save(cityTemplate);
		return new JsonMap(0, "保存成功");
	}



}

