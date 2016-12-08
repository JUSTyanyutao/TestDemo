package com.mxep.web.controller.goods;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.SysConstant;
import com.mxep.model.goods.AttributeValue;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.AttributeService;
import com.mxep.web.service.AttributeValueService;
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
 * Controller - 特性
 */
@Controller
@RequestMapping("/attribute/value")
public class AttributeValueController extends WebController {

	@Autowired
	private AttributeValueService attributeValueService;

	@Autowired
	private AttributeService attributeService;


	/**
	 *获取所有特性
	 * @return
	 */
	@RequestMapping(value = "/getAttributeValueByAttrbuteId/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getAttributeValueByAttrbuteId(@PathVariable("id")Integer id) {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择");
		list.add(objTemp);
		List<AttributeValue> attributeValueList = attributeValueService.getAttributeValueByAttrbuteId(id);
		for (AttributeValue g : attributeValueList) {
			Map<String, Object> obj = Maps.newHashMap();
			obj.put("id", g.getId());
			obj.put("text", g.getAttrValue()+"("+ g.getPercentum() +")");
			list.add(obj);
		}
		return list;
	}


	/** 特性列表 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		model.addAttribute("attributes",attributeService.findAllAttribute());
		return "attributeValue/list";
	}


	/** 特性列表 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public String agreements(Pagination pagination,
							 HttpServletRequest request, Model model,
							 @RequestParam(value = "search_EQ_status" , required = false)Integer[] status){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_flag",1);
		searchParams.remove("EQ_status");
		if(null != status && status.length > 0)
		{
			searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
		}
		model.addAttribute("page", attributeValueService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
		return "attributeValue/nested";
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
			model.addObject("result", attributeValueService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	/** 删除特性*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {

		return attributeValueService.deleteattributeValue(ids);
	}

	/** 新建特性*/
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "attributeValue/edit";
	}



	/** 编辑特性*/
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id")Integer id, Model model) {
		AttributeValue attributeValue = attributeValueService.get(id);
		attributeValue.setColor(attributeValue.getColor().substring(1));
		attributeValue.setPercentum(attributeValue.getPercentum().substring(0,attributeValue.getPercentum().length()-1));
		model.addAttribute("attributeValue",attributeValue);
		return "attributeValue/edit";
	}

	/**
	 * 启用特性
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id[]")Integer[] ids){
		return attributeValueService.enable(ids);
	}

	/**
	 * 禁用特性
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public JsonMap disable(@RequestParam("id[]")Integer[] ids){
		return attributeValueService.disable(ids);
	}

	/** 保存特性*/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonMap save(AttributeValue attributeValue) {
		attributeValue.setFlag((byte) 1);
		attributeValueService.save(attributeValue);
		return new JsonMap(0, "保存成功");
	}



}

